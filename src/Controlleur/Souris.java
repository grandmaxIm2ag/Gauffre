package Controlleur;

import Modele.*;
import Vue.Interface;
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
        switch(value){
            case Interface.SOURIS_BOUGEE:
                a.depointe();
                a.pointe(new Point((int)me.getSceneX()/50,(int)me.getSceneY()/50));
            break;
            case Interface.SOURIS_PRESSEE:
                a.joue(new Point((int)me.getSceneX()/50,(int)me.getSceneY()/50 ));
                break;
            default:
                break;
        }
    }
    
}
