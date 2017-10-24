package tn.codeinc.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.codeinc.persistance.CourseReview;
import tn.codeinc.persistance.Questions;

/**
 * Session Bean implementation class QuestionManagement
 */
@Stateless
@LocalBean
public class QuestionManagement implements QuestionManagementRemote{
	@PersistenceContext
	EntityManager em;
    /**
     * Default constructor. 
     */
    public QuestionManagement() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public String addQuestion(Questions question) {
		try {
			em.persist(question);
			return "Question added succesfully";
		} catch (Exception ex) {
			ex.printStackTrace();
			return String.format("Failed to add question", ex.toString());
		}
	}

	

	@Override
	public String updateQuestion(Questions question) {
		try {
			em.merge(question);
			return "Question updated with success";
		} catch (Exception ex) {
			ex.printStackTrace();
			return String.format("Failed to update question", ex.toString());
		}
	}

	@Override
	public Questions findQuestionByID(int id) {
		return em.find(Questions.class, id);
	}

	@Override
	public List<Questions> listAllQuestions() {
		List<Questions> listQuestions;
		listQuestions = em.createQuery("Select q from Questions q").getResultList();
		return listQuestions;
	}

	@Override
	public String deleteQuestion(int id) {
		try {
			em.remove(em.merge(em.find(CourseReview.class, id)));
			return "Question deleted succesfully";
		} catch (Exception ex) {
			ex.printStackTrace();
			return String.format("Failed to delete question", ex.toString());
		}
	}

}
