/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.arena202301;

import javax.swing.JLabel;
import java.awt.*;
import javax.swing.ImageIcon;

/**
 *
 * @author User
 */
public class labelGolpes extends JLabel {

    public labelGolpes(String path, int x, int y) {
        ImageIcon imagen = new ImageIcon(path);
        setIcon(imagen);
        setSize(30,30);
        setLocation(x,y);
    }
    
    
}
