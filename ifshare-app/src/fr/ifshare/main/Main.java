package fr.ifshare.main;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import fr.ifshare.IStore;
import fr.ifshare.store.Store;

public class Main {

	public static void main(String[] args) {
		try {
			LocateRegistry.createRegistry(1099);
			IStore store = new Store();

			Naming.rebind("storeService", store);
			System.out.println("Store start");
			
		} catch (Exception e) {
			System.out.println("Erreur");
		}
	}

}
