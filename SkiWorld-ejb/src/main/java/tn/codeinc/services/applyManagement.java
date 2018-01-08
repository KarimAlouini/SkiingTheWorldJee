package tn.codeinc.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import tn.codeinc.persistance.apply;

/**
 * Session Bean implementation class CourseManagement
 */
@Stateless
@LocalBean
public class applyManagement implements applyRemote {
	@Inject
	PersistanceContextLocal pc;
	@Override
	public Boolean addCourse(apply apply) {
		try {
			pc.getEM().persist(apply);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
		
	}
	@Override
	public List<apply> getAll() {
		String requete ="SELECT a FROM apply a";
		return pc.getEM().createQuery(requete,apply.class).getResultList();
	}

}
