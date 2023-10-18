/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.arena202301;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

/**
 *
 * @author User
 */
public class JLabelDragMouseAdapter implements MouseListener {

    private JLabel dragLabel;
    private Point point;
    private Ventana ventana;

    public JLabelDragMouseAdapter(JLabel label, Ventana ventana) {
        this.dragLabel = label;
        this.ventana = ventana;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        point = new Point(e.getPoint());
    }

    public void mouseDragged(MouseEvent e) {
        Point newLocation = e.getPoint();
        newLocation.translate(dragLabel.getX() - point.x, dragLabel.getY() - point.y);
        dragLabel.setLocation(newLocation);
        point = e.getPoint(); 
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Point dropPoint = e.getPoint();
        System.out.println(dropPoint.x);
        System.out.println(dropPoint.y);
        SwingUtilities.convertPointToScreen(dropPoint, dragLabel);
        /*ventana.setEjercito(dragLabel, dropPoint.x, dropPoint.y);*/
      
    }
    

    @Override
    public void mouseClicked(MouseEvent e) {
       
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }

}
