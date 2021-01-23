package roll_the_ball.models;

import java.io.Serializable;
import java.util.LinkedList;

public class Niveau implements Serializable {
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 7539199942982208879L;
	private Plateau plateau ;
	private int score;
	private int scoreBut;
	private Plateau plateau_gagnant ;



	public Niveau(Plateau plateau)
	{
		this.plateau = plateau;
	}

	public Niveau(int width,int height,int indice)
	{

		plateau=new Plateau(width,height);
		plateau_gagnant=new Plateau(width,height);

		plateau.initPlateau(indice);
		plateau_gagnant.initPlateauGagnant(indice);

		scoreBut=plateau.getMouvementBut();

	}





	public Plateau getPlateau() {
		return plateau;
	}

	public void setPlateau(Plateau plateau) {
		this.plateau = plateau;
	}

	public Plateau getPlateau_gagnant() {
		return plateau_gagnant;
	}

	public int getScoreBut() {
		return scoreBut;
	}
}
