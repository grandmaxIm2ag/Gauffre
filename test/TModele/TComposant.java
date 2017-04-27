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
public class TComposant {
    ComposantGraphique c;
    public TComposant() {
        c = new ComposantGraphique(0,0,0,0) {
         

            @Override
            public boolean equals(Object o) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public boolean accept(Visiteur v) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
    }
    
    public void base(){
        assertEquals(c.l(), 0);
        assertEquals(c.h(), 0);
        assertEquals(c.location(), new Point(0,0));
        c.setLocation(1,1);
        assertEquals(c.location(), new Point(1,1));
    }
    
    @Test
    public void test(){
        base();
    }
    
}
