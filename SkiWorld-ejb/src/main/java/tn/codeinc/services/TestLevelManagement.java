package tn.codeinc.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.codeinc.persistance.TestLevel;

/**
 * Session Bean implementation class TestLevelManagement
 */
@Stateless
@LocalBean
public class TestLevelManagement implements TestLevelManagementRemote{
	@PersistenceContext
	EntityManager em;
    /**
     * Default constructor. 
     */
    public TestLevelManagement() {
        // TODO Auto-generated constructor stub
    }


	@Override
	public String TestParticipation(TestLevel test) {
		try {
			em.persist(test);
			return "Participation added succesfully";
		} catch (Exception ex) {
			ex.printStackTrace();
			return String.format("Failed to save participation", ex.toString());
		}
		
	}

	@Override
	public List<TestLevel> listAllMyTestParticipation(String participant) {
		
		try{
		   	 return em.createQuery(
				        "SELECT t FROM TestLevel t WHERE t.user.firstName = :participant",TestLevel.class)
				        .setParameter("participant", participant).getResultList();
	    } catch(Exception e) {
	        return null;
	    }
	}

	@Override
	public List<TestLevel> trackMyProgress(String participant) {
		try{
		   	 return em.createQuery(
				        "SELECT t FROM TestLevel t WHERE t.user.firstName = :participant order by t.date",TestLevel.class)
				        .setParameter("participant", participant).getResultList();
	    } catch(Exception e) {
	        return null;
	    }
	}

}
