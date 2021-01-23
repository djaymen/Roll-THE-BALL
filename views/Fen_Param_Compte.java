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
import roll_the_ball.models.RollTheBall;
import roll_the_ball.models.Utilisateur;

import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;


public class Fen_Param_Compte implements Initializable
{

    @FXML
    public AnchorPane scene;

    @FXML
    private AnchorPane container;

    @FXML
    private JFXTextField identifiant_txt;



    @FXML
    private JFXPasswordField nouveau_txt,ancien_txt;



    @FXML
    private JFXButton btn_retour;

    @FXML
    private JFXButton btn_valider;

    @FXML
    void retour(ActionEvent event) throws Exception
    {
        Fen_Menu.changerScene(this,event,"menu",Fen_Menu.WIDTH,Fen_Menu.HEIGHT);
    }

    @FXML
    void valider(ActionEvent event) throws Exception
    {
        LinkedList<Niveau> niveaux=new LinkedList<>();

        Stage fenetre=(Stage)((Node)event.getSource()).getScene().getWindow();

        if(ancien_txt.getLength()!=0 && nouveau_txt.getLength()!=0 && identifiant_txt.getLength()!=0) {
            Utilisateur u=Fen_Menu.rollTheball3.retourneUtilisateur(identifiant_txt.getText());

            if(u!=null)
            {
                if(u.verifierMotDePasse(ancien_txt.getText()))
                {

                    u.rinitialiserMDP(nouveau_txt.getText());
                    Fen_Menu.rollTheball3.removeutilisateur(u);
                    Fen_Menu.rollTheball3.ajouterUtilisateur(u);
                    RollTheBall r=Fen_Menu.rollTheball3;
                    r.sauver_objets();
                    Fen_Niveaux tutoScene=(Fen_Niveaux) Fen_Menu.changerScene(this,event,"niveaux",Fen_Menu.WIDTH,Fen_Menu.HEIGHT);
                    tutoScene.setUser(u);
                }
                else
                {
                    Fen_Alert.display(fenetre,"Mot De Passe Incorrect", "Erreur");

                }
            }
            else
            {
                Fen_Alert.display(fenetre,"cette identifiant n'existe pas", "Erreur");
            }


        }
        else
        {
            Fen_Alert.display(fenetre,"veuillez remplir tous les champs", "Erreur");
        }

    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}