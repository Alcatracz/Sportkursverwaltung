package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import model.KursListeTagModel;
import model.ProfilDatenModel;

public class AnotherTest {

	public static void main(String args[]) {
		Time t;
		DateFormat timeformatter = new SimpleDateFormat("HH:MM:SS");
	      
		try {
			
			t = new Time(timeformatter.parse("11:00:00").getTime());
			
			System.out.println(t.toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      
		}
	}

