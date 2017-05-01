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
import javafx.scene.text.Font;

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
        b1.setOnAction(new Bouton(BOUTTON_NOUVEL, arbitre));
        b2.setOnAction(new Bouton(BOUTTON_ANNULER, arbitre));
        b3.setOnAction(new Bouton(BOUTTON_REFAIRE, arbitre));
        b4.setOnAction(new Bouton(BOUTTON_SAUVEGARDER, arbitre));
        b5.setOnAction(new Bouton(BOUTON_AIDE, arbitre));
        paneLeft.add(b1,0,3);
        paneBottom.add(b2, 3,0);
        paneBottom.add(b3, 5, 0);
        paneLeft.add(b4,0,8);
        paneBottom.add(b5, 7, 0);


        Canvas[] cn = {c};
        Animation a = new Animation(arbitre, cn);
        a.start();


        
        //System.err.println("Taille : "+c2.getWidth()+" "+c2.getHeight()+" ");
        c.setOnMousePressed(new Souris(arbitre, SOURIS_PRESSEE,c));
        c.setOnMouseMoved(new Souris(arbitre, SOURIS_BOUGEE,c));
        s.setOnKeyPressed(new Touche(arbitre,0));

    }
    
    public static void goMenu(){
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
        initChoix(mode, CHOIX_MODE);
        initChoix(plateau, CHOIX_PLATEAU);
        
        nouv.setOnAction(new Bouton(BOUTTON_NOUVEL, arbitre));
        charg.setOnAction(new Bouton(BOUTON_CHARGER, arbitre));
        
        g.add(nouv, 4,3);
        g.add(charg, 6,3);
        g.add(new Label("Difficulté"), 5,7);
        g.add(difficulte, 5,8);
        g.add(new Label("Mode de jeu"), 4,7);
        g.add(mode, 4,8);
        g.add(new Label("Choix Plateau"), 6,7);
        g.add(plateau, 6,8);
        
    }
    
    public static void goFin(String gagnant){
        b.setBottom(new Pane());
        b.setRight(new Pane());
        b.setLeft(new Pane());
        
        Label l = new Label(gagnant+" à remporter la partie");
        Button menu = new Button("Menu Principale");
        Button quitter = new Button("Quitter");
        
        menu.setOnAction(new Bouton(BOUTON_MENU, arbitre));
        quitter.setOnAction(new Bouton(BOUTON_QUITTER, arbitre));
        
        GridPane g = new GridPane();
        g.setVgap(50);
        g.setHgap(10);
        g.add(l, 5, 2);
        g.add(menu, 4, 6);
        g.add(quitter, 6, 6);
        b.setCenter(g);
    }
    
    public static void initChoix(ChoiceBox cb, int c){
        String[] tmp = arbitre.tabInit(c);
        for(int i=0; i<tmp.length; i++ ){
            cb.getItems().add(tmp[i]);
        }
        cb.getSelectionModel().selectedIndexProperty().addListener(new Choix(arbitre, c));
    }

}
