package roll_the_ball.models;

import javafx.scene.image.Image;

import java.util.*;

public class Plateau
{
	private  Piece[][] plateau ;
	private int  width, height;
	private int mouvementBut;

	final static String arrive_ball = "roll_the_ball/resources/imgs/arrive_ball.png";
	final static String arrive_2 = "roll_the_ball/resources/imgs/arrive_2.png";
	final static String arrive = "roll_the_ball/resources/imgs/arrive.png";
	final static String case_vide = "roll_the_ball/resources/imgs/case_vide.jpg";
	final static String depart_gauche = "roll_the_ball/resources/imgs/depart_gauche.png";
	final static String depart_droite = "roll_the_ball/resources/imgs/depart_droite.png";
	final static String depart = "roll_the_ball/resources/imgs/depart.png";
	final static String horizontal_etoile_gris = "roll_the_ball/resources/imgs/horizontal_etoile_gris.png";
	final static String horizontal = "roll_the_ball/resources/imgs/horizontal.png";
	final static String rotation_2 = "roll_the_ball/resources/imgs/rotation_2.png";
	final static String rotation = "roll_the_ball/resources/imgs/rotation.png";
	final static String vertical_etoile = "roll_the_ball/resources/imgs/vertical_etoile.png";
	final static String vertical = "roll_the_ball/resources/imgs/vertical.png";
	final static String vide_gris = "roll_the_ball/resources/imgs/vide_gris.png";
	final static String vide = "roll_the_ball/resources/imgs/vide.png";
	final static String virage_2 = "roll_the_ball/resources/imgs/virage_2.png";
	final static String virage_3 = "roll_the_ball/resources/imgs/virage_3.png";
	final static String virage_etoile_gris = "roll_the_ball/resources/imgs/virage_etoile_gris.png";
	final static String virage_etoile = "roll_the_ball/resources/imgs/virage_etoile.png";
	final static String virage_gris_2 = "roll_the_ball/resources/imgs/virage_gris_2.png";
	final static String virage_gris = "roll_the_ball/resources/imgs/virage_gris.png";
	final static String virage = "roll_the_ball/resources/imgs/virage.png";
	final static String virage_gris_3="roll_the_ball/resources/imgs/virage_gris_3.png";
	final static String arrive_3="roll_the_ball/resources/imgs/arrive_3.png";
	final static String depart_3="roll_the_ball/resources/imgs/depart_bas.png";

	public Plateau(int width, int height)
	{
		this.width = width;
		this.height = height;
		plateau=new Piece[width][height];
		mouvementBut=0;
	}




	public void initPlateauGagnant(int id_niveau)
	{
		switch (id_niveau)
		{
			case 0: initPlateauG0();
				break;

			case 1: initPlateauG1();
				break;

			case 2: initPlateauG2();
				break;

			case 3: initPlateauG3();
				break;

			case 4: initPlateauG4();
				break;

			case 5: initPlateauG5();
				break;

			case 6: initPlateauG6();
				break;

			case 7: initPlateauG7();
				break;

			case 8: initPlateauG8();
				break;

			case 9: initPlateauG9();
				break;

			case 10: initPlateauG10();
				break;

			case 11: initPlateauG11();
				break;
		}

	}

	public void initPlateau(int id_niveau)
	{
		switch (id_niveau)
		{
			case 0: initPlateau0();
			        mouvementBut=1;
				break;

			case 1: initPlateau1();
					mouvementBut=2;
				break;

			case 2: initPlateau2();
					mouvementBut=5;
				break;

			case 3: initPlateau3();
			        mouvementBut=4;
				break;

			case 4: initPlateau4();
				mouvementBut=9;
				break;

			case 5: initPlateau5();
				mouvementBut=9;
				break;

			case 6: initPlateau6();
			    mouvementBut=9;
				break;

			case 7: initPlateau7();
				mouvementBut=9;
				break;

			case 8: initPlateau8();
				mouvementBut=9;
				break;

			case 9: initPlateau9();
				mouvementBut=9;
				break;

			case 10: initPlateau10();
				mouvementBut=10;
				break;

			case 11: initPlateau11();
				mouvementBut=15;
				break;

		}

	}


	//--------------------------------------------------------------------------------------
	/**
	 *
	 * Initialisation des plateaux :
	 *
	 * **/

	/*
	*  Plateau 0
	*  ---------
	* **/
	public void initPlateau0()

	{
		int i,j;



		plateau[0][0]=new PieceFixe(depart,false,false,true,false);
		plateau[3][3]=new PieceFixe(arrive_ball,false,true,false,false);
		plateau[1][0]=new PieceChemin(horizontal,true,false,true,false);
		plateau[2][0]=new PieceChemin(horizontal,true,false,true,false);
		plateau[3][0]=new PieceFixe(virage_etoile_gris,true,false,false,true);
		plateau[3][2]=new PieceChemin(vertical,false,true,false,true);
		plateau[3][1]=new PieceChemin(case_vide,false,true,false,true);



		for (i=0 ; i!=3 ; i++)
		{
			for (j=1 ; j!=4 ; j++)
			{
				if(j==1 && i==2)
				{
					plateau[2][1]=new PieceChemin(vertical,false,true,false,true);


				}
				else
				{
					plateau[i][j]=new PieceChemin(vide,false,false,false,false);

				}
			}
		}
	}

