/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.arena202301;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author diego
 */
public class ThreadPersonaje extends Thread{
    Personaje personaje;
    Ventana refVentana;
    boolean isRunning = true;

    public ThreadPersonaje(Personaje personaje, Ventana refVentana) {
        this.personaje = personaje; 
        this.refVentana = refVentana;
    }

    @Override
    public void run() {
        
        while(isRunning){
            
            try {
                //determina x y y personaje.mover()
                refVentana.moverLabel(personaje.getLabel());
                sleep(1000);
            } catch (InterruptedException ex) {
                
            }
            
        }
        
    }
    
    
    
    
    
    
    
}
