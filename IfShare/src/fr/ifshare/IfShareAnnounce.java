package fr.ifshare;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class IfShareAnnounce implements Announce {

	private static int SERIAL_ID = 1;
	
	private final int id;
	private final Product product;
	private final List<String> tags; 
	private boolean closed = false;
	
	
	private IfShareAnnounce(Product product, List<String> tags) {
		this.id = SERIAL_ID;
		this.product = product;
		this.tags = new ArrayList<String>(tags);
		
		SERIAL_ID++;
	}
	

	@Override
	public IfShareAnnounce createAnnounce(Product product, String...tags) {
		if(tags == null) {
			return new IfShareAnnounce(product, Collections.emptyList());
		}
		return new IfShareAnnounce(product, Arrays.asList(tags));
	}
	
	@Override
	public String getAnnounce() throws RemoteException {
		return "[Announce] " + product.toString() + ", closed = " + closed;
	}

}
