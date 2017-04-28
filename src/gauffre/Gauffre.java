/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gauffre;

import Joueurs.Ordinateur;

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
        Ordinateur m = new Ordinateur(0,0,0,0,true);
        m.affiche();
        m.coupIA(2);
        m.coupIA(3);
        m.affiche();
    /*    m.coupIA(12);
        m.affiche();*/
        System.out.println(m.testconfig());
    }
    
}
