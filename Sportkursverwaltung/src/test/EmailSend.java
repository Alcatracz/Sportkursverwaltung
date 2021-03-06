package test;


import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSend {

	public static void main(String[] args) {

		final String username = "clientserverprogha@gmail.com";
		final String password = "kappa123";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("clientserverprogha@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse("jan.ole.clausen78@gmail.com"));//hier die email die eingegeben wurde pls 
			message.setSubject("Anmeldung Sportkursverwaltung");
			message.setText("Text mit Passwort und Aufforderung es zu �ndern");

			Transport.send(message);

			System.out.println("Email Send");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}