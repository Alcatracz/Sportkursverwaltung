package entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "Terminliste" database table.
 * 
 */
@Entity
@Table(name="\"Terminliste\"")
@NamedQuery(name="Terminliste.findAll", query="SELECT t FROM Terminliste t")
public class Terminliste implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="\"ID\"")
	private Long id;

	@Column(name="\"MitgliedID\"")
	private Long mitgliedID;

	@Column(name="\"TerminID\"")
	private Long terminID;

	public Terminliste() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMitgliedID() {
		return this.mitgliedID;
	}

	public void setMitgliedID(Long mitgliedID) {
		this.mitgliedID = mitgliedID;
	}

	public Long getTerminID() {
		return this.terminID;
	}

	public void setTerminID(Long terminID) {
		this.terminID = terminID;
	}

}