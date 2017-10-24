package tn.codeinc.persistance;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Integer id;
	private String login, firstName, lastName, email, phoneNumber;

	@JsonIgnore
	@Transient
	private String plainPassword;

	private String password;
	private UserRole role;
	
	@JsonIgnore
	@OneToMany(targetEntity=Event.class,fetch=FetchType.EAGER)
	private List<Event> myEvents;
	@JsonIgnore
	@ManyToMany
	private List<Event> myParticipation;
	@OneToMany(mappedBy="user",fetch=FetchType.EAGER)
	
	@JsonIgnore
	
	private List<TestLevel> levelTests;
	@OneToMany(fetch=FetchType.EAGER,mappedBy="user")
	@JsonIgnore
	private List<CourseReview> reviews;
	@OneToMany(mappedBy="user",fetch=FetchType.EAGER)
	@JsonIgnore
	private List<CourseParticipation> participations;
	@JsonIgnore
	@OneToMany(fetch=FetchType.EAGER,mappedBy="user")
	private List<Notification> lstNotif;
	
	
	/*@OneToMany
	private List<EventInvitation> eventInvitationsSender;
	
	@OneToMany (mappedBy ="Receiver")
	private List<EventInvitation> eventInvitationsReceiver;
	*/
	@JsonIgnore
	@OneToMany(targetEntity=AdAreaPurchaseRequest.class,mappedBy="user",fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	private List<AdAreaPurchaseRequest> purchaseRequests;
	

	
	
	public enum UserRole {
		ROLE_MODERATOR, ROLE_USER, ROLE_SUPER_ADMIN, ROLE_GUIDE

	}

	@Embedded()
	
	private Address address;
	@Column(columnDefinition = "int default 0")
	private boolean isBanned;
	
	@Column(columnDefinition = "int default 0")
	private boolean isConfirmed;
	
	private String confirmationCode;
	
	

	public User() {

	}
	
	public void setConfirmationCode(String cofirmationCode) {
		this.confirmationCode = cofirmationCode;
	}

	public User(String login, String firstName, String lastName, String email, String phoneNumber, String password,
			UserRole role, Address address) {
		super();
		this.login = login;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.password = password;
		this.role = role;
		this.address = address;
	}
	
	
	
	

	public User(Integer id, String login, String firstName, String lastName, String email, String phoneNumber,
			String plainPassword, String password, UserRole role, List<Event> myEvents, List<Event> myParticipation,
			List<TestLevel> levelTests, List<CourseReview> reviews, List<CourseParticipation> participations,
			List<Notification> lstNotif, List<AdAreaPurchaseRequest> purchaseRequests, Address address,
			boolean isBanned, boolean isConfirmed, String confirmationCode) {
		super();
		this.id = id;
		this.login = login;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.plainPassword = plainPassword;
		this.password = password;
		this.role = role;
		this.myEvents = myEvents;
		this.myParticipation = myParticipation;
		this.levelTests = levelTests;
		this.reviews = reviews;
		this.participations = participations;
		this.lstNotif = lstNotif;
		this.purchaseRequests = purchaseRequests;
		this.address = address;
		this.isBanned = isBanned;
		this.isConfirmed = isConfirmed;
		this.confirmationCode = confirmationCode;
	}

	public List<Event> getMyParticipation() {
		return myParticipation;
	}

	public void setMyParticipation(List<Event> myParticipation) {
		this.myParticipation = myParticipation;
	}

	public List<Event> getMyEvents() {
		return myEvents;
	}

	public void setMyEvents(List<Event> myEvents) {
		this.myEvents = myEvents;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
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
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public boolean isSuperAdmin() {
		return this.role == UserRole.ROLE_SUPER_ADMIN;
	}

	public void setBanned(boolean isBanned) {
		this.isBanned = isBanned;
	}

	public boolean isBanned() {
		return isBanned;
	}

	

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	

	public String getPlainPassword() {
		return plainPassword;
	}

	public void setPlainPassword(String plainPassword) {
		this.plainPassword = plainPassword;
	}

	public List<TestLevel> getLevelTests() {
		return levelTests;
	}

	public void setLevelTests(List<TestLevel> levelTests) {
		this.levelTests = levelTests;
	}

	public List<CourseReview> getReviews() {
		return reviews;
	}

	public void setReviews(List<CourseReview> reviews) {
		this.reviews = reviews;
	}

	public List<CourseParticipation> getParticipations() {
		return participations;
	}

	public void setParticipations(List<CourseParticipation> participations) {
		this.participations = participations;
	}

	public List<Notification> getLstNotif() {
		return lstNotif;
	}

	public void setLstNotif(List<Notification> lstNotif) {
		this.lstNotif = lstNotif;
	}

	public List<AdAreaPurchaseRequest> getPurchaseRequests() {
		return purchaseRequests;
	}

	public void setPurchaseRequests(List<AdAreaPurchaseRequest> purchaseRequests) {
		this.purchaseRequests = purchaseRequests;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", login=" + login + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", email=" + email + ", password=" + password + ", role=" + role + ", address=" + address + "]";
	}

	public boolean isConfirmed() {
		return isConfirmed;
	}
	
	public void setConfirmed(boolean isConfirmed) {
		this.isConfirmed = isConfirmed;
	}
	
	public String getConfirmationCode() {
		return confirmationCode;
	}
	
	
}
