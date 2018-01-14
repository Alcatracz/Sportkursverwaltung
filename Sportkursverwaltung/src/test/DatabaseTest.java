package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseTest {

	public static Connection connect() throws SQLException, ClassNotFoundException {
		Connection c = null;   
		
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Terminverwaltung","postgres", "postgres");
		    c.setAutoCommit(true);
        
	         return c;
	}
	
	
	public static void selectAll(Connection c) throws SQLException {
		String sql="SELECT * FROM aktivitaet;";
		PreparedStatement pstmt = c.prepareStatement(sql);
		
		ResultSet rs = pstmt.executeQuery();
		
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
         pstmt.close();
	}
	
	public static void selectByAttribute(Connection c) throws SQLException {
		String sql="SELECT tl.id, t.datum, t.enduhrzeit, t.startuhrzeit, a.name, a.beschreibung, a.trainer FROM terminliste tl INNER JOIN termin t ON "
				+ "tl.terminid = t.id INNER JOIN aktivitaet a ON t.aktivitaetid = a.id WHERE tl.mitgliedid = 1;";
        PreparedStatement pstmt=c.prepareStatement(sql);
        //pstmt.setInt(1, 1); 
        
        ResultSet rs = pstmt.executeQuery();
		
        while(rs.next()) {
        	int id = rs.getInt("id");
        	String name = rs.getString("name");
        	String beschreibung = rs.getString("beschreibung");
        	String trainer = rs.getString("trainer");
        	String datum =rs.getDate("datum").toString();
        	String startuhrzeit =rs.getTime("startuhrzeit").toString();
        	String enduhrzeit = rs.getTime("enduhrzeit").toString();
        	
            System.out.println( "ID = " + id );
            System.out.println( "NAME = " + name );
            System.out.println( "BESCHREIBUNG = " + beschreibung);
            System.out.println( "DATUM = " + datum );
            System.out.println( "START = " + startuhrzeit );
            System.out.println( "ENDE = " + enduhrzeit );
            System.out.println( "Trainer = " + trainer );
            System.out.println();
         }
         rs.close();
         pstmt.close();
	}
	
	public static void insert(Connection c) throws SQLException {
		String sql = "INSERT INTO aktivitaet (Name,Beschreibung,Teilnehmer,Trainer) VALUES(?,?,?,?);";
		
        PreparedStatement pstmt=c.prepareStatement(sql);
        pstmt.setString(1, "Kickboxen");
        pstmt.setString(2, "Kampsportart");
        pstmt.setInt(3, 10);
        pstmt.setString(4, "Max Muster");
        
        pstmt.executeUpdate();
        
        pstmt.close();
	}
	
	public static void delete(Connection c) throws SQLException {
		String sql = "DELETE FROM terminliste WHERE terminid = ? AND mitgliedid=?";
		PreparedStatement pstmt = c.prepareStatement(sql);
		pstmt.setInt(1, 1);
		pstmt.setInt(2, 3);
		pstmt.executeUpdate();
		pstmt.close();
	}
	
	public static void update(Connection c) throws SQLException {
		String sql = "UPDATE aktivitaet SET beschreibung = ? WHERE id = ?;";
		
		PreparedStatement pstmt = c.prepareStatement(sql);
		pstmt.setString(1, "Hab was geändert");
		pstmt.setInt(2, 7);
		
		pstmt.executeUpdate();
		pstmt.close();
	}
	
//-----------------------------------------------------------------------------
	
	  public static void main(String args[]) {
		  Connection c= null;
		  
		  try {
			c=connect();
			
			//selectAll(c);
			//System.out.println("-----------------------------");
			//selectByAttribute(c);
			//System.out.println("-----------------------------");
			//insert(c);
			//selectAll(c);
			//System.out.println("-----------------------------");
			//update(c);
			//selectAll(c);
			//System.out.println("-----------------------------");
			delete(c);
			//selectAll(c);
			//System.out.println("-----------------------------");
			
			c.close();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      
	   }
  
		 
}
