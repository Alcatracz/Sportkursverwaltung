package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.ProfilDatenModel;

public class AnotherTest {

	public static void main(String args[]) {
		Connection c = null;
	      PreparedStatement pstmt = null;
	      String sql="SELECT * FROM mitglied WHERE id=?;";
	      
	      try {
	         Class.forName("org.postgresql.Driver");
	         c = DriverManager
	 	            .getConnection("jdbc:postgresql://localhost:5432/Terminverwaltung",
	 	            "postgres", "amaterasu");
	         
	         c.setAutoCommit(false);
	         System.out.println("Opened database successfully");
	        
	         pstmt = c.prepareStatement(sql);
	         pstmt.setInt(1, 1);
	         
	         
	         ResultSet rs = pstmt.executeQuery();
	         
	         while(rs.next()) {
	        	ProfilDatenModel profilDaten = new ProfilDatenModel();
	        	
	        	profilDaten.setVorname(rs.getString("vorname"));
	        	profilDaten.setNachname(rs.getString("nachname"));
	        	profilDaten.setEmail(rs.getString("email"));
	        	profilDaten.setPasswort(rs.getString("passwort"));
	        	profilDaten.setIstTrainer(rs.getBoolean("isttrainer"));
	        	profilDaten.setIstBuchungsbestaetigung(rs.getBoolean("istbuchungsbestaetigung"));
	        	profilDaten.setIstTerminerinnerung(rs.getBoolean("istterminerinnerung"));
	        	profilDaten.setTerminerinnerungZeit(rs.getInt("terminerinnerungzeit"));
	        	
	        
	        	System.out.println("Name: "+profilDaten.getVorname());
	        	System.out.println("Name: "+profilDaten.isIstBuchungsbestaetigung());
	        	
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
}
