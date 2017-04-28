package Modele;

import Joueurs.*;
import Modele.Ensembles.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 *
 * @author grandmax
 */
public class Arbitre {
    
    public final static int JvJ = 0;
    public final static int JvIA = 1;
    public final static int J1 = 0;
    public final static int J2 = 1;
    
    Plateau p;
    Joueur joueurs[];
    int jCourant, type;
    Chargeur c;
    LIFO<String> historique;
    LIFO<String> refaire;
    private Point h;
    
    public Arbitre(){
        joueurs = new Joueur[2];
        jCourant=0;
        c = new Chargeur();
        historique = new LIFO();
        refaire = new LIFO();
        
    }
    
    public void init(int t){
        type = t;
        
        c.init();
        p=c.charger();
        
        switch(type){
            case JvJ:
                joueurs[0] = new Humain(p.tailleInitiale()+1, 0, 5, 5, true, "Joueur1");
                joueurs[1] = new Humain(p.tailleInitiale()+1, 0, 5, 5, true, "Joueur2");
                break;
            case JvIA:
                joueurs[0] = new Humain(p.tailleInitiale()+1, 0, 5, 5, true, "Joueur1");
                joueurs[1] = new Ordinateur(p.tailleInitiale()+1, 0, 5, 5, true, p);
                break;
            default:
                break;
                
        }
    }
    public void init(String plateau, int t){
        type = t;
        
        c.init(plateau);
        p = c.charger();
        
        switch(type){
            case JvJ:
                joueurs[0] = new Humain(p.tailleInitiale()+1, 0, 5, 5, true, "Joueur1");
                joueurs[1] = new Humain(p.tailleInitiale()+1, 0, 5, 5, true, "Joueur2");
                break;
            case JvIA:
                joueurs[0] = new Humain(p.tailleInitiale()+1, 0, 5, 5, true, "Joueur1");
                joueurs[1] = new Ordinateur(p.tailleInitiale()+1, 0, 5, 5, true, p);
                break;
            default:
                break;
        }
    }

    
    
    public Plateau plateau(){
        return p;
    }
    
    public void joue(Point coup){
        historique.inserer(p.observable().historique(coup));
        p.maj(coup);
        
        p.accept(new Visiteur(){
           public boolean visite(Case c){
               c.location().equals(h);
               c.fixeProp(c.AIDE, false);
               return false;
           } 
        });
        
        while(!refaire.estVide())
            refaire.extraire();
        
        if(p.estPoison(coup)){
            if(jCourant==0)
                jCourant = 1;
            else
                jCourant=0;
            
            joueurs[jCourant].upScore();
            
            nouvellePartie();
        }
        
        prochainJoueur();
    }
    
    public void help(){
        
        Ordinateur aide = new Ordinateur(p.tailleInitiale()+1, 0, 5, 5, true, p, Ordinateur.FACILE);
        
        //h = aide.go(this.p.clone());
        
        p.accept(new Visiteur(){
           public boolean visite(Case c){
               c.location().equals(h);
               c.fixeProp(c.AIDE, true);
               return false;
           } 
        });
        
    }
    
    public void precedent(){
        if(!historique.estVide()){
            String coup = historique.extraire();
            refaire.inserer(coup);
            String[] cases = coup.split(":");
            for(int i=0; i<cases.length; i++){
                Point tmp = new Point(cases[i]);
                p.ajoutComposant(new Case(tmp.x(), tmp.y(), 1, 1));
            }
        } 
    }
    public void refaire(){
        if(!refaire.estVide()){
            String coup = refaire.extraire();
            historique.inserer(coup);
            String[] cases = coup.split(":");
            for(int i=0; i<cases.length; i++){
                Point tmp = new Point(cases[i]);
                p.ajoutComposant(new Case(tmp.x(), tmp.y(), 1, 1));
            }
        } 
    }
    
    public void nouvellePartie(){
        c.init();
        p=c.charger();
        while(!historique.estVide())
            historique.extraire();
        while(!refaire.estVide())
            refaire.extraire();
    }
    
    public void sauvegarde(){
        String res = p.toString();
        while(!historique.estVide())
            res += ("\n"+historique.extraire());
        SimpleDateFormat d = new SimpleDateFormat ("dd/MM/yyyy" );
        SimpleDateFormat h = new SimpleDateFormat ("hh:mm");
 
        Date currentTime_1 = new Date();
 
        String dateString = d.format(currentTime_1);
        String heureString = h.format(currentTime_1);
        
        String fichier = "sauv_"+dateString+"_"+heureString;
        
        try{
            BufferedWriter fw = new BufferedWriter(new FileWriter(fichier));
            fw.write(res);
            fw.close();
        }catch(IOException e){
            System.err.println("Echec de la saucegarde");
        }
        
    }
    public LIFO<String> historique(){
        return historique;
    }
    public LIFO<String> arefaire(){
        return refaire;
    } 
    public void prochainJoueur(){
        joueurs[jCourant].setMain();
        if(jCourant==0)
            jCourant = 1;
        else
            jCourant=0;
        
        if(joueurs[jCourant] instanceof Ordinateur){
            Ordinateur o = (Ordinateur) joueurs[jCourant];
            //p=joue(o.go(this.p.clone()));
        }
        joueurs[jCourant].setMain();
    }
    public int score(int j){
        return joueurs[j].getScore();
    }
    public int courant(){
        return jCourant;
    }
}
