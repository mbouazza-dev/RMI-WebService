package fr.ifshare.store;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import fr.ifshare.Employee;
import fr.ifshare.Product;

public class StoreDB {

	public void insert(String dbName, Product pct) {
		Connection c = null;
	      Statement stmt = null;
	      
	      try {
	         Class.forName("org.h2.Driver");
	         c = DriverManager.getConnection("jdbc:h2:~/db/"+dbName);
	         c.setAutoCommit(false);
	         System.out.println("Opened database successfully");

	         stmt = c.createStatement();
	         String sql = "INSERT INTO PRODUCT (ID,LABEL,IDEMPLOYEE,RATE,PRICE,STATE) " +
	                        "VALUES ("+pct.getId()+",'"+pct.getLabel()+"','"+pct.getIdEmployee()+"','"+pct.getRate()+"','"+pct.getPrice()+"','"+String.valueOf(pct.getState())+"');"; 
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
		
	
	public void getProducts(String dbName){
		Connection c = null;
		   Statement stmt = null;
		   try {
		      Class.forName("org.h2.Driver");
		      c = DriverManager.getConnection("jdbc:h2:~/db/"+dbName);
		      c.setAutoCommit(false);
		      System.out.println("Opened database successfully");

		      stmt = c.createStatement();
		      ResultSet rs = stmt.executeQuery( "SELECT * FROM PRODUCT;" );
		      
		      while ( rs.next() ) {
		         int id = rs.getInt("ID");
		         String  label = rs.getString("LABEL");
		         int  idEmployee = rs.getInt("IDEMPLOYEE");
		         float	rate = rs.getFloat("RATE");
		         float	price = rs.getFloat("PRICE");
		         String	state = rs.getString("STATE");
		         
		         System.out.println( "ID = " + id );
		         System.out.println( "LABEL = " + label );
		         System.out.println( "IDEMPLOYEE = " + idEmployee );
		         System.out.println( "RATE = " + rate);
		         System.out.println( "PRICE = " + price );
		         System.out.println( "STATE = " + state );
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

	
	public void createTable(String dbName) {
		Connection c = null;
	    Statement stmt = null;
	      
	    try {
	       Class.forName("org.h2.Driver");
	       c = DriverManager.getConnection("jdbc:h2:~/db/"+dbName);
	       System.out.println("Opened database successfully");

	       stmt = c.createStatement();
	       String sql = "CREATE TABLE PRODUCT " +
	                      "(ID INTEGER PRIMARY KEY     NOT NULL," +
	                      " LABEL           TEXT    NOT NULL, " + 
	                      " IDEMPLOYEE           INTEGER, " +
	                      " RATE	FLOAT4," +
	                      " PRICE	FLOAT4," +
	                      " STATE	TEXT"+ ");";
	       stmt.executeUpdate(sql);
	       stmt.close();
	       c.close();
	    } catch ( Exception e ) {
	       System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	       System.exit(0);
	    }
	    System.out.println("Table created successfully");
	}

	
	public void connect(String dbName) {
		Connection c = null;
	      
	      try {
	         Class.forName("org.h2.Driver");
	         c = DriverManager.getConnection("jdbc:h2:~/db/"+dbName);
	      } catch ( Exception e ) {
	         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	         System.exit(0);
	      }
	      System.out.println("Opened database successfully");
	}

	
	public void createNewDatabase(String dbName) {
		String url = "jdbc:h2:~/db/" + dbName;
		try {
			Class.forName("org.h2.Driver");
			Connection conn = DriverManager.getConnection(url);
			if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}		
	}
	
	public void update(String dbName, Product pct, int id) {
		Connection c = null;
		Statement stmt = null;
		   
		try {
		   Class.forName("org.h2.Driver");
		   c = DriverManager.getConnection("jdbc:h2:~/db/"+dbName);
		   c.setAutoCommit(false);
		   System.out.println("Opened database successfully");

		   stmt = c.createStatement();
		   String sql = "UPDATE PRODUCT set LABEL = "+pct.getLabel()+", IDEMPLOYEE = "+pct.getIdEmployee()+", RATE = "+pct.getRate()+", PRICE = "+pct.getPrice()+", STATE = "+String.valueOf(pct.getState())+" WHERE ID="+id+";";
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
	       Class.forName("org.h2.Driver");
	       c = DriverManager.getConnection("jdbc:h2:~/db/"+dbName);
	       c.setAutoCommit(false);
	       System.out.println("Opened database successfully");

           stmt = c.createStatement();
	       String sql = "DELETE from PRODUCT where ID="+id+";";
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
