package fr.sharedclasses;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Announce implements Serializable {

	private static int SERIAL_ID = 1;
	
	private final int id;
	private final List<String> tags; 
	private final List<Rating> rates;
	private final String label;
	private final String description;
	private final String category;
	private final Map<Integer, Product> products; // <idProduct, Product>
	
	
	public Announce(String label, String description, Product firstProduct, List<String> tags, String category) {
		this.id = SERIAL_ID++;
		this.label = Objects.requireNonNull(label);
		this.description = Objects.requireNonNull(description);
		this.tags = new ArrayList<String>(tags);
		this.rates = new ArrayList<>();
		this.products = new HashMap<>();
		this.products.put(firstProduct.getId(), firstProduct);
		this.category = category;
	}
		
	public int getId() {
		return id;
	}
	
	public String getLabel() {
		return label;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getCategory() {
		return category;
	}

	public List<String> getTags() {
		return tags;
	}

	public List<Rating> getRates() {
		return rates;
	}

	public void addProduct(Product product) {
		if (!products.containsKey(product.getId())) {
			products.put(product.getId(), product);
		}
	}
	
	public void addRate(Rating rating) {
		rates.add(rating);
	}
	
	public Product getProduct(int idProduct) {
		return products.get(idProduct);
	}
	
	public double getRate() {
		return rates.stream().mapToDouble(e -> e.getRate()).average().orElse(0);
	}
	
	public List<String> getComments() {
		return rates.stream().map(r -> r.getComment()).collect(Collectors.toList());
	}
	
	public List<Product> getProducts() {
		return products.values().stream().collect(Collectors.toList());
	}
	
	public void soldProduct(int idProduct) {
		products.remove(idProduct);
	}
	
	public float getMinPrice() {
		return products.values().stream().map(p -> p.getPrice()).min(Float::compare).orElse(-1.f);
	}
	
	@Override
	public String toString() {
		return "[Announce] (" +id+ ") " +label+ ", " +products.size()+ " produit(s) dispo et premier prix : " +getMinPrice()+ " €";
	}
	
//
//	public boolean available() {
//		return productsMap.isEmpty();
//	}
	
}
