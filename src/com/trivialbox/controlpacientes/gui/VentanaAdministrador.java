package com.trivialbox.controlpacientes.gui;

import com.trivialbox.controlpacientes.srv.LoginSrv;
import com.trivialbox.controlpacientes.srv.encuestaSrv.EncuestaSrv;
import com.trivialbox.controlpacientes.srv.objetos.Encuesta;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.beans.PropertyVetoException;
import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;


/**
 *
 * @author root
 */
public class VentanaAdministrador extends javax.swing.JFrame {
    int posX = 5, posY = 115;
    List<JPanel> listaEncuestas = new ArrayList<>();
    EncuestaSrv encuestaSrv;
    VentanaPreguntas ventanaPreguntas;
    public int actualizar = 0;
    /**
     * Creates new form VentanaAdministrador
     */
    public VentanaAdministrador() {
        initComponents();
        crearEncuetas();
        agregarPanelIngreso();
        
        
    }
    private void agregarPanelIngreso(){
        VentanaEncabezadoAdministrador vA = new VentanaEncabezadoAdministrador();
 
        this.jDesktopPane1.add(vA);
        vA.setVisible(true);
        try {
            vA.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(VentanaAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }
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
            if(encuestas.get(a).getNombre().length() >= 15){
                label1 = new JLabel(encuestas.get(a).getNombre().substring(0, 15)+"..");
            }else {
                label1 = new JLabel(encuestas.get(a).getNombre());
            }
            label1.setToolTipText(encuestas.get(a).getNombre());
            JLabel label = new JLabel(encuestas.get(a).getNombre());
            panel.setBounds(posX, posY, 100, 100);
            if(posX >400){
                posX = 10;
                posY = posY + 80;
            }else{
                posX= posX+110;
            }
            
            this.add(panel);
            JButton btnEditar = new JButton(" Editar ");
            JButton btnAsiganr = new JButton("Asignar");
            btnEditar.setSize(dimension);


            btnEditar.addActionListener((java.awt.event.ActionEvent evt) -> {
                actionPerformedqq(evt,label.getText());
            });
            label.setBounds(a, a, 100, 30);
            panel.add(label1);
            panel.add(btnEditar);
            panel.add(btnAsiganr);
            actualizar++;
        }
    }
    private void agregarNuevaEncuesta(String nombreEncuesta){
        encuestaSrv = new EncuestaSrv();
        List<Encuesta> encuestas = encuestaSrv.obtenerEncuestas();
        Dimension dimension = new Dimension(150, 30);

         for(int a = actualizar; a < encuestas.size();a++){
            JPanel panel = new JPanel();
            panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK),
                                                                BorderFactory.createEmptyBorder(10, 15, 5, 15)));
            JLabel label1;
            if(encuestas.get(a).getNombre().length() >= 15){
                label1 = new JLabel(encuestas.get(a).getNombre().substring(0, 15)+"..");
            }else {
                label1 = new JLabel(encuestas.get(a).getNombre());
            }
            label1.setToolTipText(encuestas.get(a).getNombre());
            JLabel label = new JLabel(encuestas.get(a).getNombre());
            panel.setBounds(posX, posY, 100, 100);
            if(posX >400){
                posX = 10;
                posY = posY + 80;
            }else{
                posX= posX+110;
            }
            
            this.add(panel);
            JButton btnEditar = new JButton(" Editar ");
            JButton btnAsiganr = new JButton("Asignar");
            btnEditar.setSize(dimension);


            btnEditar.addActionListener((java.awt.event.ActionEvent evt) -> {
                actionPerformedqq(evt,label.getText());
            });
            label.setBounds(a, a, 100, 30);
            panel.add(label1);
            panel.add(btnEditar);
            panel.add(btnAsiganr);
        }

    }
    public void actionPerformedqq(ActionEvent e, String nombreEncuesta) {
        ventanaPreguntas = new VentanaPreguntas(nombreEncuesta);
        ventanaPreguntas.setVisible(true);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane1 = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 458, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 108, Short.MAX_VALUE)
        );

        jMenu1.setText("Herramientas");

        jMenuItem1.setText("Registrar administrador");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem3.setText("Registrar paciente");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);
        jMenu1.add(jSeparator1);

        jMenuItem4.setText("Actualizar");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuItem2.setText("cerrar sesión");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Acerca de");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jDesktopPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 287, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        LoginSrv.cerrarSesion();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
       VentanaAgregarAdministrador agregarAdmin = new VentanaAgregarAdministrador();
       agregarAdmin.setVisible(true);
       
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        VentanaAgregarPaciente ventanaAgregarPaciente = new VentanaAgregarPaciente();
        ventanaAgregarPaciente.setVisible(true);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        agregarNuevaEncuesta(null);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

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
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    // End of variables declaration//GEN-END:variables
}
