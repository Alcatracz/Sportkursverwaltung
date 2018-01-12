package entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Time;
import java.util.Date;


/**
 * The persistent class for the "Termin" database table.
 * 
 */
@Entity
@Table(name="\"Termin\"")
@NamedQuery(name="Termin.findAll", query="SELECT t FROM Termin t")
public class Termin implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="\"ID\"")
	private Long id;

	@Column(name="\"BuchbarAb\"")
	private Integer buchbarAb;

	@Column(name="\"BuchbarBis\"")
	private Integer buchbarBis;

	@Temporal(TemporalType.DATE)
	@Column(name="\"Datum\"")
	private Date datum;

	@Column(name="\"EndUhrzeit\"")
	private Time endUhrzeit;

	@Column(name="\"IstWoechentlich\"")
	private Boolean istWoechentlich;

	@Column(name="\"StartUhrzeit\"")
	private Time startUhrzeit;

	@Column(name="\"StornierbarBis\"")
	private Integer stornierbarBis;

	public Termin() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getBuchbarAb() {
		return this.buchbarAb;
	}

	public void setBuchbarAb(Integer buchbarAb) {
		this.buchbarAb = buchbarAb;
	}

	public Integer getBuchbarBis() {
		return this.buchbarBis;
	}

	public void setBuchbarBis(Integer buchbarBis) {
		this.buchbarBis = buchbarBis;
	}

	public Date getDatum() {
		return this.datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public Time getEndUhrzeit() {
		return this.endUhrzeit;
	}

	public void setEndUhrzeit(Time endUhrzeit) {
		this.endUhrzeit = endUhrzeit;
	}

	public Boolean getIstWoechentlich() {
		return this.istWoechentlich;
	}

	public void setIstWoechentlich(Boolean istWoechentlich) {
		this.istWoechentlich = istWoechentlich;
	}

	public Time getStartUhrzeit() {
		return this.startUhrzeit;
	}

	public void setStartUhrzeit(Time startUhrzeit) {
		this.startUhrzeit = startUhrzeit;
	}

	public Integer getStornierbarBis() {
		return this.stornierbarBis;
	}

	public void setStornierbarBis(Integer stornierbarBis) {
		this.stornierbarBis = stornierbarBis;
	}

}