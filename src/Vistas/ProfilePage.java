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
import java.awt.BorderLayout;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author alumnat
 */
public class ProfilePage extends javax.swing.JFrame {

    private final int idUser;
    private Conexion conexion;
    int creditos;
    String textoDelJTextField;
    private Object chooser;

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
        jLabel1.setText(String.valueOf(creditos));

        mostrarDatosUsuario();
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

    private void mostrarDatosUsuario() {
        Conexion conexion = new Conexion();
        Connection connection = conexion.DatabaseConnection(); // Obtén la conexión
        int creditos = 0;

        try {
            // Consulta SQL para obtener los créditos del usuario por su ID
            String consulta = "SELECT * FROM usuarios WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(consulta);
            statement.setInt(1, idUser);

            ResultSet resultado = statement.executeQuery();
            // Si se encontraron resultados, obtenemos los créditos del usuario
            if (resultado.next()) {

                byte[] FotoBytes = resultado.getBytes("Foto");
                String NombreTexto = resultado.getString("Nombre");
                String Apellido1Texto = resultado.getString("Apellido1");
                String Apellido2Texto = resultado.getString("Apellido2");
                String MailTexto = resultado.getString("Email");
                String TelefonoInt = resultado.getString("Telefono");
                String DNITexto = resultado.getString("DNI");
                String PasswordTexto = resultado.getString("Password");
                String DomicilioTexto = resultado.getString("Domicilio");

                if (FotoBytes != null) {

                    ImageIcon imagenReserva = new ImageIcon(FotoBytes);
                    Image imagenRedimensionada = imagenReserva.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                    ImageIcon imagenRedimensionadaIcon = new ImageIcon(imagenRedimensionada);
                    jLabelFoto.setIcon(imagenRedimensionadaIcon);
                }
                inputNombre.setText(NombreTexto + " " + Apellido1Texto + " " + Apellido2Texto);
                inputMail.setText(MailTexto);
                inputDNI.setText(DNITexto);
                inputTelef.setText(TelefonoInt);
                inputPassw.setText(PasswordTexto);
                inputDomicilio.setText(DomicilioTexto);

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

    private void actualizarDatosUsuario() {
        Conexion conexion = new Conexion();
        Connection connection = conexion.DatabaseConnection(); // Obtén la conexión
        int creditos = 0;

        try {
            // Consulta SQL para obtener los créditos del usuario por su ID
            String consulta = "UPDATE usuarios SET Nombre = ?, Apellido1 = ?, Apellido2 = ?, Telefono = ?, DNI = ?, Password = ?, Domicilio = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(consulta);

            // Obtener el contenido actual de inputNombre
            String nombreCompleto = inputNombre.getText();

            // Separar el contenido en nombre, apellido1 y apellido2
            String[] partesNombre = nombreCompleto.split(" ");

            // Asegurarse de tener al menos un nombre
            if (partesNombre.length > 0) {
                // Establecer los valores correspondientes
                statement.setString(1, partesNombre[0]); // Nombre
            }

            // Establecer los apellidos si están disponibles
            if (partesNombre.length > 1) {
                statement.setString(2, partesNombre[1]); // Apellido1
            }

            if (partesNombre.length > 2) {
                statement.setString(3, partesNombre[2]); // Apellido2
            }
            statement.setString(4, inputTelef.getText());
            statement.setString(5, inputDNI.getText());
            statement.setString(6, inputPassw.getText());
            statement.setString(7, inputDomicilio.getText());
            statement.setInt(8, idUser);

            // Ejecutar la actualización
            int filasActualizadas = statement.executeUpdate();

            if (filasActualizadas > 0) {
                JOptionPane.showMessageDialog(this, "Datos del usuario actualizados exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                mostrarDatosUsuario();

                String nombreUsuario = getNombreUsuarioPorId(idUser);
                jLabel20.setText(nombreUsuario);
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo actualizar los datos del usuario.", "Error", JOptionPane.ERROR_MESSAGE);
            }

            // Si se encontraron resultados, obtenemos los créditos del usuario
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
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelDatos = new javax.swing.JPanel();
        jPanelSuperior = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        Logo = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jPanelInferior = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        btnReservas = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabelFoto = new javax.swing.JLabel();
        SaveNombre = new javax.swing.JLabel();
        SaveMail = new javax.swing.JLabel();
        SaveTelef = new javax.swing.JLabel();
        SaveDNI = new javax.swing.JLabel();
        SavePassw = new javax.swing.JLabel();
        SaveDomicilio = new javax.swing.JLabel();
        inputNombre = new javax.swing.JTextField();
        inputDNI = new javax.swing.JTextField();
        inputMail = new javax.swing.JTextField();
        inputPassw = new javax.swing.JTextField();
        inputDomicilio = new javax.swing.JTextField();
        inputTelef = new javax.swing.JTextField();
        jButtonGuardar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelDatos.setBackground(new java.awt.Color(255, 255, 255));
        jPanelDatos.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 1, 0));
        jPanelDatos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelSuperior.setBackground(new java.awt.Color(255, 222, 89));
        jPanelSuperior.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        jPanelSuperior.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 33, -1));

        jLabel1.setText("0");
        jLabel1.setPreferredSize(new java.awt.Dimension(30, 20));
        jPanelSuperior.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 36, 30));

        Logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Logo_BOOK4U_Little.png"))); // NOI18N
        Logo.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        Logo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Logo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Logo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LogoMouseClicked(evt);
            }
        });
        jPanelSuperior.add(Logo, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 10, -1, -1));

        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/notify.png"))); // NOI18N
        jLabel15.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel15.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jLabel15.setIconTextGap(1);
        jPanelSuperior.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 10, -1, -1));

        jPanelDatos.add(jPanelSuperior, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 440, 60));

        jPanelInferior.setBackground(new java.awt.Color(255, 222, 89));
        jPanelInferior.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/perfil.png"))); // NOI18N
        jLabel27.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel27.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jLabel27.setIconTextGap(1);
        jLabel27.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel27MouseClicked(evt);
            }
        });
        jPanelInferior.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 10, -1, -1));

        jLabel17.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Inicio");
        jLabel17.setPreferredSize(new java.awt.Dimension(30, 20));
        jPanelInferior.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 60, 23));

        jLabel18.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Favoritos");
        jLabel18.setPreferredSize(new java.awt.Dimension(30, 20));
        jPanelInferior.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 50, 80, 23));

        jLabel19.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Reservas");
        jLabel19.setPreferredSize(new java.awt.Dimension(30, 20));
        jPanelInferior.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 50, 100, 23));

        jLabel20.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Perfil");
        jLabel20.setPreferredSize(new java.awt.Dimension(30, 20));
        jPanelInferior.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 50, 90, 23));

        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/HomeUser.png"))); // NOI18N
        jLabel29.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel29.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jLabel29.setIconTextGap(1);
        jLabel29.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel29MouseClicked(evt);
            }
        });
        jPanelInferior.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, -1, -1));

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
        jPanelInferior.add(btnReservas, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 10, 40, 40));

        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/favoritos.png"))); // NOI18N
        jLabel31.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel31.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jLabel31.setIconTextGap(1);
        jPanelInferior.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, 40, 40));

        jPanelDatos.add(jPanelInferior, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 490, 440, 80));

        jLabelFoto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelFoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/profile_Null.png"))); // NOI18N
        jLabelFoto.setFocusable(false);
        jLabelFoto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelFotoMouseClicked(evt);
            }
        });
        jPanelDatos.add(jLabelFoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 90, 120, 100));

        SaveNombre.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        SaveNombre.setText("Nombre:");
        SaveNombre.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        SaveNombre.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        SaveNombre.setIconTextGap(1);
        jPanelDatos.add(SaveNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 210, -1, 30));

        SaveMail.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        SaveMail.setText("E-Mail:");
        SaveMail.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        SaveMail.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        SaveMail.setIconTextGap(1);
        jPanelDatos.add(SaveMail, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 240, -1, 30));

        SaveTelef.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        SaveTelef.setText("Teléfono Móvil:");
        SaveTelef.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        SaveTelef.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        SaveTelef.setIconTextGap(1);
        jPanelDatos.add(SaveTelef, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 270, -1, 30));

        SaveDNI.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        SaveDNI.setText("DNI:");
        SaveDNI.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        SaveDNI.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        SaveDNI.setIconTextGap(1);
        jPanelDatos.add(SaveDNI, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 300, -1, 30));

        SavePassw.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        SavePassw.setText("Contraseña:");
        SavePassw.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        SavePassw.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        SavePassw.setIconTextGap(1);
        jPanelDatos.add(SavePassw, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 330, -1, 30));

        SaveDomicilio.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        SaveDomicilio.setText("Domicilio:");
        SaveDomicilio.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        SaveDomicilio.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        SaveDomicilio.setIconTextGap(1);
        jPanelDatos.add(SaveDomicilio, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 360, -1, 30));

        inputNombre.setColumns(1);
        inputNombre.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        inputNombre.setForeground(new java.awt.Color(153, 153, 153));
        inputNombre.setText("Nombre");
        inputNombre.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        inputNombre.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        inputNombre.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                inputNombreFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                inputNombreFocusLost(evt);
            }
        });
        inputNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputNombreActionPerformed(evt);
            }
        });
        jPanelDatos.add(inputNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 220, 230, -1));

        inputDNI.setColumns(1);
        inputDNI.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        inputDNI.setForeground(new java.awt.Color(153, 153, 153));
        inputDNI.setText("DNI");
        inputDNI.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        inputDNI.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        inputDNI.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                inputDNIFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                inputDNIFocusLost(evt);
            }
        });
        inputDNI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputDNIActionPerformed(evt);
            }
        });
        jPanelDatos.add(inputDNI, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 310, 230, -1));

        inputMail.setColumns(1);
        inputMail.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        inputMail.setForeground(new java.awt.Color(153, 153, 153));
        inputMail.setText("E-mail");
        inputMail.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        inputMail.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        inputMail.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                inputMailFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                inputMailFocusLost(evt);
            }
        });
        inputMail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputMailActionPerformed(evt);
            }
        });
        jPanelDatos.add(inputMail, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 250, 230, -1));

        inputPassw.setColumns(1);
        inputPassw.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        inputPassw.setForeground(new java.awt.Color(153, 153, 153));
        inputPassw.setText("Contraseña");
        inputPassw.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        inputPassw.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        inputPassw.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                inputPasswFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                inputPasswFocusLost(evt);
            }
        });
        inputPassw.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputPasswActionPerformed(evt);
            }
        });
        jPanelDatos.add(inputPassw, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 340, 230, -1));

        inputDomicilio.setColumns(1);
        inputDomicilio.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        inputDomicilio.setForeground(new java.awt.Color(153, 153, 153));
        inputDomicilio.setText("Domicilio");
        inputDomicilio.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        inputDomicilio.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        inputDomicilio.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                inputDomicilioFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                inputDomicilioFocusLost(evt);
            }
        });
        inputDomicilio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputDomicilioActionPerformed(evt);
            }
        });
        jPanelDatos.add(inputDomicilio, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 370, 230, -1));

        inputTelef.setColumns(1);
        inputTelef.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        inputTelef.setForeground(new java.awt.Color(153, 153, 153));
        inputTelef.setText("Teléfono Móvil");
        inputTelef.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        inputTelef.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        inputTelef.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                inputTelefFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                inputTelefFocusLost(evt);
            }
        });
        inputTelef.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputTelefActionPerformed(evt);
            }
        });
        jPanelDatos.add(inputTelef, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 280, 230, -1));

        jButtonGuardar.setBackground(new java.awt.Color(255, 222, 89));
        jButtonGuardar.setText("Guardar");
        jButtonGuardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonGuardarMouseClicked(evt);
            }
        });
        jButtonGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGuardarActionPerformed(evt);
            }
        });
        jPanelDatos.add(jButtonGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 420, 200, 30));

        getContentPane().add(jPanelDatos, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 440, 570));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnReservasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReservasMouseClicked
        MevesReservesPage MevesReservesPage = new MevesReservesPage(idUser, creditos);

        MevesReservesPage.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_btnReservasMouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        CreditPage creditPage = new CreditPage(idUser);
        creditPage.setVisible(true);
        this.dispose();      // TODO add your handling code here:
    }//GEN-LAST:event_jLabel5MouseClicked

    private void LogoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LogoMouseClicked
        NewMainPage NewMainPage = new NewMainPage(idUser);
        NewMainPage.setVisible(true);
        setVisible(false);        // TODO add your handling code here:
    }//GEN-LAST:event_LogoMouseClicked

    private void inputMailFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputMailFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_inputMailFocusGained

    private void inputMailFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputMailFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_inputMailFocusLost

    private void inputMailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputMailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputMailActionPerformed

    private void inputPasswFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputPasswFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_inputPasswFocusGained

    private void inputPasswFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputPasswFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_inputPasswFocusLost

    private void inputPasswActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputPasswActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputPasswActionPerformed

    private void inputDomicilioFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputDomicilioFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_inputDomicilioFocusGained

    private void inputDomicilioFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputDomicilioFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_inputDomicilioFocusLost

    private void inputDomicilioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputDomicilioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputDomicilioActionPerformed

    private void inputTelefFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputTelefFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_inputTelefFocusGained

    private void inputTelefFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputTelefFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_inputTelefFocusLost

    private void inputTelefActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputTelefActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputTelefActionPerformed

    private void jButtonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonGuardarActionPerformed

    private void jLabel29MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel29MouseClicked
        NewMainPage NewMainPage = new NewMainPage(idUser);
        NewMainPage.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_jLabel29MouseClicked

    private void jLabel27MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel27MouseClicked
        ProfilePage ProfilePage = new ProfilePage(idUser);

        ProfilePage.setVisible(true);
        setVisible(false);        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel27MouseClicked

    private void inputDNIFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputDNIFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_inputDNIFocusGained

    private void inputDNIFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputDNIFocusLost
        //textoDelJTextField = inputDinero.getText();
    }//GEN-LAST:event_inputDNIFocusLost

    private void inputDNIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputDNIActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputDNIActionPerformed

    private void inputNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputNombreActionPerformed

    private void inputNombreFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputNombreFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_inputNombreFocusLost

    private void inputNombreFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputNombreFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_inputNombreFocusGained

    private void jButtonGuardarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonGuardarMouseClicked

        actualizarDatosUsuario();

    }//GEN-LAST:event_jButtonGuardarMouseClicked

    private void jLabelFotoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelFotoMouseClicked
        Conexion conexion = new Conexion();
        Connection connection = conexion.DatabaseConnection();

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(this);

        File selectedFile = fileChooser.getSelectedFile();
        if (selectedFile != null) {
            try {
                FileInputStream fis = new FileInputStream(selectedFile);
                byte[] imagenBytes = new byte[(int) selectedFile.length()];
                fis.read(imagenBytes);
                fis.close();

                try {
                    String consulta = "UPDATE usuarios SET Foto = ? WHERE id = ?";
                    PreparedStatement statement = connection.prepareStatement(consulta);
                    statement.setBytes(1, imagenBytes);
                    statement.setInt(2, idUser);

                    // Ejecutar la actualización
                    int filasActualizadas = statement.executeUpdate();

                    if (filasActualizadas > 0) {
                        JOptionPane.showMessageDialog(this, "Imagen actualizada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        mostrarDatosUsuario();
                    } else {
                        JOptionPane.showMessageDialog(this, "No se pudo actualizar los datos del usuario.", "Error", JOptionPane.ERROR_MESSAGE);
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
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_jLabelFotoMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ProfilePage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Logo;
    private javax.swing.JLabel SaveDNI;
    private javax.swing.JLabel SaveDomicilio;
    private javax.swing.JLabel SaveMail;
    private javax.swing.JLabel SaveNombre;
    private javax.swing.JLabel SavePassw;
    private javax.swing.JLabel SaveTelef;
    private javax.swing.JLabel btnReservas;
    private javax.swing.JTextField inputDNI;
    private javax.swing.JTextField inputDomicilio;
    private javax.swing.JTextField inputMail;
    private javax.swing.JTextField inputNombre;
    private javax.swing.JTextField inputPassw;
    private javax.swing.JTextField inputTelef;
    private javax.swing.JButton jButtonGuardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabelFoto;
    private javax.swing.JPanel jPanelDatos;
    private javax.swing.JPanel jPanelInferior;
    private javax.swing.JPanel jPanelSuperior;
    // End of variables declaration//GEN-END:variables
}
