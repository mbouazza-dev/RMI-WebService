package fr.ifshare;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IStore extends Remote {
	Product getProduct(int idProduct) throws RemoteException;
	List<Product> getProducts() throws RemoteException;
	void addProduct(String label, int idEmployee) throws RemoteException;
	void buyProduct(int idProduct, int idEmployee) throws RemoteException;
	void rateProduct(int idProduct, float rate) throws RemoteException;
}
