package db_class;

import java.sql.*;
import java.util.ArrayList;

import classes.*;

public class DAOVisiteur {
	private Connection connection;

	public DAOVisiteur(Connection connection) {
		this.connection = connection;
	}
	
	public Visiteur getVisiteurByName(String id) {
		Visiteur v = null;
		
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * from visiteur WHERE VIS_NOM = ?");
			statement.setString(1, id);
			
			ResultSet resultat = statement.executeQuery();
			while (resultat.next())
				v = new Visiteur(resultat.getString("VIS_MATRICULE"), resultat.getString("VIS_NOM"),
						resultat.getString("Vis_PRENOM"), resultat.getString("VIS_ADRESSE"),
						resultat.getString("VIS_CP"), resultat.getString("VIS_VILLE"),
						resultat.getString("VIS_DATEEMBAUCHE"), resultat.getString("SEC_CODE"),
						resultat.getString("LAB_CODE"));
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		
		return v;
	}

	public Visiteur getVisiteur(String id) {
		Visiteur v = null;

		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * from visiteur WHERE VIS_MATRICULE = ?");
			statement.setString(1, id);

			ResultSet resultat = statement.executeQuery();

			while (resultat.next())
				v = new Visiteur(resultat.getString("VIS_MATRICULE"), resultat.getString("VIS_NOM"),
						resultat.getString("Vis_PRENOM"), resultat.getString("VIS_ADRESSE"),
						resultat.getString("VIS_CP"), resultat.getString("VIS_VILLE"),
						resultat.getString("VIS_DATEEMBAUCHE"), resultat.getString("SEC_CODE"),
						resultat.getString("LAB_CODE"));

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return v;
	}

	public ArrayList<Visiteur> getVisiteurs() {
		ArrayList<Visiteur> lesVisiteurs = new ArrayList<Visiteur>();
		Visiteur v = null;

		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * from visiteur");

			ResultSet resultat = statement.executeQuery();

			while (resultat.next()) {
				v = new Visiteur(resultat.getString("VIS_MATRICULE"), resultat.getString("VIS_NOM"),
						resultat.getString("Vis_PRENOM"), resultat.getString("VIS_ADRESSE"),
						resultat.getString("VIS_CP"), resultat.getString("VIS_VILLE"),
						resultat.getString("VIS_DATEEMBAUCHE"), resultat.getString("SEC_CODE"),
						resultat.getString("LAB_CODE"));
				lesVisiteurs.add(v);
				v = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lesVisiteurs;
	}
	
	
	public String getLaboratoireType(String code) {
		
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT LAB_NOM from labo WHERE LAB_CODE = ?");
			statement.setString(1, code);
			
			ResultSet rs = statement.executeQuery();
			while (rs.next())
				code = rs.getString("LAB_NOM");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return code;
		
	}
	
	public String getSecteurType (String code) {
		
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT SEC_LIBELLE FROM secteur WHERE SEC_CODE = ?");
			statement.setString(1, code);
			
			ResultSet rs = statement.executeQuery();
			while (rs.next())
				code = rs.getString("SEC_LIBELLE");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return code;
	}
	
	public boolean updateVisiteur(String code, String lastname, String name, String cp, String addr, String city) {
		boolean next = true;
		
		try {
			PreparedStatement statement = connection.prepareStatement("UPDATE visiteur SET VIS_NOM = ?, VIS_PRENOM = ?, VIS_ADRESSE = ?, VIS_CP = ?, VIS_VILLE = ? WHERE VIS_MATRICULE = ?");
			statement.setString(1, lastname);
			statement.setString(2, name);
			statement.setString(3, addr);
			statement.setString(4, cp);
			statement.setString(5, city);
			statement.setString(6, code);
			next = statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return next;
	}
}
