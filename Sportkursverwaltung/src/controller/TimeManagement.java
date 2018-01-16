package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.*;

import model.KursTerminModel;
import model.TerminModel;

@Stateless
public class TimeManagement {


	private String dbUser = "postgres";
	private String dbPasswort = "postgres";
	private List<KursTerminModel> trainerTermine = new ArrayList<KursTerminModel>();

	
	@Schedule(hour="*",minute="*",second="*/10",persistent=false)
	public void test() {
		ladeTrainerTermine();
		trainerTermine.size();
	       LocalDate localDate = LocalDate.now().minusDays(1);
	       DateTimeFormatter.ofPattern("yyy-MM-dd").format(localDate);
	       System.out.println("Current Date"+localDate.toString());
		for(int i=0;i<trainerTermine.size();i++) {
			String date1 = trainerTermine.get(i).getDatum();
			System.out.println(date1);
			if(date1.equals(localDate.toString())){
				System.out.println("Wrong order");
			}
		}
		trainerTermine.clear();
	}
	
	
	public void ladeTrainerTermine() {
		// TODO Auto-generated method stub
		System.out.println("ladeTrainerTermine");
				 Connection c = null;
			      Statement stmt = null;
			      String sql="SELECT a.name, a.trainer, a.beschreibung, a.teilnehmer, t.datum, t.startuhrzeit, t.enduhrzeit, t.id"
			      		+ ",t.buchbarab, t.buchbarbis, t.stornierbarbis"
			      		+ " FROM aktivitaet a INNER JOIN termin t ON a.id = t.aktivitaetid;";
			      
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
		
			        	KursTerminModel terminModel = new KursTerminModel();
			        	terminModel.setTerminId(rs.getInt("id"));
			        	terminModel.setName(rs.getString("name"));
			        	terminModel.setTrainer(rs.getString("trainer"));
			        	terminModel.setBeschreibung(rs.getString("beschreibung"));
			        	terminModel.setDatum(rs.getDate("datum").toString());
			        	terminModel.setStartUhrzeit(rs.getTime("startuhrzeit").toString());
			        	terminModel.setEndUhrzeit(rs.getTime("enduhrzeit").toString());
			        	terminModel.setStornierbarBis(rs.getInt("stornierbarbis"));
			        	terminModel.setBuchbarAb(rs.getInt("buchbarab"));
			        	terminModel.setBuchbarBis(rs.getInt("buchbarbis"));
			        	
			     	
			        	
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
	
	
	
	
}
