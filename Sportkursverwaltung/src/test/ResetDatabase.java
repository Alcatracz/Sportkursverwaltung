package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ResetDatabase {
	
	public static void main(String args[]) {
		Connection c = null;
	    PreparedStatement pstmt = null;
	    String sql = "";
	
	      
	      try {
	         Class.forName("org.postgresql.Driver");
	         c = DriverManager
	            .getConnection("jdbc:postgresql://localhost:5432/Terminverwaltung",
	            "postgres", "postgres");
	         
	         c.setAutoCommit(true);
	         System.out.println("Opened database successfully");
	        
	         sql="TRUNCATE TABLE terminliste,mitglied,termin,aktivitaet; ";
	         pstmt = c.prepareStatement(sql);  
	         pstmt.executeUpdate();
	         
	         sql="ALTER SEQUENCE mitglied_id_seq RESTART WITH 1;";
	         pstmt = c.prepareStatement(sql);  
	         pstmt.executeUpdate();
	         
	         sql="ALTER SEQUENCE aktivitaet_id_seq RESTART WITH 1;";
	         pstmt = c.prepareStatement(sql);  
	         pstmt.executeUpdate();
	         
	         sql="ALTER SEQUENCE termin_id_seq RESTART WITH 1;";
	         pstmt = c.prepareStatement(sql);  
	         pstmt.executeUpdate();
	         
	         sql="ALTER SEQUENCE terminliste_id_seq RESTART WITH 1;";
	         pstmt = c.prepareStatement(sql);  
	         pstmt.executeUpdate();
	         
	 
	         sql= "INSERT INTO mitglied (vorname,nachname,email,passwort,isttrainer,istbuchungsbestaetigung,istterminerinnerung,terminerinnerungzeit) "
	         		+ "VALUES('Max','Muster','admin','passwort',true,false,false,0);";
	         
	         pstmt= c.prepareStatement(sql);
	         pstmt.executeUpdate();
	         
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
