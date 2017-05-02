/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import gauffre.Reglage;
import java.io.*;
import java.util.Properties;

/**
 *
 * @author grandmax
 */
public class Chargeur {
    BufferedReader fr;
    Properties prop;
    
    public void init(String m, Properties prop){
        this.prop = prop;
        try{
          fr = new BufferedReader(new FileReader(m));
        }catch(IOException e){
            System.out.println("Chargement du terrain à échoué : "+e);
            init(prop);
        }
    }
    public void init(Properties prop){
        init("Ressources/Plateau/Default", prop);
    }
    public Plateau charger(){
        try{
            int taille;
            String ligne = fr.readLine();
            taille = Integer.parseInt(ligne);
            Point poison = new Point(fr.readLine());
        
            Plateau p = new Plateau(poison.x(), poison.y(), taille, taille, taille, poison , prop);
        
            ligne = fr.readLine();
        
            while(ligne!=null){
                String[] cases = ligne.split(":");
                for(int i=0; cases.length > i; i++){
                    Point tmp = new Point(cases[i]);
                    System.out.println(tmp);
                    p.supprimeComposant(new Case(tmp.x(), tmp.y(), Reglage.lis("tailleCase"), Reglage.lis("tailleCase"), (tmp.equals(poison))) );
                }
                ligne=fr.readLine();
            }
        
            return p;
        }catch(IOException e){
            System.out.println(e);
            return null;
        }
    }
    
}
