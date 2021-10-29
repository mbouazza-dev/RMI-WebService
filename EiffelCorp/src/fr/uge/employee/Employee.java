package fr.uge.employee;

import java.util.Objects;

public class Employee {
	private static int COUNTER = 1;
	private final int id;
	private final String firstName;
	private final String lastName;
	
	public Employee(String firstName, String lastName) {
		this.id = COUNTER++;
		this.firstName = Objects.requireNonNull(firstName);
		this.lastName = Objects.requireNonNull(lastName);
	}
}
