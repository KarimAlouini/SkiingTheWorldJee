package tn.codeinc.webservices;

import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import tn.codeinc.persistance.TestLevel;
import tn.codeinc.services.TestLevelManagement;

@RequestScoped
@Path("/LevelTest")
public class TestLevelRessource {
	@EJB
	TestLevelManagement tlM;
	@POST
	@Path("/takeTest")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createParticipation(TestLevel test) {
		tlM.TestParticipation(test);
		return Response.status(200).build();
		//return Response.status(Status.CREATED).entity(courseMetier.addCourse(course)).build();
	}
	
	@GET
	@Path("/getAllMyTests/{participant}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<TestLevel> GetAllMyTest(@PathParam(value = "participant") String participant) {
		return tlM.listAllMyTestParticipation(participant);
	}
	@GET
	@Path("/trackMyScore/{participant}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<TestLevel> TrackMyScore(@PathParam(value = "participant") String participant) {
		return tlM.trackMyProgress(participant);
	}
	
	
}
