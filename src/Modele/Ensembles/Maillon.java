/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele.Ensembles;

/**
 *
 * @author maxence
 */
public class Maillon<T> {
    public T element;
    public Maillon suivant;
    
    public Maillon(T c, Maillon<T> s) {
        this.element = c;
        this.suivant = s;
    }
    
    public Maillon getSuivant() {
        return this.suivant;
    }
    
    public T getElem(){
        return element;
    }
}
