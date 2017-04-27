/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele.Ensembles;
import java.util.NoSuchElementException;

/**
 *
 * @author maxence
 */
public class Fap<T> implements File{
    public MaillonPrio<T> tete;
    
    public Fap(){
        tete = null;
    }
    @Override
    public boolean estVide() {
        return (tete == null);
    }

    public void inserer(Object e, int prio) {
        MaillonPrio<T> nouveau = new MaillonPrio(e, null, prio);
        
        if((tete == null)||(this.tete.priorite <= nouveau.priorite)){
            nouveau.suivant = this.tete;
            this.tete = nouveau;
        }else{
            
            MaillonPrio<T> precedent = tete;
            MaillonPrio<T> courant = precedent.suivant;
        
        
            while((courant != null) && (courant.priorite >= nouveau.priorite)){
                precedent = courant;
                courant = precedent.suivant;
            }
            
            precedent.suivant = nouveau;
            nouveau.suivant = courant;
        }
    }

    @Override
    public Object extraire() {
        if(!this.estVide()){
            T res = tete.element;
            this.tete = (MaillonPrio<T>) this.tete.getSuivant();
            return res;
        }else{
            throw new NoSuchElementException("File a priorite vide");
        }
    }

    @Override
    public void inserer(Object e) {
        inserer(e, -Integer.MIN_VALUE);
    }
    
    public String toString(){
        String res;
        if(!estVide()){
            res = "{";
             MaillonPrio<T> tmp = tete;
             while(tmp.getSuivant() != null){
                 res+=("("+tmp.element+","+tmp.priorite+"), ");
                tmp = (MaillonPrio<T>) tmp.getSuivant();
            }
            res+=("("+tmp.element+","+tmp.priorite+")}");
        }else{
            res = "{}";
        }
        return res;
    }
    
    public MaillonPrio<T> getTete(){
        return this.tete;
    }

    
}
