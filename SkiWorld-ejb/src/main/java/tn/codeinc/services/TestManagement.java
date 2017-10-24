package tn.codeinc.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.codeinc.persistance.Questions;
import tn.codeinc.persistance.Test;

/**
 * Session Bean implementation class TestManagement
 */
@Stateless
@LocalBean
public class TestManagement implements TestManagementRemote {
	@PersistenceContext
	EntityManager em;
    /**
     * Default constructor. 
     */
    public TestManagement() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public String addTest() {
		try {
			Test test = new Test();
			List<Questions> questions = new ArrayList<Questions>();
			questions.addAll(em.createQuery("select  DISTINCT q FROM Questions q ORDER BY rand()").setMaxResults(5).getResultList());
			test.setQuestions(questions);
			em.persist(test);
		
			return "Test added succesfully";
		} catch (Exception ex) {
			ex.printStackTrace();
			return String.format("Failed to add test", ex.toString());
		}
	}


	@Override
	public String updateTest(Test test) {
		try {
			em.merge(test);
			return "test updated with success";
		} catch (Exception ex) {
			ex.printStackTrace();
			return String.format("Failed to update test", ex.toString());
		}
	}

	@Override
	public List<Test> listAllTests() {
		List<Test> listTests;
		listTests = em.createQuery("Select t from Test t").getResultList();
		return listTests;
	}

	@Override
	public String deleteTest(Test test) {
		try {
			em.remove(em.contains(test) ? test : em.merge(test));
			
			return "Test deleted succesfully";
		} catch (Exception ex) {
			ex.printStackTrace();
			return String.format("Failed to delete test", ex.toString());
		}
	}

	@Override
	public Test findTestByID(int id) {
		// TODO Auto-generated method stub
		return em.find(Test.class, id);
	}
	
	

}
