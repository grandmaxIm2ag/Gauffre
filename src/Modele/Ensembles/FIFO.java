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
public class FIFO<T> implements File{
    public Maillon<T> tete;
    int taille = 0;
    
    public FIFO(){
        tete = null;
    }
    @Override
    public boolean estVide() {
        return(tete == null);
    }

    @Override
    public void inserer(Object e) {
        if(this.tete == null){
            this.tete = new Maillon(e, null);
        }
        else{
            Maillon<T> nouveau = new Maillon(e, null);
            Maillon<T> courant = this.tete;
        
            while(courant.suivant != null){
                courant = courant.suivant;
            }
            
            courant.suivant = nouveau;
        }
        taille++;
    }

    @Override
    public T extraire() {
        if(!this.estVide()){
            T res = (T) tete.getElem();
            this.tete = (Maillon<T>) this.tete.getSuivant();
            taille--;
            return res;
        }else{
            throw new NoSuchElementException("FIFO vide");
        }
    }
    
    public String toString(){
        String res;
        if(!estVide()){
            res = "{";
             Maillon<T> tmp = tete;
             while(tmp.getSuivant() != null){
                 res+=(""+tmp.getElem()+", ");
                tmp = (Maillon<T>) tmp.getSuivant();
            }
            res+=(""+tmp.getElem()+"}");
        }else{
            res = "{}";
        }
        return res;
    }
    
    public Maillon<T> getTete(){
        return this.tete;
    }
    
    
    public int taille(){
        return taille;
    }
}
