/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vistas;

import java.awt.Color;
import java.awt.Image;
import javax.swing.ImageIcon;
import bd.Conexion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import Vistas.CreditPage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author alumnat
 */
public class MainPage extends javax.swing.JFrame {

    private final int idUser;
    private Conexion conexion;
    int creditos;

    /**
     * Creates new form MainPage
     */
    public MainPage(int idUser) {
        initComponents();
        System.out.println(idUser);
        this.setLocationRelativeTo(null); //Inicializa al centro de la pantalla

        ImageIcon icon = new ImageIcon(getClass().getResource("/imagenes/icono.png")); // Esto es para cambiar el icono de la app
        Image image = icon.getImage();
        setIconImage(image);
        this.idUser = idUser;

        String nombreUsuario = getNombreUsuarioPorId(idUser);
        jLabel20.setText(nombreUsuario);
        creditos = getCreditosPorId(idUser);
        jLabel20.setText(nombreUsuario);
        jLabel1.setText(String.valueOf(creditos));

        getReservas();
    }

    private MainPage() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    // Método para validar las credenciales en la base de datos
    private String getNombreUsuarioPorId(int id) {
        Conexion conexion = new Conexion();
        Connection connection = conexion.DatabaseConnection(); // Obtén la conexión
        String nombre = "";

        try {
            // Consulta SQL para verificar las credenciales
            String consulta = "SELECT * FROM usuarios WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(consulta);
            statement.setInt(1, id);

            ResultSet resultado = statement.executeQuery();
            // Si se encontraron resultados, las credenciales son válidas
            if (resultado.next()) {
                nombre = resultado.getString("Nombre");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nombre;
    }

    private int getCreditosPorId(int id) {
        Conexion conexion = new Conexion();
        Connection connection = conexion.DatabaseConnection(); // Obtén la conexión
        int creditos = 0;

        try {
            // Consulta SQL para obtener los créditos del usuario por su ID
            String consulta = "SELECT credito FROM usuarios WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(consulta);
            statement.setInt(1, id);

            ResultSet resultado = statement.executeQuery();
            // Si se encontraron resultados, obtenemos los créditos del usuario
            if (resultado.next()) {
                creditos = resultado.getInt("credito");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Recuerda cerrar la conexión y otros recursos relacionados con la base de datos
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return creditos;
    }

    // Método para validar las credenciales en la base de datos
    private void getReservas() {
        Conexion conexion = new Conexion();
        Connection connection = conexion.DatabaseConnection(); // Obtén la conexión

        try {
            String consulta = "SELECT * FROM reservas WHERE id_usuario IS NULL";
            PreparedStatement statement = connection.prepareStatement(consulta);

            ResultSet resultado = statement.executeQuery();

            int i = 0;
            while (resultado.next() && i < 4) {
                byte[] imagenBytes = resultado.getBytes("imagen");
                ImageIcon imagenIcon = new ImageIcon(imagenBytes);
                Image imagen = imagenIcon.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
                ImageIcon imagenEscalada = new ImageIcon(imagen);

                // Obtén otros datos de la fila
                String tipoEstancia = resultado.getString("tipo_estancia");
                String direccion = resultado.getString("direccion");
                int id_reserva = resultado.getInt("id_reserva");
                int coste_dia = resultado.getInt("coste_dia");
                int metros = resultado.getInt("metros");

                switch (i) {
                    case 0:
                        imagen0.setIcon(imagenEscalada);
                        tipoEstancia0.setText(tipoEstancia);
                        direccion0.setText(direccion);
                        metros0.setText(String.valueOf(metros));
                        coste0.setText(String.valueOf(coste_dia));
                        id_reserva0.setText(String.valueOf(id_reserva));
                        break;
                    case 1:
                        imagen1.setIcon(imagenEscalada);
                        tipoEstancia1.setText(tipoEstancia);
                        direccion1.setText(direccion);
                        metros1.setText(String.valueOf(metros));
                        coste1.setText(String.valueOf(coste_dia));
                        id_reserva1.setText(String.valueOf(id_reserva));
                        break;
                    case 2:
                        imagen2.setIcon(imagenEscalada);
                        tipoEstancia2.setText(tipoEstancia);
                        direccion2.setText(direccion);
                        metros2.setText(String.valueOf(metros));
                        coste2.setText(String.valueOf(coste_dia));
                        id_reserva2.setText(String.valueOf(id_reserva));
                        break;
                    case 3:
                        imagen3.setIcon(imagenEscalada);
                        tipoEstancia3.setText(tipoEstancia);
                        direccion3.setText(direccion);
                        metros3.setText(String.valueOf(metros));
                        coste3.setText(String.valueOf(coste_dia));
                        id_reserva3.setText(String.valueOf(id_reserva));
                        break;
                }
                i++;
            }

            // Ocultar los JLabel restantes si no hay suficientes registros
            switch (i) {
                case 3:
                    imagen3.setVisible(false);
                    tipoEstancia3.setVisible(false);
                    direccion3.setVisible(false);
                    metros3.setVisible(false);
                    coste3.setVisible(false);
                    id_reserva3.setVisible(false);
                    fechaEntrada3.setVisible(false);
                    fechaSalida3.setVisible(false);
                    reservar3.setVisible(false);
                    jScrollPane1.setSize(280, 670);
                    break;
                case 2:
                    imagen2.setVisible(false);
                    tipoEstancia2.setVisible(false);
                    direccion2.setVisible(false);
                    metros2.setVisible(false);
                    coste2.setVisible(false);
                    id_reserva2.setVisible(false);
                    fechaEntrada2.setVisible(false);
                    fechaSalida2.setVisible(false);
                    reservar2.setVisible(false);
                    jScrollPane1.setSize(280, 450);
                    break;
                case 1:
                    imagen1.setVisible(false);
                    tipoEstancia1.setVisible(false);
                    direccion1.setVisible(false);
                    metros1.setVisible(false);
                    coste1.setVisible(false);
                    id_reserva1.setVisible(false);
                    fechaEntrada1.setVisible(false);
                    fechaSalida1.setVisible(false);
                    reservar1.setVisible(false);
                    jScrollPane1.setSize(280, 220);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
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

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        imagen0 = new javax.swing.JLabel();
        tipoEstancia0 = new javax.swing.JLabel();
        direccion0 = new javax.swing.JLabel();
        metros0 = new javax.swing.JLabel();
        id_reserva0 = new javax.swing.JLabel();
        reservar0 = new javax.swing.JButton();
        imagen1 = new javax.swing.JLabel();
        tipoEstancia1 = new javax.swing.JLabel();
        direccion1 = new javax.swing.JLabel();
        metros1 = new javax.swing.JLabel();
        reservar1 = new javax.swing.JButton();
        coste1 = new javax.swing.JLabel();
        reservar2 = new javax.swing.JButton();
        coste2 = new javax.swing.JLabel();
        metros2 = new javax.swing.JLabel();
        direccion2 = new javax.swing.JLabel();
        tipoEstancia2 = new javax.swing.JLabel();
        imagen2 = new javax.swing.JLabel();
        metros3 = new javax.swing.JLabel();
        coste3 = new javax.swing.JLabel();
        tipoEstancia3 = new javax.swing.JLabel();
        imagen3 = new javax.swing.JLabel();
        reservar3 = new javax.swing.JButton();
        direccion3 = new javax.swing.JLabel();
        coste0 = new javax.swing.JLabel();
        id_reserva1 = new javax.swing.JLabel();
        id_reserva2 = new javax.swing.JLabel();
        id_reserva3 = new javax.swing.JLabel();
        fechaEntrada0 = new com.toedter.calendar.JDateChooser();
        fechaSalida0 = new com.toedter.calendar.JDateChooser();
        fechaEntrada1 = new com.toedter.calendar.JDateChooser();
        fechaSalida1 = new com.toedter.calendar.JDateChooser();
        fechaEntrada2 = new com.toedter.calendar.JDateChooser();
        fechaSalida2 = new com.toedter.calendar.JDateChooser();
        fechaEntrada3 = new com.toedter.calendar.JDateChooser();
        fechaSalida3 = new com.toedter.calendar.JDateChooser();
        jPanel4 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        btnReservas = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel3.add(imagen0, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, -1, -1));

        tipoEstancia0.setText("jLabel21");
        jPanel3.add(tipoEstancia0, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        direccion0.setText("jLabel2");
        jPanel3.add(direccion0, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, -1));

        metros0.setText("jLabel2");
        jPanel3.add(metros0, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, -1, -1));

        id_reserva0.setText("jLabel2");
        jPanel3.add(id_reserva0, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 150, -1, -1));

        reservar0.setText("Reservar");
        reservar0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reservar0ActionPerformed(evt);
            }
        });
        jPanel3.add(reservar0, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 180, -1, -1));
        jPanel3.add(imagen1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 250, -1, -1));

        tipoEstancia1.setText("jLabel21");
        jPanel3.add(tipoEstancia1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, -1, -1));

        direccion1.setText("jLabel2");
        jPanel3.add(direccion1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, -1, -1));

        metros1.setText("jLabel2");
        jPanel3.add(metros1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, -1, -1));

        reservar1.setText("Reservar");
        reservar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reservar1ActionPerformed(evt);
            }
        });
        jPanel3.add(reservar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 420, -1, -1));

        coste1.setText("jLabel2");
        jPanel3.add(coste1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 360, -1, -1));

        reservar2.setText("Reservar");
        reservar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reservar2ActionPerformed(evt);
            }
        });
        jPanel3.add(reservar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 630, -1, -1));

        coste2.setText("jLabel2");
        jPanel3.add(coste2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 580, -1, -1));

        metros2.setText("jLabel2");
        jPanel3.add(metros2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 580, -1, -1));

        direccion2.setText("jLabel2");
        jPanel3.add(direccion2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 550, -1, -1));

        tipoEstancia2.setText("jLabel21");
        jPanel3.add(tipoEstancia2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 520, -1, -1));
        jPanel3.add(imagen2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 470, -1, -1));
        jPanel3.add(metros3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 790, -1, -1));
        jPanel3.add(coste3, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 790, -1, -1));
        jPanel3.add(tipoEstancia3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 730, -1, -1));
        jPanel3.add(imagen3, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 680, -1, -1));

        reservar3.setText("Reservar");
        reservar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reservar3ActionPerformed(evt);
            }
        });
        jPanel3.add(reservar3, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 850, -1, -1));
        jPanel3.add(direccion3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 760, -1, -1));

        coste0.setText("jLabel2");
        jPanel3.add(coste0, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 120, -1, -1));

        id_reserva1.setText("jLabel2");
        jPanel3.add(id_reserva1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 390, -1, -1));

        id_reserva2.setText("jLabel2");
        jPanel3.add(id_reserva2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 610, -1, -1));
        jPanel3.add(id_reserva3, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 830, -1, -1));
        jPanel3.add(fechaEntrada0, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 150, -1));
        jPanel3.add(fechaSalida0, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 150, -1));
        jPanel3.add(fechaEntrada1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 390, 150, -1));
        jPanel3.add(fechaSalida1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 420, 150, -1));
        jPanel3.add(fechaEntrada2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 600, 150, -1));
        jPanel3.add(fechaSalida2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 630, 150, -1));
        jPanel3.add(fechaEntrada3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 820, 150, -1));
        jPanel3.add(fechaSalida3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 850, 150, -1));

        jScrollPane1.setViewportView(jPanel3);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 110, 280, 380));

        jPanel4.setBackground(new java.awt.Color(255, 222, 89));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/perfil.png"))); // NOI18N
        jLabel27.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel27.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jLabel27.setIconTextGap(1);
        jPanel4.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 10, -1, -1));

        jLabel17.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel17.setText("Buscar");
        jLabel17.setPreferredSize(new java.awt.Dimension(30, 20));
        jPanel4.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, 50, 23));

        jLabel18.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Favoritos");
        jLabel18.setPreferredSize(new java.awt.Dimension(30, 20));
        jPanel4.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 50, 80, 23));

        jLabel19.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Reservas");
        jLabel19.setPreferredSize(new java.awt.Dimension(30, 20));
        jPanel4.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 50, 100, 23));

        jLabel20.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Perfil");
        jLabel20.setPreferredSize(new java.awt.Dimension(30, 20));
        jPanel4.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 50, 90, 23));

        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/busqueda.png"))); // NOI18N
        jLabel29.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel29.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jLabel29.setIconTextGap(1);
        jPanel4.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, -1, -1));

        btnReservas.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnReservas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/reservas.png"))); // NOI18N
        btnReservas.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        btnReservas.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnReservas.setIconTextGap(1);
        btnReservas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnReservasMouseClicked(evt);
            }
        });
        jPanel4.add(btnReservas, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 10, 40, 40));

        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/favoritos.png"))); // NOI18N
        jLabel31.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel31.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jLabel31.setIconTextGap(1);
        jPanel4.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, 40, 40));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 490, 440, 80));

        jPanel2.setBackground(new java.awt.Color(255, 222, 89));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/coin1.png"))); // NOI18N
        jLabel5.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jLabel5.setIconTextGap(1);
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 20, 33, -1));

        jLabel1.setText("0");
        jLabel1.setPreferredSize(new java.awt.Dimension(30, 20));
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(55, 20, 36, 30));

        jPanel5.setBackground(new java.awt.Color(255, 222, 89));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/casas_rurales.png"))); // NOI18N
        jLabel8.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel8.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jLabel8.setIconTextGap(1);
        jPanel5.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, -1));

        jLabel3.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jLabel3.setText("Casas rurales");
        jLabel3.setPreferredSize(new java.awt.Dimension(30, 20));
        jPanel5.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, 80, 30));

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/apartamentos.png"))); // NOI18N
        jLabel9.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel9.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jLabel9.setIconTextGap(1);
        jPanel5.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 0, -1, -1));

        jLabel4.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jLabel4.setText("Apartamentos");
        jLabel4.setPreferredSize(new java.awt.Dimension(30, 20));
        jPanel5.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 0, 90, 30));

        jLabel7.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Hoteles");
        jLabel7.setPreferredSize(new java.awt.Dimension(30, 20));
        jPanel5.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 0, 50, 30));

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Hoteles.png"))); // NOI18N
        jLabel10.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel10.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jLabel10.setIconTextGap(1);
        jPanel5.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 0, -1, -1));

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/apartahoteles.png"))); // NOI18N
        jLabel11.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel11.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jLabel11.setIconTextGap(1);
        jPanel5.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 0, -1, -1));

        jLabel12.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel12.setText("Apartahoteles");
        jLabel12.setPreferredSize(new java.awt.Dimension(30, 20));
        jPanel5.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 0, 100, 30));

        jPanel2.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 62, 450, -1));

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Logo_BOOK4U_Little.png"))); // NOI18N
        jLabel13.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel13.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jLabel13.setIconTextGap(1);
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 10, -1, -1));

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/notify.png"))); // NOI18N
        jLabel14.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel14.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jLabel14.setIconTextGap(1);
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 20, -1, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 440, 100));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 440, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnReservasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReservasMouseClicked
        MevesReservesPage MevesReservesPage = new MevesReservesPage(idUser);

        MevesReservesPage.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_btnReservasMouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        CreditPage creditPage = new CreditPage(idUser);
        creditPage.setVisible(true);
        this.dispose();      // TODO add your handling code here:
    }//GEN-LAST:event_jLabel5MouseClicked

    private void reservar0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reservar0ActionPerformed
        Conexion conexion = new Conexion();
        Connection connection = conexion.DatabaseConnection();

        int coste = Integer.parseInt(coste0.getText());
        int idReserva = Integer.parseInt(id_reserva0.getText());
        Date fechaEntrada = fechaEntrada0.getDate();
        Date fechaSalida = fechaSalida0.getDate();

        if (fechaEntrada == null || fechaSalida == null) {
            JOptionPane.showMessageDialog(null, "Las fechas de entrada y salida deben estar seleccionadas", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        LocalDate localDateEntrada = fechaEntrada.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate localDateSalida = fechaSalida.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        long diasDiferencia = ChronoUnit.DAYS.between(localDateEntrada, localDateSalida);

        if (diasDiferencia <= 0) {
            JOptionPane.showMessageDialog(null, "La fecha de salida debe ser posterior a la fecha de entrada", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int totalCoste = (int) (diasDiferencia * coste);

        int opcion = JOptionPane.showOptionDialog(
                null,
                "El precio total de la reserva es de: " + totalCoste + "\n Pulse Ok si desea continuar",
                "Advertencia",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.WARNING_MESSAGE,
                null,
                null,
                null);

        if (opcion == JOptionPane.OK_OPTION) {
            creditos = creditos - totalCoste;
            System.out.println(creditos);
            String updateReservas = "UPDATE reservas SET id_usuario = ? WHERE id_reserva = ?";
            try (PreparedStatement updateStatement = connection.prepareStatement(updateReservas)) {
                updateStatement.setInt(1, idUser);  // Suponiendo que idUser es tu variable que contiene el ID del usuario
                updateStatement.setInt(2, idReserva);  // Cambié este índice a 

                int rowsAffected = updateStatement.executeUpdate();
                if (rowsAffected > 0) {
                    String updateCreditos = "Update usuarios SET Credito = ? WHERE id = ?";
                    try (PreparedStatement updateStatementCreditos = connection.prepareStatement(updateCreditos)) {
                            updateStatementCreditos.setInt(1, creditos); 
                            updateStatementCreditos.setInt(2, idUser);

                            int rowsAffectedCreditos = updateStatementCreditos.executeUpdate();
                            if (rowsAffectedCreditos > 0) {
                                System.out.println("Actualización exitosa en la tabla usuarios");
                            } else {
                                System.out.println("No se ha actualizado ninguna fila en la tabla usuarios");
                                return;
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                } else {
                    System.out.println("No se ha actualizado ninguna fila en la tabla reservas");
                    return;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            String insertReservasUsuarios = "INSERT INTO reservas_usuarios (id_usuario, fecha_solicitud, fecha_fin_reserva, coste_reserva) VALUES (?, ?, ?, ?)";
            try (PreparedStatement insertStatement = connection.prepareStatement(insertReservasUsuarios)) {
                insertStatement.setInt(1, idUser);
                insertStatement.setDate(2, new java.sql.Date(fechaEntrada.getTime()));
                insertStatement.setDate(3, new java.sql.Date(fechaSalida.getTime()));
                insertStatement.setInt(4, totalCoste);

                int rowsInserted = insertStatement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("Inserción exitosa en la tabla reservas_usuarios");
                    getReservas();
                    jLabel1.setText(String.valueOf(creditos));
                    fechaEntrada0.setDate(null);
                    fechaSalida0.setDate(null);
                } else {
                    System.out.println("No se ha insertado ninguna fila en la tabla reservas_usuarios");
                    return;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            return;
        }
    }//GEN-LAST:event_reservar0ActionPerformed

    private void reservar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reservar1ActionPerformed
        Conexion conexion = new Conexion();
        Connection connection = conexion.DatabaseConnection();

        int coste = Integer.parseInt(coste1.getText());
        int idReserva = Integer.parseInt(id_reserva1.getText());
        Date fechaEntrada = fechaEntrada1.getDate();
        Date fechaSalida = fechaSalida1.getDate();

        if (fechaEntrada == null || fechaSalida == null) {
            JOptionPane.showMessageDialog(null, "Las fechas de entrada y salida deben estar seleccionadas", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        LocalDate localDateEntrada = fechaEntrada.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate localDateSalida = fechaSalida.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        long diasDiferencia = ChronoUnit.DAYS.between(localDateEntrada, localDateSalida);

        if (diasDiferencia <= 0) {
            JOptionPane.showMessageDialog(null, "La fecha de salida debe ser posterior a la fecha de entrada", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int totalCoste = (int) (diasDiferencia * coste);

        int opcion = JOptionPane.showOptionDialog(
                null,
                "El precio total de la reserva es de: " + totalCoste + "\n Pulse Ok si desea continuar",
                "Advertencia",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.WARNING_MESSAGE,
                null,
                null,
                null);

        if (opcion == JOptionPane.OK_OPTION) {
            creditos = creditos - totalCoste;
            String updateReservas = "UPDATE reservas SET id_usuario = ? WHERE id_reserva = ?";
            try (PreparedStatement updateStatement = connection.prepareStatement(updateReservas)) {
                updateStatement.setInt(1, idUser);  // Suponiendo que idUser es tu variable que contiene el ID del usuario
                updateStatement.setInt(2, idReserva);  // Cambié este índice a 

                int rowsAffected = updateStatement.executeUpdate();
                if (rowsAffected > 0) {
                    String updateCreditos = "Update usuarios SET Credito = ? WHERE id = ?";
                    try (PreparedStatement updateStatementCreditos = connection.prepareStatement(updateCreditos)) {
                            updateStatementCreditos.setInt(1, creditos);  // Suponiendo que idUser es tu variable que contiene el ID del usuario
                            updateStatementCreditos.setInt(2, idUser);

                            int rowsAffectedCreditos = updateStatementCreditos.executeUpdate();
                            if (rowsAffectedCreditos > 0) {
                                System.out.println("Actualización exitosa en la tabla usuarios");
                            } else {
                                System.out.println("No se ha actualizado ninguna fila en la tabla usuarios");
                                return;
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                } else {
                    System.out.println("No se ha actualizado ninguna fila en la tabla reservas");
                    return;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            String insertReservasUsuarios = "INSERT INTO reservas_usuarios (id_usuario, fecha_solicitud, fecha_fin_reserva, coste_reserva) VALUES (?, ?, ?, ?)";
            try (PreparedStatement insertStatement = connection.prepareStatement(insertReservasUsuarios)) {
                insertStatement.setInt(1, idUser);
                insertStatement.setDate(2, new java.sql.Date(fechaEntrada.getTime()));
                insertStatement.setDate(3, new java.sql.Date(fechaSalida.getTime()));
                insertStatement.setInt(4, totalCoste);

                int rowsInserted = insertStatement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("Inserción exitosa en la tabla reservas_usuarios");
                    getReservas();
                    jLabel1.setText(String.valueOf(creditos));
                    fechaEntrada1.setDate(null);
                    fechaSalida1.setDate(null);
                } else {
                    System.out.println("No se ha insertado ninguna fila en la tabla reservas_usuarios");
                    return;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            return;
        }
    }//GEN-LAST:event_reservar1ActionPerformed

    private void reservar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reservar2ActionPerformed
        Conexion conexion = new Conexion();
        Connection connection = conexion.DatabaseConnection();

        int coste = Integer.parseInt(coste2.getText());
        int idReserva = Integer.parseInt(id_reserva2.getText());
        Date fechaEntrada = fechaEntrada2.getDate();
        Date fechaSalida = fechaSalida2.getDate();

        if (fechaEntrada == null || fechaSalida == null) {
            JOptionPane.showMessageDialog(null, "Las fechas de entrada y salida deben estar seleccionadas", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        LocalDate localDateEntrada = fechaEntrada.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate localDateSalida = fechaSalida.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        long diasDiferencia = ChronoUnit.DAYS.between(localDateEntrada, localDateSalida);

        if (diasDiferencia <= 0) {
            JOptionPane.showMessageDialog(null, "La fecha de salida debe ser posterior a la fecha de entrada", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int totalCoste = (int) (diasDiferencia * coste);

        int opcion = JOptionPane.showOptionDialog(
                null,
                "El precio total de la reserva es de: " + totalCoste + "\n Pulse Ok si desea continuar",
                "Advertencia",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.WARNING_MESSAGE,
                null,
                null,
                null);

        if (opcion == JOptionPane.OK_OPTION) {
            creditos = creditos - totalCoste;
            String updateReservas = "UPDATE reservas SET id_usuario = ? WHERE id_reserva = ?";
            try (PreparedStatement updateStatement = connection.prepareStatement(updateReservas)) {
                updateStatement.setInt(1, idUser);  // Suponiendo que idUser es tu variable que contiene el ID del usuario
                updateStatement.setInt(2, idReserva);  // Cambié este índice a 

                int rowsAffected = updateStatement.executeUpdate();
                if (rowsAffected > 0) {
                    String updateCreditos = "Update usuarios SET Credito = ? WHERE id = ?";
                    try (PreparedStatement updateStatementCreditos = connection.prepareStatement(updateCreditos)) {
                            updateStatementCreditos.setInt(1, creditos);  
                            updateStatementCreditos.setInt(2, idUser);

                            int rowsAffectedCreditos = updateStatementCreditos.executeUpdate();
                            if (rowsAffectedCreditos > 0) {
                                System.out.println("Actualización exitosa en la tabla usuarios");
                            } else {
                                System.out.println("No se ha actualizado ninguna fila en la tabla usuarios");
                                return;
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                } else {
                    System.out.println("No se ha actualizado ninguna fila en la tabla reservas");
                    return;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            String insertReservasUsuarios = "INSERT INTO reservas_usuarios (id_usuario, fecha_solicitud, fecha_fin_reserva, coste_reserva) VALUES (?, ?, ?, ?)";
            try (PreparedStatement insertStatement = connection.prepareStatement(insertReservasUsuarios)) {
                insertStatement.setInt(1, idUser);
                insertStatement.setDate(2, new java.sql.Date(fechaEntrada.getTime()));
                insertStatement.setDate(3, new java.sql.Date(fechaSalida.getTime()));
                insertStatement.setInt(4, totalCoste);

                int rowsInserted = insertStatement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("Inserción exitosa en la tabla reservas_usuarios");
                    getReservas();
                    jLabel1.setText(String.valueOf(creditos));
                    fechaEntrada2.setDate(null);
                    fechaSalida2.setDate(null);
                } else {
                    System.out.println("No se ha insertado ninguna fila en la tabla reservas_usuarios");
                    return;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            return;
        }
    }//GEN-LAST:event_reservar2ActionPerformed

    private void reservar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reservar3ActionPerformed
        Conexion conexion = new Conexion();
        Connection connection = conexion.DatabaseConnection();

        int coste = Integer.parseInt(coste3.getText());
        int idReserva = Integer.parseInt(id_reserva3.getText());
        Date fechaEntrada = fechaEntrada3.getDate();
        Date fechaSalida = fechaSalida3.getDate();

        if (fechaEntrada == null || fechaSalida == null) {
            JOptionPane.showMessageDialog(null, "Las fechas de entrada y salida deben estar seleccionadas", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        LocalDate localDateEntrada = fechaEntrada.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate localDateSalida = fechaSalida.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        long diasDiferencia = ChronoUnit.DAYS.between(localDateEntrada, localDateSalida);

        if (diasDiferencia <= 0) {
            JOptionPane.showMessageDialog(null, "La fecha de salida debe ser posterior a la fecha de entrada", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int totalCoste = (int) (diasDiferencia * coste);

        int opcion = JOptionPane.showOptionDialog(
                null,
                "El precio total de la reserva es de: " + totalCoste + "\n Pulse Ok si desea continuar",
                "Advertencia",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.WARNING_MESSAGE,
                null,
                null,
                null);

        if (opcion == JOptionPane.OK_OPTION) {
            creditos = creditos - totalCoste;
            String updateReservas = "UPDATE reservas SET id_usuario = ? WHERE id_reserva = ?";
            try (PreparedStatement updateStatement = connection.prepareStatement(updateReservas)) {
                updateStatement.setInt(1, idUser);  // Suponiendo que idUser es tu variable que contiene el ID del usuario
                updateStatement.setInt(2, idReserva);  // Cambié este índice a 

                int rowsAffected = updateStatement.executeUpdate();
                if (rowsAffected > 0) {
                    String updateCreditos = "Update usuarios SET Credito = ? WHERE id = ?";
                    try (PreparedStatement updateStatementCreditos = connection.prepareStatement(updateCreditos)) {
                            updateStatementCreditos.setInt(1, creditos);  // Suponiendo que idUser es tu variable que contiene el ID del usuario
                            updateStatementCreditos.setInt(2, idUser);

                            int rowsAffectedCreditos = updateStatementCreditos.executeUpdate();
                            if (rowsAffectedCreditos > 0) {
                                System.out.println("Actualización exitosa en la tabla usuarios");
                            } else {
                                System.out.println("No se ha actualizado ninguna fila en la tabla usuarios");
                                return;
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                } else {
                    System.out.println("No se ha actualizado ninguna fila en la tabla reservas");
                    return;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            String insertReservasUsuarios = "INSERT INTO reservas_usuarios (id_usuario, fecha_solicitud, fecha_fin_reserva, coste_reserva) VALUES (?, ?, ?, ?)";
            try (PreparedStatement insertStatement = connection.prepareStatement(insertReservasUsuarios)) {
                insertStatement.setInt(1, idUser);
                insertStatement.setDate(2, new java.sql.Date(fechaEntrada.getTime()));
                insertStatement.setDate(3, new java.sql.Date(fechaSalida.getTime()));
                insertStatement.setInt(4, totalCoste);

                int rowsInserted = insertStatement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("Inserción exitosa en la tabla reservas_usuarios");
                    getReservas();
                    jLabel1.setText(String.valueOf(creditos));
                    fechaEntrada3.setDate(null);
                    fechaSalida3.setDate(null);
                } else {
                    System.out.println("No se ha insertado ninguna fila en la tabla reservas_usuarios");
                    return;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            return;
        }
    }//GEN-LAST:event_reservar3ActionPerformed

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
            java.util.logging.Logger.getLogger(MainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnReservas;
    private javax.swing.JLabel coste0;
    private javax.swing.JLabel coste1;
    private javax.swing.JLabel coste2;
    private javax.swing.JLabel coste3;
    private javax.swing.JLabel direccion0;
    private javax.swing.JLabel direccion1;
    private javax.swing.JLabel direccion2;
    private javax.swing.JLabel direccion3;
    private com.toedter.calendar.JDateChooser fechaEntrada0;
    private com.toedter.calendar.JDateChooser fechaEntrada1;
    private com.toedter.calendar.JDateChooser fechaEntrada2;
    private com.toedter.calendar.JDateChooser fechaEntrada3;
    private com.toedter.calendar.JDateChooser fechaSalida0;
    private com.toedter.calendar.JDateChooser fechaSalida1;
    private com.toedter.calendar.JDateChooser fechaSalida2;
    private com.toedter.calendar.JDateChooser fechaSalida3;
    private javax.swing.JLabel id_reserva0;
    private javax.swing.JLabel id_reserva1;
    private javax.swing.JLabel id_reserva2;
    private javax.swing.JLabel id_reserva3;
    private javax.swing.JLabel imagen0;
    private javax.swing.JLabel imagen1;
    private javax.swing.JLabel imagen2;
    private javax.swing.JLabel imagen3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel metros0;
    private javax.swing.JLabel metros1;
    private javax.swing.JLabel metros2;
    private javax.swing.JLabel metros3;
    private javax.swing.JButton reservar0;
    private javax.swing.JButton reservar1;
    private javax.swing.JButton reservar2;
    private javax.swing.JButton reservar3;
    private javax.swing.JLabel tipoEstancia0;
    private javax.swing.JLabel tipoEstancia1;
    private javax.swing.JLabel tipoEstancia2;
    private javax.swing.JLabel tipoEstancia3;
    // End of variables declaration//GEN-END:variables
}
