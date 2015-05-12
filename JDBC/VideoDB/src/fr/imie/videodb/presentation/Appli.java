package fr.imie.videodb.presentation;

import java.util.List;
import java.util.Scanner;

import fr.imie.videodb.dao.FilmDAO;
import fr.imie.videodb.dao.IFilmDAO;
import fr.imie.videodb.dto.FilmDTO;
import fr.imie.videodb.exception.VideoDBPersistenceException;
import fr.imie.videodb.exception.VideoDBPresentationException;

public class Appli {
	
	IFilmDAO filmDAO;

	public Appli() {
		super();
		filmDAO = new FilmDAO();
	}

	public void start() throws VideoDBPresentationException {
		Scanner scanner = null;
		scanner = new Scanner(System.in);
		Boolean finAppli = false;

		do {

			Integer numLigne = 1;
			List<FilmDTO> films;
			try {
				films = filmDAO.findAllFilm();
			} catch (VideoDBPersistenceException e1) {
				throw new VideoDBPresentationException(e1);
			}
			System.out.println("saisir le numero de la video à afficher");
			for (FilmDTO filmDTO : films) {
				System.out.format("%d : %s \n", numLigne, filmDTO.getLibelle());
				numLigne++;
			}

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
				displayVideo(films.get(numLigneSelection - 1));
			} else {
				finAppli = true;
			}
		} while (!finAppli);
		scanner.close();

	}

	private void displayVideo(FilmDTO filmDTO) {
		System.out.print(displayColumn(filmDTO.getLibelle(), 20));
		System.out.print("|");
		System.out.print(displayColumn(filmDTO.getDuree(), 3));
		System.out.print("|");
		System.out.println(filmDTO.getDateSortie());
	}

	private String displayColumn(Object data, Integer length) {
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
