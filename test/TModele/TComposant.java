/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TModele;

import Modele.*;
import java.util.*;
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
    
    public void testCase(){
        Case c = new Case(1,1,1,1);
        assertTrue(c.equals(c));
        Case c2 = new Case(1,1,1,0);
        assertFalse(c.equals(c2));
        assertFalse(c.empoisonnee());
        assertFalse(c.detruit());
        c.fixeProp(Case.DETRUIT, true);
        assertTrue(c.detruit());
        c.fixeProp(Case.EMPOISONNEE, true);
        assertTrue(c.empoisonnee());
        c.fixeProp(Case.DETRUIT, false);
        c.maj(new Point(3,3));
        assertFalse(c.detruit());
        c.maj(new Point(0,0));
        assertTrue(c.detruit());
    }
    
    public void testPoison(){
        Poison p = new Poison(1,1,1,1);
        assertTrue(p.equals(new Poison(1,1,1,1)));
        assertFalse(p.equals(new Poison(1,1,1,2)));
    }
    
    public void testMessage(){
        Message m = new Message(1,1,1,1,"test");
        assertEquals("test", m.message());
        assertFalse(m.message().equals("toto"));
        assertTrue(m.equals(new Message(1,1,1,1,"test")));
        assertFalse(m.equals(new Message(1,1,1,1,"toto")));
    }
    
    public void testPlateau(){
        Plateau p = new Plateau(0,0,0,0,5,new Point(0,0));
        Case c = new Case(6,6,6,6);
        assertEquals(5, p.tailleInitiale());
        assertEquals(26, p.taille());
        assertEquals(p.poison(), new Point(0,0));
        assertEquals(p.toString(), "5\n(0,0)");
        assertEquals(p.observable().size(),25);
        p.ajoutComposant(c);
        assertEquals(p.observable().size(),26);
        assertEquals(27, p.taille());
        p.supprimeComposant(c);
        assertEquals(p.observable().size(),25);
        assertEquals(26, p.taille());
        assertTrue(p.estPoison(new Point(0,0)));
        assertFalse(p.estPoison(new Point(0,1)));
        p.supprimeComposant(new Poison(0,0,1,1));
        assertEquals(p.observable().size(),25);
        assertEquals(25, p.taille());
        p.maj(c.location());
        boolean b = false;
        Iterator<Observateur> it = p.observable().observateurs().iterator();
        while(it.hasNext()){
            Observateur o = it.next();
            if(o instanceof Case){
                Case tmp = (Case)o;
                b |= tmp.detruit();
            }
        }
        assertFalse(b);
        p.maj(new Point(0,0));
        b = true;
        it = p.observable().observateurs().iterator();
        while(it.hasNext()){
            Observateur o = it.next();
            if(o instanceof Case){
                Case tmp = (Case)o;
                b &= tmp.detruit();
            }
        }
        assertTrue(b);
    }
    
    public void testChargeur(){
        Chargeur c = new Chargeur();
        c.init();
        Plateau p = c.charger();
        assertEquals(p.poison(), new Point(0,0));
        assertEquals(p.tailleInitiale(), 10);
        assertEquals(p.taille(), 101);
        c.init("Ressources/Plateau/essaie1");
        p = c.charger();
        assertEquals(p.poison(), new Point(1,2));
        assertEquals(p.tailleInitiale(), 10);
        assertEquals(p.taille(), 98);
    }
    
    @Test
    public void test(){
        base();
        testCase();
        testMessage();
        testPlateau();
        testChargeur();
    }
    
}
