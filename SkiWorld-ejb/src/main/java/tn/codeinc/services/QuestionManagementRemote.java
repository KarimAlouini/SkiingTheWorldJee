package tn.codeinc.services;


import java.util.List;

import javax.ejb.Remote;

import tn.codeinc.persistance.Questions;

@Remote
public interface QuestionManagementRemote {
	public String addQuestion(Questions question);
	public String deleteQuestion(int id);
	public String updateQuestion(Questions question);
	public Questions findQuestionByID(int id);
	public List<Questions> listAllQuestions();
}
