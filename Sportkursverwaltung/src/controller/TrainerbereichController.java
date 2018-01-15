package controller;

import java.util.List;

import interfaces.TrainerbereichControllerInterface;
import model.MitgliedModel;
import model.AktivitaetModel;
import model.TerminModel;
import model.TrainerTermineModel;

public class TrainerbereichController implements TrainerbereichControllerInterface {

	private List<MitgliedModel> mitglieder;
	private MitgliedModel mitglied;
	
	private List<AktivitaetModel> aktivitaeten;
	private AktivitaetModel aktivitaet;
	
	private List<TerminModel> termine;
	private TerminModel termin;
	
	private List<TrainerTermineModel> trainerTermine;
	private TrainerTermineModel trainerTermin;
	
	public TrainerbereichController () {
		
	}
	
	@Override
	public String generatePasswort() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void ladeMitglieder() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ladeAktivitaeten() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ladeTermine() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String speicherNeuesMitglied() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String loescheMitglied() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String speicherNeueAktivitaet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String loescheAktivitaet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String speicherNeuenTermin() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String loescheTermin() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public List<MitgliedModel> getMitglieder() {
		return mitglieder;
	}

	public void setMitglieder(List<MitgliedModel> mitglieder) {
		this.mitglieder = mitglieder;
	}

	public MitgliedModel getMitglied() {
		return mitglied;
	}

	public void setMitglied(MitgliedModel mitglied) {
		this.mitglied = mitglied;
	}

	public List<AktivitaetModel> getAktivitaeten() {
		return aktivitaeten;
	}

	public void setAktivitaeten(List<AktivitaetModel> aktivitaeten) {
		this.aktivitaeten = aktivitaeten;
	}

	public AktivitaetModel getAktivitaet() {
		return aktivitaet;
	}

	public void setAktivitaet(AktivitaetModel aktivitaet) {
		this.aktivitaet = aktivitaet;
	}

	public List<TerminModel> getTermine() {
		return termine;
	}

	public void setTermine(List<TerminModel> termine) {
		this.termine = termine;
	}

	public TerminModel getTermin() {
		return termin;
	}

	public void setTermin(TerminModel termin) {
		this.termin = termin;
	}

	public List<TrainerTermineModel> getTrainerTermine() {
		return trainerTermine;
	}

	public void setTrainerTermine(List<TrainerTermineModel> trainerTermine) {
		this.trainerTermine = trainerTermine;
	}

	public TrainerTermineModel getTrainerTermin() {
		return trainerTermin;
	}

	public void setTrainerTermin(TrainerTermineModel trainerTermin) {
		this.trainerTermin = trainerTermin;
	}

}
