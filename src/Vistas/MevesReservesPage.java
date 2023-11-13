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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author bella
 */
public class MevesReservesPage extends javax.swing.JFrame {

    private final int idUser;
    private Conexion conexion;

    public MevesReservesPage(int idUser) {
        initComponents();
        System.out.println(idUser);
        this.setLocationRelativeTo(null); //Inicializa al centro de la pantalla

        ImageIcon icon = new ImageIcon(getClass().getResource("/imagenes/icono.png")); // Esto es para cambiar el icono de la app
        Image image = icon.getImage();
        setIconImage(image);
        this.idUser = idUser;

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

    private void mostrarReservasActivas() {
    // Realiza la consulta SQL con INNER JOIN para obtener la información necesaria
    String consulta = "SELECT r.tipo_estancia, r.direccion, ru.coste_reserva, ru.fecha_solicitud, ru.fecha_fin_reserva "
            + "FROM reservas r INNER JOIN reservas_usuarios ru ON r.id_reserva = ru.id_reserva "
            + "WHERE ru.id_usuario = ? AND ru.fecha_fin_reserva >= CURRENT_DATE";

    try (Connection connection = conexion.DatabaseConnection(); PreparedStatement statement = connection.prepareStatement(consulta)) {

        statement.setInt(1, idUser);
        ResultSet resultado = statement.executeQuery();

        StringBuilder resultados = new StringBuilder();
        String tipoEstanciaTexto = "";
        String direccionTexto = "";
        int costeReservaValor = 0;
        Date fechaSolicitudValor = null;
        Date fechaFinReservaValor = null;

        while (resultado.next()) {
            tipoEstanciaTexto = resultado.getString("tipo_estancia");
            direccionTexto = resultado.getString("direccion");
            costeReservaValor = resultado.getInt("coste_reserva");
            fechaSolicitudValor = resultado.getDate("fecha_solicitud");
            fechaFinReservaValor = resultado.getDate("fecha_fin_reserva");
            
            // Acumula los valores en el StringBuilder
            resultados.append("Tipo de estancia: ").append(tipoEstanciaTexto).append("\n");
            resultados.append("Dirección: ").append(direccionTexto).append("\n");
            resultados.append("Coste de reserva: ").append(costeReservaValor).append("\n\n");
            resultados.append(fechaSolicitudValor);
            
        }

        // Muestra los resultados acumulados en las etiquetas
        tipoEstancia.setText("<html><b>Tipo de estancia:</b><br>" + tipoEstanciaTexto + "</html>");
        direccion.setText("<html><b>Dirección:</b><br>" + direccionTexto + "</html>");
        costeReserva.setText("<html><b>Coste de reserva:</b> " + costeReservaValor + "</html>");
        fechaSolicitud.setDate(fechaSolicitudValor);
        fechaFinReserva.setDate(fechaFinReservaValor);

    } catch (Exception e) {
        e.printStackTrace();
    }
}


    private String formatDate(Date date) {
        // Formatea la fecha en el formato deseado
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        return sdf.format(date);
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
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
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
        tipoEstancia = new javax.swing.JLabel();
        direccion = new javax.swing.JLabel();
        costeReserva = new javax.swing.JLabel();
        fechaSolicitud = new com.toedter.calendar.JDateChooser();
        fechaFinReserva = new com.toedter.calendar.JDateChooser();
        panelPasados = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        panelCancelados = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 222, 89));
        jPanel2.setMinimumSize(new java.awt.Dimension(145, 105));
        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, -20));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Logo_BOOK4U.png"))); // NOI18N
        jLabel5.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel2.add(jLabel5);

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
        jLabel27.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jLabel27.setIconTextGap(1);
        jPanel4.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 10, -1, -1));

        jLabel17.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel17.setText("Buscar");
        jLabel17.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel17.setPreferredSize(new java.awt.Dimension(30, 20));
        jLabel17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel17MouseClicked(evt);
            }
        });
        jPanel4.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, 50, 23));

        jLabel18.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Favoritos");
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
        jLabel20.setPreferredSize(new java.awt.Dimension(30, 20));
        jPanel4.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 50, 90, 23));

        btnBuscar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/busqueda.png"))); // NOI18N
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
        jLabel31.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jLabel31.setIconTextGap(1);
        jPanel4.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, 40, 40));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 490, 440, 80));

        panelActivos.setBackground(new java.awt.Color(255, 255, 255));
        panelActivos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setBorder(null);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tipoEstancia.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        tipoEstancia.setText("tipoEstancia");
        jPanel5.add(tipoEstancia, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, -1, -1));

        direccion.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        direccion.setText("direccion");
        jPanel5.add(direccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, -1, -1));

        costeReserva.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        costeReserva.setText("costeReserva");
        jPanel5.add(costeReserva, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 90, -1, -1));
        jPanel5.add(fechaSolicitud, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 150, -1));
        jPanel5.add(fechaFinReserva, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 150, -1));

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

        MevesReservesPage mevesReservesPage = new MevesReservesPage(idUser);

        mevesReservesPage.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_btnReservasMouseClicked

    private void labelReservasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelReservasMouseClicked
        MevesReservesPage mevesReservesPage = new MevesReservesPage(idUser);

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
    private javax.swing.JButton btnActivos;
    private javax.swing.JLabel btnBuscar;
    private javax.swing.JButton btnCancelados;
    private javax.swing.JButton btnPasados;
    private javax.swing.JLabel btnReservas;
    private javax.swing.JLabel costeReserva;
    private javax.swing.JLabel direccion;
    private com.toedter.calendar.JDateChooser fechaFinReserva;
    private com.toedter.calendar.JDateChooser fechaSolicitud;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelReservas;
    private javax.swing.JPanel panelActivos;
    private javax.swing.JPanel panelCancelados;
    private javax.swing.JPanel panelPasados;
    private javax.swing.JLabel tipoEstancia;
    // End of variables declaration//GEN-END:variables
}
