package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import interfaces.MeinProfilControllerInterface;
import model.ProfilDatenModel;
import model.TerminAnzeigeModel;
import model.User;

public class MeinProfilController implements MeinProfilControllerInterface {

	User user;
	ProfilDatenModel profilDaten;
	
	public MeinProfilController() {
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
	      String sql="SELECT * FROM mitglied WHERE id=1";
	      
	      try {
	         Class.forName("org.postgresql.Driver");
	         c = DriverManager
	            .getConnection("jdbc:postgresql://localhost:5432/Terminverwaltung",
	            "postgres", "amaterasu");
	         
	         c.setAutoCommit(false);
	         System.out.println("Opened database successfully");
	        
	         pstmt = c.prepareStatement(sql);
	       //  pstmt.setInt(1, user.getId());
	         
	         
	         ResultSet rs = pstmt.executeQuery();
	         
	         while(rs.next()) {
	        	ProfilDatenModel profilDaten = new ProfilDatenModel();
	        	
	        	profilDaten.setVorname(rs.getString("vorname"));
	        	profilDaten.setNachname(rs.getString("nachname"));
	        	profilDaten.setEmail(rs.getString("email"));
	        	profilDaten.setPasswort(rs.getString("passwort"));
	        	profilDaten.setIstTrainer(rs.getBoolean("isttrainer"));
	        	profilDaten.setIstBuchungsbestaetigung(rs.getBoolean("istbuchungsbestaetigung"));
<<<<<<< Upstream, based on branch 'master' of https://github.com/Alcatracz/Sportkursverwaltung.git
	        	profilDaten.setIstTerminerinnerung(rs.getBoolean("istterminerinnerung"));
=======
	        	profilDaten.setIstTerminerinnerung(rs.getBoolean("istterminnerinerung"));
>>>>>>> 2884fd2 datebase tests
	        	profilDaten.setTerminerinnerungZeit(rs.getInt("terminerinnerungzeit"));
	        	
	        	this.profilDaten=profilDaten;
	        	
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
	public void speicherProfildaten() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void emailAendern() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void passwortAendern() {
		// TODO Auto-generated method stub
		
	}

}
