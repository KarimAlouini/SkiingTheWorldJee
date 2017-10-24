package tn.codeinc.services;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import tn.codeinc.persistance.KeyWord;

@Stateless
public class KeyWordManagement implements KeyWordManagementLocal,KeyWordManagementRemote {

	@PersistenceContext
	EntityManager em;
	
	@Override
	public void create(KeyWord keyWord) {
		em.persist(keyWord);		
	}

	@Override
	public List<KeyWord> getAll() {
		return em.createQuery("SELECT k FROM KeyWord k",KeyWord.class).getResultList();

	}

	@Override
	public KeyWord get(int id) {
		return em.find(KeyWord.class, id);
	}

	@Override
	public void remove(KeyWord keyWord) {
		em.remove(keyWord);
		
	}

	@Override
	public void update(KeyWord keyWord) {
		em.merge(keyWord);
	}

}
