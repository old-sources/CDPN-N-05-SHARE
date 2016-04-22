package fr.imie;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import fr.imie.Jeu;

public class TennisRuleTest {

	private Jeu jeu;
	@Before
	public void initJeux(){
		ISerialiser serialiser = new StateLessMockedSerialiser();
		jeu = new Jeu();
		jeu.setSerialiser(serialiser);
	}
	
	@Test
	public void testNewJeuxForSuccessCreatedClass() {
		
	}
	
	@Test
	public void testInitJeuxForSuccess0_0() {
		assertEquals("0-0",jeu.getScore());
	}
	
	@Test
	public void testFirstPointJeuxForSuccess15_0() {
		//Arrange
		//Act
		jeu.marquerJoueur1();
		//Assert
		assertEquals("15-0",jeu.getScore());
	}
	@Test
	public void testSecondPointJeuxForSuccess30_0() {
		//Arrange
		jeu.marquerJoueur1();
		//Act
		jeu.marquerJoueur1();
		//Assert
		assertEquals("30-0",jeu.getScore());
	}

	@Test
	public void testThirdPointJeuxForSuccess40_0() {
		//Arrange + Act
		joueur1To40();
		//Assert
		assertEquals("40-0",jeu.getScore());
	}

	
	
	@Test
	public void testFirstOpenentPointJeuxForSuccess40_15() {
		//Arrange
		joueur1To40();
		//Act
		jeu.marquerJoueur2();
		//Assert
		assertEquals("40-15",jeu.getScore());
	}
	
	@Test
	public void testSecondOpenentPointJeuxForSuccess40_30() {
		//Arrange
		joueur1To40();
		jeu.marquerJoueur2();
		//Act
		jeu.marquerJoueur2();
		//Assert
		assertEquals("40-30",jeu.getScore());
	}
	
	@Test
	public void testEqualityForSuccessEgalite() {
		//Arrange+Act
		joueursToEquality();
		//Assert
		assertEquals("égalité",jeu.getScore());
	}

	@Test
	public void testJ1AvantageForSuccessAvantageJ1() {
		//Arrange
		joueursToEquality();
		//Act
		jeu.marquerJoueur1();
		//Assert
		assertEquals("av J1",jeu.getScore());
	}
	
	@Test
	public void testResetForSuccessEgalite(){
		//Arrange
		joueursToEquality();
		//Act
		jeu.reset();
		//Assert
		assertEquals("0-0",jeu.getScore());
		
	}
	
	@Test
	public void testSaveAndLoadForSuccessEgalite(){
		//Arrange
		joueursToEquality();
		jeu.saveJeu();
		jeu.reset();
		//Act
		jeu.loadJeu();
		//Assert
		assertEquals("égalité",jeu.getScore());
		
	}
	
	@Test
	public void testSaveAndLoadTwiceForSuccess40_0(){
		//Arrange
		joueursToEquality();
		jeu.saveJeu();
		jeu.reset();
		jeu.loadJeu();
		jeu.reset();
		joueur1To40();
		jeu.saveJeu();
		jeu.reset();
		//Act
		jeu.loadJeu();
		//Assert
		assertEquals("40-0",jeu.getScore());
		
	}
	
	private void joueursToEquality() {
		joueur1To40();
		jeu.marquerJoueur2();
		jeu.marquerJoueur2();
		jeu.marquerJoueur2();
	}
	private void joueur1To40() {
		jeu.marquerJoueur1();
		jeu.marquerJoueur1();
		jeu.marquerJoueur1();
	}
	
		
	

}
