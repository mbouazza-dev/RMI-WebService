package fr.ifshare;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Announce extends Remote{
	public String getAnnounce() throws RemoteException;

	public IfShareAnnounce createAnnounce(Product product, String... tags) throws RemoteException;
}
