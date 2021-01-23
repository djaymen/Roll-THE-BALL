package roll_the_ball.views;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXSlider;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class Fen_Sons implements Initializable {
    @FXML
    public AnchorPane scene;

    @FXML
    private AnchorPane container;

    @FXML
    private JFXButton btn_retour;

    @FXML
    private JFXButton btn_valider;

    @FXML
    private JFXComboBox<String> musique;

    @FXML
    private JFXSlider volume;

    String numMusique;
    String musicFichier;
    Media son;
    static MediaPlayer mP;

    @Override
    public void initialize(URL location, ResourceBundle resources)

    {


        musicFichier="src/roll_the_ball/resources/mp3/1.mp3";
        son=new Media(new File(musicFichier).toURI().toString());
        mP=new MediaPlayer(son);
//        mP.play();

        volume.setValue(mP.getVolume()*100);

        initMusiqueListe();

        volume.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                mP.setVolume(volume.getValue());

            }
        });

    }

    @FXML
    void musiqueActuelle(ActionEvent event) {
        if (!musique.getValue().isEmpty())
        {
             mP.stop();
             numMusique=musique.getValue().charAt(12)+"";
             musicFichier="src/roll_the_ball/resources/mp3/"+numMusique+".mp3";
             son=new Media(new File(musicFichier).toURI().toString());
             mP=new MediaPlayer(son);
             mP.play();
             volume.setValue(50);
        }
    }



    public void initMusiqueListe()
    {
        musique.getItems().addAll("   Musique 01  ","   Musique 02  ","   Musique 03  ");
    }


    @FXML
    void retour(ActionEvent event)
    {

        try {
            mP.stop();
            Fen_Menu.changerScene(this,event,"menu",Fen_Menu.WIDTH,Fen_Menu.HEIGHT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void valider(ActionEvent event) {

        Fen_Menu.musiquePlayer=mP;

    }

}

