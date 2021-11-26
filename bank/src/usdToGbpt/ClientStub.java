package usdToGbpt;

import java.rmi.RemoteException; 
import javax.xml.rpc.ServiceException; 
 
public class ClientStub { 
 
	public static void main(String[] args) throws ServiceException, RemoteException { 
		ServiceUSDtoGBPT service = new ServiceUSDtoGBPTServiceLocator().getServiceUSDtoGBPT();
		System.out.println(service.convert(10));
	}

}
