package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.*;

import model.AktivitaetModel;
import model.KursTerminModel;
import model.TerminModelTimeManagement;

@Stateless
public class TimeManagement {


	private String dbUser = "postgres";
	private String dbPasswort = "postgres";
	private List<TerminModelTimeManagement> trainerTermine = new ArrayList<TerminModelTimeManagement>();
	LocalDate localDate;

	
	@Schedule(hour="*",minute="*",second="*/10",persistent=false)
	public void test() {
		ladeTrainerTermine();
		trainerTermine.size();
	       localDate = LocalDate.now().minusDays(1);
	       DateTimeFormatter.ofPattern("yyy-MM-dd").format(localDate);
	       System.out.println("Current Date"+localDate.toString());
		for(int i=0;i<trainerTermine.size();i++) {
			String date1 = trainerTermine.get(i).getDatum();
			System.out.println("boolean"+trainerTermine.get(i).isIstWoechentlich());
			if(date1.equals(localDate.toString())){
				if(trainerTermine.get(i).isIstWoechentlich()) {
					 System.out.println("Ist Wöchentlich");
					 erstelleNeuenTermin(trainerTermine.get(i));
				}
				loescheAltenTermin(trainerTermine.get(i));
			}
		}
		trainerTermine.clear();
	}
	
	
	private void loescheAltenTermin(TerminModelTimeManagement termin) {
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
	}


	public void ladeTrainerTermine() {
		// TODO Auto-generated method stub
		System.out.println("ladeTrainerTermine");
				 Connection c = null;
			      Statement stmt = null;
			      String sql="SELECT   t.datum, t.startuhrzeit, t.enduhrzeit,t.id,t.istwoechentlich"
			      		+ ",t.buchbarab, t.buchbarbis, t.stornierbarbis, t.aktivitaetid,t.istbuchbar,t.iststornierbar,t.dauer"
			      		+ " FROM termin t;";
			      
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
		
			        	TerminModelTimeManagement terminModel = new TerminModelTimeManagement();
			        	terminModel.setId(rs.getInt("id"));
			        	terminModel.setDatum(rs.getDate("datum").toString());
			        	terminModel.setStartUhrzeit(rs.getTime("startuhrzeit").toString());
			        	terminModel.setEndUhrzeit(rs.getTime("enduhrzeit").toString());
			        	terminModel.setIstWoechentlich(rs.getBoolean("istwoechentlich"));
			        	System.out.println(terminModel.isIstWoechentlich());
			        	terminModel.setStornierbarBis(rs.getInt("stornierbarbis"));
			        	terminModel.setBuchbarAb(rs.getInt("buchbarab"));
			        	terminModel.setBuchbarBis(rs.getInt("buchbarbis"));
			        	terminModel.setAktivitaetid(rs.getInt("aktivitaetid"));
			        	terminModel.setIstBuchbar(rs.getBoolean("istbuchbar"));
			        	terminModel.setIstStornierbar(rs.getBoolean("iststornierbar"));
			        	terminModel.setDauer(rs.getInt("dauer"));
			        	
			        	
			     	
			        	
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
	
	public String erstelleNeuenTermin(TerminModelTimeManagement termin) {
		System.out.println("speicherNeuenTermin");
		
		 Connection c = null;
	      PreparedStatement pstmt = null;
	      String sql = "INSERT INTO termin (startuhrzeit,enduhrzeit,datum,istwoechentlich,buchbarab,buchbarbis,stornierbarbis,aktivitaetid,istbuchbar,iststornierbar,dauer)"
	      		+ " VALUES (?,?,?,?,?,?,?,?,?,?,?);";
	      
	      try {
	         Class.forName("org.postgresql.Driver");
	         c = DriverManager
	            .getConnection("jdbc:postgresql://localhost:5432/Terminverwaltung",
	            		dbUser, dbPasswort);
	         
	         c.setAutoCommit(true);
	         System.out.println("Opened database successfully");
	        DateFormat timeformatter = new SimpleDateFormat("HH:MM:SS");
	        
	         pstmt = c.prepareStatement(sql);
	         
	         pstmt.setTime(1, new Time(timeformatter.parse(termin.getStartUhrzeit()).getTime()));
	         pstmt.setTime(2, new Time(timeformatter.parse(termin.getEndUhrzeit()).getTime()));
	         pstmt.setDate(3, java.sql.Date.valueOf(localDate.plusDays(14)));
	         pstmt.setBoolean(4, termin.isIstWoechentlich());
	         pstmt.setInt(5, termin.getBuchbarAb()); 
	         pstmt.setInt(6, termin.getBuchbarBis());
	         pstmt.setInt(7, termin.getStornierbarBis());
	         pstmt.setInt(8, termin.getAktivitaetid());
	         pstmt.setBoolean(9, true);
	         pstmt.setBoolean(10, true);
	         pstmt.setInt(11, termin.getDauer());
	         
	         
	    
	         pstmt.executeUpdate();
	  
	         pstmt.close();
	         c.close();
	         
	      } catch (Exception e) {
	         e.printStackTrace();
	         System.err.println(e.getClass().getName()+": "+e.getMessage());
	         System.exit(0);
	      }
	      System.out.println("Operation done successfully");
	      //ladeTermine(currentAktivitaetId);
		return null;
	}
	
	
	
	
}
