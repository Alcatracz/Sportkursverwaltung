package time;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.*;

import model.AktivitaetModel;
import model.KursTerminModel;
import model.TerminModelTimeManagement;

@Singleton
@LocalBean
public class TimeManagement {

	private String dbPfad = "localhost:5432/Terminverwaltung";
	private String dbUser = "postgres";
	private String dbPasswort = "postgres";
	
	private DateFormat format = new SimpleDateFormat("dd.mm.yyyy");
	ZoneId defaultZoneId = ZoneId.systemDefault();
	LocalDate localDate;
	
	@Schedule(hour="*",minute="*",second="*/10",persistent=false)
	public void test() {
		//System.out.println("test()");
		
		List<TerminModelTimeManagement> termine = ladeTermine();

	    
	    DateTimeFormatter formatter= DateTimeFormatter.ofPattern("dd.LLLL.yyyy");


		for(int i=0;i<termine.size();i++) {
			
			try {
				Date terminDate = format.parse(termine.get(i).getDatum());
				Instant instant = terminDate.toInstant();
				localDate = instant.atZone(defaultZoneId).toLocalDate();


			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			LocalDate currentdate = LocalDate.now();
			LocalDate buchbarabdate = localDate.minusDays(termine.get(i).getBuchbarAb());
			LocalDate buchbarbisdate = localDate.minusDays(termine.get(i).getBuchbarBis());
			LocalDate stornierbarbisdate = localDate.minusDays(termine.get(i).getStornierbarBis());
			
			String formatedcurrdate = currentdate.format(formatter);
			String formatedabdate = buchbarabdate.format(formatter);
			String formatedbisdate = buchbarbisdate.format(formatter);
			String formaetdstodate = stornierbarbisdate.format(formatter);
			
			//System.out.println("ENDE: "+formatedcurrdate );
			
			try {
				Date current = format.parse(formatedcurrdate);
				Date buchBarAb = format.parse(formatedabdate);
				Date buchBarBis = format.parse(formatedbisdate);
				Date stornierbarBis = format.parse(formaetdstodate);
				//System.out.println("current"+current);
				//System.out.println("buchBarAb"+buchBarAb);
				//System.out.println("buchBarBis"+buchBarBis);
				//System.out.println("stornierbarBis"+stornierbarBis);
				
				if (current.before(buchBarBis) && current.after(buchBarAb)) {
					updateTermin (termine.get(i),"istbuchbar",true);
				} else {
					updateTermin (termine.get(i),"istbuchbar",false);
				}
				if (current.after(stornierbarBis)) {
					updateTermin (termine.get(i),"iststornierbar",false);
				}
				

			} catch (Exception e) {
				e.printStackTrace();
			}
		
			

			
			
		}
		
		termine.clear();
		
	}
	

	
	private void loescheAltenTermin(TerminModelTimeManagement termin) {
		Connection c = null;
	    PreparedStatement pstmt = null;
	    String sql = "DELETE FROM termin WHERE id=?";
	    
	    try {
	    	Class.forName("org.postgresql.Driver");
	        c = DriverManager.getConnection("jdbc:postgresql://" + dbPfad, dbUser, dbPasswort);
	        c.setAutoCommit(true);
	        
	        pstmt = c.prepareStatement(sql);
	        pstmt.setInt(1, termin.getId());
	        pstmt.executeUpdate();
	      
	        String delete ="DELETE FROM terminliste WHERE terminid=?";
	        pstmt =c.prepareStatement(delete);
	        pstmt.setInt(1, termin.getId());
	        pstmt.executeUpdate();
	        
	        pstmt.close();	      
	        c.close();
	         
	      } catch (Exception e) {
	    	  e.printStackTrace();
	    	  System.err.println(e.getClass().getName()+": "+e.getMessage());
	    	  System.exit(0);
	      }
	}


	public List<TerminModelTimeManagement> ladeTermine() {
		
		List<TerminModelTimeManagement> termine = new ArrayList<TerminModelTimeManagement>();
		Connection c = null;
		PreparedStatement pstmt = null;
		String sql="SELECT  t.datum, t.startuhrzeit, t.enduhrzeit,t.id,t.istwoechentlich"
			      		+ ",t.buchbarab, t.buchbarbis, t.stornierbarbis, "
			      		+ "t.aktivitaetid,t.istbuchbar,t.iststornierbar,t.dauer"
			      		+ " FROM termin t;";
			      
		try {//
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://" + dbPfad, dbUser, dbPasswort);
			c.setAutoCommit(false);
			         
			pstmt = c.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				TerminModelTimeManagement terminModel = new TerminModelTimeManagement();
			    terminModel.setId(rs.getInt("id"));
			    terminModel.setDatum(rs.getString("datum"));
			    terminModel.setStartUhrzeit(rs.getString("startuhrzeit"));
			    terminModel.setEndUhrzeit(rs.getString("enduhrzeit"));
			    terminModel.setIstWoechentlich(rs.getBoolean("istwoechentlich"));
			    terminModel.setStornierbarBis(rs.getInt("stornierbarbis"));
			    terminModel.setBuchbarAb(rs.getInt("buchbarab"));
			    terminModel.setBuchbarBis(rs.getInt("buchbarbis"));
			    terminModel.setAktivitaetid(rs.getInt("aktivitaetid"));
			    terminModel.setIstBuchbar(rs.getBoolean("istbuchbar"));
			    terminModel.setIstStornierbar(rs.getBoolean("iststornierbar"));
			    terminModel.setDauer(rs.getInt("dauer"));
			        	
			    termine.add(terminModel);
			    
			}
			rs.close();
			pstmt.close();
			c.close();
			} catch (Exception e) {
				e.printStackTrace();
			    System.err.println(e.getClass().getName()+": "+e.getMessage());
			    System.exit(0);
			    
			}
		return termine;
	}
	
	public void erstelleNeuenTermin(TerminModelTimeManagement termin) {
		Connection c = null;
	    PreparedStatement pstmt = null;
	    String sql = "INSERT INTO termin (startuhrzeit,enduhrzeit,datum,istwoechentlich,"
	    		+ "buchbarab,buchbarbis,stornierbarbis,aktivitaetid,istbuchbar,iststornierbar,dauer)"
	      		+ " VALUES (?,?,?,?,?,?,?,?,?,?,?);";
	    
	    try {
	    	Class.forName("org.postgresql.Driver");
	        c = DriverManager.getConnection("jdbc:postgresql://" + dbPfad, dbUser, dbPasswort);
	        c.setAutoCommit(true);
	        
	        LocalDate localDate = LocalDate.now();
	        localDate.plusDays(14);
	        
	        DateTimeFormatter formatter= DateTimeFormatter.ofPattern("dd.llll.yyyy");
		    String formatedldate=localDate.format(formatter);
		    
	        pstmt = c.prepareStatement(sql);
	        pstmt.setString(1, termin.getStartUhrzeit());
	        pstmt.setString(2, termin.getEndUhrzeit());
	        pstmt.setString(3, formatedldate );
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
	
	

	
	public void updateTermin(TerminModelTimeManagement termin,String attribute, boolean state) {
		Connection c = null;
	    PreparedStatement pstmt = null;
	    String sql = "UPDATE termin SET "+attribute+"=? where id=?";
	    
	    try {
	    	Class.forName("org.postgresql.Driver");
	        c = DriverManager.getConnection("jdbc:postgresql://"+dbPfad, dbUser, dbPasswort);
	        c.setAutoCommit(true);
	        
	        pstmt = c.prepareStatement(sql);
	        pstmt.setBoolean(1, state);
	        pstmt.setInt(2,termin.getId());
	        
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
