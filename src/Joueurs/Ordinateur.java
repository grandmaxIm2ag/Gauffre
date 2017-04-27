/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Joueurs;

import Modele.*;
import java.util.*;
import java.util.Random;
/**
 *
 * @author grandmax
 */
public class Ordinateur extends Joueur{
    public final static int FACILE = 0;

    public final static long GRAINE = 548789;
    
    Plateau p;
    Arbitre a;
    int nbColonnes;
    int nbLignes;
    int difficulte;
    LinkedList<Integer> casesJouables;
    int [][] mat;
    Random r;
    
    public Ordinateur(int x, int y, int larg, int haut, boolean main, Plateau p) {
        this(x,y,larg, haut, main, p, FACILE);
    }
    public Ordinateur(int x, int y, int larg, int haut, boolean main, Plateau p, int dif) {
        super(x, y, larg, haut, main);
        this.p = p;
        this.nbColonnes=2;//attention! pour g 0<=a<nbcolonnes//mettre à jour avec plateau lorsque disponible//NEEDTOUPDATE
        this.nbLignes=6;//attention! pour g 0<=b<nblignes//mettre à jour avec plateau lorsque disponible//NEEDTOUPDATE
        difficulte = dif;
        casesJouables= new LinkedList<>();
        //initialisation des cases//rempli g
        mat = new int [nbLignes][nbColonnes];
        for(int i=0;i<nbLignes;i=i+1){
            for(int j=0;j<nbColonnes;j=j+1){
                int g= (int)(Math.pow(2,j) * Math.pow(3,i));
                casesJouables.add(g);
            }
        }
        r= new Random(GRAINE);
    }

    public void joue(){
        //mettre à jour cout du joueur (p)
        int indiceChoix;
        indiceChoix = (r.nextInt()%(casesJouables.size()));
        int choix=casesJouables.remove(indiceChoix);
        
        //this.p = a.joue(p);
        //Parcours
        //NEEDTOUPDATE//besoin de mettre à jour la liste
        
    }
    
    public void affiche(){  
        System.out.println(casesJouables);
        for(int i=0;i<nbLignes;i++){
            for(int j=0;j<nbColonnes;j++){
                System.out.print(mat[i][j]+"  ");
            }
            System.out.println();
        }
    }
    
    public void convert(){  
        for(int k=0;k<casesJouables.size();k++){
            int x = casesJouables.get(k);
            int i=0,j=0;
            while(x/3!=0 || x/2!=0){
                while(i<nbLignes-1 && x/3!=0 && x%3==0){
                    x=x/3;
                    i++;
                }
                while(j<nbColonnes-1 && x/2!=0 && x%2==0){
                    x=x/2;
                    j++;
                }
            }    
           // System.out.println(i+" , "+j);
            mat[i][j] = casesJouables.get(k);
        }
    }
    
    @Override
    public boolean equals(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
