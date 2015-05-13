/**
 * 
 */
package fr.imie.videodb.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import fr.imie.videodb.dto.ExemplaireDTO;
import fr.imie.videodb.exception.VideoDBPersistenceException;

/**
 * @author imie
 *
 */
public class ExemplaireDAO implements IExemplaireDAO {

	/* (non-Javadoc)
	 * @see fr.imie.videodb.dao.IExemplaireDAO#deleteExemplaire(fr.imie.videodb.dto.ExemplaireDTO)
	 */
	@Override
	public void deleteExemplaire(ExemplaireDTO exemplaireDTO) throws VideoDBPersistenceException {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/videotheque", "postgres",
					"postgres");

			deleteExemplaire(exemplaireDTO,connection);

		} catch (SQLException e) {
			throw new VideoDBPersistenceException(e);
		} finally {
			try {
				if (connection != null && !connection.isClosed()) {
					connection.close();
				}

			} catch (SQLException e) {
				throw new VideoDBPersistenceException(e);
			}
		}
	}
	@Override
	public void deleteExemplaire(ExemplaireDTO exemplaireDTO, Connection connection) throws VideoDBPersistenceException {
		if(exemplaireDTO.getId()==9){
			throw new RuntimeException("exemplaire pas encore retourné");
		}
		PreparedStatement statement = null;
		try {
			
			String query = "delete from exemplaire where id=?";

			statement = connection.prepareStatement(query);

			if (exemplaireDTO.getId() != null) {
				statement.setInt(1, exemplaireDTO.getId());
			}

			statement.executeUpdate();

		} catch (SQLException e) {
			throw new VideoDBPersistenceException(e);
		} finally {
			try {
				if (statement != null && !statement.isClosed()) {
					statement.close();
				}
			
			} catch (SQLException e) {
				throw new VideoDBPersistenceException(e);
			}
		}
	}

}
