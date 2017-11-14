package  tn.codeinc.persistance;
import javax.persistence.Entity;

@Entity
public class Chalet extends Establishment {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5334574178287938633L;
	private int nbrPers,prixNuit,nbrchambres,classification,nbrLit;
	private String nomChalet;
	
	
	
	public Chalet(int nbrPers, int prixNuit, int nbrchambres, int classification, int nbrLit, String nomChalet ) {
		super();
		
		this.nbrPers = nbrPers;
		this.prixNuit = prixNuit;
		this.nbrchambres = nbrchambres;
		this.classification = classification;
		this.nbrLit = nbrLit;
		this.nomChalet = nomChalet;
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

	public int getNbrchambres() {
		return nbrchambres;
	}

	public void setNbrchambres(int nbrchambres) {
		this.nbrchambres = nbrchambres;
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

	public String getNomChalet() {
		return nomChalet;
	}

	public void setNomChalet(String nomChalet) {
		this.nomChalet = nomChalet;
	}

	public Chalet() {
	}


}
