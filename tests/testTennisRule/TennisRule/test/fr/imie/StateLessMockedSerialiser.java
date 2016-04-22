package fr.imie;

public class StateLessMockedSerialiser implements ISerialiser {

	private Integer readCallOccurence=0;
	
	@Override
	public void persist(Jeu jeu) {
		// not necessary

	}

	@Override
	public Jeu read() {
		Jeu fakeLoadedJeu= new Jeu();
		switch (readCallOccurence) {
		case 0:
			fakeLoadedJeu.setScoreJoueur1(3);
			fakeLoadedJeu.setScoreJoueur2(3);
			break;
		case 1:
			fakeLoadedJeu.setScoreJoueur1(3);
			fakeLoadedJeu.setScoreJoueur2(0);
			break;

		default:
			break;
		}
		readCallOccurence++;
		return fakeLoadedJeu;
	}

}
