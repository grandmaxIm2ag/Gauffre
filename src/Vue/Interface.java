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
        Button b1 = new Button("Truc"); 
        b1.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Faire un truc !");
            }
        });
        Button b2 = new Button("Dire \"Jean\""); 
        b2.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Jean");
            }
        });
        paneRight.getChildren().add(b1);
        paneRight.getChildren().add(b2);
        b.setRight(paneRight);
            
        Scene s;
        s = new Scene(b, 800, 600);
        stage.setScene(s);
        Animation a = new Animation(arbitre, c);
        a.start();
        
        s.setOnMousePressed(new Souris());
        
        
        
        stage.show();
        //System.exit(0);
    }
    
        public static void creer(String[] args, Arbitre a) {
        arbitre = a;
        launch(args);
    }
    
}
