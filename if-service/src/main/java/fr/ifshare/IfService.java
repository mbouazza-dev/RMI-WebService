package fr.ifshare;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import fr.sharedclasses.IAnnounce;
import fr.sharedclasses.IStore;
import fr.sharedclasses.Product;

public class IfService {
	private final IStore store;
	private final List<BasketItem> basket = new ArrayList<>();;
	
	public IfService() throws MalformedURLException, RemoteException, NotBoundException {
		this.store = (IStore) Naming.lookup("storeService");
	}
	
	/**
	 * Gets minimum price of the specified announce.
	 * @param idAnnounce
	 * @return
	 * @throws RemoteException
	 */
	public double getMinPrice(int idAnnounce) throws RemoteException {
		IAnnounce announce = store.getAnnounce(idAnnounce);
		if (announce == null) {
			return -1;
		}
		return announce.getMinPrice();
	}
	
	public double getProductPrice(int idAnnounce, int idProduct) throws RemoteException {
		IAnnounce announce = store.getAnnounce(idAnnounce);
		if (announce == null) {
			return -1;
		}
		Product product = announce.getProduct(idProduct);
		if (product == null) {
			return -1;
		}
		return product.getPrice();
	}
	
	public boolean isAvailable(int idAnnounce) throws RemoteException {
		IAnnounce announce = store.getAnnounce(idAnnounce);
		if (announce == null) {
			return false;
		}
		return announce.getProducts().size() > 0;
	}
	
	public boolean addProductToBasket(int idAnnounce, int idProduct) throws RemoteException {
		IAnnounce announce = store.getAnnounce(idAnnounce);
		if (announce == null) {
			return false;
		}
		Product product = announce.getProduct(idProduct);
		if (product == null) {
			return false;
		}
		return basket.add(new BasketItem(idAnnounce, product));
	}
	
	public boolean removeProductFromBasket(int idProduct) {
		BasketItem itemToRemove = null;
		for (BasketItem item : basket) {
			if (item.getIdProduct() == idProduct) {
				itemToRemove = item;
				break;
			}
		}
		if (itemToRemove == null) {
			return false;
		}
		return basket.remove(itemToRemove);
	}
	
	public String getBasket() {
		StringBuilder bld = new StringBuilder();
		for (BasketItem p : basket) {
			bld.append(p.getProduct().getLabel());
			bld.append(";");
		}
		return bld.toString();
	}
	
	public String buyBasket() throws RemoteException {
		String result = "Les produits suivants ont été achetés : \n";
		String error = "Les produits suivants n'ont pas été achetés à cause d'une erreur : \n";
		for (BasketItem item : basket) {
			Product product = item.getProduct();
			if (store.buyProduct(item.getIdAnnounce(), product.getId(), 99)) {
				result += "(" +product.getId()+ ") " +product.getLabel()+ "\n";
			} else {
				error += "(" +product.getId()+ ") " +product.getLabel()+ "\n";
			}
		}
		return result + "\n" + error;
	}
	
	
}
