/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Controlleur.Bouton;
import Controlleur.Souris;
import Controlleur.Touche;
import javafx.application.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.BorderPane;
import Modele.Arbitre;
import Controlleur.*;
import Joueurs.Joueur;
import Joueurs.Ordinateur;
import Modele.Point;
import javafx.collections.FXCollections;
import javafx.event.*;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 *
 * @author grandmax
 */
public class Interface extends Application{
    public final static int BOUTTON_NOUVEL = 0;
    public final static int BOUTTON_REFAIRE = 1;
    public final static int BOUTTON_ANNULER = 2;
    public final static int BOUTTON_SAUVEGARDER = 3;
    public final static int BOUTON_AIDE = 4;
    public final static int BOUTON_CHARGER = 5;
    public final static int BOUTON_MENU = 6;
    public final static int BOUTON_COMMENCER = 7;
    public final static int BOUTON_QUITTER = 7;

    public final static int SOURIS_PRESSEE = 0;
    public final static int SOURIS_BOUGEE = 1;
    
    public final static int CHOIX_MODE = 0;
    public final static int CHOIX_DIFFICULTE = 1;
    public final static int CHOIX_PLATEAU = 2;
    
    static Arbitre arbitre;
    static BorderPane b;
    static Scene s;
    final static boolean fullScreen = false;
        
    @Override
    public void start(Stage stage) throws Exception {



        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        stage.setTitle("Gauffre empoisonnee");
        if (fullScreen) {
            s = new Scene(b);
            stage.setFullScreen(true);
        } else {
            s = new Scene(b, 800, 600);
        }
        
        Label titre = new Label("La Gauffre Empoisonnée");
        titre.setFont(Font.font("Cambria", 32));
        StackPane paneTop = new StackPane(titre);
        paneTop.setAlignment(Pos.CENTER);
        b.setTop(paneTop);
        
        //goMenu();
        stage.setScene(s);
        goMenu();
        stage.show();
    }

    public static void creer(String[] args, Arbitre a) {
        b = new BorderPane();
        arbitre = a;
        launch(args);
        
    }
    
    public static void goPartie(){
        Canvas c = new Canvas(500,500);


        //Création des panes
        Pane paneCenter = new Pane(c);
        GridPane paneLeft = new GridPane();
        paneLeft.setPadding(new Insets(20, 10, 20, 0));
        paneLeft.setVgap(30.0);
        
        GridPane paneBottom = new GridPane();
        paneBottom.setPadding(new Insets(20, 10, 20, 0));
        paneBottom.setHgap(60.0);
        

        b.setBottom(paneBottom);
        b.setCenter(paneCenter);
        b.setLeft(paneLeft);

        //paneLeft.getChildren().addAll(new ChoiceBox(FXCollections.observableArrayList(
                //"Facile", "Moyen", "Difficile")), new Label("Difficulté IA"));


        c.widthProperty().bind(paneCenter.widthProperty());
        c.heightProperty().bind(paneCenter.heightProperty());


        //Boutons
        Button b1 = new Button("Nouvelle Partie");
        Button b2 = new Button("Précédent");
        Button b3 = new Button("Refaire");
        Button b4 = new Button("Sauvegarder");
        Button b5 = new Button("Aide");
        Button menu = new Button("Menu Principale");
        b1.setOnAction(new Bouton(BOUTTON_NOUVEL, arbitre));
        b2.setOnAction(new Bouton(BOUTTON_ANNULER, arbitre));
        b3.setOnAction(new Bouton(BOUTTON_REFAIRE, arbitre));
        b4.setOnAction(new Bouton(BOUTTON_SAUVEGARDER, arbitre));
        b5.setOnAction(new Bouton(BOUTON_AIDE, arbitre));
        menu.setOnAction(new Bouton(BOUTON_MENU, arbitre));
        paneLeft.add(b1,0,3);
        paneBottom.add(b2, 3,0);
        paneBottom.add(b3, 5, 0);
        paneLeft.add(b4,0,5);
        paneLeft.add(menu,0,7);
        paneBottom.add(b5, 7, 0);


        Canvas[] cn = {c};
        Animation a = new Animation(arbitre, cn);
        a.start();


        
        //System.err.println("Taille : "+c2.getWidth()+" "+c2.getHeight()+" ");
        c.setOnMousePressed(new Souris(arbitre, SOURIS_PRESSEE,c));
        c.setOnMouseMoved(new Souris(arbitre, SOURIS_BOUGEE,c));
        s.setOnKeyPressed(new Touche(arbitre,0));

        infoPartie(arbitre.joueur(arbitre.J1),arbitre.joueur(arbitre.J2), arbitre.nbMache() ,arbitre.J1 );
    }
    