	public void initPlateauG0()
	{
		initPlateau0();
		plateau[2][1]=new PieceChemin(case_vide,false,true,false,true);
		plateau[3][1]=new PieceChemin(vertical,false,true,false,true);

	}

	/*
	 *  Plateau 1
	 *  ---------
	 * **/
	public void initPlateau1()

	{
		int i;

		plateau[0][2]=new PieceFixe(depart,false,false,true,false);
		plateau[1][1]=new PieceFixe(arrive_2,false,false,true,false);

		plateau[0][3]=new PieceChemin(virage,false,true,true,false);
		plateau[2][3]=new PieceChemin(virage_2,true,true,false,false);
		plateau[3][0]=new PieceChemin(virage_2,true,true,false,false);
		plateau[3][2]=new PieceChemin(vertical,false,true,false,true);

		plateau[1][2]=new PieceFixe(virage_etoile_gris,true,false,false,true);
		plateau[2][1]=new PieceFixe(virage_etoile_gris,true,false,false,true);




		for (i=0 ; i!=3 ; i++)
		{
			plateau[i][0]=new PieceChemin(vide,false,false,false,false);
		}
		plateau[0][1]=new PieceChemin(vide,false,false,false,false);

		// normalement vide
		plateau[3][3]=new PieceChemin(case_vide,true,true,true,true);
		plateau[1][3]=new PieceChemin(case_vide,true,true,true,true);
		plateau[2][2]=new PieceChemin(case_vide,true,true,true,true);
		plateau[3][1]=new PieceChemin(case_vide,true,true,true,true);


	}

	public void initPlateauG1()
	{
		initPlateau1();
		plateau[1][3]=new PieceChemin(virage,false,true,true,false);
		plateau[0][3]=new PieceChemin(case_vide,true,true,true,true);
		plateau[3][2]=new PieceChemin(case_vide,true,true,true,true);
		plateau[2][2]=new PieceChemin(vertical,false,true,false,true);

	}

	/*
	 *  Plateau 2
	 *  ---------
	 * **/

	public void initPlateau2()

	{

		plateau[0][1]=new PieceFixe(depart_gauche,false,false,false,true);
		plateau[3][3]=new PieceFixe(arrive_ball,false,true,false,false);

		plateau[0][2]=new PieceChemin(virage,false,true,true,false);
		plateau[3][2]=new PieceChemin(virage,false,true,true,false);
		plateau[1][0]=new PieceChemin(virage_2,true,true,false,false);
		plateau[1][1]=new PieceChemin(virage_3,true,false,false,true);
		plateau[3][1]=new PieceChemin(virage_3,true,false,false,true);
		plateau[1][3]=new PieceChemin(virage_etoile,false,false,true,true);

		plateau[1][2]=new PieceTournante(rotation_2,true,false,true,false);
		plateau[2][0]=new PieceChemin(horizontal,true,false,true,false);
		plateau[0][3]=new PieceTournante(rotation,false,true,false,true);
		plateau[3][0]=new PieceTournante(rotation,false,true,false,true);

		// normalement vide
		plateau[0][0]=new PieceChemin(case_vide,true,true,true,true);
		plateau[2][1]=new PieceChemin(case_vide,true,true,true,true);
		plateau[2][2]=new PieceChemin(case_vide,true,true,true,true);
		plateau[2][3]=new PieceChemin(case_vide,true,true,true,true);


	}
	public void initPlateauG2()
	{

	}

	/*
	 *  Plateau 3
	 *  ---------
	 * **/

	public void initPlateau3()

	{


		plateau[0][0]=new PieceFixe(depart,false,false,true,false);
		plateau[3][3]=new PieceFixe(arrive_ball,false,true,false,false);

		plateau[0][1]=new PieceChemin(virage,false,true,true,false);
		plateau[2][2]=new PieceChemin(virage_2,true,true,false,false);
		plateau[1][0]=new PieceChemin(virage_3,true,false,false,true);
		plateau[3][0]=new PieceChemin(virage_3,true,false,false,true);
		plateau[2][3]=new PieceChemin(vide,false,false,false,false);



		plateau[1][1]=new PieceTournante(rotation,false,true,false,true);
		plateau[1][3]=new PieceTournante(rotation,false,true,false,true);
		plateau[3][2]=new PieceTournante(rotation_2,true,false,true,false);


		plateau[2][1]=new PieceFixe(virage_gris_2,false,false,true,true);

		// normalement vide
		plateau[0][2]=new PieceChemin(case_vide,true,true,true,true);
		plateau[0][3]=new PieceChemin(case_vide,true,true,true,true);
		plateau[1][2]=new PieceChemin(case_vide,true,true,true,true);
		plateau[2][0]=new PieceChemin(case_vide,true,true,true,true);
		plateau[3][1]=new PieceChemin(case_vide,true,true,true,true);



	}
	public void initPlateauG3()
	{
		initPlateau3();

		plateau[1][2]=new PieceChemin(virage,false,true,true,false);
		plateau[0][1]=new PieceChemin(case_vide,true,true,true,true);
		plateau[3][0]=new PieceChemin(case_vide,true,true,true,true);
		plateau[3][1]=new PieceChemin(virage_3,true,false,false,true);
		plateau[3][2]=new PieceTournante(rotation,false,true,false,true);

	}

