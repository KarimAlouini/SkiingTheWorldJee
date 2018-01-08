package tn.codeinc.webservices;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
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
import javax.ws.rs.core.Response.Status;

import tn.codeinc.client.CurrentUserLocal;
import tn.codeinc.persistance.Test;
import tn.codeinc.persistance.User.UserRole;
import tn.codeinc.services.TestManagement;

@RequestScoped
@Path("/secured/Tests")
public class TestRessource {
@EJB
TestManagement tM;
@Inject
CurrentUserLocal currentUser;
@POST
@Path("/addTest")
@Produces(MediaType.APPLICATION_JSON)
public Response createTest() {
	if(!(currentUser.get().getRole() == UserRole.ROLE_SUPER_ADMIN) && !(currentUser.get().getRole() == UserRole.ROLE_MODERATOR)){
		return Response.status(Status.UNAUTHORIZED).build();
	}
	tM.addTest();
	return Response.ok("Test has been created").build();
	//return Response.status(Status.CREATED).entity(courseMetier.addCourse(course)).build();
}
@GET
@Path("/getAllTests")
@Produces(MediaType.APPLICATION_JSON)
public Response GetAllTests() {
	if(!(currentUser.get().getRole() == UserRole.ROLE_SUPER_ADMIN) && !(currentUser.get().getRole() == UserRole.ROLE_MODERATOR)){
		return Response.status(Status.UNAUTHORIZED).build();
	}
	return Response.ok().entity(tM.listAllTests()).build();
	
}
@DELETE
@Path("/deleteTest/{id}")
@Consumes(MediaType.APPLICATION_JSON)
public Response DeleteCourse(@PathParam(value = "id") int id) {
	if(!(currentUser.get().getRole() == UserRole.ROLE_SUPER_ADMIN) && !(currentUser.get().getRole() == UserRole.ROLE_MODERATOR)){
		return Response.status(Status.UNAUTHORIZED).build();
	}
	tM.deleteTest(tM.findTestByID(id)) ;
	return Response.ok("Test has been deleted").build();
	
}
@PUT
@Path("/updateTest/{id}")
@Consumes(MediaType.APPLICATION_JSON)
public Response updateTest(Test test) {
	if(!(currentUser.get().getRole() == UserRole.ROLE_SUPER_ADMIN) && !(currentUser.get().getRole() == UserRole.ROLE_MODERATOR)){
		return Response.status(Status.UNAUTHORIZED).build();
	}
	tM.updateTest(test);
	
	return Response.ok("Test has been updated").build();
	//return Response.status(Status.CREATED).entity(courseMetier.addCourse(course)).build();
}
}
