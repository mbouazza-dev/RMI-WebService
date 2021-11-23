package fr.ifshare.main;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.util.Arrays;

import fr.ifshare.store.Announce;
import fr.ifshare.store.NotificationReplenishmentObserver;
import fr.ifshare.store.Store;
import fr.sharedclasses.Product;
import fr.sharedclasses.Product.State;
import fr.sharedclasses.Rating;

public class Main {

	public static void main(String[] args) {
		try {
			LocateRegistry.createRegistry(1099);
			Store store = new Store();
			store.addAnnounce(new Announce("Samsung S10", "Le smartphone qui vous immerge dans l'image avec son écran Infinity 6,1\" Full HD+.", new Product("Samsung Galaxy S10", 1, 250.f, State.ALMOST_NEW), Arrays.asList(new String[] {"Samsung S10", "Téléphone", "64Go"}), "Téléphone", new NotificationReplenishmentObserver()));
			store.addAnnounce(new Announce("VTT ST 120", "Un VTT performant et facile ! Aux commandes du VTT ST 120, vous vous sentez précis et léger.", new Product("VTT ST 120", 2, 299.f, State.NEW), Arrays.asList(new String[] {"VTT", "Sport", "VTT ST 120"}), "Vélo", new NotificationReplenishmentObserver()));
			store.addAnnounce(new Announce("Le Livre de la Jungle", "Un recueil de nouvelles dont chacune raconte une histoire qui se passe dans la jungle...", new Product("Livre de la jungle", 2, 12.f, State.USED), Arrays.asList(new String[] {"Livre", "Littérature"}), "Livre", new NotificationReplenishmentObserver()));
			store.addProductToAnnounce(1, new Product("Test",3, 189.f, State.USED));
			store.buyProduct(3, 3, 5);
			store.rateAnnounce(1, new Rating(4.f, "Bon téléphone"));
			store.rateAnnounce(1, new Rating(3.5f, "Rechargement trop long"));

			Naming.rebind("storeService", store);
			System.out.println("Store start");
			
			// Décomenter et utiliser un point d'arrêt pour tester le réapprovisionnement
			//store.addProductToAnnounce(3, new Product("Toto", 5, 200.f, State.ALMOST_NEW));
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

}
