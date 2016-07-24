package com.trivialbox.controlpacientes.gui;


public class Login extends javax.swing.JFrame {
    private final MensajeHolder mensajeHolder = new MensajeHolder();
    private final DisenioHolder disenioHolder = new DisenioHolder();
    /**
     * Creates new form Login
     */
    public Login() {
        initComponents();
        
        iniciarHolder();
    }
    private void iniciarHolder(){
        disenioHolder.mensaje(textCedula, mensajeHolder.getCedula(), 0);
        disenioHolder.mensaje(textPassword, mensajeHolder.getPassword(), 0);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBox1 = new javax.swing.JComboBox<>();
        textCedula = new javax.swing.JTextField();
        textPassword = new javax.swing.JPasswordField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnAceptar = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Paciente", "Usuario" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 100, 240, -1));

        textCedula.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                textCedulaFocusLost(evt);
            }
        });
        textCedula.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textCedulaMouseClicked(evt);
            }
        });
        getContentPane().add(textCedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 140, 243, -1));

        textPassword.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                textPasswordFocusLost(evt);
            }
        });
        textPassword.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textPasswordMouseClicked(evt);
            }
        });
        getContentPane().add(textPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 170, 243, -1));

        jLabel2.setFont(new java.awt.Font("Cantarell", 0, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(254, 254, 254));
        jLabel2.setText("Trivial Forms");
        jLabel2.setToolTipText("");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 20, 280, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/trivialbox/controlpacientes/imagenes/logo_solo_small.png"))); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, -1, -1));

        btnAceptar.setText("Aceptar");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });
        getContentPane().add(btnAceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 220, 110, 33));

        jButton2.setText("Cancelar");
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 220, 110, 33));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/trivialbox/controlpacientes/imagenes/7uHVs34 - Imgur.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void textCedulaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textCedulaFocusLost
        disenioHolder.mensaje(textCedula, mensajeHolder.getCedula(), textCedula.getText().trim().length());

    }//GEN-LAST:event_textCedulaFocusLost

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void textCedulaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textCedulaMouseClicked
        disenioHolder.click(textCedula, mensajeHolder.getCedula());
    }//GEN-LAST:event_textCedulaMouseClicked

    private void textPasswordFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textPasswordFocusLost
        disenioHolder.mensaje(textPassword, mensajeHolder.getPassword(), textPassword.getPassword().length);
    }//GEN-LAST:event_textPasswordFocusLost

    private void textPasswordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textPasswordMouseClicked
        disenioHolder.click(textPassword, mensajeHolder.getPassword());
        System.out.println(textPassword.getPassword());
    }//GEN-LAST:event_textPasswordMouseClicked

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        VentanaPreguntas ventanaRespuestas = new VentanaPreguntas();
        ventanaRespuestas.setVisible(true);
    }//GEN-LAST:event_btnAceptarActionPerformed

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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField textCedula;
    private javax.swing.JPasswordField textPassword;
    // End of variables declaration//GEN-END:variables
}
