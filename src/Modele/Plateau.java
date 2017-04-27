/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.util.List;

/**
 *
 * @author grandmax
 */
public class Plateau extends ComposantGraphique{
    List<ComposantGraphique> composant;
    Point poison;

    public Plateau(int x, int y, int larg, int haut, int taille) {
        super(x, y, larg, haut);
    }

    public void ajout(Observateur obs){
        
    }
    public void maj(){
        
    }
    public boolean estPoison(Point p){
        return poison.equals(p);
    }
    
    @Override
    public boolean accept() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean equals() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
