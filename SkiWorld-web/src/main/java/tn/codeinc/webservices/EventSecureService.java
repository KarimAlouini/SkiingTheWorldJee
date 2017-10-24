package tn.codeinc.webservices;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import tn.codeinc.client.CurrentUserLocal;
import tn.codeinc.persistance.Event;
import tn.codeinc.persistance.Event.EventType;
import tn.codeinc.persistance.User.UserRole;
import tn.codeinc.services.EventManagementLocal;
import tn.codeinc.util.ResponseMessage;

@Path("/secured/event")
@RequestScoped
public class EventSecureService {
	@Inject
	private EventManagementLocal events;
	@Inject
	CurrentUserLocal currentUser;
	

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll() {
		List<Event> allEvents = events.getByType(EventType.Public);
		allEvents.addAll(currentUser.get().getMyEvents());
		return Response.ok().entity(allEvents).build();
	}
	
	@GET
	@Path("/private")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPrivate() {
		return Response.ok().entity(currentUser.get().getMyEvents()).build();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response add(Event event) {
		if (currentUser.get().getRole()!=UserRole.ROLE_USER)
			return Response.status(Status.UNAUTHORIZED).build();
		events.create(event);
		return Response.ok().entity(new ResponseMessage(0,"q")).build();
	}
	
	
}
