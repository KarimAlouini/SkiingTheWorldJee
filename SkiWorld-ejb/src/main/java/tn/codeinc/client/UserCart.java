package tn.codeinc.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;

import tn.codeinc.persistance.Course;

@Stateful
@SessionScoped
public class UserCart implements UserCartLocal,UserCartRemote{
	List<Course> courses;
	private int number;
	 
	
	@Override
	public int getNumber() {
		return number;
	}
	@Override
	public void setNumber() {
		this.number = new Random().nextInt();
		
	}
	
	@PostConstruct
	void init(){
		System.out.println("UserCart.init()");
	}
	

}
