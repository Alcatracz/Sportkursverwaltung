package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

	private String dbPfad = "localhost:5432/Terminverwaltung";
	private String dbUser = "postgres";
	private String dbPasswort = "postgres";
	
	private List<KursListeTagModel> wochenListe;
	private KursTerminModel termin;

	private User user;
	
	public KurseController() {
		System.out.println("KurseController ()");
		
		wochenListe = new ArrayList<KursListeTagModel> ();	
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_WEEK,calendar.getFirstDayOfWeek());
		DateFormat df = new SimpleDateFormat("E, dd. MMM yyyy");
		
		for(int i = 1; i<=7; i++) {
			String tag= df.format(calendar.getTime());
			calendar.add(Calendar.DATE, 1);
			//System.out.println(tag);
			KursListeTagModel kltm = new KursListeTagModel();
			kltm.setTag(tag);
			wochenListe.add(kltm);
		}
	}
	
	@PostConstruct
	public void init() {
		System.out.println("KurseController.init ()");
		
		ladeTermine();
	}
	
	@Override
	public void ladeTermine() {
		System.out.println("KurseController.ladeTermine ()");
		
		Connection c = null;
	    PreparedStatement pstmt = null;
	    String sql="SELECT a.name, a.trainer, a.beschreibung, a.teilnehmer, "
	    		+ "t.id, t.datum, t.startuhrzeit, t.enduhrzeit,"
	      		+ "t.buchbarab, t.buchbarbis, t.stornierbarbis, "
	      		+ "t.istbuchbar, t.iststornierbar, t.dauer, t.istwoechentlich"
	      		+ " FROM aktivitaet a INNER JOIN termin t ON a.id = t.aktivitaetid;";
	      
	    try {
	    	Class.forName("org.postgresql.Driver");
	        c = DriverManager.getConnection("jdbc:postgresql://" + dbPfad, dbUser, dbPasswort);       
	        c.setAutoCommit(false);
	
	        pstmt = c.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();
	        while(rs.next()) {
	        	
	        	KursTerminModel terminModel = new KursTerminModel();
	        	terminModel.setId(rs.getInt("id"));
	        	terminModel.setName(rs.getString("name"));
	        	terminModel.setTrainer(rs.getString("trainer"));
	        	terminModel.setBeschreibung(rs.getString("beschreibung"));
	        	terminModel.setDatum(rs.getString("datum"));
	        	terminModel.setStartUhrzeit(rs.getString("startuhrzeit"));
	        	terminModel.setEndUhrzeit(rs.getString("enduhrzeit"));
	        	terminModel.setStornierbarBis(rs.getInt("stornierbarbis"));
	        	terminModel.setBuchbarAb(rs.getInt("buchbarab"));
	        	terminModel.setBuchbarBis(rs.getInt("buchbarbis"));
	        	terminModel.setIstBuchbar(rs.getBoolean("istbuchbar"));
	        	terminModel.setIstStornierbar(rs.getBoolean("iststornierbar"));
	        	terminModel.setDauer(rs.getInt("dauer"));
	        	terminModel.setIstWoechentlich(rs.getBoolean("istwoechentlich"));
	        	
	        	int maxTeilnehmer = rs.getInt("teilnehmer");
	        	int terminID= terminModel.getId();
	        	
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
	        	
	        	if(!terminModel.isIstStornierbar()) {
	        		terminModel.setActionName("Nicht stornierbar");
	 	        	terminModel.setGesperrt(true);
	 	        }else if(terminmitgliedid != 0) {
	    	        terminModel.setActionName("Absagen");
	    	        terminModel.setGesperrt(false);
	    	    }else if(!terminModel.isIstBuchbar()) {
	    	        terminModel.setActionName("Nicht buchbar");
	    	        terminModel.setGesperrt(true);
	    	    }else if(terminModel.isIstBuchbar() && maxTeilnehmer-currTeilnehmmer>0) {
	        		terminModel.setActionName("Teilnehmen");
	        		terminModel.setIstBuchbar(true);
	        		terminModel.setGesperrt(false);
	        	} else if (terminModel.isIstBuchbar()){
	        		terminModel.setActionName("Bereits voll");
	        		terminModel.setIstBuchbar(false);
	        		terminModel.setGesperrt(true);
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
	public String toggleButton(KursTerminModel termin) {
		System.out.println("KurseController.toggleButton ()");

		if(termin.getActionName().equals("Absagen")) {
			absagen(termin);
		} else if(termin.getActionName().equals("Teilnehmen")) {
			teilnehmen(termin);
		}
		
		return null;
	}
	
	@Override
	public void teilnehmen(KursTerminModel termin) {
		System.out.println("KurseController.teilnehmen ()");
		
		Connection c = null;
	    PreparedStatement pstmt = null;
	    String insert = "INSERT INTO terminliste(terminid,mitgliedid) VALUES (?,?);";
	    String check = "SELECT istbuchbar FROM termin WHERE id=?;";
	      
	    try {
	    	Class.forName("org.postgresql.Driver");
	        c = DriverManager.getConnection("jdbc:postgresql://" + dbPfad, dbUser, dbPasswort); 
	        c.setAutoCommit(true);
	        
	        pstmt = c.prepareStatement(check);
	        pstmt.setInt(1, termin.getId());
	        ResultSet rs = pstmt.executeQuery();
	        
	        if(rs.next()) {
	        	if(rs.getBoolean("istbuchbar")) {
	    	        pstmt = c.prepareStatement(insert);
	    	        pstmt.setInt(1, termin.getId());
	    	        pstmt.setInt(2, user.getId());
	    	      
	    	        pstmt.executeUpdate();
	    	        
	        		termin.setActionName("Absagen");
	        		
	        	} else {
	        		termin.setActionName("Nicht Buchbar");
	        		termin.setGesperrt(true);
	        	}
	        }
	         
	        c.close();
	        
	        } catch (Exception e) {
	        	e.printStackTrace();
	        	System.err.println(e.getClass().getName()+": "+e.getMessage());
	        	System.exit(0);
	        	}
	}

	@Override
	public void absagen(KursTerminModel termin) {
		System.out.println("KurseController.absagen ()");
		
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
	    	        pstmt = c.prepareStatement(delete);
	    	        pstmt.setInt(1, termin.getId());
	    	        pstmt.setInt(2, user.getId());
	    	      
	    	        pstmt.executeUpdate();
	    	        
	    	        termin.setActionName("Teilnehmen");
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
	}

	//------------------------------------------------------------------
	//------------GETTER UND SETTER-------------------------------------
	//------------------------------------------------------------------
	
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
