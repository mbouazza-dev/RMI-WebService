package fr.ifshare.main;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import fr.ifshare.Employee;
import fr.ifshare.IStore;
import fr.ifshare.Product;
import fr.ifshare.Product.State;
import fr.ifshare.store.Store;
import fr.ifshare.store.StoreDB;

public class Main {

	public static void main(String[] args) {
		try {
			LocateRegistry.createRegistry(1099);
			IStore store = new Store();
			store.addProduct("Vélo", 0);
			store.addProduct("Livre", 1);

			Naming.rebind("storeService", store);
			System.out.println("Store start");
			StoreDB db = new StoreDB();
			db.createNewDatabase("storedb");
			db.createTable("storedb");
			db.insert("storedb",new Product("iPhone 13", 1, 1300, State.ALMOST_NEW));
			db.getProducts("storedb");
			
		} catch (Exception e) {
			System.out.println("Erreur");
		}
	}

}
