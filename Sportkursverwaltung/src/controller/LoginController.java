package controller;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import entity.Customer;
import model.User;

public class LoginController {

	private User user;
	
	
    public String login(){
        // Image here a database access to validate the users
    	System.out.println("login");
    	
    	System.out.println("Email: "+user.getName());
    	System.out.println("Password: "+user.getPassword());
    	
    	EntityManagerFactory factory =
    			Persistence.createEntityManagerFactory("Sportkursverwaltung");
    			EntityManager manager = factory.createEntityManager();
    			// Daten auslesen und anzeigen
    			Query query = manager.createNativeQuery("SELECT * FROM customer  WHERE email='"+user.getName()+"' AND passwort='"+user.getPassword()+"';");
    			//Query query = manager.createNativeQuery("SELECT * FROM customer;");
    			List<Customer> liste = query.getResultList();
    			System.out.println("Size: "+liste.size());
    			// Die Liste braucht bei REST gar nicht weiter aufgelöst zu werden!
    		
    			manager.close();
    			
        if (liste.size()>0){
    	//if (user.getName().equalsIgnoreCase("tester") && user.getPassword().equalsIgnoreCase("tester")){
            return "customer";
        } else if(user.getName().equalsIgnoreCase("trainer") && user.getPassword().equalsIgnoreCase("trainer")){
        	return "trainer";
        }
         else{
            return "index";
        }

    }

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
