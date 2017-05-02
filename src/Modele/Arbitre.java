package Modele;

import Joueurs.*;
import Modele.Ensembles.*;
import Vue.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import gauffre.*;
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
    Properties prop;
    Joueur joueurs[];
    int jCourant, type, difficulte;
    String plateau;
    Chargeur c;
    LIFO<String> historique;
    LIFO<String> refaire;
    private Point h;
    boolean estAide = false;
    int cpt = 0;
    
    String[] diff;
    String[] types;
    String[] plateaux;
    
    public Arbitre(Properties prop){
        this.prop = prop;
        joueurs = new Joueur[2];
        jCourant=0;
        c = new Chargeur();
        historique = new LIFO();
        refaire = new LIFO();
        
        diff = new String[3];
        diff[Ordinateur.FACILE] = "Facile";
        diff[Ordinateur.NORMAL] = "Normal";
        diff[Ordinateur.DIFFICILE] = "Difficile";
        
        types = new String[2];
        types[JvJ] = "Joueur vs Joueur";
        types[JvIA] = "Joueur vs IA";
        
        try{
          BufferedReader fr = new BufferedReader(new FileReader("Ressources/Plateau/Sauvegarde"));
          String str = fr.readLine();
          if(str == null || str.equals("")){
              plateaux = new String[1];
              plateaux[0] = "(none)";
          }else{
              plateaux = str.split(":");
          }
        }catch(IOException e){
            System.out.println("Initialisation plateaux : "+e);
            plateaux = new String[1];
            plateaux[1] = "(none)";
        }
        
        c.init(prop);
        type = JvIA;
        difficulte = Ordinateur.NORMAL;
    }

    public void init(){

        switch(type){
            case JvJ:
                joueurs[J1] = new Humain((double)p.tailleInitiale()+0.5,(double)p.tailleInitiale()/3 ,  Reglage.lis("lJoueur"), Reglage.lis("hJoueur"), true, "Joueur1");
                joueurs[J2] = new Humain((double)p.tailleInitiale()+0.5,(double)p.tailleInitiale()*2/3,  Reglage.lis("lJoueur"), Reglage.lis("hJoueur"), false, "Joueur2");
                break;
            case JvIA:
                joueurs[J1] = new Humain((double)p.tailleInitiale()+0.5,(double)p.tailleInitiale()/3,  Reglage.lis("lJoueur"), Reglage.lis("hJoueur"), true, "Joueur1");
                joueurs[J2] = new Ordinateur((double)p.tailleInitiale()+0.5,(double)p.tailleInitiale()*2/3,  Reglage.lis("lJoueur"), Reglage.lis("hJoueur"), false, difficulte);
                break;
            default:
                break;
        }
        
        //p.ajoutComposant(joueurs[J1]);
        //p.ajoutComposant(joueurs[J2]);
    }

    public Plateau plateau(){
        return p;
    }

    public void joue(Point coup){
        
        if(p.estValide(coup)){
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

                nouvellePlateau(); 
            }
            
            if(joueurs[jCourant].getScore()==Reglage.lis("nbManche")){
                Interface.goFin(joueurs[jCourant].getNom());
            }else
                prochainJoueur();
        }
    }

    public void help(){
        estAide = true;
        Ordinateur aide = new Ordinateur(p.tailleInitiale()+0.5, 0, 5, 5, true,Ordinateur.FACILE);

        h = aide.joue(this);

        p.accept(new Visiteur(){
           public boolean visite(Case c){

               if(c.location().equals(h))
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
        c.init(prop);
        p=c.charger();
        init();
        while(!historique.estVide())
            historique.extraire();
        while(!refaire.estVide())
            refaire.extraire();
        Interface.goPartie();
    }
    public void chargerPartie(){
        if(plateau!=null && !plateau.equals("(none)")){
            c.init(plateau,prop);
            p=c.charger();
            init();
            while(!historique.estVide())
                historique.extraire();
            while(!refaire.estVide())
                refaire.extraire();
            Interface.goPartie();
        }
    }
    public void nouvellePlateau(){
        c.init(prop);
        p=c.charger();
        //p.ajoutComposant(joueurs[0]);
        //p.ajoutComposant(joueurs[1]);
        while(!historique.estVide())
            historique.extraire();
        while(!refaire.estVide())
            refaire.extraire();
    }

    public void sauvegarde(){
        String res = p.toString();
        String str="";
        while(!historique.estVide())
            res += ("\n"+historique.extraire());

        SimpleDateFormat d = new SimpleDateFormat ("dd_MM_yyyy" );
        SimpleDateFormat h = new SimpleDateFormat ("hh-mm");

        Date currentTime_1 = new Date();

        String dateString = d.format(currentTime_1);
        String heureString = h.format(currentTime_1);

        String fichier = "Ressources/Plateau/sauv_"+dateString+"_"+heureString;
        

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
        
        try{
          BufferedReader fr = new BufferedReader(new FileReader("Ressources/Plateau/Sauvegarde"));
          str = fr.readLine();
          
          if(str == null || str.equals("")){
              str = fichier+fichier.split("/")[2];
          }else{
              str += (":"+fichier.split("/")[2]);
          }
        }catch(IOException e){
            System.err.println("Echec de la sauvegarde "+fichier);
        }
        
        try{
            PrintWriter writer = new PrintWriter("Ressources/Plateau/Sauvegarde", "UTF-8");
            writer.print(str);
            writer.close();
        }catch(IOException e){
            System.err.println("Echec de la saucegarde "+e);
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
        
        Interface.infoPartie(joueur(J1), joueur(J2), nbMache(), jCourant);
        
        if(joueurs[jCourant] instanceof Ordinateur){
            Ordinateur o = (Ordinateur) joueurs[jCourant];
            joue(o.joue(this));
        }

    }
    public int score(int j){
        return joueurs[j].getScore();
    }
    public int courant(){
        return jCourant;
    }
    public void depointe(){
        p.depointe();
    }
    public void pointe(Point point){
        p.pointe(point);
    }

    public boolean accept(Visiteur v){
        return p.accept(v);
    }

    public void sourisCoup(double curseurX, double curseurY, double canX, double canY){
        Etendeur e = new Etendeur();
        e.fixeEchelle(p.l()/canX, p.h()/canY);
        joue(e.souris(curseurX, curseurY));
    }
    public void sourisPointe(double curseurX, double curseurY, double canX, double canY){
        Etendeur e = new Etendeur();
        e.fixeEchelle(p.l()/canX, p.h()/canY);
        p.pointe(e.souris(curseurX, curseurY));
    }
    public void go(){
        Interface.goMenu();
    }
    
    public void setInit(int c,int i){
        switch(c){
            case Interface.CHOIX_MODE:
                type=i;
                break;
            case Interface.CHOIX_DIFFICULTE:
                difficulte=i;
                break;
            case Interface.CHOIX_PLATEAU:
                charger(i);
                break;
            default:
                break;
        }
    }
    
    public void charger(int i){
        if(i<plateaux.length){
            if(!plateaux[i].equals("(none)")){
                plateau = "Ressources/Plateau/"+plateaux[i];
                
            }
        }
    }
    
    public String[] tabInit(int c){
        switch(c){
            case Interface.CHOIX_MODE:
                return types;
            case Interface.CHOIX_DIFFICULTE:
                return diff;
            case Interface.CHOIX_PLATEAU:
                return plateaux;
            default:
                return null;
        }
    }
    
    public Joueur joueur(int j){
        return joueurs[j];
    }
    public int nbMache(){
        return Reglage.lis("nbManche");
    }
}
