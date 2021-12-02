package fr.ifshare.main;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.util.Arrays;

import fr.ifshare.store.Announce;
import fr.ifshare.store.AnnounceObserver;
import fr.ifshare.store.Store;
import fr.sharedclasses.Product;
import fr.sharedclasses.Product.State;
import fr.sharedclasses.Rating;
import fr.uge.bank.AccountManager;
import fr.uge.bank.AccountManagerServiceLocator;

public class Main {

	public static void main(String[] args) {
		try {
			AccountManager manager = new AccountManagerServiceLocator().getAccountManager();
			LocateRegistry.createRegistry(1099);
			Store store = new Store(manager);
			store.addAnnounce(new Announce("Samsung S10", "Le smartphone qui vous immerge dans l'image avec son écran Infinity 6,1\" Full HD+.", new Product("Samsung Galaxy S10", 1, 250.f, State.ALMOST_NEW), Arrays.asList(new String[] {"Samsung S10", "Téléphone", "64Go"}), "Téléphone", new AnnounceObserver()));
			store.addAnnounce(new Announce("VTT ST 120", "Un VTT performant et facile ! Aux commandes du VTT ST 120, vous vous sentez précis et léger.", new Product("VTT ST 120", 2, 299.f, State.NEW), Arrays.asList(new String[] {"VTT", "Sport", "VTT ST 120"}), "Vélo", new AnnounceObserver()));
			store.addAnnounce(new Announce("Le Livre de la Jungle", "Un recueil de nouvelles dont chacune raconte une histoire qui se passe dans la jungle...", new Product("Livre de la jungle", 2, 12.f, State.USED), Arrays.asList(new String[] {"Livre", "Littérature"}), "Livre", new AnnounceObserver()));
			store.addProductToAnnounce(1, new Product("Test",3, 189.f, State.USED));
			store.rateAnnounce(1, new Rating(4.f, "Bon téléphone"));
			store.rateAnnounce(1, new Rating(3.5f, "Rechargement trop long"));

			Naming.rebind("storeService", store);
			System.out.println("Store start");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

}
