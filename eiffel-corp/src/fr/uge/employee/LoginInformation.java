package fr.uge.employee;

/**
 * Classe de test, sera remplacée plus tard grâce à une composant Spring.
 * Ne pas en tenir compte.
 */
public class LoginInformation {
	private int id;
	private String password;
	
	public LoginInformation() { }
	
	public LoginInformation(int id, String password) {
		this.id = id;
		this.password = password;
	}
	
	public int getId() {
		return id;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
}
