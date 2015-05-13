package fr.imie.videodb.presentation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import fr.imie.videodb.dao.FilmDAO;
import fr.imie.videodb.dao.IFilmDAO;
import fr.imie.videodb.dto.FilmDTO;
import fr.imie.videodb.exception.NullUpdateException;
import fr.imie.videodb.exception.VideoDBPersistenceException;
import fr.imie.videodb.exception.VideoDBPresentationException;

public class Appli {
	
	private final String nullSymbol="%NULL";

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
			System.out.println("3:rechercher un film");
			System.out.println("4:supprimer un film");
			System.out.println("5:modifier un film");
			System.out.println("6:créer des suites");
			System.out.println("7:epuiser un film");
			numMenu = numericInput(scanner);

			if (numMenu == 0) {
				finAppli = true;
			} else {
				FilmDTO filmDTO=null;
				List<FilmDTO> filmDTOs;
				switch (numMenu) {
				case 1:
					displayAllFilm();
					break;
				case 2:
					filmDTO = inputFilm(scanner);
					try {
						filmDTO = filmDAO.createFilm(filmDTO);
					} catch (VideoDBPersistenceException e) {
						throw new VideoDBPresentationException(e);
					}
					System.out.format("film créé : %s", displayVideo(filmDTO));
					break;
				case 3:
					filmDTO = inputFilm(scanner);
					try {
						filmDTOs = filmDAO.findFilmByExample(filmDTO);
					} catch (VideoDBPersistenceException e) {
						throw new VideoDBPresentationException(e);
					}
					for (FilmDTO filmDTO2 : filmDTOs) {
						System.out.println(displayVideo(filmDTO2));
					}
					break;
				case 4:
					filmDTOs = displayAllFilm();
					System.out.print("quel film supprimer? :");
					try {
						Integer numLigne = numericInput(scanner);
						if (numLigne != null) {
							numLigne--;
							FilmDTO filmToDelete = filmDTOs.get(numLigne);
							filmDAO.deleteFilmById(filmToDelete);
						}
					} catch (VideoDBPersistenceException e) {
						throw new VideoDBPresentationException(e);
					}
					break;
				case 5:
					filmDTOs = displayAllFilm();
					System.out.print("quel film modifier? :");
					
					try {
						Integer numLigne = numericInput(scanner);
						if (numLigne != null) {
							numLigne--;
							filmDTO = filmDTOs.get(numLigne);
							System.out.format("libelle : %s ->",filmDTO.getLibelle());
							String libelle;
							try {
								libelle = stringInput(scanner);
								if (libelle!=null){
									filmDTO.setLibelle(libelle);
								}
							} catch (NullUpdateException e) {
								filmDTO.setLibelle(null);
							}
							
							System.out.format("duree : %d ->",filmDTO.getDuree());
							try {
								Integer duree = numericInput(scanner);
								if (duree!=null){
									filmDTO.setDuree(duree);
								}
							} catch (NullUpdateException e) {
								// TODO Auto-generated catch block
								filmDTO.setDuree(null);
							}
							System.out.format("date de sortie : %s ->",filmDTO.getDateSortie());
							try {
								Date dateSortie= dateInput(scanner);
								if (dateSortie!=null){
									filmDTO.setDateSortie(dateSortie);
								}
							} catch (Exception e) {
								filmDTO.setDateSortie(null);
							}
							filmDTO = filmDAO.updateFilm(filmDTO);
						}
					} catch (VideoDBPersistenceException e) {
						throw new VideoDBPresentationException(e);
					}
					System.out.format("film mis à jour : %s", displayVideo(filmDTO));
					break;
				case 6:
					try {
						filmDAO.createSequels();
					} catch (VideoDBPersistenceException e) {
						throw new VideoDBPresentationException(e);
					}
					break;
				case 7:
					filmDTOs = displayAllFilm();
					System.out.print("quel film epuiser? :");
					try {
						Integer numLigne = numericInput(scanner);
						if (numLigne != null) {
							numLigne--;
							FilmDTO filmToSoldOut = filmDTOs.get(numLigne);
							filmDAO.soldOutFilm(filmToSoldOut);
						}
					} catch (VideoDBPersistenceException e) {
						throw new VideoDBPresentationException(e);
					}
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

	private FilmDTO inputFilm(Scanner scanner) {
		System.out.print("libelle : ");
		String libelle = stringInput(scanner);
		System.out.print("date : ");
		Date dateSortie = dateInput(scanner);
		System.out.print("duree : ");
		Integer duree = numericInput(scanner);
		FilmDTO filmDTO = new FilmDTO();
		filmDTO.setLibelle(libelle);
		filmDTO.setDuree(duree);
		filmDTO.setDateSortie(dateSortie);
		return filmDTO;
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
	private String stringInput(Scanner scanner) {
		String libelle = scanner.nextLine();
		if (libelle.compareTo(nullSymbol)==0){
			throw new NullUpdateException();
		}
		libelle = libelle.compareTo("") == 0 ? null : libelle;
		return libelle;
	}
	
	private Integer numericInput(Scanner scanner) {
		String saisie;
		Integer intValue = null;
		Boolean finSaisie = false;
		do {
			saisie = scanner.nextLine();
			if (saisie.compareTo(nullSymbol)==0){
				throw new NullUpdateException();
			}
			if (saisie.compareTo("") != 0) {
				try {
					intValue = Integer.valueOf(saisie);
					finSaisie = true;
				} catch (NumberFormatException e) {
					System.out.println("saisie numerique svp");
				}
			} else {
				finSaisie = true;
			}
		} while (!finSaisie);
		return intValue;
	}

	private Date dateInput(Scanner scanner) {
		String saisie;
		Date dateValue = null;
		Boolean finSaisie = false;
		do {
			saisie = scanner.nextLine();
			if (saisie.compareTo(nullSymbol)==0){
				throw new NullUpdateException();
			}
			if (saisie.compareTo("") != 0) {
				try {
					dateValue = simpleDateFormat.parse(saisie);
					finSaisie = true;
				} catch (ParseException e) {
					System.out.println("saisie date svp");
				}
			} else {
				finSaisie = true;
			}

		} while (!finSaisie);
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
