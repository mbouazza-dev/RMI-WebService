package fr.sharedclasses;

import java.io.Serializable;
import java.util.Objects;

public class Product implements Serializable {
	
	public enum State { 
		NEW("Nouveau"), 
		ALMOST_NEW("Presque nouveau"), 
		USED("Usé"), 
		BROKEN("Cassé");
		
		private String frEquivalent;
		
		State(final String frEquivalent) {
			this.frEquivalent = frEquivalent;
		}
		
		public String getFrEquivalent() {
			return frEquivalent;
		}	
	}
	
	public static State[] getAllState() {
		return State.values();
	}
	
	private static int COUNTER = 1;
	private int id;
	private String label;
	private int idEmployee;
	private double price;
	private State state;
	
	public Product() {
		// Empty constructor for Spring
	}	
	
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
	
	public double getPrice() {
		return price;
	}
	
	public State getState() {
		return state;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setLabel(String label) {
		this.label = label;
	}
	
	public void setIdEmployee(int idEmployee) {
		this.idEmployee = idEmployee;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public void setState(State state) {
		this.state = state;
	}
	
	@Override
	public String toString() {
		return "(" +id+ ") Employe n°" +idEmployee+ ", Etat : " +state+ ", prix : " +price;
	}
	
}
