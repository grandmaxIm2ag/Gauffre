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
    
    public Joueur(double x, double y, int larg, int haut, boolean m) {
        super(x, y, larg, haut);
        score = 0;
        main = m;
    }

    public void upScore(){
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
    public boolean accept(Visiteur v) {
        return v.visite(this);
    }
    
}
