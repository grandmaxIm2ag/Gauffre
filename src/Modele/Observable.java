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
            boolean bb = it.next().maj(p);
            if(bb)
                it.remove();
            b |= bb;
        }
        return b;
    }
    public int size(){
        return observateurs.size();
    }
    public boolean contains(Observateur o){
        return observateurs.contains(o);
    }
    public List<Observateur> observateurs(){
        return observateurs;
    }
    public String historique(Point p){
        String res = p.toString();
        Point tmp;
        
        Iterator<Observateur> it = observateurs.iterator();
        while(it.hasNext()){
            Observateur o = it.next();
            if(o instanceof Case){
                Case c = (Case) o;
                if(c.collision(p)){
                    res += (":"+c.location());
                }
            }
        
        }
        return res;
    }
}
