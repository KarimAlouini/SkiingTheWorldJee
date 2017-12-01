package tn.codeinc.services;

import java.util.Base64;
import java.util.Date;
import java.util.Properties;

import javax.ejb.Stateless;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.ws.rs.core.UriInfo;

import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import tn.codeinc.persistance.User;
import tn.codeinc.util.ConfirmationCode;

@Stateless
public class MailSender implements MailSenderLocal, MailSenderRemote {
	private static final String HOST = "smtp.gmail.com";
	private static final String FROM = "ccodeinc@gmail.com";
	private static final String PASSWORD = "codeinctwin";

	@Override
	public void send(String to, String subject, String content)throws RuntimeException {
		// Get system properties
		   Properties props = new Properties();
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.starttls.enable", "true");
	        props.put("mail.smtp.host", HOST);
	        props.put("mail.smtp.port", "587");

	        Session session = Session.getInstance(props,
	                new javax.mail.Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(FROM, PASSWORD);
	            }
	        });

	        try {

	            Message message = new MimeMessage(session);
	            message.setContent(content, "text/html; charset=utf-8");

	            message.setFrom(new InternetAddress(FROM));
	            

	            message.setRecipients(Message.RecipientType.TO,
	                    InternetAddress.parse(to));
	            message.setSubject(subject);

	            Transport.send(message);

	            System.out.println("Done");

	        } catch (MessagingException e) {
	            throw new RuntimeException(e);
	        }


	}

	@Override
	public void sendConfirmation(User u) {
		
		u.setConfirmationCode(
				ConfirmationCode.generate());
		JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/confirmation_mail.twig");
		JtwigModel model = JtwigModel.newModel();
		model.with("username", u.getFirstName());
		model.with("confirmationLink","http://localhost:4200/confirm/" + u.getConfirmationCode());

		
		send(u.getEmail(), "Skiing the world confirmation", template.render(model));

	}

	@Override
	public void resendConfirmation(User u) {
		u.setConfirmationCode(ConfirmationCode.generate());
		JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/reconfirmation_email.html.twig");
		JtwigModel model = JtwigModel.newModel();
		model.with("username", u.getFirstName());
		model.with("confirmationLink","http://localhost:4200/confirm/" + u.getConfirmationCode());
		send(u.getEmail(), "Skiing the world confirmation", template.render(model));
		
		
	}

}
