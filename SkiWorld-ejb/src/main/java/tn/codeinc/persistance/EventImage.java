package tn.codeinc.persistance;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="event_image")
public class EventImage {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String fileName;
	@ManyToOne
	@JsonBackReference(value="events")
	private Event event;
	public EventImage() {
		super();
	}
	public EventImage(String fileName, Event event) {
		super();
		this.fileName = fileName;
		this.event = event;
	}
	public EventImage(Integer id, String fileName, Event event) {
		super();
		this.id = id;
		this.fileName = fileName;
		this.event = event;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public Event getEvent() {
		return event;
	}
	public void setEvent(Event event) {
		this.event = event;
	}

}
