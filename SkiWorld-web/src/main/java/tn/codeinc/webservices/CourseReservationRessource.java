package tn.codeinc.webservices;

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
import tn.codeinc.persistance.CourseParticipation;
import tn.codeinc.persistance.User.UserRole;
import tn.codeinc.services.CourseReservationsManagement;

@RequestScoped
@Path("/secured/CourseResrvation")
public class CourseReservationRessource {
	@EJB
	CourseReservationsManagement reservationM;
	@Inject
	CurrentUserLocal currentUser;
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createReservation(CourseParticipation reservation) {
		
		reservationM.addReservation(reservation);
		return Response.ok("Participation hes been regestred").build();
		//return Response.status(Status.CREATED).entity(courseMetier.addCourse(course)).build();
	}
	@GET
	@Path("/getMyReservations/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response GetMyReservations(@PathParam(value = "name") String name) {
		if(!(currentUser.get().getRole() == UserRole.ROLE_SUPER_ADMIN) && !(currentUser.get().getRole() == UserRole.ROLE_MODERATOR) && !(currentUser.get().getRole() == UserRole.ROLE_GUIDE) && !(currentUser.get().getRole() == UserRole.ROLE_USER)){
			return Response.status(Status.UNAUTHORIZED).build();
		}
		return Response.ok().entity(reservationM.listAllMyReservations(name)).build();
	}
	@GET
	@Path("/getAll")
	@Produces(MediaType.APPLICATION_JSON)
	public Response GetAllReservations() {
		return Response.ok().entity(reservationM.listAllReservations()).build();
	}
	@DELETE
	@Path("/delete/{e}/{f}/{g}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response cancelReservation(@PathParam("e") int x,@PathParam("f") int y,@PathParam("g") int z) {
		if(!(currentUser.get().getRole() == UserRole.ROLE_SUPER_ADMIN) && !(currentUser.get().getRole() == UserRole.ROLE_MODERATOR) && !(currentUser.get().getRole() == UserRole.ROLE_GUIDE) && !(currentUser.get().getRole() == UserRole.ROLE_USER)){
			return Response.status(Status.UNAUTHORIZED).build();
		}
		reservationM.cancelReservation(x,y,z);
		return Response.ok("Reservation has been canceled").build();
	}
	
}
