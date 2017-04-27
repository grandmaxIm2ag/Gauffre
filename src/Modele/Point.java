/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

/**
 *
 * @author grandmax
 */
public class Point {
    int x, y;
    
    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    public void fixe(int x, int y){
        this.x = x; this.y = y;
    }
    
    public int x(){
        return x;
    }
    public int y(){
        return y;
    }
}
