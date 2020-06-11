package db_class;

import java.sql.*;
import java.util.ArrayList;

import classes.*;

public class DAOPraticien {
	private Connection connection;

	public DAOPraticien(Connection connection) {
		this.connection = connection;
	}
	
	
	public Praticien getPraticien(int id) {
		Praticien p = null;
		
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * from praticien WHERE PRA_NUM = ?");
			statement.setInt(1, id);
			
			ResultSet resultat = statement.executeQuery();
			
			while (resultat.next())
				p = new Praticien(resultat.getInt("PRA_NUM"), resultat.getString("PRA_NOM"), resultat.getString("PRA_PRENOM"), resultat.getString("PRA_ADRESSE"), 
						resultat.getString("PRA_CP"), resultat.getString("PRA_VILLE"), resultat.getFloat("PRA_COEFNOTORIETE"), resultat.getString("TYP_CODE"));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return p;
		
	}
	
	public int getIdPraticien(String nom) {
		int numPraticien = 0;
		PreparedStatement s;
		try {
			s = connection.prepareStatement("SELECT PRA_NUM FROM praticien WHERE PRA_NOM = ?");
			
			s.setString(1, nom);
			
			ResultSet rs = s.executeQuery();
			while (rs.next())
				 numPraticien = rs.getInt("PRA_NUM");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return numPraticien;
	}
	
	public ArrayList<Praticien> getPraticiens() {
		ArrayList<Praticien> lesPraticiens = new ArrayList<Praticien>();
		Praticien p = null;
		
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM praticien");
			
			ResultSet resultat = statement.executeQuery();
			
			while (resultat.next()) {
				p = new Praticien(resultat.getInt("PRA_NUM"), resultat.getString("PRA_NOM"), resultat.getString("PRA_PRENOM"), resultat.getString("PRA_ADRESSE"), 
						resultat.getString("PRA_CP"), resultat.getString("PRA_VILLE"), resultat.getFloat("PRA_COEFNOTORIETE"), resultat.getString("TYP_CODE"));
				lesPraticiens.add(p);
				p = null;
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return lesPraticiens;
	}
	
	public String getPraticienType(String id) {
		String res = "";
		String type = "";
		
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT TYP_CODE from praticien WHERE PRA_NUM = ?");
			statement.setString(1, id);
			
			ResultSet rs = statement.executeQuery();
			while (rs.next())
				type = rs.getString("TYP_CODE");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			PreparedStatement s = connection.prepareStatement("SELECT TYP_LIBELLE FROM type_praticien WHERE TYP_CODE = ?");
			s.setString(1, type);
			
			ResultSet resultat = s.executeQuery();
			while (resultat.next())
				res = resultat.getString("TYP_LIBELLE");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return res;
	}
}
