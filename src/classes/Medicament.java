package classes;

public class Medicament {
	public String depotlegal, nom, famille_code, composition, effets, contre_indication, prix_echantillon;

	public Medicament(String depotlegal, String nom, String famille_code, String composition, String effets,
			String contre_indication, String prix_echantillon) {
		super();
		this.depotlegal = depotlegal;
		this.nom = nom;
		this.famille_code = famille_code;
		this.composition = composition;
		this.effets = effets;
		this.contre_indication = contre_indication;
		this.prix_echantillon = prix_echantillon;
	}

	public String getDepotlegal() {
		return depotlegal;
	}

	public void setDepotlegal(String depotlegal) {
		this.depotlegal = depotlegal;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getFamille_code() {
		return famille_code;
	}

	public void setFamille_code(String famille_code) {
		this.famille_code = famille_code;
	}

	public String getComposition() {
		return composition;
	}

	public void setComposition(String composition) {
		this.composition = composition;
	}

	public String getEffets() {
		return effets;
	}

	public void setEffets(String effets) {
		this.effets = effets;
	}

	public String getContre_indication() {
		return contre_indication;
	}

	public void setContre_indication(String contre_indication) {
		this.contre_indication = contre_indication;
	}

	public String getPrix_echantillon() {
		return prix_echantillon;
	}

	public void setPrix_echantillon(String prix_echantillon) {
		this.prix_echantillon = prix_echantillon;
	}

}
