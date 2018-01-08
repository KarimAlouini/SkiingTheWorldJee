package tn.codeinc.services;

import java.util.List;

import javax.ejb.Remote;

import tn.codeinc.persistance.apply;

@Remote
public interface applyRemote {
	public Boolean addCourse(apply apply);
	public List<apply> getAll();
}
