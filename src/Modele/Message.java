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
    
    public Message(int x, int y, int larg, int haut, String m) {
        super(x, y, larg, haut);
        message =
                m;
    }

    @Override
    public boolean accept() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
