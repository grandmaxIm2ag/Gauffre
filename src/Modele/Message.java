/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

/**
 *
 * @author grandmax
 */
public class Message extends ComposantGraphique{
    String message;
    
    public Message(float x, float y, int larg, int haut, String m) {
        super(x, y, larg, haut);
        message = m;
    }
    
    public String message(){
        return message;
    }

    @Override
    public boolean accept(Visiteur v) {
        return v.visite(this);
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof Message){
            Message c = (Message)o;
            return(p.equals(c.location()) && l==c.l() && h==c.h() && message.equals(c.message()));
        }
        return false;
    }
    
}
