package fr.ifshare.store;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import fr.sharedclasses.AnnounceObserver;
import fr.sharedclasses.IAnnounce;

public class NotificationReplenishmentObserver extends UnicastRemoteObject implements AnnounceObserver {
	
	public NotificationReplenishmentObserver() throws RemoteException {
		super();
	}

	@Override
	public void onReplenishment(IAnnounce announce, int idEmployee) throws RemoteException {
		// avertir l'employé qu'un nouveau produit a été ajouté sur l'annonce
		System.out.println("Server side : " +idEmployee+ " pour l'annonce : " +announce);
	}

}
