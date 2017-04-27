/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TModele;

import Modele.*;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author grandmax
 */
public class TObservateur {
    Observable obs;
    public TObservateur() {
        obs = new Observable();
    }
    
    public void base(){
        assertEquals(0, obs.size());
        Case c = new Case(1,2,1,1);
        Case c2 = new Case(2,2,1,1);
        obs.ajout(c); obs.ajout(c2);
        assertEquals(2, obs.size());
        assertTrue(obs.contains(c));
        assertTrue(obs.contains(c2));
        obs.supprime(c2);
        assertEquals(1, obs.size());
        assertTrue(obs.contains(c));
        assertFalse(obs.contains(c2));
        
    }
    
    @Test
    public void test(){
        base();
    }
    
}
