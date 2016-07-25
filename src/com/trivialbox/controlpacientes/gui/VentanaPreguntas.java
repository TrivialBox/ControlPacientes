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
    private VentanaPreguntaOpcional preguntaOpcional;
    
    static String nombreEncuesta;
    
    /**
     * Creates new form VentanaPreguntas
     * @param nombreEncuesta
     */
    public VentanaPreguntas(String nombreEncuesta) {
        initComponents();

        this.nombreEncuesta = nombreEncuesta;
        this.labelNombreEncuesta.setText(nombreEncuesta);
        inicioVentanaPregunta();
    }
    
    private void inicioVentanaPregunta() {
        VentanaIncioPreguntas ventana = new VentanaIncioPreguntas();
        this.ventanaPregunta.add(ventana);
        ventana.setVisible(true);
        try {
            ventana.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(VentanaPreguntas.class.getName()).log(Level.SEVERE, null, ex);
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

        comboBoxTipoPregunta = new javax.swing.JComboBox<String>();
        ventanaPregunta = new javax.swing.JDesktopPane();
        jButton2 = new javax.swing.JButton();
        labelNombreEncuesta = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        comboBoxTipoPregunta.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pregunta Texto", "Pregunta Numérica", "Pregunta Booleana", "Pregunta Fecha", "Pregunta Hora", "Pregunta Opción multiple" }));
        comboBoxTipoPregunta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxTipoPreguntaActionPerformed(evt);
            }
        });
        getContentPane().add(comboBoxTipoPregunta, new org.netbeans.lib.awtextra.AbsoluteConstraints(534, 100, 250, -1));
        getContentPane().add(ventanaPregunta, new org.netbeans.lib.awtextra.AbsoluteConstraints(174, 141, 610, 325));

        jButton2.setText("Agregar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(802, 141, -1, 30));

        labelNombreEncuesta.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        getContentPane().add(labelNombreEncuesta, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 308, 30));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/trivialbox/controlpacientes/imagenes/cEmdNf9 - Imgur.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 970, -1));

        jLabel2.setText("Trivial Box 2016");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 500, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
                preguntaOpcional = new VentanaPreguntaOpcional();
                this.ventanaPregunta.add(preguntaOpcional);
                preguntaOpcional.setVisible(true);
                preguntaOpcional.setMaximum(true);
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
                new VentanaPreguntas(nombreEncuesta).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> comboBoxTipoPregunta;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel labelNombreEncuesta;
    private javax.swing.JDesktopPane ventanaPregunta;
    // End of variables declaration//GEN-END:variables
}
