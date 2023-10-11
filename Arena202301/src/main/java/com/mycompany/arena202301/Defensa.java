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
public class Defensa extends Personaje{

    public Defensa(String nombre, Color color, int golpe) {
        super(nombre, color, golpe);
    }

    public String getNombre() {
        return nombre;
    }


    public int getVida() {
        return vida;
    }

    public int getGolpe() {
        return golpe;
    }

    public JLabel getLabel() {
        return label;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public void setGolpe(int golpe) {
        this.golpe = golpe;
    }
    
    
    
    @Override
    public void pelear() {
        
    }
    
}
