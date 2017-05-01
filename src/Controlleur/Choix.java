/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlleur;

import Modele.Arbitre;
import Vue.Interface;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

/**
 *
 * @author maxence
 */
public class Choix implements ChangeListener<Number>{
    Arbitre a;
    int value;
    
    public Choix(Arbitre arbitre, int v){
        a=arbitre; value = v;
    }
    @Override
    public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
        a.setInit(value, (int)newValue);
    }

    
    
}
