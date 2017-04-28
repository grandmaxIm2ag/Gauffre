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
        // TODO code application logic here
        Ordinateur m = new Ordinateur(0,0,0,0,true,null,0);
        Plateau p = new Plateau(10,10,10,10,10,new Point(0,0));
        //Arbitre a = new Arbitre(p);
        System.out.println(p);
        //Interface.creer(args, a);
        m.convert();
        m.affiche();
        
       // System.out.println(8/3);
    }
    
}
