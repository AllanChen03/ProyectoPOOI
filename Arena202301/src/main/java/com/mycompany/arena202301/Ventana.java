/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.arena202301;

import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.Timer;
import javax.swing.TransferHandler;
import javax.transaction.xa.Xid;

public class Ventana extends javax.swing.JFrame {
    ArrayList<ThreadPersonaje> zombies;
    ArrayList<ThreadPersonaje> defensas;
    ArrayList<Personaje> defensasAtacar;
    private Map<Point, Defensa> posicionDefensas  = new HashMap<>();
    private Map<Point, Zombie> posicionZombies = new HashMap<>();
    private Map<JLabel, Zombie> labelZombie = new HashMap<>();
    Tablero tableroJuego;
    private String tipoDefensa = null;
    private int nivel = 1;
    private int cantEjercito = 15;
    private int espaciosUtilizados = 0;
    private Fuente fuente;
    boolean perdedor = false;
    String infoFinal= " ";
    
    //ArrayList<Zombie> vivos;
    
    

    /**
     * Creates new form Ventana
     */
    public Ventana() {
        zombies = new ArrayList<ThreadPersonaje>();
        defensas = new ArrayList<ThreadPersonaje>();
        defensasAtacar = new ArrayList<Personaje>();
        initComponents();       
        ejercito();
        pnlPrincipal.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (tipoDefensa != null) {
                    int x = e.getX() / 40;
                    int y = e.getY() / 40;
                    colocarDefensa(tipoDefensa, x, y,cantEjercito+(nivel*5), espaciosUtilizados, nivel);
                }
            }
        });
        iniciarJuego(nivel);
        
    }
    
    public void iniciarJuego(int nivel){
        infoFinal = " ";
        tableroJuego = new Tablero();
        fuente();
        generarZombies(cantEjercito+(nivel*5));
    }
    
    public void fuente(){
        String path = "C:\\Users\\User\\Documents\\Universidad\\II Semestre 2023"
                + "\\Introclases23II\\Arena202301\\src\\main\\java\\com\\mycompany\\arena202301\\img\\";
        JLabel label = new JLabel("Fuente");
        label.setIcon(new ImageIcon(path + "fuente.png"));
        fuente = new Fuente(300, label);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tableroJuego.setValorEnCoordenadas(11+i, 11+j, 3);
            }    
        }
        pnlPrincipal.add(label);
        label.setSize(120, 120);
        label.setLocation(11*40, 11*40);
        
        
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
        int contadorContacto = 0;
        int contadorMedio = 0;
        int contadorVolador = 0;
        int contadorChoque = 0;
        int porcentaje = 5 +  (new Random()).nextInt(16);
        for (int i = 0; i < size; i++) {      
            String tipoZombie = tiposDeZombies[random.nextInt(tiposDeZombies.length)];
            if ("Contacto".equals(tipoZombie)){
                contadorContacto +=1;
                JLabel label = new JLabel("Contacto");
                label.setName(label.getText()+ contadorContacto);
                label.setIcon(new ImageIcon(path + "zombie.png"));
                Zombie zombie = new Zombie("Contacto", 1, 5+((nivel-1)*(porcentaje)/10),0);
                zombie.setVida(100+((nivel-1)*(porcentaje)/10));
                zombie.setLabel(label);

                // Crear el thread
                ThreadPersonaje tp =  new ThreadPersonaje(zombie, this);
                zombies.add(tp);
                pnlPrincipal.add(label);
                colocarZombie(zombie);
                label.setSize(40, 40);  
                label.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        mostrarInformacionZombie(zombie); 
                    }
                });
                infoFinal += "Zombie tipo: " + zombie.getLabel().getName() + " Vida inicial: " 
                        + zombie.getVida() + " Poder de golpe: " + zombie.getDaño() + "\n";
            } else if ("Medioalcance".equals(tipoZombie)){
                contadorMedio += 1;
                JLabel label = new JLabel("Medioalcance");
                label.setIcon(new ImageIcon(path + "medioalcance.png"));
                Zombie zombie = new Zombie("Medioalcance",1, 2+((nivel-1)*(porcentaje)/10),0);
                zombie.setVida(100+((nivel-1)*(porcentaje)/10));
                zombie.setLabel(label);
                label.setName(label.getText()+ contadorMedio);

                // Crear el thread
                ThreadPersonaje tp =  new ThreadPersonaje(zombie, this);
                zombies.add(tp);
    
                pnlPrincipal.add(label);
                colocarZombie(zombie);
                label.setSize(40, 40);
                label.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        mostrarInformacionZombie(zombie); 
                    }
                });
                infoFinal += "Zombie tipo: " + zombie.getLabel().getName() + " Vida inicial: " 
                        + zombie.getVida() + " Poder de golpe: " + zombie.getDaño() + "\n";
            } else if ("Volador".equals(tipoZombie)){
                contadorVolador += 1;
                JLabel label = new JLabel("Volador");
                label.setIcon(new ImageIcon(path + "volador.png"));
                Zombie zombie = new Zombie("Volador", 1, 3+((nivel-1)*(porcentaje)/10),0);
                zombie.setVida(100+((nivel-1)*(porcentaje)/10));
                zombie.setLabel(label);
                label.setName(label.getText()+ contadorVolador);

                // Crear el thread
                ThreadPersonaje tp =  new ThreadPersonaje(zombie, this);
                zombies.add(tp);
                pnlPrincipal.add(label);
                colocarZombie(zombie);
                label.setSize(40, 40);
                label.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        mostrarInformacionZombie(zombie); 
                    }
                });
                infoFinal += "Zombie tipo: " + zombie.getLabel().getName() + " Vida inicial: " 
                        + zombie.getVida()  + " Poder de golpe: " + zombie.getDaño() + "\n";
            } else if ("Choque".equals(tipoZombie)){
                contadorChoque += 1;
                JLabel label = new JLabel("Choque");
                label.setIcon(new ImageIcon(path + "choque.png"));
                Zombie zombie = new Zombie("Choque", 1, 10+((nivel-1)*(porcentaje/10)),0);
                zombie.setVida(100+((nivel-1)*(porcentaje)/10));
                zombie.setLabel(label);
                label.setName(label.getText()+ contadorChoque);

                // Crear el thread
                ThreadPersonaje tp =  new ThreadPersonaje(zombie, this);
                zombies.add(tp);

                pnlPrincipal.add(label);
                colocarZombie(zombie);
                label.setSize(40, 40);
                label.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        mostrarInformacionZombie(zombie); 
                    }
                });
                infoFinal += "Zombie tipo: " + zombie.getLabel().getName() + " Vida inicial: " 
                        + zombie.getVida()  + " Poder de golpe: " + zombie.getDaño() + "\n";
            } 
            
        }
    }
    private void mostrarInformacionZombie(Zombie zombie){
        String informacion = "Zombie tipo: " + zombie.getNombre() + "\n" 
                + "Vida: "+zombie.getVida() + "\n" + "Golpes: " + zombie.getGolpes() + " de: " 
                + zombie.getDaño() + " de daño";
        JOptionPane.showMessageDialog(pnlPrincipal, informacion);
    }
    
    
    public void colocarZombie(Zombie zombie) {
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
            JLabel label = zombie.getLabel();
            posicionZombies.put(new Point(x,y),zombie);
            labelZombie.put(label,zombie);
            label.setLocation(x * 40, y * 40);
        }
    }


    public void moverLabel(JLabel label) throws InterruptedException {
        Zombie zombie = labelZombie.get(label);
        String tipoGolpe = zombie.getNombre();
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
            /*if(zombie.getNombre().equals("Medioalcance")){
                if (lastX+3 < 25 && lastY + 3 <25 && lastX - 3 >= 0 && lastY - 3 >= 0){
                    if (tableroJuego.getTablero()[lastX+3][lastY] == 2){
                        ataqueZombieMedio(lastX,lastY,lastX+3,lastY,zombie);
                    } else if (tableroJuego.getTablero()[lastX][lastY+3] == 2){
                        ataqueZombieMedio(lastX,lastY,lastX,lastY+3,zombie);
                    } else if (tableroJuego.getTablero()[lastX-3][lastY] == 2){
                        ataqueZombieMedio(lastX,lastY,lastX-3,lastY,zombie);
                    } else if (tableroJuego.getTablero()[lastX][lastY-3] == 2){
                        ataqueZombieMedio(lastX,lastY,lastX,lastY-3,zombie);
                    }
                } else{
                    tableroJuego.setValorEnCoordenadas(lastX, lastY, 0);
                    tableroJuego.setValorEnCoordenadas(newX, newY, 1);
                    posicionZombies.remove(new Point(lastX,lastY));
                    posicionZombies.put(new Point(newX, newY), zombie);
                    int x = newX * 40;
                    int y = newY * 40;
                    label.setLocation(x, y);
                }        
            }*/ 
            if (tableroJuego.getTablero()[newX][newY] == 2) {
                atacarDefensa(newX,newY, zombie);
                sleep(1000);
                
            }else if(tableroJuego.getTablero()[newX][newY] == 3){
                atacarFuente(newX,newY, zombie);
                sleep(1000);
            }
            else {
                tableroJuego.setValorEnCoordenadas(lastX, lastY, 0);
                tableroJuego.setValorEnCoordenadas(newX, newY, 1);
                posicionZombies.remove(new Point(lastX,lastY));
                posicionZombies.put(new Point(newX, newY), zombie);
                int x = newX * 40;
                int y = newY * 40;
                label.setLocation(x, y);

            }
        }
    }

    private boolean esMovimientoValido(int x, int y) {
        if (x >= 0 && x < 25 && y >= 0 && y < 25) {
            return tableroJuego.getTablero()[x][y] != 1;
        }
        return false;
    }
    
    public void colocarDefensa(String tipo,int x, int y, int espaciosTotales, int espaciosUtilizados, int nivel) {
        String path = "C:\\Users\\User\\Documents\\Universidad\\II Semestre 2023"
                + "\\Introclases23II\\Arena202301\\src\\main\\java\\com\\mycompany\\arena202301\\img\\";
        Map<String, Integer> espaciosDefensa = new HashMap<String, Integer>() {{
            put("Contacto", 1);
            put("Aereo", 3);
            put("MedioAlcance", 2);
            put("Impacto", 4);
            put("Bloque", 1);
            put("Multiple", 5);
        }};
        int contadorContacto = 0;
        int contadorMedio = 0;
        int contadorImpacto = 0;
        int contadorMultiple = 0;
        int contadorAereo = 0;
        int porcentaje = 5 + (new Random()).nextInt(16);
        if (espaciosUtilizados + espaciosDefensa.get(tipo) <= espaciosTotales){
            if(tableroJuego.getTablero()[x][y] == 0){
                if (tipo.equals("Contacto")) {
                    contadorContacto += 1;
                    JLabel contacto = new JLabel("Contacto");
                    contacto.setIcon(new ImageIcon(path + "contactoP.png"));
                    Defensa defensa = new Defensa("Contacto", 1, 3+((nivel-1)*(porcentaje/10)),0);
                    defensa.setVida(100+((nivel-1)*(porcentaje/10)));
                    defensa.setLabel(contacto);
                    contacto.setName("Contacto" + contadorContacto);

                    ThreadPersonaje tp = new ThreadPersonaje(defensa,this);
                    defensas.add(tp);
                    defensasAtacar.add(defensa);
                    pnlPrincipal.add(contacto);
                    contacto.setSize(40,40);
                    contacto.setLocation(x*40, y*40);
                    tableroJuego.setValorEnCoordenadas(x, y, 2);
                    actualizarEspacios(espaciosDefensa.get(tipo));
                    posicionDefensas.put(new Point(x,y), defensa);
                    contacto.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            mostrarInformacionDefensas(defensa); 
                        }
                    });
                    infoFinal += "Defensa tipo: " + defensa.getLabel().getName() + " Vida inicial: " 
                        + defensa.getVida()  + " Poder de golpe: " + defensa.getDaño() + "\n";


                } else if(tipo.equals("Aereo")){
                    contadorAereo +=1;
                    JLabel aereo = new JLabel("Aereo");
                    aereo.setIcon(new ImageIcon(path + "aereo.png"));
                    Defensa defensa = new Defensa("Aereo", 3, 5+((nivel-1)*(porcentaje/10)),0);
                    defensa.setVida(100+((nivel-1)*(porcentaje/10)));
                    defensa.setLabel(aereo);
                    aereo.setName("Aereo" + contadorAereo);

                    ThreadPersonaje tp = new ThreadPersonaje(defensa,this);
                    defensas.add(tp);
                    defensasAtacar.add(defensa);
                    pnlPrincipal.add(aereo);
                    aereo.setSize(40,40);
                    aereo.setLocation(x*40, y*40); 
                    tableroJuego.setValorEnCoordenadas(x, y, 2);
                    actualizarEspacios(espaciosDefensa.get(tipo));
                    posicionDefensas.put(new Point(x,y), defensa);
                    
                    aereo.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            mostrarInformacionDefensas(defensa); 
                        }
                    });
                    infoFinal += "Defensa tipo: " + defensa.getLabel().getName() + " Vida inicial: " 
                        + defensa.getVida()  + " Poder de golpe: " + defensa.getDaño() + "\n";
                } else if(tipo.equals("MedioAlcance")){
                    contadorMedio +=1;
                    JLabel medioalcance = new JLabel("MedioAlcance");
                    medioalcance.setIcon(new ImageIcon(path + "medio2.png"));
                    Defensa defensa = new Defensa("MedioAlcance", 2, 3+((nivel-1)*(porcentaje/10)),0);
                    defensa.setVida(100+((nivel-1)*(porcentaje/10)));
                    defensa.setLabel(medioalcance);
                    medioalcance.setName("Medio Alcance" + contadorMedio);

                    ThreadPersonaje tp = new ThreadPersonaje(defensa,this);
                    defensas.add(tp);
                    defensasAtacar.add(defensa);
                    pnlPrincipal.add(medioalcance);
                    medioalcance.setSize(40,40);
                    medioalcance.setLocation(x*40, y*40); 
                    tableroJuego.setValorEnCoordenadas(x, y, 2);
                    actualizarEspacios(espaciosDefensa.get(tipo));
                    posicionDefensas.put(new Point(x,y), defensa);
                    
                    medioalcance.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            mostrarInformacionDefensas(defensa); 
                        }
                    });
                    infoFinal += "Defensa tipo: " + defensa.getLabel().getName() + " Vida inicial: " 
                        + defensa.getVida()  + " Poder de golpe: " + defensa.getDaño() + "\n";
                } else if(tipo.equals("Bloque")){
                    JLabel bloque = new JLabel("Bloque");
                    bloque.setIcon(new ImageIcon(path + "nuez.png"));
                    Defensa defensa = new Defensa("Bloque", 1, 0*((nivel-1)*(porcentaje/10)),0);
                    defensa.setVida(150+((nivel-1)*(porcentaje/10)));
                    defensa.setLabel(bloque);

                    ThreadPersonaje tp = new ThreadPersonaje(defensa,this);
                    defensas.add(tp);
                    defensasAtacar.add(defensa);
                    pnlPrincipal.add(bloque);
                    bloque.setSize(40,40);
                    bloque.setLocation(x*40, y*40); 
                    tableroJuego.setValorEnCoordenadas(x, y, 2);
                    actualizarEspacios(espaciosDefensa.get(tipo));
                    posicionDefensas.put(new Point(x,y), defensa);
                    
                    bloque.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            mostrarInformacionDefensas(defensa); 
                        }
                    });
                    infoFinal += "Defensa tipo: " + defensa.getLabel().getName() + " Vida inicial: " 
                        + defensa.getVida()  + " Poder de golpe: " + defensa.getDaño() + "\n";
                } else if(tipo.equals("Impacto")){
                    contadorImpacto+=1;
                    JLabel impacto = new JLabel("Impacto");
                    impacto.setIcon(new ImageIcon(path + "mina.png"));
                    Defensa defensa = new Defensa("Impacto", 4, 7+((nivel-1)*(porcentaje/10)),0);
                    defensa.setVida(100+((nivel-1)*(porcentaje/10)));
                    defensa.setLabel(impacto);
                    impacto.setName("Impacto" + contadorImpacto);
                    ThreadPersonaje tp = new ThreadPersonaje(defensa,this);
                    defensas.add(tp);
                    defensasAtacar.add(defensa);
                    pnlPrincipal.add(impacto);
                    impacto.setSize(40,40);
                    impacto.setLocation(x*40, y*40);  
                    tableroJuego.setValorEnCoordenadas(x, y, 2);
                    actualizarEspacios(espaciosDefensa.get(tipo));
                    posicionDefensas.put(new Point(x,y), defensa);
                    impacto.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            mostrarInformacionDefensas(defensa); 
                        }
                    });
                    infoFinal += "Defensa tipo: " + defensa.getLabel().getName() + " Vida inicial: " 
                        + defensa.getVida()  + " Poder de golpe: " + defensa.getDaño() + "\n";
                } else if(tipo.equals("Multiple")){
                    contadorMultiple += 1;
                    JLabel multiple = new JLabel("Multiple");
                    multiple.setIcon(new ImageIcon(path + "multiple.png"));
                    Defensa defensa = new Defensa("Multiple", 5, 10+((nivel-1)*(porcentaje/10)),0);
                    defensa.setVida(100+((nivel-1)*(porcentaje/10)));
                    defensa.setLabel(multiple);
                    multiple.setName("Multiple" + contadorMultiple);

                    ThreadPersonaje tp = new ThreadPersonaje(defensa,this);
                    defensas.add(tp);
                    defensasAtacar.add(defensa);
                    pnlPrincipal.add(multiple);
                    multiple.setSize(40,40);
                    multiple.setLocation(x*40, y*40);  
                    tableroJuego.setValorEnCoordenadas(x, y, 2);
                    actualizarEspacios(espaciosDefensa.get(tipo));
                    posicionDefensas.put(new Point(x,y), defensa);
                    
                    multiple.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            mostrarInformacionDefensas(defensa); 
                        }
                    });
                    infoFinal += "Defensa tipo: " + defensa.getLabel().getName() + " Vida inicial: " 
                        + defensa.getVida()  + " Poder de golpe: " + defensa.getDaño() + "\n";
                }
            }
        } else{
            System.out.println("Error");
        }
       
    }
    public void mostrarInformacionDefensas(Defensa defensa){
        String informacion = "Defensa tipo: " + defensa.getNombre() + "\n" 
                + "Vida: "+defensa.getVida() + "\n" + "Golpes: " + defensa.getGolpes() + " de: " 
                + defensa.getDaño() + " de daño";
        JOptionPane.showMessageDialog(pnlPrincipal, informacion);
    }
    
    private void actualizarEspacios(int espacios){
        espaciosUtilizados += espacios;
    }
    
    private void detenerThreads(){
        for (int i = 0; i < zombies.size(); i++) {
            zombies.get(i).stopThread();
        }
        for (int i = 0; i < defensas.size(); i++) {
            defensas.get(i).stopThread();
        }
    }
    
    public void ataqueZombieMedio(int x, int y, int xDefensa, int yDefensa, Zombie zombie){
        String path = "C:\\Users\\User\\Documents\\Universidad\\II Semestre 2023"
                + "\\Introclases23II\\Arena202301\\src\\main\\java\\com\\mycompany\\arena202301\\img\\";
        Point coordenadas = new Point(xDefensa,yDefensa);
        Defensa defensa = posicionDefensas.get(coordenadas);
        int distanciaX = Math.abs(x - xDefensa);
        int distanciaY = Math.abs(y-yDefensa);
        int horizontal = 0;
        int vertical = 0;
        
        if (distanciaX <= 3 && distanciaY <= 3){
            if (xDefensa - x > 0){
                horizontal = 1;
            } else{
               horizontal = -1;
            }
            if (yDefensa - y > 0){
                vertical = 1;
            }       
            else{
                vertical = -1;
            }
            Bala bala = new Bala(path,x*40, y*40, vertical, horizontal);
            pnlPrincipal.add(bala);
            pnlPrincipal.setComponentZOrder(bala, 0);
            pnlPrincipal.revalidate();
            pnlPrincipal.repaint();
            
            Timer timer = new Timer(100, e -> {
                bala.mover();
                pnlPrincipal.repaint();
                if (bala.getX() == xDefensa && bala.getY() == yDefensa) {
                    pnlPrincipal.remove(bala);
                    pnlPrincipal.revalidate();
                    pnlPrincipal.repaint();
                    ataqueZombieMedio(x, y, xDefensa, yDefensa, zombie);
                }   
            });
            timer.setRepeats(true);
            timer.start();
            
            
        }
    }
   
    public void atacarDefensa(int x, int y, Zombie zombie){
        String path = "C:\\Users\\User\\Documents\\Universidad\\II Semestre 2023"
                + "\\Introclases23II\\Arena202301\\src\\main\\java\\com\\mycompany\\arena202301\\img\\";
        Point coordenadas = new Point(x,y);
        Defensa defensa = posicionDefensas.get(coordenadas);
        if(defensa.getNombre().equals("Aereo")){
            if(zombie.getNombre().equals("Volador")){
                JLabel labelGolpe = new labelGolpes(path + "hitZombie.png", x*40, y*40);
                pnlPrincipal.add(labelGolpe);
                pnlPrincipal.setComponentZOrder(labelGolpe, 0);
                pnlPrincipal.revalidate();
                pnlPrincipal.repaint();
                defensa.setVida(defensa.getVida()-zombie.getDaño());
                Timer timer = new Timer(800, e -> {
                    pnlPrincipal.remove(labelGolpe);
                    pnlPrincipal.revalidate();
                    pnlPrincipal.repaint();
                });
                timer.setRepeats(false);
                timer.start();
                infoFinal += "Zombie tipo: " + zombie.getLabel().getName() + " atacó a: " 
                            + defensa.getLabel().getName() + "\n";
            }
        }else{
            if (zombie.getNombre().equals("Contacto")){
                JLabel labelGolpe = new labelGolpes(path + "hitZombie.png", x*40, y*40);
                pnlPrincipal.add(labelGolpe);
                pnlPrincipal.setComponentZOrder(labelGolpe, 0);
                pnlPrincipal.revalidate();
                pnlPrincipal.repaint();
                defensa.setVida(defensa.getVida()-zombie.getDaño());
                Timer timer = new Timer(800, e -> {
                    pnlPrincipal.remove(labelGolpe);
                    pnlPrincipal.revalidate();
                    pnlPrincipal.repaint();
                });
                timer.setRepeats(false);
                timer.start();
                infoFinal += "Zombie tipo: " + zombie.getLabel().getName() + " atacó a: " 
                            + defensa.getLabel().getName() + "\n";
            }else if(zombie.getNombre().equals("medioalcance")){
                JLabel labelGolpe = new labelGolpes(path + "bala.png", x*40, y*40);
                pnlPrincipal.add(labelGolpe);
                pnlPrincipal.setComponentZOrder(labelGolpe, 0);
                pnlPrincipal.revalidate();
                pnlPrincipal.repaint();
                defensa.setVida(defensa.getVida()-zombie.getDaño());
                Timer timer = new Timer(800, e -> {
                    pnlPrincipal.remove(labelGolpe);
                    pnlPrincipal.revalidate();
                    pnlPrincipal.repaint();
                });
                timer.setRepeats(false);
                timer.start();
                infoFinal += "Zombie tipo: " + zombie.getLabel().getName() + " atacó a: " 
                            + defensa.getLabel().getName() + "\n";
            }else if(zombie.getNombre().equals("Volador")){
                JLabel labelGolpe = new labelGolpes(path + "hitZombie.png", x*40, y*40);
                pnlPrincipal.add(labelGolpe);
                pnlPrincipal.setComponentZOrder(labelGolpe, 0);
                pnlPrincipal.revalidate();
                pnlPrincipal.repaint();
                defensa.setVida(defensa.getVida()-zombie.getDaño());
                Timer timer = new Timer(800, e -> {
                    pnlPrincipal.remove(labelGolpe);
                    pnlPrincipal.revalidate();
                    pnlPrincipal.repaint();
                });
                timer.setRepeats(false);
                timer.start();
                infoFinal += "Zombie tipo: " + zombie.getLabel().getName() + " atacó a: " 
                            + defensa.getLabel().getName() + "\n";
            } else if(zombie.getNombre().equals("Choque")){
                JLabel labelGolpe = new labelGolpes(path + "hitZombie.png", x*40, y*40);
                pnlPrincipal.add(labelGolpe);
                pnlPrincipal.setComponentZOrder(labelGolpe, 0);
                pnlPrincipal.revalidate();
                pnlPrincipal.repaint();
                defensa.setVida(defensa.getVida()-zombie.getDaño());
                Timer timer = new Timer(800, e -> {
                    pnlPrincipal.remove(labelGolpe);
                    pnlPrincipal.revalidate();
                    pnlPrincipal.repaint();
                });
                timer.setRepeats(false);
                timer.start();
                infoFinal += "Zombie tipo: " + zombie.getLabel().getName() + " atacó a: " 
                            + defensa.getNombre() + "\n";
            }
            
        }
        
        zombie.setGolpes(zombie.getGolpes()+1);
        if (defensa.getVida()<= 0){
            JLabel label = defensa.getLabel();
            pnlPrincipal.remove(label);
            tableroJuego.setValorEnCoordenadas(x, y, 0);
            defensas.remove(defensa);
            pnlPrincipal.revalidate();
            pnlPrincipal.repaint();
            infoFinal += "Zombie tipo: " + zombie.getLabel().getName() + " mató a: " 
                        + defensa.getLabel().getName() + "\n";
        }
    }
    
    public void atacarFuente(int x, int y, Zombie zombie){
        String path = "C:\\Users\\User\\Documents\\Universidad\\II Semestre 2023"
                + "\\Introclases23II\\Arena202301\\src\\main\\java\\com\\mycompany\\arena202301\\img\\";
        if (fuente.getVida()<= 0){
            infoFinal += "Zombie tipo: " + zombie.getLabel().getName() + " destruyó la fuente"+ "\n";
            tableroJuego.setValorEnCoordenadas(x, y, 0);
            perdedor();
        }
        
        fuente.setVida(fuente.getVida()-zombie.getDaño());
        JLabel labelGolpe = new labelGolpes(path + "hitZombie.png", x*40, y*40);
                pnlPrincipal.add(labelGolpe);
                pnlPrincipal.setComponentZOrder(labelGolpe, 0);
                pnlPrincipal.revalidate();
                pnlPrincipal.repaint();
                fuente.setVida(fuente.getVida()-zombie.getDaño());
                Timer timer = new Timer(500, e -> {
                    pnlPrincipal.remove(labelGolpe);
                    pnlPrincipal.revalidate();
                    pnlPrincipal.repaint();
                });
                timer.setRepeats(false);
                timer.start();
        zombie.setGolpes(zombie.getGolpes()+1);
        infoFinal += "Zombie tipo: " + zombie.getLabel().getName() + " atacó la " 
                        + "fuente" + "\n";
    }
    
    private void perdedor(){
        detenerThreads();
        defensas.clear();
        zombies.clear();
        JLabel label = fuente.getLabel();
        pnlPrincipal.remove(label);
        pnlPrincipal.removeAll();
        JTextArea texto = new JTextArea(50,50);
        texto.setEditable(false);
        texto.setText(infoFinal);
        JScrollPane scrollPane = new JScrollPane(texto, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        JOptionPane.showMessageDialog(pnlPrincipal, scrollPane);
        pnlPrincipal.revalidate();
        pnlPrincipal.repaint();
        perdedor = true;
        
    }
    
    /*public void getPosicionDefensa() throws InterruptedException {
        for (int i = 0; i < defensas.size(); i++) {
            Defensa defensa = (Defensa) defensasAtacar.get(i);
            int xDefensa = defensa.getLabel().getY();
            int yDefensa = defensa.getLabel().getX();
            atacarZombie(xDefensa/40,yDefensa/40, defensa);
        }
        
    }*/
    
    public void atacarZombie(JLabel label) throws InterruptedException {
        String path = "C:\\Users\\User\\Documents\\Universidad\\II Semestre 2023"
                + "\\Introclases23II\\Arena202301\\src\\main\\java\\com\\mycompany\\arena202301\\img\\";
        int x = (label.getX()/40);
        int y = (label.getY()/40);
        
        Point coordenadas = null;
        Zombie zombie = null;
        Defensa defensa = posicionDefensas.get(new Point(x,y));
        
        if (tableroJuego.getTablero()[x+1][y] == 1){ 
            coordenadas = new Point(x+1,y);
            zombie = posicionZombies.get(coordenadas);
            if (zombie.getNombre().equals("Volador")){
                if(defensa.getNombre().equals("Aereo")){
                    JLabel labelGolpe = new labelGolpes(path + "ataqueMultiple.png" , (x+1)*40, y*40);
                    pnlPrincipal.add(labelGolpe);
                    pnlPrincipal.setComponentZOrder(labelGolpe, 0);
                    pnlPrincipal.revalidate();
                    pnlPrincipal.repaint();
                    zombie.setVida(zombie.getVida()-defensa.getDaño());
                    muerteZombie(zombie, defensa, x+1, y);
                    Timer timer = new Timer(500, e -> {
                        pnlPrincipal.remove(labelGolpe);
                        pnlPrincipal.revalidate();
                        pnlPrincipal.repaint();
                    });
                    timer.setRepeats(false);
                    timer.start();
                    defensa.setGolpes(zombie.getGolpes()+1);
                    infoFinal += "Defensa tipo: " + defensa.getLabel().getName() + " atacó a: " 
                            + zombie.getLabel().getName() + "\n";
                }
                
            }else{
                if (defensa.getNombre().equals("Contacto")){
                    JLabel labelGolpe = new labelGolpes(path + "hitPlanta.png" , (x+1)*40, y*40);
                    pnlPrincipal.add(labelGolpe);
                    pnlPrincipal.setComponentZOrder(labelGolpe, 0);
                    pnlPrincipal.revalidate();
                    pnlPrincipal.repaint();
                    zombie.setVida(zombie.getVida()-defensa.getDaño());
                    muerteZombie(zombie, defensa, x+1, y);
                    Timer timer = new Timer(500, e -> {
                        pnlPrincipal.remove(labelGolpe);
                        pnlPrincipal.revalidate();
                        pnlPrincipal.repaint();
                    });
                    timer.setRepeats(false);
                    timer.start();
                    defensa.setGolpes(zombie.getGolpes()+1);
                    infoFinal += "Defensa tipo: " + defensa.getLabel().getName() + " atacó a: " 
                            + zombie.getLabel().getName() + "\n";

                } else if (defensa.getNombre().equals("MedioAlcance")){
                    JLabel labelGolpe = new labelGolpes(path + "ataqueMedio.png" , (x+1)*40, y*40);
                    pnlPrincipal.add(labelGolpe);
                    pnlPrincipal.setComponentZOrder(labelGolpe, 0);
                    pnlPrincipal.revalidate();
                    pnlPrincipal.repaint();
                    zombie.setVida(zombie.getVida()-defensa.getDaño());
                    muerteZombie(zombie, defensa, x+1, y);
                    Timer timer = new Timer(500, e -> {
                        pnlPrincipal.remove(labelGolpe);
                        pnlPrincipal.revalidate();
                        pnlPrincipal.repaint();
                    });
                    timer.setRepeats(false);
                    timer.start();
                    defensa.setGolpes(zombie.getGolpes()+1);
                    infoFinal += "Defensa tipo: " + defensa.getLabel().getName() + " atacó a: " 
                            + zombie.getLabel().getName() + "\n";

                }else if (defensa.getNombre().equals("Impacto") 
                        || defensa.getNombre().equals("Multiple") ||defensa.getNombre().equals("Aereo")){
                    JLabel labelGolpe = new labelGolpes(path + "ataqueMultiple.png" , (x+1)*40, y*40);
                    pnlPrincipal.add(labelGolpe);
                    pnlPrincipal.setComponentZOrder(labelGolpe, 0);
                    pnlPrincipal.revalidate();
                    pnlPrincipal.repaint();
                    zombie.setVida(zombie.getVida()-defensa.getDaño());
                    muerteZombie(zombie, defensa, x+1, y);
                    Timer timer = new Timer(500, e -> {
                        pnlPrincipal.remove(labelGolpe);
                        pnlPrincipal.revalidate();
                        pnlPrincipal.repaint();
                    });
                    timer.setRepeats(false);
                    timer.start();
                    defensa.setGolpes(zombie.getGolpes()+1);
                    infoFinal += "Defensa tipo: " + defensa.getLabel().getName() + " atacó a: " 
                            + zombie.getLabel().getName() + "\n";

                }
            }
           
        }else if(tableroJuego.getTablero()[x][y+1] == 1){ 
            coordenadas = new Point(x,y+1);
            zombie = posicionZombies.get(coordenadas);
            if (zombie.getNombre().equals("Volador")){
                if(defensa.getNombre().equals("Aereo")){
                    JLabel labelGolpe = new labelGolpes(path + "ataqueMultiple.png" , x*40, (y+1)*40);
                    pnlPrincipal.add(labelGolpe);
                    pnlPrincipal.setComponentZOrder(labelGolpe, 0);
                    pnlPrincipal.revalidate();
                    pnlPrincipal.repaint();
                    zombie.setVida(zombie.getVida()-defensa.getDaño());
                    muerteZombie(zombie, defensa, x, y+1);
                    Timer timer = new Timer(500, e -> {
                        pnlPrincipal.remove(labelGolpe);
                        pnlPrincipal.revalidate();
                        pnlPrincipal.repaint();
                    });
                    timer.setRepeats(false);
                    timer.start();
                    defensa.setGolpes(zombie.getGolpes()+1);
                    infoFinal += "Defensa tipo: " + defensa.getLabel().getName() + " atacó a: " 
                            + zombie.getLabel().getName() + "\n";
                    
                }
                
            }else{
                if (defensa.getNombre().equals("Contacto")){
                    JLabel labelGolpe = new labelGolpes(path + "hitPlanta.png" , x*40, (y+1)*40);
                    pnlPrincipal.add(labelGolpe);
                    pnlPrincipal.setComponentZOrder(labelGolpe, 0);
                    pnlPrincipal.revalidate();
                    pnlPrincipal.repaint();
                    zombie.setVida(zombie.getVida()-defensa.getDaño());
                    muerteZombie(zombie, defensa, x, y+1);
                    Timer timer = new Timer(500, e -> {
                        pnlPrincipal.remove(labelGolpe);
                        pnlPrincipal.revalidate();
                        pnlPrincipal.repaint();
                    });
                    timer.setRepeats(false);
                    timer.start();
                    defensa.setGolpes(zombie.getGolpes()+1);
                    infoFinal += "Defensa tipo: " + defensa.getLabel().getName() + " atacó a: " 
                            + zombie.getLabel().getName() + "\n";
      
                } else if (defensa.getNombre().equals("MedioAlcance")){
                    JLabel labelGolpe = new labelGolpes(path + "ataqueMedio.png" , x*40, (y+1)*40);
                    pnlPrincipal.add(labelGolpe);
                    pnlPrincipal.setComponentZOrder(labelGolpe, 0);
                    pnlPrincipal.revalidate();
                    pnlPrincipal.repaint();
                    zombie.setVida(zombie.getVida()-defensa.getDaño());
                    muerteZombie(zombie, defensa, x, y+1);
                    Timer timer = new Timer(500, e -> {
                        pnlPrincipal.remove(labelGolpe);
                        pnlPrincipal.revalidate();
                        pnlPrincipal.repaint();
                    });
                    timer.setRepeats(false);
                    timer.start();
                    defensa.setGolpes(zombie.getGolpes()+1);
                    infoFinal += "Defensa tipo: " + defensa.getLabel().getName() + " atacó a: " 
                            + zombie.getLabel().getName() + "\n";

                }else if (defensa.getNombre().equals("Impacto") 
                        || defensa.getNombre().equals("Multiple") ||defensa.getNombre().equals("Aereo")){
                    JLabel labelGolpe = new labelGolpes(path + "ataqueMultiple.png" , x*40, (y+1)*40);
                    pnlPrincipal.add(labelGolpe);
                    pnlPrincipal.setComponentZOrder(labelGolpe, 0);
                    pnlPrincipal.revalidate();
                    pnlPrincipal.repaint();
                    zombie.setVida(zombie.getVida()-defensa.getDaño());
                    muerteZombie(zombie, defensa, x, y+1);
                    Timer timer = new Timer(500, e -> {
                        pnlPrincipal.remove(labelGolpe);
                        pnlPrincipal.revalidate();
                        pnlPrincipal.repaint();
                    });
                    timer.setRepeats(false);
                    timer.start();
                    defensa.setGolpes(zombie.getGolpes()+1);
                    infoFinal += "Defensa tipo: " + defensa.getLabel().getName() + " atacó a: " 
                            + zombie.getLabel().getName() + "\n";
                }
            }
        }else if(tableroJuego.getTablero()[x-1][y] == 1){ 
            coordenadas = new Point(x-1,y);
            zombie = posicionZombies.get(coordenadas);
            if (zombie.getNombre().equals("Volador")){
                if(defensa.getNombre().equals("Aereo")){
                    JLabel labelGolpe = new labelGolpes(path + "ataqueMultiple.png" , (x-1)*40, y*40);
                    pnlPrincipal.add(labelGolpe);
                    pnlPrincipal.setComponentZOrder(labelGolpe, 0);
                    pnlPrincipal.revalidate();
                    pnlPrincipal.repaint();
                    zombie.setVida(zombie.getVida()-defensa.getDaño());
                    muerteZombie(zombie, defensa, x-1, y);
                    Timer timer = new Timer(500, e -> {
                        pnlPrincipal.remove(labelGolpe);
                        pnlPrincipal.revalidate();
                        pnlPrincipal.repaint();
                    });
                    timer.setRepeats(false);
                    timer.start();
                    defensa.setGolpes(zombie.getGolpes()+1);
                    infoFinal += "Defensa tipo: " + defensa.getLabel().getName() + " atacó a: " 
                            + zombie.getLabel().getName() + "\n";
                }else{
                    System.out.println("No puede atacar a un volador");
                }
                
            }else{
                if (defensa.getNombre().equals("Contacto")){
                    JLabel labelGolpe = new labelGolpes(path + "hitPlanta.png" , (x-1)*40, y*40);
                    pnlPrincipal.add(labelGolpe);
                    pnlPrincipal.setComponentZOrder(labelGolpe, 0);
                    pnlPrincipal.revalidate();
                    pnlPrincipal.repaint();
                    zombie.setVida(zombie.getVida()-defensa.getDaño());
                    muerteZombie(zombie, defensa, x-1, y);
                    Timer timer = new Timer(500, e -> {
                        pnlPrincipal.remove(labelGolpe);
                        pnlPrincipal.revalidate();
                        pnlPrincipal.repaint();
                    });
                    timer.setRepeats(false);
                    timer.start();
                    defensa.setGolpes(zombie.getGolpes()+1);
                    infoFinal += "Defensa tipo: " + defensa.getLabel().getName() + " atacó a: " 
                            + zombie.getLabel().getName() + "\n";

                } else if (defensa.getNombre().equals("MedioAlcance")){
                    JLabel labelGolpe = new labelGolpes(path + "ataqueMedio.png" , (x-1)*40, y*40);
                    pnlPrincipal.add(labelGolpe);
                    pnlPrincipal.setComponentZOrder(labelGolpe, 0);
                    pnlPrincipal.revalidate();
                    pnlPrincipal.repaint();
                    zombie.setVida(zombie.getVida()-defensa.getDaño());
                    muerteZombie(zombie, defensa, x-1, y);
                    Timer timer = new Timer(500, e -> {
                        pnlPrincipal.remove(labelGolpe);
                        pnlPrincipal.revalidate();
                        pnlPrincipal.repaint();
                    });
                    timer.setRepeats(false);
                    timer.start();
                    defensa.setGolpes(zombie.getGolpes()+1);
                    infoFinal += "Defensa tipo: " + defensa.getLabel().getName() + " atacó a: " 
                            + zombie.getLabel().getName() + "\n";

                }else if (defensa.getNombre().equals("Impacto") 
                        || defensa.getNombre().equals("Multiple") ||defensa.getNombre().equals("Aereo")){
                    JLabel labelGolpe = new labelGolpes(path + "ataqueMultiple.png" , (x-1)*40, y*40);
                    pnlPrincipal.add(labelGolpe);
                    pnlPrincipal.setComponentZOrder(labelGolpe, 0);
                    pnlPrincipal.revalidate();
                    pnlPrincipal.repaint();
                    zombie.setVida(zombie.getVida()-defensa.getDaño());
                    muerteZombie(zombie, defensa, x-1, y);
                    Timer timer = new Timer(500, e -> {
                        pnlPrincipal.remove(labelGolpe);
                        pnlPrincipal.revalidate();
                        pnlPrincipal.repaint();
                    });
                    timer.setRepeats(false);
                    timer.start();
                    defensa.setGolpes(zombie.getGolpes()+1);
                    infoFinal += "Defensa tipo: " + defensa.getLabel().getName() + " atacó a: " 
                            + zombie.getLabel().getName() + "\n";

                }
            }
        }else if(tableroJuego.getTablero()[x][y-1] == 1){ 
            coordenadas = new Point(x,y-1);
            zombie = posicionZombies.get(coordenadas);
            if (zombie.getNombre().equals("Volador")){
                if(defensa.getNombre().equals("Aereo")){
                    JLabel labelGolpe = new labelGolpes(path + "ataqueMultiple.png" , x*40, (y-1)*40);
                    pnlPrincipal.add(labelGolpe);
                    pnlPrincipal.setComponentZOrder(labelGolpe, 0);
                    pnlPrincipal.revalidate();
                    pnlPrincipal.repaint();
                    zombie.setVida(zombie.getVida()-defensa.getDaño());
                    muerteZombie(zombie, defensa, x, y-1);
                    Timer timer = new Timer(500, e -> {
                        pnlPrincipal.remove(labelGolpe);
                        pnlPrincipal.revalidate();
                        pnlPrincipal.repaint();
                    });
                    timer.setRepeats(false);
                    timer.start();
                    defensa.setGolpes(zombie.getGolpes()+1);
                    infoFinal += "Defensa tipo: " + defensa.getLabel().getName() + " atacó a: " 
                            + zombie.getLabel().getName() + "\n";
                }
                
            }else{
                if (defensa.getNombre().equals("Contacto")){
                    JLabel labelGolpe = new labelGolpes(path + "hitPlanta.png" , x*40, (y-1)*40);
                    pnlPrincipal.add(labelGolpe);
                    pnlPrincipal.setComponentZOrder(labelGolpe, 0);
                    pnlPrincipal.revalidate();
                    pnlPrincipal.repaint();
                    zombie.setVida(zombie.getVida()-defensa.getDaño());
                    muerteZombie(zombie, defensa, x, y-1);
                    Timer timer = new Timer(500, e -> {
                        pnlPrincipal.remove(labelGolpe);
                        pnlPrincipal.revalidate();
                        pnlPrincipal.repaint();
                    });
                    timer.setRepeats(false);
                    timer.start();
                    defensa.setGolpes(zombie.getGolpes()+1);
                    infoFinal += "Defensa tipo: " + defensa.getLabel().getName() + " atacó a: " 
                            + zombie.getLabel().getName() + "\n";

                } else if (defensa.getNombre().equals("MedioAlcance")){
                    JLabel labelGolpe = new labelGolpes(path + "ataqueMedio.png" , x*40, (y-1)*40);
                    pnlPrincipal.add(labelGolpe);
                    pnlPrincipal.setComponentZOrder(labelGolpe, 0);
                    pnlPrincipal.revalidate();
                    pnlPrincipal.repaint();
                    zombie.setVida(zombie.getVida()-defensa.getDaño());
                    muerteZombie(zombie, defensa, x, y-1);
                    Timer timer = new Timer(500, e -> {
                        pnlPrincipal.remove(labelGolpe);
                        pnlPrincipal.revalidate();
                        pnlPrincipal.repaint();
                    });
                    timer.setRepeats(false);
                    timer.start();
                    defensa.setGolpes(zombie.getGolpes()+1);
                    infoFinal += "Defensa tipo: " + defensa.getLabel().getName() + " atacó a: " 
                            + zombie.getLabel().getName() + "\n";

                }else if (defensa.getNombre().equals("Impacto") 
                        || defensa.getNombre().equals("Multiple") ||defensa.getNombre().equals("Aereo")){
                    JLabel labelGolpe = new labelGolpes(path + "ataqueMultiple.png" , x*40, (y-1)*40);
                    pnlPrincipal.add(labelGolpe);
                    pnlPrincipal.setComponentZOrder(labelGolpe, 0);
                    pnlPrincipal.revalidate();
                    pnlPrincipal.repaint();
                    zombie.setVida(zombie.getVida()-defensa.getDaño());
                    muerteZombie(zombie, defensa, x, y-1);
                    Timer timer = new Timer(500, e -> {
                        pnlPrincipal.remove(labelGolpe);
                        pnlPrincipal.revalidate();
                        pnlPrincipal.repaint();
                    });
                    timer.setRepeats(false);
                    timer.start();
                    defensa.setGolpes(zombie.getGolpes()+1);
                    infoFinal += "Defensa tipo: " + defensa.getLabel().getName() + " atacó a: " 
                            + zombie.getLabel().getName() + "\n";

                }
            }
            
        }
        

    }
    public void muerteZombie(Zombie zombie, Defensa defensa, int x, int y){
        if(zombie.getVida() <= 0){
            JLabel labelMuerto = zombie.getLabel();
            pnlPrincipal.remove(labelMuerto);
            tableroJuego.setValorEnCoordenadas(x, y, 0);
            pnlPrincipal.revalidate();
            pnlPrincipal.repaint();
            zombies.remove(zombie);
            infoFinal += "Defensa tipo: " + defensa.getLabel().getName() + " mató a: " 
                        + zombie.getLabel().getName() + "\n";
        
        }
    }
    
    
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnIniciar = new javax.swing.JButton();
        txtNombre = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        nombreCargar = new javax.swing.JTextField();
        pnlPrincipal = new javax.swing.JPanel();
        ejercito = new javax.swing.JPanel();
        CargarNombre = new javax.swing.JLabel();
        errorNombre = new javax.swing.JPanel();
        btnAgregar = new javax.swing.JButton();
        guardarNombre = new javax.swing.JLabel();
        reiniciar = new javax.swing.JButton();
        siguienteNivel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnIniciar.setText("Iniciar");
        btnIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarActionPerformed(evt);
            }
        });

        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });

        jLabel2.setText("Ejercito #1");

        jButton1.setText("Cargar");
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

        CargarNombre.setText("Nombre:");

        javax.swing.GroupLayout errorNombreLayout = new javax.swing.GroupLayout(errorNombre);
        errorNombre.setLayout(errorNombreLayout);
        errorNombreLayout.setHorizontalGroup(
            errorNombreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 215, Short.MAX_VALUE)
        );
        errorNombreLayout.setVerticalGroup(
            errorNombreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 52, Short.MAX_VALUE)
        );

        btnAgregar.setText("Guardar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        guardarNombre.setText("Nombre:");

        reiniciar.setText("Reiniciar");
        reiniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reiniciarActionPerformed(evt);
            }
        });

        siguienteNivel.setText("Siguiente Nivel");
        siguienteNivel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siguienteNivelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ejercito, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(CargarNombre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(nombreCargar, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnAgregar, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnIniciar, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(guardarNombre)
                                .addGap(15, 15, 15)
                                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(siguienteNivel, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(reiniciar))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(errorNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnIniciar)
                .addGap(64, 64, 64)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CargarNombre)
                    .addComponent(nombreCargar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(errorNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAgregar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(guardarNombre))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 93, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ejercito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(reiniciar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(siguienteNivel)
                .addGap(328, 328, 328))
            .addComponent(pnlPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarActionPerformed
        pnlPrincipal.removeMouseListener(pnlPrincipal.getMouseListeners()[0]);
        pnlPrincipal.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (tipoDefensa != null) {
                    int x = e.getX() / 40;
                    int y = e.getY() / 40;
                    colocarDefensa(tipoDefensa, x, y,cantEjercito+(nivel*5), espaciosUtilizados, nivel);
                }
            }
        });
        for (int i = 0; i < zombies.size(); i++) {
            ThreadPersonaje get = zombies.get(i);
            get.start();
        }
        for (int i = 0; i < defensas.size(); i++) {
            ThreadPersonaje get = defensas.get(i);
            get.start();
        }
        
    }//GEN-LAST:event_btnIniciarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(nombreCargar.getText().isEmpty()){
            nombreCargar.setText("Ponga su nombre");
        } else{
            if (nivel == -1){
                nombreCargar.setText("No se encontro el nombre");
            } else{
                nivel = FileManager.leerInformacion(nombreCargar.getText());
                pnlPrincipal.removeAll();  
                pnlPrincipal.revalidate();
                pnlPrincipal.repaint();
                detenerThreads();
                zombies.clear();
                espaciosUtilizados = 0;
                iniciarJuego(nivel);
            }
        }
        /*nivel +=1;
        pnlPrincipal.removeAll();  
        pnlPrincipal.revalidate();
        pnlPrincipal.repaint();
        
        detenerThreadsZombies();
        zombies.clear();
        iniciarJuego(nivel);
        
        System.out.println(nivel);
        */   
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed

        if (txtNombre.getText().isEmpty()){
            txtNombre.setText("Ponga su nombre");
        } else{
            if (FileManager.leerInformacion(txtNombre.getText()) == -1){
                System.out.println(FileManager.leerInformacion(txtNombre.getText()));
                FileManager.escribirInformacion(txtNombre.getText(), nivel);
            } else{
                txtNombre.setText("Ese nombre ya se ha guardado");
            }
            
        }
        
        /*if (txtNombre.getText().isEmpty()){
            txtNombre.setText("Ponga su nombre");
        } else{
            FileManager.escribirInformacion(txtNombre.getText(), nivel);
            for (int i = 0; i < zombies.size(); i++) {
                ThreadPersonaje get = zombies.get(i);
                get.start();
            }
        }*/
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
   
    }//GEN-LAST:event_txtNombreActionPerformed

    private void reiniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reiniciarActionPerformed
        if (perdedor == true){
            detenerThreads();
            espaciosUtilizados = 0;
            zombies.clear();
            defensas.clear();
            iniciarJuego(nivel);
            perdedor = false;
        }
    }//GEN-LAST:event_reiniciarActionPerformed

    private void siguienteNivelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siguienteNivelActionPerformed
        if (perdedor == true){
            nivel +=1;
            zombies.clear();
            defensas.clear();
            iniciarJuego(nivel);
            perdedor = false;
        }
    }//GEN-LAST:event_siguienteNivelActionPerformed

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
    private javax.swing.JLabel CargarNombre;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnIniciar;
    private javax.swing.JPanel ejercito;
    private javax.swing.JPanel errorNombre;
    private javax.swing.JLabel guardarNombre;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField nombreCargar;
    private javax.swing.JPanel pnlPrincipal;
    private javax.swing.JButton reiniciar;
    private javax.swing.JButton siguienteNivel;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
