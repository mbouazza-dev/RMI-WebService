package fr.uge.employee;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;

public class EmployeesDB {
	@Value("${spring.datasource.url}")
	private String datasourceURL;
	@Value("${spring.datasource.driverClassName}")
	private String driverName;
	@Value("${spring.datasource.username}")
	private String username;
	@Value("${spring.datasource.password}")
	private String password;
	
	
	public void insert(String dbName, Employee emp) {
		Connection c = null;
	      Statement stmt = null;
	      
	      try {
	         Class.forName("org.sqlite.JDBC");
	         c = DriverManager.getConnection("jdbc:sqlite:db/"+dbName);
	         c.setAutoCommit(false);
	         System.out.println("Opened database successfully");

	         stmt = c.createStatement();
	         String sql = "INSERT INTO EMPLOYEE (ID,NAME,FIRSTNAME,BALANCE) " +
	                        "VALUES ("+emp.getId()+",'"+emp.getName()+"','"+emp.getFirstName()+"','"+emp.getBalance()+"');"; 
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
	public Optional<Employee> getById(int id) {
		Optional<Employee> employee = Optional.empty();
		String sql = "SELECT * FROM EMPLOYEE WHERE ID = ?";
		try (Connection conn = connect()) {
			conn.setAutoCommit(false);
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.first()) {
				String name = rs.getString("NAME");
				String firstName = rs.getString("FIRSTNAME");
				long balance = rs.getLong("BALANCE");
				String email = rs.getString("EMAIL");
				String password = rs.getString("PASWWORD"); // typo in database
				
				Employee empl = new Employee(id, name, firstName, balance, email, password);
				employee = Optional.of(empl);
			}
		} catch (SQLException e) {
			System.err.println("Erreur lors de la connexion à la base de données Employé : " +e.getMessage());
		}
		return employee;
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
		         long	balance = rs.getLong("BALANCE");
		         
		         System.out.println( "ID = " + id );
		         System.out.println( "NAME = " + name );
		         System.out.println( "FIRST NAME = " + firstname );
		         System.out.println( "BALANCE = " + String.format("%,d", balance));
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
	                      " FIRSTNAME           TEXT    NOT NULL, " +
	                      " BALANCE		BIGINT NOT NULL);";
	       stmt.executeUpdate(sql);
	       stmt.close();
	       c.close();
	    } catch ( Exception e ) {
	       System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	       System.exit(0);
	    }
	    System.out.println("Table created successfully");
	}

	
	private Connection connect() {
		Connection conn = null;
	    try {
	    	Class.forName(driverName);
	    	conn = DriverManager.getConnection(datasourceURL, username, password);
	    } catch (SQLException e) {
	    	System.err.println("Connexion à la base de données impossible : " +e.getMessage());
	    	System.exit(0);
	    } catch (ClassNotFoundException e) {
			System.err.println("Erreur de driver : " +e.getMessage());
		}
	    return conn;
	}

	
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
	
	public void update(String dbName, Employee emp, int id) {
		Connection c = null;
		Statement stmt = null;
		   
		try {
		   Class.forName("org.sqlite.JDBC");
		   c = DriverManager.getConnection("jdbc:sqlite:db/"+dbName);
		   c.setAutoCommit(false);
		   System.out.println("Opened database successfully");

		   stmt = c.createStatement();
		   String sql = "UPDATE EMPLOYEE set NAME = "+emp.getName()+", FIRSTNAME = "+emp.getFirstName()+", BALANCE = "+emp.getBalance()+" where ID="+id+";";
		   stmt.executeUpdate(sql);
		   c.commit();

	       stmt.close();
		   c.close();
		} catch ( Exception e ) {
		   System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		   System.exit(0);
		}
		   System.out.println("Operation done successfully");
	}
	
	public void delete(String dbName, int id) {
		Connection c = null;
	    Statement stmt = null;
	      
	    try {
	       Class.forName("org.sqlite.JDBC");
	       c = DriverManager.getConnection("jdbc:sqlite:db/"+dbName);
	       c.setAutoCommit(false);
	       System.out.println("Opened database successfully");

           stmt = c.createStatement();
	       String sql = "DELETE from EMPLOYEE where ID="+id+";";
	       stmt.executeUpdate(sql);
	       c.commit();

	       stmt.close();
	       c.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	      System.out.println("Operation done successfully");
	}

}
