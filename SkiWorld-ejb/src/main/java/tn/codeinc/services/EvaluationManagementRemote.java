package tn.codeinc.services;

import java.util.List;

import javax.ejb.Remote;

import tn.codeinc.persistance.Evaluation;



@Remote
public interface EvaluationManagementRemote {
public void AddEvaluation(Evaluation evaluation);
public List<Evaluation> findAll();


}
