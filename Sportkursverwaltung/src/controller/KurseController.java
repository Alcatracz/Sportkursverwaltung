package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import interfaces.KurseControllerInterface;
import model.MeineTermineModel;
import model.TerminModel;
import model.User;

public class KurseController implements KurseControllerInterface{

	private List<MeineTermineModel> termine;
	private MeineTermineModel termin;
	
	private User user;
	
	public KurseController() {
		termine = new ArrayList<MeineTermineModel> ();		
	}
	
	@PostConstruct
	public void init() {
		ladeTermine();
	}
	
	@Override
	public void ladeTermine() {
		// TODO Auto-generated method stub
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
	        	MeineTermineModel terminModel = new MeineTermineModel();
	        	
	        	terminModel.setName(rs.getString("name"));
	        	terminModel.setTrainer(rs.getString("trainer"));
	        	terminModel.setBeschreibung(rs.getString("beschreibung"));
	        	terminModel.setDatum(rs.getDate("datum").toString());
	        	terminModel.setStartUhrzeit(rs.getTime("startuhrzeit").toString());
	        	terminModel.setEndUhrzeit(rs.getTime("enduhrzeit").toString());

	        	
	        	
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

	@Override
	public String teilnehmen() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String absagen() {
		// TODO Auto-generated method stub
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
