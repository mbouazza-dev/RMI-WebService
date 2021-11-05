package fr.ifshare;

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
	Product getProduct(int idProduct) throws RemoteException;
	
	/**
	 * Returns the list of all products in the store.
	 * 
	 * @return a list of products
	 * @throws RemoteException
	 */
	List<Product> getProducts() throws RemoteException;
	
	/**
	 * Add a new product to the store.
	 * 
	 * @param label the label of the new product
	 * @param idEmployee the id of the employee who sells the product
	 * @throws RemoteException
	 */
	void addProduct(String label, int idEmployee) throws RemoteException;
	
	/**
	 * Remove the product of the store.
	 * 
	 * @param idProduct the id of the product that has just been purchased
	 * @param idEmployee the id of the employee who buys the product
	 * @throws RemoteException
	 */
	void buyProduct(int idProduct, int idEmployee) throws RemoteException;
	
	/**
	 * Adding a note to the specified product.
	 * 
	 * @param idProduct the id of the product that is rated
	 * @param rate the rate to add
	 * @param comment the comment of the client to add
	 * @throws RemoteException
	 */
	void rateProduct(int idProduct, double rate, String comment) throws RemoteException;
}
