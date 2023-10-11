/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.arena202301;

import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.util.Random;
import javax.swing.TransferHandler;

public class Ventana extends javax.swing.JFrame {
    ArrayList<ThreadPersonaje> zombies;
    ArrayList<ThreadPersonaje> defensas;
    Tablero tableroJuego;
    private String tipoDefensa = null;
    
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
        ejercito();
        
        pnlPrincipal.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (tipoDefensa != null) {
                    int x = e.getX() / 40;
                    int y = e.getY() / 40;
                    setDefense(tipoDefensa, x, y);
                }
            }
        });
        
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
    
    public void ejercito(){
        String path = "C:\\Users\\User\\Documents\\Universidad\\II Semestre 2023"
                + "\\Introclases23II\\Arena202301\\src\\main\\java\\com\\mycompany\\arena202301\\img\\";

        JLabel contacto = new JLabel("Contacto");
        contacto.setIcon(new ImageIcon(path + "contactoP.png"));
        contacto.setSize(40, 40);
        contacto.setLocation(0, 0);

        JLabel medio = new JLabel("MedioAlcance");
        medio.setIcon(new ImageIcon(path + "medio2.png"));
        medio.setSize(40, 40);
        medio.setLocation(0, 80);

        JLabel aereo = new JLabel("Aereo");
        aereo.setIcon(new ImageIcon(path + "aereo.png"));
        aereo.setSize(40, 40);
        aereo.setLocation(0, 160);

        JLabel impacto = new JLabel("Impacto");
        impacto.setIcon(new ImageIcon(path + "mina.png"));
        impacto.setSize(40, 40);
        impacto.setLocation(80, 0);

        JLabel bloque = new JLabel("Bloque");
        bloque.setIcon(new ImageIcon(path + "nuez.png"));
        bloque.setSize(40, 40);
        bloque.setLocation(80, 80);

        JLabel multiple = new JLabel("Multiple");
        multiple.setIcon(new ImageIcon(path + "multiple.png"));
        multiple.setSize(40, 40);
        multiple.setLocation(80, 160);
        
        /*contacto.addMouseListener(new JLabelDragMouseAdapter(contacto,this));
        medio.addMouseListener(new JLabelDragMouseAdapter(medio,this));
        aereo.addMouseListener(new JLabelDragMouseAdapter(aereo,this));
        impacto.addMouseListener(new JLabelDragMouseAdapter(impacto,this));
        bloque.addMouseListener(new JLabelDragMouseAdapter(bloque,this));
        multiple.addMouseListener(new JLabelDragMouseAdapter(multiple,this));*/

        contacto.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tipoDefensa ="Contacto";
                
            }
        });

        medio.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tipoDefensa ="MedioAlcance";
            }
        });
        
        aereo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tipoDefensa ="Aereo";
            }
        });
        
        impacto.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tipoDefensa ="Impacto";
            }
        });
        
        bloque.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tipoDefensa ="Bloque";
            }
        });
        
        multiple.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tipoDefensa ="Multiple";
            }
        });
        ejercito.add(contacto);
        ejercito.add(medio);
        ejercito.add(aereo);
        ejercito.add(impacto);
        ejercito.add(bloque);
        ejercito.add(multiple);
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
                Zombie zombie = new Zombie("Medioalcance", Color.yellow, 3);
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
                Zombie zombie = new Zombie("Volador", Color.yellow, 3);
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
                Zombie zombie = new Zombie("Choque", Color.yellow, 20);
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
        int lastX = label.getX() / 40; 
        int lastY = label.getY() / 40; 

        int newX, newY;

        if (lastY > 13) { 
            newX = lastX; 
            newY = lastY - 1;
        } else if(lastY < 11 ) { 
            newX = lastX;
            newY = lastY +1;
        }
        else{
            if (lastX < 11){
                newX = lastX+1;
                newY = lastY;
            } else if (lastX > 13){
                newX = lastX-1;
                newY = lastY;
            } else{
                newX = lastX;
                newY = lastY;
            }
        }
        
        if(!esMovimientoValido(newX, newY)){
            newX = lastX;
            newY = lastY + 1;
        } else if(!esMovimientoValido(newX, newY)){
            newX = lastX;
            newY = lastY -1;
        } else if(!esMovimientoValido(newX, newY)){
            newX = lastX+1;
            newY = lastY;
        }else if(!esMovimientoValido(newX, newY)){
            newX = lastX-1;
            newY = lastY;
        }

        
        if (esMovimientoValido(newX, newY)) {
            
            tableroJuego.setValorEnCoordenadas(lastX, lastY, 0);

            
            tableroJuego.setValorEnCoordenadas(newX, newY, 1);

            
            int x = newX * 40;
            int y = newY * 40;

            
            label.setLocation(x, y);
        }
        
    }

    private boolean esMovimientoValido(int x, int y) {
        if (x >= 0 && x < 25 && y >= 0 && y < 25) {
            return tableroJuego.getTablero()[x][y] != 1;
        }
        return false;
    }
    
    public void setDefense(String tipo,int x, int y) {
        String path = "C:\\Users\\User\\Documents\\Universidad\\II Semestre 2023"
                + "\\Introclases23II\\Arena202301\\src\\main\\java\\com\\mycompany\\arena202301\\img\\";
        
        if (tableroJuego.getTablero()[x][y] == 0) {
            if (tipo.equals("Contacto")) {
            JLabel contacto = new JLabel("Contacto");
            contacto.setIcon(new ImageIcon(path + "contactoP.png"));
            Defensa defensa = new Defensa("Contacto", Color.yellow, 5);
            defensa.setLabel(contacto);

            ThreadPersonaje tp = new ThreadPersonaje(defensa,this);
            defensas.add(tp);
            pnlPrincipal.add(contacto);
            contacto.setSize(40,40);
            contacto.setLocation(x*40, y*40);
            } else if(tipo.equals("Aereo")){
                JLabel aereo = new JLabel("Aereo");
                aereo.setIcon(new ImageIcon(path + "aereo.png"));
                Defensa defensa = new Defensa("Aereo", Color.yellow, 5);
                defensa.setLabel(aereo);

                ThreadPersonaje tp = new ThreadPersonaje(defensa,this);
                defensas.add(tp);
                pnlPrincipal.add(aereo);
                aereo.setSize(40,40);
                aereo.setLocation(x*40, y*40);
            } else if(tipo.equals("MedioAlcance")){
                JLabel medioalcance = new JLabel("MedioAlcance");
                medioalcance.setIcon(new ImageIcon(path + "medio2.png"));
                Defensa defensa = new Defensa("Contacto", Color.yellow, 5);
                //defensa.setLabel(contacto);

                //ThreadPersonaje tp = new ThreadPersonaje(defensa,this);
                //defensas.add(tp);
                pnlPrincipal.add(medioalcance);
                medioalcance.setSize(40,40);
                medioalcance.setLocation(x*40, y*40);
            } else if(tipo.equals("Impacto")){
                JLabel impacto = new JLabel("Impacto");
                impacto.setIcon(new ImageIcon(path + "mina.png"));
                //Defensa defensa = new Defensa("Contacto", color.yellow, 5);
                //defensa.setLabel(contacto);

                //ThreadPersonaje tp = new ThreadPersonaje(defensa,this);
                //defensas.add(tp);
                pnlPrincipal.add(impacto);
                impacto.setSize(40,40);
                impacto.setLocation(x*40, y*40);
            } else if(tipo.equals("Bloque")){
                JLabel bloque = new JLabel("Bloque");
                bloque.setIcon(new ImageIcon(path + "nuez.png"));
                //Defensa defensa = new Defensa("Contacto", color.yellow, 5);
                //defensa.setLabel(contacto);

                //ThreadPersonaje tp = new ThreadPersonaje(defensa,this);
                //defensas.add(tp);
                pnlPrincipal.add(bloque);
                bloque.setSize(40,40);
                bloque.setLocation(x*40, y*40);
            } else if(tipo.equals("Multiple")){
                JLabel multiple = new JLabel("Multiple");
                multiple.setIcon(new ImageIcon(path + "multiple.png"));
                //Defensa defensa = new Defensa("Contacto", color.yellow, 5);
                //defensa.setLabel(contacto);

                //ThreadPersonaje tp = new ThreadPersonaje(defensa,this);
                //defensas.add(tp);
                pnlPrincipal.add(multiple);
                multiple.setSize(40,40);
                multiple.setLocation(x*40, y*40);
            }
        }
        tableroJuego.setValorEnCoordenadas(x, y, 2);
        System.out.println(x);
        System.out.println(y);
        
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
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        txfY = new javax.swing.JTextField();
        pnlPrincipal = new javax.swing.JPanel();
        ejercito = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1400, 1000));

        btnAgregar.setText("Crear");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnIniciar.setText("Iniciar");
        btnIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarActionPerformed(evt);
            }
        });

        jLabel2.setText("Ejercito #1");

        jButton1.setText("Colocar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        pnlPrincipal.setBackground(new java.awt.Color(193, 193, 121));
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

        javax.swing.GroupLayout ejercitoLayout = new javax.swing.GroupLayout(ejercito);
        ejercito.setLayout(ejercitoLayout);
        ejercitoLayout.setHorizontalGroup(
            ejercitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 240, Short.MAX_VALUE)
        );
        ejercitoLayout.setVerticalGroup(
            ejercitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 240, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(txfY, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txfX, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregar)
                    .addComponent(jButton1)
                    .addComponent(btnIniciar)
                    .addComponent(ejercito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(201, Short.MAX_VALUE)
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
                .addComponent(ejercito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(376, 376, 376))
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

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAgregarActionPerformed

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
    private javax.swing.JPanel ejercito;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel pnlPrincipal;
    private javax.swing.JTextField txfX;
    private javax.swing.JTextField txfY;
    // End of variables declaration//GEN-END:variables
}
