package interfaces;

import model.KursTerminModel;

public interface KurseControllerInterface {

	public void ladeTermine();
	
	public String toggleButton(KursTerminModel termin);
	public void teilnehmen(KursTerminModel termin);
	public void absagen(KursTerminModel termin);
	
}
