/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * jdgSaisie.java
 *
 * Created on 7 avr. 2009, 18:11:54
 */

package gevi.Views;

import gevi.Model.Creneau;
import gevi.Model.Document;
import gevi.Model.Executant;
import java.awt.BorderLayout;
import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListDataListener;

/**
 *
 * @author root
 */
public class jdgVersements extends javax.swing.JDialog {

    Document _document;
    
    /** Creates new form jdgSaisie */
    public jdgVersements(java.awt.Frame parent, boolean modal,Document document) {
        super(parent, modal);
        initComponents();

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gs = ge.getDefaultScreenDevice();
        DisplayMode dmode = gs.getDisplayMode();

        if (dmode != null) {
            int screenWidth = dmode.getWidth();
            int screenHeight = dmode.getHeight();
            this.setLocation((screenWidth - this.getWidth()) / 2, (screenHeight - this.getHeight()) / 2);
        }

        _document=document;
        this.setIconImage(new javax.swing.ImageIcon(getClass().getResource("/gevi/images/sos_cyan.png")).getImage());
        
        jpnVersements jpn=new jpnVersements(document);
        this.add(jpn,BorderLayout.CENTER);
        
        pack();

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jbtOK = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Versement");
        setMinimumSize(new java.awt.Dimension(800, 600));
        setResizable(false);

        jbtOK.setText("OK");
        jbtOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtOKActionPerformed(evt);
            }
        });
        jPanel3.add(jbtOK);

        getContentPane().add(jPanel3, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtOKActionPerformed
        this.setVisible(false);
}//GEN-LAST:event_jbtOKActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel3;
    private javax.swing.JButton jbtOK;
    // End of variables declaration//GEN-END:variables

}