package fr.uge.eiffelcorp.employees;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EmployeesDB implements IDBServices{
	private final HashMap<Integer, Employee> employees = new HashMap<>();
	
	
	public void insert(String dbName, Employee emp) {
		Connection c = null;
	      Statement stmt = null;
	      
	      try {
	         Class.forName("org.sqlite.JDBC");
	         c = DriverManager.getConnection("jdbc:sqlite:db/"+dbName);
	         c.setAutoCommit(false);
	         System.out.println("Opened database successfully");

	         stmt = c.createStatement();
	         String sql = "INSERT INTO EMPLOYEE (ID,NAME,FIRSTNAME) " +
	                        "VALUES ("+emp.getId()+",'"+emp.getName()+"','"+emp.getFirstName()+"');"; 
	         System.out.println("Command: "+sql);
	         
	         stmt.executeUpdate(sql);

	         stmt.close();
	         c.commit();
	         c.close();
	      } catch ( Exception e ) {
	         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	         System.exit(0);
	      }
	      System.out.println("Records created successfully");
	}
	
	/**
	 * get an employee inside the EmployeeDB using his ID
	 * @param id represents the employee's id
	 */
	public Employee getById(int id) {
		return employees.get(id);
	}
	
	
	public void getEmployees(String dbName){
		Connection c = null;
		   Statement stmt = null;
		   try {
		      Class.forName("org.sqlite.JDBC");
		      c = DriverManager.getConnection("jdbc:sqlite:db/"+dbName);
		      c.setAutoCommit(false);
		      System.out.println("Opened database successfully");

		      stmt = c.createStatement();
		      ResultSet rs = stmt.executeQuery( "SELECT * FROM EMPLOYEE;" );
		      
		      while ( rs.next() ) {
		         int id = rs.getInt("ID");
		         String  name = rs.getString("NAME");
		         String  firstname = rs.getString("FIRSTNAME");
		         
		         System.out.println( "ID = " + id );
		         System.out.println( "NAME = " + name );
		         System.out.println( "FIRST NAME = " + firstname );
		         System.out.println();
		      }
		      rs.close();
		      stmt.close();
		      c.close();
		   } catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		   }
		   System.out.println("Operation done successfully");
	}

	@Override
	public void createTables(String dbName) {
		Connection c = null;
	    Statement stmt = null;
	      
	    try {
	       Class.forName("org.sqlite.JDBC");
	       c = DriverManager.getConnection("jdbc:sqlite:db/"+dbName);
	       System.out.println("Opened database successfully");

	       stmt = c.createStatement();
	       String sql = "CREATE TABLE EMPLOYEE " +
	                      "(ID INTEGER PRIMARY KEY     NOT NULL," +
	                      " NAME           TEXT    NOT NULL, " + 
	                      " FIRSTNAME           TEXT    NOT NULL)"; 
	       stmt.executeUpdate(sql);
	       stmt.close();
	       c.close();
	    } catch ( Exception e ) {
	       System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	       System.exit(0);
	    }
	    System.out.println("Table created successfully");
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
