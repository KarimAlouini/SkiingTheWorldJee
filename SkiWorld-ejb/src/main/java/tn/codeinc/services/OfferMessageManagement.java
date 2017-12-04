package tn.codeinc.services;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import tn.codeinc.persistance.OfferMessage;
import tn.codeinc.persistance.User;

@Stateless
public class OfferMessageManagement implements OfferMessageManagementLocal,OfferMessageManagementRemote {

	@PersistenceContext
	EntityManager em;
	
	@Override
	public void create(OfferMessage offerMessage) {
		em.persist(offerMessage);
		
	}

	@Override
	public List<OfferMessage> getAll() {
	     return em.createQuery("SELECT om FROM OfferMessage om",OfferMessage.class).getResultList();
		
	}

	@Override
	public OfferMessage get(int id) {
		return em.find(OfferMessage.class, id);
	}

	@Override
	public void remove(OfferMessage offerMessage) {
		em.remove(offerMessage);
		
	}

	@Override
	public void update(OfferMessage offerMessage) {
		em.merge(offerMessage);
		
	}

	@Override
	public OfferMessage getByDate(Date messageDate) {
		try{
			return em.createQuery("SELECT mo FROM OfferMessage mo WHERE mo.messageDate = :messageDate", OfferMessage.class)
					.setParameter("messageDate", messageDate).getSingleResult();
			}catch (NoResultException e){
				return null;
			}
		
		}

	@Override
	public List<OfferMessage> getByUsers(User agent, User client) {
		System.out.println("OfferMessageManagement.getByUsers() agent "+agent.getId());
		System.out.println("OfferMessageManagement.getByUsers() client "+client.getId());

		return em.createQuery("FROM OfferMessage WHERE (agent = :a AND client = :c) OR (agent = :c AND client =:a)",OfferMessage.class)
				.setParameter("a", agent)
				.setParameter("c", client)
				.getResultList();
	}
}