	/*
	 *  Plateau 4
	 *  ---------
	 * **/

	public void initPlateau4()

	{

		plateau[2][1]=new PieceFixe(depart,false,false,true,false);
		plateau[1][2]=new PieceFixe(arrive,true,false,false,false);
		plateau[1][0]=new PieceChemin(horizontal,true,false,true,false);
		plateau[2][0]=new PieceChemin(vide,false,false,false,false);
		plateau[3][0]=new PieceChemin(case_vide,true,true,true,true);
		plateau[3][2]=new PieceChemin(virage_2,true,true,false,false);
		plateau[3][1]=new PieceChemin(virage_3,true,false,false,true);
		plateau[3][3]=new PieceChemin(virage_etoile,false,false,true,true);
		plateau[2][3]=new PieceChemin(virage_2,true,true,false,false);
		plateau[1][3]=new PieceChemin(case_vide,true,true,true,true);
		plateau[0][3]=new PieceChemin(horizontal,true,false,true,false);
		plateau[0][2]=new PieceChemin(virage,false,true,true,false);
		plateau[0][1]=new PieceChemin(vertical,false,true,false,true);
		plateau[0][0]=new PieceChemin(virage_etoile,false,false,true,true);
		plateau[1][1]=new PieceChemin(case_vide,true,true,true,true);
		plateau[2][2]=new PieceChemin(case_vide,true,true,true,true);

	}

	public void initPlateauG4()
	{
		plateau[2][1]=new PieceFixe(depart,false,false,true,false);
		plateau[1][2]=new PieceFixe(arrive,false,true,false,false);
		plateau[1][0]=new PieceChemin(horizontal,false,true,false,true);
		plateau[2][0]=new PieceChemin(horizontal,false,true,false,true);
		plateau[3][0]=new PieceChemin(case_vide,true,true,true,true);
		plateau[3][2]=new PieceChemin(virage_2,true,true,false,false);
		plateau[3][1]=new PieceChemin(virage_3,true,false,false,true);
		plateau[3][3]=new PieceChemin(case_vide,true,true,true,true);
		plateau[2][3]=new PieceChemin(virage_2,true,true,false,false);
		plateau[1][3]=new PieceChemin(horizontal,false,true,false,true);
		plateau[0][3]=new PieceChemin(virage,false,true,true,false);
		plateau[0][2]=new PieceChemin(virage_etoile,false,false,true,true);
		plateau[0][1]=new PieceChemin(case_vide,true,true,true,true);
		plateau[0][0]=new PieceChemin(case_vide,true,true,true,true);
		plateau[1][1]=new PieceChemin(vertical,false,true,false,true);
		plateau[2][2]=new PieceChemin(virage_etoile,false,false,true,true);


	}

	public void initPlateau5()

	{
		plateau[0][0]=new PieceChemin(case_vide,true,true,true,true);
		plateau[0][1]=new PieceChemin(virage_etoile,false,false,true,true);
		plateau[0][2]=new PieceChemin(virage_etoile,false,false,true,true);
		plateau[0][3]=new PieceFixe(arrive_2,false,false,true,false);

		plateau[1][0]=new PieceChemin(horizontal,true,false,true,false);
		plateau[1][1]=new PieceChemin(virage_2,true,true,false,false);
		plateau[1][2]=new PieceChemin(case_vide,true,true,true,true);
		plateau[1][3]=new PieceChemin(case_vide,true,true,true,true);

		plateau[2][0]=new PieceChemin(case_vide,true,true,true,true);
		plateau[2][1]=new PieceChemin(virage,false,true,true,false);
		plateau[2][2]=new PieceChemin(virage_3,true,false,false,true);
		plateau[2][3]=new PieceChemin(virage_2,true,true,false,false);

		plateau[3][0]=new PieceChemin(virage_3,true,false,false,true);
		plateau[3][1]=new PieceChemin(vertical_etoile,false,true,false,true);
		plateau[3][2]=new PieceChemin(vertical_etoile,false,true,false,true);
		plateau[3][3]=new PieceFixe(depart_droite,false,true,false,false);


	}

