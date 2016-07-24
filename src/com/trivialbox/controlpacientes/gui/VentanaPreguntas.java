package com.trivialbox.controlpacientes.gui;

import java.beans.PropertyVetoException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author root
 */
public class VentanaPreguntas extends javax.swing.JFrame {
    private VentanaPreguntaTextual preguntaTextual;
    private VentanaPreguntaNumerica preguntaNumerica;
    private VentanaPreguntaBooleana preguntaBooleana;
    private VentanaPreguntaFecha pregunraFecha;
    private VentanaPreguntaHora preguntaHora;
    
    private final MensajeHolder mensajeHolder = new MensajeHolder();
    private final DisenioHolder disenioHolder = new DisenioHolder();
    /**
     * Creates new form VentanaPreguntas
     */
    public VentanaPreguntas() {
        initComponents();
        iniciarHolder();
    }
    
    private void iniciarHolder(){
        
        mensajeHolder.setCedula("Nombre Formulario");
        disenioHolder.mensaje(textNombreEncuesta, mensajeHolder.getCedula(), 0);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        textNombreEncuesta = new javax.swing.JTextField();
        comboBoxTipoPregunta = new javax.swing.JComboBox<>();
        ventanaPregunta = new javax.swing.JDesktopPane();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        textNombreEncuesta.setBackground(new java.awt.Color(223, 216, 216));
        textNombreEncuesta.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                textNombreEncuestaFocusLost(evt);
            }
        });
        textNombreEncuesta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textNombreEncuestaMouseClicked(evt);
            }
        });
        textNombreEncuesta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textNombreEncuestaActionPerformed(evt);
            }
        });

        comboBoxTipoPregunta.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pregunta Texto", "Pregunta Numérica", "Pregunta Booleana", "Pregunta Fecha", "Pregunta Hora", "Pregunta Opción multiple" }));
        comboBoxTipoPregunta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxTipoPreguntaActionPerformed(evt);
            }
        });

        jButton2.setText("Agregar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Cantarell", 0, 16)); // NOI18N
        jButton1.setForeground(new java.awt.Color(1, 1, 1));
        jButton1.setText("Enviar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(textNombreEncuesta, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(174, 174, 174)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(comboBoxTipoPregunta, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ventanaPregunta, javax.swing.GroupLayout.PREFERRED_SIZE, 610, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(86, 86, 86)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textNombreEncuesta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addComponent(comboBoxTipoPregunta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ventanaPregunta, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void textNombreEncuestaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textNombreEncuestaFocusLost
        disenioHolder.mensaje(textNombreEncuesta, mensajeHolder.getCedula(),
            textNombreEncuesta.getText().trim().length());
    }//GEN-LAST:event_textNombreEncuestaFocusLost

    private void textNombreEncuestaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textNombreEncuestaMouseClicked
        disenioHolder.click(textNombreEncuesta, mensajeHolder.getCedula());
    }//GEN-LAST:event_textNombreEncuestaMouseClicked

    private void textNombreEncuestaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textNombreEncuestaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textNombreEncuestaActionPerformed

    private void comboBoxTipoPreguntaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxTipoPreguntaActionPerformed
        String textComboBoxType = this.comboBoxTipoPregunta.getSelectedItem().toString();
        this.ventanaPregunta.removeAll();
        try{
            switch (textComboBoxType){
                case "Pregunta Texto":
                preguntaTextual = new VentanaPreguntaTextual();
                this.ventanaPregunta.add(preguntaTextual);
                preguntaTextual.setVisible(true);
                preguntaTextual.setMaximum(true);
                break;
                case "Pregunta Numérica":
                preguntaNumerica = new VentanaPreguntaNumerica();
                this.ventanaPregunta.add(preguntaNumerica);
                preguntaNumerica.setVisible(true);
                preguntaNumerica.setMaximum(true);
                break;
                case "Pregunta Booleana":
                preguntaBooleana = new VentanaPreguntaBooleana();
                this.ventanaPregunta.add(preguntaBooleana);
                preguntaBooleana.setVisible(true);
                preguntaBooleana.setMaximum(true);
                break;
                case "Pregunta Fecha":
                pregunraFecha = new VentanaPreguntaFecha();
                this.ventanaPregunta.add(pregunraFecha);
                pregunraFecha.setVisible(true);
                pregunraFecha.setMaximum(true);
                break;
                case "Pregunta Hora":
                preguntaHora = new VentanaPreguntaHora();
                this.ventanaPregunta.add(preguntaHora);
                preguntaHora.setVisible(true);
                preguntaHora.setMaximum(true);
                break;
                case "Pregunta Opción multiple":
                System.out.println("multiple");
                break;
            }
        }catch (PropertyVetoException ex) {
            Logger.getLogger(VentanaPreguntas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_comboBoxTipoPreguntaActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        VentanaPreguntaNumerica pregunta = new VentanaPreguntaNumerica();
        this.ventanaPregunta.add(pregunta);

        try {
            pregunta.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(VentanaPreguntas.class.getName()).log(Level.SEVERE, null, ex);
        }
        pregunta.setVisible(true);
        System.out.println("hola mundo");
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(VentanaPreguntas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaPreguntas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaPreguntas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaPreguntas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaPreguntas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> comboBoxTipoPregunta;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JTextField textNombreEncuesta;
    private javax.swing.JDesktopPane ventanaPregunta;
    // End of variables declaration//GEN-END:variables
}
