package fr.ifshare.main;

import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.registry.LocateRegistry;
import java.util.List;

import fr.ifshare.IStore;
import fr.ifshare.Product;
import fr.ifshare.Product.State;
import fr.ifshare.store.Store;

public class Main {
	
	@SuppressWarnings("deprecation")
	private static void setSecurityPolicy(String securityFilePath) {
		System.setProperty("java.security.policy", securityFilePath);
	    if (System.getSecurityManager() == null) {
	        System.setSecurityManager(new RMISecurityManager());
	    }
	}

	public static void main(String[] args) {
		try {
			setSecurityPolicy("resources/rmi_policy/security.policy");
			LocateRegistry.createRegistry(1099);
			IStore store = new Store();
			store.createAnnounce("Samsung S10", "Le smartphone qui vous immerge dans l'image avec son écran Infinity 6,1\" Full HD+.", new Product(1, 250.f, State.ALMOST_NEW), List.of("Samsung S10", "Téléphone", "64Go"));
			store.createAnnounce("VTT ST 120", "Un VTT performant et facile ! Aux commandes du VTT ST 120, vous vous sentez précis et léger.", new Product(2, 299.f, State.NEW), List.of("Vélo", "VTT", "VTT ST 120", "Sport"));
			store.createAnnounce("Le Livre de la Jungle", "Un recueil de nouvelles dont chacune raconte une histoire qui se passe dans la jungle...", new Product(2, 12.f, State.USED), List.of("Livre", "Rudyard Kipling", "Littérature"));
			store.addProductToAnnounce(1, new Product(3, 189.f, State.USED));
			store.buyProduct(3, 3, 5);
			store.rateAnnounce(1, 4.f, "Bon téléphone");
			store.rateAnnounce(1, 3.5f, "Rechargement trop long");

			Naming.rebind("storeService", store);
			System.out.println("Store start");
			
		} catch (Exception e) {
			System.out.println("Erreur");
		}
	}

}
