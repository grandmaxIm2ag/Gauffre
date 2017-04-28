/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Controlleur.Souris;
import javafx.application.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.BorderPane;
import Modele.Arbitre;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author grandmax
 */
public class Interface extends Application{
    static Arbitre arbitre;

    @Override
    public void start(Stage stage) throws Exception {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        stage.setTitle("Gauffre empoisonnee");
        Canvas c = new Canvas();
        BorderPane b = new BorderPane(c);
            
        c.widthProperty().bind(b.widthProperty());
        c.heightProperty().bind(b.heightProperty());
            
        Scene s;
        s = new Scene(b, 800, 600);
        stage.setScene(s);
        Animation a = new Animation(arbitre, c);
        a.start();
        
        
        /*s.setOnMouseEntered(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                System.out.println("Mouse entered"); 
            }
        });

        s.setOnMouseExited(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                System.out.println("Mouse exited");
            }
        });*/

        /*s.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                System.out.println("Mouse pressed");
                System.out.println("X : " + (int)me.getSceneX()/50 + " Y : " + (int)me.getSceneY()/50);
            }
        });*/
        
        s.setOnMousePressed(new Souris());
        
        
        stage.show();
        //System.exit(0);
    }
    
        public static void creer(String[] args, Arbitre a) {
        arbitre = a;
        launch(args);
    }
    
}
