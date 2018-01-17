package controller;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import interfaces.LoginControllerInterface;
import model.MeineTermineModel;
import model.User;
import entity.Mitglied;

public class LoginController implements LoginControllerInterface {

	private String dbPfad = "localhost:5432/Terminverwaltung";
	private String dbUser = "postgres";
	private String dbPasswort = "postgres";
	
	private User user;

	public LoginController() {
		System.out.println("LoginController ()");
	}
	
	@PostConstruct
	public void init() {
		System.out.println("LoginController.init ()");
	}
	
	@Override
    public String login(){
		System.out.println("LoginController.login ()");

		Connection c = null;
	    PreparedStatement pstmt = null;
		String sql="SELECT m.id, m.email, m.passwort, m.isttrainer FROM mitglied m WHERE m.email=? AND m.passwort=?";
	      
	    try {
	    	Class.forName("org.postgresql.Driver");
	        c = DriverManager.getConnection("jdbc:postgresql://" + dbPfad, dbUser, dbPasswort);
	        c.setAutoCommit(true);
    
	        pstmt = c.prepareStatement(sql);
	        pstmt.setString(1, user.getEmail());
	        pstmt.setString(2, user.getPasswort());
	         
	        ResultSet rs = pstmt.executeQuery();
	        while(rs.next()) {
	        	user.setId(rs.getInt("id"));
	        	user.setEmail(rs.getString("email"));
	        	user.setPasswort(rs.getString("passwort"));
	        	user.setIstTrainer(rs.getBoolean("isttrainer"));
	        	user.setIstAuthentifiziert(true);
	         }
	        
	         rs.close();
	         pstmt.close();
	         c.close();
	         
	      } catch (Exception e) {
	         e.printStackTrace();
	         System.err.println(e.getClass().getName()+": "+e.getMessage());
	         System.exit(0);
	      } 
    			
        if (user.isIstAuthentifiziert() && !user.isIstTrainer()){
            return "kurse";
        } else if(user.isIstAuthentifiziert() && user.isIstTrainer()){
        	return "trainer";
        }
         else{
            return "index";
        }

    }

	@Override
	public String logout() {
		System.out.println("LoginController.logout ()");
		user.logout();
		return "index";
	}
	
	
	//------------------------------------------------------------------
	//------------GETTER UND SETTER-------------------------------------
	//------------------------------------------------------------------
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
