/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.programa;
import java.io.Serializable;
/**
 *
 * @author User
 */
public class Personaje implements Serializable {
    private String Path;
    private String nombre;
    private int daño;

    public Personaje(String Path, String nombre, int daño) {
        this.Path = Path;
        this.nombre = nombre;
        this.daño = daño;
    }

    public String getPath() {
        return Path;
    }

    public String getNombre() {
        return nombre;
    }

    public int getDaño() {
        return daño;
    }

    public void setPath(String Path) {
        this.Path = Path;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDaño(int daño) {
        this.daño = daño;
    }
    
}
