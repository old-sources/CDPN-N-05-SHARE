package fr.imie;

public class Jeu {

	private Integer scoreJoueur1=0;
	private Integer scoreJoueur2=0;
	
	public Object getScore() {
		String stringScore="";
		System.out.println(scoreJoueur1);
		System.out.println(scoreJoueur2);
		if((scoreJoueur1>3 && scoreJoueur2==3)||((scoreJoueur1==3 && scoreJoueur2>3))){
			stringScore="av "+ (scoreJoueur1>scoreJoueur2?"J1":"J2");
			
		}else if(scoreJoueur1==3 && scoreJoueur2==3){
			stringScore="égalité";
		}else{
			stringScore=translatePoint(scoreJoueur1)+"-"+translatePoint(scoreJoueur2);
		}
		return stringScore;
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



}
