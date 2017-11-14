package tn.codeinc.webservices;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import tn.codeinc.persistance.Event;
import tn.codeinc.persistance.Event.EventType;
import tn.codeinc.services.EventManagementLocal;

@Path("/event")
@RequestScoped
public class EventService {
	@Inject
	private EventManagementLocal events;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllPublic() {
		List<Event> allEvents = events.getByType(EventType.Public);
		return Response.ok().entity(allEvents).build();
	}

}
