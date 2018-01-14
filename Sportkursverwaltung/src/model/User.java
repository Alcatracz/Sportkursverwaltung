package model;

public class User {
    private String email;
    private String passwort;
    private boolean istTrainer;
    private int id;
    private boolean istAuthentifiziert = false;

    public User() {
    	istAuthentifiziert=false;
    }
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


}
