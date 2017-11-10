package App;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class ActivityModel {

	private Connection dbConnection;
	
	public ActivityModel(Connection dbConnection) {
		this.dbConnection = dbConnection;
	}
	
	public void addActivity(Activity activity) {
		try {
			
			String sql = "INSERT INTO activity (name, description, max_participants ) VALUES (?,?,?);";
			
			PreparedStatement stmt = dbConnection.prepareStatement(sql);
		
			stmt.setString(1, activity.getName());
			stmt.setString(2, activity.getDescription());
			stmt.setString(3, Integer.toString(activity.getMax_participants()));
			
			stmt.executeUpdate();
			stmt.close();
			
			dbConnection.commit();
			dbConnection.close();
			
			
		} catch(Exception e) {
			System.out.println("Fehler");
		}
	}
	
	public Activity getActivity(String activityName) {	
		String sql = "SELECT * FROM activity WHERE activityName = "+activityName;
		
		Activity activity = new Activity();
		try {
			Statement stmt = dbConnection.createStatement();
			ResultSet ergebnis = stmt.executeQuery(sql);

			activity.setId(ergebnis.getInt("activityID"));
			activity.setName(ergebnis.getString("activityName"));
			activity.setDescription(ergebnis.getString("activityDescription"));
			activity.setMax_participants(ergebnis.getInt("activityMaxParticipants"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return activity;
	}
	
	
}
