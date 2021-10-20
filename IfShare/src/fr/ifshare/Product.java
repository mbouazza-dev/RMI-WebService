package fr.ifshare;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Product implements Serializable {
	private static int COUNTER = 1;
	private int id;
	private String label;
	private int idEmployee;
	private List<Float> rates = new ArrayList<>();
	
	
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
		return "(" +id+ ") " +label+ " : " +getRate()+ " star(s)";
	}
	
	public void addRate(float rate) {
		rates.add(rate);
	}
	
	public double getRate() {
		return rates.stream().mapToDouble(e -> e.doubleValue()).average().orElse(0);
	}
}
