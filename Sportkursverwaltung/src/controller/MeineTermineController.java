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
	
	@Override
	public void ladeTermine() {
		System.out.println("MeineTermineController.ladeTermine ()");
		
		Connection c = null;
	    PreparedStatement pstmt = null;
		String sql="SELECT t.id, t.datum, t.enduhrzeit, t.startuhrzeit, t.iststornierbar, "
				+ "a.name, a.beschreibung, a.trainer "
				+ "FROM terminliste tl INNER JOIN termin t ON "
				+ "tl.terminid = t.id INNER JOIN aktivitaet a "
				+ "ON t.aktivitaetid = a.id WHERE tl.mitgliedid = ?;";
		try {
			Class.forName("org.postgresql.Driver");
	        c = DriverManager.getConnection("jdbc:postgresql://" + dbPfad ,dbUser, dbPasswort);
	        c.setAutoCommit(true);
    
	        pstmt = c.prepareStatement(sql);
	        pstmt.setInt(1, user.getId());
	        ResultSet rs = pstmt.executeQuery();
	        while(rs.next()) {
	        	MeineTermineModel terminModel = new MeineTermineModel();
	        	
	        	terminModel.setId(rs.getInt("id"));
	        	terminModel.setName(rs.getString("name"));
	        	terminModel.setTrainer(rs.getString("trainer"));
	        	terminModel.setBeschreibung(rs.getString("beschreibung"));
	        	terminModel.setDatum(rs.getString("datum"));
	        	terminModel.setStartUhrzeit(rs.getString("startuhrzeit"));
	        	terminModel.setEndUhrzeit(rs.getString("enduhrzeit"));	  
	        	terminModel.setStornierbar(rs.getBoolean("iststornierbar"));
	      
	        	if(terminModel.isStornierbar()) {
	        		terminModel.setGesperrt(false);
	        		terminModel.setActionName("Absagen");
	        	} else {
	        		terminModel.setActionName("NichtStornierbar");
	        		terminModel.setGesperrt(true);
	        	}
	        	
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
	     
	   
	}
	@Override
	public String absagen(MeineTermineModel termin) {
		System.out.println("MeineTermineController.absagen ()"+termin.getId());
		
		Connection c = null;
	    PreparedStatement pstmt = null;
	    String delete = "DELETE FROM terminliste WHERE terminid = ? AND mitgliedid=?";
	    String check = "SELECT iststornierbar FROM termin WHERE id=?;";
	      
	    try {
	    	Class.forName("org.postgresql.Driver");
	        c = DriverManager.getConnection("jdbc:postgresql://" + dbPfad, dbUser, dbPasswort);
	         
	        c.setAutoCommit(true);
	        
	        pstmt = c.prepareStatement(check);
	        pstmt.setInt(1, termin.getId());
	        ResultSet rs = pstmt.executeQuery();
	        
	        if(rs.next()) {
	        	if(rs.getBoolean("iststornierbar")) {
	        		System.out.println("UserID:"+user.getId()+" /TerminID: "+termin.getId());
	    	        pstmt = c.prepareStatement(delete);
	    	        pstmt.setInt(1, termin.getId());
	    	        pstmt.setInt(2, user.getId());
	    	      
	    	        pstmt.executeUpdate();
	        	} else {
	        		termin.setActionName("Nicht Stornierbar");
	        		termin.setGesperrt(true);
	        	}
	        }

	        pstmt.close();
	        c.close();
	         
	      } catch (Exception e) {
	        e.printStackTrace();
	        System.err.println(e.getClass().getName()+": "+e.getMessage());
	        System.exit(0);
	      }
	    termine.remove(termin);
		return null;
	}

	//------------------------------------------------------------------
	//------------GETTER UND SETTER-------------------------------------
	//------------------------------------------------------------------
	
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
