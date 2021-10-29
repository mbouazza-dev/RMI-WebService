package fr.ifshare.store;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import fr.ifshare.IStore;
import fr.ifshare.Product;

public class Store extends UnicastRemoteObject implements IStore {
	private final HashMap<Integer, Product> products;

	public Store() throws RemoteException {
		super();
		products = new HashMap<>();
	}

	@Override
	public Product getProduct(int idProduct) throws RemoteException {
		return products.get(idProduct);
	}

	@Override
	public List<Product> getProducts() throws RemoteException {
		return products.values().stream().collect(Collectors.toList());
	}
	
	@Override
	public void addProduct(String label, int idEmployee) throws RemoteException {
		Product newProduct = new Product(label, idEmployee);
		products.put(newProduct.getId(), newProduct);
	}

	@Override
	public void buyProduct(int idProduct, int idEmployee) throws RemoteException {
		if (products.containsKey(idProduct)) {
			products.remove(idProduct);
			// logging de l'event
			// appel service banque
		}
	}

	@Override
	public void rateProduct(int idProduct, float rate) throws RemoteException {
		products.computeIfPresent(idProduct, (x, y) -> {
			y.addRate(rate);
			// logging de l'event
			return y;
		});
	}

}
