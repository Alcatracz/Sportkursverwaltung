package interfaces;

import model.AktivitaetModel;
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
	
	public String speicherNeuenTermin (int id);
	public String loescheTermin (TerminModel termin);
}
