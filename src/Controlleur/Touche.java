/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlleur;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import Modele.*;

/**
 *
 * @author grandmax
 */
public class Touche implements EventHandler<KeyEvent>{
    
    Arbitre a;
    int value;
    
        public Touche(Arbitre a, int v) {
        this.a = a;
        value = v;
    }
   
    @Override
    public void handle(KeyEvent event) {
        switch (event.getCode()) {
            case N:
                a.nouvellePartie();
                break;
            case H:
                a.help();
                break;
            case P:
                a.precedent();
                break;
            case R:
                //refaire
                break;
            case ESCAPE:
                System.exit(0);
                break;
            default:
                break;
        }
    }
    
}
