package tn.codeinc.client;

import javax.ejb.Local;

@Local
public interface UserCartLocal {
	public int getNumber();
	public void setNumber();

}
