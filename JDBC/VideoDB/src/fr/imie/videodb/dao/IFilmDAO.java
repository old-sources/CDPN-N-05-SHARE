package fr.imie.videodb.dao;

import java.util.List;

import fr.imie.videodb.dto.FilmDTO;
import fr.imie.videodb.exception.VideoDBPersistenceException;

public interface IFilmDAO {

	public abstract List<FilmDTO> findAllFilm()
			throws VideoDBPersistenceException;

}