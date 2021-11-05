package fr.ifshare;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class Employee {
	
	private static final AtomicInteger count = new AtomicInteger(0);
	private int id;
	private String name;
	private String firstName;
	private long balance;
	
	public Employee(int id, String name, String firstName, long balance) {
		this.id = count.incrementAndGet();
		this.name = Objects.requireNonNull(name);
		this.firstName = Objects.requireNonNull(firstName);
		this.balance = Objects.requireNonNull(balance);
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
