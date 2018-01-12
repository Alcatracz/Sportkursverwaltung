package entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "Mitglied" database table.
 * 
 */
@Entity
@Table(name="\"Mitglied\"")
@NamedQuery(name="Mitglied.findAll", query="SELECT m FROM Mitglied m")
public class Mitglied implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="\"ID\"")
	private Long id;

	@Column(name="\"Email\"")
	private String email;

	@Column(name="\"IstBuchungsbestaetigung\"")
	private Boolean istBuchungsbestaetigung;

	@Column(name="\"IstTerminerinnerung\"")
	private Boolean istTerminerinnerung;

	@Column(name="\"IstTrainer\"")
	private Boolean istTrainer;

	@Column(name="\"Nachname\"")
	private String nachname;

	@Column(name="\"Passwort\"")
	private String passwort;

	@Column(name="\"TerminerinnerungZeit\"")
	private Integer terminerinnerungZeit;

	@Column(name="\"Vorname\"")
	private String vorname;

	public Mitglied() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getIstBuchungsbestaetigung() {
		return this.istBuchungsbestaetigung;
	}

	public void setIstBuchungsbestaetigung(Boolean istBuchungsbestaetigung) {
		this.istBuchungsbestaetigung = istBuchungsbestaetigung;
	}

	public Boolean getIstTerminerinnerung() {
		return this.istTerminerinnerung;
	}

	public void setIstTerminerinnerung(Boolean istTerminerinnerung) {
		this.istTerminerinnerung = istTerminerinnerung;
	}

	public Boolean getIstTrainer() {
		return this.istTrainer;
	}

	public void setIstTrainer(Boolean istTrainer) {
		this.istTrainer = istTrainer;
	}

	public String getNachname() {
		return this.nachname;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	public String getPasswort() {
		return this.passwort;
	}

	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}

	public Integer getTerminerinnerungZeit() {
		return this.terminerinnerungZeit;
	}

	public void setTerminerinnerungZeit(Integer terminerinnerungZeit) {
		this.terminerinnerungZeit = terminerinnerungZeit;
	}

	public String getVorname() {
		return this.vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

}