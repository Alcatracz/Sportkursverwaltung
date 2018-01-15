package model;

public class AktivitaetModel {

	private int id;
	private String name;
	private String trainer;
	private String beschreibung;
	private int teilnehmer;
	
	public AktivitaetModel() {
		
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

	public int getTeilnehmer() {
		return teilnehmer;
	}

	public void setTeilnehmer(int teilnehmer) {
		this.teilnehmer = teilnehmer;
	}
	
	
}
