package fr.ifshare;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;

public class Product implements Serializable {
	
	private static int COUNTER = 1;
	private final int id;
	private final String label;
	private final int idEmployee;
	private final List<Rating> rates = new ArrayList<>();
	
	
	public Product(String label, int idEmployee) {
		this.id = COUNTER++;
		this.label = Objects.requireNonNull(label);
		this.idEmployee = idEmployee;
	}
	
	public int getId() {
		return id;
	}
	
	@Override
	public String toString() {
		return "(" +id+ ") " +label+ " : " +getRate()+ " star(s), comment(s) : " +rates.toString();
	}
	
	/**
	 * Add a new rating to this product.
	 * 
	 * @param rating
	 */
	public void addRate(Rating rating) {
		rates.add(rating);
	}
	
	/**
	 * Get a average rate.
	 * 
	 * @return
	 */
	public double getRate() {
		return rates.stream().mapToDouble(e -> e.getRate()).average().orElse(0);
	}
}
