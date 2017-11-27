package tn.codeinc.services;

import java.util.List;

import javax.ejb.Remote;

import tn.codeinc.persistance.*;

@Remote
public interface EstablishmentInterfaceRemote  {
	

	public List<Establishment> getEstablishment() ;
	public List<Establishment> findEstablishmentByName(String n);
	public Establishment findEstablishmentById(int id);
	public String seeDetailsAboutEstablishment(int id);
	
	////////////////////////////////

	public List<Chalet> getChal();

	///////////////////////////////////
	
	public List<Resort> getResort();
	
}
