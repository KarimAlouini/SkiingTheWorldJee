package tn.codeinc.webservices;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tn.codeinc.persistance.CourseParticipation;
import tn.codeinc.services.CourseReservationsManagement;

@RequestScoped
@Path("/CourseResrvation")
public class CourseReservationRessource {
	@EJB
	CourseReservationsManagement reservationM;
	@POST
	@Path("/addReservation")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createReservation(CourseParticipation reservation) {
		reservationM.addReservation(reservation);
		return Response.status(200).build();
		//return Response.status(Status.CREATED).entity(courseMetier.addCourse(course)).build();
	}
	@GET
	@Path("/getMyReservations/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<CourseParticipation> GetMyReservations(@PathParam(value = "name") String name) {
		return reservationM.listAllMyReservations(name);
	}
	@GET
	@Path("/getAllReservations")
	@Produces(MediaType.APPLICATION_JSON)
	public List<CourseParticipation> GetAllReservations() {
		return reservationM.listAllReservations();
	}
	@DELETE
	@Path("/cancelReservation/{e}/{f}/{g}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response cancelReservation(@PathParam("e") int x,@PathParam("f") int y,@PathParam("g") int z) {
		reservationM.cancelReservation(x,y,z);
		return Response.ok("reservation has been canceled").build();
	}
	
}
