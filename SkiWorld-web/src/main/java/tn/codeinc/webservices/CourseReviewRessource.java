package tn.codeinc.webservices;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tn.codeinc.persistance.CourseReview;
import tn.codeinc.services.CourseReviewManagement;

@RequestScoped
@Path("/CourseReviews")
public class CourseReviewRessource {
	@EJB
	CourseReviewManagement reviewM;
	@POST
	@Path("/addReview")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createReview(CourseReview review) {
		reviewM.addReview(review);
		
		return Response.status(200).build();
		//return Response.status(Status.CREATED).entity(courseMetier.addCourse(course)).build();
	}
	@PUT
	@Path("/updateReview")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateReview(CourseReview review) {
		reviewM.updateReview(review);
		
		return Response.status(200).build();
		//return Response.status(Status.CREATED).entity(courseMetier.addCourse(course)).build();
	}
	@GET
	@Path("/getReviewById/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public CourseReview GetReviewById(@PathParam(value = "id") int id) {
		return reviewM.findReviewByID(id);
	}
	@GET
	@Path("/getReviewByCourse/{courseName}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<CourseReview> GetReviewByCourse(@PathParam(value = "courseName") String courseName) {
		return reviewM.findReviewByCourse(courseName);
	}
	@GET
	@Path("/getReviewByRate/{rate}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<CourseReview> GetCourseByRate(@PathParam(value = "rate") int rate) {
		return reviewM.findReviewByRate(rate);
	}
	@GET
	@Path("/getReviewByParticipant/{participantName}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<CourseReview> GetReviewByParticipant(@PathParam(value = "participantName") String participantName) {
		return reviewM.findReviewByParticipant(participantName);
	}
	@GET
	@Path("/getAllReviews")
	@Produces(MediaType.APPLICATION_JSON)
	public List<CourseReview> GetAllReviews() {
		return reviewM.listAllReviews();
	}
	
	@DELETE
	@Path("/deleteReview/{e}/{f}/{g}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteReview(@PathParam("e") int x,@PathParam("f") int y,@PathParam("g") int z) {

	reviewM.deleteReview(x,y,z);
		return Response.ok("review has been deleted").build();
	}

	
	
}
