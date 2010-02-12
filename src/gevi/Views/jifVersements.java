/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * jifPeriodReport.java
 *
 * Created on 9 avr. 2009, 17:13:13
 */
package gevi.Views;

import gevi.Model.Creneau;
import gevi.Model.Document;
import gevi.Model.Versement;
import gevi.Model.Visite;
import java.awt.BorderLayout;
import java.beans.PropertyVetoException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author root
 */
public class jifVersements extends javax.swing.JInternalFrame {

    Document _data;
    Date _debut;
    Date _fin;
    jpnVersements jpnVers;

    /** Creates new form jifPeriodReport */
    public jifVersements(Document data,Date debut, Date fin) {
        initComponents();
        _data = data;

        _debut = debut;
        _fin = fin;

        SimpleDateFormat format = new SimpleDateFormat("EEEEEEE dd MMMMMMMMMMM yyyy");
        String titre = "Versements du " + format.format(_debut) + " au " + format.format(_fin);
        this.setTitle(titre);

        try {
            this.setSelected(true);
        } catch (PropertyVetoException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }

        jpnVers = new jpnVersements(_data,_debut,_fin);
        this.add(jpnVers, BorderLayout.CENTER);
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

        jPanel1 = new javax.swing.JPanel();
        jbtOK = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jbtAdd = new javax.swing.JButton();
        jbtDelete = new javax.swing.JButton();

        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/gevi/images/sos_cyan.png"))); // NOI18N
        try {
            setSelected(true);
        } catch (java.beans.PropertyVetoException e1) {
            e1.printStackTrace();
        }

        jbtOK.setText("OK");
        jbtOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtOKActionPerformed(evt);
            }
        });
        jPanel1.add(jbtOK);

        getContentPane().add(jPanel1, java.awt.BorderLayout.SOUTH);

        jbtAdd.setText("Ajouter un versement");
        jbtAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtAddActionPerformed(evt);
            }
        });
        jPanel2.add(jbtAdd);

        jbtDelete.setText("Supprimer un versement");
        jbtDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtDeleteActionPerformed(evt);
            }
        });
        jPanel2.add(jbtDelete);

        getContentPane().add(jPanel2, java.awt.BorderLayout.PAGE_START);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtOKActionPerformed
        MainWindow.getMainWindow().closeWindow(this);
        this.setVisible(false);
    }//GEN-LAST:event_jbtOKActionPerformed

    private void jbtAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtAddActionPerformed
        if (jpnVers.getMedecin() != null) {
            jdgAddVersement jdg = new jdgAddVersement(MainWindow.getMainWindow(), true, _data, jpnVers.getMedecin());
            jdg.setVisible(true);
            jpnVers.refreshVersements();
            repaint();
        } else {
            JOptionPane.showMessageDialog(this, "Mauvaise sélection du médecin", "Erreur", JOptionPane.ERROR_MESSAGE);
        }

}//GEN-LAST:event_jbtAddActionPerformed

    private void jbtDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtDeleteActionPerformed
        if (jpnVers.getVersement() != null) {

            Versement ver = jpnVers.getVersement();
            int id = ver.getId();

            for (int i = 0; i < _data.getCreneaux().size(); i++) {
                Creneau c = _data.getCreneaux().get(i);

                if (c.getAstreinte()) {
                    if (c.getVersementId() == id) {
                        c.setVersementId(-1);
                    }
                }
                for (int j = 0; j < c.getVisites().size(); j++) {
                    Visite v = c.getVisites().get(j);
                    if (v.getVersementId() == id) {
                        v.setVersementId(-1);
                    }
                }
            }
            _data.getVersements().remove(ver);
            jpnVers.refreshVersements();
            repaint();
        } else {
            JOptionPane.showMessageDialog(this, "Mauvaise sélection du versement", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jbtDeleteActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton jbtAdd;
    private javax.swing.JButton jbtDelete;
    private javax.swing.JButton jbtOK;
    // End of variables declaration//GEN-END:variables
}
