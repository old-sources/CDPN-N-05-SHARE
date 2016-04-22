package fr.imie.videodb.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class ADAO {

	protected Connection getConnection() throws SQLException{
		return DriverManager.getConnection(
				"jdbc:postgresql://localhost:5432/videotheque", "imiedev",
				"imiedev");
	}
}
