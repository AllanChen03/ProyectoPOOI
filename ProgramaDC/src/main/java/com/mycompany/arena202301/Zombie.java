package com.mycompany.arena202301;

import java.io.Serializable;

/**
 *
 * @author
 */
public class Zombie implements Serializable{

    public static Zombie zombie = null;
    
    String nombre;
    int golpe;
    int aparicion;
    int vida;
    String imagen1;
    String imagen2;
    private String tipo;
    
    public Zombie(String nombre, int golpe, int aparicion, int vida, String imagen1, String imagen2, String tipo) {
        this.nombre = nombre;
        this.golpe = golpe;
        this.aparicion = aparicion;
        this.vida = vida;
        this.imagen1 = imagen1;
        this.imagen2 = imagen2;
        this.tipo = tipo;
    }

    public void pelear() {
        
    }
    
//    public static void cargarZombies(){
//        Zombie tmpZombie = SerializarObjeto.deserializarObjeto("Zombies.dat", Zombie.class);
//        if (tmpZombie == null){
//            System.out.println("Creando los Datos Iniciales");
//            //Defensa.defensa = new Defensa();
//        }
//        else{
//            System.out.println("Cargando Datos del Archivo");
//            Zombie.zombie = tmpZombie;
//        }
//    }
}