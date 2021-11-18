package fr.ifshare.store;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import fr.sharedclasses.IAnnounce;
import fr.sharedclasses.IStore;
import fr.sharedclasses.Product;
import fr.sharedclasses.Rating;

public class Store extends UnicastRemoteObject implements IStore {
	private final HashMap<Integer, Announce> announces; // <idAnnounce, Announce>

	public Store() throws RemoteException {
		super();
		announces = new HashMap<>();
	}
	
	public void addAnnounce(Announce announce) throws RemoteException {
		Objects.requireNonNull(announces);
		announces.put(announce.getId(), announce);
	}

	@Override
	public Product getProduct(int idAnnounce, int idProduct) throws RemoteException {
		return announces.get(idAnnounce).getProduct(idProduct);
	}

	@Override
	public List<Product> getProducts(int idAnnounce) throws RemoteException {
		return announces.get(idAnnounce).getProducts();
	}

	@Override
	public void buyProduct(int idAnnounce, int idProduct, int idEmployee) throws RemoteException {
		if (announces.containsKey(idAnnounce)) {
			Announce announce = announces.get(idAnnounce);
			announce.soldProduct(idProduct);
		}
	}

	@Override
	public void rateAnnounce(int idAnnounce, double rate, String comment) throws RemoteException {
		if (announces.containsKey(idAnnounce)) {
			Announce announce = announces.get(idAnnounce);
			announce.addRate(new Rating(rate, comment));
			announces.put(idAnnounce, announce);
		}
	}

	@Override
	public void createAnnounce(String label, String description, Product firstProduct, List<String> tags, String category)
			throws RemoteException {
		Announce announce = new Announce(label, description, firstProduct, tags, category);
		announces.put(announce.getId(), announce);
	}

	@Override
	public void addProductToAnnounce(int idAnnounce, Product product) throws RemoteException {
		if (announces.containsKey(idAnnounce)) {
			Announce announce = announces.get(idAnnounce);
			announce.addProduct(product);
		}
	}

	@Override
	public IAnnounce getAnnounce(int idAnnounce) throws RemoteException {
		return announces.get(idAnnounce);
	}

	@Override
	public List<IAnnounce> getAnnounces() throws RemoteException {
		return announces.values().stream().collect(Collectors.toList());
	}

}
