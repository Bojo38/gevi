/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * jdgParametres.java
 *
 * Created on 7 avr. 2009, 14:35:24
 */

package gevi.Views.jdgOptions;

import gevi.Model.Parametres;
import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import javax.swing.SpinnerNumberModel;

/**
 *
 * @author root
 */
public class jdgParametres extends javax.swing.JDialog {

    Parametres _params;
    /** Creates new form jdgParametres */
    public jdgParametres(java.awt.Frame parent, boolean modal, Parametres params) {
        super(parent, modal);
        initComponents();

        setPreferredSize(new java.awt.Dimension(616, 583));

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gs = ge.getDefaultScreenDevice();
        DisplayMode dmode = gs.getDisplayMode();

        if (dmode != null) {
            int screenWidth = dmode.getWidth();
            int screenHeight = dmode.getHeight();
            this.setLocation((screenWidth - this.getWidth()) / 2, (screenHeight - this.getHeight()) / 2);
        }

        this.setIconImage(new javax.swing.ImageIcon(getClass().getResource("/gevi/images/sos_bleu.png")).getImage());
        _params=params;

        jfttVersementRemplacant.setValue(_params.getPrelevement());
        jfttAstreinte.setValue(_params.getTarifAstreinte());
        jfttC.setValue(_params.getTarifC());
        jfttCRD.setValue(_params.getTarifCRD());
        jfttCRM.setValue(_params.getTarifCRM());
        jfttCRN.setValue(_params.getTarifCRN());
        jfttDEQP003.setValue(_params.getTarifDEQP003());
        jfttIK.setValue(_params.getTarifIK());
        jfttIKM.setValue(_params.getTarifIKM());
        jfttMD.setValue(_params.getTarifMD());
        jfttMNO.setValue(_params.getTarifMNO());
        jfttV.setValue(_params.getTarifV());
        jfttVRD.setValue(_params.getTarifVRD());
        jfttVRM.setValue(_params.getTarifVRM());
        jfttVRN.setValue(_params.getTarifVRN());
        jfttYYY490.setValue(_params.getTarifYYYY490());
        jfttYYYY010.setValue(_params.getTarifYYYY010());
    
        jfttGAV.setValue(_params.getTarifGAV());
        jfttIPM.setValue(_params.getTarifIPM());

        jfttPeriod.setValue(_params.getSavePeriod()/1000);
        jtfAdress.setText(_params.getFTPServer());
        jtfDirectory.setText(_params.getFTPDirectory());
        jtfIdentifier.setText(_params.getFTPId());
        jcbAutomaticSave.setSelected(_params.getAutoFTP());
    }
  
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel14 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jpnBottom = new javax.swing.JPanel();
        jbtAnnuler = new javax.swing.JToggleButton();
        jbtOK = new javax.swing.JToggleButton();
        jPanel13 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jfttGAV = new javax.swing.JFormattedTextField();
        jLabel20 = new javax.swing.JLabel();
        jfttIPM = new javax.swing.JFormattedTextField();
        jLabel21 = new javax.swing.JLabel();
        jfttIPMN = new javax.swing.JFormattedTextField();
        jLabel22 = new javax.swing.JLabel();
        jfttIPMM = new javax.swing.JFormattedTextField();
        jpnParams = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jfttVersementRemplacant = new javax.swing.JFormattedTextField();
        jpnVisites = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jfttC = new javax.swing.JFormattedTextField();
        jLabel4 = new javax.swing.JLabel();
        jfttV = new javax.swing.JFormattedTextField();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jfttMD = new javax.swing.JFormattedTextField();
        jPanel6 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jfttCRD = new javax.swing.JFormattedTextField();
        jLabel11 = new javax.swing.JLabel();
        jfttVRD = new javax.swing.JFormattedTextField();
        jLabel14 = new javax.swing.JLabel();
        jfttCRN = new javax.swing.JFormattedTextField();
        jLabel15 = new javax.swing.JLabel();
        jfttVRN = new javax.swing.JFormattedTextField();
        jLabel16 = new javax.swing.JLabel();
        jfttCRM = new javax.swing.JFormattedTextField();
        jLabel17 = new javax.swing.JLabel();
        jfttVRM = new javax.swing.JFormattedTextField();
        jPanel11 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jfttDEQP003 = new javax.swing.JFormattedTextField();
        jLabel19 = new javax.swing.JLabel();
        jfttYYY490 = new javax.swing.JFormattedTextField();
        jPanel9 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jfttMNO = new javax.swing.JFormattedTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jfttIK = new javax.swing.JFormattedTextField();
        jLabel7 = new javax.swing.JLabel();
        jfttIKM = new javax.swing.JFormattedTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jfttYYYY010 = new javax.swing.JFormattedTextField();
        jPanel7 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jfttAstreinte = new javax.swing.JFormattedTextField();
        jPanel15 = new javax.swing.JPanel();
        jpnParams1 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jfttPeriod = new javax.swing.JFormattedTextField();
        jpnParams2 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jtfAdress = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jtfIdentifier = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jtfDirectory = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jcbAutomaticSave = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Paramètres");
        setResizable(false);

