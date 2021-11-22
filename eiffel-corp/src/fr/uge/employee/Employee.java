package fr.uge.employee;

import java.util.Objects;

public class Employee {
	private final int id;
	private final String name;
	private final String firstName;
	private long balance;
	private final String email;
	private final String password;
	
	public Employee(int id, String name, String firstName, long balance, String email, String password) {
		this.id = id;
		this.name = Objects.requireNonNull(name);
		this.firstName = Objects.requireNonNull(firstName);
		this.balance = balance;
		this.email = Objects.requireNonNull(email);
		this.password = Objects.requireNonNull(password);
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
	
	public String getEmail() {
		return email;
	}
	
	public String getPassword() {
		return password;
	}
	
}
