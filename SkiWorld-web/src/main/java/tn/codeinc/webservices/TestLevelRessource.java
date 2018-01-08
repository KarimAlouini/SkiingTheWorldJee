package tn.codeinc.webservices;

import java.util.List;

import javax.ejb.EJB;
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
import tn.codeinc.persistance.TestLevel;
import tn.codeinc.persistance.User.UserRole;
import tn.codeinc.services.TestLevelManagement;

@RequestScoped
@Path("/secured/LevelTest")
public class TestLevelRessource {
	@EJB
	TestLevelManagement tlM;
	@Inject
	CurrentUserLocal currentUser;
	@POST
	@Path("/takeTest")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createParticipation(TestLevel test) {
		if(!(currentUser.get().getRole() == UserRole.ROLE_SUPER_ADMIN) && !(currentUser.get().getRole() == UserRole.ROLE_MODERATOR) && !(currentUser.get().getRole() == UserRole.ROLE_GUIDE) && !(currentUser.get().getRole() == UserRole.ROLE_USER)){
			return Response.status(Status.UNAUTHORIZED).build();
		}
		tlM.TestParticipation(test);
		return Response.ok("TestLevel has been created").build();
		//return Response.status(Status.CREATED).entity(courseMetier.addCourse(course)).build();
	}
	
	@GET
	@Path("/getAllMyTests/{participant}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response GetAllMyTest(@PathParam(value = "participant") String participant) {
		if(!(currentUser.get().getRole() == UserRole.ROLE_SUPER_ADMIN) && !(currentUser.get().getRole() == UserRole.ROLE_MODERATOR) && !(currentUser.get().getRole() == UserRole.ROLE_GUIDE) && !(currentUser.get().getRole() == UserRole.ROLE_USER)){
			return Response.status(Status.UNAUTHORIZED).build();
		}
		
		return Response.ok().entity(tlM.listAllMyTestParticipation(participant)).build();
	}
	@GET
	@Path("/trackMyScore/{participant}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response TrackMyScore(@PathParam(value = "participant") String participant) {
		if(!(currentUser.get().getRole() == UserRole.ROLE_SUPER_ADMIN) && !(currentUser.get().getRole() == UserRole.ROLE_MODERATOR) && !(currentUser.get().getRole() == UserRole.ROLE_GUIDE) && !(currentUser.get().getRole() == UserRole.ROLE_USER)){
			return Response.status(Status.UNAUTHORIZED).build();
		}
		
		return Response.ok().entity(tlM.trackMyProgress(participant)).build();
	}
	
	
}
