package tn.codeinc.services;

import javax.ejb.Local;
import javax.persistence.EntityManager;

@Local
public interface PersistanceContextLocal {
	public EntityManager getEM();
}
