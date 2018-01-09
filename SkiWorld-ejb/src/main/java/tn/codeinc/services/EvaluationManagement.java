package tn.codeinc.services;


import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.codeinc.persistance.Courses;
import tn.codeinc.persistance.Evaluation;

/**
 * Session Bean implementation class EvaluationManagement
 */
@Stateless
@LocalBean
public class EvaluationManagement implements EvaluationManagementRemote {

    /**
     * Default constructor. 
     */
	@PersistenceContext
	private EntityManager entityManager;
    public EvaluationManagement() {
    }

    @Override
	public void AddEvaluation(Evaluation evaluation) {

	 List<Evaluation> evaluations=findAll();
	 System.out.println(evaluations.isEmpty());

	  if(evaluations.isEmpty()==true){
			entityManager.persist(evaluation);
		 	System.out.println("dejaaaa nulll");
		 	evaluations=findAll();

		 			
		 }
	 for (Evaluation eval : evaluations) {
		 		evaluations=findAll();
		 		System.out.println(eval.getIdEvaluation());
		 if(eval.getIdUser()==evaluation.getIdUser() && eval.getCourse().getCourseID()==evaluation.getCourse().getCourseID()){
			 		System.out.println("dejaaaa evalueee");
			 		break ;
			 	 //	 System.out.println(getEvaluationbyuserandoffer().isEmpty());

		 }	 
		 else if(eval.getIdUser()==evaluation.getIdUser() && eval.getCourse().getCourseID()!=evaluation.getCourse().getCourseID())
		 {
			 	entityManager.persist(evaluation);
		 		System.out.println("dejaaaa not offer");
		 		//evaluations=findAll();
		 						
		 }
		 else if(eval.getIdUser()!=evaluation.getIdUser() && eval.getCourse().getCourseID()==evaluation.getCourse().getCourseID())
		 {
			 	entityManager.persist(evaluation);
		 		System.out.println("dejaaaa not user");
		 		//evaluations=findAll();
		 						
		 }
		  
		
	
	 
	 }
	
		
	}

	@Override
	public List<Evaluation> findAll() {
		String requete = "SELECT o FROM Evaluation o";
		return entityManager.createQuery(requete,Evaluation.class).getResultList();
	}
	
	public List<Evaluation> getEvaluationbyuserandoffer(int iduser,int idoffer) {
		List<Evaluation> rl =new ArrayList<Evaluation>();
		rl = entityManager.createQuery("Select o from Evaluation o where o.iduser =?1 and offerid=?1").setParameter(1,iduser).setParameter(1,idoffer).getResultList();
		return rl; 
	}
	
	
	
	
		
		
	
	
	
	
	
	
	
	
	
	
	}


