/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

/**
 *
 * @author mullerl
 */
// Class mise à disposition du dessinateur pour redimensionner les composants
// à la taille de son dessin
public class Etendeur {

    double x, y, l, h;
    public double factX, factY;

    public void fixeEchelle(double fx, double fy) {
        //System.out.println(fx+"-"+fy);
        factX = fx;
        factY = fy;
    }

    // Ici on triche un peu :
    // Comme on sait que les dimensions du composant ne varient pas
    // durant toute la vie de l'étendeur, on stocke directement le
    // résultat à l'échelle.
    public void fixeComposant(ComposantGraphique c) {
        x = c.x() * factX;
        y = c.y() * factY;
        l = c.l() * factX;
        h = c.h() * factY;
    }
    
    public Point souris(double curseurX, double curseurY){
        x = curseurX * factX ;
        y = curseurY * factY ;
        return new Point((int)x, (int) y );
    }

    public double posX() {
        return x;
    }

    public double posY() {
        return y;
    }

    public double l() {
        return l;
    }

    public double h() {
        return h;
    }

    public double factX() {
        return factX;
    }

    public double factY() {
        return factY;
    }
}
