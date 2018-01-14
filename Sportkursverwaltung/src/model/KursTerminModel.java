package model;

public class KursTerminModel {

	private int id;
	private String name;
	private String trainer;
	private String beschreibung;
	private String datum;
	private String startUhrzeit;
	private String endUhrzeit;
	
	public KursTerminModel() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTrainer() {
		return trainer;
	}

	public void setTrainer(String trainer) {
		this.trainer = trainer;
	}

	public String getBeschreibung() {
		return beschreibung;
	}

	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}

	public String getDatum() {
		return datum;
	}

	public void setDatum(String datum) {
		this.datum = datum;
	}

	public String getStartUhrzeit() {
		return startUhrzeit;
	}

	public void setStartUhrzeit(String startUhrzeit) {
		this.startUhrzeit = startUhrzeit;
	}

	public String getEndUhrzeit() {
		return endUhrzeit;
	}

	public void setEndUhrzeit(String endUhrzeit) {
		this.endUhrzeit = endUhrzeit;
	}
	
}
