package model;

import java.util.ArrayList;
import java.util.List;

public class KursListeTagModel {

	private String tag;
	private List<KursTerminModel> termine;
	
	public KursListeTagModel() {
		termine = new ArrayList<KursTerminModel>();
	}
	
	public void add(KursTerminModel termin) {
		termine.add(termin);
	}
	
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public List<KursTerminModel> getTermine() {
		return termine;
	}
	public void setTermine(List<KursTerminModel> termine) {
		this.termine = termine;
	}
	
}
