package App;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class User {

	private String userName;
	private Integer userID;
	private boolean isAuthenticated;
	
	public User() {
		this.userName = null;
		this.userID = null;
		this.isAuthenticated = false;
	}
	
	public static User getFromSession(HttpSession session) {
		User user = new User();
		
		user =  (User) session.getAttribute("user");
		
		return user;
	}
	
	public String getUserName() {
		return this.userName;
	}
	
	public Integer getUserID() {
		return this.userID;
	}
	
	public boolean istAuthenticated() {
		return this.isAuthenticated;
	}
	
	public void logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		if(session != null) {
		//	session = new HttpSession();
		}
		
		session.removeAttribute("user");
		this.userName = null;
		this.userID = null;
		this.isAuthenticated = false;
	}
}
