package tn.codeinc.webservices;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
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
import tn.codeinc.persistance.Courses;
import tn.codeinc.persistance.User.UserRole;
import tn.codeinc.services.CourseManagement;
import tn.codeinc.services.NotificationManagement;


@RequestScoped
@Path("/secured/Courses")
public class CourseSecuredRessource {
	@EJB
	CourseManagement courseM;
	NotificationManagement nm;
	@Inject
	CurrentUserLocal currentUser;
	
	
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createCourse(Courses course) {
		if( !(currentUser.get().getRole() == UserRole.ROLE_GUIDE) ){
			return Response.status(Status.UNAUTHORIZED).build();
		}
		courseM.addCourse(course);
		
		return Response.ok("New course has been created").build();
		//return Response.status(Status.CREATED).entity(courseMetier.addCourse(course)).build();
	}
	
	@PUT
	@Path("/delete/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response DeleteCourse(@PathParam(value = "id") int id) {
		if( !(currentUser.get().getRole() == UserRole.ROLE_GUIDE) ){
			return Response.status(Status.UNAUTHORIZED).build();
		}
		courseM.deleteCourse(id); 
		EchoServer.notif="";
		for(int i=0;i<courseM.listNotif().size();i++){
			EchoServer.notif+=courseM.listNotif().get(i).toString();
		}
		return Response.ok("Course " + courseM.findCourseByID(id).getCourseName() + " is deleted").build();
		
	}
	@PUT
	@Path("/update/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateCourse(Courses course) {
		if( !(currentUser.get().getRole() == UserRole.ROLE_GUIDE) ){
			return Response.status(Status.UNAUTHORIZED).build();
		}
		courseM.updateCourse(course);
		
		return Response.ok("Course updated successfully").build();
		//return Response.status(Status.CREATED).entity(courseMetier.addCourse(course)).build();
	}
	@GET
	@Path("/stat")
	@Produces(MediaType.APPLICATION_JSON)
	public Response stat() {
		if( !(currentUser.get().getRole() == UserRole.ROLE_GUIDE) ){
			return Response.status(Status.UNAUTHORIZED).build();
		}
		
		return Response.ok().entity( courseM.Stat()).build();
	}

}  
