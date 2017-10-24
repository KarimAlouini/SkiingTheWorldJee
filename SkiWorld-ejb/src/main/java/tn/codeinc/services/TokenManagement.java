package tn.codeinc.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.codeinc.persistance.AccessToken;
import tn.codeinc.persistance.User;

@Stateless
public class TokenManagement implements TokenManagementLocal, TokenManagementRemote {

	@PersistenceContext
	EntityManager em;

	@Override
	public List<AccessToken> getAll() {

		return em.createQuery("SELECT t FROM AccessToken t", AccessToken.class).getResultList();
	}

	@Override
	public void add(AccessToken at) {
		em.persist(at);
	}

	@Override
	public AccessToken get(String value) {
		Query q = em.createQuery("SELECT t FROM AccessToken t  where t.value = :value");
		q.setParameter("value", value);
		if (!q.getResultList().isEmpty())
			return (AccessToken) q.getResultList().get(0);
		return null;
	}

	@Override
	public AccessToken getLastPerUser(User u) {
		String query = "SELECT t FROM AccessToken t "
				+ "WHERE t.user = :user  AND"
				+ " t.expiresAt >= current_date ORDER BY"
				+ " t.expiresAt DESC";
		try{
			return em.createQuery(query,
					AccessToken.class).setParameter("user", u).getSingleResult();
		}catch(NonUniqueResultException e){
			return em.createQuery(query,AccessToken.class)
					.setParameter(""
							+ ""
							+ "user", u)
					.getResultList().get(0);
		}
		catch(NoResultException e){
			return null;
		}
	}

}
