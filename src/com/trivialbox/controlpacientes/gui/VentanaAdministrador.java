package com.trivialbox.controlpacientes.gui;

import com.trivialbox.controlpacientes.srv.encuestaSrv.EncuestaSrv;
import com.trivialbox.controlpacientes.srv.objetos.Encuesta;
import java.awt.Color;
import java.awt.Dimension;
import java.util.List;
import java.awt.event.ActionEvent;

import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


/**
 *
 * @author root
 */
public class VentanaAdministrador extends javax.swing.JFrame {
    int posX = 10, posY = 100;
    List<JPanel> listaEncuestas = new ArrayList<>();
    EncuestaSrv encuestaSrv;
    VentanaPreguntas ventanaPreguntas;
    /**
     * Creates new form VentanaAdministrador
     */
    public VentanaAdministrador() {
        initComponents();
        crearEncuetas();
    }
    void crearEncuetas(){
        encuestaSrv = new EncuestaSrv();
        List<Encuesta> encuestas = encuestaSrv.obtenerEncuestas();
        Dimension dimension = new Dimension(150, 30);
        
        
         for(int a = 0; a < encuestas.size();a++){
            JPanel panel = new JPanel();
            panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK),
                                                                BorderFactory.createEmptyBorder(10, 15, 5, 15)));
            JLabel label1;
            if(encuestas.get(a).getNombre().length() >= 10){
                label1 = new JLabel(encuestas.get(a).getNombre().substring(0, 10)+"..");
            }else {
                label1 = new JLabel(encuestas.get(a).getNombre());
            }
            label1.setToolTipText(encuestas.get(a).getNombre());
            JLabel label = new JLabel(encuestas.get(a).getNombre());
            panel.setBounds(posX, posY, 100, 70);
            if(posX >400){
                posX = 10;
                posY = posY + 80;
            }else{
                posX= posX+110;
            }
            
            this.add(panel);
            JButton boton = new JButton("Editar");
            
            boton.setSize(dimension);


            boton.addActionListener((java.awt.event.ActionEvent evt) -> {
                actionPerformedqq(evt,label.getText());
            });
            label.setBounds(a, a, 100, 30);
            panel.add(label1);
            panel.add(boton);
        }
    }
    private void agregarNuevaEncuesta(String nombreEncuesta){
        JPanel panel = new JPanel();
            panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK),
                                                                BorderFactory.createEmptyBorder(10, 15, 5, 15)));
            JLabel label1;
            if(nombreEncuesta.length() >= 10){
                label1 = new JLabel(nombreEncuesta.substring(0, 10)+"..");
            }else {
                label1 = new JLabel(nombreEncuesta);
            }
            label1.setToolTipText(nombreEncuesta);
            JLabel label = new JLabel(nombreEncuesta);
            panel.setBounds(posX, posY, 100, 70);
            if(posX >300){
                posX = 10;
                posY = posY + 80;
            }else{
                posX= posX+110;
            }
            
            this.add(panel);
            JButton boton = new JButton("Editar");

            boton.addActionListener((java.awt.event.ActionEvent evt) -> {
                actionPerformedqq(evt,label.getText());
            });
            panel.add(label1);
            panel.add(boton);
            System.out.println("agregando");

    }
    public void actionPerformedqq(ActionEvent e, String nombreEncuesta) {
        ventanaPreguntas = new VentanaPreguntas(nombreEncuesta);
        ventanaPreguntas.setVisible(true);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        entryNombreEncuesta = new javax.swing.JTextField();
        btnCrearEncuesta = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("lISTA DE ENCUESTAS");

        entryNombreEncuesta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                entryNombreEncuestaActionPerformed(evt);
            }
        });

        btnCrearEncuesta.setText("Agregar");
        btnCrearEncuesta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearEncuestaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(entryNombreEncuesta, javax.swing.GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnCrearEncuesta, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(entryNombreEncuesta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCrearEncuesta))
                .addGap(155, 155, 155))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCrearEncuestaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearEncuestaActionPerformed
        encuestaSrv = new EncuestaSrv();
        encuestaSrv.agregarEncuesta(entryNombreEncuesta.getText(), "null");
        agregarNuevaEncuesta(entryNombreEncuesta.getText());
        this.update(this.getGraphics());
        this.entryNombreEncuesta.setText(null);
        
        
        
    }//GEN-LAST:event_btnCrearEncuestaActionPerformed

    private void entryNombreEncuestaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_entryNombreEncuestaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_entryNombreEncuestaActionPerformed

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
            java.util.logging.Logger.getLogger(VentanaAdministrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaAdministrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaAdministrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaAdministrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaAdministrador().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCrearEncuesta;
    private javax.swing.JTextField entryNombreEncuesta;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
