package tn.codeinc.webservices;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import tn.codeinc.client.CurrentUserLocal;
import tn.codeinc.persistance.OfferMessage;
import tn.codeinc.persistance.User;
import tn.codeinc.services.OfferMessageManagementLocal;
import tn.codeinc.services.UsersManagementLocal;
import tn.codeinc.util.ResponseMessage;

@Path("/secured/offer_messages")
public class OfferMessageSecureService {
	
	@EJB
	OfferMessageManagementLocal messages;
	
	@EJB
	UsersManagementLocal um;
	
	@Inject
	CurrentUserLocal cu;
	
	@PUT
	public Response add(OfferMessage message){
		messages.create(message);
		return Response.ok().entity(new ResponseMessage(0, "Message added")).build();
	}
	@Path("/get")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMessages(@HeaderParam("otherUser") Integer id){
		System.out.println("OfferMessageSecureService.getMessages()"+ cu == null);
		System.out.println("OfferMessageSecureService.getMessages() "+cu.get().getId());
		
		
		User other = um.get(id);
		
		if (other == null)
			return Response.status(Status.BAD_REQUEST).build();
		else{
		System.out.println("OfferMessageSecureService.getMessages() "+cu.get().getId());
		return Response.ok().entity(messages.getByUsers(cu.get(), other)).build();
		}
	}

}
