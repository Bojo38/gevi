/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * jdgSelectionCreneau.java
 *
 * Created on 9 avr. 2009, 13:34:15
 */
package gevi.Views;

import it.sauronsoftware.ftp4j.FTPFile;
import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.util.Date;
import java.util.Vector;
import javax.swing.DefaultListModel;

/**
 *
 * @author root
 */
public class jdgSelectionFichier extends javax.swing.JDialog {

    FTPFile[] _fichiers;
    int _action;
    FTPFile _selection;


    public jdgSelectionFichier(java.awt.Frame parent, boolean modal, FTPFile[] fichiers) {
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
        _fichiers = fichiers;
        if (fichiers.length == 0) {
            _selection = null;
        } else {
            _selection = _fichiers[0];
        }

        this.setTitle("Selection d'un fichier FTP");

        //String[] liste=new String[_data.getCreneaux().size()];
        DefaultListModel model = new DefaultListModel();

        for (int i = 0; i < _fichiers.length; i++) {
            model.addElement(_fichiers[i].getName());
        }
        jlsFichiers.setModel(model);
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jlsFichiers = new javax.swing.JList();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(378, 367));
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

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder("Selection des fichiers"));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(300, 300));

        jlsFichiers.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jlsFichiers.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(jlsFichiers);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public FTPFile getSelection()
    {
        return _selection;
    }

    private void jbtAnnulerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtAnnulerActionPerformed
        this.setVisible(false);
        _selection=null;
    }//GEN-LAST:event_jbtAnnulerActionPerformed

    private void jbtOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtOKActionPerformed
        int index=jlsFichiers.getSelectedIndex();
        if (index>=0)
        {
          _selection= _fichiers[jlsFichiers.getSelectedIndex()];
          this.setVisible(false);
        }

    }//GEN-LAST:event_jbtOKActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtAnnuler;
    private javax.swing.JButton jbtOK;
    private javax.swing.JList jlsFichiers;
    // End of variables declaration//GEN-END:variables
}
