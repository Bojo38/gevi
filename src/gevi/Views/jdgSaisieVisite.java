/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * jdgSaisieVisite.java
 *
 * Created on 8 avr. 2009, 11:04:02
 */
package gevi.Views;

import gevi.Model.Creneau;
import gevi.Model.Visite;
import java.awt.DisplayMode;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author root
 */
public class jdgSaisieVisite extends javax.swing.JDialog {

    Visite _data;
    Creneau _creneau;
    boolean addVisite = false;

    /** Creates new form jdgSaisieVisite */
    public jdgSaisieVisite(java.awt.Frame parent, boolean modal, Creneau creneau) {
        super(parent, modal);

        _data = new Visite();
        addVisite = true;
        _creneau = creneau;

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gs = ge.getDefaultScreenDevice();
        DisplayMode dmode = gs.getDisplayMode();

        this.setIconImage(new javax.swing.ImageIcon(getClass().getResource("/gevi/images/sos_rouge.png")).getImage());
        initComponents();

        jspDépassement.setModel(new javax.swing.SpinnerNumberModel(0.0, 0.0, null, 0.1));
        jtfNomMedecin.setText(creneau.getExecutant().getNom() + ", " + creneau.getExecutant().getPrenom());

        repaint();

        if (dmode != null) {
            int screenWidth = dmode.getWidth();
            int screenHeight = dmode.getHeight();
            this.setLocation((screenWidth - this.getWidth()) / 2, (screenHeight - this.getHeight()) / 2);
        }
    }

