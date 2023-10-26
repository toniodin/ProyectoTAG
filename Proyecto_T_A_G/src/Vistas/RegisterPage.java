/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vistas;

import bd.Conexion;
import java.awt.Image;
import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import javax.swing.JOptionPane;
import java.sql.Timestamp;
import javax.swing.ImageIcon;

/**
 *
 * @author Usuario
 */
public class RegisterPage extends javax.swing.JFrame {

    private Conexion conexion;
    
    public RegisterPage() {
        initComponents();
        conexion = new Conexion();
        
        this.setLocationRelativeTo(null); //Inicializa al centro de la pantalla
        
        ImageIcon icon = new ImageIcon(getClass().getResource("/imagenes/Logo_Book4u.png")); // Esto es para cambiar el icono de la app
        Image image = icon.getImage();
        setIconImage(image);
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
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        label1 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        inputEmail = new javax.swing.JFormattedTextField();
        jLabel4 = new javax.swing.JLabel();
        inputPass = new javax.swing.JFormattedTextField();
        jLabel3 = new javax.swing.JLabel();
        inputNombre = new javax.swing.JFormattedTextField();
        jLabel5 = new javax.swing.JLabel();
        inputApellido1 = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        inputApellido2 = new javax.swing.JFormattedTextField();
        jLabel7 = new javax.swing.JLabel();
        inputDNI = new javax.swing.JFormattedTextField();
        jLabel8 = new javax.swing.JLabel();
        inputDomicilio = new javax.swing.JFormattedTextField();
        registerButton = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        volverLogin = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Registro");
        setMinimumSize(new java.awt.Dimension(440, 570));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 222, 89));
        jPanel1.setMinimumSize(new java.awt.Dimension(145, 105));
        jPanel1.setPreferredSize(new java.awt.Dimension(145, 105));
        jPanel1.setLayout(new java.awt.FlowLayout(1, 0, -20));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Logo_BOOK4U.png"))); // NOI18N
        jLabel9.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(jLabel9);

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 440, 100));

        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));
        jScrollPane1.setMinimumSize(new java.awt.Dimension(440, 470));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(440, 470));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        label1.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        label1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label1.setText("Registro");
        label1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        label1.setMaximumSize(new java.awt.Dimension(49, 24));
        label1.setMinimumSize(new java.awt.Dimension(49, 24));
        label1.setPreferredSize(new java.awt.Dimension(47, 23));
        jPanel2.add(label1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, 80, 24));
        label1.getAccessibleContext().setAccessibleParent(jPanel2);

        jLabel1.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel1.setText("<html>Rellena los campos para crear un usuario</html>");
        jLabel1.setFocusable(false);
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, 346, -1));

        jLabel2.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("E-mail");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, -1, -1));

        inputEmail.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        inputEmail.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        inputEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputEmailActionPerformed(evt);
            }
        });
        jPanel2.add(inputEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, 344, 40));

        jLabel4.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Password");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 230, -1, -1));

        inputPass.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jPanel2.add(inputPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 260, 344, 40));

        jLabel3.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Nombre");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 320, -1, -1));

        inputNombre.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jPanel2.add(inputNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 350, 344, 40));

        jLabel5.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Apellido 1");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 410, -1, -1));

        inputApellido1.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jPanel2.add(inputApellido1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 440, 344, 40));

        jLabel6.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Apellido 2");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 500, -1, -1));

        inputApellido2.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jPanel2.add(inputApellido2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 530, 344, 40));

        jLabel7.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("DNI");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 590, -1, -1));

        inputDNI.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jPanel2.add(inputDNI, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 620, 344, 40));

        jLabel8.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Domicilio");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 680, -1, -1));

        inputDomicilio.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jPanel2.add(inputDomicilio, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 710, 344, 40));

        registerButton.setBackground(new java.awt.Color(255, 222, 89));
        registerButton.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        registerButton.setText("Registrarse");
        registerButton.setBorder(null);
        registerButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        registerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerButtonActionPerformed(evt);
            }
        });
        jPanel2.add(registerButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 810, 344, 46));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new java.awt.FlowLayout(1, 0, 15));

        volverLogin.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        volverLogin.setForeground(new java.awt.Color(0, 0, 255));
        volverLogin.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        volverLogin.setText("<html><u>Ya tengo cuenta</u></html>");
        volverLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        volverLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                volverLoginMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                volverLoginMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                volverLoginMouseExited(evt);
            }
        });
        jPanel3.add(volverLogin);

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 750, 440, 60));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new java.awt.FlowLayout(1, 0, 15));
        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 850, 440, 30));

        jScrollPane1.setViewportView(jPanel2);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 440, 470));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void registerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerButtonActionPerformed
        String email = inputEmail.getText();
        String password = inputPass.getText();
        String nombre = inputNombre.getText();
        String apellido1 = inputApellido1.getText();
        String apellido2 = inputApellido2.getText();
        String dni = inputDNI.getText();
        String domicilio = inputDomicilio.getText();

        // Verifica que no haya campos nulos
        if (email.isEmpty() || password.isEmpty() || nombre.isEmpty() || apellido1.isEmpty() || dni.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.");
            return;
        }

        // Verifica si el email ya existe en la base de datos
        if (emailExists(email)) {
            JOptionPane.showMessageDialog(this, "El email ya está registrado.");
        } else {
            // Inserta el nuevo usuario en la base de datos
            if (insertUser(email, password, nombre, apellido1, apellido2, dni, domicilio)) {
                JOptionPane.showMessageDialog(this, "Registro exitoso.");
            } else {
                JOptionPane.showMessageDialog(this, "Ocurrió un error al registrar el usuario.");
            }
        }
    }//GEN-LAST:event_registerButtonActionPerformed

    private boolean emailExists(String email) {
        Connection connection = conexion.DatabaseConnection();
        try {
            String consulta = "SELECT Email FROM usuarios WHERE Email = ?";
            PreparedStatement statement = connection.prepareStatement(consulta);
            statement.setString(1, email);
            ResultSet resultado = statement.executeQuery();
            return resultado.next();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean insertUser(String email, String password, String nombre, String apellido1, String apellido2, String dni, String domicilio) {
        Connection connection = conexion.DatabaseConnection();
        try {
            String consulta = "INSERT INTO usuarios (Email, Password, Nombre, Apellido1, Apellido2, DNI, Domicilio, Fecha_alta) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(consulta);
            statement.setString(1, email);
            statement.setString(2, password);
            statement.setString(3, nombre);
            statement.setString(4, apellido1);
            statement.setString(5, apellido2);
            statement.setString(6, dni);
            statement.setString(7, domicilio);

            // Obten la fecha actual
            Date fechaActual = new Date();
            Timestamp fechaAlta = new Timestamp(fechaActual.getTime());
            statement.setTimestamp(8, fechaAlta); 
            
            return statement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    private void volverLoginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_volverLoginMouseClicked
        LoginPage LoginPage = new LoginPage();
        LoginPage.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_volverLoginMouseClicked

    private void inputEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputEmailActionPerformed

    private void volverLoginMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_volverLoginMouseEntered
        volverLogin.setForeground(new Color(255,204,0));
    }//GEN-LAST:event_volverLoginMouseEntered

    private void volverLoginMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_volverLoginMouseExited
        volverLogin.setForeground(new Color(0, 0, 255));
    }//GEN-LAST:event_volverLoginMouseExited

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
            java.util.logging.Logger.getLogger(RegisterPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegisterPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegisterPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegisterPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RegisterPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFormattedTextField inputApellido1;
    private javax.swing.JFormattedTextField inputApellido2;
    private javax.swing.JFormattedTextField inputDNI;
    private javax.swing.JFormattedTextField inputDomicilio;
    private javax.swing.JFormattedTextField inputEmail;
    private javax.swing.JFormattedTextField inputNombre;
    private javax.swing.JFormattedTextField inputPass;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel label1;
    private javax.swing.JButton registerButton;
    private javax.swing.JLabel volverLogin;
    // End of variables declaration//GEN-END:variables
}
