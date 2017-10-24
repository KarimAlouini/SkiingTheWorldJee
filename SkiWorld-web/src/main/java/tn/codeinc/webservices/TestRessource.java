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

import tn.codeinc.persistance.Test;
import tn.codeinc.services.TestManagement;

@RequestScoped
@Path("/Tests")
public class TestRessource {
@EJB
TestManagement tM;
@POST
@Path("/addTest")
@Produces(MediaType.APPLICATION_JSON)
public Response createTest() {
	tM.addTest();
	return Response.status(200).build();
	//return Response.status(Status.CREATED).entity(courseMetier.addCourse(course)).build();
}
@GET
@Path("/getAllTests")
@Produces(MediaType.APPLICATION_JSON)
public List<Test> GetAllTests() {
	return tM.listAllTests();
}
@DELETE
@Path("/deleteTest/{id}")
@Consumes(MediaType.APPLICATION_JSON)
public String DeleteCourse(@PathParam(value = "id") int id) {
	tM.deleteTest(tM.findTestByID(id)) ;
	return "Test deleted ";
	
}
@PUT
@Path("/updateTest/{id}")
@Consumes(MediaType.APPLICATION_JSON)
public Response updateTest(Test test) {
	tM.updateTest(test);
	
	return Response.status(200).build();
	//return Response.status(Status.CREATED).entity(courseMetier.addCourse(course)).build();
}
}
