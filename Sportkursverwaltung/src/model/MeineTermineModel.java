package model;

public class MeineTermineModel {

	private int id;
	private String name;
	private String trainer;
	private String beschreibung;
	private String datum;
	private String startUhrzeit;
	private String endUhrzeit;
	private boolean stornierbar;
	private boolean gesperrt;
	private String actionName;
	
	public MeineTermineModel() {
		
	}

	
	//------------------------------------------------------------------
	//------------GETTER UND SETTER-------------------------------------
	//------------------------------------------------------------------
	
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
	public boolean isGesperrt() {
		return gesperrt;
	}
	public void setGesperrt(boolean gesperrt) {
		this.gesperrt = gesperrt;
	}
	public String getActionName() {
		return actionName;
	}
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	public boolean isStornierbar() {
		return stornierbar;
	}
	public void setStornierbar(boolean stornierbar) {
		this.stornierbar = stornierbar;
	}
}
