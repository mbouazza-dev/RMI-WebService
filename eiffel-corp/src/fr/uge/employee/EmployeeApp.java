package fr.uge.employee;

import fr.ifshare.Employee;

public class EmployeeApp {
	public static void main(String[] args) {
		EmployeesDB db = new EmployeesDB();
		db.createNewDatabase("employeedb");
		db.createTables("employeedb");
		db.insert("employeedb",new Employee(1, "Elons", "Musk", 1000000000));
		db.getEmployees("employeedb");
	}

}