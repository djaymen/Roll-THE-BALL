package roll_the_ball.views;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import roll_the_ball.models.Cordonnee;
import roll_the_ball.models.Niveau;
import roll_the_ball.models.Partie;
import roll_the_ball.models.Piece;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class Fen_NiveauSuivant implements Initializable
{




    private Fen_Partie partie;

    @FXML
    void niveaux(javafx.event.ActionEvent event) throws Exception
    {
        Fen_Menu.changerScene(this,event,"niveaux",Fen_Menu.WIDTH,Fen_Menu.HEIGHT);
    }

    @FXML
    void rejouer(javafx.event.ActionEvent event) throws Exception
    {
        Fen_Partie fen_partie= (Fen_Partie)Fen_Menu.changerScene(this,event,
                "partie",Fen_Menu.WIDTH,Fen_Menu.HEIGHT);
        String titre_niveau=partie.getTitre_partie();
        fen_partie.setTitre_partie(titre_niveau);
        fen_partie.setPartie(partie.getPartie(),partie.id_niveau);

        fen_partie.recommencer();

    }

    @FXML
    void suivant(javafx.event.ActionEvent event) throws Exception
    {
        Fen_Partie fen_partie= (Fen_Partie)Fen_Menu.changerScene(this,event,
                "partie",Fen_Menu.WIDTH,Fen_Menu.HEIGHT);

        partie.id_niveau++;
        if(partie.id_niveau==12)
        {
            partie.id_niveau=0;
        }
        partie.initPlateauxBis(partie.id_niveau);
        String titre_niveau="Niveau "+partie.id_niveau;
        fen_partie.setTitre_partie(titre_niveau);
        fen_partie.setPartie(partie.getPartie(),partie.id_niveau);
        fen_partie.recommencer();


    }


    public void setPartie(Fen_Partie partie)
    {
        this.partie=partie;

        partie.initPlateaux();
        partie.repaintPlateau(partie.plateaux.getFirst(),0,0);
        partie.setPartie(partie.getPartie(),partie.id_niveau);

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }
}