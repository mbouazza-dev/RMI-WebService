package fr.uge.employee;

import java.rmi.RemoteException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import fr.ifshare.IStore;

@Controller
public class EmployeeController {
	private final IStore store;
	
	public EmployeeController(IStore store) {
		this.store = store;
	}
	
//	@GetMapping("/login")
//	public String login(Model model) {
//		model.addAttribute("login", new LoginInformation());
//		System.out.println("toto : " + store);
//		return "login";
//	}
//	
//	@PostMapping("/login")
//	public String submitLogin(@ModelAttribute("LoginInformation") LoginInformation login, Model model) {
//		model.addAttribute("login", login);
//		System.out.println("Login : " +login.getId()+ ", password : " +login.getPassword());
//		return "products";
//	}
	
	@GetMapping("/products")
	public String listProducts(Model model) throws RemoteException {
		model.addAttribute("products", store.getProducts());
		store.getProducts().stream().forEach(p -> System.out.println(p.toString())); // pour debug
		return "products";
	}
}