	public void initPlateauG5()
	{
		plateau[0][0]=new PieceChemin(case_vide,true,true,true,true);
		plateau[0][1]=new PieceChemin(case_vide,true,true,true,true);
		plateau[0][2]=new PieceChemin(case_vide,true,true,true,true);
		plateau[0][3]=new PieceFixe(arrive_2,false,false,true,false);

		plateau[1][0]=new PieceChemin(virage_etoile,false,false,true,true);
		plateau[1][1]=new PieceChemin(virage,false,true,true,false);
		plateau[1][2]=new PieceChemin(virage_etoile,false,false,true,true);
		plateau[1][3]=new PieceChemin(virage_2,true,true,false,false);

		plateau[2][0]=new PieceChemin(horizontal,true,false,true,false);
		plateau[2][1]=new PieceChemin(virage_3,true,false,false,true);
		plateau[2][2]=new PieceChemin(virage_2,true,true,false,false);
		plateau[2][3]=new PieceChemin(case_vide,true,true,true,true);

		plateau[3][0]=new PieceChemin(virage_3,true,false,false,true);
		plateau[3][1]=new PieceChemin(vertical_etoile,false,true,false,true);
		plateau[3][2]=new PieceChemin(vertical_etoile,false,true,false,true);
		plateau[3][3]=new PieceFixe(depart_droite,false,true,false,false);

	}


	public void initPlateau6()
	{
		plateau[0][0]=new PieceChemin(depart,false,false,true,false);
		plateau[0][1]=new PieceChemin(vertical,false,true,false,true);
		plateau[0][2]=new PieceChemin(virage,false,true,true,false);
		plateau[0][3]=new PieceChemin(case_vide,true,true,true,true);

		plateau[1][0]=new PieceChemin(virage_etoile_gris,true,false,false,true);
		plateau[1][1]=new PieceChemin(virage_etoile,false,false,true,true);
		plateau[1][2]=new PieceChemin(case_vide,true,true,true,true);
		plateau[1][3]=new PieceChemin(virage_2,true,true,false,false);

		plateau[2][0]=new PieceChemin(arrive_2,false,false,true,false);
		plateau[2][1]=new PieceChemin(vertical,false,true,false,true);
		plateau[2][2]=new PieceChemin(vertical,false,true,false,true);
		plateau[2][3]=new PieceChemin(vide,false,false,false,false);

		plateau[3][0]=new PieceChemin(virage_etoile_gris,true,false,false,true);
		plateau[3][1]=new PieceChemin(case_vide,true,true,true,true);
		plateau[3][2]=new PieceChemin(virage_2,true,true,false,false);
		plateau[3][3]=new PieceChemin(case_vide,true,true,true,true);



	}

	public void initPlateauG6()
	{
		plateau[0][0]=new PieceChemin(depart,false,false,true,false);
		plateau[0][1]=new PieceChemin(case_vide,true,true,true,true);
		plateau[0][2]=new PieceChemin(case_vide,true,true,true,true);
		plateau[0][3]=new PieceChemin(case_vide,true,true,true,true);

		plateau[1][0]=new PieceChemin(virage_etoile_gris,true,false,false,true);
		plateau[1][1]=new PieceChemin(vertical,false,true,false,true);
		plateau[1][2]=new PieceChemin(vertical,false,true,false,true);
		plateau[1][3]=new PieceChemin(virage,false,true,true,false);

		plateau[2][0]=new PieceChemin(arrive_2,false,false,true,false);
		plateau[2][1]=new PieceChemin(case_vide,true,true,true,true);
		plateau[2][2]=new PieceChemin(virage_etoile,false,false,true,true);
		plateau[2][3]=new PieceChemin(virage_2,true,true,false,false);

		plateau[3][0]=new PieceChemin(virage_etoile_gris,true,false,false,true);
		plateau[3][1]=new PieceChemin(vertical,false,true,false,true);
		plateau[3][2]=new PieceChemin(virage_2,true,true,false,false);
		plateau[3][3]=new PieceChemin(case_vide,true,true,true,true);

	}

	//--------------------------------------------------------------------------------------

	public void initPlateau7()

