/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Joueurs;

import Modele.*;
import static java.lang.Thread.sleep;
import java.util.*;
import java.util.Random;

/**
 *
 * @author HADJADJ Liès DOUARD Amélina
 */
public class Ordinateur extends Joueur{
    public final static int FACILE = 0;
    public final static int NORMAL = 1;
    public final static int DIFFICILE = 2;
    
    public final static long GRAINE = 548789;
    
    Plateau p;
    Arbitre a;
    int nbColonnes;
    int nbLignes;
    int difficulte;
    LinkedList<Integer> casesJouables;
    int [][] mat;
    Random r;
    
    public Ordinateur(int x, int y, int larg, int haut, boolean main) {
        this(x,y,larg, haut, main, FACILE);
        this.nom = "Ordinateur";
    }
    public Ordinateur(int x, int y, int larg, int haut, boolean main, int dif) {
        super(x, y, larg, haut, main);
        this.nbColonnes=10;//attention! pour g 0<=a<nbcolonnes//mettre à jour avec plateau lorsque disponible//NEEDTOUPDATE
        this.nbLignes=10;//attention! pour g 0<=b<nblignes//mettre à jour avec plateau lorsque disponible//NEEDTOUPDATE
        difficulte = dif;
        //initialisation des cases//rempli g
         this.nom = "Ordinateur";
        
        r= new Random(GRAINE);
    }

    public Point joue(Arbitre a) {
        
        this.p = a.plateau();
        this.a = a;
        
        nbColonnes=p.tailleInitiale();
        nbLignes=p.tailleInitiale();
        mat = new int[nbColonnes][nbColonnes];
        casesJouables= new LinkedList<>();
        p.accept(new Visiteur(){
            @Override
            public boolean visite(Case c){
                if(!c.detruit())
                casesJouables.add(convertToVal(c.location()));            
                return false;
            }
        });
        convertToMat();
        affiche();
        switch (this.difficulte){
            case FACILE:
                return jouerIAFacile();
            case NORMAL:
                return jouerIANormal();
            case DIFFICILE:
                return jouerIADiff(2);
          
        }
        return null;
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
    
    public Point convertToPoint(int x){  

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
        Point p1 = new Point(j,i);
        return p1;
    }
    
    public int convertToVal(Point m){
        return (int)(Math.pow(2,m.x()) * Math.pow(3,m.y()));
    }
    
    public int testconfig(int nbcoups){
        int vc=1,vl=1;
        
        if(casesJouables.size() == 1 && casesJouables.get(0) == 1) return nbcoups-1000;
        for(int i=1;i<casesJouables.size();i++){
            if(Math.log(casesJouables.get(i))/Math.log(2) % 1 != 0)
                vl=0;
            if(Math.log(casesJouables.get(i))/Math.log(3) % 1 != 0)
                vc=0;      
        }
        if(vc == 1) return 1000 - nbcoups;
        if(vl == 1) return 1000 - nbcoups; 
        return 0;
    }
    
    public void coupIA(int val){
        for(int k=0;k<casesJouables.size(); k++){
            if(casesJouables.get(k) % val == 0){
                Point o = convertToPoint(casesJouables.get(k));
               // mat[o.x()][o.y()] = 1;
                casesJouables.remove(k);
                k--;
            }   
        }
    }
    
    public Point jouerIAFacile(){
        int indiceChoix;
        System.out.print(casesJouables.size());
        indiceChoix = (r.nextInt(casesJouables.size()));    
        int choix = casesJouables.get(indiceChoix);
        coupIA(choix);
        Point res = convertToPoint(choix);
        return res;
    }
    
    public void convertToMat(){  
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
            mat[i][j] = casesJouables.get(k);
        }
    }
    
    public Point jouerIANormal(){
        //TODO
        return new Point(0,0);
    }
    
    public Point jouerIADiff(int profondeur){
        int max_poids = -10000;
        int meilleur_coup = 1;
        int hauteur = profondeur;
        for(int i = 0; i < casesJouables.size();i++){
            LinkedList<Integer> casestmp = (LinkedList<Integer>) casesJouables.clone();
            coupIA(casesJouables.get(i));
            int tmp = Min(profondeur -1, hauteur);
            if(tmp > max_poids){
                max_poids = tmp;
                meilleur_coup = i;
                
            }
            casesJouables = casestmp;
        }
        System.out.println(casesJouables.get(meilleur_coup));
        System.out.println(convertToPoint(casesJouables.get(meilleur_coup)));
        return convertToPoint(casesJouables.get(meilleur_coup));
    }
    
    public int Max(int profondeur,int hauteur){
        if(profondeur == 0)
            return testconfig(hauteur - profondeur);
        int max_poids = -100000;
        
        for(int i=0;i<casesJouables.size();i++){
            LinkedList<Integer> casestmp = (LinkedList<Integer>) casesJouables.clone();
            coupIA(casesJouables.get(i));
            int tmp = Min(profondeur-1, hauteur);
            if(tmp > max_poids){
                max_poids = tmp;
            }
            casesJouables = casestmp;
        }
        return max_poids;
    }
    
    public int Min(int profondeur,int hauteur){
        if(profondeur == 0)
            return testconfig(hauteur - profondeur);
        int min_poids = 100000;
        
        for(int i=0;i<casesJouables.size();i++){
            LinkedList<Integer> casestmp = (LinkedList<Integer>) casesJouables.clone();
            coupIA(casesJouables.get(i));
            int tmp = Max(profondeur-1, hauteur);
            if(tmp < min_poids){
                min_poids = tmp;
            }
            casesJouables = casestmp;
        }
        return min_poids;
    }
    
    @Override
    public boolean equals(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
