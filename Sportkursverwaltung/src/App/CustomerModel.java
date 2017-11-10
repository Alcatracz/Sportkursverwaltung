package App;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CustomerModel {

	private Connection dbConnection;
	
	public CustomerModel(Connection dbConnection) {
		this.dbConnection = dbConnection;
	}
	
	public void addCustomer(Customer customer) {
		try {
			
			String sql = "INSERT INTO customer (firstname,lastname, email, password ) VALUES (?,?,?,?);";
			
			PreparedStatement stmt = dbConnection.prepareStatement(sql);
		
			stmt.setString(1, customer.getFirstname());
			stmt.setString(2, customer.getLastname());
			stmt.setString(3, customer.getEmail());
			stmt.setString(4, customer.getPassword());
			
			stmt.executeUpdate();
			stmt.close();
			
			dbConnection.commit();
			dbConnection.close();
			
			
		} catch(Exception e) {
			System.out.println("Fehler");
		}
	}
	
	public Customer getCustomer(String email) {	
		String sql = "SELECT * FROM customer WHERE email = "+email;
		
		Customer customer = new Customer();
		try {
			Statement stmt = dbConnection.createStatement();
			ResultSet ergebnis = stmt.executeQuery(sql);
			ergebnis.next();
			customer.setId(ergebnis.getInt("id"));
			customer.setFirstname(ergebnis.getString("firstname"));
			customer.setLastname(ergebnis.getString("lastname"));
			customer.setEmail(ergebnis.getString("email"));
			customer.setPassword(ergebnis.getString("password"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return customer;
	}
	
	public boolean isValidCustomer(String email, String password) {
		Customer customer = getCustomer(email);
		if(customer.getPassword().equals(password)) {
			return true;
		}
		return false;
	}
	
}
