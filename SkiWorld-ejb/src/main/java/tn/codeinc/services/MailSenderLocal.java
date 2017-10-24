package tn.codeinc.services;

import javax.ws.rs.core.UriInfo;

import tn.codeinc.persistance.User;

public interface MailSenderLocal {
	public void send(String to,String subject,String message);
	public void sendConfirmation(User u,UriInfo inf);

}
