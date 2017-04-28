/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Controlleur.Bouton;
import Controlleur.Souris;
import javafx.application.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.BorderPane;
import Modele.Arbitre;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 *
 * @author grandmax
 */
public class Interface extends Application{
    public final static int BOUTTON_NOUVEL = 0;
    public final static int BOUTTON_REFAIRE = 1;
    public final static int BOUTTON_ANNULER = 2;
    public final static int BOUTTON_SAUVEGARDER = 3;
     
    static Arbitre arbitre;

    @Override
    public void start(Stage stage) throws Exception {
        
       
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        stage.setTitle("Gauffre empoisonnee");
        BorderPane b = new BorderPane();
        
        Pane paneCenter= new Pane();
        Canvas c = new Canvas(600, 600);
        
        paneCenter.getChildren().addAll( c);
        b.setCenter(paneCenter);
            
        c.widthProperty().bind(b.widthProperty());
        c.heightProperty().bind(b.heightProperty());
        
        VBox paneRight = new VBox();
        paneRight.setPrefSize(200, 600);
        paneRight.setPadding(new Insets(20));
        paneRight.setSpacing(10);
        paneRight.setAlignment(Pos.TOP_CENTER);
        Button b1 = new Button("Nouvelle Partie");
        Button b2 = new Button("Précédent");
        Button b3 = new Button("Refaire");
        Button b4 = new Button("Sauvegarder");
        b1.setOnAction(new Bouton(BOUTTON_NOUVEL, arbitre));
        b1.setOnAction(new Bouton(BOUTTON_ANNULER, arbitre));
        b1.setOnAction(new Bouton(BOUTTON_REFAIRE, arbitre));
        b1.setOnAction(new Bouton(BOUTTON_SAUVEGARDER, arbitre));
        
        paneRight.getChildren().add(b1);
        paneRight.getChildren().add(b2);
        paneRight.getChildren().add(b3);
        paneRight.getChildren().add(b4);
        b.setRight(paneRight);
            
        Scene s;
        s = new Scene(b, 800, 600);
        stage.setScene(s);
        Animation a = new Animation(arbitre, c);
        a.start();
        
        s.setOnMousePressed(new Souris(true));
        s.setOnMouseMoved(new Souris(false, arbitre.plateau()));
        
        
        stage.show();
        //System.exit(0);
    }
    
        public static void creer(String[] args, Arbitre a) {
        arbitre = a;
        launch(args);
    }
    
}
