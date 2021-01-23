package roll_the_ball.views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import roll_the_ball.models.RollTheBall;


/**
 * UNIVERSITE PARIS DIDEROT     | 10/02/2019 |
 *
 * Remarques :
 * -----------
 *
 * - Pour la méthode changerScene vous remarquerez qu'elle est définie dans toutes
 * les classes du package Views !
 * Parcontre on a bien pensé à faire une méthode static puisque c'est le meme code qui se répete !
 * Mais il n'était pas possible d y faire puisque à l'intérieure de cette méthode vous remarquerez
 * qu'il y a un appel à la méthode (privée) getClass() de la classe concernée.
 *
 *
 * - Les fichiers fxml sont générés à l'aide de l'outil Scene Builder
 * Mais seulement pour les composants qui sont statiques (toujours dans le meme emplacement comme les bouttons...
 * Par exemple pour l'affichage du plateau dans la partie le code ne l'est pas !
 *
 *
 * - Dans le packages ressources: vous trouverez tout ce qui est utilisé dans notre application
 * et pour le fonctionnement parfait de notre code les liens sont relatifs.
 *
 *
 * - Si vous utilisez un IDE (Eclipse,IntellIj...) n'oubliez pas de joindre les (.jar) comme bibliotheques
 * externes.
 * les (.jar) sont accessible via le chemin : roll_the_ball.ressources.jars.*
 *
 *
 * - Pour le moment on a fixé la taille de la fenetre pour s'intérésser beaucoup plus sur la partie du jeu !
 * cela est suceptible d'etre changer vers une application plus responsive.
 *
 *
 *
 *
 * **/


public class Fen_Lanceur extends Application{


    static  Scene scene ;


    @Override
    public void start(Stage fenetre) throws Exception
    {

        Parent root =FXMLLoader.load(getClass().getResource("../resources/fxmls/menu.fxml"));
        Scene scene0 = new Scene(root, Fen_Menu.WIDTH, Fen_Menu.HEIGHT);

        scene=scene0;
       // partie de sauvguarde
        
        RollTheBall r=new RollTheBall();
        Fen_Menu.rollTheball3.setUtilisateurs( r.charger_objets());
      
        fenetre.setTitle("Roll The Ball");
        fenetre.setScene(scene);

        javafx.geometry.Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        fenetre.setX((screenBounds.getWidth() - Fen_Menu.WIDTH) / 2);
        fenetre.setY((screenBounds.getHeight() - Fen_Menu.HEIGHT) / 2);


        fenetre.setResizable(false);
        fenetre.show();
    }


    public static void main(String... args)
    {
        launch(args);
    }


}
