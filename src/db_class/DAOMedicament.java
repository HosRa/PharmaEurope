package db_class;

import classes.*;
import ui.*;
import exec.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class DAOMedicament {
	private Connection connection;

	public DAOMedicament(Connection connection) {
		this.connection = connection;
	}
	
	public Medicament getMedicament(String id) {
		Medicament medicament = null;

		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM medicament WHERE MED_DEPOTLEGAL = ?");
			statement.setString(1, id);
			
			ResultSet resultat = statement.executeQuery();
			while (resultat.next())
				medicament = new Medicament(resultat.getString("MED_DEPOTLEGAL"), resultat.getString("MED_NOMCOMMERCIAL"), resultat.getString("FAM_CODE"),
				resultat.getString("MED_COMPOSITION"), resultat.getString("MED_EFFETS"), resultat.getString("MED_CONTREINDIC"), resultat.getString("MED_PRIXECHANTILLON"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return medicament;
	}
	
	public HashMap<String, Integer> getMedicamentsParRapport(int id) {
		HashMap<String, Integer> lesMedicaments = new HashMap<String, Integer>();
		
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT MED_DEPOTLEGAL, OFF_QTE from offrir WHERE RAP_NUM = ?");
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			
			while (rs.next())
				lesMedicaments.put(rs.getString("MED_DEPOTLEGAL"), rs.getInt("OFF_QTE"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lesMedicaments;
	}
	
	
	public ArrayList<Medicament> getMedicaments() {
		ArrayList<Medicament> lesMedicaments = new ArrayList<Medicament>();
		Medicament m = null;
		
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM medicament");
			ResultSet resultat = statement.executeQuery();

			while (resultat.next()) {
				m = new Medicament(resultat.getString("MED_DEPOTLEGAL"), resultat.getString("MED_NOMCOMMERCIAL"), resultat.getString("FAM_CODE"), resultat.getString("MED_COMPOSITION"),
						resultat.getString("MED_EFFETS"), resultat.getString("MED_CONTREINDIC"), resultat.getString("MED_PRIXECHANTILLON"));
				lesMedicaments.add(m);
				m = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return lesMedicaments;
	}
	
	public Medicament getMedicamentById(String numMedicament) {
		Medicament m = null;
		
		try {
			PreparedStatement s = connection.prepareStatement("SELECT * from medicament WHERE MED_DEPOTLEGAL = ?");
			s.setString(1, numMedicament);
			
			ResultSet rs = s.executeQuery();
			while (rs.next())
				m = new Medicament(rs.getString("MED_DEPOTLEGAL"), rs.getString("MED_NOMCOMMERCIAL"), rs.getString("FAM_CODE"), rs.getString("MED_COMPOSITION"),
						rs.getString("MED_EFFETS"), rs.getString("MED_CONTREINDIC"), rs.getString("MED_PRIXECHANTILLON"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return m;
	}
	
	public boolean updateMedicament(String nom, String famille, String composition, String effets, String indic, String prix, String code) {
		boolean next = false;
		
		if (prix.isEmpty())
			prix = "0";
		try {	
			PreparedStatement statement = connection.prepareStatement("UPDATE medicament SET MED_NOMCOMMERCIAL = ?, FAM_CODE = ?, MED_COMPOSITION = ?, MED_EFFETS = ?, MED_CONTREINDIC = ?, MED_PRIXECHANTILLON = ? WHERE MED_DEPOTLEGAL = ? ");
			statement.setString(1, nom);
			statement.setString(2, famille);
			statement.setString(3, composition);
			statement.setString(4, effets);
			statement.setString(5, indic);
			statement.setString(6, prix);
			statement.setString(7, code);
			System.out.println(statement.toString());
			next = statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return next;
	}
}