	{
		plateau[0][0]=new PieceChemin(case_vide,true,true,true,true);//ok
		plateau[0][1]=new PieceChemin(virage_etoile,false,false,true,true);//ok
		plateau[0][2]=new PieceChemin(vertical,false,true,false,true);//ok
		plateau[0][3]=new PieceFixe(arrive_ball,false,true,false,false);//ok
		//l1
		plateau[1][0]=new PieceChemin(vide,false,false,false,false);//ok
		plateau[1][1]=new PieceChemin(vertical,false,true,false,true);//ok
		plateau[1][2]=new PieceChemin(vertical,false,true,false,true);//ok
		plateau[1][3]=new PieceChemin(vertical,false,true,false,true);//ok
		//L2
		plateau[2][0]=new PieceChemin(horizontal,true,false,true,false);//corrigé
		plateau[2][1]=new PieceFixe(depart_gauche,false,false,false,true);//ok
		plateau[2][2]=new PieceChemin(vertical,false,true,false,true);//ok
		plateau[2][3]=new PieceFixe(virage_gris_3,false,true,true,false);//ok
		//L3
		plateau[3][0]=new PieceChemin(horizontal,true,false,true,false);//corrigé
		plateau[3][1]=new PieceChemin(virage_3,true,false,false,true);//ok
		plateau[3][2]=new PieceChemin(case_vide,true,true,true,true);//ok
		plateau[3][3]=new PieceChemin(virage_2,true,true,false,false);//ok
		//L4



	}

	public void initPlateauG7()
	{
		plateau[0][0]=new PieceChemin(virage_etoile,false,false,true,true);
		plateau[0][1]=new PieceChemin(vertical,false,true,false,true);
		plateau[0][2]=new PieceChemin(vertical,false,true,false,true);
		plateau[0][3]=new PieceFixe(arrive_ball,false,true,false,false);
		//l1
		plateau[1][0]=new PieceChemin(horizontal,true,false,true,false);
		plateau[1][1]=new PieceChemin(case_vide,true,true,true,true);
		plateau[1][2]=new PieceChemin(case_vide,true,true,true,true);
		plateau[1][3]=new PieceChemin(vide,false,false,false,false);
		//L2
		plateau[2][0]=new PieceChemin(horizontal,true,false,true,false);
		plateau[2][1]=new PieceFixe(depart_gauche,false,false,false,true);
		plateau[2][2]=new PieceChemin(vertical,false,true,false,true);
		plateau[2][3]=new PieceFixe(virage_gris_3,false,true,true,false);
		//L3
		plateau[3][0]=new PieceChemin(virage_3,true,false,false,true);
		plateau[3][1]=new PieceChemin(vertical,false,true,false,true);
		plateau[3][2]=new PieceChemin(vertical,false,true,false,true);
		plateau[3][3]=new PieceChemin(virage_2,true,true,false,false);
		//L4



	}

	//---------------------------------------------------------------------------

	public void initPlateau8() {
		plateau[0][0]=new PieceChemin(case_vide,true,true,true,true);
		plateau[0][1]=new PieceChemin(case_vide,true,true,true,true);
		plateau[0][2]=new PieceFixe(depart_droite,false,true,false,false);
		plateau[0][3]=new PieceFixe(arrive_2,false,false,true,false);
		//l1
		plateau[1][0]=new PieceChemin(case_vide,true,true,true,true);
		plateau[1][1]=new PieceChemin(vertical,false,true,false,true);
		plateau[1][2]=new PieceChemin(virage_3,true,false,false,true);
		plateau[1][3]=new PieceChemin(case_vide,true,true,true,true);
		//L2
		plateau[2][0]=new PieceChemin(virage_etoile,false,false,true,true);
		plateau[2][1]=new PieceChemin(virage,false,true,true,false);
		plateau[2][2]=new PieceChemin(virage_etoile,false,false,true,true);
		plateau[2][3]=new PieceChemin(virage_2,true,true,false,false);
		//L3
		plateau[3][0]=new PieceChemin(virage_3,true,false,false,true);
		plateau[3][1]=new PieceChemin(virage_2,true,true,false,false);
		plateau[3][2]=new PieceChemin(horizontal,true,false,true,false);
		plateau[3][3]=new PieceChemin(vide,false,false,false,false);
		//L4

	}

	public void initPlateauG8() {
		plateau[0][0]=new PieceChemin(virage_etoile,false,false,true,true);
		plateau[0][1]=new PieceChemin(vertical,false,true,false,true);
		plateau[0][2]=new PieceFixe(depart_droite,false,true,false,false);
		plateau[0][3]=new PieceFixe(arrive_2,false,false,true,false);
		//l1
		plateau[1][0]=new PieceChemin(virage_3,true,false,false,true);
		plateau[1][1]=new PieceChemin(virage,false,true,true,false);
		plateau[1][2]=new PieceChemin(virage_etoile,false,false,true,true);
		plateau[1][3]=new PieceChemin(virage_2,true,true,false,false);
		//L2
		plateau[2][0]=new PieceChemin(case_vide,true,true,true,true);
		plateau[2][1]=new PieceChemin(virage_3,true,false,false,true);
		plateau[2][2]=new PieceChemin(virage_2,true,true,false,false);
		plateau[2][3]=new PieceChemin(case_vide,true,true,true,true);
		//L3
		plateau[3][0]=new PieceChemin(horizontal,true,false,true,false);
		plateau[3][1]=new PieceChemin(case_vide,true,true,true,true);
		plateau[3][2]=new PieceChemin(case_vide,true,true,true,true);
		plateau[3][3]=new PieceChemin(vide,false,false,false,false);
		//L4

	}

