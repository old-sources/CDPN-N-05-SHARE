package fr.imie.videodb.dao;

import java.sql.Connection;

import fr.imie.videodb.dto.ExemplaireDTO;
import fr.imie.videodb.exception.VideoDBPersistenceException;

public interface IExemplaireDAO {

	public abstract void deleteExemplaire(ExemplaireDTO exemplaireDTO)
			throws VideoDBPersistenceException;

	void deleteExemplaire(ExemplaireDTO exemplaireDTO, Connection connection)
			throws VideoDBPersistenceException;
	
}
