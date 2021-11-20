package fr.uge.main;

import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import fr.sharedclasses.IStore;

@SpringBootApplication
@ComponentScan(basePackages = "fr.uge")
public class Main {
//	private static List<Command> commands = Arrays.asList(
//			// command, usage, description, action
//			new Command("list", "list", "Lister tous les produits disponibles.", (store, params) -> { System.out.println(store.getProducts()); }),
//			new Command("get", "get \\d+", "Obtenir les informations d'un produit en particulier.", (store, params) -> { System.out.println(store.getProduct(Integer.parseInt(params[1]))); }),
//			new Command("add", "add \\w+ \\d+", "Ajouter un produit au catalogue.", (store, params) -> { store.addProduct(params[1], Integer.parseInt(params[2])); }),
//			// le pattern est à vérifier
//			new Command("rate", "rate \\d+ \\d(\\.\\d)? [\\w\\s',]+", "Noter un produit", (store, params) -> { store.rateProduct(Integer.parseInt(params[1]), Double.parseDouble(params[2]), params[3]); })
//		);
	
	/**
	 * Affiche les commandes disponibles dans la console.
	 */
	private static void displayHelp() {
		System.out.println("Liste des commandes disponibles :");
		//System.out.println(commands.stream().map(Command::toString).collect(Collectors.joining("\n")));
	}

	public static void main(String[] args) throws RemoteException {
		SpringApplication.run(Main.class, args);
		try {
			displayHelp();
		} catch (Exception e) {
			System.err.println("Erreur");
		  }	
		
//		try {
//			IStore store = (IStore) Naming.lookup("storeService");
//			
//			try (Scanner scanner = new Scanner(System.in)) {
//				while (scanner.hasNextLine()) {
//					String line = scanner.nextLine();
//					if (line.isEmpty()) {
//						continue;
//					}
//					String[] tokens = line.split(" ");
//
//					Optional<Command> cmd = commands.stream().filter(e -> Pattern.matches(e.getUsage(), line)).findFirst();
//					if (cmd.isPresent()) {
//						cmd.get().getAction().accept(store, tokens);
//					} else {
//						System.out.println("Commande inconnue");
//					}
//				}
//			}
//		} catch (Exception e) {
//			
//		}
	}

}
