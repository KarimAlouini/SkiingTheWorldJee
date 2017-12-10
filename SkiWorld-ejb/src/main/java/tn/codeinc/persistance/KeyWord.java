package tn.codeinc.persistance;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Entity implementation class for Entity: KeyWord
 *
 */
@Entity

public class KeyWord {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull
	private String content;
	
	@JsonIgnore
	@ManyToOne
	private Event event;


	public KeyWord() {
	}


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


	public KeyWord(String content, Event event) {
		super();
		this.content = content;
		this.event = event;
	}


	public Event getEvent() {
		return event;
	}


	public void setEvent(Event event) {
		this.event = event;
	}


	
	
   
}
