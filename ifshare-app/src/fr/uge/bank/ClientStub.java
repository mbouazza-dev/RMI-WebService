package fr.uge.bank;

import java.rmi.RemoteException; 
import javax.xml.rpc.ServiceException; 
 
public class ClientStub { 
 
	public static void main(String[] args) throws ServiceException, RemoteException { 
		AccountManager service = new AccountManagerServiceLocator().getAccountManager(); 
		System.out.println(service.amount(3));
	}

}
