package controller;

import model.User;

public class LoginController {

	private User user;
	
    public String login(){
        // Image here a database access to validate the users
        if (user.getName().equalsIgnoreCase("tester") && user.getPassword().equalsIgnoreCase("tester")){
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
