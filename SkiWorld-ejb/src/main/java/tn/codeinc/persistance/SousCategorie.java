package tn.codeinc.persistance;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: SousCategorie
 *
 */
@Entity

public class SousCategorie implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String libelle;
	private static final long serialVersionUID = 1L;
	
	private CategorieEnum marqueCategorieProd;

	public SousCategorie() {
		super();
	}   
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}   
	public String getLibelle() {
		return this.libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
	public enum CategorieEnum{
		Adult,Children,Article
	}
   
}
