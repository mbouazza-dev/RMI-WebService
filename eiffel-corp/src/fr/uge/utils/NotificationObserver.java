package fr.uge.utils;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import fr.sharedclasses.AnnounceObserver;
import fr.sharedclasses.IAnnounce;

public class NotificationObserver extends UnicastRemoteObject implements AnnounceObserver {
	
	public NotificationObserver() throws RemoteException {
		super();
	}

	@Override
	public void onReplenishment(IAnnounce announce, int idEmployee) throws RemoteException {
		System.out.println("Client side : employee n°" + idEmployee + " replenishment on " +announce);		
	}

}
