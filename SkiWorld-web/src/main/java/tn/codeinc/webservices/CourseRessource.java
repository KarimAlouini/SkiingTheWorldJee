package tn.codeinc.webservices;

import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import tn.codeinc.persistance.Courses;
import tn.codeinc.persistance.apply;
import tn.codeinc.services.CourseManagement;
import tn.codeinc.services.NotificationManagement;
import tn.codeinc.services.applyManagement;


@RequestScoped
@Path("/Courses")
public class CourseRessource {
	@EJB
	CourseManagement courseM;
	NotificationManagement nm;
	@EJB
	applyManagement app;
	
	
	
	@GET
	@Path("/getAll")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Courses> GetAllCourses() {
		return courseM.listAllCourse();
	}
	@GET
	@Path("/getByLocation/{location}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Courses> GetCourseByLocation(@PathParam(value = "location") String location) {
		return courseM.findCourseByLocation(location);
	}
	@GET
	@Path("/getByLevel/{level}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Courses> GetCourseByLevel(@PathParam(value = "level") String level) {
		return courseM.findCourseByLevel(level);
	}
	@GET
	@Path("/getById/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Courses GetCourseById(@PathParam(value = "id") int id) {
		return courseM.findCourseByID(id);
	}
	@GET
	@Path("/getByState/{state}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Courses> GetCourseByState(@PathParam(value = "state") String state) {
		return courseM.findCourseByState(state);
	}
	@GET
	@Path("/getByDate/{date}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Courses> GetCourseByDate(@PathParam(value = "date") Date date) {
		return courseM.findCourseByDate(date);
	}
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createCourse(Courses course) {
		courseM.addCourse(course);
		return Response.ok("New course has been created").build();
		//return Response.status(Status.CREATED).entity(courseMetier.addCourse(course)).build();
	}
	@PUT
	@Path("/delete/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response DeleteCourse(@PathParam(value = "id") int id) {
		
		courseM.deleteCourse(id); 
		EchoServer.notif="";
		for(int i=0;i<courseM.listNotif().size();i++){
			EchoServer.notif+=courseM.listNotif().get(i).toString();
		}
		return Response.ok("Course " + courseM.findCourseByID(id).getCourseName() + " is deleted").build();
		
	}
	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateCourse(Courses course) {
		courseM.updateCourse(course);
		return Response.ok("Course updated successfully").build();
		//return Response.status(Status.CREATED).entity(courseMetier.addCourse(course)).build();
	}
	@POST
	@Path("/apply")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response apply(apply apply) {
		app.addCourse(apply);
		return Response.ok("New course has been created").build();
		//return Response.status(Status.CREATED).entity(courseMetier.addCourse(course)).build();
	}
	@GET
	@Path("/getapp")
	@Produces(MediaType.APPLICATION_JSON)
	public List<apply> GetAll() {
		return app.getAll();
	}
	@GET
	@Path("/gets1")
	@Produces(MediaType.APPLICATION_JSON)
	public Integer Gets1() {
		return courseM.getl1();
	}
	@GET
	@Path("/gets2")
	@Produces(MediaType.APPLICATION_JSON)
	public Integer Gets2() {
		return courseM.getl2();
	}
	@GET
	@Path("/gets3")
	@Produces(MediaType.APPLICATION_JSON)
	public Integer Gets3() {
		return courseM.getl3();
	}
	
	
	
	

}  
