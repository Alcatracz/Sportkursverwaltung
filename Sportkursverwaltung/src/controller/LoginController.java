package controller;

import model.User;
import ejb.CustomerEJB;
import entitys.Customer;

public class LoginController {

	private User user;
	CustomerEJB cejb;
	
    public String login(){
        // Image here a database access to validate the users
    	System.out.println("login");
        if (cejb.isValidUser(user.getName(), user.getPassword())){
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
