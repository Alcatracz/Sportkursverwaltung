package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import com.sun.xml.rpc.encoding.MissingTrailingBlockIDException;

import interfaces.TrainerbereichControllerInterface;
import model.MitgliedModel;
import model.AktivitaetModel;
import model.KursTerminModel;
import model.MeineTermineModel;
import model.TerminModel;
import model.TrainerTermineModel;
import model.User;

public class TrainerbereichController implements TrainerbereichControllerInterface {

	private String dbUser = "postgres";
	private String dbPasswort = "postgres";
	
	private User user;
	
	private List<MitgliedModel> mitglieder;
	private MitgliedModel mitglied;
	
	private List<AktivitaetModel> aktivitaeten;
	private AktivitaetModel aktivitaet;
	private AktivitaetModel aktivitaetInfo;
	
	

	private List<TerminModel> termine;
	private TerminModel termin;
	
	private List<KursTerminModel> trainerTermine;
	private KursTerminModel trainerTermin;
	
	private List<MitgliedModel> terminMitglieder;
	private MitgliedModel terminMitglied;
	
	
	public TrainerbereichController () {
		System.out.println("KONSTRUKOR");
		mitglieder = new ArrayList<MitgliedModel> ();
		aktivitaeten = new ArrayList<AktivitaetModel> ();
		termine = new ArrayList<TerminModel> ();
		trainerTermine = new ArrayList<KursTerminModel>();
		terminMitglieder = new ArrayList<MitgliedModel>();
		
		trainerTermin = new KursTerminModel();
		terminMitglied = new MitgliedModel();
		aktivitaet = new AktivitaetModel();
		mitglied = new MitgliedModel();
		termin = new TerminModel();
		aktivitaetInfo= new AktivitaetModel();
	}
	
	public AktivitaetModel getAktivitaetInfo() {
		return aktivitaetInfo;
	}

	public void setAktivitaetInfo(AktivitaetModel aktivitaetInfo) {
		this.aktivitaetInfo = aktivitaetInfo;
	}

	@PostConstruct
	public void init() {
		ladeMitglieder();
		ladeAktivitaeten();
		ladeTrainerTermine();
	}
	
	@Override
	public String generatePasswort() {
		// TODO Auto-generated method stub
		return "klose";
	}

