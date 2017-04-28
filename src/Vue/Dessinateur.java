/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;
import Joueurs.Joueur;
import Modele.*;
import Modele.Etendeur;
import javafx.scene.paint.Color;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.geometry.Bounds;


/**
 *
 * @author grandmax
 */
public class Dessinateur extends Visiteur{
    
    Canvas c;
    GraphicsContext gc;
    Etendeur e;
    
    public Dessinateur(Canvas c) {
        this.c=c;
        e = new Etendeur();
        e.fixeEchelle(50, 50);
        gc = c.getGraphicsContext2D();
    }
        
    @Override
    public boolean visite(Case c){
        e.fixeComposant(c);
        if(!c.detruit()){
            gc.setStroke(Color.BLACK);
            if (c.empoisonnee())
                gc.setFill(Color.BLUEVIOLET);
            else
                gc.setFill(Color.BLUE);
            gc.fillRect(e.posX(), e.posY(), e.l(), e.h());
            gc.strokeRect(e.posX(), e.posY(), e.l(), e.h());
        }
        return false;
    }
    @Override 
    public boolean visite(Poison p){
        e.fixeComposant(p);
        gc.setFill(Color.RED);
        gc.fillOval(e.posX(), e.posY(), e.l(), e.h());
        return false;
    }
    @Override
    public boolean visite(Plateau p){
        gc.clearRect(0, 0, c.getWidth(), c.getHeight());
        gc.setStroke(Color.BLACK);
        gc.strokeRect(0, 0, c.getWidth(), c.getHeight());
        return false;
    }
    @Override
    public boolean visite(Joueur j){
        e.fixeComposant(j);
        String s= j.getNom()+":"+Integer.toString(j.getScore());
        /*Text t = new Text(e.posX(),e.posY(),s );
        t.setFont(f);
        Bounds b = t.getBoundsInLocal();*/
        Font f= new Font(10);
        if(j.getMain()){
            gc.setStroke(Color.RED);
        }else{
            gc.setStroke(Color.BLACK);
        }
        gc.save();
        gc.setFont(f);
        gc.strokeText(s, j.location().x(),j.location().y());
        gc.restore();

        return false;
    }
    @Override
    public boolean visite(Message m){
        Text t = new Text(m.message());
        Font f = new Font(10);
        t.setFont(f);
        Bounds b = t.getBoundsInLocal();

        // On dessine au milieu de l'écran en prenant garde à restaurer la
        // fonte précédente une fois le tracé terminé
        gc.setStroke(Color.BLACK);
        gc.save();
        gc.setFont(f);
        gc.strokeText(m.message(), m.location().x(),m.location().y());
        gc.restore();
        
        return false;
    }
}

