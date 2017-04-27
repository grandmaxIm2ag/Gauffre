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
public abstract class ComposantGraphique {
    Point p;
    int l;
    int h;
    
    public ComposantGraphique(int x, int y, int larg, int haut){
        p = new Point(x,y);
        l = haut;
        h = haut;
    }
    
    public abstract boolean accept();
    
}
