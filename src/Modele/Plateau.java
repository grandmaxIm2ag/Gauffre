/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.util.*;

/**
 *
 * @author grandmax
 */
public class Plateau extends ComposantGraphique{
    List<ComposantGraphique> composant;
    Observable observable;
    Point poison;
    int tailleInitiale;

    public Plateau(int x, int y, int larg, int haut, int taille, Point p) {
        super(x, y, larg, haut);
        observable  = new Observable();
        composant = new LinkedList();
        
        composant.add(new Poison(0,0, 1, 1));
        
        poison = p;
        tailleInitiale = taille;
        for(int i=0; i<taille; i++)
            for(int j=0; j<taille; j++)
                if(poison.equals(new Point(i,j)))
                    composant.add(new Case(i,j,1,1,true));
                else
                    composant.add(new Case(i,j,1,1));
        
        
    }

    public void ajoutObservateur(Observateur obs){
        observable.ajout(obs);
    }
    public void ajoutComposant(ComposantGraphique comp){
        Plateau p = this;
        composant.add(comp);
        this.accept(new Visiteur(){
            public boolean visite(Case c){
                p.ajoutObservateur(c);
                return false;
            }
        });
    }
    public void supprimeObservateur(Observateur obs){
        observable.supprime(obs);
    }
    public void supprimeComposant(ComposantGraphique comp){
        composant.remove(comp);
    }
    public boolean maj(Point p){
        return observable.maj(p);
    }
    public boolean estPoison(Point p){
        return poison.equals(p);
    }
    public String toString(){
        return tailleInitiale+"\n"+poison;
    }
    public Point poison(){
        return poison;
    }

    @Override
    public boolean equals(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean accept(Visiteur v) {
        boolean b = false;
        
        Iterator<ComposantGraphique> it = composant.iterator();
        
        while(it.hasNext()){
            b |= it.next().accept(v);
        }
        
        return b;
    }
    
    
    
}
