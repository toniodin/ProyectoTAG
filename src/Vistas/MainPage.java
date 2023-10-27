/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vistas;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author alumnat
 */
public class MainPage extends javax.swing.JFrame {

    /**
     * Creates new form MainPage
     */
    public MainPage() {
        initComponents();
        
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
        jLabel16 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
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
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Para_ti_uwu.PNG"))); // NOI18N
        jLabel16.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel16.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jLabel16.setIconTextGap(1);
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 270, 220, 210));

        jPanel6.setBackground(new java.awt.Color(255, 222, 89));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextField1.setText("Introduce el destino");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jPanel6.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(55, 16, 205, 33));

        jTextField2.setText("Introduce fechas");
        jPanel6.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(55, 61, 205, 33));

        jButton1.setText("Buscar");
        jPanel6.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(86, 107, 100, 29));

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/calendario.png"))); // NOI18N
        jLabel6.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel6.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jLabel6.setIconTextGap(1);
        jPanel6.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 64, 33, -1));

        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/busqueda_little.png"))); // NOI18N
        jLabel15.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel15.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jLabel15.setIconTextGap(1);
        jPanel6.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 16, 33, -1));

        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 120, 280, 150));

        jPanel4.setBackground(new java.awt.Color(255, 222, 89));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/perfil.png"))); // NOI18N
        jLabel27.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel27.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jLabel27.setIconTextGap(1);
        jPanel4.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 10, -1, -1));

        jLabel17.setText("Buscar");
        jLabel17.setPreferredSize(new java.awt.Dimension(30, 20));
        jPanel4.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, 35, 23));

        jLabel18.setText("Favoritos");
        jLabel18.setPreferredSize(new java.awt.Dimension(30, 20));
        jPanel4.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 50, 48, 23));

        jLabel19.setText("Reservas");
        jLabel19.setPreferredSize(new java.awt.Dimension(30, 20));
        jPanel4.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 50, 45, 23));

        jLabel20.setText("Perfil");
        jLabel20.setPreferredSize(new java.awt.Dimension(30, 20));
        jPanel4.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 50, -1, 23));

        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/busqueda.png"))); // NOI18N
        jLabel29.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel29.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jLabel29.setIconTextGap(1);
        jPanel4.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, -1, -1));

        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/reservas.png"))); // NOI18N
        jLabel30.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel30.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jLabel30.setIconTextGap(1);
        jPanel4.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 10, 40, 40));

        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/favoritos.png"))); // NOI18N
        jLabel31.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel31.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jLabel31.setIconTextGap(1);
        jPanel4.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, 40, 40));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 490, 450, 80));

        jPanel2.setBackground(new java.awt.Color(255, 222, 89));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/coin1.png"))); // NOI18N
        jLabel5.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jLabel5.setIconTextGap(1);
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 20, 33, -1));

        jLabel1.setText("0");
        jLabel1.setPreferredSize(new java.awt.Dimension(30, 20));
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(55, 20, 36, 30));

        jPanel5.setBackground(new java.awt.Color(255, 222, 89));

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/casas_rurales.png"))); // NOI18N
        jLabel8.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel8.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jLabel8.setIconTextGap(1);

        jLabel3.setText("Casas rurales");
        jLabel3.setPreferredSize(new java.awt.Dimension(30, 20));

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/apartamentos.png"))); // NOI18N
        jLabel9.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel9.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jLabel9.setIconTextGap(1);

        jLabel4.setText("Apartamentos");
        jLabel4.setPreferredSize(new java.awt.Dimension(30, 20));

        jLabel7.setText("Hoteles");
        jLabel7.setPreferredSize(new java.awt.Dimension(30, 20));

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Hoteles.png"))); // NOI18N
        jLabel10.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel10.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jLabel10.setIconTextGap(1);

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/apartahoteles.png"))); // NOI18N
        jLabel11.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel11.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jLabel11.setIconTextGap(1);

        jLabel12.setText("Apartahoteles");
        jLabel12.setPreferredSize(new java.awt.Dimension(30, 20));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 62, 450, -1));

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Logo_BOOK4U_Little.png"))); // NOI18N
        jLabel13.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel13.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jLabel13.setIconTextGap(1);
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(175, 6, -1, -1));

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/notify.png"))); // NOI18N
        jLabel14.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel14.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jLabel14.setIconTextGap(1);
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 20, -1, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 450, 120));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 440, 570));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

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
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
