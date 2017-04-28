/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlleur;

import Modele.Plateau;
import Modele.Point;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author grandmax
 */
public class Souris implements EventHandler<MouseEvent>{
boolean clicked;
Plateau p;

public Souris () {
    boolean clicked = false;
    p = null;
}

public Souris (boolean b) {
    clicked = b;
    p = null;
}

public Souris (boolean b, Plateau p) {
    clicked = b;
    this.p = p;
}
    @Override
    public void handle(MouseEvent t) {
        if (clicked) {
            System.out.println("Mouse pressed");
            System.out.println("X : " + (int)t.getSceneX()/50 + " Y : " + (int)t.getSceneY()/50);
        } else {
            p.depointe();
            p.pointe(new Point((int)(t.getSceneX()/50),(int)(t.getSceneY()/50)));
        }
    }
    
}
