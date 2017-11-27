
package tn.codeinc.services;

import java.util.List;

import javax.ejb.Remote;

import tn.codeinc.persistance.*;

@Remote
public interface EstablishmentInterfaceLocal  {
	
	public void addEstablishment (Establishment e);
	public List<Establishment> getEstablishment() ;
	public void delete(int id)  ;
	public String updateEstablishment(Establishment e) ;
	public List<Establishment> findEstablishmentByName(String n);
	public Establishment findEstablishmentById(int id);
	public String seeDetailsAboutEstablishment(int id);
	
	////////////////////////////////
	public void addChalet (Chalet c);
	public List<Chalet> getChal();

	///////////////////////////////////
	public void addResort (Resort r);
	public List<Resort> getResort();
	
}
