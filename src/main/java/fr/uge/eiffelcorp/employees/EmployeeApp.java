package fr.uge.eiffelcorp.employees;

public class EmployeeApp {
	public static void main(String[] args) {
		EmployeesDB db = new EmployeesDB();
		db.createNewDatabase("employeedb");
		db.createTables("employeedb");
		db.insert("employeedb",new Employee(1, "Elons", "Musk"));
		db.getEmployees("employeedb");
	}

}