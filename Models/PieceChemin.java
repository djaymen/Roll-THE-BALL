package roll_the_ball.models;

import java.io.Serializable;

public class PieceChemin extends Piece implements Deplacable,Serializable
{

	public PieceChemin(String image_path, boolean haut, boolean gauche, boolean bas, boolean droite)
    {
        super(image_path, haut, gauche, bas, droite);
    }

    @Override
    public void deplacer(Cordonnee depart, Cordonnee arrive)
    {

    }
}
