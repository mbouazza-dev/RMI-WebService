package fr.uge.main;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import fr.sharedclasses.IStore;

@Configuration
public class AppConfiguration {
	
	@Bean
	public IStore getStore() throws MalformedURLException, RemoteException, NotBoundException {
		return (IStore) Naming.lookup("storeService"); // récupère l'objet transmis via RMI
	}
}
