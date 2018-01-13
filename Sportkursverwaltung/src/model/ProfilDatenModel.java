package model;

public class ProfilDatenModel {

	private String vorname;
	private String nachname;
	private String email;
	private String passwort;
	private boolean istTrainer;
	private boolean istBuchungsbestaetigung;
	private boolean istTerminerinnerung;
	private int terminerinnerungZeit;
	
	public ProfilDatenModel() {
		
	}
	
	public String getVorname() {
		return vorname;
	}
	public void setVorname(String vorname) {
		this.vorname = vorname;
	}
	public String getNachname() {
		return nachname;
	}
	public void setNachname(String nachname) {
		this.nachname = nachname;
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
	public int getTerminerinnerungZeit() {
		return terminerinnerungZeit;
	}
	public void setTerminerinnerungZeit(int terminerinnerungZeit) {
		this.terminerinnerungZeit = terminerinnerungZeit;
	}
}