	@Override
	public void ladeMitglieder() {
		System.out.println("ladeMitglieder");
		Connection c = null;
	      PreparedStatement pstmt = null;
			String sql="SELECT * FROM mitglied;";
	      
	      try {
	         Class.forName("org.postgresql.Driver");
	         c = DriverManager
	            .getConnection("jdbc:postgresql://localhost:5432/Terminverwaltung",
	            		dbUser, dbPasswort);
	         
	         c.setAutoCommit(true);
	         System.out.println("Opened database successfully");
	        
	         pstmt = c.prepareStatement(sql);	    
	         ResultSet rs = pstmt.executeQuery();
	         
	         while(rs.next()) {
		      MitgliedModel mitgliedModel = new MitgliedModel();
		      mitgliedModel.setId(rs.getInt("id"));
		      mitgliedModel.setVorname(rs.getString("vorname"));
		      mitgliedModel.setNachname(rs.getString("nachname"));
		      mitgliedModel.setEmail(rs.getString("email"));
		      mitgliedModel.setPasswort(rs.getString("passwort"));
		      mitgliedModel.setIstTrainer(rs.getBoolean("isttrainer"));
		      mitgliedModel.setIstBuchungsbestaetigung(rs.getBoolean("istbuchungsbestaetigung"));
		      mitgliedModel.setIstTerminerinnerung(rs.getBoolean("istterminerinnerung"));
		      mitgliedModel.setTerminerinnerungZeit(rs.getInt("terminerinnerungzeit"));
		      
		      mitglieder.add(mitgliedModel);
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

	@Override
	public void ladeAktivitaeten() {
		System.out.println("ladeAktivitaeten");
		Connection c = null;
	      PreparedStatement pstmt = null;
			String sql="SELECT * FROM aktivitaet;";
	      
	      try {
	         Class.forName("org.postgresql.Driver");
	         c = DriverManager
	            .getConnection("jdbc:postgresql://localhost:5432/Terminverwaltung",
	            		dbUser, dbPasswort);
	         
	         c.setAutoCommit(true);
	         System.out.println("Opened database successfully");
	        
	         pstmt = c.prepareStatement(sql);	    
	         ResultSet rs = pstmt.executeQuery();
	         
	         while(rs.next()) {
	        	 AktivitaetModel ak = new AktivitaetModel();
	        	 ak.setId(rs.getInt("id"));
	        	 ak.setName(rs.getString("name"));
	        	 ak.setBeschreibung(rs.getString("beschreibung"));
	        	 ak.setTeilnehmer(rs.getInt("teilnehmer"));
	        	 ak.setTrainer(rs.getString("trainer"));
	        	 
	        	 aktivitaeten.add(ak);
	        	 
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

	@Override
	public void ladeTermine(int id) {
		System.out.println("ladeTermine");
		
		termine= new ArrayList<TerminModel>();
		Connection c = null;
	      PreparedStatement pstmt = null;
			String sql="SELECT * FROM termin WHERE aktivitaetid=?;";
	      
	      try {
	         Class.forName("org.postgresql.Driver");
	         c = DriverManager
	            .getConnection("jdbc:postgresql://localhost:5432/Terminverwaltung",
	            		dbUser, dbPasswort);
	         
	         c.setAutoCommit(true);
	         System.out.println("Opened database successfully");
	        
	         pstmt = c.prepareStatement(sql);	
	         pstmt.setInt(1, id);
	         ResultSet rs = pstmt.executeQuery();
	         
	         while(rs.next()) {
	        	 TerminModel tm = new TerminModel();
	        	 tm.setId(rs.getInt("id"));
	        	 tm.setStartUhrzeit(rs.getTime("startuhrzeit").toString());
	        	 tm.setBuchbarAb(rs.getInt("buchbarab"));
	        	 tm.setBuchbarBis(rs.getInt("buchbarbis"));
	        	 tm.setDatum(rs.getDate("datum").toString());
	        	 tm.setEndUhrzeit(rs.getTime("enduhrzeit").toString());
	        	 tm.setIstWoechentlich(rs.getBoolean("istwoechentlich"));
	        	 tm.setStornierbarBis(rs.getInt("stornierbarbis"));
	        	 tm.setAktivitaetid(id); //arne suckt
	        	 termine.add(tm);
	         }
	         rs.close();
	         pstmt.close();
	         c.close();
	         
	      } catch (Exception e) {
	         e.printStackTrace();
	         System.err.println(e.getClass().getName()+": "+e.getMessage());
	         System.exit(0);
	      }
	     // System.out.println("ID ARNE SUCKT"+ aktivitaetinfo.getId());
	    
	      System.out.println("Operation done successfully");
	}

	@Override
	public String speicherNeuesMitglied() {
		System.out.println("speicherNeuesMitglied");
		// TODO Auto-generated method stub		
		 Connection c = null;
	      PreparedStatement pstmt = null;
	      String sql = "INSERT INTO mitglied (vorname, nachname, email, passwort, isttrainer,istbuchungsbestaetigung,istterminerinnerung,terminerinnerungzeit)"
	      		+ " VALUES (?,?,?,?,?,?,?,?);";
	      
	      try {
	         Class.forName("org.postgresql.Driver");
	         c = DriverManager
	            .getConnection("jdbc:postgresql://localhost:5432/Terminverwaltung",
	            		dbUser, dbPasswort);
	         
	         c.setAutoCommit(true);
	         System.out.println("Opened database successfully");
	        
	         pstmt = c.prepareStatement(sql);
	         pstmt.setString(1, mitglied.getVorname());
	         pstmt.setString(2, mitglied.getNachname());
	         pstmt.setString(3, mitglied.getEmail());
	         pstmt.setString(4, generatePasswort());
	         pstmt.setBoolean(5, mitglied.isIstTrainer());
	         pstmt.setBoolean(6, false);
	         pstmt.setBoolean(7, false);
	         pstmt.setInt(8, 0);
	      
	    
	         pstmt.executeUpdate();
	  
	         pstmt.close();
	         c.close();
	         
	      } catch (Exception e) {
	         e.printStackTrace();
	         System.err.println(e.getClass().getName()+": "+e.getMessage());
	         System.exit(0);
	      }
	     
	      System.out.println("Operation done successfully");
	      
		mitglieder.add(mitglied);
		mitglied= new MitgliedModel();
		//ladeMitglieder();
		return null;
	}

	@Override
	public String loescheMitglied(MitgliedModel mitglied) {
		System.out.println("löscheMitglied: "+mitglied.getVorname());
		Connection c = null;
	      PreparedStatement pstmt = null;
	      String sql = "DELETE FROM mitglied WHERE id=?";
	      
	      try {
	         Class.forName("org.postgresql.Driver");
	         c = DriverManager
	            .getConnection("jdbc:postgresql://localhost:5432/Terminverwaltung",
	            		dbUser, dbPasswort);
	         
	         c.setAutoCommit(true);
	         System.out.println("Opened database successfully");
	        
	         pstmt = c.prepareStatement(sql);
	         pstmt.setInt(1, mitglied.getId());
	         pstmt.executeUpdate();
	         pstmt.close();
	         
	         String delete ="DELETE FROM terminliste WHERE mitgliedid=?";
	         PreparedStatement ps =c.prepareStatement(delete);
	         ps.setInt(1, mitglied.getId());
	         ps.executeUpdate();
	         ps.close();
	         
	         c.close();
	         
	      } catch (Exception e) {
	         e.printStackTrace();
	         System.err.println(e.getClass().getName()+": "+e.getMessage());
	         System.exit(0);
	      }
	     
	      System.out.println("Operation done successfully");
	      
	      														//lösche Termine von mitglied-------------------------------------
		mitglieder.remove(mitglied);
		return null;
	}

	@Override
	public String speicherNeueAktivitaet() {
		System.out.println("speicherNeueAktivitaet");
		//aktivitaet = new AktivitaetModel();
		
		 Connection c = null;
	      PreparedStatement pstmt = null;
	      String sql = "INSERT INTO aktivitaet (name, beschreibung, trainer, teilnehmer)"
	      		+ " VALUES (?,?,?,?);";
	      
	      try {
	         Class.forName("org.postgresql.Driver");
	         c = DriverManager
	            .getConnection("jdbc:postgresql://localhost:5432/Terminverwaltung",
	            		dbUser, dbPasswort);
	         
	         c.setAutoCommit(true);
	         System.out.println("Opened database successfully");
	        
	         pstmt = c.prepareStatement(sql,new String[] {"id"});
	         
	         pstmt.setString(1, aktivitaet.getName());
	         pstmt.setString(2, aktivitaet.getBeschreibung());
	         pstmt.setString(3, aktivitaet.getTrainer());
	         pstmt.setInt(4, aktivitaet.getTeilnehmer());
	         

	    
	         pstmt.executeUpdate();
	  
	         ResultSet rs = pstmt.getGeneratedKeys();
	         System.out.println("rslength: "+rs.getFetchSize());
	         long id=0L;
	         if(rs.next()) {
	        	 id = rs.getLong(1); 
	         }//
	         
	         System.out.println("ROFL DIE ID: " + id);
	         aktivitaet.setId(Math.toIntExact(id));
	         
	         pstmt.close();
	         c.close();
	         
	      } catch (Exception e) {
	         e.printStackTrace();
	         System.err.println(e.getClass().getName()+": "+e.getMessage());
	         System.exit(0);
	      }
	     aktivitaeten.add(aktivitaet);
	     aktivitaet= new AktivitaetModel();
	    // ladeAktivitaeten();
	      System.out.println("Operation done successfully");
		return null;
	}

	@Override
	public String loescheAktivitaet(AktivitaetModel aktivitaet) {
		System.out.println("löscheAktivitaet"+aktivitaet.getName());
		Connection c = null;
	      PreparedStatement pstmt = null;
	      String sql = "DELETE FROM aktivitaet WHERE id=?";
	      
	      try {
	         Class.forName("org.postgresql.Driver");
	         c = DriverManager
	            .getConnection("jdbc:postgresql://localhost:5432/Terminverwaltung",
	            		dbUser, dbPasswort);
	         
	         c.setAutoCommit(true);
	         System.out.println("Opened database successfully");
	        
	         pstmt = c.prepareStatement(sql);
	         pstmt.setInt(1, aktivitaet.getId());
	         pstmt.executeUpdate();
	         
	         String select = "SELECT t.id FROM termin t WHERE aktivitaetid=?;";
	         
	         pstmt=c.prepareStatement(select);
	         pstmt.setInt(1, aktivitaet.getId());
	         ResultSet rs=pstmt.executeQuery();
	        
	         
	         while(rs.next()) {
	        	 String deleteListe="DELETE FROM terminliste WHERE terminid=?";
	        	 PreparedStatement pst = c.prepareStatement(deleteListe);
	        	 pst.setInt(1, rs.getInt("id"));
	        	 pst.executeUpdate();
	        	 pst.close();
	        	 
	         }
	         pstmt.close(); 
	         
	         
	         System.out.println("ID:"+aktivitaet.getId());
	         
	         String delete = "DELETE FROM termin WHERE aktivitaetid=?";
	         PreparedStatement ps = c.prepareStatement(delete);
	         ps.setInt(1, aktivitaet.getId());
	         ps.executeUpdate();
	         ps.close();
	         
	         c.close();
	         
	      } catch (Exception e) {
	         e.printStackTrace();
	         System.err.println(e.getClass().getName()+": "+e.getMessage());
	         System.exit(0);
	      }
	     
	      System.out.println("Operation done successfully");
	      
	      														//lösche Termine von aktivität------------------------------
		aktivitaeten.remove(aktivitaet);
		return null;
	}

	@Override
	public String speicherNeuenTermin(AktivitaetModel aktivitaet) {
		System.out.println("speicherNeuenTermin");
		System.out.println("AktivitaetID: " +aktivitaet.getId());
		
		 Connection c = null;
	      PreparedStatement pstmt = null;
	      String sql = "INSERT INTO termin (startuhrzeit,enduhrzeit,datum,istwoechentlich,buchbarab,buchbarbis,stornierbarbis,aktivitaetid)"
	      		+ " VALUES (?,?,?,?,?,?,?,?);";
	      
	      try {
	         Class.forName("org.postgresql.Driver");
	         c = DriverManager
	            .getConnection("jdbc:postgresql://localhost:5432/Terminverwaltung",
	            		dbUser, dbPasswort);
	         
	         c.setAutoCommit(true);
	         System.out.println("Opened database successfully");
	        DateFormat dateformatter = new SimpleDateFormat("YYYY-MM-DD");
	        DateFormat timeformatter = new SimpleDateFormat("HH:MM:SS");
	        
	         pstmt = c.prepareStatement(sql);
	         
	         pstmt.setTime(1, new Time(timeformatter.parse(termin.getStartUhrzeit()).getTime()));
	         pstmt.setTime(2, new Time(timeformatter.parse(termin.getEndUhrzeit()).getTime()));
	         pstmt.setDate(3, new java.sql.Date(dateformatter.parse(termin.getDatum()).getTime()));
	         pstmt.setBoolean(4, termin.isIstWoechentlich());
	         pstmt.setInt(5, termin.getBuchbarAb());
	         pstmt.setInt(6, termin.getBuchbarBis());
	         pstmt.setInt(7, termin.getStornierbarBis());
	         pstmt.setInt(8, aktivitaet.getId());
	      
	    
	         pstmt.executeUpdate();
	  
	         pstmt.close();
	         c.close();
	         
	      } catch (Exception e) {
	         e.printStackTrace();
	         System.err.println(e.getClass().getName()+": "+e.getMessage());
	         System.exit(0);
	      }
	     termine.add(termin);
	     termin = new TerminModel();
	      System.out.println("Operation done successfully");
	      //ladeTermine(currentAktivitaetId);
		return null;
	}

	@Override
	public String loescheTermin(TerminModel termin) {
		System.out.println("löscheTermin"+termin.getDatum()+"/ "+termin.getId());
		Connection c = null;
	      PreparedStatement pstmt = null;
	      String sql = "DELETE FROM termin WHERE id=?";
	      
	      try {
	         Class.forName("org.postgresql.Driver");
	         c = DriverManager
	            .getConnection("jdbc:postgresql://localhost:5432/Terminverwaltung",
	            		dbUser, dbPasswort);
	         
	         c.setAutoCommit(true);
	         System.out.println("Opened database successfully");
	        
	         pstmt = c.prepareStatement(sql);
	         pstmt.setInt(1, termin.getId());
	         
	      
	         pstmt.executeUpdate();
	         pstmt.close();
	         
	         String delete ="DELETE FROM terminliste WHERE terminid=?";
	         PreparedStatement ps =c.prepareStatement(delete);
	         ps.setInt(1, termin.getId());
	         ps.executeUpdate();
	         ps.close();
	         
	         c.close();
	         
	      } catch (Exception e) {
	         e.printStackTrace();
	         System.err.println(e.getClass().getName()+": "+e.getMessage());
	         System.exit(0);
	      }
	     
	      System.out.println("Operation done successfully");
	      
	      														
		termine.remove(termin);
		return null;
	}


	@Override
	public void ladeTrainerTermine() {
		// TODO Auto-generated method stub
		System.out.println("ladeTrainerTermine");
				 Connection c = null;
			      Statement stmt = null;
			      String sql="SELECT a.name, a.trainer, a.beschreibung, a.teilnehmer, t.datum, t.startuhrzeit, t.enduhrzeit, t.id"
			      		+ ",t.buchbarab, t.buchbarbis, t.stornierbarbis"
			      		+ " FROM aktivitaet a INNER JOIN termin t ON a.id = t.aktivitaetid;";
			      
			      try {
			         Class.forName("org.postgresql.Driver");
			         c = DriverManager
			            .getConnection("jdbc:postgresql://localhost:5432/Terminverwaltung",
			            		dbUser, dbPasswort);
			         
			         c.setAutoCommit(false);
			         System.out.println("Opened database successfully");
			        
			         stmt = c.createStatement();
			         ResultSet rs = stmt.executeQuery(sql);
			         while(rs.next()) {
		
			        	KursTerminModel terminModel = new KursTerminModel();
			        	terminModel.setTerminId(rs.getInt("id"));
			        	terminModel.setName(rs.getString("name"));
			        	terminModel.setTrainer(rs.getString("trainer"));
			        	terminModel.setBeschreibung(rs.getString("beschreibung"));
			        	terminModel.setDatum(rs.getDate("datum").toString());
			        	terminModel.setStartUhrzeit(rs.getTime("startuhrzeit").toString());
			        	terminModel.setEndUhrzeit(rs.getTime("enduhrzeit").toString());
			        	terminModel.setStornierbarBis(rs.getInt("stornierbarbis"));
			        	terminModel.setBuchbarAb(rs.getInt("buchbarab"));
			        	terminModel.setBuchbarBis(rs.getInt("buchbarbis"));
			        	
			     	
			        	
			        	trainerTermine.add(terminModel);
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
			      System.out.println("LISTE: "+trainerTermine.size());
		
	}

	@Override
	public void ladeTerminDetails(int id) {
		terminMitglieder= new ArrayList<MitgliedModel>();
		// TODO Auto-generated method stub
		System.out.println("ladeTerminDetailsr");
		Connection c = null;
	      PreparedStatement pstmt = null;
			String sql="SELECT m.vorname, m.nachname FROM mitglied m INNER JOIN terminliste tl ON tl.mitgliedid=m.id WHERE tl.terminid = ? ;";
	      
	      try {
	         Class.forName("org.postgresql.Driver");
	         c = DriverManager
	            .getConnection("jdbc:postgresql://localhost:5432/Terminverwaltung",
	            		dbUser, dbPasswort);
	         
	         c.setAutoCommit(true);
	         System.out.println("Opened database successfully");
	        
	         pstmt = c.prepareStatement(sql);
	         pstmt.setInt(1, id);
	         ResultSet rs = pstmt.executeQuery();
	         
	         while(rs.next()) {
		      MitgliedModel mitgliedModel = new MitgliedModel();
		      mitgliedModel.setVorname(rs.getString("vorname"));
		      mitgliedModel.setNachname(rs.getString("nachname"));
	  
		      terminMitglieder.add(mitgliedModel);
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<MitgliedModel> getMitglieder() {
		return mitglieder;
	}

	public void setMitglieder(List<MitgliedModel> mitglieder) {
		this.mitglieder = mitglieder;
	}

	public MitgliedModel getMitglied() {
		return mitglied;
	}

	public void setMitglied(MitgliedModel mitglied) {
		this.mitglied = mitglied;
	}

	public List<AktivitaetModel> getAktivitaeten() {
		return aktivitaeten;
	}

	public void setAktivitaeten(List<AktivitaetModel> aktivitaeten) {
		this.aktivitaeten = aktivitaeten;
	}

	public AktivitaetModel getAktivitaet() {
		return aktivitaet;
	}

	public void setAktivitaet(AktivitaetModel aktivitaet) {
		this.aktivitaet = aktivitaet;
	}

	public List<TerminModel> getTermine() {
		return termine;
	}

	public void setTermine(List<TerminModel> termine) {
		this.termine = termine;
	}

	public TerminModel getTermin() {
		return termin;
	}

	public void setTermin(TerminModel termin) {
		this.termin = termin;
	}

	public List<KursTerminModel> getTrainerTermine() {
		return trainerTermine;
	}

	public void setTrainerTermine(List<KursTerminModel> trainerTermine) {
		this.trainerTermine = trainerTermine;
	}

	public KursTerminModel getTrainerTermin() {
		return trainerTermin;
	}

	public void setTrainerTermin(KursTerminModel trainerTermin) {
		this.trainerTermin = trainerTermin;
	}

	public List<MitgliedModel> getTerminMitglieder() {
		return terminMitglieder;
	}

	public void setTerminMitglieder(List<MitgliedModel> terminMitglieder) {
		this.terminMitglieder = terminMitglieder;
	}

	public MitgliedModel getTerminMitglied() {
		return terminMitglied;
	}

	public void setTerminMitglied(MitgliedModel terminMitglied) {
		this.terminMitglied = terminMitglied;
	}

}
