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
public class MaillonPrio<T>{
    public T element;
    public int priorite;
    public MaillonPrio<T> suivant; 
    public MaillonPrio(T c, MaillonPrio<T> s, int priorite) {
        this.element = c;
        this.suivant = s;
        this.priorite  = priorite;
    }
    
    public int getPriorite(){
        return this.priorite;
    }
    public MaillonPrio getSuivant() {
        return (MaillonPrio) this.suivant;
    }
    
}
