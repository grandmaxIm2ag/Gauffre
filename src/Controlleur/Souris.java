package Controlleur;

import Modele.*;
import Vue.Interface;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author grandmax
 */
public class Souris implements EventHandler<MouseEvent>{
    
    Arbitre a;
    int value;
    Canvas c;
    
    public Souris(Arbitre a, int v, Canvas can) {
        this.a = a;
        value = v;
        c = can;
    }

    @Override
    public void handle(MouseEvent me) {
        switch(value){
            case Interface.SOURIS_BOUGEE:
                a.depointe();
                a.sourisPointe(me.getX(), me.getY(), c.getWidth(), c.getHeight());
            break;
            case Interface.SOURIS_PRESSEE:
                a.sourisCoup(me.getX(), me.getY(), c.getWidth(),  c.getHeight());
                break;
            default:
                break;
        }
    }
    
}
