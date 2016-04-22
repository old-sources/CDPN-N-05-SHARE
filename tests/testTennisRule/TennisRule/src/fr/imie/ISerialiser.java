package fr.imie;

import fr.imie.Jeu;

public interface ISerialiser {

	public abstract void persist(Jeu jeu);

	public abstract Jeu read();

}