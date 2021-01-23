package roll_the_ball.views;


import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import roll_the_ball.models.Niveau;
import roll_the_ball.models.Partie;
import roll_the_ball.models.Utilisateur;

import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;


public class Fen_Niveaux implements Initializable
{

    @FXML
    public AnchorPane scene;

    @FXML
    private AnchorPane container;

    @FXML
    private JFXButton btn_retour;

    @FXML
    private Button btn_niveau0,btn_niveau1,btn_niveau2,
                      btn_niveau3,btn_niveau4,btn_niveau5,
                      btn_niveau6,btn_niveau7,btn_niveau8,
                      btn_niveau9,btn_niveau10,btn_niveau11;





    private Utilisateur user;



    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

    }


    public void setUser(Utilisateur u)
    {

        this.user=u;
        int nb=12,i=0;

        if(u.getNiveaux()!=null)
        {
            nb=u.getNiveaux().size();
        }
        LinkedList<Button> btns=new LinkedList<>();

        btns.add(btn_niveau0);btns.add(btn_niveau1);btns.add(btn_niveau2);
        btns.add(btn_niveau3);btns.add(btn_niveau4);btns.add(btn_niveau5);
        btns.add(btn_niveau6);btns.add(btn_niveau7);btns.add(btn_niveau8);
        btns.add(btn_niveau9);btns.add(btn_niveau10);btns.add(btn_niveau11);


        while (i<12)
        {
            btns.get(i).setDisable(++i>nb);
        }
    }


    @FXML
    void tutorial(javafx.event.ActionEvent event) throws Exception
    {
        // Initialisation du Niveau 0
        Niveau niveau0=new Niveau(4,4,0);
        accederPartie(event,niveau0,"Tutorial",0);

    }

    @FXML
    void niveau1(javafx.event.ActionEvent event) throws Exception
    {
        // Initialisation du Niveau 1
        Niveau niveau1=new Niveau(4,4,1);
        accederPartie(event,niveau1,"Niveau 1",1);

    }

    @FXML
    void niveau2(javafx.event.ActionEvent event) throws Exception
    {
        // Initialisation du Niveau 2
        Niveau niveau2=new Niveau(4,4,2);
        accederPartie(event,niveau2,"Niveau 2",2);

    }

    @FXML
    void niveau3(javafx.event.ActionEvent event) throws Exception
    {
        // Initialisation du Niveau 3
        Niveau niveau3=new Niveau(4,4,3);
        accederPartie(event,niveau3,"Niveau 3",3);

    }

    @FXML
    void niveau4(javafx.event.ActionEvent event) throws Exception
    {
        // Initialisation du Niveau 4
        Niveau niveau4=new Niveau(4,4,4);
        accederPartie(event,niveau4,"Niveau 4",4);
    }

    @FXML
    void niveau5(javafx.event.ActionEvent event) throws Exception
    {
        // Initialisation du Niveau 1
        Niveau niveau5=new Niveau(4,4,5);
        accederPartie(event,niveau5,"Niveau 5",5);

    }

    @FXML
    void niveau6(javafx.event.ActionEvent event) throws Exception
    {
        // Initialisation du Niveau 1
        Niveau niveau6=new Niveau(4,4,6);
        accederPartie(event,niveau6,"Niveau 6",6);

    }

    @FXML
    void niveau7(javafx.event.ActionEvent event) throws Exception
    {
        // Initialisation du Niveau 1
        Niveau niveau7=new Niveau(4,4,7);
        accederPartie(event,niveau7,"Niveau 7",7);

    }
    @FXML
    void niveau8(javafx.event.ActionEvent event) throws Exception
    {
        // Initialisation du Niveau 1
        Niveau niveau8=new Niveau(4,4,8);
        accederPartie(event,niveau8,"Niveau 8",8);

    }
    @FXML
    void niveau9(javafx.event.ActionEvent event) throws Exception
    {
        // Initialisation du Niveau 1
        Niveau niveau9=new Niveau(4,4,9);
        accederPartie(event,niveau9,"Niveau 9",9);

    }
    @FXML
    void niveau10(javafx.event.ActionEvent event) throws Exception
    {
        // Initialisation du Niveau 1
        Niveau niveau10=new Niveau(4,4,10);
        accederPartie(event,niveau10,"Niveau 10",10);

    }

    @FXML
    void niveau11(javafx.event.ActionEvent event) throws Exception
    {
        // Initialisation du Niveau 1
        Niveau niveau11=new Niveau(4,4,11);
        accederPartie(event,niveau11,"Niveau 11",11);

    }

    public void accederPartie(javafx.event.ActionEvent event,Niveau niveau,String titre_niveau,int id_niveau) throws Exception
    {
        Fen_Partie tutoScene=(Fen_Partie) Fen_Menu.changerScene(this,event,"partie",Fen_Menu.WIDTH,Fen_Menu.HEIGHT);
        tutoScene.setTitre_partie(titre_niveau);
        tutoScene.setPartie(new Partie(user,niveau),id_niveau);

    }


    @FXML
    void retour(javafx.event.ActionEvent event) throws Exception
    {
        Fen_Menu.changerScene(this,event,"menu",Fen_Menu.WIDTH,Fen_Menu.HEIGHT);
    }






}