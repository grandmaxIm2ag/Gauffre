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
    public final static int NB = 2;
    
    public static final int EMPOISONNEE = 0;
    public final static int DETRUIT = 1;
    
    boolean propriete[] = new boolean[NB];
    
    public Case(int x, int y, int larg, int haut) {
        this(x, y, larg, haut, false);
    }
    public Case(int x, int y, int larg, int haut, boolean b) {
        super(x, y, larg, haut);
        fixeProp(EMPOISONNEE,b);
        fixeProp(DETRUIT, false);
    }

    public boolean empoisonnee(){
        return propriete[EMPOISONNEE];
    }
    public boolean detruit(){
        return propriete[DETRUIT];
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
        if(p.x()<=this.p.x() && p.y()<=this.p.y())
            return ((propriete[DETRUIT] = true));
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof Case){
            Case c = (Case)o;
            return (p.equals(c.location()) && l==c.l() && h==c.h() && this.empoisonnee()==c.empoisonnee());
        }
        return false;
    }
    
}
