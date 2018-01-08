package tn.codeinc.webservices;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import tn.codeinc.client.CurrentUserLocal;
import tn.codeinc.exceptions.ElementNotFoundException;
import tn.codeinc.persistance.Event;
import tn.codeinc.persistance.Event.EventType;
import tn.codeinc.persistance.KeyWord;
import tn.codeinc.services.EventManagementLocal;

@Path("/event")
@RequestScoped
public class EventService {
	@Inject
	private EventManagementLocal events;
	@Inject
	CurrentUserLocal currentUser;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllPublic() {
		List<Event> allEvents = events.getByType(EventType.Public);
		return Response.ok().entity(allEvents).build();
	}
	
	@Path("/top5")
	@GET
	@Produces (MediaType.APPLICATION_JSON)
	public Response getTop() {
		List<Event> allEvents = events.getNewestEvent();
		return Response.ok().entity(allEvents).build();
	}

	@Path("/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPublicEventById(@PathParam("id") Integer id) {

		try {
			return Response.ok().entity(events.get(id)).build();
		} catch (ElementNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(Status.NOT_FOUND).build();
		}
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getPublicEventByKW(List<KeyWord> keyWords) {

		return Response.ok().entity(events.getByKeywords(keyWords)).build();

	}

}
