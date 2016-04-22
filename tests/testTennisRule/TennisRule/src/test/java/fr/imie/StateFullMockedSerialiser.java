package fr.imie;

public class StateFullMockedSerialiser implements ISerialiser {

	private Jeu savedJeu;
	
	@Override
	public void persist(Jeu jeu) {
		System.out.println(jeu.getScore());
		savedJeu= new Jeu();
		savedJeu.setScoreJoueur1(jeu.getScoreJoueur1());
		savedJeu.setScoreJoueur2(jeu.getScoreJoueur2());
		
	}

	@Override
	public Jeu read() {
		System.out.println(savedJeu.getScore());
		return savedJeu;
	}

}
