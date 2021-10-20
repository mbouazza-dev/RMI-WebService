package fr.uge.main;

import java.rmi.Naming;
import java.util.Scanner;

import fr.ifshare.IStore;

public class Main {

	public static void main(String[] args) {
		try {
			IStore store = (IStore) Naming.lookup("storeService");
			
			try (Scanner scanner = new Scanner(System.in)) {
				while (scanner.hasNextLine()) {
					String line = scanner.nextLine();
					
					if (line.startsWith("1-")) {
						// list products
						System.out.println(store.getProducts());
						
					} else if (line.startsWith("2-")) {
						// get one product : 2- id
						String[] extractor = line.split(" ");
						int id = Integer.parseInt(extractor[1]);
						System.out.println(store.getProduct(id));
						
					} else if (line.startsWith("3-")) {
						// rate product : 3- id rate
						String[] extractor = line.split(" ");
						int id = Integer.parseInt(extractor[1]);
						float rate = Float.parseFloat(extractor[2]);
						
						store.rateProduct(id, rate);
					} else if (line.startsWith("4-")) {
						// add product : 4- label idEmployee
						String[] extractor = line.split(" ");
						int id = Integer.parseInt(extractor[2]);
						store.addProduct(extractor[1], id);
					}
				}
			}
		} catch (Exception e) {
			System.out.println("Erreur");
		}

	}

}
