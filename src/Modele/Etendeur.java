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

    float x, y, l, h;
    float factX, factY;

    public void fixeEchelle(float fx, float fy) {
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

    public float posX() {
        return x;
    }

    public float posY() {
        return y;
    }

    public float l() {
        return l;
    }

    public float h() {
        return h;
    }

    public float factX() {
        return factX;
    }

    public float factY() {
        return factY;
    }
}
