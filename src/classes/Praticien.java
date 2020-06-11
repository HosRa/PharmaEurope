package classes;

public class Praticien {
	private int num;
	private String nom, prenom, adresse, cp, ville,  typeCode;
	private float coefNotoriete;
	
	public Praticien(int num, String nom, String prenom, String adresse, String cp, String ville, float coefNotoriete,
			String typeCode) {
		super();
		this.num = num;
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.cp = cp;
		this.ville = ville;
		this.coefNotoriete = coefNotoriete;
		this.typeCode = typeCode;
	}

	public Praticien() {
		// TODO Auto-generated constructor stub
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
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

	public float getCoefNotoriete() {
		return coefNotoriete;
	}

	public void setCoefNotoriete(float coefNotoriete) {
		this.coefNotoriete = coefNotoriete;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	
	
}
