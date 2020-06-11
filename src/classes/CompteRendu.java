package classes;

public class CompteRendu {
	public int numPraticien, numCompteRendu;
	public String date, numVisiteur;
	public String bilan;
	public String motif;

	public CompteRendu(String numVisiteur, int numCompteRendu, int numPraticien, String date, String bilan, String motif) {
		super();
		this.numVisiteur = numVisiteur;
		this.numCompteRendu = numCompteRendu;
		this.numPraticien = numPraticien;
		this.date = date;
		this.bilan = bilan;
		this.motif = motif;
	}

	public int getNumCompteRendu() {
		return numCompteRendu;
	}

	public void setNumCompteRendu(int numCompteRendu) {
		this.numCompteRendu = numCompteRendu;
	}

	public String getNumVisiteur() {
		return numVisiteur;
	}

	public void setNumVisiteur(String numVisiteur) {
		this.numVisiteur = numVisiteur;
	}

	public int getNumPraticien() {
		return numPraticien;
	}

	public void setNumPraticien(int numPraticien) {
		this.numPraticien = numPraticien;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getBilan() {
		return bilan;
	}

	public void setBilan(String bilan) {
		this.bilan = bilan;
	}

	public String getMotif() {
		return motif;
	}

	public void setMotif(String motif) {
		this.motif = motif;
	}
}
