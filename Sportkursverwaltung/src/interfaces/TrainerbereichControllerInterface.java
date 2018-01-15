package interfaces;

public interface TrainerbereichControllerInterface {

	public String generatePasswort ();
	
	public void ladeMitglieder ();
	public void ladeAktivitaeten ();
	public void ladeTermine ();
	
	public String speicherNeuesMitglied ();
	public String loescheMitglied ();
	
	public String speicherNeueAktivitaet ();
	public String loescheAktivitaet ();
	
	public String speicherNeuenTermin ();
	public String loescheTermin ();
}