	//---------------------------------------------------------------------------
	public void initPlateau9()

	{
		plateau[0][0]=new PieceChemin(virage,false,true,true,false);
		plateau[0][1]=new PieceChemin(virage_etoile,false,false,true,true);
		plateau[0][2]=new PieceChemin(virage_2,true,true,false,false);
		plateau[0][3]=new PieceFixe(virage,false,true,true,false);
		//l1
		plateau[1][0]=new PieceFixe(arrive_3,true,true,true,true);
		plateau[1][1]=new PieceChemin(case_vide,true,true,true,true);
		plateau[1][2]=new PieceChemin(horizontal,true,false,true,false);
		plateau[1][3]=new PieceChemin(horizontal,true,false,true,false);
		//L2
		plateau[2][0]=new PieceChemin(virage_etoile,false,false,true,true);
		plateau[2][1]=new PieceChemin(virage_3,true,false,false,true);
		plateau[2][2]=new PieceChemin(case_vide,true,true,true,true);
		plateau[2][3]=new PieceChemin(horizontal,true,false,true,false);
		//L3
		plateau[3][0]=new PieceChemin(case_vide,true,true,true,true);
		plateau[3][1]=new PieceChemin(virage_2,true,true,false,false);
		plateau[3][2]=new PieceChemin(vertical,false,true,false,true);
		plateau[3][3]=new PieceFixe(depart_3,true,false,false,false);
		//L4


	}

	public void initPlateauG9()
	{
		plateau[0][0]=new PieceChemin(case_vide,true,true,true,true);
		plateau[0][1]=new PieceChemin(case_vide,true,true,true,true);
		plateau[0][2]=new PieceChemin(virage_etoile,false,false,true,true);
		plateau[0][3]=new PieceFixe(virage,false,true,true,false);
		//l1
		plateau[1][0]=new PieceFixe(arrive_3,true,true,true,true);
		plateau[1][1]=new PieceChemin(virage,false,true,true,false);
		plateau[1][2]=new PieceChemin(horizontal,true,false,true,false);
		plateau[1][3]=new PieceChemin(horizontal,true,false,true,false);
		//L2
		plateau[2][0]=new PieceChemin(vertical,false,true,false,true);
		plateau[2][1]=new PieceChemin(virage_3,true,false,false,true);
		plateau[2][2]=new PieceChemin(virage_2,true,true,false,false);
		plateau[2][3]=new PieceChemin(horizontal,true,false,true,false);
		//L3
		plateau[3][0]=new PieceChemin(virage_etoile,false,false,true,true);
		plateau[3][1]=new PieceChemin(virage_2,true,true,false,false);
		plateau[3][2]=new PieceChemin(case_vide,true,true,true,true);
		plateau[3][3]=new PieceFixe(depart_3,true,false,false,false);
		//L4


	}


	//---------------------------------------------------------------------------
	public void initPlateau10(){

		//but= 10 coups
		plateau[0][0]=new PieceChemin(horizontal,true,false,true,false);
		plateau[0][1]=new PieceChemin(virage_etoile,false,false,true,true);
		plateau[0][2]=new PieceChemin(virage,false,true,true,false);
		plateau[0][3]=new PieceChemin(horizontal,true,false,true,false);
		//l1
		plateau[1][0]=new PieceChemin(virage_3,true,false,false,true);
		plateau[1][1]=new PieceFixe(depart_3,true,false,false,false);
		plateau[1][2]=new PieceChemin(virage,false,true,true,false);
		plateau[1][3]=new PieceChemin(vide,false,false,false,false);
		//L2
		plateau[2][0]=new PieceChemin(vertical,false,true,false,true);
		plateau[2][1]=new PieceChemin(vertical,false,true,false,true);
		plateau[2][2]=new PieceChemin(case_vide,true,true,true,true);
		plateau[2][3]=new PieceFixe(arrive_2,false,false,true,false);
		//L3
		plateau[3][0]=new PieceChemin(case_vide,true,true,true,true);
		plateau[3][1]=new PieceChemin(case_vide,true,true,true,true);
		plateau[3][2]=new PieceChemin(virage_3,true,false,false,true);
		plateau[3][3]=new PieceChemin(virage_2,true,true,false,false);
	}

