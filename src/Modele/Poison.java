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
public class Poison extends ComposantGraphique{

    public Poison(double x, double y, int larg, int haut) {
        super(x, y, larg, haut);
    }

    @Override
    public boolean accept(Visiteur v) {
        return v.visite(this);
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof Poison){
            Poison c = (Poison)o;
            return(p.equals(c.location()) && l==c.l() && h==c.h());
        }
        return false;
    }
    
}
