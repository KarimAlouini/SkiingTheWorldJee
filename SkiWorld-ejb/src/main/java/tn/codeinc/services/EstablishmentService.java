package tn.codeinc.services;

import java.util.List;

import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.codeinc.persistance.*;

@Stateless
public class EstablishmentService implements EstablishmentInterfaceLocal, EstablishmentInterfaceRemote{

	@PersistenceContext(name="SkiingTheWorld-ejb")
	EntityManager em;
	
	@Override
	public void addEstablishment(Establishment e) {
		
		em.persist(e);	
		
	}

	public Establishment findEstablishmentById(int id){
		
		return em.find(Establishment.class, id);
	}
	

	@Override
	public void delete(int id) {
		
		em.remove(em.merge(em.find(Establishment.class, id)));		
	}
	
	@Override
	public String updateEstablishment(Establishment e) {
		
		em.merge(e);
		return "establishment updated with success";
	}
	
	@Override
	public List<Establishment> findEstablishmentByName(String n) {
		
		return em.createQuery("SELECT e FROM Establishment e WHERE e.name LIKE '%"+n+"%'",Establishment.class).getResultList();
	}
	
	@Override
	public List<Establishment> getEstablishment() {	
		
		return em.createQuery("SELECT e FROM Establishment e ",Establishment.class).getResultList();
		
	}

	
	@Override
	public String seeDetailsAboutEstablishment(int id) {
		return em.find(Establishment.class,id).getName();
	}
	
	
	//////////////////////////////////////////
	
	
	@Override
	public void addChalet(Chalet c) {
		
		em.persist(c);
	}
	

	
	@Override
	public List<Chalet> getChal() {
	
		return em.createQuery("SELECT e FROM Chalet e ",Chalet.class).getResultList();
	}
	

	
	//////////////////////////////////////////////
	
	@Override
	public void addResort(Resort r) {
		
		em.persist(r);
	}
	
	@Override
	public List<Resort> getResort() {
		
		return em.createQuery("SELECT e FROM Resort e ",Resort.class).getResultList();
	}
	

}