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
    
    public void setLocation(int x, int y){
        p.fixe(x, y);
    }
    public int h(){
        return h;
    }
    public int l(){
        return l;
    }
    public int x(){
        return p.x();
    }
    public int y(){
        return p.y();
    }
    public Point location(){
        return p;
    }
    
    public abstract boolean equals();
    
    public abstract boolean accept();
    
}
