package fr.ifshare;

import java.io.Serializable;

public class Product implements Serializable {
	
	public enum State { NEW, ALMOST_NEW , USED, BROKEN }
	
	private static int COUNTER = 1;
	private final int id;
	private final int idEmployee;
	private final float price;
	private final State state;
	
	public Product(int idEmployee, float price, State state) {
		this.id = COUNTER++;
		this.idEmployee = idEmployee;
		this.price = price;
		this.state = state;
	}
	
	public int getId() {
		return id;
	}
	
	public float getPrice() {
		return price;
	}

	public State getState() {
		return state;
	}
	
	public int getIdEmployee() {
		return idEmployee;
	}
	
	@Override
	public String toString() {
		return "(" +id+ ") Employe n°" +idEmployee+ ", Etat : " +state+ ", prix : " +price;
	}

}
