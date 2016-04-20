package fr.imie;

import java.io.Serializable;

public class Jeu implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6554593031050402981L;
	private Integer scoreJoueur1=0;
	private Integer scoreJoueur2=0;
	

	private transient ISerialiser serialiser;
	

	public Object getScore() {
		String stringScore="";
		if((scoreJoueur1>3 && scoreJoueur2==3)||((scoreJoueur1==3 && scoreJoueur2>3))){
			stringScore="av "+ (scoreJoueur1>scoreJoueur2?"J1":"J2");
			
		}else if(scoreJoueur1==3 && scoreJoueur2==3){
			stringScore="égalité";
		}else{
			stringScore=translatePoint(scoreJoueur1)+"-"+translatePoint(scoreJoueur2);
		}
		return stringScore;
	}

	public void setSerialiser(ISerialiser serialiser) {
		this.serialiser = serialiser;
	}
	
	public void saveJeu(){
		serialiser.persist(this);
	}
	
	public void loadJeu(){
		Jeu loadedJeu = serialiser.read();
		this.setScoreJoueur1(loadedJeu.getScoreJoueur1());
		this.setScoreJoueur2(loadedJeu.getScoreJoueur2());
	}

	public void marquerJoueur1() {
		scoreJoueur1++;	
	}
	
	public void marquerJoueur2() {
		scoreJoueur2++;	
		
	}
	
	private String translatePoint(Integer score){
		String translatedScore ="";
		switch (score){
			case 0 :
				translatedScore="0";
				break;
			case 1 :
				translatedScore="15";
				break;
			case 2 :
				translatedScore="30";
				break;
			case 3 :
				translatedScore="40";
				break;
				
		}

		return translatedScore;
	}

	public Integer getScoreJoueur1() {
		return scoreJoueur1;
	}

	public void setScoreJoueur1(Integer scoreJoueur1) {
		this.scoreJoueur1 = scoreJoueur1;
	}

	public Integer getScoreJoueur2() {
		return scoreJoueur2;
	}

	public void setScoreJoueur2(Integer scoreJoueur2) {
		this.scoreJoueur2 = scoreJoueur2;
	}

	public void reset() {
		scoreJoueur1=0;
		scoreJoueur2=0;
		
	}



}
