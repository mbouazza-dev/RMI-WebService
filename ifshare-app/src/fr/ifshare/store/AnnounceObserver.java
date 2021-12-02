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
 * Class implementing Observer pattern to allow notification on client.
 * Waiting client was notified when a product is available again if they are registered on its queue.
 * 
 * This class owns a HashMap<Announce ID, List<Client Mail>> to retrieve the list of waiters client for 
 * a given announce.
 */
public class AnnounceObserver extends UnicastRemoteObject implements IAnnounceObserver {
	
	private HashMap<Integer, Queue<String>> map = new HashMap<Integer, Queue<String>>(); 
	private EmailService emailService = new EmailService();
	
	
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
	
	@Override
	public void register(int announceId, String mail) throws RemoteException {
		Objects.requireNonNull(mail);
		Queue<String> queue = getOrCreate(announceId);
		queue.add(mail);
		map.put(announceId, queue);
	}
	
	@Override
	public void unregister(int announceId, String mail) throws RemoteException {
		Objects.requireNonNull(mail);
		Queue<String> queue = map.get(announceId);
		if(queue == null || !queue.remove(mail)) {
			throw new IllegalStateException();
		}
	}
	
	@Override
	public void onReplenishment(IAnnounce announce) throws RemoteException {
		Objects.requireNonNull(announce);
		if(!containsWaiters(announce.getId())) {
			return;
		}
		Queue<String> queue = map.get(announce.getId());
		for(String mail: queue) {
			String content = mail + ":> L'announce " + announce.getLabel() + " a de nouveaux produits disponibles." ;
			System.out.println(content);
			emailService.sendEmail(content, mail);
		}
		queue.clear();
	}
	
	public boolean containsWaiters(int announceId) {
		Queue<String> queue = map.get(announceId);
		return queue != null && !queue.isEmpty();
	}
}
