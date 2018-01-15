package model;

public class KursTerminModel {

	
	private String actionName;
	private int terminMitgliedId;
	
	
	public int getTerminMitgliedId() {
		return terminMitgliedId;
	}
	public void setTerminMitgliedId(int terminMitgliedId) {
		this.terminMitgliedId = terminMitgliedId;
	}

	
	private int terminId;
	private boolean bereitsgebucht;
	private boolean istBuchbar;
	
	
	public boolean isBereitsgebucht() {
		return bereitsgebucht;
	}
	public void setBereitsgebucht(boolean bereitsgebucht) {
		this.bereitsgebucht = bereitsgebucht;
	}
	public String getActionName() {
		return actionName;
	}
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	public boolean isIstBuchbar() {
		return istBuchbar;
	}
	public void setIstBuchbar(boolean istBuchbar) {
		this.istBuchbar = istBuchbar;
	}

	
	private String name;
	private String trainer;
	private String beschreibung;
	private String datum;
	private String startUhrzeit;
	private String endUhrzeit;
	
	public KursTerminModel() {
		
	}







	public int getTerminId() {
		return terminId;
	}



	public void setTerminId(int terminId) {
		this.terminId = terminId;
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
