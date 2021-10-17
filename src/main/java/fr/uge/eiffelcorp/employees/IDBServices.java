package fr.uge.eiffelcorp.employees;

public interface IDBServices {

	/**
	 * Create tables according to the Employee data schema into the SQLite database
	 */
	public void createTables();
	
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
}
