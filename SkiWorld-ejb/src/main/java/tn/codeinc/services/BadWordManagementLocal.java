package tn.codeinc.services;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import tn.codeinc.persistance.BadWord;

public interface BadWordManagementLocal {
	public void create(BadWord badWord) throws SQLIntegrityConstraintViolationException ;

	public List<BadWord> getAll();

	public BadWord get(int id);

	public void remove(BadWord badWord);

	public void update(BadWord badWord);

}
