package mail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import model.KursTerminModel;
import model.MitgliedModel;
import model.User;

public class MailController {

	private static final String username = "clientserverprogha@gmail.com";
	private static final String password = "kappa123";
	
	
	public static void sendEinladung(MitgliedModel mitglied) {
		String text ="Hallo "+mitglied.getVorname()+",\r\n" + 
				"\r\n" + 
				"hier sind deine Logindaten für unsere Terminverwaltung\r\n" + 
				"\r\n" + 
				"Email:   "+ mitglied.getEmail() +"\r\n" + 
				"Passwort:"+mitglied.getPasswort()+"\r\n" +
				"\r\n" + 
				"Unsere Bitte an Dich:\r\n" + 
				"Ändere bitte aus Sicherheitsgründen dein Passwort.\r\n" + 
				"Dies kannst du unter Mein Profil machen.\r\n" + 
				"\r\n" + 
				"Deine Client- Server Gruppe\r\n" + 
				"";
		
		sendMail(mitglied.getEmail()," Einladung",text);
	}
	
	
	public static void sendBestaetigung(User user, KursTerminModel termin) {
		String text = "Hallo "+user.getName()+",\r\n" + 
				"\r\n" + 
				"gerne bestätigen wir Dir Deinen persönlichen Termin wie folgt:\r\n" + 
				"\r\n" + 
				"Terminart:   "+ termin.getName() +"\r\n" + 
				"Datum:        "+termin.getDatum()+"\r\n" + 
				"Uhrzeit:       "+termin.getStartUhrzeit()+"\r\n" + 
				"Dauer:         "+termin.getDauer()+"\r\n" + 
				"\r\n" + 
				"Unsere Bitte an Dich:\r\n" + 
				"Falls Du den von Dir ausgewählten Termin nicht einhalten\r\n" + 
				"kannst, bitten wir Dich diesen online wieder zu stornieren.\r\n" + 
				"\r\n" + 
				"Deine Client- Server Gruppe\r\n" + 
				"";
		sendMail(user.getEmail(),"Terminbestätigung",text);
	}
	
	public static void sendTerminerinnerung(MitgliedModel mitglied,KursTerminModel termin) {
		String text = "Hallo "+mitglied.getVorname()+",\r\n" + 
				"\r\n" + 
				"Wir erinnern dich hiermit an deinen Termin in"+mitglied.getTerminerinnerungZeit()+" Minuten :\r\n" + 
				"\r\n" + 
				"Terminart:   "+ termin.getName() +"\r\n" + 
				"Datum:        "+termin.getDatum()+"\r\n" + 
				"Uhrzeit:       "+termin.getStartUhrzeit()+"\r\n" + 
				"Dauer:         "+termin.getDauer()+"\r\n" + 
				"\r\n" + 
				"Unsere Bitte an Dich:\r\n" + 
				"Bis gleich ;)\r\n" + 
			
				"\r\n" + 
				"Deine Client- Server Gruppe\r\n" + 
				"";
		sendMail(mitglied.getEmail(),"Terminerinnerung",text);
	}
	
	private static void sendMail(String mail, String subject, String text) {

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
				InternetAddress.parse(mail));//hier die email die eingegeben wurde pls 
			message.setSubject(subject);
			message.setText(text);

			Transport.send(message);

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
