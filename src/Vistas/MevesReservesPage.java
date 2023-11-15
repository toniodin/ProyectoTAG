/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vistas;

import java.awt.Color;
import java.awt.Image;
import javax.swing.ImageIcon;
import bd.Conexion;
import com.toedter.calendar.JDateChooser;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.Box;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import javax.swing.JButton;

/**
 *
 * @author bella
 */
public class MevesReservesPage extends javax.swing.JFrame {

    private final int idUser;
    private int creditosUser;
    private Conexion conexion;
  
    public MevesReservesPage(int idUser, int creditos) {
        initComponents();
        System.out.println(idUser);
        this.setLocationRelativeTo(null); //Inicializa al centro de la pantalla

        ImageIcon icon = new ImageIcon(getClass().getResource("/imagenes/icono.png")); // Esto es para cambiar el icono de la app
        Image image = icon.getImage();
        setIconImage(image);
        this.idUser = idUser;
        this.creditosUser = creditos;
        
        this.conexion = new Conexion();

        String nombreUsuario = getNombreUsuarioPorId(idUser);
        jLabel20.setText(nombreUsuario);

        mostrarReservasActivas();

        //Muestra el panel de reservas Activadas y oculta los otros
        panelActivos.setVisible(true);
        panelPasados.setVisible(false);
        panelCancelados.setVisible(false);

        btnActivos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panelActivos.setVisible(true);
                panelPasados.setVisible(false);
                panelCancelados.setVisible(false);
            }
        });

        btnPasados.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panelActivos.setVisible(false);
                panelPasados.setVisible(true);
                panelCancelados.setVisible(false);
            }
        });

        btnCancelados.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panelActivos.setVisible(false);
                panelPasados.setVisible(false);
                panelCancelados.setVisible(true);
            }
        });
    }

    private MevesReservesPage() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private String getNombreUsuarioPorId(int id) {
        Conexion conexion = new Conexion();
        Connection connection = conexion.DatabaseConnection();
        String nombre = "";

        try {
            String consulta = "SELECT * FROM usuarios WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(consulta);
            statement.setInt(1, id);

            ResultSet resultado = statement.executeQuery();
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

    private void mostrarReservasActivas() {

        String consulta = "SELECT ru.id_reserva, r.tipo_estancia, r.direccion, r.imagen, ru.coste_reserva, ru.fecha_solicitud, ru.fecha_fin_reserva "
                + "FROM reservas r INNER JOIN reservas_usuarios ru ON r.id_reserva = ru.id_reserva "
                + "WHERE ru.id_usuario = ? AND ru.fecha_fin_reserva >= CURRENT_DATE";

        try (Connection connection = conexion.DatabaseConnection(); PreparedStatement statement = connection.prepareStatement(consulta)) {

            statement.setInt(1, idUser);
            ResultSet resultado = statement.executeQuery();

            // Contar el número de resultados obtenidos
            int contadorResultados = 0;

             // Crear un nuevo panel para contener los resultados
            JPanel nuevoPanelActivos = new JPanel();
            nuevoPanelActivos.setLayout(new BoxLayout(nuevoPanelActivos, BoxLayout.Y_AXIS));

            while (resultado.next()) {
                contadorResultados++;
                
                String tipoEstanciaTexto = resultado.getString("tipo_estancia");
                String direccionTexto = resultado.getString("direccion");
                byte[] imagenBytes = resultado.getBytes("imagen");
                int costeReservaValor = resultado.getInt("coste_reserva");
                int idReserva = resultado.getInt("id_reserva");
                Date fechaSolicitudValor = resultado.getDate("fecha_solicitud");
                Date fechaFinSolicitudValor = resultado.getDate("fecha_fin_reserva");

                ImageIcon imagenReserva = new ImageIcon(imagenBytes);
                Image imagenRedimensionada = imagenReserva.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                ImageIcon imagenRedimensionadaIcon = new ImageIcon(imagenRedimensionada);
                JLabel imagenRedimensionadaLabel = new JLabel(imagenRedimensionadaIcon);
                JButton editarReservaButton = new JButton("Editar Reserva");
                editarReservaButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        String consulta2 = "SELECT * FROM reservas WHERE id_reserva = ?";
                        try (Connection connection2 = conexion.DatabaseConnection(); PreparedStatement statement2 = connection2.prepareStatement(consulta2)) {
                            statement2.setInt(1, idReserva);
                            ResultSet resultado2 = statement2.executeQuery();
                            while (resultado2.next()) {
                                int coste = resultado2.getInt("coste_dia");

                                // Convertir las fechas a LocalDate
                                LocalDate fechaSolicitudLocal = new java.sql.Date(fechaSolicitudValor.getTime()).toLocalDate();
                                LocalDate fechaFinSolicitudLocal = new java.sql.Date(fechaFinSolicitudValor.getTime()).toLocalDate();
                                long diferenciaDias = ChronoUnit.DAYS.between(fechaSolicitudLocal, fechaFinSolicitudLocal);

                                int totalCoste = (int) (diferenciaDias * coste);
                                int devolucion = totalCoste - costeReservaValor;
                                
                                int confirmacion = JOptionPane.showOptionDialog(
                                    null,
                                    "El precio total de la reserva es de: " + totalCoste +" se le retornarán los puntos si es que sobran"+ "\n Pulse Ok si desea continuar",
                                    "Advertencia",
                                    JOptionPane.OK_CANCEL_OPTION,
                                    JOptionPane.WARNING_MESSAGE,
                                    null,
                                    null,
                                    null);
                                
                                creditosUser = creditosUser + devolucion;
                                
                                if (confirmacion == JOptionPane.OK_OPTION) {
                                    String updateCreditos = "Update usuarios SET Credito = ? WHERE id = ?";
                                    try (Connection connection3 = conexion.DatabaseConnection(); PreparedStatement updateStatementCreditos = connection3.prepareStatement(updateCreditos)) {
                                        updateStatementCreditos.setInt(1, creditosUser);
                                        updateStatementCreditos.setInt(2, idUser);

                                        int rowsAffectedCreditos = updateStatementCreditos.executeUpdate();
                                    } catch (SQLException e2) {
                                        e2.printStackTrace();
                                    }
                                }
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                });

              JPanel panelReserva = crearPanelReservaMejorado(imagenRedimensionadaLabel, tipoEstanciaTexto, direccionTexto, costeReservaValor, fechaSolicitudValor, fechaFinSolicitudValor, editarReservaButton);
                nuevoPanelActivos.add(panelReserva);
            }

            // Crear un nuevo JScrollPane con el nuevo panel
            JScrollPane nuevoJScrollPane = new JScrollPane(nuevoPanelActivos);
            nuevoJScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            nuevoJScrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Ajusta los márgenes

            // Remover todos los componentes del panel principal
            panelActivos.removeAll();

            // Agregar el nuevo JScrollPane al panel principal
            panelActivos.setLayout(new BorderLayout());
            panelActivos.add(nuevoJScrollPane, BorderLayout.CENTER);

            // Actualizar la interfaz gráfica
            revalidate();
            repaint();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private JPanel crearPanelReservaMejorado(JLabel imagen, String tipoEstancia, String direccion, double coste, Date fechaSolicitud, Date fechaFinReserva, JButton editarReservaButton) {
        JPanel panelReserva = new JPanel();
        panelReserva.setLayout(new BorderLayout());
        panelReserva.setBackground(Color.WHITE);
        panelReserva.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // JPanel para la información de la reserva
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS)); // Diseño en una columna
        infoPanel.setBackground(Color.WHITE);

        // JLabel para el tipo de estancia
        JLabel tipoEstanciaLabel = new JLabel("<html><b>Tipo de estancia:</b> " + tipoEstancia + "</html>");
        tipoEstanciaLabel.setFont(new Font("Century Gothic", Font.PLAIN, 14));
        infoPanel.add(tipoEstanciaLabel);

        // JLabel para la dirección
        JLabel direccionLabel = new JLabel("<html><b>Dirección:</b> " + direccion + "</html>");
        direccionLabel.setFont(new Font("Century Gothic", Font.PLAIN, 14));
        infoPanel.add(direccionLabel);

        // JLabel para el coste de la reserva
        JLabel costeReservaLabel = new JLabel("<html><b>Coste de reserva:</b> " + coste + "</html>");
        costeReservaLabel.setFont(new Font("Century Gothic", Font.PLAIN, 14));
        infoPanel.add(costeReservaLabel);

        // JLabel para la fecha de solicitud
        JLabel fechaSolicitudLabel = new JLabel("<html><b>Fecha de Solicitud:</b></html>");
        fechaSolicitudLabel.setFont(new Font("Century Gothic", Font.PLAIN, 14));
        infoPanel.add(fechaSolicitudLabel);

        // JDateChooser para la fecha de solicitud
        JDateChooser fechaSolicitudChooser = new JDateChooser(fechaSolicitud);
        fechaSolicitudChooser.setDateFormatString("dd-MM-yyyy"); // Puedes establecer el formato aquí
        infoPanel.add(fechaSolicitudChooser);

        // JLabel para la fecha de fin de reserva
        JLabel fechaFinReservaLabel = new JLabel("<html><b>Fecha de Fin Reserva:</b></html>");
        fechaFinReservaLabel.setFont(new Font("Century Gothic", Font.PLAIN, 14));
        infoPanel.add(fechaFinReservaLabel);

        // JDateChooser para la fecha de fin de reserva
        JDateChooser fechaFinReservaChooser = new JDateChooser(fechaFinReserva);
        fechaFinReservaChooser.setDateFormatString("dd-MM-yyyy"); // Puedes establecer el formato aquí
        infoPanel.add(fechaFinReservaChooser);

        // Botón "Editar Reserva" deshabilitado
        infoPanel.add(editarReservaButton);

        // Añadir elementos al panelReserva
        panelReserva.add(imagen, BorderLayout.WEST);
        panelReserva.add(infoPanel, BorderLayout.CENTER);

        return panelReserva;
    }

    private String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        return sdf.format(date);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        Logo = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btnActivos = new javax.swing.JButton();
        btnPasados = new javax.swing.JButton();
        btnCancelados = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        labelReservas = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        btnBuscar = new javax.swing.JLabel();
        btnReservas = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        panelActivos = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        imagenLabel = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JButton();
        fechaFinReserva = new com.toedter.calendar.JDateChooser();
        fechaSolicitud = new com.toedter.calendar.JDateChooser();
        costeReserva = new javax.swing.JLabel();
        direccion = new javax.swing.JLabel();
        tipoEstancia = new javax.swing.JLabel();
        panelPasados = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        panelCancelados = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 222, 89));
        jPanel2.setMinimumSize(new java.awt.Dimension(145, 105));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/coin1.png"))); // NOI18N
        jLabel6.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel6.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jLabel6.setIconTextGap(1);
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 33, -1));

        jLabel7.setText("0");
        jLabel7.setPreferredSize(new java.awt.Dimension(30, 20));
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 30, 36, 30));

        Logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Logo_BOOK4U.png"))); // NOI18N
        Logo.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        Logo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Logo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Logo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LogoMouseClicked(evt);
            }
        });
        jPanel2.add(Logo, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, -10, -1, -1));

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/notify.png"))); // NOI18N
        jLabel14.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel14.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jLabel14.setIconTextGap(1);
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 30, -1, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 440, 100));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setMinimumSize(new java.awt.Dimension(1000, 1000));
        jPanel3.setPreferredSize(new java.awt.Dimension(1000, 1000));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnActivos.setBackground(new java.awt.Color(255, 222, 89));
        btnActivos.setText("Activos");
        btnActivos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActivosActionPerformed(evt);
            }
        });
        jPanel3.add(btnActivos, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 110, 40));

        btnPasados.setBackground(new java.awt.Color(255, 222, 89));
        btnPasados.setText("Pasados");
        jPanel3.add(btnPasados, new org.netbeans.lib.awtextra.AbsoluteConstraints(165, 20, 110, 40));

        btnCancelados.setBackground(new java.awt.Color(255, 222, 89));
        btnCancelados.setText("Cancelados");
        jPanel3.add(btnCancelados, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 20, 110, 40));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 440, 60));

        jPanel4.setBackground(new java.awt.Color(255, 222, 89));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/perfil.png"))); // NOI18N
        jLabel27.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel27.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel27.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jLabel27.setIconTextGap(1);
        jPanel4.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 10, -1, -1));

        jLabel17.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Inicio");
        jLabel17.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel17.setPreferredSize(new java.awt.Dimension(30, 20));
        jLabel17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel17MouseClicked(evt);
            }
        });
        jPanel4.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 60, 23));

        jLabel18.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Favoritos");
        jLabel18.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel18.setPreferredSize(new java.awt.Dimension(30, 20));
        jPanel4.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 50, 80, 23));

        labelReservas.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        labelReservas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelReservas.setText("Reservas");
        labelReservas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        labelReservas.setPreferredSize(new java.awt.Dimension(30, 20));
        labelReservas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelReservasMouseClicked(evt);
            }
        });
        jPanel4.add(labelReservas, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 50, 60, 23));

        jLabel20.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Perfil");
        jLabel20.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel20.setPreferredSize(new java.awt.Dimension(30, 20));
        jPanel4.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 50, 90, 23));

        btnBuscar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/HomeUser.png"))); // NOI18N
        btnBuscar.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        btnBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnBuscar.setIconTextGap(1);
        btnBuscar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBuscarMouseClicked(evt);
            }
        });
        jPanel4.add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, -1, -1));

        btnReservas.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnReservas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/reservas.png"))); // NOI18N
        btnReservas.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        btnReservas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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
        jLabel31.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel31.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jLabel31.setIconTextGap(1);
        jPanel4.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, 40, 40));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 490, 440, 80));

        panelActivos.setBackground(new java.awt.Color(255, 255, 255));
        panelActivos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setBorder(null);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel6.add(imagenLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, -1, -1));

        btnCancelar.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        btnCancelar.setText("Cancelar");
        jPanel6.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 180, -1, -1));
        jPanel6.add(fechaFinReserva, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 150, -1));
        jPanel6.add(fechaSolicitud, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 150, -1));

        costeReserva.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        costeReserva.setText("costeReserva");
        jPanel6.add(costeReserva, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 110, -1, -1));

        direccion.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        direccion.setText("direccion");
        jPanel6.add(direccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, -1));

        tipoEstancia.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        tipoEstancia.setText("tipoEstancia");
        jPanel6.add(tipoEstancia, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, -1));

        jPanel5.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 290, 250));

        jScrollPane1.setViewportView(jPanel5);

        panelActivos.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, 280, 380));

        jPanel1.add(panelActivos, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 440, 330));

        panelPasados.setBackground(new java.awt.Color(255, 255, 255));
        panelPasados.setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));
        panelPasados.setMinimumSize(new java.awt.Dimension(326, 155));
        panelPasados.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setText("Reservas Pasadas");
        panelPasados.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 70, -1, -1));

        jPanel1.add(panelPasados, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 440, 330));

        panelCancelados.setBackground(new java.awt.Color(255, 255, 255));
        panelCancelados.setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));
        panelCancelados.setMinimumSize(new java.awt.Dimension(326, 155));
        panelCancelados.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setText("Reservas Canceladas");
        panelCancelados.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 70, -1, -1));

        jPanel1.add(panelCancelados, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 440, 330));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 440, 570));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnActivosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActivosActionPerformed
        mostrarReservasActivas();
    }//GEN-LAST:event_btnActivosActionPerformed

    private void btnReservasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReservasMouseClicked

        MevesReservesPage mevesReservesPage = new MevesReservesPage(idUser, creditosUser);

        mevesReservesPage.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_btnReservasMouseClicked

    private void labelReservasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelReservasMouseClicked
        MevesReservesPage mevesReservesPage = new MevesReservesPage(idUser,creditosUser);

        mevesReservesPage.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_labelReservasMouseClicked

    private void btnBuscarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBuscarMouseClicked
        MainPage MainPage = new MainPage(idUser);

        MainPage.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_btnBuscarMouseClicked

    private void jLabel17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MouseClicked
        MainPage MainPage = new MainPage(idUser);

        MainPage.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_jLabel17MouseClicked

    private void LogoMouseClicked(java.awt.event.MouseEvent evt) {
        MainPage mainPage = new MainPage(idUser);
        mainPage.setVisible(true);
        setVisible(false);
    }

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {
        CreditPage creditPage = new CreditPage(idUser);
        creditPage.setVisible(true);
        this.dispose();   
    }
    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {
        
    }

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
            java.util.logging.Logger.getLogger(MevesReservesPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MevesReservesPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MevesReservesPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MevesReservesPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MevesReservesPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Logo;
    private javax.swing.JButton btnActivos;
    private javax.swing.JLabel btnBuscar;
    private javax.swing.JButton btnCancelados;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnPasados;
    private javax.swing.JLabel btnReservas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel costeReserva;
    private javax.swing.JLabel direccion;
    private com.toedter.calendar.JDateChooser fechaFinReserva;
    private com.toedter.calendar.JDateChooser fechaSolicitud;
    private javax.swing.JLabel imagenLabel;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelReservas;
    private javax.swing.JPanel panelActivos;
    private javax.swing.JPanel panelCancelados;
    private javax.swing.JPanel panelPasados;
    private javax.swing.JLabel tipoEstancia;
    // End of variables declaration//GEN-END:variables
}
