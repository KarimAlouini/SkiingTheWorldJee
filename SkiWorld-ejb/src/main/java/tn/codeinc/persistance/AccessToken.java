package tn.codeinc.persistance;

import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.joda.time.Interval;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

@Entity
@Table(name = "access_tokens")
public class AccessToken {
	@Override
	public String toString() {
		return "AccessToken [id=" + id + ", expiresAt=" + expiresAt + ", user=" + user + ", value=" + value + "]";
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@JsonFormat(shape=Shape.STRING,pattern="yyyy-MM-dd hh:mm")
	private Date expiresAt;
	@ManyToOne(fetch = FetchType.EAGER,cascade=CascadeType.REMOVE)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	private String value;

	public AccessToken(User user, String value) {
		super();
		this.user = user;
		this.value = value;

	}

	public AccessToken() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getExpiresAt() {
		return expiresAt;
	}

	public void setExpiresAt(Date expiresAt) {
		this.expiresAt = expiresAt;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public boolean isValid() {
		
		return (this.expiresAt.compareTo(new Date()) >= 0);
	}

	public void generate() {
		this.value = Base64.getEncoder().encodeToString(String.valueOf(new Random().nextDouble()).getBytes());
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.HOUR_OF_DAY, 3);
		this.expiresAt = c.getTime();
	}

}
