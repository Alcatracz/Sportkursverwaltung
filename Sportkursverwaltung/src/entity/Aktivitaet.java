package entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "Aktivitaet" database table.
 * 
 */
@Entity
@Table(name="\"Aktivitaet\"")
@NamedQuery(name="Aktivitaet.findAll", query="SELECT a FROM Aktivitaet a")
public class Aktivitaet implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="\"ID\"")
	private Long id;

	@Column(name="\"Beschreibung\"")
	private String beschreibung;

	@Column(name="\"Name\"")
	private String name;

	@Column(name="\"Teilnehmer\"")
	private Integer teilnehmer;

	@Column(name="\"Trainer\"")
	private String trainer;

	public Aktivitaet() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBeschreibung() {
		return this.beschreibung;
	}

	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getTeilnehmer() {
		return this.teilnehmer;
	}

	public void setTeilnehmer(Integer teilnehmer) {
		this.teilnehmer = teilnehmer;
	}

	public String getTrainer() {
		return this.trainer;
	}

	public void setTrainer(String trainer) {
		this.trainer = trainer;
	}

}