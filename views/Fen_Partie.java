package roll_the_ball.views;


import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import roll_the_ball.models.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class Fen_Partie implements Initializable
{

    @FXML
    public AnchorPane scene;

    @FXML
    private Label scoreBut,scoreLabel;

    @FXML
    private Label mouvement_meilleur,timeLabel;

    @FXML
    public AnchorPane container;

    @FXML
    private Label titre_partie;

    public GridPane plateau_pane;


    public int id_niveau;
    public long temps;
    private Boule boule;
    private PathTransition ballAnimation=new PathTransition();
    private LinkedList<Case> liste=new LinkedList<Fen_Partie.Case>();

    // Model
    private Partie partie;
    private HashMap<Cordonnee,Piece> casesVoisines=null; // Les cases voisines de la carte en cours
    private Case caseSource=null;                        // La case qu'on à cliqué
    LinkedList<LinkedList<Case>> plateaux= new LinkedList<>();


    public   int WIDTH_PIECE=150,HEIGHT_PIECE=150;
    public   int NB_PIECES_X,NB_PIECES_Y;
    public   int WIDTH_PLATEAU,HEIGHT_PLATEAU;
    public   int mouvements_cpt=0;

    @FXML
    private HBox score_pane;


    private AnimationTimer timer;

    public void initialize(URL location, ResourceBundle rs)
    {

        container.getParent().getStyleClass().remove("fond_pause");
        initialiserCloche();
        timer.start();
    }




    public void afficheScore()
    {
        int score=100;


        long tempsActuel=temps;
        long tempsMin=10;

        int mouvActuel=Integer.parseInt(mouvement_meilleur.getText());
        int mouvMin=Integer.parseInt(scoreBut.getText());
        long scoreBis=0;

        if(temps<=tempsMin)
        {
            if(mouvActuel<=mouvMin)
            {
                scoreBis=0;
            }
            else
            {
                scoreBis=(tempsActuel+mouvMin/mouvActuel)%score;
            }


        }
        else
        {

            if(mouvActuel<=mouvMin)
            {
                scoreBis=(tempsMin/tempsActuel+mouvActuel)%score;
            }
            else
            {
                int tmp=mouvActuel-mouvMin;
                scoreBis=(tempsMin/tempsActuel+mouvActuel)%score+tmp;
            }
        }
//        scoreBis=(tempsActuel+mouvActuel)%score;
        score-=scoreBis;

        scoreLabel.setText(Integer.toString(score));

    }


    public void initialiserCloche()
    {

        timer = new AnimationTimer() {
            private long timestamp;
            private long time = -1;
            private long fraction = 0;

            @Override
            public void start() {
                // current time adjusted by remaining time from last run
                timestamp = System.currentTimeMillis() - fraction;
                super.start();
            }

            @Override
            public void stop() {
                super.stop();
                // save leftover time not handled with the last update
                fraction = System.currentTimeMillis() - timestamp;
            }

            @Override
            public void handle(long now) {
                long sec,min;
                String secStr,minStr;
                String timeFormat;


                long newTime = System.currentTimeMillis();

                if (timestamp + 1000 <= newTime) {


                    long deltaT = (newTime - timestamp) / 1000;
                    time += deltaT;

                    sec=time%60;
                    min=time/60;


                    timestamp += 1000 * deltaT;



                    if ( sec < 59 )
                    {
                        sec++;
                    }
                    else if ( sec >= 59 && min < 59 )
                    {
                        sec=0;
                        min++;
                    }


                    if(sec/10==0)
                    {
                        secStr="0"+sec;
                    }
                    else
                    {
                        secStr=""+sec;

                    }

                    if(min/10==0)
                    {
                        minStr="0"+min;
                    }
                    else
                    {
                        minStr=min+"";
                    }

                    timeFormat=minStr+":"+secStr;
                    timeLabel.setText(timeFormat);
                }

                temps=time;
            }
        };

    }

    @FXML
    public void revenir()
    {

        if(plateaux.size()>1)
        {
            mouvements_cpt--;
            repaintPlateau(plateaux.get(1),0,0);
            plateaux.removeFirst();
            caseSource=null;
            casesVoisines=null;
        }

    }


    @FXML
    void resume(ActionEvent event)
    {
        timer.start();
        container.getStyleClass().remove("plateauEnPause");
        container.getParent().getStyleClass().remove("fond_pause");

    }

    @FXML
    void pause(ActionEvent event)
    {
        timer.stop();
        container.getStyleClass().add("plateauEnPause");
        container.getParent().getStyleClass().add("fond_pause");

    }

    @FXML
    void recommencer()
    {
        mouvements_cpt=0;
        repaintPlateau(plateaux.getLast(),0,0);

        initPlateaux();

        caseSource=null;
        casesVoisines=null;
    }


    @FXML
    void sauvegarder(ActionEvent event)
    {
        RollTheBall r=Fen_Menu.rollTheball3;
        r.sauver_objets();
    }



    public void setTitre_partie(String titre)
    {
        titre_partie.setText(titre);
    }

    public String getTitre_partie()
    {
        return titre_partie.getText();
    }

    public void setPartie(Partie partie,int id_niveau)
    {
        this.partie=partie;
        this.id_niveau=id_niveau;

        // Model
        Niveau niveau=partie.getNiveau();
        scoreBut.setText(Integer.toString(niveau.getScoreBut()));
        int x=0,y=0;

        NB_PIECES_X=niveau.getPlateau().getWidth();    // Le nombre de pieces par ligne !
        NB_PIECES_Y=niveau.getPlateau().getHeight();   // Le nombre de pieces par colonne !

        WIDTH_PLATEAU=WIDTH_PIECE*NB_PIECES_X;
        HEIGHT_PLATEAU=HEIGHT_PIECE*NB_PIECES_Y;

        plateau_pane=new GridPane();

        plateau_pane.setPrefSize(WIDTH_PLATEAU,HEIGHT_PLATEAU);
        plateau_pane.setPadding(new Insets(13,38,13,38));
        plateau_pane.setHgap(1);
        plateau_pane.setVgap(1);

        // Initialisation du Plateau (GridPane)


        LinkedList<Case> casesBis=new LinkedList<>();

        for (int i=0 ; i!=NB_PIECES_X ; i++)
        {
            for (int j=0 ; j!=NB_PIECES_Y ; j++)
            {
                Cordonnee cordonnee=new Cordonnee(i,j);
                Piece piece=niveau.getPlateau().pieceDeCoordonne(cordonnee);

                Case figure=new Case(piece,cordonnee);
                casesBis.add(figure);

                if(figure.getPiece().estUnePieceDepart())
                {
                    x=figure.getCordonnee().getX();
                    y=figure.getCordonnee().getY();
                }

                plateau_pane.add(figure,j,i);
            }
        }

        //plateau_pane.add(boule,y,x);

        plateaux.addFirst(casesBis);
        container.getChildren().add(plateau_pane);
        plateau_pane.setAlignment(Pos.CENTER);

    }


    public void initPlateaux()
    {
        plateaux.clear();

        LinkedList<Case> casesBis=new LinkedList<>();

        for (int i=0 ; i!=NB_PIECES_X ; i++)
        {
            for (int j=0 ; j!=NB_PIECES_Y ; j++)
            {
                Cordonnee cordonnee=new Cordonnee(i,j);
                Piece piece=partie.getNiveau().getPlateau().pieceDeCoordonne(cordonnee);

                Case figure=new Case(piece,cordonnee);
                casesBis.add(figure);


            }
        }

        plateaux.addFirst(casesBis);
    }

    public Partie getPartie() {
        return partie;
    }

    public void afficherFen_Gagnee(MouseEvent event) throws Exception
    {
        Fen_NiveauSuivant tutoScene=(Fen_NiveauSuivant) Fen_Menu.changerScene(this,event,"niveauSuivant",600,418);
        initPlateauxBis(id_niveau);
        tutoScene.setPartie(this);

        Niveau niveau=new Niveau(4,4,id_niveau);
        if(partie.getUtilisateur().getId_utilisateur()!="libre")
        {
            partie.getUtilisateur().getNiveaux().put(id_niveau,niveau);

        }


    }

    // Cette méthode permet d'afficher le plateau initial de chaque partie !

    public void initPlateauxBis(int id_niveau)
    {
        Niveau niveau=new Niveau(4,4,id_niveau);
        partie.setNiveau(niveau);
    }
    // La classe interne Case pour mettre la relation entre la vue et le modele
    // ( répresenter les pieces (le plateau) dans le gridPane du jeu !

    public  class Case extends StackPane
    {
        Piece piece;
        public ImageView imageView;
        Cordonnee cordonnee;

        public Case(Piece piece,Cordonnee cordonnee)
        {
            this.piece=piece;
            this.cordonnee=cordonnee;

            Rectangle bg = new Rectangle(WIDTH_PIECE, HEIGHT_PIECE);
            bg.setOpacity(0.4);

            imageView = new ImageView(piece.getImage());

            imageView.setFitWidth(WIDTH_PIECE);
            imageView.setFitHeight(HEIGHT_PIECE);

            setAlignment(Pos.CENTER);
            getChildren().addAll(bg,imageView);



            setOnMouseEntered(event -> {


            });



            setOnMouseExited(event -> {
                bg.setFill(Color.BLACK);
            });

            setOnMousePressed(event -> { jouerTour();

                try {

                    LinkedList<Cordonnee> liste=testerChemin(event);


                    if(liste!=null)
                    {
                        animation_chemin_gagnant(liste);
                        afficheScore();
                        PauseTransition delay = new PauseTransition(Duration.seconds(3));
                        delay.setOnFinished( event0 ->
                                {
                                    try {
                                        RollTheBall r=Fen_Menu.rollTheball3;
                                        r.sauver_objets();
                                        afficherFen_Gagnee(event);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    ballAnimation.stop();
                                }
                        );

                        delay.play();

                    }


                } catch (FileNotFoundException e) {

                    e.printStackTrace();
                } catch (IOException e) {

                    e.printStackTrace();
                }
                catch (Exception e) {

                    e.printStackTrace();
                }
            });

            setOnMouseReleased(event -> {

            });
        }


        public void animation_chemin_gagnant(LinkedList<Cordonnee> liste) throws InterruptedException
        {

            ballAnimation.setPath(null);
            Polyline ballPath=new Polyline();
            boule=new Boule();


            int cX=liste.get(0).getX();
            int cY=liste.get(0).getY();



            // k= décalage par rapport aux colonnes au début
            // ( par exemple si la case de départ est dans la 2 eme colonne donc
            //   toute la trajectoire sera décalée de 2 cases (150*k)
            // l = meme principe mais cette fois ci par rapport aux lignes

            int k=cY,l=cX;

            double bX=plateau_pane.getChildren().get(4*cX+cY).getLayoutX()-k*150+15,
                    bY=plateau_pane.getChildren().get(4*cX+cY).getLayoutY()-l*150;


            plateau_pane.add(boule,cY,cX);


            ballPath.getPoints().addAll(bX,bY);


            for(int i=1;i<liste.size();i++)
            {
                cX=liste.get(i).getX();
                cY=liste.get(i).getY();


                Node caSe=plateau_pane.getChildren().get(4*cX+cY);


                if(liste.get(i-1).getX()==liste.get(i).getX())
                {
                    bX=caSe.getLayoutX();
                    bY=caSe.getLayoutY()-15;

                }
                else if(liste.get(i-1).getY()==liste.get(i).getY())
                {
                    bX=caSe.getLayoutX()+13;
                    bY=caSe.getLayoutY();
                }


                ballPath.getPoints().addAll(bX-k*150,bY-l*150);

            }




            ballAnimation.setNode(boule);
            ballAnimation.setDuration(Duration.seconds(3));
            ballAnimation.setPath(ballPath);
            ballAnimation.setCycleCount(PathTransition.INDEFINITE);
            ballAnimation.play();
        }

        public void jouerTour()
        {


            // Le cas du premier clique sur la case !
            if(casesVoisines==null)
            {
                // Si L'utilisateur clique sur une piece non jouable (non déplacable)
                // On force la source à null !

                if(getPiece().estVide(imageView.getImage()) || !(getPiece() instanceof Deplacable))
                {
                    caseSource=null;
                }

                if(getPiece() instanceof PieceTournante)
                {
                    if(getPiece().ouvertBas())
                    {
                        imageView.setImage(new Image("roll_the_ball/resources/imgs/rotation.png"));
                    }
                    else
                    {
                        imageView.setImage(new Image("roll_the_ball/resources/imgs/rotation_2.png"));

                    }

                    partie.getNiveau().getPlateau().rotationPiece(getCordonnee(),imageView.getImage());
                    partie.getNiveau().getPlateau().getPiece(getCordonnee().getX(),getCordonnee().getY())
                            .setImage(imageView.getImage());


                }
                // Sinon ( Le joueur clique sur une case jouable !)

                else {

                    // On récupere la liste des pieces voisines de notre case sous forme d'un HashMap
                    // Pour éviter de faire de la vue dans le model le HashMap était nécaissaire !
                    // 'On peut voir d'autre maniere le couple (Coordonnee,Piece) comme étant une Case
                    // mais d'un point de vue modele !


                    HashMap<Cordonnee,Piece> casesVoisines1=partie.getNiveau().getPlateau().piecesJouables(getPiece(),getCordonnee());

                    // Si il y a au moins un voisin (Jouable toujours ) à notre case !
                    if (casesVoisines1!=null)
                    {
                        // Si on a plusieurs cases jouables possibles !
                        // Dans ce cas le deuxieme clique ( le suivant ) sera le décisif !
                        // ie : la source serait notre case courante
                        //      les cases voisines sont les voisins de notre source

                        if(casesVoisines1.size()!=1)
                        {

                            casesVoisines=casesVoisines1;
                            caseSource=this;



                            for (Map.Entry<Cordonnee,Piece> entry:casesVoisines.entrySet())
                            {
                                Cordonnee cordonneeD = entry.getKey();
                                plateau_pane.getChildren().get(4*cordonneeD.getX()+cordonneeD.getY())
                                        .getStyleClass().add("case_verte");
                            }




                        }

                        // Si on en a qu'un seul voisin !
                        // On permute d'une maniere fluide les deux cases !
                        // Par pieceD et coordonneD on veut clairement dire la case destinataire !
                        // Comme il y a q'une seule solution on la connais directement du HashMap des
                        // Voisins ! ( !null )

                        else
                        {
                            echangerDeuxCases(casesVoisines1);

                        }
                    }

                }

            }
            else
            {
                if(casesVoisines.containsKey(getCordonnee()))
                {
                    for (Map.Entry<Cordonnee,Piece> entry:casesVoisines.entrySet())
                    {
                        Cordonnee cordonneeD = entry.getKey();
                        plateau_pane.getChildren().get(4*cordonneeD.getX()+cordonneeD.getY())
                                .getStyleClass().remove("case_verte");
                    }
                    casesVoisines=null;
                    echangerDeuxCases(casesVoisines);

                }
                else
                {
                    for (Map.Entry<Cordonnee,Piece> entry:casesVoisines.entrySet())
                    {
                        Cordonnee cordonneeD = entry.getKey();
                        plateau_pane.getChildren().get(4*cordonneeD.getX()+cordonneeD.getY())
                                .getStyleClass().remove("case_verte");
                    }
                    caseSource=null;
                    casesVoisines=null;
                }

            }
        }

        private void echangerDeuxCases(HashMap<Cordonnee,Piece> casesVoisines1)
        {
            Piece pieceD=null;
            Cordonnee cordonneeD=null;
            HashMap<Cordonnee,Piece> newPlateau;
            LinkedList<Case> cases=new LinkedList<>();
            LinkedList<Case> casesBis=new LinkedList<>();





            /**
             * Si @casesVoisines1 non null ie : on est dans le 1 er clique au plus
             *
             * **/

            if(casesVoisines1!=null)
            {
                // On prends du HashMap nos informations !

                for (Map.Entry<Cordonnee,Piece> entry:casesVoisines1.entrySet())
                {
                    cordonneeD = entry.getKey();
                    pieceD = entry.getValue();
                }

                /**
                 * @newPlateau : le plateau apres l'echange de nos cases !
                 * @cases      : la tronsformation du HashMap en LinkedList !
                 *               juste pour éviter les boucles imbriqués aprés
                 * **/

                newPlateau=partie.getNiveau().getPlateau().echangerPieces(getPiece(),getCordonnee(),pieceD,cordonneeD);




                if(newPlateau!=null)
                {
                    for (Map.Entry<Cordonnee,Piece> entry:newPlateau.entrySet())
                    {
                        cordonneeD=entry.getKey();
                        pieceD=entry.getValue();
                        cases.add(new Case(pieceD,cordonneeD));
                    }

                }
            }

            /**
             * Sinon : il faut maintenant gérer le 2 eme clique ce qui est réalisé dans cette méthode !
             *         la @caseSource étant déjà sauvegardé ( d'ou la nécéssité de la mettre comme attribut
             *         de la classe )
             *         sera permutée avec la case qu'on vient de cliquer desus !
             *         et ceci est bien réalisé seulement si les conditions sont vrai !
             *
             *
             *
             * **/
            else
            {
                newPlateau=partie.getNiveau().getPlateau().echangerPieces(caseSource.piece,caseSource.cordonnee,getPiece(),getCordonnee());

                caseSource=null;

                if(newPlateau!=null)
                {
                    for (Map.Entry<Cordonnee,Piece> entry:newPlateau.entrySet())
                    {
                        Cordonnee c1=entry.getKey();
                        Piece p1=entry.getValue();

                        cases.add(new Case(p1,c1));
                    }


                }
            }
            liste=cases;
            plateaux.addFirst(cases);
            mouvements_cpt++;
            repaintPlateau(cases,0,0);

        }





        public Piece getPiece() {
            return piece;
        }
        public Cordonnee getCordonnee() {
            return cordonnee;
        }



    }



    public void repaintPlateau(LinkedList<Case> cases,int cordx,int cordy)
    {

        Plateau plateau=new Plateau(NB_PIECES_X,NB_PIECES_Y);
        Piece[][] pl=new Piece[NB_PIECES_X][NB_PIECES_Y];
        int x=0,y=0;

        for (int i=0 ; i!=NB_PIECES_X ; i++)
        {
            for (int j=0 ; j!=NB_PIECES_Y ; j++)
            {
                pl[i][j]=cases.get(4*i+j).getPiece();
            }
        }



        plateau.setPlateau(pl);
        partie.getNiveau().setPlateau(plateau);

        plateau_pane.getChildren().removeAll(cases);
        container.getChildren().remove(plateau_pane);
        for (int i=0 ; i!=NB_PIECES_X ; i++)
        {
            for (int j=0 ; j!=NB_PIECES_Y ; j++)
            {
                Case figure=cases.get(4*i+j);
                if(figure.getPiece().estUnePieceDepart())
                {
                    x=figure.getCordonnee().getX();
                    y=figure.getCordonnee().getY();
                }
                plateau_pane.add(figure,j,i);
            }
        }


        // Un petit Effet sonore !!

        String musicFichier="src/roll_the_ball/resources/mp3/swap.mp3";
        Media son=new Media(new File(musicFichier).toURI().toString());
        MediaPlayer mP=new MediaPlayer(son);
        mP.play();


        String str=String.valueOf(mouvements_cpt);
        mouvement_meilleur.setText(str);

        container.getChildren().add(plateau_pane);
    }

    public void retour(ActionEvent event) throws Exception
    {
        Utilisateur user=partie.getUtilisateur();

        Fen_Niveaux niveauScene=(Fen_Niveaux) Fen_Menu.changerScene(this,event,"niveaux",Fen_Menu.WIDTH,Fen_Menu.HEIGHT);
        niveauScene.setUser(user);

    }




    public Cordonnee cordonneeDepart() {
        Cordonnee c;
        int x=0;
        int y=0;
        for (int i=0 ; i!=NB_PIECES_X ; i++)
        {
            for (int j=0 ; j!=NB_PIECES_Y ; j++)
            {

                Case figure=(Case) plateau_pane.getChildren().get(i*4+j);
                if(figure.getPiece().estUnePieceDepart()) {
                    x=i;
                    y=j;
                }


            }
        }
        c=new Cordonnee(x, y);
        return c;
    }

    public LinkedList<Cordonnee> testerChemin(MouseEvent event) throws FileNotFoundException, IOException {
        Niveau niveau=partie.getNiveau();
        LinkedList<Cordonnee> liste=new LinkedList<Cordonnee>();
        Cordonnee dep= cordonneeDepart();
        int x=dep.getX();
        int y=dep.getY();
        liste.add(dep);
        boolean arrive=false;
        boolean not_lien=false;

        Case c=(Case) plateau_pane.getChildren().get(x*4+y);
        String direction=c.getPiece().suiv("Haut");
        Cordonnee c1=partie.getNiveau().getPlateau().suivantPiece(x, y,direction);
        x=c1.getX();
        y=c1.getY();
        while(!arrive && !not_lien) {
            liste.add(c1);

            c=(Case) plateau_pane.getChildren().get(x*4+y);
            if(!partie.getNiveau().getPlateau().pieceEstVide(x, y)){
                if(c.getPiece().estUnePieceArrive() && (partie.getNiveau().getPlateau().getPiece(x, y).complet(direction))) {
                    arrive=true;
                }else {
                    if(partie.getNiveau().getPlateau().getPiece(x, y).complet(direction)) {
                        direction=partie.getNiveau().getPlateau().getPiece(c.getCordonnee().getX(), c.getCordonnee().getY()).suiv(partie.getNiveau().getPlateau().getPiece(c.getCordonnee().getX(), c.getCordonnee().getY()).return_inverse(direction));

                        c1=partie.getNiveau().getPlateau().suivantPiece(x, y,direction);

                        if(c1!=null) {
                            x=c1.getX();
                            y=c1.getY();
                        }else {
                            not_lien=true;
                        }

                    }else {
                        not_lien=true;
                    }
                }

            }else {
                not_lien=true;

            }
        }
        if(!arrive) {
            return null;
        }
        return liste;

    }

    @FXML
    void debutAide(MouseEvent event)
    {
        LinkedList<Case> plateauG=new LinkedList<>();

        for (int i=0;i!=NB_PIECES_X;i++)
        {
            for (int j=0;j!=NB_PIECES_Y;j++)
            {
                Case tmp=new Case(partie.getNiveau().getPlateau_gagnant().getPlateau()[i][j],new Cordonnee(i,j));
                plateauG.add(tmp);
            }
        }
        repaintPlateau(plateauG,0,0);

        caseSource=null;
        casesVoisines=null;
    }

    @FXML
    void finAide(MouseEvent event)
    {
        repaintPlateau(plateaux.getFirst(),0,0);
        caseSource=null;
        casesVoisines=null;

    }





}