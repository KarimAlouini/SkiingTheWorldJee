package tn.codeinc.webservices;

import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import tn.codeinc.client.CurrentUserLocal;
import tn.codeinc.persistance.OfferMessage;
import tn.codeinc.persistance.User;
import tn.codeinc.persistance.User.UserRole;
import tn.codeinc.services.OfferMessageService;

@RequestScoped
@Path("/msg")
public class OfferMessageResource {
	@EJB
	OfferMessageService OMS;
	@Inject
	CurrentUserLocal currentUser;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getOfferMessage() {
		return Response.ok().entity(OMS.getOfferMessage()).build();
	}

	@POST
	@Path("/msg")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response OfferMessage(OfferMessage msg) {
		User user = currentUser.get();
		msg.setUser(user);
		Date date = new Date();
		msg.setDate(date);
		OMS.OfferMessage(msg);
		return Response.status(200).build();
		// return
		// Response.status(Status.CREATED).entity(courseMetier.addCourse(course)).build();
	}

	@GET
	@Path("getAllMessage/{user}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<OfferMessage> listAllOfferMessageUser(@PathParam(value = "user") String user) {
		return OMS.listAllOfferMessageUser(user);
	}

	@GET
	@Path("ByDate/{user}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<OfferMessage> listByDate(@PathParam(value = "user") String user) {
		return OMS.listByDate(user);
	}

	@DELETE
	@Path("delet/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteOfferMessage(@PathParam("id") int id) {
		System.out.println(currentUser.get());
		if (currentUser.get().getRole() != UserRole.ROLE_SUPER_ADMIN) {
			return Response.status(Status.UNAUTHORIZED).build();
		}
		OMS.deleteOfferMessage(id);
		return Response.ok("OfferMessage deleted").build();
	}

}