        jPanel14.setLayout(new java.awt.BorderLayout());

        jPanel12.setLayout(new java.awt.BorderLayout());

        jbtAnnuler.setText("Annuler");
        jbtAnnuler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtAnnulerActionPerformed(evt);
            }
        });
        jpnBottom.add(jbtAnnuler);

        jbtOK.setText("OK");
        jbtOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtOKActionPerformed(evt);
            }
        });
        jpnBottom.add(jbtOK);

        jPanel12.add(jpnBottom, java.awt.BorderLayout.SOUTH);

        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder("Autre"));
        jPanel13.setLayout(new java.awt.GridLayout(4, 2));

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel10.setText("Gardes à vue (GAV) :");
        jPanel13.add(jLabel10);

        jfttGAV.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getCurrencyInstance())));
        jPanel13.add(jfttGAV);

        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel20.setText("IPM :");
        jPanel13.add(jLabel20);

        jfttIPM.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getCurrencyInstance())));
        jPanel13.add(jfttIPM);

        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel21.setText("IPM de nuit :");
        jPanel13.add(jLabel21);

        jfttIPMN.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getCurrencyInstance())));
        jPanel13.add(jfttIPMN);

        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel22.setText("IPM de nuit :");
        jPanel13.add(jLabel22);

        jfttIPMM.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getCurrencyInstance())));
        jPanel13.add(jfttIPMM);

        jPanel12.add(jPanel13, java.awt.BorderLayout.NORTH);

        jPanel14.add(jPanel12, java.awt.BorderLayout.SOUTH);

        jpnParams.setBorder(javax.swing.BorderFactory.createTitledBorder("Versements"));
        jpnParams.setLayout(new java.awt.GridLayout(1, 2));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel2.setText("Versement des remplaçants (%):");
        jpnParams.add(jLabel2);

        jfttVersementRemplacant.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getPercentInstance())));
        jpnParams.add(jfttVersementRemplacant);

        jPanel14.add(jpnParams, java.awt.BorderLayout.NORTH);

        jpnVisites.setBorder(javax.swing.BorderFactory.createTitledBorder("Tarifs"));
        jpnVisites.setPreferredSize(new java.awt.Dimension(604, 379));
        jpnVisites.setLayout(new java.awt.BorderLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(new java.awt.GridLayout(2, 2));

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel3.setText("Consultation (C) :");
        jPanel1.add(jLabel3);

        jfttC.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getCurrencyInstance())));
        jPanel1.add(jfttC);

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel4.setText("Visite (V) :");
        jPanel1.add(jLabel4);

        jfttV.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getCurrencyInstance())));
        jPanel1.add(jfttV);

        jpnVisites.add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel3.setPreferredSize(new java.awt.Dimension(604, 263));
        jPanel3.setLayout(new java.awt.BorderLayout());

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel4.setLayout(new java.awt.GridLayout(1, 2));

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel8.setText("Majoration de déplacement(MD) :");
        jPanel4.add(jLabel8);

        jfttMD.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getCurrencyInstance())));
        jPanel4.add(jfttMD);

        jPanel3.add(jPanel4, java.awt.BorderLayout.PAGE_START);

        jPanel6.setLayout(new java.awt.BorderLayout());

        jPanel10.setLayout(new java.awt.BorderLayout());

        jPanel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel8.setLayout(new java.awt.GridLayout(6, 2));

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel13.setText("Majoration de consultation du week-end (CRD) :");
        jPanel8.add(jLabel13);

        jfttCRD.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getCurrencyInstance())));
        jPanel8.add(jfttCRD);

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel11.setText("Majoration de visite du week-end (VRD) :");
        jPanel8.add(jLabel11);

        jfttVRD.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getCurrencyInstance())));
        jPanel8.add(jfttVRD);

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel14.setText("Majoration de consultation de nuit (20h-0h et 6h-8h) (CRN) :");
        jPanel8.add(jLabel14);

        jfttCRN.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getCurrencyInstance())));
        jPanel8.add(jfttCRN);

        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel15.setText("Majoration de visite de nuit (20h-0h et 6h-8h) (VRN) :");
        jPanel8.add(jLabel15);

        jfttVRN.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getCurrencyInstance())));
        jPanel8.add(jfttVRN);

        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel16.setText("Majoration de consultation de nuit (0h-6h) (CRM) :");
        jPanel8.add(jLabel16);

        jfttCRM.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getCurrencyInstance())));
        jPanel8.add(jfttCRM);

        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel17.setText("Majoration de visite de nuit (0h-6h) (VRM) :");
        jPanel8.add(jLabel17);

        jfttVRM.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getCurrencyInstance())));
        jPanel8.add(jfttVRM);

        jPanel10.add(jPanel8, java.awt.BorderLayout.PAGE_START);

        jPanel11.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel11.setLayout(new java.awt.GridLayout(2, 2));

        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel18.setText("Eléctrocardiogramme au cabinet (DEQP003):");
        jPanel11.add(jLabel18);

        jfttDEQP003.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getCurrencyInstance())));
        jPanel11.add(jfttDEQP003);

        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel19.setText("Majoration éléctrocardiogramme à domicile (YYYY490):");
        jPanel11.add(jLabel19);

        jfttYYY490.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getCurrencyInstance())));
        jPanel11.add(jfttYYY490);

        jPanel10.add(jPanel11, java.awt.BorderLayout.SOUTH);

        jPanel6.add(jPanel10, java.awt.BorderLayout.CENTER);

        jPanel9.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel9.setLayout(new java.awt.GridLayout(1, 2));

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel12.setText("Majoration nourissons (moins de 2 ans) (MNO) :");
        jPanel9.add(jLabel12);

        jfttMNO.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getCurrencyInstance())));
        jPanel9.add(jfttMNO);

        jPanel6.add(jPanel9, java.awt.BorderLayout.NORTH);

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel5.setLayout(new java.awt.GridLayout(2, 2));

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel6.setText("Indémnité kilometrique (IK) :");
        jPanel5.add(jLabel6);

        jfttIK.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getCurrencyInstance())));
        jPanel5.add(jfttIK);

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel7.setText("Indémnité kilometrique de montagne (IKM) :");
        jPanel5.add(jLabel7);

        jfttIKM.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getCurrencyInstance())));
        jPanel5.add(jfttIKM);

        jPanel6.add(jPanel5, java.awt.BorderLayout.SOUTH);

        jPanel3.add(jPanel6, java.awt.BorderLayout.CENTER);

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setLayout(new java.awt.GridLayout(1, 2));

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel5.setText("Détresse vitale (YYYY010) :");
        jPanel2.add(jLabel5);

        jfttYYYY010.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getCurrencyInstance())));
        jPanel2.add(jfttYYYY010);

        jPanel3.add(jPanel2, java.awt.BorderLayout.SOUTH);

        jpnVisites.add(jPanel3, java.awt.BorderLayout.CENTER);

        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel7.setLayout(new java.awt.GridLayout(1, 2));

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel9.setText("Astreinte de sécurité sociale :");
        jPanel7.add(jLabel9);

        jfttAstreinte.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getCurrencyInstance())));
        jPanel7.add(jfttAstreinte);

        jpnVisites.add(jPanel7, java.awt.BorderLayout.SOUTH);

        jPanel14.add(jpnVisites, java.awt.BorderLayout.CENTER);

        jTabbedPane1.addTab("Paramètres de payement", jPanel14);

        jPanel15.setLayout(new java.awt.BorderLayout());

        jpnParams1.setBorder(javax.swing.BorderFactory.createTitledBorder("Période de sauvegarde automatique"));
        jpnParams1.setLayout(new java.awt.GridLayout(1, 2));

        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel23.setText("Période entre 2 sauvegardes automatiques en secondes : ");
        jpnParams1.add(jLabel23);

        try {
            jfttPeriod.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#######s")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jpnParams1.add(jfttPeriod);

        jPanel15.add(jpnParams1, java.awt.BorderLayout.NORTH);

        jpnParams2.setBorder(javax.swing.BorderFactory.createTitledBorder("Période de sauvegarde par FTP"));
        jpnParams2.setLayout(new java.awt.GridLayout(5, 2));

        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel24.setText("Adresse du site FTP :");
        jpnParams2.add(jLabel24);
        jpnParams2.add(jtfAdress);

        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel25.setText("Identifiant de connexion:");
        jpnParams2.add(jLabel25);
        jpnParams2.add(jtfIdentifier);

        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel27.setText("Répertoire de sauvegarde: ");
        jpnParams2.add(jLabel27);
        jpnParams2.add(jtfDirectory);

        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel28.setText("Option de sauvegarde :");
        jpnParams2.add(jLabel28);

        jcbAutomaticSave.setText("Sauvegarde automatique en quittant");
        jpnParams2.add(jcbAutomaticSave);

        jPanel15.add(jpnParams2, java.awt.BorderLayout.SOUTH);

        jTabbedPane1.addTab("Paramètres du programme", jPanel15);

        getContentPane().add(jTabbedPane1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtAnnulerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtAnnulerActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_jbtAnnulerActionPerformed

    private void jbtOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtOKActionPerformed
        _params.setPrelevement(((Double)jfttVersementRemplacant.getValue()).doubleValue());
        _params.setTarifAstreinte(((Double)jfttAstreinte.getValue()).doubleValue());
        _params.setTarifC(((Double)jfttC.getValue()).doubleValue());
        _params.setTarifCRD(((Double)jfttCRD.getValue()).doubleValue());
        _params.setTarifCRM(((Double)jfttCRM.getValue()).doubleValue());
        _params.setTarifCRN(((Double)jfttCRN.getValue()).doubleValue());
        _params.setTarifDEQP003(((Double)jfttDEQP003.getValue()).doubleValue());
        _params.setTarifIK(((Double)jfttIK.getValue()).doubleValue());
        _params.setTarifIKM(((Double)jfttIKM.getValue()).doubleValue());
        _params.setTarifMD(((Double)jfttMD.getValue()).doubleValue());
        _params.setTarifMNO(((Double)jfttMNO.getValue()).doubleValue());
        _params.setTarifV(((Double)jfttV.getValue()).doubleValue());
        _params.setTarifVRD(((Double)jfttVRD.getValue()).doubleValue());
        _params.setTarifVRM(((Double)jfttVRM.getValue()).doubleValue());
        _params.setTarifVRN(((Double)jfttVRN.getValue()).doubleValue());
        _params.setTarifYYYY490(((Double)jfttYYY490.getValue()).doubleValue());
        _params.setTarifYYYY010(((Double)jfttYYYY010.getValue()).doubleValue());

        _params.setTarifGAV(((Double)jfttGAV.getValue()).doubleValue());
        _params.setTarifIPM(((Double)jfttIPM.getValue()).doubleValue());

        _params.setSavePeriod(((Integer)jfttPeriod.getValue()).intValue()*1000);

        _params.setAutoFTP(jcbAutomaticSave.isSelected());
        _params.setFTPDirectory(jtfDirectory.getText());
        _params.setFTPId(jtfIdentifier.getText());
        _params.setFTPServer(jtfAdress.getText());

        this.setVisible(false);
    }//GEN-LAST:event_jbtOKActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JToggleButton jbtAnnuler;
    private javax.swing.JToggleButton jbtOK;
    private javax.swing.JCheckBox jcbAutomaticSave;
    private javax.swing.JFormattedTextField jfttAstreinte;
    private javax.swing.JFormattedTextField jfttC;
    private javax.swing.JFormattedTextField jfttCRD;
    private javax.swing.JFormattedTextField jfttCRM;
    private javax.swing.JFormattedTextField jfttCRN;
    private javax.swing.JFormattedTextField jfttDEQP003;
    private javax.swing.JFormattedTextField jfttGAV;
    private javax.swing.JFormattedTextField jfttIK;
    private javax.swing.JFormattedTextField jfttIKM;
    private javax.swing.JFormattedTextField jfttIPM;
    private javax.swing.JFormattedTextField jfttIPMM;
    private javax.swing.JFormattedTextField jfttIPMN;
    private javax.swing.JFormattedTextField jfttMD;
    private javax.swing.JFormattedTextField jfttMNO;
    private javax.swing.JFormattedTextField jfttPeriod;
    private javax.swing.JFormattedTextField jfttV;
    private javax.swing.JFormattedTextField jfttVRD;
    private javax.swing.JFormattedTextField jfttVRM;
    private javax.swing.JFormattedTextField jfttVRN;
    private javax.swing.JFormattedTextField jfttVersementRemplacant;
    private javax.swing.JFormattedTextField jfttYYY490;
    private javax.swing.JFormattedTextField jfttYYYY010;
    private javax.swing.JPanel jpnBottom;
    private javax.swing.JPanel jpnParams;
    private javax.swing.JPanel jpnParams1;
    private javax.swing.JPanel jpnParams2;
    private javax.swing.JPanel jpnVisites;
    private javax.swing.JTextField jtfAdress;
    private javax.swing.JTextField jtfDirectory;
    private javax.swing.JTextField jtfIdentifier;
    // End of variables declaration//GEN-END:variables

}
