/**
 * 
 */
package fr.imie.videodb.dao;

import java.security.InvalidParameterException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import fr.imie.videodb.dto.FilmDTO;
import fr.imie.videodb.exception.VideoDBPersistenceException;

/**
 * @author imie
 *
 */
public class FilmDAO implements IFilmDAO {

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.imie.videodb.dao.IFilmDAO#findAllFilm()
	 */
	@Override
	public List<FilmDTO> findAllFilm() throws VideoDBPersistenceException {
		List<FilmDTO> retour = new ArrayList<FilmDTO>();

		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			connection = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/videotheque", "postgres",
					"postgres");

			String query = "select id, libelle, duree, datesortie from video";
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				FilmDTO filmDTO = buildDTO(resultSet);
				retour.add(filmDTO);
			}

		} catch (SQLException e) {
			throw new VideoDBPersistenceException(e);
		} finally {
			try {

				if (resultSet != null && !resultSet.isClosed()) {
					resultSet.close();
				}
				if (statement != null && !statement.isClosed()) {
					statement.close();
				}
				if (connection != null && !connection.isClosed()) {
					connection.close();
				}

			} catch (SQLException e) {
				throw new VideoDBPersistenceException(e);
			}
		}

		return retour;
	}

	private FilmDTO buildDTO(ResultSet resultSet) throws SQLException {
		FilmDTO filmDTO = new FilmDTO();
		filmDTO.setId(resultSet.getInt("id"));
		filmDTO.setLibelle(resultSet.getString("libelle"));
		filmDTO.setDuree(resultSet.getInt("duree"));
		if (resultSet.wasNull()) {
			filmDTO.setDuree(null);
		}
		filmDTO.setDateSortie(resultSet.getDate("datesortie"));
		return filmDTO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.imie.videodb.dao.IFilmDAO#createFilm()
	 */
	@Override
	public FilmDTO createFilm(FilmDTO filmDTO)
			throws VideoDBPersistenceException {
		if (filmDTO.getLibelle() == null) {
			throw new VideoDBPersistenceException(
					new InvalidParameterException("libelle obligatoire"));
		}

		FilmDTO retour;

		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			connection = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/videotheque", "postgres",
					"postgres");

			String query = "INSERT INTO video(libelle, duree, datesortie) VALUES (?, ?, ?) returning id, libelle, duree, datesortie";
			statement = connection.prepareStatement(query);
			statement.setString(1, filmDTO.getLibelle());
			if (filmDTO.getDuree() != null) {
				statement.setInt(2, filmDTO.getDuree());
			} else {
				// TODO gérer construction dynamique de la requête
				statement.setNull(2, Types.INTEGER);
			}

			Date dateSortie = null;
			if (filmDTO.getDateSortie() != null) {
				dateSortie = new java.sql.Date(filmDTO.getDateSortie()
						.getTime());
			}
			statement.setDate(3, dateSortie);

			resultSet = statement.executeQuery();
			resultSet.next();
			retour = buildDTO(resultSet);

		} catch (SQLException e) {
			throw new VideoDBPersistenceException(e);
		} finally {
			try {
				if (resultSet != null && !resultSet.isClosed()) {
					resultSet.close();
				}
				if (statement != null && !statement.isClosed()) {
					statement.close();
				}
				if (connection != null && !connection.isClosed()) {
					connection.close();
				}

			} catch (SQLException e) {
				throw new VideoDBPersistenceException(e);
			}
		}

		return retour;
	}

	@Override
	public List<FilmDTO> findFilmByExample(FilmDTO filmDTO)
			throws VideoDBPersistenceException {
		List<FilmDTO> retour = new ArrayList<FilmDTO>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			connection = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/videotheque", "postgres",
					"postgres");

			String query = "select id,libelle,datesortie,duree from video ";
			Boolean firstCriteria=true;
			if (filmDTO.getId()!=null){
				query=query.concat(firstCriteria?"where ":"and ").concat("id=? ");
				firstCriteria=false;
			}
			
			if (filmDTO.getLibelle()!=null){
				query=query.concat(firstCriteria?"where ":"and ").concat("libelle like ? ");
				firstCriteria=false;
			}
			
			if (filmDTO.getDateSortie()!=null){
				query=query.concat(firstCriteria?"where ":"and ").concat("datesortie=? ");
				firstCriteria=false;
			}
			
			if (filmDTO.getDuree()!=null){
				query=query.concat(firstCriteria?"where ":"and ").concat("duree=? ");
				firstCriteria=false;
			}
			
			statement = connection.prepareStatement(query);
			
			Integer numCriteria = 1;
			if (filmDTO.getId() != null) {
				statement.setInt(numCriteria, filmDTO.getId());
				numCriteria ++;
			}
			
			if (filmDTO.getLibelle() != null) {
				statement.setString(numCriteria, "%"+filmDTO.getLibelle()+"%");
				numCriteria ++;
			}
			
			if (filmDTO.getDateSortie() != null) {
				statement.setDate(numCriteria, new Date( filmDTO.getDateSortie().getTime()));
				numCriteria ++;
			}
			
			if (filmDTO.getDuree() != null) {
				statement.setInt(numCriteria, filmDTO.getDuree());
				numCriteria ++;
			}
			
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				retour.add(buildDTO(resultSet));
			}
			
		} catch (SQLException e) {
			throw new VideoDBPersistenceException(e);
		} finally {
			try {
				if (resultSet != null && !resultSet.isClosed()) {
					resultSet.close();
				}
				if (statement != null && !statement.isClosed()) {
					statement.close();
				}
				if (connection != null && !connection.isClosed()) {
					connection.close();
				}

			} catch (SQLException e) {
				throw new VideoDBPersistenceException(e);
			}
		}

		return retour;
	}

}
