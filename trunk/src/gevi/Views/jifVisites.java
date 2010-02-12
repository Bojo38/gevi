/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * jifCreneau.java
 *
 * Created on 9 avr. 2009, 15:02:03
 */
package gevi.Views;

import gevi.Model.Creneau;
import gevi.Model.Visite;
import gevi.Model.Document;
import gevi.Model.Executant;
import gevi.Model.Singleton;
import java.awt.BorderLayout;
import java.beans.PropertyVetoException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;

/**
 *
 * @author root
 */
public class jifVisites extends javax.swing.JInternalFrame {

    Document _data;
    Vector _visites;
    Date _debut;
    Date _fin;
    Vector _dates;

    /** Creates new form jifCreneau */
    public jifVisites(Document data, Date debut, Date fin) {
        initComponents();
        _data = data;
        SimpleDateFormat f = new SimpleDateFormat("EEEEEEEEE dd MMMMMMMMMMM yyyy");
        this.setTitle("Liste des visites du " + f.format(debut) + " au " + f.format(fin));

        try {
            this.setSelected(true);
        } catch (PropertyVetoException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }

        int selectedNames[] = new int[data.getRemplacants().size() + 1];
        DefaultListModel model = new DefaultListModel();
        model.addElement(data.getUtilisateur().getNom() + ", " + data.getUtilisateur().getPrenom());
        selectedNames[0] = 0;
        for (int cpt = 0; cpt < data.getRemplacants().size(); cpt++) {
            Executant e = data.getRemplacants().get(cpt);
            model.addElement(e.getNom() + ", " + e.getPrenom());
            selectedNames[cpt + 1] = cpt + 1;
        }
        jlsMedecins.setModel(model);

        _debut = debut;
        _fin = fin;

        _visites = new Vector();
        _dates=new Vector();
        mtVisites tableModel = new mtVisites(_visites,_dates);
        jxtVisites.setModel(tableModel);
        jxtVisites.setDefaultRenderer(String.class, tableModel);
        jxtVisites.setDefaultRenderer(Double.class, tableModel);
        jxtVisites.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        refreshVisites();

        jlsMedecins.setSelectedIndices(selectedNames);

        pack();
    }

