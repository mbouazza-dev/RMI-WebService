package fr.uge.employee;

public class EmployeeApp {
	public static void main(String[] args) {
		EmployeesDB db = new EmployeesDB();
		db.createNewDatabase("employeedb");
		db.createTables("employeedb");
		db.insert("employeedb",new Employee(  (int)(Math.random() * ((77777 - 0) + 1)) + 0  , "Elons", "Musk", 1000000000));
		db.getEmployees("employeedb");
	}

}