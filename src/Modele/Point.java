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
    public Point(String m){
        String tmp[] = m.split(",");
        int x = Integer.parseInt(tmp[0].substring(1));
        int y = Integer.parseInt(tmp[1].substring(0, tmp[1].length()-1));
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
    
    public boolean equals(Object o){
        if(o instanceof Point){
            Point p = (Point) o;
            return (p.x()==x && p.y()==y);
        }
        return false;
    }
    
    public String toString(){
        return "("+x+","+y+")";
    }
}
