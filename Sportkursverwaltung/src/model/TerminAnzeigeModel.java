package model;

public class TerminAnzeigeModel {

	private String name;
	private String trainer;
	private String beschreibung;
	private String datum;
	private String startUhrzeit;
	private String endUhrzeit;
	private boolean istWoechentlich;
	private int buchbarAb;
	private int buchbarBis;
	private int stornierbarBis;
	
	public TerminAnzeigeModel() {
		
	}
	

	public String getDatum() {
		return datum;
	}
	public void setDatum(String datum) {
		this.datum = datum;
	}
	public String getEndUhrzeit() {
		return endUhrzeit;
	}
	public void setEndUhrzeit(String endUhrzeit) {
		this.endUhrzeit = endUhrzeit;
	}
	public boolean isIstWoechentlich() {
		return istWoechentlich;
	}
	public void setIstWoechentlich(boolean istWoechentlich) {
		this.istWoechentlich = istWoechentlich;
	}
	public String getStartUhrzeit() {
		return startUhrzeit;
	}
	public void setStartUhrzeit(String startUhrzeit) {
		this.startUhrzeit = startUhrzeit;
	}
	public int getBuchbarAb() {
		return buchbarAb;
	}
	public void setBuchbarAb(int buchbarAb) {
		this.buchbarAb = buchbarAb;
	}
	public int getBuchbarBis() {
		return buchbarBis;
	}
	public void setBuchbarBis(int buchbarBis) {
		this.buchbarBis = buchbarBis;
	}
	public int getStornierbarBis() {
		return stornierbarBis;
	}
	public void setStornierbarBis(int stornierbarBis) {
		this.stornierbarBis = stornierbarBis;
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
}
