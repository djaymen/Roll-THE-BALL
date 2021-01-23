package roll_the_ball.views;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPopup;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
import roll_the_ball.models.RollTheBall;
import roll_the_ball.models.Utilisateur;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Fen_Menu implements Initializable{
    public static RollTheBall rollTheball3=new RollTheBall();


//    public static final double WIDTH=Toolkit.getDefaultToolkit().getScreenSize().width*0.75,
//            HEIGHT=Toolkit.getDefaultToolkit().getScreenSize().height*0.9;

    public static final double WIDTH=1280,
            HEIGHT=720;


    @FXML
    private AnchorPane scene;

    @FXML
    private AnchorPane menu_pane;


    @FXML
    private AnchorPane container;



    @FXML
    private Button btn_param;

    @FXML
    private Label titre_pane;
    static MediaPlayer musiquePlayer;
    private boolean param_bool=false;

    Timeline btn_param_animation=new Timeline();
    Timeline titre_animation=new Timeline();

    public void initialize(URL location, ResourceBundle rs)
    {
        if(musiquePlayer!=null)
        {
            musiquePlayer.play();
        }
        initAnimations();
    }


    public void initAnimations()
    {
        // Annimation du bouton parametres

        btn_param_animation=new Timeline();


        btn_param_animation.getKeyFrames().addAll(new KeyFrame(Duration.ZERO, new KeyValue(btn_param.rotateProperty(),0)
                ),
                new KeyFrame(Duration.millis(1500.0d)
                        ,new KeyValue(btn_param.rotateProperty(),180)
                ));

        btn_param_animation.setCycleCount(Timeline.INDEFINITE);

        btn_param_animation.play();


        // Annimation du Titre

        titre_animation=new Timeline();


        titre_animation.getKeyFrames().addAll(new KeyFrame(Duration.ZERO, new KeyValue(titre_pane.scaleZProperty(),0)
                ),
                new KeyFrame(Duration.millis(2500.0d)
                        ,new KeyValue(titre_pane.scaleZProperty(),1.5)
                ));

        titre_animation.setCycleCount(Timeline.INDEFINITE);

        titre_animation.play();

    }


    public void initPopup()
    {

        AnchorPane param=new AnchorPane();

        Timeline animation1=new Timeline();


        animation1.getKeyFrames().addAll(new KeyFrame(Duration.ZERO, new KeyValue(menu_pane.opacityProperty(),1)
                ),
                new KeyFrame(Duration.millis(1500.0d)
                        ,new KeyValue(menu_pane.opacityProperty(),0)
                ));


        animation1.play();

        Timeline animation2=new Timeline();


        animation2.getKeyFrames().addAll(new KeyFrame(Duration.ZERO, new KeyValue(param.opacityProperty(),0)
                ),
                new KeyFrame(Duration.millis(1500.0d)
                        ,new KeyValue(param.opacityProperty(),1)
                ));

        Timeline animation3=new Timeline();


        animation3.getKeyFrames().addAll(new KeyFrame(Duration.ZERO, new KeyValue(param.opacityProperty(),1)
                ),
                new KeyFrame(Duration.millis(1500.0d)
                        ,new KeyValue(param.opacityProperty(),0)
                ));

        Timeline animation4=new Timeline();


        animation4.getKeyFrames().addAll(new KeyFrame(Duration.ZERO, new KeyValue(menu_pane.opacityProperty(),0)
                ),
                new KeyFrame(Duration.millis(1500.0d)
                        ,new KeyValue(menu_pane.opacityProperty(),1)
                ));


        //menu_pane.getChildren().clear();
        menu_pane.setVisible(false);

        param.setPrefWidth(556);
        param.setPrefHeight(457);
        param.setLayoutX(306);
        param.setLayoutY(132);
        param.getStyleClass().add("carre");


        Button b1,b2,b3,b4;
        javafx.scene.image.ImageView iv1,iv2,iv3;
        Image img1,img2,img3;

        img1=new Image("roll_the_ball/resources/imgs/profil_icon.png");
        img2=new Image("roll_the_ball/resources/imgs/sound_icon.png");
        img3=new Image("roll_the_ball/resources/imgs/theme_icon.png");

        iv1=new ImageView(img1);
        iv2=new ImageView(img2);
        iv3=new ImageView(img3);


        b1=new Button(" Compte ");
        b1.setGraphic(iv1);

        b2=new Button(" Sons   ");
        b2.setGraphic(iv2);

//        b3=new Button(" Theme   ");
//        b3.setGraphic(iv3);

        b4=new Button(" Retour   ");

        b1.getStyleClass().add("btn_aide");
        b2.getStyleClass().add("btn_aide");
        //b3.getStyleClass().add("btn_aide");
        b4.getStyleClass().add("btn_retour");


        b1.setPrefSize(214,102);
        b1.setLayoutX(36);
        b1.setLayoutY(80);

        b2.setPrefSize(224,75);
        b2.setLayoutX(300);
        b2.setLayoutY(80);
        b2.setOnAction(event -> {
            try {
                if(musiquePlayer!=null) musiquePlayer.stop();
                Fen_Sons.mP=musiquePlayer;
                changerScene(this,event,"sons_param",WIDTH,HEIGHT);
            } catch (Exception e) {
                e.printStackTrace();
            }


        });
        b1.setOnAction(event -> {
            try {
                changerScene(this,event,"param_compte",WIDTH,HEIGHT);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        });

//        b3.setPrefSize(214,75);
//        b3.setLayoutX(45);
//        b3.setLayoutY(191);
//        b3.setOnAction(event -> {
//
//            scene.getStylesheets().add("roll_the_ball/resources/fxmls/light.css");
//            Parent.
//            try {
//                Fen_Connexion fC=(Fen_Connexion) changerScene(this,"connexion");
//                fC.scene.getStylesheets().add("roll_the_ball/resources/fxmls/light.css");
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//
//
//        });

        b4.setPrefSize(350,75);
        b4.setLayoutX(114);
        b4.setLayoutY(328);
        b4.setOnAction(event -> {
            param.setVisible(false);
            menu_pane.setVisible(true);

            animation3.play();
            animation4.play();
            btn_param_animation.play();


        });

        param.getChildren().addAll(b1,b2,b4);



        container.getChildren().add(param);


        animation1.stop();
        animation2.play();
        btn_param_animation.pause();





    }


    @FXML
    void connexion(ActionEvent event) throws Exception
    {
        changerScene(this,event,"connexion",Fen_Menu.WIDTH,Fen_Menu.HEIGHT);
    }

    @FXML
    void inscription(ActionEvent event) throws Exception
    {
        changerScene(this,event,"inscription",Fen_Menu.WIDTH,Fen_Menu.HEIGHT);
    }

    @FXML
    void mode_libre(ActionEvent event) throws Exception {
        Utilisateur u=new Utilisateur("libre");
        Fen_Niveaux tutoScene=(Fen_Niveaux) changerScene(this,event,"niveaux",Fen_Menu.WIDTH,Fen_Menu.HEIGHT);
        tutoScene.setUser(u);

    }

    @FXML
    void quitter(ActionEvent event) throws FileNotFoundException, IOException, ClassNotFoundException
    {
        Stage fenetre=(Stage)((Node)event.getSource()).getScene().getWindow();
        Fen_Alert.display(fenetre," Etes vous sur de vouloir quitter ? ","Quitter");
    }


    @FXML
    void parametres(ActionEvent event) {

        initPopup();
    }

    static Initializable changerScene(Object o,String chemin) throws Exception
    {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(o.getClass().getResource("../resources/fxmls/"+chemin+".fxml"));
        return loader.getController();
    }

    static Initializable changerScene(Object o ,Event event, String chemin,double width,double height) throws Exception
    {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(o.getClass().getResource("../resources/fxmls/"+chemin+".fxml"));
        Parent parent = loader.load();

        Scene scene=new Scene(parent,width,height);
        Stage fenetre=(Stage)((Node)event.getSource()).getScene().getWindow();

        Timeline animation1=new Timeline();


        animation1.getKeyFrames().addAll(new KeyFrame(Duration.ZERO, new KeyValue(fenetre.opacityProperty(),1)
                ),
                new KeyFrame(Duration.millis(1000.0d)
                        ,new KeyValue(fenetre.opacityProperty(),0)
                ));


        animation1.play();

        Timeline animation2=new Timeline();


        animation2.getKeyFrames().addAll(new KeyFrame(Duration.ZERO, new KeyValue(fenetre.opacityProperty(),0)
                ),
                new KeyFrame(Duration.millis(1000.0d)
                        ,new KeyValue(fenetre.opacityProperty(),1)
                ));

        animation1.stop();
        animation2.play();
        javafx.geometry.Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        fenetre.setX((screenBounds.getWidth() - width) / 2);
        fenetre.setY((screenBounds.getHeight() - height) / 2);


        fenetre.setScene(scene);

        return loader.getController();
    }







}