/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TModele;

import Modele.*;
import gauffre.*;
import java.util.*;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author grandmax
 */
public class TArbitre {
    Arbitre a;
    Properties prop;
    public TArbitre() {
        prop = Configuration.proprietes();
        a = new Arbitre(prop);
    }
    
    public void base(){
       a.init(Arbitre.JvJ);
       Chargeur c = new Chargeur();
       c.init(prop);
       assertTrue(a.plateau().equals(c.charger()));
       a.init("Ressources/Plateau/essaie1",Arbitre.JvJ);
       c.init("Ressources/Plateau/essaie1", prop);
       assertTrue(a.plateau().equals(c.charger()));
    }
    
    public void testHistorique(){
        a.init(Arbitre.JvJ);
        a.joue(new Point(24,24));
        String h = a.historique().extraire();
        a.historique().inserer(h);
        a.precedent();
        String r = a.arefaire().extraire();
        assertEquals(r,h);
        assertEquals(r,"(24,24)");
    }
    
    public void simulation1(){
        a = new Arbitre(prop);
        a.init(Arbitre.JvJ);
        a.joue(new Point(0,0));
        a.joue(new Point(4,3));
        a.joue(new Point(0,0));
        assertEquals(a.score(Arbitre.J2), 1);
        assertEquals(a.score(Arbitre.J2), 1);
    }
    
    @Test
    public void test(){
        base();
        testHistorique();
        simulation1();
    }
    
}
