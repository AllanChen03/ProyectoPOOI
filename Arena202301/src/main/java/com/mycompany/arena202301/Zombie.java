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
public class Zombie extends Personaje{

    public Zombie(String nombre, int espacios, int daño, int golpes) {
        super(nombre, espacios, daño, golpes);
    }

    public String getNombre() {
        return nombre;
    }


    public int getEspacios() {
        return espacios;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getDaño() {
        return daño;
    }

    public JLabel getLabel() {
        return label;
    }

    public int getGolpes() {
        return golpes;
    }

    public void setGolpes(int golpes) {
        this.golpes = golpes;
    }

    
    
    @Override
    public void pelear() {
        
    }
     
}
