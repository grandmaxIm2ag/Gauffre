/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

/**
 *
 * @author grandmax
 */
public class Arbitre {
    Plateau p;
    
    public Arbitre (Plateau p) {
        this.p = p;
    }
    
    
    public Plateau plateau(){
        return p;
    }
    
    public Plateau joue(Point coup){
        return p;
    }
}
