package fr.ifshare.main;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.util.List;

import fr.ifshare.IStore;
import fr.ifshare.Product;
import fr.ifshare.Product.State;
import fr.ifshare.store.Store;

public class Main {

	public static void main(String[] args) {
		try {
			LocateRegistry.createRegistry(1099);
			IStore store = new Store();
			store.createAnnounce("Samsung S10", "Le smartphone qui vous immerge dans l'image avec son écran Infinity 6,1\" Full HD+.", new Product(1, 250.f, State.ALMOST_NEW), List.of("Samsung S10", "Téléphone", "64Go"));
			store.createAnnounce("VTT ST 120", "Un VTT performant et facile ! Aux commandes du VTT ST 120, vous vous sentez précis et léger.", new Product(2, 299.f, State.NEW), List.of("Vélo", "VTT", "VTT ST 120", "Sport"));
			store.addProductToAnnounce(1, new Product(3, 189.f, State.USED));

			Naming.rebind("storeService", store);
			System.out.println("Store start");
			
		} catch (Exception e) {
			System.out.println("Erreur");
		}
	}

}
