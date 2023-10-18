/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.arena202301;


import javax.swing.JLabel;

/**
 *
 * @author diego
 */
public abstract class Personaje {
    String nombre;
    int espacios;
    int vida = 100;
    int daño;
    int golpes;
    JLabel label;

    public Personaje(String nombre, int espacios, int daño, int golpes) {
        this.nombre = nombre;
        this.espacios = espacios;
        this.daño = daño;
        this.golpes = golpes;

    }
    
    
    
    public abstract void pelear();
    
    public void morirse(){
        this.vida = 0;
    }

    public JLabel getLabel() {
        return label;
    }

    public void setLabel(JLabel label) {
        this.label = label;
    }
    
    
    
}