	public void initPlateauG10()
	{
		plateau[0][0]=new PieceChemin(virage_etoile,false,false,true,true);
		plateau[0][1]=new PieceChemin(virage,false,true,true,false);
		plateau[0][2]=new PieceChemin(case_vide,true,true,true,true);
		plateau[0][3]=new PieceChemin(horizontal,true,false,true,false);
		//l1
		plateau[1][0]=new PieceChemin(horizontal,true,false,true,false);
		plateau[1][1]=new PieceFixe(depart_3,true,false,false,false);
		plateau[1][2]=new PieceChemin(case_vide,true,true,true,true);
		plateau[1][3]=new PieceChemin(vide,false,false,false,false);
		//L2
		plateau[2][0]=new PieceChemin(virage_3,true,false,false,true);
		plateau[2][1]=new PieceChemin(vertical,false,true,false,true);
		plateau[2][2]=new PieceChemin(virage,false,true,true,false);
		plateau[2][3]=new PieceFixe(arrive_2,false,false,true,false);
		//L3
		plateau[3][0]=new PieceChemin(vertical,false,true,false,true);
		plateau[3][1]=new PieceChemin(case_vide,true,true,true,true);
		plateau[3][2]=new PieceChemin(virage_3,true,false,false,true);
		plateau[3][3]=new PieceChemin(virage_2,true,true,false,false);
	}
	//---------------------------------------------------------------------------


	//---------------------------------------------------------------------------
	public void initPlateau11(){
		//but= 15 coups
		plateau[0][0]=new PieceChemin(vide,false,false,false,false);
		plateau[0][1]=new PieceChemin(virage_etoile,false,false,true,true);
		plateau[0][2]=new PieceChemin(virage,false,true,true,false);
		plateau[0][3]=new PieceFixe(arrive_ball,false,true,false,false);
		//l1
		plateau[1][0]=new PieceChemin(horizontal,true,false,true,false);
		plateau[1][1]=new PieceChemin(vertical,false,true,false,true);
		plateau[1][2]=new PieceChemin(vertical,false,true,false,true);
		plateau[1][3]=new PieceChemin(case_vide,true,true,true,true);
		//L2
		plateau[2][0]=new PieceChemin(virage_3,true,false,false,true);
		plateau[2][1]=new PieceChemin(vertical,false,true,false,true);
		plateau[2][2]=new PieceChemin(vertical,false,true,false,true);
		plateau[2][3]=new PieceChemin(virage_3,true,false,false,true);
		//L3
		plateau[3][0]=new PieceChemin(case_vide,true,true,true,true);
		plateau[3][1]=new PieceChemin(vertical,false,true,false,true);
		plateau[3][2]=new PieceChemin(horizontal,true,false,true,false);
		plateau[3][3]=new PieceFixe(depart_droite,false,true,false,false);
		//L4
	}

	public void initPlateauG11()
	{
		plateau[0][0]=new PieceChemin(vide,false,false,false,false);
		plateau[0][1]=new PieceChemin(virage_etoile,false,false,true,true);
		plateau[0][2]=new PieceChemin(vertical,false,true,false,true);
		plateau[0][3]=new PieceFixe(arrive_ball,false,true,false,false);
		//l1
		plateau[1][0]=new PieceChemin(case_vide,true,true,true,true);
		plateau[1][1]=new PieceChemin(horizontal,true,false,true,false);
		plateau[1][2]=new PieceChemin(case_vide,true,true,true,true);
		plateau[1][3]=new PieceChemin(vertical,false,true,false,true);
		//L2
		plateau[2][0]=new PieceChemin(vertical,false,true,false,true);
		plateau[2][1]=new PieceChemin(horizontal,true,false,true,false);
		plateau[2][2]=new PieceChemin(virage,false,true,true,false);
		plateau[2][3]=new PieceChemin(virage_3,true,false,false,true);
		//L3
		plateau[3][0]=new PieceChemin(vertical,false,true,false,true);
		plateau[3][1]=new PieceChemin(virage_3,true,false,false,true);
		plateau[3][2]=new PieceChemin(vertical,false,true,false,true);
		plateau[3][3]=new PieceFixe(depart_droite,false,true,false,false);

	}
	//---------------------------------------------------------------------------

	//---------------------------------------------------------------------------

	//--------------------------------------------------------------------------------------
	// Cette méthode nous retourne une liste de cases qui sont jouables
	// Ie: on peut faire un échange entre les pieces de cette liste et notre case actuel !
	//--------------------------------------------------------------------------------------

	public HashMap<Cordonnee,Piece> piecesJouables(Piece piece,Cordonnee cordonnee)
	{
		HashMap<Cordonnee,Piece> cases=new HashMap<>();

        int[] pieces_frontieres={0,1,
		                         0,-1,
						         1,0,
							    -1,0};

        if(!piece.estVide(piece.image))
		{
			for(int i=0;i!=pieces_frontieres.length;i++)
			{
				int dx=pieces_frontieres[i];
				int dy=pieces_frontieres[++i];

				int newX=dx+cordonnee.getX();
				int newY=dy+cordonnee.getY();

				if(newX>=0 && newX<height && newY>=0 && newY<width)
				{
					Piece p=plateau[newX][newY];
					Cordonnee coord1=new Cordonnee(newX,newY);


					if(p.estVide(p.image) && piece.estDeplacable())
					{
						cases.put(coord1,p);
					}

				}

			}
		}

		return cases;
	}


