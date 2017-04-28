/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gauffre;

import java.io.*;
import java.util.*;

/**
 *
 * @author grandmax
 */
public class Configuration {
    
    static void erreur(Exception e, String nom) {
        System.err.println("Impossible de charger " + nom);
        System.err.println(e);
        System.exit(1);
    }

    static void chargerProprietes(Properties p, InputStream in) {
        try {
            p.load(in);
        } catch (IOException e) {
            erreur(e, "Ressources/defaut.cfg");
        }
    }

    public static Properties proprietes() {
        Properties p;
        InputStream in = ClassLoader.getSystemClassLoader().getResourceAsStream("Ressources/defaut.cfg");
        Properties defaut = new Properties();
        chargerProprietes(defaut, in);
        p = defaut;
        return p;
    }
}
