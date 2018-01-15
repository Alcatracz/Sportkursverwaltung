package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import interfaces.TrainerbereichControllerInterface;
import model.MitgliedModel;
import model.AktivitaetModel;
import model.MeineTermineModel;
import model.TerminModel;
import model.TrainerTermineModel;
import model.User;

public class TrainerbereichController implements TrainerbereichControllerInterface {

	private User user;
	
	private List<MitgliedModel> mitglieder;
	private MitgliedModel mitglied;
	
	private List<AktivitaetModel> aktivitaeten;
	private AktivitaetModel aktivitaet;
	
	private List<TerminModel> termine;
	private TerminModel termin;
	
	private List<TrainerTermineModel> trainerTermine;
	private TrainerTermineModel trainerTermin;
	
	public TrainerbereichController () {
		mitglieder = new ArrayList<MitgliedModel> ();
		aktivitaeten = new ArrayList<AktivitaetModel> ();
		termine = new ArrayList<TerminModel> ();
		aktivitaet = new AktivitaetModel();
		mitglied = new MitgliedModel();
		termin = new TerminModel();
	}
	
	@PostConstruct
	public void init() {
		ladeMitglieder();
		ladeAktivitaeten();
	}
	
	@Override
	public String generatePasswort() {
		// TODO Auto-generated method stub
		return "klose";
	}

