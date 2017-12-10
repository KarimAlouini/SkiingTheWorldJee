package tn.codeinc.persistance;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * Entity implementation class for Entity: BadWords
 *
 */
@Entity
public class BadWord implements Serializable {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	
	@NotNull
	@Column(unique=true)
	private String content;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public BadWord() {
		super();
	}

	public BadWord(String content) {
		super();
		this.content = content;
	}

	@Override
	public String toString() {
		return "BadWords [content=" + content + "]";
	}
	
	
   
}
