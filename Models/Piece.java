package roll_the_ball.models;



import javafx.scene.image.Image;

import java.io.Serializable;
import java.util.Objects;

public abstract class Piece implements Serializable
{
	public Image image;


	private boolean haut;
	private boolean gauche;
	private boolean bas;
	private boolean droite;


	public Piece(String image_path,boolean haut, boolean gauche, boolean bas, boolean droite)
	{
		this.droite = droite;
		this.haut = haut;
		this.gauche = gauche;
		this.bas = bas;


		try
		{
			Image img =new Image(image_path);
			this.image=img;

		} catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("Probleme avec l'import de l'image !");
		}
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Piece piece = (Piece) o;
		return haut == piece.haut &&
				gauche == piece.gauche &&
				bas == piece.bas &&
				droite == piece.droite &&
				Objects.equals(image, piece.image);
	}



	public boolean estVide(Image image)
	{
		boolean ok;
		ok=image.impl_getUrl().contains("case_vide");

		return ok;
	}

	public boolean estDeplacable()
	{

		return this instanceof PieceChemin;
	}

	public boolean estUnePieceDepart()
	{
		String path=this.getImage().impl_getUrl();
		return path.contains("depart");
	}

	public boolean estUnePieceArrive ()
	{
		String path=this.getImage().impl_getUrl();
		return path.contains("arrive");
	}



	public String suiv(String depart) {
		//System.out.println(this);
		switch(depart) {
			case "Haut":
				if(ouvertBas())
					return "Bas";
				if(ouvertDroite())
					return "Droite";
				if(ouvertGauche())
					return "Gauche";

				break;
			case "Bas":
				if(ouvertHaut())
					return "Haut";
				if(ouvertDroite())
					return "Droite";
				if(ouvertGauche())
					return "Gauche";

				break;
			case "Gauche":
				if(ouvertBas())
					return "Bas";
				if(ouvertDroite())
					return "Droite";
				if(ouvertHaut())
					return "Haut";

				break;
			case "Droite":
				if(ouvertBas())
					return "Bas";
				if(ouvertGauche())
					return "Gauche";
				if(ouvertHaut())
					return "Haut";

				break;
		}
		return depart;
	}
	public boolean complet(String s) {
		switch(s) {
			case "Haut":
				return ouvertBas();
			case "Bas":
				return ouvertHaut();
			case "Droite":
				return ouvertGauche();
			case "Gauche":
				return ouvertDroite();
		}
		return false;
	}
	public String return_inverse(String s) {
		//	System.out.println("return inverse :"+s);
		switch(s) {
			case "Haut":
				//System.out.println("Bas");
				return "Bas";
			case "Bas":
				//System.out.println("Haut");
				return "Haut";
			case "Droite":
				//System.out.println("Gauche");
				return "Gauche";
			case "Gauche":
				//System.out.println("Droite");
				return "Droite";
		}
		return s;
	}

	public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
		this.image = image;
	}


	public boolean ouvertHaut() {
		return haut;
	}

	public void setHaut(boolean haut) {
		this.haut = haut;
	}

	public boolean ouvertGauche() {
		return gauche;
	}

	public void setGauche(boolean gauche) {
		this.gauche = gauche;
	}

	public boolean ouvertBas() {
		return bas;
	}

	public void setBas(boolean bas) {
		this.bas = bas;
	}

	public boolean ouvertDroite() {
		return droite;
	}

	public void setDroite(boolean droite) {
		this.droite = droite;
	}
}
