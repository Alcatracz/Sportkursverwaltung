package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import model.KursListeTagModel;
import model.ProfilDatenModel;

public class AnotherTest {

	public static void main(String args[]) {
		
		List<KursListeTagModel> wochenListe = new ArrayList<KursListeTagModel> ();	
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_WEEK,calendar.getFirstDayOfWeek());
		
		for(int i = 1; i<=7; i++) {
			String tag= calendar.getTime().toString();
			System.out.println(tag);
			System.out.println(""+calendar.get(Calendar.DAY_OF_WEEK));
			calendar.add(Calendar.DATE, 1);
			
			KursListeTagModel kltm = new KursListeTagModel();
			kltm.setTag(tag);
			wochenListe.add(kltm);
		}
	}
}
