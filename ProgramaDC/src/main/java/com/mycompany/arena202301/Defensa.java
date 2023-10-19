package com.mycompany.arena202301;

import java.io.Serializable;

/**
 *
 * @author 
 */
public class Defensa implements Serializable{
    
    public static Defensa defensa = null;
    
    private String nombre;
    private int vida;
    private int golpe;
    private int aparicion;
    private int rango;
    private int campos;
    private String imagen1;
    private String imagen2;
    private String tipo;
    
    //Para armas de mediano alcance y ataque multiple:
    public Defensa(String nombre, int vida, int golpe, int aparicion, int campos, String imagen1, String imagen2, String tipo) {
        this.nombre = nombre;
        this.vida = vida;
        this.golpe = golpe;
        this.aparicion = aparicion;
        this.campos = campos;
        this.imagen1 = imagen1;
        this.imagen2 = imagen2;
        this.tipo = tipo;
    }
    
    //Para armas de impacto:
    public Defensa(String nombre, int vida, int golpe, int aparicion, int rango, int campos, String imagen1, String imagen2, String tipo) {
        this.nombre = nombre;
        this.vida = vida;
        this.golpe = golpe;
        this.aparicion = aparicion;
        this.rango = rango;
        this.campos = campos;
        this.imagen1 = imagen1;
        this.imagen2 = imagen2;
        this.tipo = tipo;
    }
    
    //Para armas aéreas:
    public Defensa(String nombre, int vida, int golpe, int aparicion, String imagen1, String imagen2, String tipo) {
        this.nombre = nombre;
        this.vida = vida;
        this.golpe = golpe;
        this.aparicion = aparicion;
        this.imagen1 = imagen1;
        this.imagen2 = imagen2;
        this.tipo = tipo;
    }
    
    //Para bloques:
    public Defensa(String nombre, int vida, int aparicion, String imagen1, String tipo) {
        this.nombre = nombre;
        this.vida = vida;
        this.aparicion = aparicion;
        this.imagen1 = imagen1;
        this.tipo = tipo;
    }
    
    //Para armas de contacto:
    public Defensa(String nombre, int vida, int golpe, int aparicion, String imagen1, String tipo) {
        this.nombre = nombre;
        this.vida = vida;
        this.golpe = golpe;
        this.aparicion = aparicion;
        this.imagen1 = imagen1;
        this.tipo = tipo;
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

    public void setVida(int vida) {
        this.vida = vida;
    }

    public void setGolpe(int golpe) {
        this.golpe = golpe;
    }
    
    public void pelear() {
        
    }
      
//    public static void cargarDefensas(){
//        Defensa tmpDefensa = SerializarObjeto.deserializarObjeto("Defensas.dat", Defensa.class);
//        if (tmpDefensa == null){
//            System.out.println("Creando los Datos Iniciales");
//            //Defensa.defensa = new Defensa();
//        }
//        else{
//            System.out.println("Cargando Datos del Archivo");
//            Defensa.defensa = tmpDefensa;
//        }
//    }
}