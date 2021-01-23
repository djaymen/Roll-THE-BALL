package roll_the_ball.models;

import java.io.Serializable;

public class Partie implements Serializable {


    Utilisateur utilisateur;
    Niveau niveau;


    public Partie(Utilisateur utilisateur, Niveau niveau)
    {
        this.utilisateur = utilisateur;
        this.niveau = niveau;
    }





    public Utilisateur getUtilisateur() {
        return utilisateur;
    }
    public Niveau getNiveau() {
        return niveau;
    }
    public void setNiveau(Niveau niveau) {
        this.niveau = niveau;
    }
}
