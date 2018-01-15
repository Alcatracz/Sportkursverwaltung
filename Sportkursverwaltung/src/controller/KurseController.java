package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
	private KursTerminModel termininfo;
	
	public KursTerminModel getTermininfo() {
		return termininfo;
	}

	public void setTermininfo(KursTerminModel termininfo) {
		this.termininfo = termininfo;
	}

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
	      String sql="SELECT a.name, a.trainer, a.beschreibung, a.teilnehmer, t.datum, t.startuhrzeit, t.enduhrzeit, t.id"
	      		+ " FROM aktivitaet a INNER JOIN termin t ON a.id = t.aktivitaetid;";
	      
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
	        	 int maxTeilnehmer = rs.getInt("teilnehmer");
	        	 int terminID= rs.getInt("id");
	        	 PreparedStatement ps = c.prepareStatement("SELECT count(id) from terminliste WHERE terminid=?;");
	        		 ps.setInt(1, terminID);
	        		 ResultSet rsCount = ps.executeQuery();
	        		 rsCount.next();
	        		 int currTeilnehmmer= rsCount.getInt(1);
	        		 ps.close();
	        		 rsCount.close();
	        		
	        		 PreparedStatement pshero = c.prepareStatement("SELECT id from terminliste WHERE terminid=? AND mitgliedid=?");
	        		 pshero.setInt(1, terminID);
	        		 pshero.setInt(2, user.getId());
	        		 ResultSet rshero = pshero.executeQuery();
	        		 int terminmitgliedid=0;
	        		 while(rshero.next()) {
	        			 terminmitgliedid=rshero.getInt("id");
	        		 }
	        		 pshero.close();
	        		 rshero.close();
	        		 
	        	 //Check ob freier platz
	        	 //Check ob angemeldet
	        	KursTerminModel terminModel = new KursTerminModel();
	        	terminModel.setTerminId(rs.getInt("id"));
	        	terminModel.setName(rs.getString("name"));
	        	terminModel.setTrainer(rs.getString("trainer"));
	        	terminModel.setBeschreibung(rs.getString("beschreibung"));
	        	terminModel.setDatum(rs.getDate("datum").toString());
	        	terminModel.setStartUhrzeit(rs.getTime("startuhrzeit").toString());
	        	terminModel.setEndUhrzeit(rs.getTime("enduhrzeit").toString());
	        	
	        	if(maxTeilnehmer-currTeilnehmmer>0) {
	        		terminModel.setActionName("Teilnehmen");
	        		terminModel.setIstBuchbar(true);
	        	}
	        	if(terminmitgliedid != 0) {
	        		terminModel.setActionName("Absagen");
	        		terminModel.setBereitsgebucht(true);
	        	}
	        	
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
			System.out.println("end");
	}
	@Override
	public String toggleButton(KursTerminModel termin) {
		System.out.println("toggleButton");
		//Check if bereits gebucht
		if(termin.isBereitsgebucht()) {
			System.out.println("");
			absagen(termin);
		} else if(termin.isIstBuchbar()) {
			teilnehmen(termin);
		}
		
		return null;
	}
	
	@Override
	public void teilnehmen(KursTerminModel termin) {
		
		Connection c = null;
	      PreparedStatement pstmt = null;
	      String sql = "INSERT INTO terminliste(mitgliedid,terminid) VALUES (?,?);";
	      
	      try {
	         Class.forName("org.postgresql.Driver");
	         c = DriverManager
	            .getConnection("jdbc:postgresql://localhost:5432/Terminverwaltung",
	            "postgres", "postgres");
	         
	         c.setAutoCommit(true);
	         System.out.println("Opened database successfully");
	        
	         pstmt = c.prepareStatement(sql);
	         
	         pstmt.setInt(1, user.getId());
	         pstmt.setInt(2, termin.getTerminId());
	         
	         pstmt.executeUpdate();
	         pstmt.close();
	         c.close();
	         
	      } catch (Exception e) {
	         e.printStackTrace();
	         System.err.println(e.getClass().getName()+": "+e.getMessage());
	         System.exit(0);
	      }
	     
	      System.out.println("Operation done successfully");
		
		termin.setBereitsgebucht(true);
		termin.setActionName("Absagen");
	}

	@Override
	public void absagen(KursTerminModel termin) {
		// TODO Auto-generated method stub
		 Connection c = null;
	      PreparedStatement pstmt = null;
	      String sql = "DELETE FROM terminliste WHERE id = ? AND mitgliedid=?";
	      
	      try {
	         Class.forName("org.postgresql.Driver");
	         c = DriverManager
	            .getConnection("jdbc:postgresql://localhost:5432/Terminverwaltung",
	            "postgres", "postgres");
	         
	         c.setAutoCommit(true);
	         System.out.println("Opened database successfully");
	        
	         pstmt = c.prepareStatement(sql);
	         pstmt.setInt(1, termin.getTerminMitgliedId());
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
	      
		
		
		termin.setBereitsgebucht(false);
		if(termin.isIstBuchbar()) {
			termin.setActionName("Teilnehmen");
		} else {
			termin.setActionName("Voll");
		}
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
