/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Joueurs;

import Modele.*;
/**
 *
 * @author grandmax
 */
public abstract class Joueur extends ComposantGraphique{
    int score;
    String nom;
    boolean main;
    
    public Joueur(int x, int y, int larg, int haut, boolean m) {
        super(x, y, larg, haut);
        score = 0;
        main = m;
    }

    public void upScrore(){
        score++;
    }
    public int getScore(){
        return score;
    }
    public void setMain(){
        main = !main;
    }
    public String getNom(){
        return nom;
    }
    
    public boolean getMain(){
        return main;
    }
    
    @Override
    public boolean accept() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
