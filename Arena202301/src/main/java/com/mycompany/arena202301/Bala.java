/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.arena202301;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author User
 */
public class Bala extends JLabel {
    private int vertical;
    private int horizontal;

    public Bala(String path,int x, int y, int vertical, int horizontal) {
        setIcon(new ImageIcon(path+"bala.png"));
        setSize(20,20);
        setLocation(x,y);
        this.vertical = vertical;
        this.horizontal = horizontal;
    }
    
    public void mover(){
        int nuevaX = getX() + horizontal;
        int nuevaY = getY() + vertical;
        setLocation(nuevaX,nuevaY);
    }
    
   
    
}
