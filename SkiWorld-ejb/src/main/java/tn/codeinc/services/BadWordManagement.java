package tn.codeinc.services;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.codeinc.persistance.BadWord;

@Stateless
public class BadWordManagement implements BadWordManagementLocal, BadWordManagementRemote {

	@PersistenceContext
	EntityManager em;

	@Override
	public void create(BadWord badWord) throws SQLIntegrityConstraintViolationException {
		em.persist(badWord);
	}

	@Override
	public List<BadWord> getAll() {
		return em.createQuery("SELECT b FROM BadWord b", BadWord.class).getResultList();
	}

	@Override
	public BadWord get(int id) {
		return em.find(BadWord.class, id);
	}

	@Override
	public void remove(BadWord badWord) {
		em.remove(badWord);
	}

	@Override
	public void update(BadWord badWord) {
		em.merge(badWord);
	}

}
