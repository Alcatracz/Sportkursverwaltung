package model;

public class Customer {

	private String id;
	private String firstname;
	private String lastname;
	private String passwort;
	private String email;
	
	public Customer(String firstname,String lastname,String passwort,String email) {
		this.firstname=firstname;
		this.lastname=lastname;
		this.passwort=passwort;
		this.email=email;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getPasswort() {
		return passwort;
	}
	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
