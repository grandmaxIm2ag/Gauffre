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
                joueurs[0] = new Humain(p.tailleInitiale()+1, 2, 5, 5, true, "Joueur1");
                joueurs[1] = new Humain(p.tailleInitiale()+1, 10, 5, 5, false, "Joueur2");
                break;
            case JvIA:
                joueurs[0] = new Humain(p.tailleInitiale()+1, 2, 5, 5, true, "Joueur1");
                joueurs[1] = new Ordinateur(p.tailleInitiale()+1, 10, 5, 5, false, p);
                break;
            default:
                break;
                
        }
        p.ajoutComposant(joueurs[0]);
        p.ajoutComposant(joueurs[1]);
    }
    public void init(String plateau, int t){
        type = t;
        
        c.init(plateau);
        p = c.charger();
        
        switch(type){
            case JvJ:
                joueurs[0] = new Humain(p.tailleInitiale()+1, 2, 5, 5, true, "Joueur1");
                joueurs[1] = new Humain(p.tailleInitiale()+1, 10, 5, 5, false, "Joueur2");
                break;
            case JvIA:
                joueurs[0] = new Humain(p.tailleInitiale()+1, 2, 5, 5, true, "Joueur1");
                joueurs[1] = new Ordinateur(p.tailleInitiale()+1, 10, 5, 5, false, p);
                break;
            default:
                break;
        }
        p.ajoutComposant(joueurs[0]);
        p.ajoutComposant(joueurs[1]);
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
            
            if(jCourant==0)
                jCourant = 1;
            else
                jCourant=0;
            
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
            System.out.println(coup);
            refaire.inserer(coup);
            String[] cases = coup.split(":");
            for(int i=0; i<cases.length; i++){
                Point tmp = new Point(cases[i]);
                System.out.println(tmp);
                p.accept(new Visiteur(){
                   public boolean visite(Case c){
                       if(c.location().equals(tmp)){
                           c.fixeProp(Case.DETRUIT, false);
                           p.ajoutObservateur(c);
                       }
                           
                       return false;
                   } 
                });
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
                p.accept(new Visiteur(){
                   public boolean visite(Case c){
                       if(c.location().equals(tmp)){
                           c.fixeProp(Case.DETRUIT, true);
                           p.supprimeObservateur(c);
                       }
                       return false;
                   } 
                });
            }
        } 
    }
    
    public void nouvellePartie(){
        c.init();
        p=c.charger();
        p.ajoutComposant(joueurs[0]);
        p.ajoutComposant(joueurs[1]);
        while(!historique.estVide())
            historique.extraire();
        while(!refaire.estVide())
            refaire.extraire();
    }
    
    public void sauvegarde(){
        String res = p.toString();
        while(!historique.estVide())
            res += ("\n"+historique.extraire());
        
        SimpleDateFormat d = new SimpleDateFormat ("dd_MM_yyyy" );
        SimpleDateFormat h = new SimpleDateFormat ("hh-mm");
 
        Date currentTime_1 = new Date();
 
        String dateString = d.format(currentTime_1);
        String heureString = h.format(currentTime_1);
        
        String fichier = "Ressources/Plateau/sauv_"+dateString+"_"+heureString+".txt";
        
        try{
            PrintWriter writer = new PrintWriter(fichier, "UTF-8");
            writer.print(res);
            writer.close();
            System.out.println("Fichier de sauvegarde "+fichier+" créer");
        }catch(FileNotFoundException e){
            System.err.println(fichier+" n'existe pas");
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
        
        joueurs[jCourant].setMain();
        
        if(joueurs[jCourant] instanceof Ordinateur){
            Ordinateur o = (Ordinateur) joueurs[jCourant];
            //p=joue(o.go(this.p.clone()));
        }
        
    }
    public int score(int j){
        return joueurs[j].getScore();
    }
    public int courant(){
        return jCourant;
    }
}