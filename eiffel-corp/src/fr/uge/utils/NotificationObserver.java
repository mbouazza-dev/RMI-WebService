package fr.uge.utils;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Optional;

import fr.sharedclasses.AnnounceObserver;
import fr.sharedclasses.IAnnounce;
import fr.uge.employee.Employee;
import fr.uge.employee.EmployeesDB;

public class NotificationObserver extends UnicastRemoteObject implements AnnounceObserver {
	
	private final NotificationEmailService emailService;
	private final EmployeesDB db;
	
	
		
	public NotificationObserver(EmployeesDB db) throws RemoteException {
		super();
		this.emailService = new NotificationEmailService(); 
		this.db = db;
	}

	@Override
	public void onReplenishment(IAnnounce announce, int idEmployee) throws RemoteException {
		System.out.println("Client side");
		Optional<Employee> employee = db.getById(idEmployee);
		if (employee.isPresent() ) {
			emailService.sendSimpleMessage(employee.get(), announce);
		} else {
			System.err.println("L'id " +idEmployee+ " n'est relié à aucun employé en base de données.");
		}
	}
}
