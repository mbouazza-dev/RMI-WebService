package fr.ifshare.store;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.stream.Collectors;

import javax.xml.rpc.ServiceException;

import org.apache.axis.AxisFault;

import fr.converter.CurncsrvReturnRate;
import fr.converter.CurrencyServerLocator;
import fr.converter.CurrencyServerSoap;
import fr.converter.CurrencyServerSoapStub;
import fr.sharedclasses.IAnnounceObserver;
import fr.sharedclasses.IAnnounce;
import fr.sharedclasses.Product;
import fr.sharedclasses.Rating;

public class Announce extends UnicastRemoteObject implements IAnnounce {

	private static int SERIAL_ID = 1;
	
	private final int id;
	private final List<String> tags; 
	private final List<Rating> rates;
	private final String label;
	private final String description;
	private final String category;
	private final Map<Integer, Product> products; // <idProduct, Product>
	
	
	public Announce(String label, String description, Product firstProduct, List<String> tags, String category) throws RemoteException {
		super();
		this.id = SERIAL_ID++;
		this.label = Objects.requireNonNull(label);
		this.description = Objects.requireNonNull(description);
		this.tags = new ArrayList<String>(tags);
		this.rates = new ArrayList<>();
		this.products = new HashMap<>();
		this.products.put(firstProduct.getId(), firstProduct);
		this.category = category;
	}
	
	public Announce(String label, String description, Product firstProduct, List<String> tags, String category, IAnnounceObserver observer) throws RemoteException {
		super();
		id = SERIAL_ID++;
		this.label = Objects.requireNonNull(label);
		this.description = Objects.requireNonNull(description);
		this.tags = new ArrayList<String>(tags);
		rates = new ArrayList<>();
		products = new HashMap<>();
		products.put(firstProduct.getId(), firstProduct);
		this.category = category;
	}
	
	// Getters
		
	@Override
	public int getId() throws RemoteException {
		return id;
	}
	
	@Override
	public String getLabel() throws RemoteException {
		return label;
	}
	
	@Override
	public String getDescription() throws RemoteException {
		return description;
	}
	
	@Override
	public String getCategory() throws RemoteException {
		return category;
	}
	
	@Override
	public List<String> getTags() throws RemoteException {
		return tags;
	}
	
	@Override
	public List<Rating> getRates() throws RemoteException {
		return rates;
	}
	
	@Override
	public Product getProduct(int idProduct) throws RemoteException {
		return products.get(idProduct);
	}
	
	@Override
	public double getRate() throws RemoteException {
		return rates.stream().mapToDouble(e -> e.getRate()).average().orElse(0);
	}
	
	@Override
	public List<String> getComments() throws RemoteException {
		return rates.stream().map(r -> r.getComment()).collect(Collectors.toList());
	}
	
	@Override
	public List<Product> getProducts() throws RemoteException {
		return products.values().stream().collect(Collectors.toList());
	}
	
	@Override
	public double getMinPrice() throws RemoteException {
		return products.values().stream().map(p -> p.getPrice()).min(Double::compare).orElse(-1.d);
	}
	
	// Private methods

	
	// Public methods
	
	public boolean empty() {
		return products.isEmpty();
	}
	
	@Override
	public void addProduct(Product product) throws RemoteException {
		if (!products.containsKey(product.getId())) {
			products.put(product.getId(), product);
		}
	}
	
	@Override
	public void addRate(Rating rating) throws RemoteException {
		rates.add(Objects.requireNonNull(rating));
	}
	
	@Override
	public int getMaxIdProduct() throws RemoteException {
		return products.keySet().stream().max(Integer::compareTo).orElse(0);
	}
	
	
	@Override
	public boolean soldProduct(int idProduct) throws RemoteException {
		return products.remove(idProduct) != null;
	}
	
	
	@Override
	public String toString() {
		return "[Announce] (" +id+ ") " +label+ ", " +products.size()+ " produit(s) dispo";
	}
	
}