	@Override
	public void ladeMitglieder() {
		Connection c = null;
	      PreparedStatement pstmt = null;
			String sql="SELECT * FROM mitglied;";
	      
	      try {
	         Class.forName("org.postgresql.Driver");
	         c = DriverManager
	            .getConnection("jdbc:postgresql://localhost:5432/Terminverwaltung",
	            "postgres", "postgres");
	         
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
		Connection c = null;
	      PreparedStatement pstmt = null;
			String sql="SELECT * FROM aktivitaet;";
	      
	      try {
	         Class.forName("org.postgresql.Driver");
	         c = DriverManager
	            .getConnection("jdbc:postgresql://localhost:5432/Terminverwaltung",
	            "postgres", "postgres");
	         
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
		Connection c = null;
	      PreparedStatement pstmt = null;
			String sql="SELECT * FROM termin;";
	      
	      try {
	         Class.forName("org.postgresql.Driver");
	         c = DriverManager
	            .getConnection("jdbc:postgresql://localhost:5432/Terminverwaltung",
	            "postgres", "postgres");
	         
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
	public String speicherNeuesMitglied() {
		System.out.println("Test: "+mitglied.getVorname());
		//mitglied = new MitgliedModel();
		// TODO Auto-generated method stub		
		 Connection c = null;
	      PreparedStatement pstmt = null;
	      String sql = "INSERT INTO mitglied (vorname, nachname, email, passwort, isttrainer,istbuchungsbestaetigung,istterminerinnerung,terminerinnerungzeit)"
	      		+ " VALUES (?,?,?,?,?,?,?,?);";
	      
	      try {
	         Class.forName("org.postgresql.Driver");
	         c = DriverManager
	            .getConnection("jdbc:postgresql://localhost:5432/Terminverwaltung",
	            "postgres", "postgres");
	         
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
		return null;
	}

	@Override
	public String loescheMitglied(MitgliedModel mitglied) {
		// TODO Auto-generated method stub
		Connection c = null;
	      PreparedStatement pstmt = null;
	      String sql = "DELETE FROM mitglied WHERE id=?";
	      
	      try {
	         Class.forName("org.postgresql.Driver");
	         c = DriverManager
	            .getConnection("jdbc:postgresql://localhost:5432/Terminverwaltung",
	            "postgres", "postgres");
	         
	         c.setAutoCommit(true);
	         System.out.println("Opened database successfully");
	        
	         pstmt = c.prepareStatement(sql);
	         pstmt.setInt(1, mitglied.getId());
	         
	      
	         pstmt.executeUpdate();
	         pstmt.close();
	         c.close();
	         
	      } catch (Exception e) {
	         e.printStackTrace();
	         System.err.println(e.getClass().getName()+": "+e.getMessage());
	         System.exit(0);
	      }
	     
	      System.out.println("Operation done successfully");
	      
	      														//lösche Termine von mitglied
		mitglieder.remove(mitglied);
		return null;
	}

	@Override
	public String speicherNeueAktivitaet() {
		//aktivitaet = new AktivitaetModel();
		
		 Connection c = null;
	      PreparedStatement pstmt = null;
	      String sql = "INSERT INTO aktivitaet (name, beschreibung, trainer, teilnehmer)"
	      		+ " VALUES (?,?,?,?);";
	      
	      try {
	         Class.forName("org.postgresql.Driver");
	         c = DriverManager
	            .getConnection("jdbc:postgresql://localhost:5432/Terminverwaltung",
	            "postgres", "postgres");
	         
	         c.setAutoCommit(true);
	         System.out.println("Opened database successfully");
	        
	         pstmt = c.prepareStatement(sql);
	         pstmt.setString(1, aktivitaet.getName());
	         pstmt.setString(2, aktivitaet.getBeschreibung());
	         pstmt.setString(3, aktivitaet.getTrainer());
	         pstmt.setInt(4, aktivitaet.getTeilnehmer());
	      
	    
	         pstmt.executeUpdate();
	  
	         pstmt.close();
	         c.close();
	         
	      } catch (Exception e) {
	         e.printStackTrace();
	         System.err.println(e.getClass().getName()+": "+e.getMessage());
	         System.exit(0);
	      }
	     aktivitaeten.add(aktivitaet);
	      System.out.println("Operation done successfully");
		return null;
	}

	@Override
	public String loescheAktivitaet(AktivitaetModel aktivitaet) {
		Connection c = null;
	      PreparedStatement pstmt = null;
	      String sql = "DELETE FROM aktivitaet WHERE id=?";
	      
	      try {
	         Class.forName("org.postgresql.Driver");
	         c = DriverManager
	            .getConnection("jdbc:postgresql://localhost:5432/Terminverwaltung",
	            "postgres", "postgres");
	         
	         c.setAutoCommit(true);
	         System.out.println("Opened database successfully");
	        
	         pstmt = c.prepareStatement(sql);
	         pstmt.setInt(1, aktivitaet.getId());
	         
	      
	         pstmt.executeUpdate();
	         pstmt.close();
	         c.close();
	         
	      } catch (Exception e) {
	         e.printStackTrace();
	         System.err.println(e.getClass().getName()+": "+e.getMessage());
	         System.exit(0);
	      }
	     
	      System.out.println("Operation done successfully");
	      
	      														//lösche Termine von aktivität
		aktivitaeten.remove(aktivitaet);
		return null;
	}

	@Override
	public String speicherNeuenTermin() {
		termin = new TerminModel();
		
		 Connection c = null;
	      PreparedStatement pstmt = null;
	      String sql = "INSERT INTO termin (startuhrzeit,datum,istwoechentlich,buchbarab,buchbarbis,stornierbarbis,aktivitaetid)"
	      		+ " VALUES (?,?,?,?,?,?,?);";
	      
	      try {
	         Class.forName("org.postgresql.Driver");
	         c = DriverManager
	            .getConnection("jdbc:postgresql://localhost:5432/Terminverwaltung",
	            "postgres", "postgres");
	         
	         c.setAutoCommit(true);
	         System.out.println("Opened database successfully");
	        
	         pstmt = c.prepareStatement(sql);
	         pstmt.setString(1, aktivitaet.getName());
	         pstmt.setString(2, aktivitaet.getBeschreibung());
	         pstmt.setString(3, aktivitaet.getTrainer());
	         pstmt.setInt(4, aktivitaet.getTeilnehmer());
	      
	    
	         pstmt.executeUpdate();
	  
	         pstmt.close();
	         c.close();
	         
	      } catch (Exception e) {
	         e.printStackTrace();
	         System.err.println(e.getClass().getName()+": "+e.getMessage());
	         System.exit(0);
	      }
	     
	      System.out.println("Operation done successfully");
		return null;
	}

	@Override
	public String loescheTermin(TerminModel termin) {
		Connection c = null;
	      PreparedStatement pstmt = null;
	      String sql = "DELETE FROM aktivitaet WHERE id=?";
	      
	      try {
	         Class.forName("org.postgresql.Driver");
	         c = DriverManager
	            .getConnection("jdbc:postgresql://localhost:5432/Terminverwaltung",
	            "postgres", "postgres");
	         
	         c.setAutoCommit(true);
	         System.out.println("Opened database successfully");
	        
	         pstmt = c.prepareStatement(sql);
	         pstmt.setInt(1, aktivitaet.getId());
	         
	      
	         pstmt.executeUpdate();
	         pstmt.close();
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

	public List<TrainerTermineModel> getTrainerTermine() {
		return trainerTermine;
	}

	public void setTrainerTermine(List<TrainerTermineModel> trainerTermine) {
		this.trainerTermine = trainerTermine;
	}

	public TrainerTermineModel getTrainerTermin() {
		return trainerTermin;
	}

	public void setTrainerTermin(TrainerTermineModel trainerTermin) {
		this.trainerTermin = trainerTermin;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
