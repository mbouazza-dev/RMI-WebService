package fr.sharedclasses;

import java.io.Serializable;
import java.util.Objects;

public class Product implements Serializable {
	
	public enum State { NEW, ALMOST_NEW , USED, BROKEN }
	
	private static int COUNTER = 1;
	private final int id;
	private final String label;
	private final int idEmployee;
	private final float price;
	private final State state;
	
	
	public Product(String label, int idEmployee, float price, State state) {
		this.id = COUNTER++;
		this.label = Objects.requireNonNull(label);
		this.idEmployee = idEmployee;
		this.price = price;
		this.state = state;
	}
	
	public int getId() {
		return id;
	}
	
	public String getLabel() {
		return label;
	}
	
	public int getIdEmployee() {
		return idEmployee;
	}
	
	public float getPrice() {
		return price;
	}
	
	public State getState() {
		return state;
	}
	
	@Override
	public String toString() {
		return "(" +id+ ") Employe n°" +idEmployee+ ", Etat : " +state+ ", prix : " +price;
	}
	
}
