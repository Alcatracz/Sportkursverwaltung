package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseTest {

	// SELECT -OPERATION
	  public static void main(String args[]) {
	      Connection c = null;
	      Statement stmt = null;
	      String sql="SELECT * FROM aktivitaet INNER JOIN termin ON aktivitaet.id=termin.aktivitaetid;";
	      try {
	         Class.forName("org.postgresql.Driver");
	         c = DriverManager
	            .getConnection("jdbc:postgresql://localhost:5432/Terminverwaltung",
	            "postgres", "amaterasu");
	         
	         c.setAutoCommit(false);
	         System.out.println("Opened database successfully");
	         
	         stmt = c.createStatement();
	         ResultSet rs = stmt.executeQuery(sql);
	         while(rs.next()) {
	        	int id = rs.getInt("ID");
	        	String name = rs.getString("Name");
	        	String beschreibung = rs.getString("Beschreibung");
	        	int teilnehmer = rs.getInt("Teilnehmer");
	        	String trainer = rs.getString("Trainer");
	        	
	            System.out.println( "ID = " + id );
	            System.out.println( "NAME = " + name );
	            System.out.println( "BESCHREIBUNG = " + beschreibung);
	            System.out.println( "Teilnehmer = " + teilnehmer );
	            System.out.println( "Trainer = " + trainer );
	            System.out.println();
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
	  
	  
	/*
	// INSERT -OPERATION
		  public static void main(String args[]) {
		      Connection c = null;
		      Statement stmt = null;
		      PreparedStatement pstmt = null;
		      try {
		         Class.forName("org.postgresql.Driver");
		         c = DriverManager
		            .getConnection("jdbc:postgresql://localhost:5432/Terminverwaltung",
		            "postgres", "postgres");
		         
		         c.setAutoCommit(false);
		         System.out.println("Opened database successfully");
		         
		         //stmt=c.createStatement();
		         String sql = "INSERT INTO aktivitaet (Name,Beschreibung,Teilnehmer,Trainer) VALUES(?,?,?,?);"; 
		         
		         pstmt=c.prepareStatement(sql);
		         pstmt.setString(1, "Kickboxen");
		         pstmt.setString(2, "Kampsportart");
		         pstmt.setInt(3, 10);
		         pstmt.setString(4, "Max Muster");
		         
		         pstmt.executeUpdate();
		         
		         
		         stmt = c.createStatement();
		         ResultSet rs = stmt.executeQuery("SELECT * FROM aktivitaet;");
		         while(rs.next()) {
		        	int id = rs.getInt("ID");
		        	String name = rs.getString("Name");
		        	String beschreibung = rs.getString("Beschreibung");
		        	int teilnehmer = rs.getInt("Teilnehmer");
		        	String trainer = rs.getString("Trainer");
		        	
		            System.out.println( "ID = " + id );
		            System.out.println( "NAME = " + name );
		            System.out.println( "BESCHREIBUNG = " + beschreibung);
		            System.out.println( "Teilnehmer = " + teilnehmer );
		            System.out.println( "Trainer = " + trainer );
		            System.out.println();
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
		   */
}
