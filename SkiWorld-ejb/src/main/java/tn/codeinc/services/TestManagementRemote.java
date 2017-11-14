package tn.codeinc.services;

import java.util.List;

import javax.ejb.Remote;

import tn.codeinc.persistance.Test;


@Remote
public interface TestManagementRemote {
	public String addTest();
	public String deleteTest(Test test);
	public String updateTest(Test test);
	public List<Test> listAllTests();
	public Test findTestByID(int id);
}
