/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.arena202301;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.*;

public class FileManager {
    private static final String NOMBRE_ARCHIVO = "C:\\Users\\User\\Documents\\Universidad\\II Semestre 2023"
                + "\\Introclases23II\\Arena202301\\src\\main\\java\\com\\mycompany\\arena202301\\datos.txt";

    public static void escribirInformacion(String nombre, int nivel) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(NOMBRE_ARCHIVO, true))) {
            String linea = nombre + "," + nivel;
            writer.write(linea);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int leerInformacion(String nombre) {
        int nivel = -1; 
        try (BufferedReader reader = new BufferedReader(new FileReader(NOMBRE_ARCHIVO))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 2) {
                    String nombreEnArchivo = partes[0];
                    int nivelEnArchivo = Integer.parseInt(partes[1]);
                    if (nombreEnArchivo.equals(nombre)) {
                        nivel = nivelEnArchivo;
                        break;  
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return nivel;
    }
}






