package model;

public class TerminModelTimeManagement {
	
	private String actionName;
	private boolean gesperrt;
	
	private int id;	
	private String name;
	private String trainer;
	private String beschreibung;
	private String datum;
	private String startUhrzeit;
	private String endUhrzeit;
	private int buchbarAb=14;
	private int buchbarBis=1;
	private int stornierbarBis=1;
	private boolean istBuchbar;
	private boolean istStornierbar;
	private boolean istWoechentlich;
	private int aktivitaetid;
	private int dauer;
	
	public TerminModelTimeManagement() {
		this.buchbarAb = 14;
		this.buchbarBis = 1;
		this.stornierbarBis = 1;
	}
	
	
	//------------------------------------------------------------------
	//------------GETTER UND SETTER-------------------------------------
	//------------------------------------------------------------------

	public String getActionName() {
		return actionName;
	}
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	public boolean isGesperrt() {
		return gesperrt;
	}
	public void setGesperrt(boolean gesperrt) {
		this.gesperrt = gesperrt;
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
	public boolean isIstBuchbar() {
		return istBuchbar;
	}
	public void setIstBuchbar(boolean istBuchbar) {
		this.istBuchbar = istBuchbar;
	}
	public boolean isIstStornierbar() {
		return istStornierbar;
	}
	public void setIstStornierbar(boolean istStornierbar) {
		this.istStornierbar = istStornierbar;
	}
	public int getDauer() {
		return dauer;
	}
	public void setDauer(int dauer) {
		this.dauer = dauer;
	}
	public boolean isIstWoechentlich() {
		return istWoechentlich;
	}
	public void setIstWoechentlich(boolean istWoechentlich) {
		this.istWoechentlich = istWoechentlich;
	}
	public int getAktivitaetid() {
		return aktivitaetid;
	}
	public void setAktivitaetid(int aktivitaetid) {
		this.aktivitaetid = aktivitaetid;
	}	
}
