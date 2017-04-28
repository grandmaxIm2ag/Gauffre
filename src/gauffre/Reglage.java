/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gauffre;

import java.util.NoSuchElementException;
import java.util.Properties;

public class Reglage {
    static Properties prop;

    public static void init(Properties p) {
        System.err.println("Partie en "+p.getProperty("nbManche"));
        prop = p;
    }
    
    public static int lis(String nom) {
        String value = prop.getProperty(nom);
        if (value != null)
            return Integer.parseInt(value);
        else
            throw new NoSuchElementException("Propriété "+nom+" manquante");
    }
}
