package classes;

public class Visiteur {
	private String matricule, nom, prenom, adresse, cp, ville, dateEmbauche, codeSection, codeLaboratoire;

	public Visiteur(String matricule, String nom, String prenom, String adresse, String cp, String ville, String dateEmbauche, String codeSection, String codeLaboratoire) {
		super();
		this.matricule = matricule;
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.cp = cp;
		this.ville = ville;
		this.dateEmbauche = dateEmbauche;
		this.codeSection = codeSection;
		this.codeLaboratoire = codeLaboratoire;
	}

	public String getMatricule() {
		return matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getDateEmbauche() {
		return dateEmbauche;
	}

	public void setDateEmbauche(String dateEmbauche) {
		this.dateEmbauche = dateEmbauche;
	}

	public String getCodeSection() {
		return codeSection;
	}

	public void setCodeSection(String codeSection) {
		this.codeSection = codeSection;
	}

	public String getCodeLaboratoire() {
		return codeLaboratoire;
	}

	public void setCodeLaboratoire(String codeLaboratoire) {
		this.codeLaboratoire = codeLaboratoire;
	}
	
	
	
}