    /** Creates new form jdgSaisieVisite */
    public jdgSaisieVisite(java.awt.Frame parent, boolean modal, Creneau creneau, Visite data) {
        super(parent, modal);

        _data = data;
        _creneau = creneau;
        addVisite = false;

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gs = ge.getDefaultScreenDevice();
        DisplayMode dmode = gs.getDisplayMode();

        this.setIconImage(new javax.swing.ImageIcon(getClass().getResource("/gevi/images/sos_rouge.png")).getImage());
        initComponents();

        jspDépassement.setModel(new javax.swing.SpinnerNumberModel(0.0, 0.0, null, 0.1));

        jcbDEQP003.setSelected(_data.getECG());
//        jcbMD.setSelected(_data.getECGDomicile());
        jcbMNO.setSelected(_data.getMajorationNourrisson());
        jcbYYYY010.setSelected(_data.getUrgenceVitale());
        jcbYYYY490.setSelected(_data.getECGDomicile());
        jtfNomMedecin.setText(creneau.getExecutant().getNom() + ", " + creneau.getExecutant().getPrenom());
        jtfNom.setText(_data.getNom());

        if (_data.getTypeVisite().equals("C")) {
            jrbC.setSelected(true);
            jrbCActionPerformed(null);

            String maj[] = {"", "MD", "CRD", "CRN", "CRM"};
            jcbMajoration.setModel(new DefaultComboBoxModel(maj));
            jcbMajoration.setSelectedItem(_data.getMajorationPeriode());
           
        }
        if (_data.getTypeVisite().equals("V")) {
            jrbV.setSelected(true);
            jrbVActionPerformed(null);
            String maj[] = {"", "MD", "VRD", "VRN", "VRM"};
            jcbMajoration.setModel(new DefaultComboBoxModel(maj));
            jcbMajoration.setSelectedItem(_data.getMajorationPeriode());
        }
        if (_data.getTypeVisite().equals("GAV")) {
            jrbGAV.setSelected(true);
            jrbGAVActionPerformed(null);

            String maj[] = {""};
            jcbMajoration.setModel(new DefaultComboBoxModel(maj));
            jcbMajoration.setSelectedIndex(0);
            _data.setMajorationPeriode("");
        }
        if (_data.getTypeVisite().equals("IPM")) {
            jrbIPM.setSelected(true);
            jrbIPMActionPerformed(null);
            String maj[] = {""};
            jcbMajoration.setModel(new DefaultComboBoxModel(maj));
            jcbMajoration.setSelectedIndex(0);
            _data.setMajorationPeriode("");
        }
        if (_data.getTypeVisite().equals("AG")) {
            jrbAG.setSelected(true);
            jrbAGActionPerformed(null);
            String maj[] = {""};
            jcbMajoration.setModel(new DefaultComboBoxModel(maj));
            jcbMajoration.setSelectedIndex(0);
            _data.setMajorationPeriode("");
        }

        jcbMajoration.setSelectedItem(_data.getMajorationPeriode());
        jspDépassement.setValue(Double.valueOf(_data.getDepassement()));
        jspIK.setValue(Integer.valueOf(_data.getIK()));
        jspIKM.setValue(Integer.valueOf(_data.getIKM()));

        jtfNomMedecin.setText(_creneau.getExecutant().getNom() + ", " + _creneau.getExecutant().getPrenom());
        jtfDiagnostique.setText(_data.getDiagnostique());
        jtaDivers.setText(_data.getDescription());

        if (dmode != null) {
            int screenWidth = dmode.getWidth();
            int screenHeight = dmode.getHeight();
            this.setLocation((screenWidth - this.getWidth()) / 2, (screenHeight - this.getHeight()) / 2);
        }
        repaint();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TypeVisite = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jbtAnnuler = new javax.swing.JButton();
        jbtOK = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jtfNomMedecin = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jtfDiagnostique = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jtfNom = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtaDivers = new javax.swing.JTextArea();
        jPanel6 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jrbC = new javax.swing.JRadioButton();
        jrbV = new javax.swing.JRadioButton();
        jLabel6 = new javax.swing.JLabel();
        jrbGAV = new javax.swing.JRadioButton();
        jrbIPM = new javax.swing.JRadioButton();
        jLabel7 = new javax.swing.JLabel();
        jrbAG = new javax.swing.JRadioButton();
        jPanel5 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jlbMajration = new javax.swing.JLabel();
        jcbMajoration = new javax.swing.JComboBox();
        jPanel11 = new javax.swing.JPanel();
        jcbMNO = new javax.swing.JCheckBox();
        jcbYYYY010 = new javax.swing.JCheckBox();
        jcbDEQP003 = new javax.swing.JCheckBox();
        jcbYYYY490 = new javax.swing.JCheckBox();
        jPanel12 = new javax.swing.JPanel();
        jlbIKM = new javax.swing.JLabel();
        jspIKM = new javax.swing.JSpinner();
        jlbIK = new javax.swing.JLabel();
        jspIK = new javax.swing.JSpinner();
        jPanel7 = new javax.swing.JPanel();
        jlbDépassement = new javax.swing.JLabel();
        jspDépassement = new javax.swing.JSpinner();
        jlbTotal = new javax.swing.JLabel();
        jfttTotal = new javax.swing.JFormattedTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jbtPayement = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jcbPaye = new javax.swing.JCheckBox();
        jlbTotal1 = new javax.swing.JLabel();
        jfttPayee = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(300, 183));
        setResizable(false);

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

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_END);

        jPanel4.setLayout(new java.awt.GridLayout(1, 0, 1, 1));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel1.setText("Nom du médecin :");
        jPanel4.add(jLabel1);

        jtfNomMedecin.setEditable(false);
        jPanel4.add(jtfNomMedecin);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel2.setText("Diagnostique :");
        jPanel4.add(jLabel2);
        jPanel4.add(jtfDiagnostique);

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel3.setText("Nom du patient :");
        jPanel4.add(jLabel3);
        jPanel4.add(jtfNom);

        getContentPane().add(jPanel4, java.awt.BorderLayout.NORTH);

        jPanel2.setLayout(new java.awt.BorderLayout());

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder("Divers"));

        jtaDivers.setColumns(20);
        jtaDivers.setRows(5);
        jScrollPane1.setViewportView(jtaDivers);

        jPanel2.add(jScrollPane1, java.awt.BorderLayout.NORTH);

        jPanel6.setLayout(new java.awt.BorderLayout());

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Type de visite"));
        jPanel3.setLayout(new java.awt.GridLayout(8, 0));

        TypeVisite.add(jrbC);
        jrbC.setText("C - Consultation");
        jrbC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbCActionPerformed(evt);
            }
        });
        jPanel3.add(jrbC);

        TypeVisite.add(jrbV);
        jrbV.setText("V - Visite");
        jrbV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbVActionPerformed(evt);
            }
        });
        jPanel3.add(jrbV);
        jPanel3.add(jLabel6);

        TypeVisite.add(jrbGAV);
        jrbGAV.setText("GAV - Garde à vue");
        jrbGAV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbGAVActionPerformed(evt);
            }
        });
        jPanel3.add(jrbGAV);

        TypeVisite.add(jrbIPM);
        jrbIPM.setText("IPM - Ivresse publique Manifeste");
        jrbIPM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbIPMActionPerformed(evt);
            }
        });
        jPanel3.add(jrbIPM);
        jPanel3.add(jLabel7);

        TypeVisite.add(jrbAG);
        jrbAG.setText("AG - Acte Gratuit");
        jrbAG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbAGActionPerformed(evt);
            }
        });
        jPanel3.add(jrbAG);

        jPanel6.add(jPanel3, java.awt.BorderLayout.WEST);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Caractéristiques"));
        jPanel5.setLayout(new java.awt.BorderLayout());

        jPanel8.setLayout(new java.awt.GridLayout(1, 0));

        jlbMajration.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jlbMajration.setText("Majoration :");
        jPanel8.add(jlbMajration);

        jcbMajoration.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "MD", "CRD", "CRN", "CRM", "VRD", "VRN", "VRM" }));
        jcbMajoration.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbMajorationActionPerformed(evt);
            }
        });
        jPanel8.add(jcbMajoration);

        jPanel5.add(jPanel8, java.awt.BorderLayout.NORTH);

        jPanel11.setLayout(new java.awt.GridLayout(5, 1, 1, 1));

        jcbMNO.setText("MNO - Majoration nourrisson");
        jcbMNO.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jcbMNO.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jcbMNO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbMNOActionPerformed(evt);
            }
        });
        jPanel11.add(jcbMNO);

        jcbYYYY010.setText("YYYY010 - Urgence vitale");
        jcbYYYY010.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jcbYYYY010.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jcbYYYY010.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbYYYY010ActionPerformed(evt);
            }
        });
        jPanel11.add(jcbYYYY010);

        jcbDEQP003.setText("DEQP003 - ECG");
        jcbDEQP003.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jcbDEQP003.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jcbDEQP003.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbDEQP003ActionPerformed(evt);
            }
        });
        jPanel11.add(jcbDEQP003);

        jcbYYYY490.setText("YYYY490 - ECG à domicile");
        jcbYYYY490.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jcbYYYY490.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jcbYYYY490.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbYYYY490ActionPerformed(evt);
            }
        });
        jPanel11.add(jcbYYYY490);

        jPanel5.add(jPanel11, java.awt.BorderLayout.CENTER);

        jPanel12.setLayout(new java.awt.GridLayout(2, 0));

        jlbIKM.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jlbIKM.setText("IKM :");
        jPanel12.add(jlbIKM);

        jspIKM.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
        jspIKM.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jspIKMStateChanged(evt);
            }
        });
        jPanel12.add(jspIKM);

        jlbIK.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jlbIK.setText("IK :");
        jPanel12.add(jlbIK);

        jspIK.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
        jspIK.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jspIKStateChanged(evt);
            }
        });
        jPanel12.add(jspIK);

        jPanel5.add(jPanel12, java.awt.BorderLayout.SOUTH);

        jPanel6.add(jPanel5, java.awt.BorderLayout.CENTER);

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Payement"));
        jPanel7.setLayout(new java.awt.GridLayout(8, 0));

        jlbDépassement.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jlbDépassement.setText("Dépassement d'honoraire :");
        jPanel7.add(jlbDépassement);

        jspDépassement.setModel(new javax.swing.SpinnerNumberModel(Double.valueOf(0.0d), null, null, Double.valueOf(1.0d)));
        jspDépassement.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jspDépassementStateChanged(evt);
            }
        });
        jPanel7.add(jspDépassement);

        jlbTotal.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jlbTotal.setText("Total :");
        jPanel7.add(jlbTotal);

        jfttTotal.setEditable(false);
        jfttTotal.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getCurrencyInstance())));
        jfttTotal.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        jPanel7.add(jfttTotal);
        jPanel7.add(jLabel9);

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jPanel7.add(jLabel12);

        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jPanel7.add(jLabel15);

        jbtPayement.setText("Editer les payements");
        jbtPayement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtPayementActionPerformed(evt);
            }
        });
        jPanel7.add(jbtPayement);

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jPanel7.add(jLabel14);

        jcbPaye.setText("Payé");
        jcbPaye.setEnabled(false);
        jPanel7.add(jcbPaye);

        jlbTotal1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jlbTotal1.setText("Somme payée:");
        jPanel7.add(jlbTotal1);

        jfttPayee.setEditable(false);
        jfttPayee.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getCurrencyInstance())));
        jfttPayee.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        jPanel7.add(jfttPayee);

        jPanel6.add(jPanel7, java.awt.BorderLayout.EAST);

        jPanel2.add(jPanel6, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtAnnulerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtAnnulerActionPerformed
        this.setVisible(false);
}//GEN-LAST:event_jbtAnnulerActionPerformed

    private void jcbYYYY010ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbYYYY010ActionPerformed
        _data.setUrgenceVitale(jcbYYYY010.isSelected());
        repaint();
}//GEN-LAST:event_jcbYYYY010ActionPerformed

    private void jrbIPMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbIPMActionPerformed

        if (jrbIPM.isSelected()) {
            if (!_data.getTypeVisite().equals("IPM")) {
                String maj[] = {""};
                jcbMajoration.setModel(new DefaultComboBoxModel(maj));
                jcbMajoration.setSelectedIndex(0);
                _data.setMajorationPeriode("");

                String sources[] = {"Mairie"};
                _data.setDepassement(0);
                _data.setECG(false);
                _data.setECGDomicile(false);
                _data.setIK(0);
                _data.setIKM(0);
                _data.setMajorationNourrisson(false);
                _data.setUrgenceVitale(false);
//                _data.setDeplacement(false);
            }
            _data.setTypeVisite("IPM");
        }
        repaint();
}//GEN-LAST:event_jrbIPMActionPerformed

    private void jrbCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbCActionPerformed

        if (jrbC.isSelected()) {
            if (!_data.getTypeVisite().equals("C")) {
                String maj[] = {"", "CRD", "CRN", "CRM"};
                jcbMajoration.setModel(new DefaultComboBoxModel(maj));
                jcbMajoration.setSelectedIndex(0);
                _data.setMajorationPeriode("");
            }
            _data.setTypeVisite("C");
        }
        repaint();
    }//GEN-LAST:event_jrbCActionPerformed

    private void jrbVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbVActionPerformed

        if (jrbV.isSelected()) {
            if (!_data.getTypeVisite().equals("V")) {
                String maj[] = {"", "MD", "VRD", "VRN", "VRM"};
                jcbMajoration.setModel(new DefaultComboBoxModel(maj));
                jcbMajoration.setSelectedIndex(1);
                _data.setMajorationPeriode("");
            }
            _data.setTypeVisite("V");
        }

        repaint();
    }//GEN-LAST:event_jrbVActionPerformed

    private void jrbGAVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbGAVActionPerformed

        if (jrbGAV.isSelected()) {
            if (!_data.getTypeVisite().equals("GAV")) {
                String maj[] = {""};
                jcbMajoration.setModel(new DefaultComboBoxModel(maj));
                jcbMajoration.setSelectedIndex(0);
                _data.setMajorationPeriode("");

                _data.setDepassement(0);
                _data.setECG(false);
                _data.setECGDomicile(false);
                _data.setIK(0);
                _data.setIKM(0);
                _data.setMajorationNourrisson(false);
                _data.setUrgenceVitale(false);
            }
            _data.setTypeVisite("GAV");
        }

        repaint();
    }//GEN-LAST:event_jrbGAVActionPerformed

    private void jcbMNOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbMNOActionPerformed
        _data.setMajorationNourrisson(jcbMNO.isSelected());
        repaint();
    }//GEN-LAST:event_jcbMNOActionPerformed

    private void jcbDEQP003ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbDEQP003ActionPerformed
        _data.setECG(jcbDEQP003.isSelected());
        repaint();
    }//GEN-LAST:event_jcbDEQP003ActionPerformed

    private void jcbYYYY490ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbYYYY490ActionPerformed
        _data.setECGDomicile(jcbYYYY490.isSelected());
        repaint();
    }//GEN-LAST:event_jcbYYYY490ActionPerformed

    private void jcbMajorationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbMajorationActionPerformed

        _data.setMajorationPeriode((String) jcbMajoration.getSelectedItem());
        repaint();

    }//GEN-LAST:event_jcbMajorationActionPerformed

    private void jspIKStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jspIKStateChanged
        _data.setIK(((Integer) jspIK.getModel().getValue()).intValue());
        repaint();
    }//GEN-LAST:event_jspIKStateChanged

    private void jspIKMStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jspIKMStateChanged
        _data.setIKM(((Integer) jspIKM.getModel().getValue()).intValue());
        repaint();
    }//GEN-LAST:event_jspIKMStateChanged

    private void jspDépassementStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jspDépassementStateChanged
        _data.setDepassement(((Double) jspDépassement.getModel().getValue()).doubleValue());
        repaint();
    }//GEN-LAST:event_jspDépassementStateChanged


    private void jbtOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtOKActionPerformed


        if (jtfNom.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Le nom est vide.", "Champ manquant", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (_data.getTypeVisite().equals("")) {
            JOptionPane.showMessageDialog(this, "Le type de visite n'a pas été choisi.", "Champ manquant", JOptionPane.ERROR_MESSAGE);
            return;
        }

        _data.setNom(jtfNom.getText());
        _data.setDiagnostique(jtfDiagnostique.getText());
        _data.setDescription(jtaDivers.getText());

        if (this.addVisite) {
            _creneau.getVisites().add(_data);
        }
        this.setVisible(false);
    }//GEN-LAST:event_jbtOKActionPerformed

    private void jrbAGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbAGActionPerformed
        if (jrbAG.isSelected()) {
            if (!_data.getTypeVisite().equals("AG")) {
                String maj[] = {""};
                jcbMajoration.setModel(new DefaultComboBoxModel(maj));
                jcbMajoration.setSelectedIndex(0);
                _data.setMajorationPeriode("");

                _data.setDepassement(0);
                _data.setECG(false);
                _data.setECGDomicile(false);
                _data.setIK(0);
                _data.setIKM(0);
                _data.setMajorationNourrisson(false);
                _data.setUrgenceVitale(false);
            }
            _data.setTypeVisite("AG");
        }

        repaint();
}//GEN-LAST:event_jrbAGActionPerformed

    private void jbtPayementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtPayementActionPerformed
        jdgPayements jdg=new jdgPayements(MainWindow.getMainWindow(), true, _data);
        jdg.setVisible(true);
        repaint();
}//GEN-LAST:event_jbtPayementActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup TypeVisite;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtAnnuler;
    private javax.swing.JButton jbtOK;
    private javax.swing.JButton jbtPayement;
    private javax.swing.JCheckBox jcbDEQP003;
    private javax.swing.JCheckBox jcbMNO;
    private javax.swing.JComboBox jcbMajoration;
    private javax.swing.JCheckBox jcbPaye;
    private javax.swing.JCheckBox jcbYYYY010;
    private javax.swing.JCheckBox jcbYYYY490;
    private javax.swing.JFormattedTextField jfttPayee;
    private javax.swing.JFormattedTextField jfttTotal;
    private javax.swing.JLabel jlbDépassement;
    private javax.swing.JLabel jlbIK;
    private javax.swing.JLabel jlbIKM;
    private javax.swing.JLabel jlbMajration;
    private javax.swing.JLabel jlbTotal;
    private javax.swing.JLabel jlbTotal1;
    private javax.swing.JRadioButton jrbAG;
    private javax.swing.JRadioButton jrbC;
    private javax.swing.JRadioButton jrbGAV;
    private javax.swing.JRadioButton jrbIPM;
    private javax.swing.JRadioButton jrbV;
    private javax.swing.JSpinner jspDépassement;
    private javax.swing.JSpinner jspIK;
    private javax.swing.JSpinner jspIKM;
    private javax.swing.JTextArea jtaDivers;
    private javax.swing.JTextField jtfDiagnostique;
    private javax.swing.JTextField jtfNom;
    private javax.swing.JTextField jtfNomMedecin;
    // End of variables declaration//GEN-END:variables

    public void paint(Graphics g) {
        super.paint(g);

           jcbPaye.setSelected(_data.getPaye());
           
        if (jrbC.isSelected()) {
            jlbDépassement.setEnabled(true);
            jlbIK.setEnabled(true);
            jlbIKM.setEnabled(true);
            jlbMajration.setEnabled(true);

            jcbDEQP003.setEnabled(true);
            jcbMNO.setEnabled(true);
            jcbMajoration.setEnabled(true);

            jcbPaye.setEnabled(true);
            jcbYYYY010.setEnabled(true);
            jcbYYYY490.setEnabled(true);

            jspIK.setEnabled(true);
            jspIKM.setEnabled(true);

            jspDépassement.setEnabled(true);

        } else {
            if (jrbV.isSelected()) {
                jlbDépassement.setEnabled(true);
                jlbIK.setEnabled(true);
                jlbIKM.setEnabled(true);
                jlbMajration.setEnabled(true);

                jcbDEQP003.setEnabled(true);
                jcbMNO.setEnabled(true);
                jcbMajoration.setEnabled(true);
                jcbPaye.setEnabled(true);
                jcbYYYY010.setEnabled(true);
                jcbYYYY490.setEnabled(true);
                jspIK.setEnabled(true);
                jspIKM.setEnabled(true);
                jspDépassement.setEnabled(true);
                jfttTotal.setEnabled(true);

            } else {
                if ((jrbGAV.isSelected()) || (jrbIPM.isSelected())) {
                    jlbDépassement.setEnabled(false);
                    jlbIK.setEnabled(false);
                    jlbIKM.setEnabled(false);
                    jlbMajration.setEnabled(false);
                    jcbDEQP003.setEnabled(false);
                    jcbMNO.setEnabled(false);
                    jcbMajoration.setEnabled(false);
                    jcbPaye.setEnabled(true);
                    jcbYYYY010.setEnabled(false);
                    jcbYYYY490.setEnabled(false);

                    jspIK.setEnabled(false);
                    jspIKM.setEnabled(false);

                    jspDépassement.setEnabled(false);
                    jfttTotal.setEnabled(true);
                    String maj[] = {""};
                    jcbMajoration.setModel(new DefaultComboBoxModel(maj));
                    jcbMajoration.setSelectedIndex(0);
                } else {
                    jspDépassement.setEnabled(false);
                    jlbDépassement.setEnabled(false);
                    jlbIK.setEnabled(false);
                    jlbIKM.setEnabled(false);
                    jlbMajration.setEnabled(false);
                    jcbDEQP003.setEnabled(false);
                    jcbMNO.setEnabled(false);
                    jcbMajoration.setEnabled(false);
                    jcbPaye.setEnabled(false);
                    jcbYYYY010.setEnabled(false);
                    jcbYYYY490.setEnabled(false);
                    jspIK.setEnabled(false);
                    jspIKM.setEnabled(false);
                    jspDépassement.setEnabled(false);
                    jfttTotal.setEnabled(false);
                    String maj[] = {""};
                    jcbMajoration.setModel(new DefaultComboBoxModel(maj));
                    jcbMajoration.setSelectedIndex(0);
                }
            }
        }

        jcbMajoration.setSelectedItem(_data.getMajorationPeriode());
        jcbDEQP003.setSelected(_data.getECG());
        jcbMNO.setSelected(_data.getMajorationNourrisson());
        jcbYYYY010.setSelected(_data.getUrgenceVitale());
        jcbYYYY490.setSelected(_data.getECGDomicile());
        jspIK.setValue(Integer.valueOf(_data.getIK()));
        jspIKM.setValue(Integer.valueOf(_data.getIKM()));
        jspDépassement.setValue(Double.valueOf(_data.getDepassement()));
        double val = _data.getTotal();
        Double value = Double.valueOf(val);
        jfttTotal.setValue(value);
        val = _data.getSommePayée();
        value = Double.valueOf(val);
        jfttPayee.setValue(value);

    }
}
