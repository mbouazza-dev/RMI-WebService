package fr.uge.employee;

import java.util.Objects;

public class Employee {
	private final int id;
	private final String name;
	private final String firstName;
	private long balance;
	
	public Employee(int id, String name, String firstName, long balance) {
		this.id = id;
		this.name = Objects.requireNonNull(name);
		this.firstName = Objects.requireNonNull(firstName);
		this.balance = balance;
	}
	
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getFirstName() {
		return firstName;
	}

	public long getBalance() {
		return balance;
	}
	
}
