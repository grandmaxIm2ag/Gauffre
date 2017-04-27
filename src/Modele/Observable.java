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
public class Observable {
    List<Observateur> observateurs;
    
    public Observable(){
        observateurs = new ArrayList();
    }
    
    public void ajout(Observateur obs){
        observateurs.add(obs);
    }
    public void supprime(Observateur obs){
        observateurs.remove(obs);
    }
    public boolean maj(Point p){
        boolean b = false;
        Iterator<Observateur> it = observateurs.iterator();
        while(it.hasNext()){
            b |= it.next().maj(p);
        }
        return b;
    }
}
