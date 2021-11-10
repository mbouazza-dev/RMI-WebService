package fr.sharedclasses;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IStore extends Remote {
	/**
	 * Returns a product copy.
	 * 
	 * @param idProduct
	 * @return
	 * @throws RemoteException
	 */
	Product getProduct(int Announce, int idProduct) throws RemoteException;
	
	void createAnnounce(String label, String description, Product firstProduct, List<String> tags) throws RemoteException;
	
	void addProductToAnnounce(int idAnnounce, Product product) throws RemoteException;
	
	Announce getAnnounce(int idAnnounce) throws RemoteException;
	
	List<Announce> getAnnounces() throws RemoteException;
	
	/**
	 * Returns the list of all products in the store.
	 * 
	 * @return a list of products
	 * @throws RemoteException
	 */
	List<Product> getProducts(int idAnnounce) throws RemoteException;
	
	/**
	 * Remove the product of the store.
	 * 
	 * @param idProduct the id of the product that has just been purchased
	 * @param idEmployee the id of the employee who buys the product
	 * @throws RemoteException
	 */
	void buyProduct(int idAnnounce, int idProduct, int idEmployee) throws RemoteException;
	
	/**
	 * Adding a note to the specified announce.
	 * 
	 * @param idAnnounce the id of the announce that is rated
	 * @param rate the rate to add
	 * @param comment the comment of the client to add
	 * @throws RemoteException
	 */
	void rateAnnounce(int idAnnounce, double rate, String comment) throws RemoteException;
}
