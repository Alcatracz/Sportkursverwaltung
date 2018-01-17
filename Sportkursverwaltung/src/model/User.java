package model;

public class User {
    private String email;
    private String name;
    private String passwort;
    private boolean istTrainer;
    private int id;
    private boolean istAuthentifiziert = false;
    private boolean istBuchungsbestaetigung;
    private boolean istTerminerinnerung;

    public User() {
    	istAuthentifiziert=false;
    }
    
    public void logout() {
    	this.email = null;
    	this.passwort = null;
    	this.istTrainer = false;
    	this.id = 0;
    	this.istAuthentifiziert = false;  	
    }
    
    
	//------------------------------------------------------------------
	//------------GETTER UND SETTER-------------------------------------
	//------------------------------------------------------------------
    
    public boolean isIstAuthentifiziert() {
		return istAuthentifiziert;
	}
	public void setIstAuthentifiziert(boolean istAuthentifiziert) {
		this.istAuthentifiziert = istAuthentifiziert;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
    public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPasswort() {
        return passwort;
    }
    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }
	public boolean isIstTrainer() {
		return istTrainer;
	}
	public void setIstTrainer(boolean istTrainer) {
		this.istTrainer = istTrainer;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isIstBuchungsbestaetigung() {
		return istBuchungsbestaetigung;
	}
	public void setIstBuchungsbestaetigung(boolean istBuchungsbestaetigung) {
		this.istBuchungsbestaetigung = istBuchungsbestaetigung;
	}
	public boolean isIstTerminerinnerung() {
		return istTerminerinnerung;
	}
	public void setIstTerminerinnerung(boolean istTerminerinnerung) {
		this.istTerminerinnerung = istTerminerinnerung;
	}
}
