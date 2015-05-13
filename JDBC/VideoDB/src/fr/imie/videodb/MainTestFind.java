/**
 * 
 */
package fr.imie.videodb;

import java.util.Date;
import java.util.List;

import fr.imie.videodb.dao.FilmDAO;
import fr.imie.videodb.dto.FilmDTO;
import fr.imie.videodb.exception.VideoDBPersistenceException;

/**
 * @author imie
 *
 */
public class MainTestFind {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FilmDTO filmDTO = new FilmDTO();
		//filmDTO.setDateSortie(new Date());
		filmDTO.setLibelle("mad");
		FilmDAO filmDAO = new FilmDAO();
		List<FilmDTO> films=null;
		try {
			films = filmDAO.findFilmByExample(filmDTO);
		} catch (VideoDBPersistenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (FilmDTO filmDTO2 : films) {
			System.out.println(filmDTO2.getLibelle());
		}

	}

}
