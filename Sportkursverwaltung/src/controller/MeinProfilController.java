package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.annotation.PostConstruct;

import interfaces.MeinProfilControllerInterface;
import model.ProfilDatenModel;
import model.MeineTermineModel;
import model.User;

public class MeinProfilController implements MeinProfilControllerInterface {

	private String dbUser = "postgres";
	private String dbPasswort = "postgres";
	
	private User user;
	private ProfilDatenModel profilDaten;
	
	private String passwortNeu;
	private String emailNeu;
	

	public MeinProfilController() {
		profilDaten= new ProfilDatenModel();
	}
	@PostConstruct
	public void init() {
		ladeProfildaten();
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public ProfilDatenModel getProfilDaten() {
		return profilDaten;
	}

	public void setProfilDaten(ProfilDatenModel profilDaten) {
		this.profilDaten = profilDaten;
	}

	@Override
	public void ladeProfildaten() {
		 Connection c = null;
	      PreparedStatement pstmt = null;
	      String sql="SELECT * FROM mitglied WHERE id=?";
	      
	      try {
	         Class.forName("org.postgresql.Driver");
	         c = DriverManager
	            .getConnection("jdbc:postgresql://localhost:5432/Terminverwaltung",
	            		dbUser, dbPasswort);
	         
	         c.setAutoCommit(true);
	         System.out.println("Opened database successfully");
	        
	         pstmt = c.prepareStatement(sql);
	         pstmt.setInt(1,user.getId());
	         
	         
	         ResultSet rs = pstmt.executeQuery();
	         
	         while(rs.next()) {
	        	
	        	
	        	profilDaten.setVorname(rs.getString("vorname"));
	        	profilDaten.setNachname(rs.getString("nachname"));
	        	profilDaten.setEmail(rs.getString("email"));
	        	profilDaten.setPasswort(rs.getString("passwort"));
	        	profilDaten.setIstTrainer(rs.getBoolean("isttrainer"));
	        	profilDaten.setIstBuchungsbestaetigung(rs.getBoolean("istbuchungsbestaetigung"));
	        	profilDaten.setIstTerminerinnerung(rs.getBoolean("istterminerinnerung"));
	        	profilDaten.setTerminerinnerungZeit(rs.getInt("terminerinnerungzeit"));
	        	
	        
	        	
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
	public String speicherProfildaten() {
		System.out.println("Speichern");
		System.out.println("BUCHUNGSBESTAETIGUNG: " + profilDaten.isIstBuchungsbestaetigung());
		 Connection c = null;
	      PreparedStatement pstmt = null;
	      String sql = "UPDATE mitglied SET istbuchungsbestaetigung = ?, istterminerinnerung = ?, terminerinnerungzeit = ? WHERE id = ?;";
	      
	      try {
	         Class.forName("org.postgresql.Driver");
	         c = DriverManager
	            .getConnection("jdbc:postgresql://localhost:5432/Terminverwaltung",
	            		dbUser, dbPasswort);
	         
	         c.setAutoCommit(true);
	         System.out.println("Opened database successfully");
	        
	         pstmt = c.prepareStatement(sql);
	         pstmt.setBoolean(1, profilDaten.isIstBuchungsbestaetigung());
	         pstmt.setBoolean(2, profilDaten.isIstTerminerinnerung());
	         pstmt.setInt(3, profilDaten.getTerminerinnerungZeit());
	         pstmt.setInt(4, user.getId());
	    
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
	public String emailAendern() {
		 Connection c = null;
	      PreparedStatement pstmt = null;
	      String sql = "UPDATE mitglied SET email = ? WHERE id = ?;";
	      
	      try {
	         Class.forName("org.postgresql.Driver");
	         c = DriverManager
	            .getConnection("jdbc:postgresql://localhost:5432/Terminverwaltung",
	            		dbUser, dbPasswort);
	         
	         c.setAutoCommit(true);
	         System.out.println("Opened database successfully");
	        
	         pstmt = c.prepareStatement(sql);
	         pstmt.setString(1, emailNeu);
	         pstmt.setInt(2, user.getId());
	         
	         
	         pstmt.executeUpdate();
	        profilDaten.setEmail(emailNeu);
	        emailNeu="";
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
	public String passwortAendern() {
		 Connection c = null;
	      PreparedStatement pstmt = null;
	      String sql = "UPDATE mitglied SET passwort = ? WHERE id = ?;";
	      
	      try {
	         Class.forName("org.postgresql.Driver");
	         c = DriverManager
	            .getConnection("jdbc:postgresql://localhost:5432/Terminverwaltung",
	            		dbUser, dbPasswort);
	         
	         c.setAutoCommit(true);
	         System.out.println("Opened database successfully");
	        
	         pstmt = c.prepareStatement(sql);
	         pstmt.setString(1, passwortNeu);
	         pstmt.setInt(2, user.getId());
	         
	         
	         pstmt.executeUpdate();
	        
	         profilDaten.setPasswort(passwortNeu);
		        passwortNeu="";
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
	
	public String getPasswortNeu() {
		return passwortNeu;
	}

	public void setPasswortNeu(String passwortNeu) {
		this.passwortNeu = passwortNeu;
	}

	public String getEmailNeu() {
		return emailNeu;
	}

	public void setEmailNeu(String emailNeu) {
		this.emailNeu = emailNeu;
	}

}
