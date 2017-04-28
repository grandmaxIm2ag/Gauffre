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
       if(o instanceof Plateau){
           Plateau n = (Plateau) o;
           return toString().equals(n.toString());
       }
       return true;
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
    
    public Plateau clone(){
        String pla = toString();
        String[] tmp = pla.split("\n");
        Plateau nouv = new Plateau(p.x(), p.y(), l, h, Integer.parseInt(tmp[0]), new Point(tmp[1]));
        
        for(int i=2; i<tmp.length; i++){
            String[] tmp2 = tmp[i].split(":");
            for(int j=0; j<tmp2.length; j++){
                Point tmp3 = new Point(tmp2[j]);
                nouv.supprimeComposant(new Case(tmp3.x(), tmp3.y(),1,1 ));
            }
        }
        
        return nouv;
    }

}