    private void refreshVisites() {
        _visites.clear();
        _dates.clear();
        Vector<Creneau> cren = Singleton.instance().getDocument().getCreneaux();
        Object selectedMedecins[] = (Object[]) jlsMedecins.getSelectedValues();
        for (int i = 0; i < cren.size(); i++) {
            Creneau c = cren.get(i);
            Vector visites = c.getVisites();
            Date d = c.getDate();
            if ((d.before(_fin) && d.after(_debut)) || (d.equals(_fin)) || (d.equals(_debut))) {
                String nom = c.getExecutant().getNom() + ", " + c.getExecutant().getPrenom();
                boolean selectionne = false;
                for (int cpt = 0; cpt < selectedMedecins.length; cpt++) {
                    if (((String) selectedMedecins[cpt]).equals(nom)) {
                        selectionne = true;
                        break;
                    }
                }
                if (selectionne) {
                    for (int j = 0; j < visites.size(); j++) {
                        Visite v = (Visite) visites.get(j);
                        String type = v.getTypeVisite();
                        String selectedType = (String) jcbType.getSelectedItem();

                        if ((type.equals("C") && (selectedType.equals("C - Consultation"))) ||
                                (type.equals("V") && (selectedType.equals("V - Visite"))) ||
                                (type.equals("GAV") && (selectedType.equals("GAV - Garde à vue"))) ||
                                (type.equals("IPM") && (selectedType.equals("IPM - Ivresse publique manifeste"))) ||
                                (selectedType.equals("Tous"))) {

                            String selectedPaye = (String) jcbPaye.getSelectedItem();
                            if ((v.getPaye() && (selectedPaye.equals("Payé"))) ||
                                    (!v.getPaye() && (selectedPaye.equals("Non payé"))) ||
                                    (selectedPaye.equals("Tous"))) {
                                String selectedSource = (String) jcbSource.getSelectedItem();

                                boolean source_equals = false;
                                for (int k = 0; k < v.getPayements().size(); k++) {
                                    if (selectedSource.equals(v.getPayements().get(k).getSourcePayement())) {
                                        source_equals = true;
                                        break;
                                    }
                                }

                                if (selectedSource.equals("Tous") || source_equals)                                   
                                {

                                    if (jtfNom.getText().equals("") ||
                                            v.getNom().contains(jtfNom.getText().toUpperCase())) {
                                        if (jtfDetails.getText().equals("") ||
                                                v.getDescription().toUpperCase().contains(jtfDetails.getText().toUpperCase())) {
                                            _visites.add(v);
                                            _dates.add(c.getDate());
                                        }
                                    }
                                }
                            }

                        }
                    }
                }
            }
        }
        mtVisites tableModel = new mtVisites(_visites,_dates);
        jxtVisites.setModel(tableModel);
        jxtVisites.setDefaultRenderer(String.class, tableModel);
        jxtVisites.setDefaultRenderer(Double.class, tableModel);
        jxtVisites.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jlsMedecins = new javax.swing.JList();
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jxtVisites = new org.jdesktop.swingx.JXTable();
        jPanel6 = new javax.swing.JPanel();
        jbtEditerVisite = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jlbSource = new javax.swing.JLabel();
        jcbSource = new javax.swing.JComboBox();
        jlbSource1 = new javax.swing.JLabel();
        jtfNom = new javax.swing.JTextField();
        jlbMoyenPayement = new javax.swing.JLabel();
        jcbPaye = new javax.swing.JComboBox();
        jlbSource2 = new javax.swing.JLabel();
        jtfDetails = new javax.swing.JTextField();
        jlbMajration1 = new javax.swing.JLabel();
        jcbType = new javax.swing.JComboBox();

        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/gevi/images/sos_vert.png"))); // NOI18N
        try {
            setSelected(true);
        } catch (java.beans.PropertyVetoException e1) {
            e1.printStackTrace();
        }
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameClosing(evt);
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });

        jbtOK.setText("OK");
        jbtOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtOKActionPerformed(evt);
            }
        });
        jPanel3.add(jbtOK);

        getContentPane().add(jPanel3, java.awt.BorderLayout.SOUTH);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Médecins", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel1.setPreferredSize(new java.awt.Dimension(150, 200));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jlsMedecins.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jlsMedecinsValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(jlsMedecins);

        jPanel1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1, java.awt.BorderLayout.WEST);

        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel5.setLayout(new java.awt.BorderLayout());

        jxtVisites.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jxtVisites);

        jPanel5.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jbtEditerVisite.setText("Editer la visite");
        jbtEditerVisite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtEditerVisiteActionPerformed(evt);
            }
        });
        jPanel6.add(jbtEditerVisite);

        jPanel5.add(jPanel6, java.awt.BorderLayout.PAGE_START);

        jPanel2.add(jPanel5, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Options", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel4.setLayout(new java.awt.GridLayout(3, 0));

        jlbSource.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jlbSource.setText("Source du payement :");
        jPanel4.add(jlbSource);

        jcbSource.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tous", "Particulier", "Maison de retraite", "Sécurité sociale - CMU", "Sécurité sociale - 100%", "Mairie", "Tribunal de grande instance", "Tutelle" }));
        jcbSource.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbSourceActionPerformed(evt);
            }
        });
        jPanel4.add(jcbSource);

        jlbSource1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jlbSource1.setText("Nom du patient :");
        jPanel4.add(jlbSource1);

        jtfNom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfNomActionPerformed(evt);
            }
        });
        jPanel4.add(jtfNom);

        jlbMoyenPayement.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jlbMoyenPayement.setText("Payement effectué :");
        jPanel4.add(jlbMoyenPayement);

        jcbPaye.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tous", "Payé", "Non payé" }));
        jcbPaye.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbPayeActionPerformed(evt);
            }
        });
        jPanel4.add(jcbPaye);

        jlbSource2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jlbSource2.setText("Détails :");
        jPanel4.add(jlbSource2);

        jtfDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfDetailsActionPerformed(evt);
            }
        });
        jPanel4.add(jtfDetails);

        jlbMajration1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jlbMajration1.setText("Type de visite :");
        jPanel4.add(jlbMajration1);

        jcbType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tous", "C - Consultation", "V - Visite", "GAV - Garde à vue", "IPM - Ivresse publique manifeste" }));
        jcbType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbTypeActionPerformed(evt);
            }
        });
        jPanel4.add(jcbType);

        getContentPane().add(jPanel4, java.awt.BorderLayout.PAGE_START);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing
        MainWindow.getMainWindow().closeWindow(this);
    }//GEN-LAST:event_formInternalFrameClosing

    private void jbtOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtOKActionPerformed
        MainWindow.getMainWindow().closeWindow(this);
        this.setVisible(false);
}//GEN-LAST:event_jbtOKActionPerformed

    private void jcbTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbTypeActionPerformed
        refreshVisites();
    }//GEN-LAST:event_jcbTypeActionPerformed

    private void jcbPayeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbPayeActionPerformed
        refreshVisites();
}//GEN-LAST:event_jcbPayeActionPerformed

    private void jlsMedecinsValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jlsMedecinsValueChanged
        refreshVisites();
    }//GEN-LAST:event_jlsMedecinsValueChanged

    private void jtfNomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfNomActionPerformed
        refreshVisites();
    }//GEN-LAST:event_jtfNomActionPerformed

    private void jcbSourceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbSourceActionPerformed
        refreshVisites();
    }//GEN-LAST:event_jcbSourceActionPerformed

    private void jbtEditerVisiteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtEditerVisiteActionPerformed
        if (jxtVisites.getSelectedRow() >= 0) {
            for (int i = 0; i < _data.getCreneaux().size(); i++) {
                Creneau c = _data.getCreneaux().get(i);
                for (int j = 0; j < c.getVisites().size(); j++) {
                    Visite s = (Visite) _visites.get(jxtVisites.getSelectedRow());
                    if (s.equals(c.getVisites().get(j))) {
                        jdgSaisieVisite saisie = new jdgSaisieVisite(MainWindow.getMainWindow(), true, c, s);
                        saisie.setVisible(true);
                        break;
                    }
                }
            }
        }
    }//GEN-LAST:event_jbtEditerVisiteActionPerformed

    private void jtfDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfDetailsActionPerformed
        refreshVisites();
}//GEN-LAST:event_jtfDetailsActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton jbtEditerVisite;
    private javax.swing.JButton jbtOK;
    private javax.swing.JComboBox jcbPaye;
    private javax.swing.JComboBox jcbSource;
    private javax.swing.JComboBox jcbType;
    private javax.swing.JLabel jlbMajration1;
    private javax.swing.JLabel jlbMoyenPayement;
    private javax.swing.JLabel jlbSource;
    private javax.swing.JLabel jlbSource1;
    private javax.swing.JLabel jlbSource2;
    private javax.swing.JList jlsMedecins;
    private javax.swing.JTextField jtfDetails;
    private javax.swing.JTextField jtfNom;
    private org.jdesktop.swingx.JXTable jxtVisites;
    // End of variables declaration//GEN-END:variables
}
