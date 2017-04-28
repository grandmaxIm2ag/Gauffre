/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.util.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

/**
 *
 * @author grandmax
 */
public class Plateau extends ComposantGraphique{
    List<ComposantGraphique> composant;
    Observable observable;
    Point poison;
    int tailleInitiale;
    Point estPointe;

    public Plateau(int x, int y, int larg, int haut, int taille, Point p) {
        super(x, y, larg, haut);
        observable  = new Observable();
        composant = new LinkedList();
        estPointe = new Point(Integer.MAX_VALUE, Integer.MAX_VALUE);
        
        poison = p;
        this.ajoutComposant(new Poison(poison.x(), poison.y(), 1, 1));
        
      
        tailleInitiale = taille;
        for(int i=0; i<taille; i++)
            for(int j=0; j<taille; j++)
                if(poison.equals(new Point(i,j)))
                    this.ajoutComposant(new Case(i,j,1,1,true));
                else
                    this.ajoutComposant(new Case(i,j,1,1));
        
        
    }

    public void ajoutObservateur(Observateur obs){
        observable.ajout(obs);
    }
    public void ajoutComposant(ComposantGraphique comp){
        Plateau p = this;
        composant.add(comp);
        comp.accept(new Visiteur(){
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
        Plateau p = this;
        comp.accept(new Visiteur(){
            public boolean visite(Case c){
                p.supprimeObservateur(c);
                return false;
            }
        });
    }
    public boolean maj(Point p){
        return observable.maj(p);
    }
    public boolean estPoison(Point p){
        return poison.equals(p);
    }
    public void depointe() {
        Iterator<ComposantGraphique> it = composant.iterator();
        ComposantGraphique comp;
        estPointe = new Point(Integer.MAX_VALUE, Integer.MAX_VALUE);
        while (it.hasNext()) {
            comp =  it.next();
            if (comp instanceof Case) {
                Case c = (Case) comp;
                if (((Case)c).pointe())
                    ((Case)c).fixeProp(Case.POINTE, false);
                if (((Case)c).aPointe())
                    ((Case)c).fixeProp(Case.APRESPOINTE, false);
            }
        }
    }
    public void pointe(Point p) {
        Iterator<ComposantGraphique> it = composant.iterator();
        ComposantGraphique comp;
        while (it.hasNext()) {
            comp = it.next();
            if (comp instanceof Case) {
                Case c = (Case) comp;
                if (c.x() == p.x() && c.y() == p.y()) {
                    c.fixeProp(Case.POINTE, true);
                    estPointe = new Point(p.x(), p.y());
                } else if (c.apresPointe(estPointe))
                    c.fixeProp(Case.APRESPOINTE, true);
            }
        }
        
    }
    @Override
    public String toString(){
        //return tailleInitiale+"\n"+poison;
        String str = "Plateau :\n";
        Iterator<ComposantGraphique> it = composant.iterator();
        while (it.hasNext())
            str = str + " " + it.next();
        return str;
        
    }
    public Point poison(){
        return poison;
    }
    public int tailleInitiale(){
        return tailleInitiale;
    }
    public int taille(){
        return composant.size();
    }
    public Observable observable(){
        return observable;
    }
    @Override
    public boolean equals(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
 
    @Override
    public boolean accept(Visiteur v) {
        boolean b = false;
        v.visite(this);
        Iterator<ComposantGraphique> it = composant.iterator();
        
        while(it.hasNext()){ 
            b |= it.next().accept(v);
        }
        
        return b;
    }

}
