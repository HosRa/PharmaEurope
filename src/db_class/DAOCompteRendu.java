package db_class;

import java.sql.*;
import java.util.*;
import java.util.Date;

import ui.*;
import classes.*;
import exec.*;

public class DAOCompteRendu {

	private Connection connection;

	public DAOCompteRendu(Connection connection) {
		this.connection = connection;
	}
	
	public CompteRendu getCompteRendu(int id) {
		CompteRendu c = null;
		
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM rapport_visite WHERE RAP_NUM = ?");
			statement.setInt(1, id);
			
			ResultSet resultat = statement.executeQuery();
			
			while (resultat.next())
				c = new CompteRendu(resultat.getString("VIS_MATRICULE"), resultat.getInt("RAP_NUM"), resultat.getInt("PRA_NUM"), resultat.getString("RAP_DATE"),
						resultat.getString("RAP_BILAN"), resultat.getString("RAP_MOTIF"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return c;
	}
	
	public ArrayList<CompteRendu> getAllComptesRendus() {
		ArrayList<CompteRendu> lesComptesRendus = new ArrayList<CompteRendu>();
		CompteRendu c = null;
		
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * from rapport_visite");
			
			ResultSet resultat = statement.executeQuery();

			while (resultat.next()) {
				c = new CompteRendu(resultat.getString("VIS_MATRICULE"), resultat.getInt("RAP_NUM"), resultat.getInt("PRA_NUM"), resultat.getString("RAP_DATE"),
						resultat.getString("RAP_BILAN"), resultat.getString("RAP_MOTIF"));
				lesComptesRendus.add(c);
				c = null;
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return lesComptesRendus;
	}
	
	public boolean updateCompteRendu(String nom, java.util.Date date, String bilan, String motif, int num) {
		int numPraticien = 0;
		java.sql.Date date_sql = new java.sql.Date(date.getTime());
		boolean next = true;
		
		PreparedStatement s;
		try {
			s = connection.prepareStatement("SELECT PRA_NUM FROM praticien WHERE PRA_NOM = ?");
			
			s.setInt(1, num);
			
			ResultSet rs = s.executeQuery();
			while (rs.next())
				 numPraticien = rs.getInt("PRA_NUM");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		try {
			PreparedStatement statement = connection.prepareStatement("UPDATE rapport_visite SET RAP_DATE = ?, RAP_BILAN = ?, RAP_MOTIF = ? WHERE RAP_NUM = ?");
			statement.setDate(1, date_sql);
			statement.setString(2, bilan);
			statement.setString(3, motif);
			statement.setInt(4, num);
			next = statement.execute();
					
		} catch (SQLException error) {
			error.printStackTrace();
		}
		
		return next;
	}
	
	public boolean ajoutCompteRendu(String numVisiteur, String nom, java.util.Date date, String bilan, String motif, int num) {
		int numPraticien = 0;
		java.sql.Date date_sql = new java.sql.Date(date.getTime());
		boolean next = true;
		
		if (numVisiteur.isEmpty() || nom.isEmpty() || date.toString().isEmpty() || bilan.isEmpty() || motif.isEmpty() || String.valueOf(num).isEmpty())
			return false;
		
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
		
		try {
			PreparedStatement statement = connection.prepareStatement("INSERT INTO rapport_visite (VIS_MATRICULE, RAP_NUM, PRA_NUM, RAP_DATE, RAP_BILAN, RAP_MOTIF) VALUES (?, ?, ?, ?, ?, ?)");
			statement.setString(1, numVisiteur);
			statement.setInt(2, num);
			statement.setInt(3, numPraticien);
			statement.setDate(4, date_sql);
			statement.setString(5, bilan);
			statement.setString(6, motif);
			next = statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return next;
		
	}
	
	public boolean setMedicamentsQuantite(String numVisiteur, String numRapport, String nom1, String qte1) {
		boolean next = true;
		PreparedStatement s;
		
		if (numVisiteur.isEmpty() || numRapport.isEmpty())
			return false;
		
		if (!nom1.isEmpty() && !qte1.isEmpty()) {
			try {
				s = connection.prepareStatement("INSERT INTO offrir (VIS_MATRICULE, RAP_NUM, MED_DEPOTLEGAL, OFF_QTE) VALUES (?, ?, ?, ?)");
				s.setString(1, numVisiteur);
				s.setString(2, numRapport);
				s.setString(3, nom1);
				s.setString(4, qte1);
				next = s.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		s = null;
		
		return next;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
