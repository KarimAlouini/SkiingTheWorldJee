package tn.codeinc.persistance;



import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Entity implementation class for Entity: Evaluation
 *
 */
@Entity
public class Evaluation implements Serializable {

	   
	@Id
	@GeneratedValue( strategy= GenerationType.AUTO )
	private Integer IdEvaluation;
	private static final long serialVersionUID = 1L;
	private float value;
	private int IdUser;

	@ManyToOne
	private Course course;
	public float getValue() {
		return value;
	}
	public void setValue(float value) {
		this.value = value;
	}
	
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public int getIdUser() {
		
		return IdUser;
	}
	public void setIdUser(int idUser) {
		IdUser = idUser;
	}


	public Evaluation() {
		super();
	}   
	public Integer getIdEvaluation() {
		return this.IdEvaluation;
	}

	public void setIdEvaluation(Integer IdEvaluation) {
		this.IdEvaluation = IdEvaluation;
	}
   
}
