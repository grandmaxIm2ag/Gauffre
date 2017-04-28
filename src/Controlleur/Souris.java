/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlleur;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author grandmax
 */
public class Souris implements EventHandler<MouseEvent>{

    @Override
    public void handle(MouseEvent t) {
        System.out.println("Mouse pressed");
        System.out.println("X : " + (int)t.getSceneX()/50 + " Y : " + (int)t.getSceneY()/50);
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
