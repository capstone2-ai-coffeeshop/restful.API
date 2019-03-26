package library;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMailTLS {

	public static void sendMail(String toMail, String passwordRandom) {
		final String username = "taikhoan6660207@gmail.com";
		final String password = "hoilamchi123";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.debug", "true");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("taikhoan6660207@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toMail));
			message.setSubject("Change password");
			message.setText("Your new password is: " + passwordRandom);

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(String[] args) {
		SendMailTLS.sendMail("taikhoan0207@gmail.com", randomCharacters.randomString(10));
	}
}
