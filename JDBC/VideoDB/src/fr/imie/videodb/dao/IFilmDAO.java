package fr.imie.videodb.dao;

import java.util.List;

import fr.imie.videodb.dto.FilmDTO;
import fr.imie.videodb.exception.VideoDBPersistenceException;

public interface IFilmDAO {

	/**
	 * @return
	 * @throws VideoDBPersistenceException
	 */
	public abstract List<FilmDTO> findAllFilm()
			throws VideoDBPersistenceException;

	/**
	 * @param filmDTO
	 * @return
	 * @throws VideoDBPersistenceException
	 */
	public abstract FilmDTO createFilm(FilmDTO filmDTO)
			throws VideoDBPersistenceException;

	/**
	 * @param filmDTO
	 * @return
	 * @throws VideoDBPersistenceException
	 */
	public abstract List<FilmDTO> findFilmByExample(FilmDTO filmDTO)
			throws VideoDBPersistenceException;

	/**
	 * @param filmDTO
	 * @throws VideoDBPersistenceException
	 */
	public abstract void deleteFilmById(FilmDTO filmDTO)
			throws VideoDBPersistenceException;

	/**
	 * @param filmDTO
	 * @return
	 * @throws VideoDBPersistenceException
	 */
	public abstract FilmDTO updateFilm(FilmDTO filmDTO)
			throws VideoDBPersistenceException;

	public abstract void createSequels() throws VideoDBPersistenceException;

	public abstract void soldOutFilm(FilmDTO filmDTO)
			throws VideoDBPersistenceException;

}