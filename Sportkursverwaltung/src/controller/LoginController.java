package controller;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import interfaces.LoginControllerInterface;
import entity.CustomerService;
import model.User;
import entity.Mitglied;

public class LoginController implements LoginControllerInterface {

	private User user;
	
	@Override
    public String login(){
        // Image here a database access to validate the users
    	System.out.println("login");
    	
    	System.out.println("Email: "+user.getName());
    	System.out.println("Password: "+user.getPassword());
    	
    	EntityManagerFactory factory =
    			Persistence.createEntityManagerFactory("Sportkursverwaltung");
    			EntityManager manager = factory.createEntityManager();
    			//CustomerService service = new CustomerService(manager);
    			// manager.getTransaction().begin();
    			// Customer emp = service.createCustomer(3l,"testmail","Jan","vroelsker","passwort123");
    			// em.getTransaction().commit();
    			
    			// Daten auslesen und anzeigen
    			Query query = manager.createNativeQuery("SELECT * FROM customer  WHERE email='"+user.getName()+"' AND passwort='"+user.getPassword()+"';");
    			//Query query = manager.createNativeQuery("SELECT * FROM customer;");
    			List<Mitglied> liste = query.getResultList();
    			System.out.println("Size: "+liste.size());
    			// Die Liste braucht bei REST gar nicht weiter aufgelöst zu werden!
    		
    			//factory.close();
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

	@Override
	public String logout() {
		// TODO Auto-generated method stub
		user = null;
		return "index";
	}
	
    
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


}
