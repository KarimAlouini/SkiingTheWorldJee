
package tn.codeinc.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.codeinc.persistance.*;

@Stateless
public class OffersService implements OffersInterfaceLocal, OffersInterfaceRemote{

	@PersistenceContext(name="SkiingTheWorld-ejb")
	EntityManager em;
	
	@Override
	public void addOffers(Offer o) {
		
		em.persist(o);	
	}

	public Offer findOffersById(int id){
		
		return em.find(Offer.class, id);
	}
	

	@Override
	public void deleteOffers(int id) {
		
		em.remove(em.merge(em.find(Offer.class, id)));		
	}
	
	@Override
	public String updateOffers(Offer o) {
		
		em.merge(o);
		return "Offers updated with success";
	}
	
	
	@Override
	public List<Offer> getOffers() {	
		
		return em.createQuery("SELECT e FROM Offer e ",Offer.class).getResultList();
		
	}

	
	@Override
	public String seeDetailsAboutOffers(int id) {
		return em.find(Offer.class,id).getLabel();
	}
	
	
	//////////////////////////////////////////
	
	@Override
	public void addLodging(Lodging lodging) throws ParseException {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    Date date1 = sdf.parse("2017-06-01");
	    Date date2 = sdf.parse("2017-09-01");
	        
		if (lodging.getStartDate().compareTo(lodging.getEndDate()) < 0) {
		if (((lodging.getStartDate().compareTo(date1) < 0)  || (lodging.getStartDate().compareTo(date2) > 0)) && 
		   ((lodging.getEndDate().compareTo(date1) < 0)  || (lodging.getEndDate().compareTo(date2) > 0))) {  
		if(lodging.getEtat().equals("libre")){  
	    if (lodging.getTyp().equals("Room")){
	    	int diffInDays = (int)( (lodging.getEndDate().getTime() - lodging.getStartDate().getTime()) 
	                 / (1000 * 60 * 60 * 24) );
	    Double p=(lodging.getPrice()*lodging.getQuantity()*1.25*diffInDays);
		lodging.setPrice(p);
		em.persist(lodging);
		} 
	    else if (lodging.getTyp().equals("Suite")) {
	    	int diffInDays = (int)( (lodging.getEndDate().getTime() - lodging.getStartDate().getTime()) 
	                 / (1000 * 60 * 60 * 24) );
			Double p=(lodging.getPrice()*lodging.getQuantity()*1.25*2*diffInDays);
			lodging.setPrice(p);
			em.persist(lodging);
			}
		}
		else
			lodging.setEtat("non libre");
		em.persist(lodging);
	
		}

			 else if  (((lodging.getStartDate().compareTo(date1) > 0) && (lodging.getEndDate().compareTo(date2) < 0)) 
						|| ((lodging.getStartDate().compareTo(date1) == 0) && (lodging.getEndDate().compareTo(date2) < 0))
						 || ((lodging.getStartDate().compareTo(date1) == 0)&&(lodging.getEndDate().compareTo(date2) == 0))
						 || ((lodging.getStartDate().compareTo(date1) > 0) && (lodging.getEndDate().compareTo(date2) == 0) )) {
				   if (lodging.getTyp().equals("Room")){
					   int diffInDays = (int)( (lodging.getEndDate().getTime() - lodging.getStartDate().getTime()) 
				                 / (1000 * 60 * 60 * 24) );
						Double p=(lodging.getPrice()*lodging.getQuantity()*1.5*diffInDays);
						lodging.setPrice(p);
						em.persist(lodging);
					} 
				    else if (lodging.getTyp().equals("Suite")) {
				    	int diffInDays = (int)( (lodging.getEndDate().getTime() - lodging.getStartDate().getTime()) 
				                 / (1000 * 60 * 60 * 24) );
				    	Double p=(lodging.getPrice()*lodging.getQuantity()*1.5*2*diffInDays);
						lodging.setPrice(p);
						em.persist(lodging);
					}
				
		    }
			 else if  ((lodging.getStartDate().compareTo(date1) < 0) && (date1.compareTo(lodging.getEndDate()) < 0) && (lodging.getEndDate().compareTo(date2) < 0))
			 {
				   if (lodging.getTyp().equals("Room"))
				   
				   {
					   int diffInDays1 = (int)( (lodging.getEndDate().getTime() - date1.getTime()) 
				                 / (1000 * 60 * 60 * 24) );
					   int diffInDays2 = (int)( (date1.getTime() - lodging.getStartDate().getTime()) 
				                 / (1000 * 60 * 60 * 24) );
						Double p=(lodging.getPrice()*lodging.getQuantity()*1.25*diffInDays2)+(lodging.getPrice()*lodging.getQuantity()*1.5*diffInDays1);
						lodging.setPrice(p);
						em.persist(lodging);
					}
				   
				   else if (lodging.getTyp().equals("Suite")) 
					   
				   {
					   int diffInDays1 = (int)( (lodging.getEndDate().getTime() - date1.getTime()) 
				                 / (1000 * 60 * 60 * 24) );
					   int diffInDays2 = (int)( (date1.getTime() - lodging.getStartDate().getTime()) 
				                 / (1000 * 60 * 60 * 24) );
						Double p=(lodging.getPrice()*lodging.getQuantity()*1.25*diffInDays2*2)+(lodging.getPrice()*lodging.getQuantity()*1.5*diffInDays1*2);
				    
						lodging.setPrice(p);
						em.persist(lodging);
					}	
		    }
			 else if  ((date1.compareTo(lodging.getStartDate()) < 0) && (lodging.getStartDate().compareTo(date2) < 0) && (date2.compareTo(lodging.getEndDate()) < 0))
			 {
				   if (lodging.getTyp().equals("Room"))
				   
				   {
					   int diffInDays1 = (int)( (lodging.getEndDate().getTime() - date2.getTime()) 
				                 / (1000 * 60 * 60 * 24) );
					   int diffInDays2 = (int)( (date2.getTime() - lodging.getStartDate().getTime()) 
				                 / (1000 * 60 * 60 * 24) );
						Double p=(lodging.getPrice()*lodging.getQuantity()*1.25*diffInDays1)+(lodging.getPrice()*lodging.getQuantity()*1.5*diffInDays2);
						lodging.setPrice(p);
						em.persist(lodging);
					}
				   
				   else if (lodging.getTyp().equals("Suite")) 
					   
				   {
					   int diffInDays1 = (int)( (lodging.getEndDate().getTime() - date2.getTime()) 
				                 / (1000 * 60 * 60 * 24) );
					   int diffInDays2 = (int)( (date2.getTime() - lodging.getStartDate().getTime()) 
				                 / (1000 * 60 * 60 * 24) );
						Double p=(lodging.getPrice()*lodging.getQuantity()*1.25*diffInDays1*2)+(lodging.getPrice()*lodging.getQuantity()*1.5*diffInDays2*2);
				    
						lodging.setPrice(p);
						em.persist(lodging);
					}	
		    }
		
		} 

		 else if (lodging.getStartDate().compareTo(lodging.getEndDate()) > 0) {
  
		System.out.println("Date start is after Date end");
    } 
		 
	 else if (lodging.getStartDate().compareTo(lodging.getEndDate())  == 0) {
       
		System.out.println("Date start is equal to Date end");
		
    } else {
        System.out.println("error");
     }

          }



	
	@Override
	public List<Lodging> getLodging() {
	
		return em.createQuery("SELECT e FROM Lodging e ",Lodging.class).getResultList();
	}
	
	
	@Override
	public String updateLodging(Lodging l) {
		
		em.merge(l);
		return "Lodging updated with success";
	}
	

	public Lodging findLodgingById(int id){
		
		return em.find(Lodging.class, id);
	}
	


}