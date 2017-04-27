/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.io.*;

/**
 *
 * @author grandmax
 */
public class Chargeur {
    BufferedReader fr;
    
    public void init(String m){
        try{
          fr = new BufferedReader(new FileReader(m));  
        }catch(IOException e){
            System.out.println(e);
        }
    }
    public void init(){
        init("Ressources/Plateau/default");
    }
    public Plateau charger(){
        try{
            int taille;
            String ligne = fr.readLine();
            taille = Integer.parseInt(ligne);
            Point poison = new Point(fr.readLine());
        
            Plateau p = new Plateau(poison.x(), poison.y(), taille+5, taille+5, taille, poison );
        
            ligne = fr.readLine();
        
            while(ligne!=null){
                String[] cases = ligne.split(":");
                for(int i=0; cases.length > i; i++){
                    Point tmp = new Point(cases[i]);
                    p.supprimeComposant(new Case(tmp.x(), tmp.y(), 1, 1, (tmp.equals(poison))) );
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
