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
public class Case extends ComposantGraphique implements Observateur{
    public final static int NB = 5;
    
    public static final int EMPOISONNEE = 0;
    public final static int DETRUIT = 1;
    public final static int POINTE = 2;
    public final static int APRESPOINTE = 3;
    public final static int AIDE = 4;
        
    boolean propriete[] = new boolean[NB];
    
    public Case(double x, double y, int larg, int haut) {
        this(x, y, larg, haut, false);
    }
    public Case(double x, double y, int larg, int haut, boolean b) {
        super(x, y, larg, haut);
        fixeProp(EMPOISONNEE,b);
        fixeProp(DETRUIT, false);
        fixeProp(AIDE, false);
    }

    public boolean empoisonnee(){
        return propriete[EMPOISONNEE];
    }
    public boolean detruit(){
        return propriete[DETRUIT];
    }
    
    public boolean pointe() {
        return propriete[POINTE];
    }
    
    public boolean aide(){
        return propriete[AIDE];
    }
    public boolean aPointe() {
        return propriete[APRESPOINTE];
    }
    
    public boolean apresPointe (Point p) {
        return collision(p);
    }
    
    public void fixeProp(int x, boolean b){
        propriete[x]=b;
    }
    @Override
    public boolean accept(Visiteur v) {
       return v.visite(this);
    }

    @Override
    public boolean maj(Point p) {
        if(/*p.x()==this.x() && p.y()==this.y() || */collision(p)){
            return ((propriete[DETRUIT] = true));
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof Case){
            Case c = (Case)o;
            return (p.equals(c.location()) && l==c.l() && h==c.h() && this.empoisonnee()==c.empoisonnee() );
        }
        return false;
    }
    
    public boolean collision(Point coup){
        if(coup.x()<=p.x() && coup.y()<=p.y()){
            return true;
        }else{
            double diffX = coup.x()-p.x();
            double diffY = coup.y()-p.y();
            if(diffX<l && diffY<h)
                return true;
            else
                return false;
        }
    }
    
}
