package db_class;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import exec.*;
import ui.*;

public class SQLConnection {
	private static Connection connection;
	private String urlbase, host, database, user, pass;

	public SQLConnection(String urlbase, String host, String database, String user, String pass) {
		this.urlbase = urlbase;
		this.host = host;
		this.database = database;
		this.user = user;
		this.pass = pass;
	}

	// Connexion à la BD
	public void connection() {
		if (!(isConnected())) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {

				connection = DriverManager.getConnection(
						urlbase + host + "/" + database + "?autoReconnect=true&useSSL=false", user, pass);
				System.out.println("connected ok");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// Deconnexion à la BD
	public void disconnect() {
		if (isConnected()) {
			try {
				connection.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
	}

	// Vérifie la connexion à la BD
	public boolean isConnected() {
		return connection != null;
	}

	public Connection getConnection() {
		return this.connection;
	}
	
	// Connexion de l'utilisateur
	@SuppressWarnings("deprecation")
	public boolean userConnection(String id, String pass) {
		String finalDate = "";
		boolean next = true;
		int i = 0, res;
		String months[] = {"jan", "feb", "mar", "apr", "may", "jun", "jul", "aug", "sep", "oct", "nov", "dec"};
		String password = "";

		if (id.isEmpty()) 
			return false;
			
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT VIS_DATEEMBAUCHE, VIS_NOM FROM visiteur WHERE VIS_NOM = ?");
			statement.setString(1, id);

			ResultSet resultat = statement.executeQuery();

			while (resultat.next()) {
				System.out.println(resultat.getString("VIS_NOM") + " " + resultat.getString("VIS_DATEEMBAUCHE"));
				String txtDate = resultat.getString("VIS_DATEEMBAUCHE");

				while (next) {
					if (txtDate.charAt(i) == ' ') 
						next = false;

					finalDate += txtDate.charAt(i);
					i++;
				}
			
				SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
				try {
					Date date = d.parse(finalDate);		
					
					int year = 1900 + date.getYear();
					password = date.getDate() + "-" + months[date.getMonth()] + "-" + year;
					
					
					System.out.println(password);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if (pass.equals(password) && !(pass.isEmpty()))
			return true;
		return false;
	}

}
