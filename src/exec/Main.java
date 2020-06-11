package exec;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

import db_class.*;
import ui.*;
import classes.*;

/*
 * Classe principalement utilisée pour les tests
 * 
 */

public class Main {
	public static SQLConnection sql;

	public static void main(String[] args) throws SQLException {
		sql = new SQLConnection("jdbc:mysql://", "localhost:3306", "db_pharmaeurope", "root", "");
		sql.connection();

		UILogin login = new UILogin(sql);
        login.setVisible(true);
	}

}
