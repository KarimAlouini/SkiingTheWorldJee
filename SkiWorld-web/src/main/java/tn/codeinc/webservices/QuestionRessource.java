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
import tn.codeinc.persistance.Questions;
import tn.codeinc.persistance.User.UserRole;
import tn.codeinc.services.QuestionManagement;

@RequestScoped
@Path("/secured/Questions")
public class QuestionRessource {
	@EJB
	QuestionManagement questionM;
	@Inject
	CurrentUserLocal currentUser;
	@POST
	@Path("/addQuestion")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createQuestion(Questions question) {
		if(!(currentUser.get().getRole() == UserRole.ROLE_SUPER_ADMIN) && !(currentUser.get().getRole() == UserRole.ROLE_MODERATOR)){
			return Response.status(Status.UNAUTHORIZED).build();
		}
		questionM.addQuestion(question);
		return Response.ok("Question has been created").build();
		//return Response.status(Status.CREATED).entity(courseMetier.addCourse(course)).build();
	}
	@PUT
	@Path("/updateQuestion")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateReview(Questions question) {
		if(!(currentUser.get().getRole() == UserRole.ROLE_SUPER_ADMIN) && !(currentUser.get().getRole() == UserRole.ROLE_MODERATOR)){
			return Response.status(Status.UNAUTHORIZED).build();

		}
		questionM.updateQuestion(question);
		
		return Response.ok("Question has been updated").build();
		//return Response.status(Status.CREATED).entity(courseMetier.addCourse(course)).build();
	}
	@GET
	@Path("/getQuestionById/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response GetQuestionById(@PathParam(value = "id") int id) {
		if(!(currentUser.get().getRole() == UserRole.ROLE_SUPER_ADMIN) && !(currentUser.get().getRole() == UserRole.ROLE_MODERATOR)){
			return Response.status(Status.UNAUTHORIZED).build();

		}
		return Response.ok().entity(questionM.findQuestionByID(id)).build();
		//return questionM.findQuestionByID(id);
	}
	@GET
	@Path("/getAllQuestions")
	@Produces(MediaType.APPLICATION_JSON)
	public Response GetAllQuestions() {
		
		
		if(!(currentUser.get().getRole() == UserRole.ROLE_SUPER_ADMIN) && !(currentUser.get().getRole() == UserRole.ROLE_MODERATOR)){
			return Response.status(Status.UNAUTHORIZED).build();

		}
		return Response.ok().entity(questionM.listAllQuestions()).build();

		
	}
	@DELETE
	@Path("/deleteQuestion/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteQuestion(@PathParam("id") int id) {
		if(!(currentUser.get().getRole() == UserRole.ROLE_SUPER_ADMIN) && !(currentUser.get().getRole() == UserRole.ROLE_MODERATOR)){
			return Response.status(Status.UNAUTHORIZED).build();
		}
		questionM.deleteQuestion(id);
		return Response.ok("question has been deleted").build();
	}
}
