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
import java.util.Properties;
import java.util.Scanner;

/**
 *
 * @author grandmax
 */
public class Gauffre {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Properties p = Configuration.proprietes();
        Reglage.init(p);
        Arbitre a = new Arbitre(p);
        
        Interface it = new Interface();
        it.creer(args,a);
        a.go();
    }
    
}