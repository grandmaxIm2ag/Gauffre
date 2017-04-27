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
public class TPoint {
    Point p;
    public TPoint() {
        p = new Point(0,0);
    }
    
    public void base(){
        assertEquals(p.x(), 0);
        assertEquals(p.y(), 0);
        assertEquals(p.toString(), "(0,0)");
        p.fixe(1, 2);
        assertEquals(p.x(), 1);
        assertEquals(p.y(), 2);
        assertTrue(p.equals(new Point(1,2)));
        assertFalse(p.equals(new Point(1,1)));
        p = new Point("(0,0)");
        assertEquals(p, new Point(0,0));
    }
    
    @Test
    public void test(){
        base();
    }
    
}
