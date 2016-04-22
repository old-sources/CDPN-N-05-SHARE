package fr.imie;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

import fr.imie.Jeu;

public class TennisRuleTestIntegration extends ATennisRuleTest {

	@Before
	public void initJeux(){
		ISerialiser serialiser = mock(ISerialiser.class);
		Jeu firstResponseJeu = mock(Jeu.class);
		when(firstResponseJeu.getScoreJoueur1()).thenReturn(3);
		when(firstResponseJeu.getScoreJoueur2()).thenReturn(3);
		Jeu secondResponseJeu = mock(Jeu.class);
		when(secondResponseJeu.getScoreJoueur1()).thenReturn(3);
		when(secondResponseJeu.getScoreJoueur2()).thenReturn(0);
		when(serialiser.read()).thenReturn(firstResponseJeu,secondResponseJeu);
		jeu = new Jeu();
		jeu.setSerialiser(serialiser);
	}
}
