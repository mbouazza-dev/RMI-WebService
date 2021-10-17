package fr.uge.eiffelcorp.employees;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class EmployeesDB implements IDBServices{
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

	@Override
	public void createTables() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void connect(String dbName) {
		Connection c = null;
	      
	      try {
	         Class.forName("org.sqlite.JDBC");
	         c = DriverManager.getConnection("jdbc:sqlite:db/"+dbName);
	      } catch ( Exception e ) {
	         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	         System.exit(0);
	      }
	      System.out.println("Opened database successfully");
	}

	@Override
	public void createNewDatabase(String dbName) {
		String url = "jdbc:sqlite:db/" + dbName;

        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }		
	}

}
