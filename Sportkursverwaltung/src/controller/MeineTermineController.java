package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import interfaces.MeineTermineControllerInterace;
import model.TerminAnzeigeModel;
import model.TerminModel;
import model.User;

public class MeineTermineController implements MeineTermineControllerInterace {

	private List<TerminAnzeigeModel> termine;
	private TerminAnzeigeModel termin;
	
	private User user;
	
	public MeineTermineController() {
		termine = new ArrayList<TerminAnzeigeModel> ();
		ladeTermine();
	}
	
	public void ladeTermine() {
		
	      Connection c = null;
	      Statement stmt = null;
	      String sql="SELECT * FROM aktivitaet INNER JOIN termin ON aktivitaet.id=termin.aktivitaetid;";
	      
	      try {
	         Class.forName("org.postgresql.Driver");
	         c = DriverManager
	            .getConnection("jdbc:postgresql://localhost:5432/Terminverwaltung",
	            "postgres", "postgres");
	         
	         c.setAutoCommit(false);
	         System.out.println("Opened database successfully");
	        
	         stmt = c.createStatement();
	         ResultSet rs = stmt.executeQuery(sql);
	         while(rs.next()) {
	        	TerminAnzeigeModel terminModel = new TerminAnzeigeModel();
	        	
	        	terminModel.setName(rs.getString("name"));
	        	terminModel.setTrainer(rs.getString("trainer"));
	        	terminModel.setBeschreibung(rs.getString("beschreibung"));
	        	terminModel.setDatum(rs.getDate("datum").toString());
	        	terminModel.setStartUhrzeit(rs.getTime("startuhrzeit").toString());
	        	terminModel.setEndUhrzeit(rs.getTime("enduhrzeit").toString());
	        	terminModel.setIstWoechentlich(rs.getBoolean("istwoechentlich"));
	        	terminModel.setBuchbarAb(rs.getInt("buchbarab"));
	        	terminModel.setBuchbarBis(rs.getInt("buchbarbis"));
	        	terminModel.setStornierbarBis(rs.getInt("stornierbarbis"));
	        	
	        	
	        	termine.add(terminModel);
	         }
	         rs.close();
	         stmt.close();
	         c.close();
	         
	      } catch (Exception e) {
	         e.printStackTrace();
	         System.err.println(e.getClass().getName()+": "+e.getMessage());
	         System.exit(0);
	      }
	     
	      System.out.println("Operation done successfully");
	}
	
	public String absagen() {
		termine.remove(termin);
								//Termin aus Datenbank l�schen
		return null;
	}
	
	
	// Setter und Getter
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<TerminAnzeigeModel> getTermine() {
		return termine;
	}
	public void setTermine(List<TerminAnzeigeModel> termine) {
		this.termine = termine;
	}
	public TerminAnzeigeModel getTermin() {
		return termin;
	}
	public void setTermin(TerminAnzeigeModel termin) {
		this.termin = termin;
	}
}
