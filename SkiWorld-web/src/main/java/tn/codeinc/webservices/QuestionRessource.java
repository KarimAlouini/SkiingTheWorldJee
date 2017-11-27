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
import tn.codeinc.persistance.Questions;
import tn.codeinc.services.QuestionManagement;

@RequestScoped
@Path("/Questions")
public class QuestionRessource {
	@EJB
	QuestionManagement questionM;
	@POST
	@Path("/addQuestion")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createQuestion(Questions question) {
		questionM.addQuestion(question);
		
		return Response.status(200).build();
		//return Response.status(Status.CREATED).entity(courseMetier.addCourse(course)).build();
	}
	@PUT
	@Path("/updateQuestion")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateReview(Questions question) {
		questionM.updateQuestion(question);
		
		return Response.status(200).build();
		//return Response.status(Status.CREATED).entity(courseMetier.addCourse(course)).build();
	}
	@GET
	@Path("/getQuestionById/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Questions GetQuestionById(@PathParam(value = "id") int id) {
		return questionM.findQuestionByID(id);
	}
	@GET
	@Path("/getAllQuestions")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Questions> GetAllQuestions() {
		return questionM.listAllQuestions();
	}
	@DELETE
	@Path("/deleteQuestion/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteQuestion(@PathParam("id") int id) {
		questionM.deleteQuestion(id);
		return Response.ok("question has been deleted").build();
	}
}
