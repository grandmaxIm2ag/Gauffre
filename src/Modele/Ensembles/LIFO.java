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
public class LIFO<T> implements File<T>{
    public Maillon<T> tete;
    int taille = 0;
    public LIFO(){
        this.tete = null;
    }
    @Override
    public boolean estVide() {
        return (this.tete == null);
    }

    @Override
    public void inserer(T e) {
        Maillon<T> t = new Maillon(e, tete);
        tete = t;
        taille++;
    }

    @Override
    public T extraire() {
        T res;
        if(!estVide()){
            res = (T) tete.getElem();
            tete = (Maillon<T>)tete.getSuivant();
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
