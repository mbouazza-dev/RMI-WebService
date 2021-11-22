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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fr.sharedclasses.IAnnounce;
import fr.sharedclasses.IStore;
import fr.uge.utils.NotificationObserver;

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
		return "redirect:/announces";
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
		Employee employee = new Employee(login.getId(), "toto", "titi", 500, "test"); // call db instead
		
		// TODO : Check password here
		// if ok : save sessionId in sessions
		Optional<String> oldSessionId = sessions.entrySet().stream().filter(entry -> Objects.equals(entry.getValue(), employee.getId())).map(Map.Entry::getKey).findFirst();
		if (oldSessionId.isPresent()) {
			sessions.remove(oldSessionId.get()); // déjà connecté : changement de sessionId
		}
		sessions.put(session.getId(), employee.getId());
		// if ko : redirect to /login with error message
		
		System.out.println("Login : " +login.getId()+ ", password : " +login.getPassword()); // pour debug
		
		return "redirect:/announces";
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
	 * @param idEmployee
	 * @param redirectAttrs
	 * @return
	 * @throws RemoteException
	 */
	@GetMapping("/announces/{idAnnounce}/{idEmployee}")
	public String addQueueEmployee(@PathVariable int idAnnounce, @PathVariable int idEmployee, RedirectAttributes redirectAttrs, HttpSession session) throws RemoteException {
		if (!sessions.containsKey(session.getId())) {
			return "redirect:/login"; // si non connecté on redirige vers la page de connexion
		}
		IAnnounce announce = store.getAnnounce(idAnnounce);
		announce.registerObserver(new NotificationObserver(db));
		if (announce.notifyEmployee(idEmployee)) {
			redirectAttrs.addFlashAttribute("toastMessage", "Vous avez été ajouté à la file d'attente.");
		} else {
			redirectAttrs.addFlashAttribute("toastMessage", "Une erreur est survenue lors de votre ajout à la file d'attente.");
		}
		redirectAttrs.addFlashAttribute("showToast", true);
		return "redirect:/announces/{idAnnounce}";
	}
	
}
