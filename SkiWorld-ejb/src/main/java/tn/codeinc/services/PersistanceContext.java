package tn.codeinc.services;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Singleton
public class PersistanceContext implements PersistanceContextLocal, PersistanceContextRemote {

		@PersistenceContext
		EntityManager em;
	@Override
	public EntityManager getEM() {
		return em;
	}

}
