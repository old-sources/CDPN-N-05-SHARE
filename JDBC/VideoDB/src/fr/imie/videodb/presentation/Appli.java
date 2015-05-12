package fr.imie.videodb.presentation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import fr.imie.videodb.dao.FilmDAO;
import fr.imie.videodb.dao.IFilmDAO;
import fr.imie.videodb.dto.FilmDTO;
import fr.imie.videodb.exception.VideoDBPersistenceException;
import fr.imie.videodb.exception.VideoDBPresentationException;

public class Appli {

	IFilmDAO filmDAO;
	SimpleDateFormat simpleDateFormat;

	public Appli() {
		super();
		filmDAO = new FilmDAO();
		simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
	}

	public void start() throws VideoDBPresentationException {
		Scanner scanner = null;
		scanner = new Scanner(System.in);
		Boolean finAppli = false;
		Integer numMenu = null;

		do {

			// afficher le menu
			System.out.println("-----------");
			System.out.println("1:voir tous les films");
			System.out.println("2:créer un films");
			numMenu = numericInput(scanner);

			if (numMenu == 0) {
				finAppli = true;
			} else {
				switch (numMenu) {
				case 1:
					displayAllFilm();
					break;
				case 2:
					System.out.print("libelle : ");
					String libelle = scanner.nextLine();
					System.out.print("date : ");
					Date dateSortie = dateInput(scanner);
					System.out.print("duree : ");
					Integer duree = numericInput(scanner);
					FilmDTO filmDTO = new FilmDTO();
					filmDTO.setLibelle(libelle);
					filmDTO.setDuree(duree);
					filmDTO.setDateSortie(dateSortie);
					try {
						filmDTO = filmDAO.createFilm(filmDTO);
					} catch (VideoDBPersistenceException e) {
						throw new VideoDBPresentationException(e);
					}
					System.out.format("film créé : %s",displayVideo(filmDTO));
					break;
				default:
					System.out.println("menu invalide");
					break;
				}
				System.out.println();
			}

		} while (!finAppli);
		scanner.close();

	}

	private List<FilmDTO> displayAllFilm() throws VideoDBPresentationException {
		Integer numLigne = 1;
		List<FilmDTO> films;
		try {
			films = filmDAO.findAllFilm();
		} catch (VideoDBPersistenceException e1) {
			throw new VideoDBPresentationException(e1);
		}

		for (FilmDTO filmDTO : films) {
			System.out.format("%d : %s \n", numLigne, displayVideo(filmDTO));
			numLigne++;
		}
		return films;
	}

	private Integer numericInput(Scanner scanner) {
		String saisie;
		Integer intValue = null;
		do {
			saisie = scanner.nextLine();
			try {
				intValue = Integer.valueOf(saisie);
			} catch (NumberFormatException e) {
				System.out.println("saisie numerique svp");
			}
		} while (intValue == null);
		return intValue;
	}

	private Date dateInput(Scanner scanner) {
		String saisie;
		Date dateValue = null;
		do {
			saisie = scanner.nextLine();
			try {
				dateValue = simpleDateFormat.parse(saisie);
			} catch (ParseException e) {
				System.out.println("saisie date svp");
			}

		} while (dateValue == null);
		return dateValue;
	}

	private String displayVideo(FilmDTO filmDTO) {
		String retour = "";
		retour = retour.concat(displayColumn(filmDTO.getLibelle(), 20));
		retour = retour.concat("|");
		retour = retour.concat(displayColumn(filmDTO.getDuree(), 3));
		retour = retour.concat("|");
		retour = retour.concat(displayColumn(filmDTO.getDateSortie(), 10));
		return retour;
	}

	private String displayColumn(Object data, Integer length) {
		String retour = "";
		String completion = "";
		if (data != null) {
			if (data instanceof java.util.Date) {
				retour = simpleDateFormat.format(data);
			} else {
				retour = data.toString();
			}
		}
		for (int i = 0; i < length; i++) {
			completion = completion.concat(" ");
		}
		retour = retour.concat(completion);
		retour = retour.substring(0, length);
		return retour;
	}

}
