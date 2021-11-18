package fr.ifshare.store;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

import fr.sharedclasses.Product;
import fr.sharedclasses.Product.State;

public class StoreDB {
	
	private List<Product> products;
	private List<Announce> announces;

	public void insertProduct(String dbName, Product pct) {
		Connection c = null;
	      Statement stmt = null;
	      
	      try {
	         Class.forName("org.h2.Driver");
	         c = DriverManager.getConnection("jdbc:h2:./db/"+dbName);
	         c.setAutoCommit(false);
	         System.out.println("Opened database successfully");

	         stmt = c.createStatement();
	         String sql = "INSERT INTO PRODUCT (ID,LABEL,IDEMPLOYEE,PRICE,STATE) " +
	                        "VALUES ("+pct.getId()+",'"+pct.getLabel()+"','"+pct.getIdEmployee()+"','"+pct.getPrice()+"','"+String.valueOf(pct.getState())+"');"; 
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
	
	public void insertAnnounce(String dbName, Announce anc) {
		Connection c = null;
	      Statement stmt = null;
	      
	      try {
	         Class.forName("org.h2.Driver");
	         c = DriverManager.getConnection("jdbc:h2:./db/"+dbName);
	         c.setAutoCommit(false);
	         System.out.println("Opened database successfully");

	         stmt = c.createStatement();
	         String sql = "INSERT INTO ANNOUNCE (ID,LABEL,DESCRIPTION,RATES,TAGS,CATEGORY) " +
	                        "VALUES ("+anc.getId()+",'"+anc.getLabel()+"','"+anc.getDescription()+"','"+anc.getRate()+"','"+anc.getTags()+"','"+anc.getCategory()+"');"; 
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
	
	public void insertOnSale(String dbName, Announce anc, Product pct) {
		Connection c = null;
	      Statement stmt = null;
	      
	      try {
	         Class.forName("org.h2.Driver");
	         c = DriverManager.getConnection("jdbc:h2:./db/"+dbName);
	         c.setAutoCommit(false);
	         System.out.println("Opened database successfully");

	         stmt = c.createStatement();
	         String sql = "INSERT INTO ON_SALE (ID,IDPRODUCT) " +
	                        "VALUES ("+anc.getId()+",'"+pct.getId()+"');"; 
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
		
	
	public List<Product> getProducts(String dbName){
		Connection c = null;
		   Statement stmt = null;
		   try {
		      Class.forName("org.h2.Driver");
		      c = DriverManager.getConnection("jdbc:h2:./db/"+dbName);
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
		         Product product = new Product(label, idEmployee, price, State.valueOf(state));
		         products.add(product);
		      }
		      rs.close();
		      stmt.close();
		      c.close();
		   } catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		   }
		   System.out.println("Operation done successfully");
		   return products;
	}
	
	
	public List<Announce> getAnnounces(String dbName){
		Connection c = null;
		   Statement stmt = null;
		   try {
		      Class.forName("org.h2.Driver");
		      c = DriverManager.getConnection("jdbc:h2:./db/"+dbName);
		      c.setAutoCommit(false);
		      System.out.println("Opened database successfully");

		      stmt = c.createStatement();
		      ResultSet rs = stmt.executeQuery( "SELECT * FROM ANNOUNCE;" );
		      
		      while ( rs.next() ) {
		         int id = rs.getInt("ID");
		         String  label = rs.getString("LABEL");
		         String  description = rs.getString("DESCRIPTION");
		         float	rates = rs.getFloat("RATES");
		         String	tags = rs.getString("TAGS");
		         String	category = rs.getString("CATEGORY");
		         
		         System.out.println( "ID = " + id );
		         System.out.println( "LABEL = " + label );
		         System.out.println( "DESCRIPTION = " + description );
		         System.out.println( "RATES = " + rates);
		         System.out.println( "TAGS = " + tags );
		         System.out.println( "CATEGORY = " + category );
		         System.out.println();
		         Announce announce = new Announce(label, description, getProducts(dbName).get(0), Arrays.asList(new String[] {tags}), category);
		         announces.add(announce);
		      }
		      rs.close();
		      stmt.close();
		      c.close();
		   } catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		   }
		   System.out.println("Operation done successfully");
		   return announces;
	}

	
	public void createTableProduct(String dbName) {
		Connection c = null;
	    Statement stmt = null;
	      
	    try {
	       Class.forName("org.h2.Driver");
	       c = DriverManager.getConnection("jdbc:h2:./db/"+dbName);
	       System.out.println("Opened database successfully");

	       stmt = c.createStatement();
	       String sql = "CREATE TABLE IF NOT EXISTS PRODUCT " +
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
	
	public void createTableAnnounce(String dbName) {
		Connection c = null;
	    Statement stmt = null;
	      
	    try {
	       Class.forName("org.h2.Driver");
	       c = DriverManager.getConnection("jdbc:h2:./db/"+dbName);
	       System.out.println("Opened database successfully");

	       stmt = c.createStatement();
	       String sql = "CREATE TABLE IF NOT EXISTS ANNOUNCE " +
	                      "(ID INTEGER PRIMARY KEY     NOT NULL," +
	                      " LABEL           TEXT    NOT NULL, " + 
	                      " DESCRIPTION           TEXT, " +
	                      " RATES	FLOAT4," +
	                      " TAGS	TEXT," +
	                      " CATEGORY	TEXT"+ ");";
	       stmt.executeUpdate(sql);
	       stmt.close();
	       c.close();
	    } catch ( Exception e ) {
	       System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	       System.exit(0);
	    }
	    System.out.println("Table created successfully");
	}
	
	public void createTableOnSale(String dbName) {
		Connection c = null;
	    Statement stmt = null;
	      
	    try {
	       Class.forName("org.h2.Driver");
	       c = DriverManager.getConnection("jdbc:h2:./db/"+dbName);
	       System.out.println("Opened database successfully");

	       stmt = c.createStatement();
	       String sql = "CREATE TABLE IF NOT EXISTS ON_SALE " +
	                      "(ID INTEGER PRIMARY KEY     NOT NULL," +
	                      " IDPRODUCT           INTEGER    NOT NULL "+ ");";
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
	         c = DriverManager.getConnection("jdbc:h2:./db/"+dbName);
	      } catch ( Exception e ) {
	         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	         System.exit(0);
	      }
	      System.out.println("Opened database successfully");
	}

	
	public void createNewDatabase(String dbName) {
		String url = "jdbc:h2:./db/" + dbName;
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
	
	public void updateProduct(String dbName, Product pct, int id) {
		Connection c = null;
		Statement stmt = null;
		   
		try {
		   Class.forName("org.h2.Driver");
		   c = DriverManager.getConnection("jdbc:h2:./db/"+dbName);
		   c.setAutoCommit(false);
		   System.out.println("Opened database successfully");

		   stmt = c.createStatement();
		   String sql = "UPDATE PRODUCT set LABEL = "+pct.getLabel()+", IDEMPLOYEE = "+pct.getIdEmployee()+", PRICE = "+pct.getPrice()+", STATE = "+String.valueOf(pct.getState())+" WHERE ID="+id+";";
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
	
	
	public void updateOnSale(String dbName, int id, Product pct, Announce anc) {
		Connection c = null;
		Statement stmt = null;
		   
		try {
		   Class.forName("org.h2.Driver");
		   c = DriverManager.getConnection("jdbc:h2:./db/"+dbName);
		   c.setAutoCommit(false);
		   System.out.println("Opened database successfully");

		   stmt = c.createStatement();
		   String sql = "UPDATE ON_SALE set ID = "+anc.getId()+", IDPRODUCT = "+pct.getId()+" WHERE ID="+id+";";
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
	
	public void updateAnnounce(String dbName, int id, Announce anc) {
		Connection c = null;
		Statement stmt = null;
		   
		try {
		   Class.forName("org.h2.Driver");
		   c = DriverManager.getConnection("jdbc:h2:./db/"+dbName);
		   c.setAutoCommit(false);
		   System.out.println("Opened database successfully");

		   stmt = c.createStatement();
		   String sql = "UPDATE ANNOUNCE set ID = "+anc.getId()+", LABEL = "+anc.getLabel()+", DESCRIPTION = "+anc.getDescription()+", RATES = "+anc.getRate()+", TAGS = "+anc.getTags()+", CATEGORY = "+anc.getCategory()+" WHERE ID="+id+";";
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
	
	public void deleteProduct(String dbName, int id) {
		Connection c = null;
	    Statement stmt = null;
	      
	    try {
	       Class.forName("org.h2.Driver");
	       c = DriverManager.getConnection("jdbc:h2:./db/"+dbName);
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
	
	public void deleteAnnounce(String dbName, int id) {
		Connection c = null;
	    Statement stmt = null;
	      
	    try {
	       Class.forName("org.h2.Driver");
	       c = DriverManager.getConnection("jdbc:h2:./db/"+dbName);
	       c.setAutoCommit(false);
	       System.out.println("Opened database successfully");

           stmt = c.createStatement();
	       String sql = "DELETE from ANNOUNCE where ID="+id+";";
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
	
	public void deleteOnSale(String dbName, int id) {
		Connection c = null;
	    Statement stmt = null;
	      
	    try {
	       Class.forName("org.h2.Driver");
	       c = DriverManager.getConnection("jdbc:h2:./db/"+dbName);
	       c.setAutoCommit(false);
	       System.out.println("Opened database successfully");

           stmt = c.createStatement();
	       String sql = "DELETE from ON_SALE where ID="+id+";";
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
