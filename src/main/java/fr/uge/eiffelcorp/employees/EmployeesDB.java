package fr.uge.eiffelcorp.employees;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EmployeesDB {
	private final HashMap<Integer, Employee> employees = new HashMap<>();
	
	
	/**
	 * add an employee to the EmployeeDB
	 * @param employee represents the employee to add
	 * @throws IllegalArgumentException
	 */
	public void add(Employee e) {
		if(e == null)
			throw new IllegalArgumentException();
		else {
			employees.putIfAbsent(e.getId(), e);
		}
	}
	
	/**
	 * get an employee inside the EmployeeDB using his ID
	 * @param id represents the employee's id
	 */
	public Employee getById(int id) {
		return employees.get(id);
	}
	
	/**
	 * get all employees inside the EmployeeDB
	 */
	public List<Employee> getEmployees(){
		return new ArrayList<Employee>(employees.values());
	}

}
