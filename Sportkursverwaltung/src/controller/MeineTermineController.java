package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.component.UIForm;

import interfaces.MeineTermineControllerInterace;
import model.MeineTermineModel;
import model.User;

public class MeineTermineController implements MeineTermineControllerInterace {

	private String dbPfad = "localhost:5432/Terminverwaltung";
	private String dbUser = "postgres";
	private String dbPasswort = "postgres";
	
	private List<MeineTermineModel> termine;
	private MeineTermineModel termin;

	private User user;
	
	public MeineTermineController() {
		System.out.println("MeineTermineController ()");
		termine = new ArrayList<MeineTermineModel> ();
	}
	
	@PostConstruct
	public void init() {
		System.out.println("MeineTermineController.init ()");
		ladeTermine();
	}
	
	public void ladeTermine() {
		System.out.println("MeineTermineController.ladeTermine ()");
		
		//System.out.println("UserID:"+user.getId());
	      Connection c = null;
	      PreparedStatement pstmt = null;
			String sql="SELECT tl.id, t.datum, t.enduhrzeit, t.startuhrzeit, a.name, a.beschreibung, a.trainer FROM terminliste tl INNER JOIN termin t ON "
					+ "tl.terminid = t.id INNER JOIN aktivitaet a ON t.aktivitaetid = a.id WHERE tl.mitgliedid = ?;";
	      
	      try {
	         Class.forName("org.postgresql.Driver");
	         c = DriverManager
	            .getConnection("jdbc:postgresql://localhost:5432/Terminverwaltung",
	            		dbUser, dbPasswort);
	         
	         c.setAutoCommit(true);
	         System.out.println("Opened database successfully");
	        
	         pstmt = c.prepareStatement(sql);
	         pstmt.setInt(1, user.getId());
	         ResultSet rs = pstmt.executeQuery();
	         while(rs.next()) {
	        	MeineTermineModel terminModel = new MeineTermineModel();
	        	
	        	terminModel.setId(rs.getInt("id"));
	        	terminModel.setName(rs.getString("name"));
	        	terminModel.setTrainer(rs.getString("trainer"));
	        	terminModel.setBeschreibung(rs.getString("beschreibung"));
	        	terminModel.setDatum(rs.getDate("datum").toString());
	        	terminModel.setStartUhrzeit(rs.getTime("startuhrzeit").toString());
	        	terminModel.setEndUhrzeit(rs.getTime("enduhrzeit").toString());	        	
	        	
	        	termine.add(terminModel);
	         }
	         rs.close();
	         pstmt.close();
	         c.close();
	         
	      } catch (Exception e) {
	         e.printStackTrace();
	         System.err.println(e.getClass().getName()+": "+e.getMessage());
	         System.exit(0);
	      }
	     
	      System.out.println("Operation done successfully");
	}
	
	public String absagen() {
		System.out.println("MeineTermineController.absagen ()");
		
		int id = termin.getId();
		System.out.println("TERMIN_ID: " + id);
		System.out.println("MITGLIED_ID: " + user.getId());
		System.out.println("Rofl Absage");
		termine.remove(termin);
		
		
	      Connection c = null;
	      PreparedStatement pstmt = null;
	      String sql = "DELETE FROM terminliste WHERE id = ? AND mitgliedid=?";
	      
	      try {
	         Class.forName("org.postgresql.Driver");
	         c = DriverManager
	            .getConnection("jdbc:postgresql://localhost:5432/Terminverwaltung",
	            		dbUser, dbPasswort);
	         
	         c.setAutoCommit(true);
	         System.out.println("Opened database successfully");
	        
	         pstmt = c.prepareStatement(sql);
	         pstmt.setInt(1, id);
	         pstmt.setInt(2, user.getId());
	      
	         pstmt.executeUpdate();
	         pstmt.close();
	         c.close();
	         
	      } catch (Exception e) {
	         e.printStackTrace();
	         System.err.println(e.getClass().getName()+": "+e.getMessage());
	         System.exit(0);
	      }
	     
	      System.out.println("Operation done successfully");
		
		//Termin aus Datenbank l�schen
		return null;
	}

	
	
	// Setter und Getter
	public List<MeineTermineModel> getTermine() {
		return termine;
	}

	public void setTermine(List<MeineTermineModel> termine) {
		this.termine = termine;
	}

	public MeineTermineModel getTermin() {
		return termin;
	}

	public void setTermin(MeineTermineModel termin) {
		this.termin = termin;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
