package fr.uge.eiffelcorp.employees;

public interface IDBServices {

	/**
	 * Create tables according to the Employee data schema into the SQLite database
	 * @param dbName name of the database to connect
	 */
	public void createTables(String dbName);
	
	/**
	 * Connect to an SQLite database via JDBC
	 * @param dbName name of the database to connect
	 */
	public void connect(String dbName);
	
	/**
	 * Create a new SQLite database
	 * @param dbName name of the database to create
	 */
	public void createNewDatabase(String dbName);
	
	/**
	 * Insert an employee into the SQLite database
	 * @param dbName name of the database to create
	 * @param emp the employee to add to the database
	 */
	public void insert(String dbName, Employee emp);
	
	/**
	 * List all employees of the SQLite database
	 * @param dbName name of the database to create
	 */
	public void getEmployees(String dbName);
}
