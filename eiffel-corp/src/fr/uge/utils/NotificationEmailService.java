package fr.uge.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import fr.sharedclasses.Product;

public class NotificationEmailService {
	@Autowired
    private JavaMailSender emailSender;
	
	public void sendSimpleMessage(String to, Product product ) {
		        SimpleMailMessage message = new SimpleMailMessage(); 
		        message.setFrom("noreply@eiffel_corp.com");
		        message.setTo(to); 
		        String subject = product.getLabel() + " is available on IfShare App !";
		        String text = "You have registered to be notified when the product becomes available again.\n" 
		                    + product.getLabel() + " is available now.\nIfShare App." ;
		        message.setSubject(subject); 
		        message.setText(text);
		        emailSender.send(message);
	}
}
