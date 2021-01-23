package roll_the_ball.views;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import roll_the_ball.models.Niveau;
import roll_the_ball.models.Plateau;
import roll_the_ball.models.Utilisateur;

import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class Fen_Inscription implements Initializable
{

    @FXML
    public AnchorPane scene;

    @FXML
    private JFXTextField id_txt;

    @FXML
    private JFXPasswordField pass_txt;

    @FXML
    private JFXTextField age_txt;

    @FXML
    void retour(ActionEvent event) throws Exception
    {
        Fen_Menu.changerScene(this,event,"menu",Fen_Menu.WIDTH,Fen_Menu.HEIGHT);
    }

    @FXML
    void valider(ActionEvent event) throws Exception
    {
        Stage fenetre=(Stage)((Node)event.getSource()).getScene().getWindow();
        int age=0;
        try {
            age = Integer.parseInt(age_txt.getText());
            if(pass_txt.getLength()!=0 && id_txt.getLength()!=0 && age_txt.getLength()!=0)  {

                if(age>4 && age<120) {
                    if(Fen_Menu.rollTheball3.verifier(id_txt.getText())){
                        Fen_Alert.display(fenetre,"identifiant existant", "Erreur");

                    }else {
                    	HashMap<Integer, Niveau> niveaux=new HashMap<Integer, Niveau>();
                        Plateau plateau=new Plateau(4,4);
                        plateau.initPlateau(0);
                        Niveau niveau0=new Niveau(plateau);
                        // niveaux.add(RollTheBall.niveaux.get(0));
                        niveaux.put(0,niveau0);
                        Utilisateur u=new Utilisateur(id_txt.getText(),pass_txt.getText(),age,niveaux);
                        Fen_Menu.rollTheball3.ajouterUtilisateur(u);
                        Fen_Niveaux tutoScene=(Fen_Niveaux) Fen_Menu.changerScene(this,event,"niveaux",Fen_Menu.WIDTH,Fen_Menu.HEIGHT);
                        tutoScene.setUser(u);
                    }

                }else {

                    Fen_Alert.display(fenetre,"l'age doit etre superieur a 4 ans et inférieur à 120 ans", "Erreur Age");
                }

            }else {
                Fen_Alert.display(fenetre,"veuillez remplir tous les champs", "Erreur");
            }
        }
        catch (Exception e) {
            Fen_Alert.display(fenetre,"l'âge doit etre un nombre ", "Erreur Age");
        }

    }
    




    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
