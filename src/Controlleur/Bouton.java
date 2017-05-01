/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlleur;

import Modele.Arbitre;
import Vue.Interface;
import javafx.event.*;

/**
 *
 * @author grandmax
 */
public class Bouton implements EventHandler<ActionEvent>{
    int value;
    Arbitre ab;
    
    public Bouton(int v, Arbitre a){
        value = v;
        ab = a;
    }
    
    @Override
    public void handle(ActionEvent t) {
        switch(value){
            case Interface.BOUTTON_ANNULER:
                ab.precedent();
                break;
            case Interface.BOUTTON_REFAIRE:
                ab.refaire();
                break;
            case Interface.BOUTTON_NOUVEL:
                ab.nouvellePartie();
                break;
            case Interface.BOUTTON_SAUVEGARDER:
                ab.sauvegarde();
                break;
            case Interface.BOUTON_AIDE:
                ab.help();
                break;
            case Interface.BOUTON_CHARGER:
                ab.chargerPartie();
                break;
            case Interface.BOUTON_MENU:
                Interface.goMenu();
                break;
            case Interface.BOUTON_QUITTER:
                System.exit(0);
                break;
            default:
                break;
        }
    }
    
}
