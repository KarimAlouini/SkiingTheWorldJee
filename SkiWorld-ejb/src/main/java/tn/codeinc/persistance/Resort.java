package  tn.codeinc.persistance;
import javax.persistence.Entity;

import tn.codeinc.persistance.*;

@Entity
public class Resort extends Establishment {
	
	private int nbrPers,prixNuit,classification,nbrLit;
	private String nomResort;
	
	
	public Resort(int nbrPers, int prixNuit, int classification, int nbrLit, String nomResort ) {
		super();
		
		this.nbrPers = nbrPers;
		this.prixNuit = prixNuit;
		this.classification = classification;
		this.nbrLit = nbrLit;
		this.nomResort = nomResort;
	}


	public int getNbrPers() {
		return nbrPers;
	}


	public void setNbrPers(int nbrPers) {
		this.nbrPers = nbrPers;
	}


	public int getPrixNuit() {
		return prixNuit;
	}


	public void setPrixNuit(int prixNuit) {
		this.prixNuit = prixNuit;
	}


	public int getClassification() {
		return classification;
	}


	public void setClassification(int classification) {
		this.classification = classification;
	}


	public int getNbrLit() {
		return nbrLit;
	}


	public void setNbrLit(int nbrLit) {
		this.nbrLit = nbrLit;
	}


	public String getNomResort() {
		return nomResort;
	}


	public void setNomResort(String nomResort) {
		this.nomResort = nomResort;
	}


	public Resort() {
	}


}
