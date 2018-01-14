package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;

import interfaces.KurseControllerInterface;
import model.KursListeTagModel;
import model.KursTerminModel;
import model.MeineTermineModel;
import model.TerminModel;
import model.User;

public class KurseController implements KurseControllerInterface{

	private List<KursListeTagModel> wochenListe;
	private KursTerminModel termin;
	
	private User user;
	
	private int currentDay;
	
	public KurseController() {
		wochenListe = new ArrayList<KursListeTagModel> ();	
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_WEEK,calendar.getFirstDayOfWeek());
		
		for(int i = 1; i<=7; i++) {
			String tag= calendar.getTime().toString();
			calendar.add(Calendar.DATE, 1);
			System.out.println(tag);
			KursListeTagModel kltm = new KursListeTagModel();
			kltm.setTag(tag);
			wochenListe.add(kltm);
		}
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
	      String sql="SELECT * FROM aktivitaet a INNER JOIN termin t ON a.id = t.aktivitaetid;";
	      
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
	        	 
	        	KursTerminModel terminModel = new KursTerminModel();
	        	terminModel.setId(rs.getInt("id"));
	        	terminModel.setName(rs.getString("name"));
	        	terminModel.setTrainer(rs.getString("trainer"));
	        	terminModel.setBeschreibung(rs.getString("beschreibung"));
	        	terminModel.setDatum(rs.getDate("datum").toString());
	        	terminModel.setStartUhrzeit(rs.getTime("startuhrzeit").toString());
	        	terminModel.setEndUhrzeit(rs.getTime("enduhrzeit").toString());
	        	
	        	Calendar calender = Calendar.getInstance();
	        	calender.setTime(rs.getDate("datum"));
	        	switch(calender.get(Calendar.DAY_OF_WEEK)) {
	        	case 1:
	        		wochenListe.get(6).add(terminModel);
	        		break;
	        	case 2:
	        		wochenListe.get(0).add(terminModel);
	        		break;
	        	case 3:
	        		wochenListe.get(1).add(terminModel);
	        		break;
	        	case 4:
	        		wochenListe.get(2).add(terminModel);
	        		break;
	        	case 5:
	        		wochenListe.get(3).add(terminModel);
	        		break;
	        	case 6:
	        		wochenListe.get(4).add(terminModel);
	        		break;
	        	case 7:
	        		wochenListe.get(5).add(terminModel);
	        		break;	
	        	}
	        	
	        	
	        	
	        	//termine.add(terminModel);
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
		
		
		
		
		return null;
	}

	@Override
	public String absagen() {
		// TODO Auto-generated method stub
		return null;
	}
	

	public List<KursListeTagModel> getWochenListe() {
		return wochenListe;
	}

	public void setWochenListe(List<KursListeTagModel> wochenListe) {
		this.wochenListe = wochenListe;
	}

	public KursTerminModel getTermin() {
		return termin;
	}

	public void setTermin(KursTerminModel termin) {
		this.termin = termin;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
