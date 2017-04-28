package Controlleur;

import Modele.*;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author grandmax
 */
public class Souris implements EventHandler<MouseEvent>{
    
    Arbitre a;
    int value;
    
    public Souris(Arbitre a, int v) {
        this.a = a;
        value = v;
    }

    @Override
    public void handle(MouseEvent me) {
        a.joue(new Point((int)me.getSceneX()/50,(int)me.getSceneY()/50 ));
    }
    
}