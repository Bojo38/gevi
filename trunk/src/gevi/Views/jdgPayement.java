/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * jdgNewDocument.java
 *
 * Created on 8 avr. 2009, 15:40:34
 */
package gevi.Views;

import gevi.Model.Depense;
import gevi.Model.Document;
import gevi.Model.Payement;
import gevi.Model.Singleton;
import gevi.Model.Utilisateur;
import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author root
 */
public class jdgPayement extends javax.swing.JDialog {

    Payement _data;

    /** Creates new form jdgNewDocument */
    public jdgPayement(java.awt.Frame parent, boolean modal, Payement data,String typeVisite) {
        super(parent, modal);
        initComponents();
        jxdpDate.setDate(new Date());

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gs = ge.getDefaultScreenDevice();
        DisplayMode dmode = gs.getDisplayMode();

        if (dmode != null) {
            int screenWidth = dmode.getWidth();
            int screenHeight = dmode.getHeight();
            this.setLocation((screenWidth - this.getWidth()) / 2, (screenHeight - this.getHeight()) / 2);
        }
        _data = data;

        jxdpDate.setFormats(new String[]{"EEEEEEEEE dd MMMMMMMMMMM yyyy"});
        if (_data.getDatePayement()==null)
        {
            jxdpDate.setDate(_data.getDatePayement());
        }
        else
        {
            jxdpDate.setDate(new Date());
        }

        if (typeVisite.equals("C")) {

            String sources[] = {"Particulier", "Sécurité sociale", "Tutelle", "Maison de retraite", "Hopital","Autre"};
            jcbSource.setModel(new DefaultComboBoxModel(sources));            
            jcbSource.setSelectedItem(_data.getSourcePayement());
        }
        if (typeVisite.equals("V")) {

            String sources[] = {"Particulier", "Sécurité sociale", "Hopital", "Tutelle", "Maison de retraite","Autre"};
            jcbSource.setModel(new DefaultComboBoxModel(sources));
            jcbSource.setSelectedItem(_data.getSourcePayement());
        }
        if (typeVisite.equals("GAV")) {
            String sources[] = {"Tribunal de grande instance"};
            jcbSource.setModel(new DefaultComboBoxModel(sources));
            jcbSource.setSelectedIndex(0);
            _data.setSourcePayement("Tribunal de grande instance");
            jcbMoyen.setEnabled(false);
        }
        if (typeVisite.equals("IPM")) {
            String sources[] = {"Mairie"};
            jcbSource.setModel(new DefaultComboBoxModel(sources));
            jcbSource.setSelectedIndex(0);
            _data.setSourcePayement("Mairie");
        }
        if (typeVisite.equals("AG")) {
            String sources[] = {"Pas de payement"};
            jcbSource.setModel(new DefaultComboBoxModel(sources));
            jcbSource.setSelectedIndex(0);
            _data.setSourcePayement("Pas de payement");
        }
        setMoyenBox((String)jcbSource.getSelectedItem(),_data.getMoyenPayement());

        jftfMontant.setValue(_data.getMontant());
    }

    private void setMoyenBox(String source,String moyen)
    {
        if ((source.equals("Particulier")) || (source.equals("Maison de retraite"))) {
                String moyens[] = {"Espèces", "Carte bleue", "Chèque"};
                jcbMoyen.setModel(new DefaultComboBoxModel(moyens));
                jcbMoyen.setSelectedItem(_data.getMoyenPayement());
                jcbMoyen.setEnabled(true);
            } else {
                if (_data.getSourcePayement().equals("Sécurité sociale")) {
                    String moyens[] = {"100%", "Tiers payant", "CMU", "Accident du travail","Autre"};
                    jcbMoyen.setModel(new DefaultComboBoxModel(moyens));
                    jcbMoyen.setSelectedItem(_data.getMoyenPayement());
                    jcbMoyen.setEnabled(true);
                } else {
                    String moyens[] = {""};
                    jcbMoyen.setModel(new DefaultComboBoxModel(moyens));
                    jcbMoyen.setEnabled(false);
                }
            }
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
        jbtAnnuler = new javax.swing.JButton();
        jbtOK = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jxdpDate = new org.jdesktop.swingx.JXDatePicker();
        jLabel7 = new javax.swing.JLabel();
        jftfMontant = new javax.swing.JFormattedTextField();
        jLabel9 = new javax.swing.JLabel();
        jcbSource = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        jcbMoyen = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Dépense");
        setMinimumSize(new java.awt.Dimension(375, 151));

        jbtAnnuler.setText("Annuler");
        jbtAnnuler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtAnnulerActionPerformed(evt);
            }
        });
        jPanel1.add(jbtAnnuler);

        jbtOK.setText("OK");
        jbtOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtOKActionPerformed(evt);
            }
        });
        jPanel1.add(jbtOK);

        getContentPane().add(jPanel1, java.awt.BorderLayout.SOUTH);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Payement"));
        jPanel3.setFocusable(false);
        jPanel3.setLayout(new java.awt.GridLayout(4, 0, 1, 1));

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel6.setText("Date :");
        jLabel6.setFocusable(false);
        jPanel3.add(jLabel6);
        jPanel3.add(jxdpDate);

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel7.setText("Montant:");
        jLabel7.setFocusable(false);
        jPanel3.add(jLabel7);

        jftfMontant.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getCurrencyInstance())));
        jftfMontant.setFocusLostBehavior(javax.swing.JFormattedTextField.COMMIT);
        jPanel3.add(jftfMontant);

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel9.setText("Source:");
        jLabel9.setFocusable(false);
        jPanel3.add(jLabel9);

        jcbSource.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Particulier", "Maison de retraite", "Hopital", "Sécurité sociale", "Mairie", "Tribunal de grande instance", "Tutelle" }));
        jcbSource.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbSourceActionPerformed(evt);
            }
        });
        jPanel3.add(jcbSource);

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel8.setText("Catégorie:");
        jLabel8.setFocusable(false);
        jPanel3.add(jLabel8);

        jcbMoyen.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Carte Bleue", "Tiers payant", "Espèces", "Chèque", "100%", "CMU", "Accident du travail", "" }));
        jPanel3.add(jcbMoyen);

        getContentPane().add(jPanel3, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtAnnulerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtAnnulerActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_jbtAnnulerActionPerformed

    private void jbtOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtOKActionPerformed

        _data.setDatePayement(jxdpDate.getDate());
        _data.setMontant(Double.parseDouble(jftfMontant.getText().replace(",",".").replace("€","")));
        _data.setSourcePayement((String)jcbSource.getSelectedItem());
        _data.setMoyenPayement((String)jcbMoyen.getSelectedItem());
        this.setVisible(false);
    }//GEN-LAST:event_jbtOKActionPerformed

    private void jcbSourceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbSourceActionPerformed
        _data.setSourcePayement((String)jcbSource.getSelectedItem());
        this.setMoyenBox((String)jcbSource.getSelectedItem(), _data.getMoyenPayement());
    }//GEN-LAST:event_jcbSourceActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JButton jbtAnnuler;
    private javax.swing.JButton jbtOK;
    private javax.swing.JComboBox jcbMoyen;
    private javax.swing.JComboBox jcbSource;
    private javax.swing.JFormattedTextField jftfMontant;
    private org.jdesktop.swingx.JXDatePicker jxdpDate;
    // End of variables declaration//GEN-END:variables
}
