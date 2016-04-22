package fr.imie;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

import fr.imie.Jeu;

public class TennisRuleTest extends ATennisRuleTest {

	
	@Before
	public void initJeux(){
		ISerialiser serialiser = new Serialiser();
		jeu = new Jeu();
		jeu.setSerialiser(serialiser);
	}
	

}
