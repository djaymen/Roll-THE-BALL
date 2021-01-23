package roll_the_ball.models;

import java.io.Serializable;

public class PieceTournante extends PieceFixe implements Serializable
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 824938850876204490L;

	public PieceTournante(String image_path, boolean haut, boolean gauche, boolean bas, boolean droite)
    {
        super(image_path, haut, gauche, bas, droite);
    }
}
