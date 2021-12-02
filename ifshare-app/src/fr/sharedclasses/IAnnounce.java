package fr.sharedclasses;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * This interface contains all available public methods of an Announce.
 *
 */
public interface IAnnounce extends Remote {
	
	/**
	 * Adds a new product to this announce, only if it not exists.
	 * 
	 * @param product	the product that will be added to this announce
	 * @see 			Product
	 */
	void addProduct(Product product) throws RemoteException;
	
	/**
	 * Adds a rate (mark + comment) to this announce.
	 * 
	 * @param rating				the rate submit by a client
	 * @throws NullPointerException	if the rate is null
	 * @see							Rating
	 */
	void addRate(Rating rating) throws RemoteException;
	
	/**
	 * Returns the category of this announce.
	 * 
	 * @return
	 * @throws RemoteException
	 */
	String getCategory() throws RemoteException;
	
	/**
	 * Returns a list of comments left by customers. The list can be empty.
	 * 
	 * @return	a list of comments
	 */
	List<String> getComments() throws RemoteException;
	
	/**
	 * Returns the description of this announce.
	 * 
	 * @return
	 * @throws RemoteException
	 */
	String getDescription() throws RemoteException;
	
	/**
	 * Returns the id of this announce.
	 * 
	 * @return
	 * @throws RemoteException
	 */
	int getId() throws RemoteException;
	
	/**
	 * Returns the label of this announce.
	 * 
	 * @return
	 * @throws RemoteException
	 */
	String getLabel() throws RemoteException;
	
	int getMaxIdProduct() throws RemoteException;
	
	/**
	 * Returns the minimum price of the products available or -1 if there is no product.
	 * 
	 * @return	the minimum price
	 */
	double getMinPrice() throws RemoteException;
	
	/**
	 * Returns the product with the specified id of this announce.
	 * 
	 * @param idProduct the id of the product
	 * @return
	 * @throws RemoteException
	 */
	Product getProduct(int idProduct) throws RemoteException;
	
	/**
	 * Returns a list of all products of this announce.
	 * 
	 * @return
	 * @throws RemoteException
	 * @see						Product
	 */
	List<Product> getProducts() throws RemoteException;
	
	/**
	 * Returns the average rate of all rates or 0 if there are no rate on this announce.
	 * 
	 * @return	the average rate or 0
	 */
	double getRate() throws RemoteException;
	
	/**
	 * Returns all rate of this announce.
	 * 
	 * @return
	 * @throws RemoteException
	 * @see						Rating
	 */
	List<Rating> getRates() throws RemoteException;
	
	/**
	 * Returns all tags link to this announce.
	 * @return
	 * @throws RemoteException
	 */
	List<String> getTags() throws RemoteException;
	
	
	/**
	 * Removes from this announce one product.
	 * 
	 * @param idProduct	the id of the product that was sold
	 * @return			<code>true</code> if the product was in the store and has been removed, or <code>false</code> if the product was not in the store
	 */
	boolean soldProduct(int idProduct) throws RemoteException;
	
}
