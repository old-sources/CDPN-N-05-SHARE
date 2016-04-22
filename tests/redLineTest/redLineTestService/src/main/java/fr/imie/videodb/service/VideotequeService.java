package fr.imie.videodb.service;

import java.util.List;

import javax.inject.Inject;

import fr.imie.videodb.dao.IFilmDAO;
import fr.imie.videodb.dto.FilmDTO;
import fr.imie.videodb.exception.VideoDBPersistenceException;

//@RequestScoped
public class VideotequeService implements IVideotequeService {

	 @Inject
	 private IFilmDAO filmDAO;
	//private IFilmDAO filmDAO = new FilmDAO();


	public void setFilmDAO(IFilmDAO filmDAO) {
		this.filmDAO = filmDAO;
	}


	@Override
	public List<FilmDTO> listAllFilm() {
		List<FilmDTO> out = null;
		try {
			out = filmDAO.findAllFilm();
		} catch (VideoDBPersistenceException e) {
			throw new RuntimeException(e);
		}
		return out;
	}

	@Override
	public List<FilmDTO> createSequel(FilmDTO filmDTO) {
		List<FilmDTO> out = null;
		try {
			List<FilmDTO> films = filmDAO.findFilmByExample(filmDTO);
			for (FilmDTO filmDTOFinded : films) {
				FilmDTO newFilm = new FilmDTO();
				newFilm.setLibelle(filmDTOFinded.getLibelle() + " II");
				filmDAO.createFilm(newFilm);
			}
			out = filmDAO.findFilmByExample(filmDTO);

		} catch (VideoDBPersistenceException e) {
			throw new RuntimeException(e);
		}
		return out;
	}


}
