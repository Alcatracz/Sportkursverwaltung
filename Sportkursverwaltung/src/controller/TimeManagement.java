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
import java.time.LocalDateTime;
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
	private List<TerminModelTimeManagement> freischaltTermine = new ArrayList<TerminModelTimeManagement>();
	LocalDate realDate;
	LocalDate localDate;
	LocalDate localDate2;
	LocalDate buchbarabdate;
	LocalDateTime ldt;
//    ldt = LocalDateTime.now();
//    ldt.mi
	
	@Schedule(hour="*",minute="*",second="*/15",persistent=false)
	public void test() {
		ladeTrainerTermine(trainerTermine);
		trainerTermine.size();
		   realDate = LocalDate.now();
	       localDate = LocalDate.now().minusDays(1);
	       DateTimeFormatter formatter= DateTimeFormatter.ofPattern("dd.llll.yyyy");
	       String formatedldate=localDate.format(formatter);
	       String formatedrealdate=realDate.format(formatter);

		for(int i=0;i<trainerTermine.size();i++) {
			
			buchbarabdate = LocalDate.now().minusDays(trainerTermine.get(i).getBuchbarAb());
			String formatedabdate=localDate.format(formatter);
			if(formatedabdate.equals(formatedrealdate)) {
				updateBuchbar(trainerTermine.get(i).getId());
			}
			
			String date1 = trainerTermine.get(i).getDatum();
			System.out.println(trainerTermine.get(i).getStartUhrzeit());		
			System.out.println("boolean"+trainerTermine.get(i).isIstWoechentlich());
			System.out.println( formatedldate);

			if(date1.equals(formatedldate)){
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


	public void ladeTrainerTermine(List<TerminModelTimeManagement> term) {
		// TODO Auto-generated method stub
	
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
			         
			        
			         stmt = c.createStatement();
			         ResultSet rs = stmt.executeQuery(sql);
			         while(rs.next()) {
		
			        	TerminModelTimeManagement terminModel = new TerminModelTimeManagement();
			        	terminModel.setId(rs.getInt("id"));
			        	terminModel.setDatum(rs.getString("datum"));
			        	terminModel.setStartUhrzeit(rs.getString("startuhrzeit"));
			        	terminModel.setEndUhrzeit(rs.getString("enduhrzeit"));
			        	terminModel.setIstWoechentlich(rs.getBoolean("istwoechentlich"));
			        	System.out.println(terminModel.isIstWoechentlich());
			        	terminModel.setStornierbarBis(rs.getInt("stornierbarbis"));
			        	terminModel.setBuchbarAb(rs.getInt("buchbarab"));
			        	terminModel.setBuchbarBis(rs.getInt("buchbarbis"));
			        	terminModel.setAktivitaetid(rs.getInt("aktivitaetid"));
			        	terminModel.setIstBuchbar(rs.getBoolean("istbuchbar"));
			        	terminModel.setIstStornierbar(rs.getBoolean("iststornierbar"));
			        	terminModel.setDauer(rs.getInt("dauer"));
			        	
			        	
			     	
			        	
			        	term.add(terminModel);
			         }
			         rs.close();
			         stmt.close();
			         c.close();
			         
			      } catch (Exception e) {
			         e.printStackTrace();
			         System.err.println(e.getClass().getName()+": "+e.getMessage());
			         System.exit(0);
			      }
			     
			    
		
	}
	
	public void erstelleNeuenTermin(TerminModelTimeManagement termin) {
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

	}
	
	

	
	public void updateBuchbar(int id) {
		System.out.println("speicherNeuenTermin");
		
		 Connection c = null;
	      PreparedStatement pstmt = null;
	      String sql = "UPDATE termin SET istbuchbar=? where id=?";
	      
	      try {
	         Class.forName("org.postgresql.Driver");
	         c = DriverManager
	            .getConnection("jdbc:postgresql://localhost:5432/Terminverwaltung",
	            		dbUser, dbPasswort);
	         
	         c.setAutoCommit(true);
	       
	        DateFormat timeformatter = new SimpleDateFormat("HH:MM:SS");
	        
	         pstmt = c.prepareStatement(sql);

	         pstmt.setBoolean(1, true);
	         pstmt.setInt(2,id);
	         
	         
	    
	         pstmt.executeUpdate();
	  
	         pstmt.close();
	         c.close();
	         
	      } catch (Exception e) {
	         e.printStackTrace();
	         System.err.println(e.getClass().getName()+": "+e.getMessage());
	         System.exit(0);
	      }

	}
	
	
	
}
