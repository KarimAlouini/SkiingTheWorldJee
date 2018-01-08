package tn.codeinc.persistance;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(ignoreUnknown=true)
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull
	private String name;
	private String Location;
	@NotNull
	private Date Start;
	private Date End;
	@Column(length = 65535,columnDefinition="Text")
	private String description;
	private EventType statue;
	private String Image;
	private Integer maxPlace;

	private Date creationDate;
	
	@OneToMany(mappedBy="event",fetch=FetchType.EAGER)
	
	private List<EventImage> images;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user", nullable = false)
	private User host;
	
	@JsonIgnore
	@OneToMany(mappedBy = "event")
	private List<EventInvitation> eventInvitations;
	
	@OneToMany(targetEntity = KeyWord.class, fetch = FetchType.EAGER,cascade=CascadeType.ALL)
	private List<KeyWord> keyword;

	
	@ManyToMany(fetch = FetchType.EAGER)
	private List<User> users;

	public enum EventType {
		Public, Private
	}

	public List<EventInvitation> getEventInvitations() {
		return eventInvitations;
	}

	public void setEventInvitations(List<EventInvitation> eventInvitations) {
		this.eventInvitations = eventInvitations;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getHost() {
		return host;
	}

	public void setHost(User host) {
		this.host = host;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return Location;
	}

	public void setLocation(String location) {
		Location = location;
	}

	public Date getStart() {
		return Start;
	}

	public void setStart(Date start) {
		Start = start;
	}

	public Date getEnd() {
		return End;
	}

	public void setEnd(Date end) {
		End = end;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public EventType getStatue() {
		return statue;
	}

	public void setStatue(EventType statue) {
		this.statue = statue;
	}

	public String getImage() {
		return Image;
	}

	public void setImage(String image) {
		Image = image;
	}

	public Integer getMaxPlace() {
		return maxPlace;
	}

	public void setMaxPlace(Integer maxPlace) {
		this.maxPlace = maxPlace;
	}

	public List<KeyWord> getKeyword() {
		return keyword;
	}

	public void setKeyword(List<KeyWord> keyword) {
		this.keyword = keyword;
	}

	public Event(String name, String location, Date start, Date end, String description, String image, Integer maxPlace,
			User host, List<KeyWord> keyword) {
		super();
		this.name = name;
		Location = location;
		Start = start;
		End = end;
		this.description = description;
		Image = image;
		this.maxPlace = maxPlace;
		this.host = host;
		this.keyword = keyword;
	}
	

	public Event(String name, String location, Date start, Date end, String description, String image, Integer maxPlace,
			Date creationDate, User host, List<KeyWord> keyword) {
		super();
		this.name = name;
		Location = location;
		Start = start;
		End = end;
		this.description = description;
		Image = image;
		this.maxPlace = maxPlace;
		this.creationDate = new Date();
		this.host = host;
		this.keyword = keyword;
	}
	
	

	public Event() {
		this.creationDate = new Date();
	}

	public Event(String name, String location, Date start, Date end, String description, EventType statue, String image,
			Integer maxPlace, Date creationDate, List<EventImage> images, User host, List<KeyWord> keyword) {
		super();
		this.name = name;
		Location = location;
		Start = start;
		End = end;
		this.description = description;
		this.statue = statue;
		Image = image;
		this.maxPlace = maxPlace;
		this.creationDate = new Date();
		this.images = images;
		this.host = host;
		this.keyword = keyword;
	}

	public Event(String name, String location, Date start, Date end, String description, EventType statue, String image,
			Integer maxPlace, Date creationDate, List<EventImage> images, User host, List<KeyWord> keyword,
			List<User> users) {
		super();
		this.name = name;
		Location = location;
		Start = start;
		End = end;
		this.description = description;
		this.statue = statue;
		Image = image;
		this.maxPlace = maxPlace;
		this.creationDate = new Date();
		this.images = images;
		this.host = host;
		this.keyword = keyword;
		this.users = users;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Event other = (Event) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	public boolean isFull(){
		return this.maxPlace == this.users.size();
	}
	public boolean hasUser(User u){
		return this.users.contains(u);
	}
	
	
	public List<EventImage> getImages() {
		return images;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

}