    public static void goMenu(){
        b.setRight(new Pane());
        b.setLeft(new Pane());
        b.setBottom(new Pane());
        
        GridPane g = new GridPane();
        g.setHgap(45);
        g.setVgap(20);
        b.setCenter(g);
        
        
        Button nouv = new Button("Nouvelle partie");
        Button charg = new Button("Charger partie");
        ChoiceBox difficulte = new ChoiceBox();
        ChoiceBox mode = new ChoiceBox();
        ChoiceBox plateau = new ChoiceBox();
        
        initChoix(difficulte, CHOIX_DIFFICULTE);
        difficulte.getSelectionModel().select(Ordinateur.NORMAL);  
        initChoix(mode, CHOIX_MODE);
        mode.getSelectionModel().select(Arbitre.JvIA);
        initChoix(plateau, CHOIX_PLATEAU);
        
        nouv.setOnAction(new Bouton(BOUTTON_NOUVEL, arbitre));
        charg.setOnAction(new Bouton(BOUTON_CHARGER, arbitre));
        
        g.add(nouv, 4,4);
        g.add(charg, 4,6);
        g.add(new Label("Difficulté"), 6,3);
        g.add(difficulte, 6,4);
        g.add(new Label("Mode de jeu"), 5,3);
        g.add(mode, 5,4);
        g.add(new Label("Choix Plateau"), 5,5);
        g.add(plateau, 5,6);
        
    }
    
    public static void goFin(String gagnant){
        b.setRight(new Pane());
        b.setLeft(new Pane());
        b.setBottom(new Pane());
        
        Label l = new Label(gagnant+" à remporter la partie");
        l.setFont(Font.font("Cambria", 25));

        Button menu = new Button("Menu Principale");
        Button quitter = new Button("Quitter");
        
        menu.setOnAction(new Bouton(BOUTON_MENU, arbitre));
        quitter.setOnAction(new Bouton(BOUTON_QUITTER, arbitre));
        
        GridPane g = new GridPane();
        g.setVgap(50);
        g.setHgap(10);
        g.add(l, 10, 2);
        g.add(menu, 8, 6);
        g.add(quitter, 12, 6);
        b.setCenter(g);
        
        
    }
    
    public static void infoPartie(Joueur j1, Joueur j2, int nbManche, int joueur){
        Label joueur1 = new Label(j1.getNom()+" : "+j1.getScore());
        Label joueur2 = new Label(j2.getNom()+" : "+j2.getScore());
        joueur1.setFont(Font.font("Cambria", 20));
        joueur2.setFont(Font.font("Cambria", 20));
        
        if(joueur==Arbitre.J1){
            joueur1.setTextFill(Color.RED);
            joueur2.setTextFill(Color.BLACK);
        }else{
            joueur1.setTextFill(Color.BLACK);
            joueur2.setTextFill(Color.RED);
        }
        
        Label nb = new Label("Match en "+nbManche);
        nb.setFont(Font.font("Cambria", 20));
        
        GridPane g = new GridPane();
        
        g.setVgap(20);
        g.setHgap(0.5);
        
        g.add(joueur1, 1, 6);
        g.add(joueur2, 1, 9);
        g.add(nb, 1, 12);
        
        b.setRight(g);
        
        
    }
    
    public static void initChoix(ChoiceBox cb, int c){
        String[] tmp = arbitre.tabInit(c);
        for(int i=0; i<tmp.length; i++ ){
            cb.getItems().add(tmp[i]);
        }
        cb.getSelectionModel().selectedIndexProperty().addListener(new Choix(arbitre, c));
    }

}
