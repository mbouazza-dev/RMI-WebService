package fr.ifshare.store;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Objects;
import java.util.Queue;

import fr.sharedclasses.IAnnounceObserver;
import fr.sharedclasses.IAnnounce;

/**
 * Classe implémentant le patron Observer afin de permettre la notification des clients 
 * inscrit sur la liste d'attente d'une annonce lorsqu'un produit est à nouveau disponible.
 * 
 * La classe possède une Hashmap permettant de retrouver une file d'attente d'email en 
 * fonction de l'id d'une annonce.
 */
public class AnnounceObserver extends UnicastRemoteObject implements IAnnounceObserver {
	
	private HashMap<Integer, Queue<String>> map = new HashMap<Integer, Queue<String>>(); 
	
	public AnnounceObserver() throws RemoteException {
		super();

	}
	
	private Queue<String> getOrCreate(int announceId){
		Queue<String> queue = map.get(announceId);
		if(queue == null) {
			queue = new ArrayDeque<String>();
		}
		return queue;
	}
	
	public void register(int announceId, String mail) {
		Objects.requireNonNull(mail);
		Queue<String> queue = getOrCreate(announceId);
		queue.add(mail);
		map.put(announceId, queue);
	}
	
	
	
	@Override
	public void onReplenishment(IAnnounce announce) throws RemoteException {
		Objects.requireNonNull(announce);
		System.out.println("L'announce " + announce.getLabel() + " a de nouveaux produits disponibles." );
	}
}
