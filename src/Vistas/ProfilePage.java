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
import java.sql.SQLException;

/**
 *
 * @author alumnat
 */
public class ProfilePage extends javax.swing.JFrame {

    private final int idUser;
    private Conexion conexion;
    int creditos;
    /**
     * Creates new form MainPage
     */
    public ProfilePage(int idUser) {
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
    }

    private ProfilePage() {
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
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
        Logo = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        inputDinero = new javax.swing.JTextField();
        inputDinero1 = new javax.swing.JTextField();
        inputDinero2 = new javax.swing.JTextField();
        inputDinero3 = new javax.swing.JTextField();
        inputDinero4 = new javax.swing.JTextField();
        inputDinero5 = new javax.swing.JTextField();
        inputDinero6 = new javax.swing.JTextField();
        inputDinero7 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 1, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/HomeUser.png"))); // NOI18N
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
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 33, -1));

        jLabel1.setText("0");
        jLabel1.setPreferredSize(new java.awt.Dimension(30, 20));
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 36, 30));

        Logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Logo_BOOK4U_Little.png"))); // NOI18N
        Logo.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        Logo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Logo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Logo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LogoMouseClicked(evt);
            }
        });
        jPanel2.add(Logo, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 10, -1, -1));

        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/notify.png"))); // NOI18N
        jLabel15.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel15.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jLabel15.setIconTextGap(1);
        jPanel2.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 10, -1, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 440, 60));

        inputDinero.setColumns(1);
        inputDinero.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        inputDinero.setForeground(new java.awt.Color(153, 153, 153));
        inputDinero.setText("Nombre de Usuario");
        inputDinero.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        inputDinero.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        inputDinero.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                inputDineroFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                inputDineroFocusLost(evt);
            }
        });
        inputDinero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputDineroActionPerformed(evt);
            }
        });
        jPanel1.add(inputDinero, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 100, 200, 40));

        inputDinero1.setColumns(1);
        inputDinero1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        inputDinero1.setForeground(new java.awt.Color(153, 153, 153));
        inputDinero1.setText("Sexo");
        inputDinero1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        inputDinero1.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        inputDinero1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                inputDinero1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                inputDinero1FocusLost(evt);
            }
        });
        inputDinero1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputDinero1ActionPerformed(evt);
            }
        });
        jPanel1.add(inputDinero1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 370, 230, -1));

        inputDinero2.setColumns(1);
        inputDinero2.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        inputDinero2.setForeground(new java.awt.Color(153, 153, 153));
        inputDinero2.setText("Ubicación");
        inputDinero2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        inputDinero2.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        inputDinero2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                inputDinero2FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                inputDinero2FocusLost(evt);
            }
        });
        inputDinero2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputDinero2ActionPerformed(evt);
            }
        });
        jPanel1.add(inputDinero2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 230, -1));

        inputDinero3.setColumns(1);
        inputDinero3.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        inputDinero3.setForeground(new java.awt.Color(153, 153, 153));
        inputDinero3.setText("Fecha de nacimiento");
        inputDinero3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        inputDinero3.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        inputDinero3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                inputDinero3FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                inputDinero3FocusLost(evt);
            }
        });
        inputDinero3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputDinero3ActionPerformed(evt);
            }
        });
        jPanel1.add(inputDinero3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 230, -1));

        inputDinero4.setColumns(1);
        inputDinero4.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        inputDinero4.setForeground(new java.awt.Color(153, 153, 153));
        inputDinero4.setText("E-mail");
        inputDinero4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        inputDinero4.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        inputDinero4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                inputDinero4FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                inputDinero4FocusLost(evt);
            }
        });
        inputDinero4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputDinero4ActionPerformed(evt);
            }
        });
        jPanel1.add(inputDinero4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, 230, -1));

        inputDinero5.setColumns(1);
        inputDinero5.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        inputDinero5.setForeground(new java.awt.Color(153, 153, 153));
        inputDinero5.setText("Contraseña");
        inputDinero5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        inputDinero5.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        inputDinero5.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                inputDinero5FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                inputDinero5FocusLost(evt);
            }
        });
        inputDinero5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputDinero5ActionPerformed(evt);
            }
        });
        jPanel1.add(inputDinero5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, 230, -1));

        inputDinero6.setColumns(1);
        inputDinero6.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        inputDinero6.setForeground(new java.awt.Color(153, 153, 153));
        inputDinero6.setText("Domicilio");
        inputDinero6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        inputDinero6.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        inputDinero6.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                inputDinero6FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                inputDinero6FocusLost(evt);
            }
        });
        inputDinero6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputDinero6ActionPerformed(evt);
            }
        });
        jPanel1.add(inputDinero6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, 230, -1));

        inputDinero7.setColumns(1);
        inputDinero7.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        inputDinero7.setForeground(new java.awt.Color(153, 153, 153));
        inputDinero7.setText("Teléfono Móvil");
        inputDinero7.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        inputDinero7.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        inputDinero7.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                inputDinero7FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                inputDinero7FocusLost(evt);
            }
        });
        inputDinero7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputDinero7ActionPerformed(evt);
            }
        });
        jPanel1.add(inputDinero7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 340, 230, -1));

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/profileSave.png"))); // NOI18N
        jLabel14.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel14.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jLabel14.setIconTextGap(1);
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 190, -1, 30));

        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/profileSave.png"))); // NOI18N
        jLabel16.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel16.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jLabel16.setIconTextGap(1);
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 340, -1, 30));

        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/profileSave.png"))); // NOI18N
        jLabel21.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel21.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jLabel21.setIconTextGap(1);
        jPanel1.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 220, -1, 30));

        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/profileSave.png"))); // NOI18N
        jLabel22.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel22.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jLabel22.setIconTextGap(1);
        jPanel1.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 250, -1, 30));

        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/profileSave.png"))); // NOI18N
        jLabel23.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel23.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jLabel23.setIconTextGap(1);
        jPanel1.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 280, -1, 30));

        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/profileSave.png"))); // NOI18N
        jLabel24.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel24.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jLabel24.setIconTextGap(1);
        jPanel1.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 310, -1, 30));

        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/profileSave.png"))); // NOI18N
        jLabel25.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel25.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jLabel25.setIconTextGap(1);
        jPanel1.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 370, -1, 30));

        jButton1.setBackground(new java.awt.Color(255, 222, 89));
        jButton1.setText("Guardar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 423, 110, 30));
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(37, 76, 110, 90));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 440, 570));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnReservasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReservasMouseClicked
        MevesReservesPage MevesReservesPage = new MevesReservesPage(idUser,creditos);

        MevesReservesPage.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_btnReservasMouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        CreditPage creditPage = new CreditPage(idUser);
        creditPage.setVisible(true);
        this.dispose();      // TODO add your handling code here:
    }//GEN-LAST:event_jLabel5MouseClicked

    private void LogoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LogoMouseClicked
        MainPage mainPage = new MainPage(idUser);
        mainPage.setVisible(true);
        setVisible(false);        // TODO add your handling code here:
    }//GEN-LAST:event_LogoMouseClicked

    private void inputDineroFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputDineroFocusGained
        if (inputDinero.getText().equals("Agregar Importe")) {
            inputDinero.setText("");
            inputDinero.setForeground(new Color(0, 0, 0));
        }
    }//GEN-LAST:event_inputDineroFocusGained

    private void inputDineroFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputDineroFocusLost

        //textoDelJTextField = inputDinero.getText();
    }//GEN-LAST:event_inputDineroFocusLost

    private void inputDineroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputDineroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputDineroActionPerformed

    private void inputDinero1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputDinero1FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_inputDinero1FocusGained

    private void inputDinero1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputDinero1FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_inputDinero1FocusLost

    private void inputDinero1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputDinero1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputDinero1ActionPerformed

    private void inputDinero2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputDinero2FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_inputDinero2FocusGained

    private void inputDinero2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputDinero2FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_inputDinero2FocusLost

    private void inputDinero2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputDinero2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputDinero2ActionPerformed

    private void inputDinero3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputDinero3FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_inputDinero3FocusGained

    private void inputDinero3FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputDinero3FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_inputDinero3FocusLost

    private void inputDinero3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputDinero3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputDinero3ActionPerformed

    private void inputDinero4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputDinero4FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_inputDinero4FocusGained

    private void inputDinero4FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputDinero4FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_inputDinero4FocusLost

    private void inputDinero4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputDinero4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputDinero4ActionPerformed

    private void inputDinero5FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputDinero5FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_inputDinero5FocusGained

    private void inputDinero5FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputDinero5FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_inputDinero5FocusLost

    private void inputDinero5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputDinero5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputDinero5ActionPerformed

    private void inputDinero6FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputDinero6FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_inputDinero6FocusGained

    private void inputDinero6FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputDinero6FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_inputDinero6FocusLost

    private void inputDinero6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputDinero6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputDinero6ActionPerformed

    private void inputDinero7FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputDinero7FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_inputDinero7FocusGained

    private void inputDinero7FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputDinero7FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_inputDinero7FocusLost

    private void inputDinero7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputDinero7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputDinero7ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
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
            java.util.logging.Logger.getLogger(ProfilePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProfilePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProfilePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProfilePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ProfilePage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Logo;
    private javax.swing.JLabel btnReservas;
    private javax.swing.JTextField inputDinero;
    private javax.swing.JTextField inputDinero1;
    private javax.swing.JTextField inputDinero2;
    private javax.swing.JTextField inputDinero3;
    private javax.swing.JTextField inputDinero4;
    private javax.swing.JTextField inputDinero5;
    private javax.swing.JTextField inputDinero6;
    private javax.swing.JTextField inputDinero7;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    // End of variables declaration//GEN-END:variables
}