package fr.ifshare.store;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.xml.rpc.ServiceException;

import fr.converter.CurncsrvReturnRate;
import fr.converter.CurrencyServerLocator;
import fr.converter.CurrencyServerSoap;
import fr.sharedclasses.IAnnounce;
import fr.sharedclasses.IStore;
import fr.sharedclasses.Product;
import fr.sharedclasses.Rating;
import fr.uge.bank.AccountManager;
import fr.uge.bank.AccountManagerServiceLocator;

public class Store extends UnicastRemoteObject implements IStore {
	private final HashMap<Integer, Announce> announces; // <idAnnounce, Announce>
	private final AnnounceObserver announceObserver;
	private final AccountManager accountManager;

	public Store(AccountManager accountManager) throws RemoteException {
		super();
		this.accountManager = accountManager;
		announces = new HashMap<>();
		announceObserver = new AnnounceObserver();
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
	public boolean buyProduct(int idAnnounce, int idProduct, int idEmployee) throws RemoteException {
		if (announces.containsKey(idAnnounce)) {
			Announce announce = announces.get(idAnnounce);
			Product product = announce.getProduct(idProduct);
			System.out.println("bank : " +accountManager.amount(idEmployee));
			System.out.println("produit : " +product.getPrice());
			if (accountManager.amount(idEmployee) < product.getPrice()) {
				// Vente impossible
				return false;
			}
			announce.soldProduct(idProduct);
			accountManager.withdraw(product.getPrice(), idEmployee); // on retire l'argent du compte de l'acheteur
			accountManager.deposit(product.getPrice(), product.getIdEmployee()); // on ajoute l'argent sur le compte du vendeur
			return true;
		}
		return false;
	}

	@Override
	public void rateAnnounce(int idAnnounce, Rating rating) throws RemoteException {
		if (announces.containsKey(idAnnounce)) {
			Announce announce = announces.get(idAnnounce);
			announce.addRate(rating);
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
			product.setId(announce.getMaxIdProduct() + 1);
			if(announce.empty() && announceObserver.containsWaiters(idAnnounce)) {
				announceObserver.onReplenishment(announce);
			}
			announce.addProduct(product);
		}  else {
			throw new IllegalStateException("The announce with id " + idAnnounce + " does not exists");
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

	@Override
	public double convertPrice(String fromCurrency, String toCurrency) throws RemoteException {
		CurrencyServerSoap converter;
		Double minprice = null;
		try {
			converter = new CurrencyServerLocator().getCurrencyServerSoap();
			for (IAnnounce anc : getAnnounces()) {
				for (Product pdt : anc.getProducts()) {
					pdt.setPrice((double) converter.convert("", fromCurrency, toCurrency, pdt.getPrice(), true, "", CurncsrvReturnRate.curncsrvReturnRateNumber, "", ""));
					minprice = anc.getMinPrice();
				}
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return minprice;
	}
	
	@Override
	public void registerClientOnQueue(int announceId, String mail) throws RemoteException{
		Announce announce = announces.get(announceId);
		if(announce == null) {
			throw new IllegalStateException("this announce with id " + announceId + " does not exist.");
		}
		if(!announce.empty()) {
			throw new IllegalStateException("this announce with id " + announceId + " still contains products.");
		}
		announceObserver.register(announceId, mail);
	}
	
	@Override
	public void UnregisterClientsOnReplenishment(int announceId, String mail) throws RemoteException {
		announceObserver.unregister(announceId, mail);
	}
}
