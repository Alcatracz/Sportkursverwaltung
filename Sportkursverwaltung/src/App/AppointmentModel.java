package App;

import java.sql.Connection;

public class AppointmentModel {

	private Connection dbConnection;
	
	public AppointmentModel(Connection dbConnection) {
		this.dbConnection = dbConnection;
	}
}
