/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.arena202301;

import java.awt.Color;
import java.awt.LayoutManager;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.util.Random;
import java.util.Set;

public class Ventana extends javax.swing.JFrame {
    ArrayList<ThreadPersonaje> zombies;
    ArrayList<ThreadPersonaje> defensas;
    Tablero tableroJuego;
    int fila;
    int columna;
    
    //ArrayList<Zombie> vivos;
    
    

    /**
     * Creates new form Ventana
     */
    public Ventana() {
        zombies = new ArrayList<ThreadPersonaje>();
        defensas = new ArrayList<ThreadPersonaje>();
        tableroJuego = new Tablero();
        initComponents();
        fuente();
        generarZombies(20);
    }
    
    public void fuente(){
        String path = "C:\\Users\\User\\Documents\\Universidad\\II Semestre 2023"
                + "\\Introclases23II\\Arena202301\\src\\main\\java\\com\\mycompany\\arena202301\\img\\";
        JLabel label = new JLabel("Fuente");
        label.setIcon(new ImageIcon(path + "fuente.png"));
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tableroJuego.setValorEnCoordenadas(11+i, 11+j, 2);
            }    
        }
        pnlPrincipal.add(label);
        label.setSize(120, 120);
        label.setLocation(12*40, 12*40);
        
        
    }
        
        
    

    public void generarZombies(int size) {
        String[] tiposDeZombies = {"Contacto", "Medioalcance", "Volador", "Choque"};
        Random random = new Random();
        String path = "C:\\Users\\User\\Documents\\Universidad\\II Semestre 2023"
                + "\\Introclases23II\\Arena202301\\src\\main\\java\\com\\mycompany\\arena202301\\img\\";
        for (int i = 0; i < size; i++) {
            String tipoZombie = tiposDeZombies[random.nextInt(tiposDeZombies.length)];
            if ("Contacto".equals(tipoZombie)){
                JLabel label = new JLabel("Contacto");
                label.setIcon(new ImageIcon(path + "zombie.png"));
                Zombie zombie = new Zombie("Contacto", Color.yellow, 5);
                zombie.setLabel(label);

                // Crear el thread
                ThreadPersonaje tp =  new ThreadPersonaje(zombie, this);
                zombies.add(tp);

                pnlPrincipal.add(label);
                setAparicion(label);
                label.setSize(40, 40);  
            } else if ("Medioalcance".equals(tipoZombie)){
                JLabel label = new JLabel("Medioalcance");
                label.setIcon(new ImageIcon(path + "medioalcance.png"));
                Zombie zombie = new Zombie("Medioalcance", Color.yellow, 5);
                zombie.setLabel(label);

                // Crear el thread
                ThreadPersonaje tp =  new ThreadPersonaje(zombie, this);
                zombies.add(tp);

                pnlPrincipal.add(label);
                setAparicion(label);
                label.setSize(40, 40);
            } else if ("Volador".equals(tipoZombie)){
                JLabel label = new JLabel("Volador");
                label.setIcon(new ImageIcon(path + "volador.png"));
                Zombie zombie = new Zombie("Volador", Color.yellow, 5);
                zombie.setLabel(label);

                // Crear el thread
                ThreadPersonaje tp =  new ThreadPersonaje(zombie, this);
                zombies.add(tp);

                pnlPrincipal.add(label);
                setAparicion(label);
                label.setSize(40, 40);
            } else if ("Choque".equals(tipoZombie)){
                JLabel label = new JLabel("Choque");
                label.setIcon(new ImageIcon(path + "choque.png"));
                Zombie zombie = new Zombie("Choque", Color.yellow, 5);
                zombie.setLabel(label);

                // Crear el thread
                ThreadPersonaje tp =  new ThreadPersonaje(zombie, this);
                zombies.add(tp);

                pnlPrincipal.add(label);
                setAparicion(label);
                label.setSize(40, 40);
            }
            
        }
    }
    
    
    public void setAparicion(JLabel label) {
        int colRow = (new Random()).nextInt(2);
        int dir = (new Random()).nextInt(2);
        int x;
        int y;

        do {
            if (colRow == 0) {
                if (dir == 0) {
                    x = (new Random()).nextInt(3);
                    y = (new Random()).nextInt(24);
                } else {
                    x = (new Random()).nextInt(3) + 21;
                    y = (new Random()).nextInt(24);
                }
            } else {
                if (dir == 0) {
                    x = (new Random()).nextInt(24);
                    y = (new Random()).nextInt(3);
                } else {
                    x = (new Random()).nextInt(24);
                    y = (new Random()).nextInt(3) + 21;
                }
            }
        } while (tableroJuego.getTablero()[x][y] == 1 || x < 0 || x >= 25 || y < 0 || y >= 25);

        if (tableroJuego.getTablero()[x][y] == 0) {
            tableroJuego.setValorEnCoordenadas(x, y, 1); 
            label.setLocation(x * 40, y * 40);
        }
    }


    public void moverLabel(JLabel label) {
        int lastX = label.getX() / 40; // Obtiene la casilla anterior en X
        int lastY = label.getY() / 40; // Obtiene la casilla anterior en Y

        int newX, newY;
        // Determina si el movimiento será horizontal o vertical de manera aleatoria
        int movimientoHorizontal = (new Random()).nextInt(2);

        do {
            if (movimientoHorizontal == 0) {
                // Movimiento horizontal (izquierda o derecha)
                newX = lastX + (new Random()).nextInt(3)-1 ; 
                newY = lastY;
            } else {
                // Movimiento vertical (arriba o abajo)
                newX = lastX;
                newY = lastY + (new Random()).nextInt(3)-1 ; 
            }
        } while (!esMovimientoValido(newX, newY));

        // Actualiza la matriz del tablero para la casilla anterior 0
        tableroJuego.setValorEnCoordenadas(lastX, lastY, 0);

        // Actualiza la matriz del tablero para la nueva casilla 1
        tableroJuego.setValorEnCoordenadas(newX, newY, 1);

        // Calcula la nueva posición en la interfaz gráfica
        int x = newX * 40;
        int y = newY * 40;

        // Establece la nueva posición del JLabel en la interfaz gráfica
        label.setLocation(x, y);

    }

    // Verifica si el movimiento es válido (no se superpone con otras imágenes)
    private boolean esMovimientoValido(int x, int y) {
        if (x >= 0 && x < 25 && y >= 0 && y < 25) {
            return tableroJuego.getTablero()[x][y] != 1;
        }
        return false;
    }

    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnAgregar = new javax.swing.JButton();
        btnIniciar = new javax.swing.JButton();
        txfX = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        txfY = new javax.swing.JTextField();
        pnlPrincipal = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1400, 1000));

        btnAgregar.setText("Crear");

        btnIniciar.setText("Iniciar");
        btnIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarActionPerformed(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane2.setViewportView(jTextArea2);

        jLabel1.setText("Ejercito #2");

        jLabel2.setText("Ejercito #1");

        jButton1.setText("Colocar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        pnlPrincipal.setBackground(new java.awt.Color(102, 102, 0));
        pnlPrincipal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout pnlPrincipalLayout = new javax.swing.GroupLayout(pnlPrincipal);
        pnlPrincipal.setLayout(pnlPrincipalLayout);
        pnlPrincipalLayout.setHorizontalGroup(
            pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 998, Short.MAX_VALUE)
        );
        pnlPrincipalLayout.setVerticalGroup(
            pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txfY, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txfX, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregar)
                    .addComponent(jButton1)
                    .addComponent(btnIniciar))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(189, Short.MAX_VALUE)
                .addComponent(btnIniciar)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAgregar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txfX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txfY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(136, 136, 136))
            .addComponent(pnlPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarActionPerformed
        for (int i = 0; i < zombies.size(); i++) {
            ThreadPersonaje get = zombies.get(i);
            get.start();
            
            
        }
        
        
        
        
    }//GEN-LAST:event_btnIniciarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
            //crea el label
            tableroJuego.imprimirTablero();
            
        
           
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ventana().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnIniciar;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JPanel pnlPrincipal;
    private javax.swing.JTextField txfX;
    private javax.swing.JTextField txfY;
    // End of variables declaration//GEN-END:variables
}
