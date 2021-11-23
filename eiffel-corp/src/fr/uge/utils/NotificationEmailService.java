package fr.uge.utils;

import java.rmi.RemoteException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import fr.sharedclasses.IAnnounce;
import fr.uge.employee.Employee;

public class NotificationEmailService {
	@Autowired
    private JavaMailSender emailSender;
	
	public void sendSimpleMessage(Employee employee, IAnnounce announce) throws RemoteException {
		        SimpleMailMessage message = new SimpleMailMessage(); 
		        message.setFrom("noreply@eiffel_corp.com");
		        message.setTo(employee.getEmail()); 
		        String subject = announce.getLabel() + " is available on IfShare App !";
		        String text = "You have registered to be notified when the product becomes available again.\n" 
		                    + announce.getLabel() + " is available now.\nIfShare App." ;
		        message.setSubject(subject); 
		        message.setText(text);
		        emailSender.send(message);
	}
}
