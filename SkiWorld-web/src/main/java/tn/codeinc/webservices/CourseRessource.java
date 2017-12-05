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
import tn.codeinc.persistance.Course;
import tn.codeinc.services.CourseManagement;
import tn.codeinc.services.NotificationManagement;


@RequestScoped
@Path("/Courses")
public class CourseRessource {
	@EJB
	CourseManagement courseM;
	NotificationManagement nm;
	
	
	@POST
	@Path("/addCourse")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createCourse(Course course) {
		courseM.addCourse(course);
		
		return Response.status(200).build();
		//return Response.status(Status.CREATED).entity(courseMetier.addCourse(course)).build();
	}
	@GET
	@Path("/getAllCourses")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Course> GetAllCourses() {
		return courseM.listAllCourse();
	}
	@GET
	@Path("/getCourseByLocation/{location}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Course> GetCourseByLocation(@PathParam(value = "location") String location) {
		return courseM.findCourseByLocation(location);
	}
	@GET
	@Path("/getCourseByLevel/{level}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Course> GetCourseByLevel(@PathParam(value = "level") String level) {
		return courseM.findCourseByLevel(level);
	}
	@GET
	@Path("/getCourseById/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Course GetCourseById(@PathParam(value = "id") int id) {
		return courseM.findCourseByID(id);
	}
	@GET
	@Path("/getCourseByState/{state}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Course> GetCourseByState(@PathParam(value = "state") String state) {
		return courseM.findCourseByState(state);
	}
	@GET
	@Path("/getCourseByDate/{date}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Course> GetCourseByDate(@PathParam(value = "date") Date date) {
		return courseM.findCourseByDate(date);
	}
	@PUT
	@Path("/deleteCourse/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public String DeleteCourse(@PathParam(value = "id") int id) {
		courseM.deleteCourse(id); 
		EchoServer.notif="";
		for(int i=0;i<courseM.listNotif().size();i++){
			EchoServer.notif+=courseM.listNotif().get(i).toString();
		}
		
		return "Course " + courseM.findCourseByID(id).getCourseName() + " is deleted";
	}
	@PUT
	@Path("/updateCourse/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateCourse(Course course) {
		courseM.updateCourse(course);
		
		return Response.status(200).build();
		//return Response.status(Status.CREATED).entity(courseMetier.addCourse(course)).build();
	}
	@GET
	@Path("/stat")
	@Produces(MediaType.APPLICATION_JSON)
	public List stat() {
		return courseM.Stat();
	}

}  
