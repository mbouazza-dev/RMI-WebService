package fr.uge.employee;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fr.sharedclasses.IAnnounce;
import fr.sharedclasses.IStore;
import fr.sharedclasses.Product;
import fr.sharedclasses.Rating;
import fr.uge.utils.NotificationObserver;
import fr.uge.utils.PasswordValidator;

@Controller
public class EmployeeController {
	private final IStore store;
	private final Map<String, Integer> sessions = new HashMap<>(); // <sessionId, idEmployee>
	@Autowired
	private EmployeesDB db;
	
	
	public EmployeeController(IStore store) {
		this.store = store;
	}
	
	@GetMapping("/")
	public String index() {
		return "home";
	}
	
	@GetMapping("/login")
	public String login(Model model, HttpSession session) {
		if (sessions.containsKey(session.getId())) {
			return "redirect:/announces";
		}
		model.addAttribute("login", new LoginInformation());
		return "login";
	}
	
	@PostMapping("/login")
	public String submitLogin(@ModelAttribute LoginInformation login, Model model, HttpSession session) {
		Optional<Employee> employeeOptional = db.getById(login.getId());
		
		if (employeeOptional.isPresent() ) { // si un employee possède bien l'id spécifié
			Employee employee = employeeOptional.get();
			if (PasswordValidator.checkPassword(login.getPassword(), employee.getPassword())) { // si le mdp correspond à celui en base
				// attribution d'un sessionId à l'employé
				Optional<String> oldSessionId = sessions.entrySet().stream().filter(entry -> Objects.equals(entry.getValue(), employee.getId())).map(Map.Entry::getKey).findFirst();
				if (oldSessionId.isPresent()) { // si cet employé est déjà connecté
					sessions.remove(oldSessionId.get()); // changement de sessionId
				}
				sessions.put(session.getId(), employee.getId());
				return "redirect:/announces";
			}			
		}
		
		return "redirect:/login";
	}
	
	/**
	 * 
	 * 
	 * @param model
	 * @return
	 * @throws RemoteException
	 */
	@GetMapping("/announces")
	public String listAnnounces(Model model, HttpSession session) throws RemoteException {
		System.out.println("sessionId = " +session.getId()); // pour debug
		System.out.println(sessions); // pour debug
		if (!sessions.containsKey(session.getId())) {
			return "redirect:/login"; // si non connecté on redirige vers la page de connexion
		}
		model.addAttribute("announces", store.getAnnounces());
		return "announces";
	}
	
	/**
	 * 
	 * @param idAnnounce
	 * @param showToast
	 * @param model
	 * @return
	 * @throws RemoteException
	 */
	@GetMapping("/announces/{idAnnounce}")
	public String listProductsOfAnnounce(@PathVariable int idAnnounce, @RequestParam(value="showToast", defaultValue="false") boolean showToast, Model model, HttpSession session) throws RemoteException {
		if (!sessions.containsKey(session.getId())) {
			return "redirect:/login"; // si non connecté on redirige vers la page de connexion
		}
		model.addAttribute("announce", store.getAnnounce(idAnnounce));
		return "announce";
	}
	
	/**
	 * 
	 * @param idAnnounce
	 * @param redirectAttrs
	 * @return
	 * @throws RemoteException
	 */
	@GetMapping("/announces/{idAnnounce}/queue")
	public String addQueueEmployee(@PathVariable int idAnnounce, RedirectAttributes redirectAttrs, HttpSession session) throws RemoteException {
		if (!sessions.containsKey(session.getId())) {
			return "redirect:/login"; // si non connecté on redirige vers la page de connexion
		}
		try {
			int idEmployee = sessions.get(session.getId());
			Optional<Employee> oEmployee = db.getById(idEmployee);
			if(oEmployee.isEmpty()) {
				throw new IllegalStateException("[Database problem] employee with id " + idEmployee + " is not present on database");
			}
			Employee employee = oEmployee.get();
			store.registerClientOnQueue(idAnnounce, employee.getEmail());
			redirectAttrs.addFlashAttribute("toastMessage", "Vous avez été ajouté à la file d'attente.");
		} catch (Exception e) {
			redirectAttrs.addFlashAttribute("toastMessage", "Une erreur est survenue lors de votre ajout à la file d'attente.");
		} finally {
			redirectAttrs.addFlashAttribute("showToast", true);
		}
		return "redirect:/announces/{idAnnounce}";
	}
	
	@GetMapping("/announces/{idAnnounce}/{idProduct}")
	public String buyProduct(@PathVariable int idAnnounce, @PathVariable int idProduct, RedirectAttributes redirectAttrs, HttpSession session) throws RemoteException {
		if (!sessions.containsKey(session.getId())) {
			return "redirect:/login"; // si non connecté on redirige vers la page de connexion
		}
		IAnnounce announce = store.getAnnounce(idAnnounce);
		if (announce.soldProduct(idProduct)) {
			// produit vendu
			redirectAttrs.addFlashAttribute("toastMessage", "Commande validée");
		} else {
			redirectAttrs.addFlashAttribute("toastMessage", "Désolé mais le produit souhaité a déjà été vendu.");
		}
		redirectAttrs.addFlashAttribute("showToast", true);
		return "redirect:/announces/{idAnnounce}";
	}
	
	@GetMapping("/announces/{idAnnounce}/rate")
	public String rateProductGet(@PathVariable int idAnnounce, Model model, HttpSession session) throws RemoteException {
		if (!sessions.containsKey(session.getId())) {
			return "redirect:/login"; // si non connecté on redirige vers la page de connexion
		}
		model.addAttribute("announce", store.getAnnounce(idAnnounce));
		model.addAttribute("ratingAttribute", new Rating());
		return "rate_product_form";
	}
	
	@PostMapping("/announces/{idAnnounce}/rate")
	public String rateProductPost(@PathVariable int idAnnounce, @ModelAttribute("ratingAttribute") Rating rating, Model model, HttpSession session) throws RemoteException {
		if (!sessions.containsKey(session.getId())) {
			return "redirect:/login"; // si non connecté on redirige vers la page de connexion
		}
		store.rateAnnounce(idAnnounce, rating);
		model.addAttribute("announce", store.getAnnounce(idAnnounce));
		return "announce";
	}
	
	@GetMapping("/announces/{idAnnounce}/sold")
	public String soldProductGet(@PathVariable int idAnnounce, Model model, HttpSession session) throws RemoteException {
		if (!sessions.containsKey(session.getId())) {
			return "redirect:/login"; // si non connecté on redirige vers la page de connexion
		}
		model.addAttribute("announce", store.getAnnounce(idAnnounce));
		model.addAttribute("productAttribute", new Product());
		model.addAttribute("stateOptions", Product.getAllState());
		return "sold_product_form";
	}
	
	@PostMapping("/announces/{idAnnounce}/sold")
	public String soldProductPost(@PathVariable int idAnnounce, @ModelAttribute("productAttribute") Product product, Model model, HttpSession session) throws RemoteException {
		if (!sessions.containsKey(session.getId())) {
			return "redirect:/login"; // si non connecté on redirige vers la page de connexion
		}
		store.addProductToAnnounce(idAnnounce, product);
		model.addAttribute("announce", store.getAnnounce(idAnnounce));
		return "announce";
	}
	
}
