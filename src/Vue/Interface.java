/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import javafx.application.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.BorderPane;
import Modele.Arbitre;
import Controlleur.*;

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
            
            
        arbitre.init();
        c.setOnKeyPressed(new Touche(arbitre, 1));
        c.setOnMousePressed(new Souris(arbitre, 1));
        stage.show();
    }
    
        public static void creer(String[] args, Arbitre a) {
        arbitre = a;
        launch(args);
    }
    
}
