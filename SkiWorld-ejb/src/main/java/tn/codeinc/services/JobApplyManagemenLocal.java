package tn.codeinc.services;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import tn.codeinc.exceptions.AuthorizationException;
import tn.codeinc.exceptions.ElementNotFoundException;
import tn.codeinc.exceptions.JobApplicationDuplicationException;
import tn.codeinc.persistance.JobApply;
import tn.codeinc.persistance.JobApplyId;
import tn.codeinc.persistance.User;


@Local	
public interface JobApplyManagemenLocal {
	public void create(JobApply jobApply)throws JobApplicationDuplicationException,ElementNotFoundException;
	public List<JobApply> getAll();
	public JobApply get(JobApplyId id);
	public void remove(JobApply jobApply) throws ElementNotFoundException,AuthorizationException;
	public void update(JobApply jobApply) throws ElementNotFoundException, AuthorizationException;
	public JobApply getByDate(Date jobApplyDate);
	public List<JobApply> getByAgent(User agent);

}
