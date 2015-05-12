/**
 * 
 */
package fr.imie.videodb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import fr.imie.videodb.presentation.Appli;

/**
 * @author imie
 *
 */
public class MainVideo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Appli appli = new Appli();
		appli.start();
	}



}
