package fr.sharedclasses;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * This interface contains all available public methods of a Store.
 * 
 */
public interface IStore extends Remote {
	/**
	 * Returns the product with the specified id.
	 * 
	 * @param idAnnounce	the id whose associated announce contains product
	 * @param idProduct		the id whose associated product is to be returned
	 * @return				the product to which the specified idProduct is mapped, or null if 
	 * 						this announce contains no mapping for the idProduct
	 * @throws RemoteException
	 * @see					Product
	 */
	Product getProduct(int idAnnounce, int idProduct) throws RemoteException;
	
	/**
	 * Returns the list of all products associated to the specified announce.
	 * 
	 * @param idAnnounce 	the id of the announce
	 * @return 				a list of products
	 * @throws RemoteException
	 * @see 				Product
	 */
	List<Product> getProducts(int idAnnounce) throws RemoteException;
	
	/**
	 * Creates a new announce with one product and add it in this store.
	 * 
	 * @param label 		the label of this new announce
	 * @param description	the description of this new announce
	 * @param firstProduct	the first product that will be added to this new announce
	 * @param tags			a list of tags to link to this new announce
	 * @param category		the main category for this new announce
	 * @throws RemoteException
	 * @see					IAnnounce
	 * @see					Product
	 */
	void createAnnounce(String label, String description, Product firstProduct, List<String> tags, String category) throws RemoteException;
	
	/**
	 * Adds the specified product to the specified announce.
	 * 
	 * @param idAnnounce	the id of the announce that will be updated
	 * @param product		the product that will be add to the announce
	 * @throws RemoteException
	 * @see					Product
	 */
	void addProductToAnnounce(int idAnnounce, Product product) throws RemoteException;
	
	/**
	 * Returns the announce with the specified id or <code>null</code> if 
	 * this store contains no mapping for the id.
	 * 
	 * @param idAnnounce	the id whose associated announce is to be returned
	 * @return				the announce to which the specified id is mapped, or null if this store contains no mapping for the id
	 * @throws RemoteException
	 * @see					IAnnounce
	 */
	IAnnounce getAnnounce(int idAnnounce) throws RemoteException;
	
	/**
	 * Returns the lists of all announces of this store, the list can be empty.
	 * 
	 * @return 				a list of announce
	 * @throws RemoteException
	 * @see 				IAnnounce
	 */
	List<IAnnounce> getAnnounces() throws RemoteException;
	
	/**
	 * Removes the product of this store.
	 * 
	 * @param idAnnounce 	the id of the announce which contains the product that has just been purchased
	 * @param idProduct 	the id of the product that has just been purchased
	 * @param idEmployee 	the id of the employee who buys the product
	 * @throws RemoteException
	 */
	boolean buyProduct(int idAnnounce, int idProduct, int idEmployee) throws RemoteException;
	
	/**
	 * Adds a note to the specified announce.
	 * 
	 * @param idAnnounce 	the id of the announce that is rated
	 * @param rating 		the rating to add
	 * @throws RemoteException
	 * @see					Rating
	 */
	void rateAnnounce(int idAnnounce, Rating rating) throws RemoteException;
	
	public double convertPrice(String fromCurrency, String toCurrency) throws RemoteException;

	/**
	 * Register a Client on a queue for a specific announce. Only the mail's client was given.
	 * @param announceId
	 * @param mail
	 * @throws RemoteException
	 */
	void registerClientOnQueue(int announceId, String mail) throws RemoteException;

	/**
	 * Unregister clients who wait a replenishment of a products. 
	 * @param announceId
	 * @param mail
	 * @throws RemoteException
	 */
	void UnregisterClientsOnReplenishment(int announceId, String mail) throws RemoteException;
}
