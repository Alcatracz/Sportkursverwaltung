package interfaces;

import model.AktivitaetModel;
import model.KursTerminModel;
import model.MitgliedModel;
import model.TerminModel;

public interface TrainerbereichControllerInterface {

	public String generatePasswort ();
	
	public void ladeMitglieder ();
	public void ladeAktivitaeten ();
	public void ladeTermine (int id);
	
	public String speicherNeuesMitglied ();
	public String loescheMitglied (MitgliedModel mitglied);
	
	public String speicherNeueAktivitaet ();
	public String loescheAktivitaet (AktivitaetModel aktivitaet);
	
	public String speicherNeuenTermin (AktivitaetModel aktivitaet);
	public String loescheTermin (TerminModel termin);
	
	public void ladeTrainerTermine();
	public void ladeTerminDetails(KursTerminModel termin);
}
