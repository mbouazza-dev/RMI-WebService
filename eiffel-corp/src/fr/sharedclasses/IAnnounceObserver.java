package fr.sharedclasses;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IAnnounceObserver extends Remote {
	
	/**
	 * Throws when a new product is added to an Announce.
	 * 
	 * @param announce			the announce that has been updated
	 * @param idEmployee		the id of the employee who must be notified
	 * @throws RemoteException
	 * @see						IAnnounce
	 */
	void onReplenishment(IAnnounce announce) throws RemoteException;

	/**
	 * 
	 * @param announceId        the announce id;
	 * @param mail              the mail of the employee for the email sended.
	 * @throws RemoteException                     
	 */
	void register(int announceId, String mail) throws RemoteException;

	void unregister(int announceId, String mail) throws RemoteException;
}
