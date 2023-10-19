/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.programa;

/**
 *
 * @author User
 */
public class Tablero {
    int[][] tablero;

    public Tablero() {
        this.tablero = new int[25][25];
        for (int i = 0; i < 25; i++) {
            for (int j = 0; j < 25; j++) {
                tablero[i][j] = 0;
            }
            
        }
        
    }

    public int[][] getTablero() {
        return tablero;
    }

    public void setValorEnCoordenadas(int x, int y, int valor) {
        if (x >= 0 && x < tablero.length && y >= 0 && y < tablero.length) {
            tablero[x][y] = valor;
        } 
    }
    
    public void imprimirTablero() {
        for (int i = 0; i < 25; i++) {
            for (int j = 0; j < 25; j++) {
                System.out.print(tablero[i][j] + " ");
            }
            System.out.println("\n");
        }
    }
}
