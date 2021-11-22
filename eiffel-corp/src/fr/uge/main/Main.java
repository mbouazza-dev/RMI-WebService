package fr.uge.main;

import java.rmi.RemoteException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "fr.uge")
public class Main {

	public static void main(String[] args) throws RemoteException {
		SpringApplication.run(Main.class, args);
	}

}
