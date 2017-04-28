/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gauffre;

import Joueurs.Ordinateur;
import Modele.Arbitre;
import Modele.Plateau;
import Modele.Point;
import Vue.Interface;

/**
 *
 * @author grandmax
 */
public class Gauffre {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Interface it = new Interface();
        it.creer(args, new Arbitre() );
       // System.out.println(8/3);
    }
    
}