	//--------------------------------------------------------------------------------------
	//
	// Pourquoi un LinkedHashMap soit (LHM) et non pas une autre structure ??!
	//
	//    ** Nous utilisons un (LHM) car cela nous aidera apres à retourner notre liste
	//    ** de cases dans l'ordre qui convient ! dans notre cas elle est sous forme LIFO
	//		 (Last in First out ) ou bien en francais : Pile !
	//
	//    ** Cette idée été produite essentiellement quand on a réalisé le mouvement en arriere
	//    ** qui est bien sur implémenté dans la Fen_Partie dans le button (revenir)
	//
	//------------------------------------------------------------------------------------------
	//
	// Pourquoi donc pas une LinkedList et qu'on applique les méthodes getFirst(),getLast...etc ??!
	//    ** On sera pas sur que n' y aura pas des doublons !
	//    ** car ceci peut se produire que le joueur échange les deux pieces deux fois et qu'il revienne
	//    ** au meme cas de départ !
	//    ** Par contre là l'idée d'utiliser un HashMap est evidente puisque on s'intéresse
	//    ** qu'au premier pas fait !
	//
	//------------------------------------------------------------------------------------------------

	//--------------------------------------------------------------------------------------
	// Cette méthode nous retourne une liste sous forme de "Linked de cases qui sont
	// Ie: on peut faire un échange entre les pieces de cette liste et notre case actuel !
	//--------------------------------------------------------------------------------------

	public LinkedHashMap<Cordonnee,Piece> echangerPieces(Piece pieceD, Cordonnee coordonneD,Piece pieceA,Cordonnee cordonneeA)
	{
		LinkedHashMap<Cordonnee,Piece> res=new LinkedHashMap<>();

		Cordonnee c1=cordonneeA;
		Piece p1=pieceA;



			for (int i=0 ; i!=width ; i++)
			{
				for (int j=0 ; j!=height ; j++)
				{
					Cordonnee c2=new Cordonnee(i,j);
					Piece p2=plateau[i][j];

					if(!c2.equals(c1) && !c2.equals(coordonneD))
					{
						res.put(c2,p2);
					}
					else if(c2.equals(c1))
					{

						res.put(c1,pieceD);
					}
					else if(c2.equals(coordonneD))
					{
						res.put(coordonneD,p1);
					}

			}
		}




		plateau[coordonneD.getX()][coordonneD.getY()]=p1;
		plateau[c1.getX()][c1.getY()]=pieceD;

		return res;

	}


	//--------------------------------------------------------------------------------------
	// Cette méthode nous retourne la piece
	// ayant comme coordonées cordonnee=(x,y)
	//--------------------------------------------------------------------------------------

	public Piece pieceDeCoordonne(Cordonnee cordonnee)
	{
		Piece piece;
		piece=plateau[cordonnee.getX()][cordonnee.getY()];
		return piece;
	}


	//--------------------------------------------------------------------------------------
	// Cette méthode nous retourne vrai si la piece
	// ayant comme coordonées (x,y) est une case vide
	//--------------------------------------------------------------------------------------

	public boolean pieceEstVide(int x, int y)
	{
		Piece p=plateau[x][y];
		return p.estVide(p.image);

	}

	//--------------------------------------------------------------------------------------

	//--------------------------------------------------------------------------------------

	public Cordonnee suivantPiece(int x, int y, String s) {
		switch(s) {
			case "Haut":
				x=x-1;
				if(x>=0 && x<height) {
					return new Cordonnee(x, y);
				}
				break;
			case "Bas":
				x=x+1;
				if(x>=0 && x<height) {
					return new Cordonnee(x, y);
				}
				break;
			case "Gauche":
				y=y-1;
				if(y>=0 && y<width) {
					return new Cordonnee(x, y);
				}
				break;
			case "Droite":
				y=y+1;
				if(y>=0 && y<width) {
					return new Cordonnee(x, y);
				}
				break;
		}

		return null;
	}


	public void rotationPiece(Cordonnee c, Image img)
	{
		int x=c.getX(),y=c.getY();

		Piece piece=getPiece(x,y);

		piece.setImage(img);
		piece.setHaut(!piece.ouvertHaut());
		piece.setGauche(!piece.ouvertGauche());
		piece.setBas(!piece.ouvertBas());
		piece.setDroite(!piece.ouvertDroite());

		setPiece(piece,x,y);

	}



	public Piece getPiece(int x,int y) {
		return plateau[x][y];
	}

	public void setPiece(Piece p,int x,int y) {

		 plateau[x][y]=p;
	}


	public Piece[][] getPlateau()
	{
		return plateau;
	}

	public void setPlateau(Piece[][] plateau) {
		this.plateau = plateau;
	}


	public int getWidth() {
		return width;
	}

	public int getMouvementBut() {
		return mouvementBut;
	}

	public int getHeight() {
		return height;
	}






}