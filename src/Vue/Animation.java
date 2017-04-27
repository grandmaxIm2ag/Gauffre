/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import javafx.animation.*;
import javafx.scene.canvas.Canvas;
import Modele.*;
/**
 *
 * @author grandmax
 */
public class Animation extends AnimationTimer{

    Arbitre arbitre;
    Canvas can;
    Visiteur[] dessinateurs;
    int courant;
    
    Animation(Arbitre j, Canvas c) {
        arbitre = j;
        can = c;
        dessinateurs = new Visiteur[1];
        courant = 0;
    }
    
    public void changeDessinateur() {
        courant++;
        if (courant >= dessinateurs.length) {
            courant = 0;
        }
    }

    
    @Override
    public void handle(long l) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        arbitre.plateau().accept(dessinateurs[courant]);
    }
    
    
}
