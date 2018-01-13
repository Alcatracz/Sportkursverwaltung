package model;

public class TerminModel {

	private String datum;
	private String startUhrzeit;
	private String endUhrzeit;
	private boolean istWoechentlich;
	private int buchbarAb;
	private int buchbarBis;
	private int stornierbarBis;
	
	public TerminModel() {
		
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
}