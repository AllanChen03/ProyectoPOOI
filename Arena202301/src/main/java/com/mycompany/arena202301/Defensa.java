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
public class Defensa extends Personaje{

    public Defensa(String nombre, int espacios, int daño, int golpes) {
        super(nombre, espacios, daño, golpes);
    }

    public String getNombre() {
        return nombre;
    }

    public int getGolpes() {
        return golpes;
    }

    public void setGolpes(int golpes) {
        this.golpes = golpes;
    }


    public int getVida() {
        return vida;
    }

    public int getDaño() {
        return daño;
    }

    public JLabel getLabel() {
        return label;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public void setGolpe(int golpe) {
        this.daño = daño;
    }
    
    
    
    @Override
    public void pelear() {
        
    }
    
    public void morir(){
        
    }
    
}
