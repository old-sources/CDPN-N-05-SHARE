/**
 * 
 */
package fr.imie.videodb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author imie
 *
 */
public class MainVideo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		Scanner scanner = null;
		try {
			connection = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/videotheque", "postgres",
					"postgres");

			scanner = new Scanner(System.in);
			Boolean finAppli = false;

			do {

				String query = "select id, libelle from video";
				statement = connection.createStatement();
				resultSet = statement.executeQuery(query);
				Integer numLigne = 1;
				List<Integer> libelles = new ArrayList<Integer>();
				System.out.println("saisir le numero de la video à afficher");
				while (resultSet.next()) {
					System.out.format("%d : %s \n", numLigne,
							resultSet.getString("libelle"));
					numLigne++;
					libelles.add(resultSet.getInt("id"));
				}
				resultSet.close();

				String saisie = null;
				Integer numLigneSelection = null;
				do {
					saisie = scanner.nextLine();
					try {
						numLigneSelection = Integer.valueOf(saisie);
					} catch (NumberFormatException e) {
						System.out.println("saisie num ligne svp");
					}
				} while (numLigneSelection == null);
				if (numLigneSelection != 0) {
					Integer id = libelles.get(numLigneSelection - 1);

					resultSet = statement
							.executeQuery(String
									.format("select libelle, duree, datesortie from video where id=%d",
											id));
					if (resultSet.next()) {
						displayVideo(resultSet);
					}
					resultSet.close();
				} else {
					finAppli = true;
				}
			} while (!finAppli);

		} catch (SQLException e) {
			throw new RuntimeException("probleme de connection", e);
		} finally {
			scanner.close();
			try {

				if (statement != null && !statement.isClosed()) {
					statement.close();
				}
				if (resultSet != null && !resultSet.isClosed()) {
					resultSet.close();
				}
				if (connection != null && !connection.isClosed()) {
					connection.close();
				}

			} catch (SQLException e) {
				throw new RuntimeException("probleme de deconnection", e);
			}
		}

	}

	private static void displayVideo(ResultSet resultSet) throws SQLException {
		System.out.print(displayColumn(resultSet.getString("libelle"), 20));
		System.out.print("|");
		System.out.print(displayColumn(resultSet.getInt("duree"), 3));
		System.out.print("|");
		System.out.println(resultSet.getDate("datesortie"));
	}

	private static String displayColumn(Object data, Integer length) {
		String retour = data.toString();
		String completion = "";
		for (int i = 0; i < length; i++) {
			completion = completion.concat(" ");
		}
		retour = retour.concat(completion);
		retour = retour.substring(0, length);
		return retour;
	}

}
