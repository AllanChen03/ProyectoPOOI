/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.arena202301;

import java.awt.Color;
import javax.swing.JLabel;

/**
 *
 * @author diego
 */
public abstract class Personaje {
    String nombre;
    Color color;
    int vida = 100;
    int golpe;
    JLabel label;

    public Personaje(String nombre, Color color, int golpe) {
        this.nombre = nombre;
        this.color = color;
        this.golpe = golpe;
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
