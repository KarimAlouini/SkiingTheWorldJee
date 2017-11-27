package tn.esprit.client;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import tn.codeinc.persistance.*;
import tn.codeinc.services.EstablishmentInterfaceLocal;
import tn.codeinc.services.OffersInterfaceLocal;

public class Client {
	public static void main(String[] args) throws NamingException, ParseException {
			String jndiName = "SkiWorld-ear/SkiWorld-ejb/EstablishmentService!tn.codeinc.services.EstablishmentInterfaceLocal";
			Context  context = new InitialContext();
			EstablishmentInterfaceLocal proxy = (EstablishmentInterfaceLocal) context.lookup(jndiName);
		    proxy.addEstablishment(new Establishment("slim", "1234", new Coordinates(13.0,1.0)));
		  //proxy.getEstablishment();

	//proxy.updateEvent(new Establishment(1,"TEST", "55555", new CoordinatesType(1,1)));
		 
        //proxy.findEstablishmentByName("slim");
        //proxy.delete(1);

			
		/////////////////////////
	//proxy.addChalet(new Chalet(1,1,1,1,1,"1"));
	//proxy.addResort(new Resort(1,1,1,1,"1"));
		
			////////////////////////////
	String jndiName1= "SkiWorld-ear/SkiWorld-ejb/OffersService!tn.codeinc.services.OffersInterfaceLocal";
	Context  context1 = new InitialContext();
	OffersInterfaceLocal proxy1 = (OffersInterfaceLocal) context1.lookup(jndiName1);
	//proxy1.addOffers(new Offer("f", "g", 1.52, 57));
		
	 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	 Date date1 = sdf.parse("2017-08-03");
     Date date2 = sdf.parse("2017-08-05");

     proxy1.addLodging(new Lodging("test", "test", 1.00, 20,"Suite",  date1, date2, "libre"));
     
   //proxy1.updateLodging(new Lodging(1,"f" ,date1, date2, "ss"));
   //proxy1.LodgingUpgrade(new Lodging(1,"libre", "test", 1.00, 40, date1, date2, "test"));

                  }	
}
