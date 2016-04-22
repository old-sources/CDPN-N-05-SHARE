package fr.imie.videodb.service;

import java.util.List;

import fr.imie.videodb.dao.IFilmDAO;
import fr.imie.videodb.dto.FilmDTO;

public interface IVideotequeService {
	public List<FilmDTO> listAllFilm();
	public List<FilmDTO> createSequel(FilmDTO filmDTO);


}
