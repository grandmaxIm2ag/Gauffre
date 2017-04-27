/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele.Ensembles;


/**
 *
 * @author maxence
 */
public interface File<T> {
    public boolean estVide();
    public void inserer(T e);
    public T extraire();
    public String toString();
}
