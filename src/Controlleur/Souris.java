/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlleur;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import Modele.*;

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
    public void handle(MouseEvent t) {
        //a.joue(new Point((int)t.getX(),(int)t.getY()));
    }
    
}
