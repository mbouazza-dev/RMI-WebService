package fr.ifshare;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;

public interface Announce extends Remote{
	public String getAnnounce() throws RemoteException;

	public IfShareAnnounce createAnnounce(Product product, String... tags) throws RemoteException;
	
	public HashMap<Product, Employee> getProductsMap();

	public boolean available();
}
