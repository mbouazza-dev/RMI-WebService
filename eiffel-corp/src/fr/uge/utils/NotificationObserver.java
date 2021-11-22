package fr.uge.utils;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

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
		// System.out.println("Client side : employee n°" + idEmployee + " replenishment on " +announce);		
		Employee employee = db.getById(idEmployee);
		emailService.sendSimpleMessage(employee, announce);
	}
}
