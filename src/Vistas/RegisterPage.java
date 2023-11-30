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

        ImageIcon icon = new ImageIcon(getClass().getResource("/imagenes/icono.png")); // Esto es para cambiar el icono de la app
        Image image = icon.getImage();
        setIconImage(image);
        jScrollPane1.getVerticalScrollBar().setUnitIncrement(50); //Incrementar la velocidad de la ruda del raton desplazamiento
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
        jLabel10 = new javax.swing.JLabel();
        inputPass1 = new javax.swing.JPasswordField();
        inputRepitaPass = new javax.swing.JPasswordField();
        mostrarContraseñaRepetida = new javax.swing.JButton();
        mostrarContraseña1 = new javax.swing.JButton();

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
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
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

        inputEmail.setForeground(new java.awt.Color(153, 153, 153));
        inputEmail.setText("Indica tu correo electrónico");
        inputEmail.setToolTipText("");
        inputEmail.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        inputEmail.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        inputEmail.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                inputEmailFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                inputEmailFocusLost(evt);
            }
        });
        inputEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputEmailActionPerformed(evt);
            }
        });
        jPanel2.add(inputEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, 344, 40));

        jLabel4.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Repita la contraseña");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 320, -1, -1));

        jLabel3.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Nombre");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 410, -1, -1));

        inputNombre.setForeground(new java.awt.Color(153, 153, 153));
        inputNombre.setText("Indica tu nombre");
        inputNombre.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        inputNombre.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                inputNombreFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                inputNombreFocusLost(evt);
            }
        });
        jPanel2.add(inputNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 440, 344, 40));

        jLabel5.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Apellido 1");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 500, -1, -1));

        inputApellido1.setForeground(new java.awt.Color(153, 153, 153));
        inputApellido1.setText("Indica tu apellido");
        inputApellido1.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        inputApellido1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                inputApellido1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                inputApellido1FocusLost(evt);
            }
        });
        inputApellido1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputApellido1ActionPerformed(evt);
            }
        });
        jPanel2.add(inputApellido1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 530, 344, 40));

        jLabel6.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Apellido 2");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 590, -1, -1));

        inputApellido2.setForeground(new java.awt.Color(153, 153, 153));
        inputApellido2.setText("Indica tu segundo apellido");
        inputApellido2.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        inputApellido2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                inputApellido2FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                inputApellido2FocusLost(evt);
            }
        });
        jPanel2.add(inputApellido2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 620, 344, 40));

        jLabel7.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("DNI");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 680, -1, -1));

        inputDNI.setForeground(new java.awt.Color(153, 153, 153));
        inputDNI.setText("Indica tu dni");
        inputDNI.setToolTipText("");
        inputDNI.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        inputDNI.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                inputDNIFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                inputDNIFocusLost(evt);
            }
        });
        jPanel2.add(inputDNI, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 710, 344, 40));

        jLabel8.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Domicilio");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 770, -1, -1));

        inputDomicilio.setForeground(new java.awt.Color(153, 153, 153));
        inputDomicilio.setText("Indica tu domicilio");
        inputDomicilio.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        inputDomicilio.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                inputDomicilioFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                inputDomicilioFocusLost(evt);
            }
        });
        jPanel2.add(inputDomicilio, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 800, 344, 40));

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
        jPanel2.add(registerButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 910, 344, 46));

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

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 840, 440, 60));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new java.awt.FlowLayout(1, 0, 15));
        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 960, 440, 30));

        jLabel10.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Contraseña");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 230, -1, -1));

        inputPass1.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        inputPass1.setForeground(new java.awt.Color(153, 153, 153));
        inputPass1.setText("Indica tu contraseña");
        inputPass1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                inputPass1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                inputPass1FocusLost(evt);
            }
        });
        jPanel2.add(inputPass1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 260, 344, 40));

        inputRepitaPass.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        inputRepitaPass.setForeground(new java.awt.Color(153, 153, 153));
        inputRepitaPass.setText("Repita la contraseña");
        inputRepitaPass.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                inputRepitaPassFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                inputRepitaPassFocusLost(evt);
            }
        });
        jPanel2.add(inputRepitaPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 350, 344, 40));

        mostrarContraseñaRepetida.setText("jButton1");
        mostrarContraseñaRepetida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mostrarContraseñaRepetidaActionPerformed(evt);
            }
        });
        jPanel2.add(mostrarContraseñaRepetida, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 360, 30, -1));

        mostrarContraseña1.setText("jButton1");
        mostrarContraseña1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mostrarContraseña1ActionPerformed(evt);
            }
        });
        jPanel2.add(mostrarContraseña1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 270, 30, -1));

        jScrollPane1.setViewportView(jPanel2);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 440, 470));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void registerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerButtonActionPerformed
        String email = inputEmail.getText();
        String passwordRepeat = inputRepitaPass.getText();
        String password = inputPass1.getText();
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

        // Verifica el formato del email
        if (!isValidEmail(email)) {
            JOptionPane.showMessageDialog(this, "El formato del email no es válido.");
            return;
        }

        // Verifica si el email ya existe en la base de datos
        if (emailExists(email)) {
            JOptionPane.showMessageDialog(this, "El email ya está registrado.");
        } else {
            // Verifica la coincidencia de las contraseñas
            if (!password.equals(passwordRepeat)) {
                JOptionPane.showMessageDialog(this, "Las contraseñas no coinciden.");
                return;
            }

            // Verifica el formato del DNI
            if (!isValidDNI(dni)) {
                JOptionPane.showMessageDialog(this, "El formato del DNI no es válido.");
                return;
            }

            // Inserta el nuevo usuario en la base de datos
            if (insertUser(email, password, nombre, apellido1, apellido2, dni, domicilio)) {
                JOptionPane.showMessageDialog(this, "Registro exitoso.");
                LoginPage LoginPage = new LoginPage();
                LoginPage.setVisible(true);
                setVisible(false);
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

    // Método para verificar el formato del email
    private boolean isValidEmail(String email) {
        // Puedes implementar una lógica más detallada para validar el formato del email
        return email.matches("\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b");
    }

    // Método para verificar el formato del DNI
    private boolean isValidDNI(String dni) {
        // Verifica que tenga exactamente 9 caracteres (8 dígitos y 1 letra)
        if (dni.length() != 9) {
            return false;
        }

        // Extrae los primeros 8 caracteres (dígitos)
        String numeros = dni.substring(0, 8);

        // Extrae el último caracter (letra)
        String letra = dni.substring(8);

        // Verifica que los primeros 8 caracteres sean dígitos
        if (!numeros.matches("\\d{8}")) {
            return false;
        }

        // Verifica que el último caracter sea una letra (mayúscula o minúscula)
        if (!letra.matches("[a-zA-Z]")) {
            return false;
        }

        // Si pasa todas las validaciones, retorna true
        return true;
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
        volverLogin.setForeground(new Color(255, 204, 0));
    }//GEN-LAST:event_volverLoginMouseEntered

    private void volverLoginMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_volverLoginMouseExited
        volverLogin.setForeground(new Color(0, 0, 255));
    }//GEN-LAST:event_volverLoginMouseExited

    private void inputApellido1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputApellido1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputApellido1ActionPerformed

    private void inputEmailFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputEmailFocusGained
        if (inputEmail.getText().equals("Indica tu correo electrónico")) {
            inputEmail.setText("");
            inputEmail.setForeground(new Color(0, 0, 0));
        }
    }//GEN-LAST:event_inputEmailFocusGained

    private void inputEmailFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputEmailFocusLost
        if (inputEmail.getText().equals("")) {
            inputEmail.setText("Indica tu correo electrónico");
            inputEmail.setForeground(new Color(153, 153, 153));
        }
    }//GEN-LAST:event_inputEmailFocusLost

    private void inputNombreFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputNombreFocusGained
        if (inputNombre.getText().equals("Indica tu nombre")) {
            inputNombre.setText("");
            inputNombre.setForeground(new Color(0, 0, 0));
        }
    }//GEN-LAST:event_inputNombreFocusGained

    private void inputNombreFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputNombreFocusLost
        if (inputNombre.getText().equals("")) {
            inputNombre.setText("Indica tu nombre");
            inputNombre.setForeground(new Color(153, 153, 153));
        }
    }//GEN-LAST:event_inputNombreFocusLost

    private void inputApellido1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputApellido1FocusGained
        if (inputApellido1.getText().equals("Indica tu apellido")) {
            inputApellido1.setText("");
            inputApellido1.setForeground(new Color(0, 0, 0));
        }
    }//GEN-LAST:event_inputApellido1FocusGained

    private void inputApellido1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputApellido1FocusLost
        if (inputApellido1.getText().equals("")) {
            inputApellido1.setText("Indica tu apellido");
            inputApellido1.setForeground(new Color(153, 153, 153));
        }
    }//GEN-LAST:event_inputApellido1FocusLost

    private void inputApellido2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputApellido2FocusGained
        if (inputApellido2.getText().equals("Indica tu segundo apellido")) {
            inputApellido2.setText("");
            inputApellido2.setForeground(new Color(0, 0, 0));
        }
    }//GEN-LAST:event_inputApellido2FocusGained

    private void inputApellido2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputApellido2FocusLost
        if (inputApellido2.getText().equals("")) {
            inputApellido2.setText("Indica tu segundo apellido");
            inputApellido2.setForeground(new Color(153, 153, 153));
        }
    }//GEN-LAST:event_inputApellido2FocusLost

    private void inputDNIFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputDNIFocusGained
        if (inputDNI.getText().equals("Indica tu dni")) {
            inputDNI.setText("");
            inputDNI.setForeground(new Color(0, 0, 0));
        }
    }//GEN-LAST:event_inputDNIFocusGained

    private void inputDNIFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputDNIFocusLost
        if (inputDNI.getText().equals("")) {
            inputDNI.setText("Indica tu dni");
            inputDNI.setForeground(new Color(153, 153, 153));
        }
    }//GEN-LAST:event_inputDNIFocusLost

    private void inputDomicilioFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputDomicilioFocusGained
        if (inputDomicilio.getText().equals("Indica tu domicilio")) {
            inputDomicilio.setText("");
            inputDomicilio.setForeground(new Color(0, 0, 0));
        }
    }//GEN-LAST:event_inputDomicilioFocusGained

    private void inputDomicilioFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputDomicilioFocusLost
        if (inputDomicilio.getText().equals("")) {
            inputDomicilio.setText("Indica tu domicilio");
            inputDomicilio.setForeground(new Color(153, 153, 153));
        }
    }//GEN-LAST:event_inputDomicilioFocusLost

    private void inputPass1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputPass1FocusGained
        if (inputPass1.getText().equals("Indica tu contraseña")) {
            inputPass1.setText("");
            inputPass1.setForeground(new Color(0, 0, 0));
        }
    }//GEN-LAST:event_inputPass1FocusGained

    private void inputPass1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputPass1FocusLost
        if (inputPass1.getText().equals("")) {
            inputPass1.setText("Indica tu contraseña");
            inputPass1.setForeground(new Color(153, 153, 153));
        }
    }//GEN-LAST:event_inputPass1FocusLost

    private void inputRepitaPassFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputRepitaPassFocusGained
        if (inputRepitaPass.getText().equals("Repita la contraseña")) {
            inputRepitaPass.setText("");
            inputRepitaPass.setForeground(new Color(0, 0, 0));
        }
    }//GEN-LAST:event_inputRepitaPassFocusGained

    private void inputRepitaPassFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputRepitaPassFocusLost
        if (inputRepitaPass.getText().equals("")) {
            inputRepitaPass.setText("Repita la contraseña");
            inputRepitaPass.setForeground(new Color(153, 153, 153));
        }
    }//GEN-LAST:event_inputRepitaPassFocusLost

    private void mostrarContraseñaRepetidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mostrarContraseñaRepetidaActionPerformed
        if (mostrarContraseñaRepetida.getText().equals("Mostrar")) {
            mostrarContraseñaRepetida.setText("Ocultar");
            inputRepitaPass.setEchoChar((char) 0);
        } else {
            mostrarContraseñaRepetida.setText("Mostrar");
            inputRepitaPass.setEchoChar('*');
        }
    }//GEN-LAST:event_mostrarContraseñaRepetidaActionPerformed

    private void mostrarContraseña1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mostrarContraseña1ActionPerformed
        if (mostrarContraseñaRepetida.getText().equals("Mostrar")) {
            mostrarContraseñaRepetida.setText("Ocultar");
            inputPass1.setEchoChar((char) 0); // Mostrar texto sin ocultar
        } else {
            mostrarContraseñaRepetida.setText("Mostrar");
            inputPass1.setEchoChar('*'); // Ocultar texto
        }
    }//GEN-LAST:event_mostrarContraseña1ActionPerformed

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
    private javax.swing.JPasswordField inputPass1;
    private javax.swing.JPasswordField inputRepitaPass;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
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
    private javax.swing.JButton mostrarContraseña1;
    private javax.swing.JButton mostrarContraseñaRepetida;
    private javax.swing.JButton registerButton;
    private javax.swing.JLabel volverLogin;
    // End of variables declaration//GEN-END:variables
}
