/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * jifPeriodReport.java
 *
 * Created on 9 avr. 2009, 17:13:13
 */
package gevi.Views.jifReports;

import gevi.Views.*;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import gevi.Model.Categorie;
import gevi.Model.Creneau;
import gevi.Model.Document;
import gevi.Model.Executant;
import gevi.Model.Depense;
import gevi.Model.Operation;
import gevi.Model.Payement;
import gevi.Model.Singleton;
import gevi.Model.Versement;
import gevi.Model.Visite;
import gevi.Views.jifGraphs.jifGraphFinancier;
import java.net.URI;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.image.BufferedImage;
import java.awt.print.PrinterException;
import java.beans.PropertyVetoException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.net.URISyntaxException;
import java.text.AttributedString;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import javax.swing.JOptionPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.encoders.KeypointPNGEncoderAdapter;
import org.jfree.chart.labels.CategoryItemLabelGenerator;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.TextAnchor;

/**
 *
 * @author root
 */
public class jifReportFinancier extends javax.swing.JInternalFrame {

    Document _data;
    Date _debut;
    Date _fin;

    /** Creates new form jifPeriodReport */
    public jifReportFinancier(Document data, Date debut, Date fin) {
        initComponents();
        this.setPreferredSize(new Dimension(640, 480));
        _data = data;
        _debut = debut;
        _fin = fin;

        SimpleDateFormat format = new SimpleDateFormat("EEEEEEE dd MMMMMMMMMMM yyyy");
        String titre = "Rapport financier du " + format.format(_debut) + " au " + format.format(_fin);
        this.setTitle(titre);

        try {
            jepHTML.setContentType("html");
            File f = genererRapport();
            jepHTML.setPage(f.toURI().toURL());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(MainWindow.getMainWindow(), e.getLocalizedMessage());
        }

        try {
            this.setSelected(true);
        } catch (PropertyVetoException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }

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
        jbtImprimer = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jepHTML = new javax.swing.JEditorPane();
        jPanel2 = new javax.swing.JPanel();
        jckRemplacant = new javax.swing.JCheckBox();
        jcbSources = new javax.swing.JCheckBox();

        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/gevi/images/sos_bleu.png"))); // NOI18N
        setMinimumSize(new java.awt.Dimension(640, 480));
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

        jbtImprimer.setText("Imprimer");
        jbtImprimer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtImprimerActionPerformed(evt);
            }
        });
        jPanel1.add(jbtImprimer);

        getContentPane().add(jPanel1, java.awt.BorderLayout.SOUTH);

        jepHTML.setContentType("text/html");
        jepHTML.setEditable(false);
        jScrollPane1.setViewportView(jepHTML);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel2.setLayout(new java.awt.GridLayout(1, 2, 1, 1));

        jckRemplacant.setText("Séparer les remplaçants");
        jckRemplacant.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jckRemplacant.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jckRemplacant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jckRemplacantActionPerformed(evt);
            }
        });
        jPanel2.add(jckRemplacant);

        jcbSources.setSelected(true);
        jcbSources.setText("Afficher le détails des sources");
        jcbSources.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbSourcesActionPerformed(evt);
            }
        });
        jPanel2.add(jcbSources);

        getContentPane().add(jPanel2, java.awt.BorderLayout.PAGE_START);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtOKActionPerformed
        MainWindow.getMainWindow().closeWindow(this);
        this.setVisible(false);
    }//GEN-LAST:event_jbtOKActionPerformed

    private void jbtImprimerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtImprimerActionPerformed
        try {
            jepHTML.print();

        } catch (PrinterException e) {
            JOptionPane.showMessageDialog(MainWindow.getMainWindow(), e.getLocalizedMessage());
        }
}//GEN-LAST:event_jbtImprimerActionPerformed

    private void jckRemplacantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jckRemplacantActionPerformed
        try {
            jepHTML.setContentType("html");
            File f = genererRapport();
            jepHTML.setPage(f.toURI().toURL());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(MainWindow.getMainWindow(), e.getLocalizedMessage());
        }
}//GEN-LAST:event_jckRemplacantActionPerformed

    private void jcbSourcesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbSourcesActionPerformed
        try {
            jepHTML.setContentType("html");
            File f = genererRapport();
            jepHTML.setPage(f.toURI().toURL());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(MainWindow.getMainWindow(), e.getLocalizedMessage());
        }
}//GEN-LAST:event_jcbSourcesActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtImprimer;
    private javax.swing.JButton jbtOK;
    private javax.swing.JCheckBox jcbSources;
    private javax.swing.JCheckBox jckRemplacant;
    private javax.swing.JEditorPane jepHTML;
    // End of variables declaration//GEN-END:variables

    public File genererRapport() {
        File address = null;


        try {
            Configuration cfg = new Configuration();
            URI uri = getClass().getResource("/gevi/templates").toURI();
            if (uri.toString().contains(".jar!")) {
                String tmp = uri.toString();
                tmp = tmp.substring(10, tmp.indexOf(".jar!") - 4);
                tmp = tmp + "templates";
                cfg.setDirectoryForTemplateLoading(new File(tmp));
            } else {
                cfg.setDirectoryForTemplateLoading(new File(uri));
            }
            cfg.setObjectWrapper(new DefaultObjectWrapper());
            Template temp = cfg.getTemplate("financier.html");

            Map root = new HashMap();
            Document doc = Singleton.instance().getDocument();
            DecimalFormat nf = new DecimalFormat();
            nf.setMaximumFractionDigits(2);
            nf.setMinimumFractionDigits(2);
            SimpleDateFormat format = new SimpleDateFormat("EEEEEEE dd MMMMMMMMMMM yyyy");
            SimpleDateFormat formatShort = new SimpleDateFormat("dd/MM/yyyy");

            root.put("nom", doc.getUtilisateur().getNom() + " " + doc.getUtilisateur().getPrenom());
            root.put("ADELI", doc.getUtilisateur().getAdeli());

            HashMap date = new HashMap();
            date.put("debut", format.format(_debut));
            date.put("fin", format.format(_fin));
            root.put("dates", date);

            Vector recettes = new Vector();
            Vector depenses = new Vector();

            double recette_banque = 0;
            double recette_especes = 0;
            double depense_banque = 0;
            double depense_especes = 0;

            /**
             * DEPENSES
             */
            /**
             * DEPENSES - Dépenses
             */
            for (int i = 0; i < _data.getDepenses().size(); i++) {
                Depense d = _data.getDepenses().get(i);
                if (d.getDate().equals(_debut) ||
                        d.getDate().equals(_fin) ||
                        (d.getDate().after(_debut) && d.getDate().before(_fin))) {
                    if (d.getSource().equals("Banque")) {
                        depense_banque += d.getMontant();
                    } else {
                        depense_especes += d.getMontant();
                    }
                }
            }

            /**
             * DEPENSES - Versements
             */
            for (int i = 0; i < _data.getVersements().size(); i++) {
                Versement v = _data.getVersements().get(i);
                if ((v.getDate().after(_debut) && v.getDate().before(_fin)) ||
                        v.getDate().equals(_debut) ||
                        v.getDate().equals(_fin)) {
                    depense_banque += v.getMontant();
                }
            }

            /**
             * TRANSFERTS
             */
            for (int i = 0; i < _data.getBanque().getOperationsFromCash().size(); i++) {
                Operation op = _data.getBanque().getOperationsFromCash().get(i);
                if ((op.getDate().after(_debut) && op.getDate().before(_fin)) ||
                        op.getDate().equals(_debut) ||
                        op.getDate().equals(_fin)) {
                    depense_especes += op.getMontant();
                    recette_banque += op.getMontant();
                }
            }

            for (int i = 0; i < _data.getBanque().getOperationsToCash().size(); i++) {
                Operation op = _data.getBanque().getOperationsToCash().get(i);
                if ((op.getDate().after(_debut) && op.getDate().before(_fin)) ||
                        op.getDate().equals(_debut) ||
                        op.getDate().equals(_fin)) {
                    depense_banque += op.getMontant();
                    recette_especes += op.getMontant();
                }
            }

            /**
             * RECETTES
             */
            for (int i = 0; i < _data.getCreneaux().size(); i++) {
                Creneau c = _data.getCreneaux().get(i);
                if (c.getAstreintePayee()) {
                    if ((c.getDatePayementAstreinte().after(_debut) && c.getDatePayementAstreinte().before(_fin)) ||
                            c.getDatePayementAstreinte().equals(_debut) ||
                            c.getDatePayementAstreinte().equals(_fin)) {
                        recette_banque += _data.getParametres().getTarifAstreinte();
                    }
                }
                for (int k = 0; k < c.getVisites().size(); k++) {
                    Visite v = c.getVisites().get(k);
                    for (int l = 0; l < v.getPayements().size(); l++) {
                        Payement p = v.getPayements().get(l);
                        if (p.getDatePayement()!=null)
                        {
                            if ((p.getDatePayement().after(_debut) && p.getDatePayement().before(_fin)) ||
                                    p.getDatePayement().equals(_debut) ||
                                    p.getDatePayement().equals(_fin)) {

                                if (p.getMoyenPayement().equals("Espèces")) {
                                    recette_especes += v.getSommePayée();
                                } else {
                                    recette_banque += v.getSommePayée();
                                }
                            }
                        }
                    }
                }
            }


            /**
             * DEPENSES PAR CATEGORIE
             */
            for (int j = 0; j < _data.getCategories().size(); j++) {
                Categorie c = _data.getCategories().get(j);
                HashMap dep = new HashMap();
                double val = 0;
                for (int i = 0; i < _data.getDepenses().size(); i++) {
                    Depense d = _data.getDepenses().get(i);
                    if (d.getDate().equals(_debut) ||
                            d.getDate().equals(_fin) ||
                            (d.getDate().after(_debut) && d.getDate().before(_fin))) {
                        if (d.getCategorie().equals(c)) {
                            val += d.getMontant();
                        }
                    }
                }
                dep.put("montant", nf.format(val).replace(" ", "&#160;") + " &euro;");
                dep.put("categorie", c.getNom().replace("é", "&eacute;").replace("à", "&aacute;").replace("û", "&ucirc;").replace("ê", "&ecirc;").replace("è", "&egrave;"));
                depenses.add(dep);
            }

            /**
             * Versements par remplaçant
             */
            double du = 0;
            for (int j = 0; j < _data.getRemplacants().size(); j++) {
                Executant e = _data.getRemplacants().get(j);
                HashMap dep = new HashMap();
                double val = 0;
                for (int i = 0; i < _data.getVersements().size(); i++) {
                    Versement v = _data.getVersements().get(i);
                    if (v.getExecutant().equals(e)) {
                        val += v.getMontant();
                    }
                }

                for (int i = 0; i < _data.getCreneaux().size(); i++) {
                    Creneau c = _data.getCreneaux().get(i);
                    if (c.getExecutant().equals(e)) {
                        if (c.getAstreintePayee() && (c.getVersementId() < 0)) {
                            du += _data.getParametres().getTarifAstreinte();
                        }
                        for (int k = 0; k < c.getVisites().size(); k++) {
                            Visite v = c.getVisites().get(k);
                            if ((v.getSommePayée()>0) && (v.getVersementId() < 0)) {
                                du += v.getSommePayée() * (1 - _data.getParametres().getPrelevement());
                            }
                        }
                    }
                }

                dep.put("montant", nf.format(val).replace(" ", "&#160;"));
                dep.put("categorie", e.getNom() + ", " + e.getPrenom().replace("é", "&eacute;").replace("à", "&aacute;").replace("û", "&ucirc;").replace("ê", "&ecirc;").replace("è", "&egrave;"));
                depenses.add(dep);
            }

            root.put("du", du);

            Vector sources = new Vector();
            sources.add("Tutelle");
            if (!jcbSources.isSelected()) {
                sources.add("Particulier");
            } else {
                sources.add("Particulier - Chèque");
                sources.add("Particulier - Espèces");
                sources.add("Particulier - Tiers payant");
                sources.add("Particulier - Carte Bleue");
            }
            if (!jcbSources.isSelected()) {
                sources.add("Sécurité sociale");
            } else {
                sources.add("Sécurité sociale - 100%");
                sources.add("Sécurité sociale - CMU");
                sources.add("Sécurité sociale - Tiers payant");
                sources.add("Sécurité sociale - Accident du travail");
            }
            sources.add("Tribunal de grande instance");
            sources.add("Mairie");
            sources.add("Hopital");

            /**
             * Recette par source
             */
            for (int i = 0; i < sources.size(); i++) {
                String source = (String) sources.get(i);
                HashMap rec = new HashMap();
                double val = 0;
                for (int j = 0; j < _data.getCreneaux().size(); j++) {
                    Creneau c = _data.getCreneaux().get(j);

                    for (int k = 0; k < c.getVisites().size(); k++) {
                        Visite v = c.getVisites().get(k);
                        if (v.getSommePayée() > 0) {
                            for (int l = 0; l < v.getPayements().size(); l++) {
                                Payement p=v.getPayements().get(l);
                                if (!jcbSources.isSelected()) {
                                    if (p.getDatePayement().equals(_fin) || p.getDatePayement().equals(_debut) || (p.getDatePayement().after(_debut) && p.getDatePayement().before(_fin))) {
                                        if (p.getSourcePayement().equals(source)) {
                                            if (c.getExecutant().equals(_data.getUtilisateur())) {
                                                val += p.getMontant();
                                            } else {
                                                if (jckRemplacant.isSelected()) {
                                                    val += p.getMontant() * _data.getParametres().getPrelevement();
                                                } else {
                                                    val += p.getMontant();
                                                }
                                            }
                                        }
                                    }

                                }
                                if (jcbSources.isSelected()) {
                                    if (p.getDatePayement().equals(_fin) || p.getDatePayement().equals(_debut) || (p.getDatePayement().after(_debut) && p.getDatePayement().before(_fin))) {
                                        if (source.equals(p.getSourcePayement()) || (source.equals(p.getSourcePayement() + " - " + p.getMoyenPayement()))) {
                                            if (c.getExecutant().equals(_data.getUtilisateur())) {
                                                val += p.getMontant();
                                            } else {
                                                if (jckRemplacant.isSelected()) {
                                                    val += p.getMontant() * _data.getParametres().getPrelevement();
                                                } else {
                                                    val += p.getMontant();
                                                }
                                            }
                                        }
                                    }

                                }
                            }
                        }
                    }
                }
                rec.put("montant", nf.format(val).replace(" ", "&#160;") + " &euro;");
                rec.put("categorie", source.replace("é", "&eacute;").replace("à", "&aacute;").replace("û", "&ucirc;").replace("ê", "&ecirc;").replace("è", "&egrave;"));
                recettes.add(rec);
            }

            int nb_astreintes = 0;
            for (int j = 0; j < _data.getCreneaux().size(); j++) {
                Creneau c = _data.getCreneaux().get(j);
                if (c.getDate().equals(_fin) || c.getDate().equals(_debut) || (c.getDate().after(_debut) && c.getDate().before(_fin))) {
                    if (c.getAstreinte() && c.getAstreintePayee()) {
                        if (c.getExecutant().equals(_data.getUtilisateur())) {
                            nb_astreintes++;
                        } else {
                            if (!jckRemplacant.isSelected()) {
                                nb_astreintes++;
                            }
                        }
                    }
                }
            }
            HashMap rec = new HashMap();
            rec.put("montant", nf.format(nb_astreintes * _data.getParametres().getTarifAstreinte()) + " &euro;");
            rec.put("categorie", "Astreintes");
            recettes.add(rec);


            root.put("depense", nf.format(depense_banque + depense_especes).replace(" ", "&#160;"));
            root.put("depenses_banque", nf.format(depense_banque).replace(" ", "&#160;"));
            root.put("depenses_especes", nf.format(depense_especes).replace(" ", "&#160;"));
            root.put("recette", nf.format(recette_banque + recette_especes).replace(" ", "&#160;"));
            root.put("recettes_banque", nf.format(recette_banque).replace(" ", "&#160;"));
            root.put("recettes_especes", nf.format(recette_especes).replace(" ", "&#160;"));
            root.put("benefice", nf.format(recette_banque + recette_especes - depense_banque - depense_especes - du).replace(" ", "&#160;"));
            root.put("recettes", recettes);
            root.put("depenses", depenses);

            root.put("dateGeneration", formatShort.format(new Date()));

            address = File.createTempFile("financier" + format.format(_debut) + "-" + format.format(_fin), ".tmp");
            address.deleteOnExit();

            File dir = address.getParentFile();

            /*            if (jcbDisplayGraphBar.isSelected()) {
            KeypointPNGEncoderAdapter encoder = new KeypointPNGEncoderAdapter();
            encoder.setEncodingAlpha(true);
            root.put("display_graph_bar", 1);

            jifGraphFinancier jif = new jifGraphFinancier(_data, _debut, _fin);

            FileOutputStream imgwriter2 = new FileOutputStream(dir + "/globalbar.png");
            encoder.encode(jif.getGlobalChart(jckRemplacant.isSelected()).createBufferedImage(640, 480, BufferedImage.BITMASK, null), imgwriter2);
            imgwriter2.close();
            FileOutputStream imgwriter3 = new FileOutputStream(dir + "/sourcebar.png");
            encoder.encode(jif.getSourceChart(jckRemplacant.isSelected()).createBufferedImage(640, 480, BufferedImage.BITMASK, null), imgwriter3);
            imgwriter3.close();
            FileOutputStream imgwriter4 = new FileOutputStream(dir + "/depensesbar.png");
            encoder.encode(jif.getVersementChart(jckRemplacant.isSelected()).createBufferedImage(640, 480, BufferedImage.BITMASK, null), imgwriter4);
            imgwriter4.close();
            FileOutputStream imgwriter5 = new FileOutputStream(dir + "/typebar.png");
            encoder.encode(jif.getTypeChart(jckRemplacant.isSelected()).createBufferedImage(640, 480, BufferedImage.BITMASK, null), imgwriter5);
            imgwriter5.close();

            } else {*/
            root.put("display_graph_bar", 0);
            /*}

            if (jcbDisplayGraphPie.isSelected()) {

            KeypointPNGEncoderAdapter encoder = new KeypointPNGEncoderAdapter();
            encoder.setEncodingAlpha(true);
            root.put("display_graph_pie", 1);

            jifGraphFinancier jif = new jifGraphFinancier(_data, _debut, _fin);

            FileOutputStream imgwriter2 = new FileOutputStream(dir + "/globalpie.png");
            encoder.encode(jif.getGlobalPie(jckRemplacant.isSelected()).createBufferedImage(640, 480, BufferedImage.BITMASK, null), imgwriter2);
            imgwriter2.close();
            FileOutputStream imgwriter3 = new FileOutputStream(dir + "/sourcepie.png");
            encoder.encode(jif.getSourcePie(jckRemplacant.isSelected()).createBufferedImage(640, 480, BufferedImage.BITMASK, null), imgwriter3);
            imgwriter3.close();
            FileOutputStream imgwriter4 = new FileOutputStream(dir + "/depensespie.png");
            encoder.encode(jif.getVersementPie(jckRemplacant.isSelected()).createBufferedImage(640, 480, BufferedImage.BITMASK, null), imgwriter4);
            imgwriter4.close();
            FileOutputStream imgwriter5 = new FileOutputStream(dir + "/typepie.png");
            encoder.encode(jif.getTypePie(jckRemplacant.isSelected()).createBufferedImage(640, 480, BufferedImage.BITMASK, null), imgwriter5);
            imgwriter5.close();
            } else {*/
            root.put("display_graph_pie", 0);
            //}

            InputStream imgreader = getClass().getResourceAsStream("/gevi/images/entete.png");
            FileOutputStream imgwriter = new FileOutputStream(dir + "/entete.png");
            byte[] data = new byte[1024];
            int nb = 0;
            while (imgreader.read(data) != -1) {
                imgwriter.write(data);
            }
            imgreader.close();
            imgwriter.close();

            Writer out = new FileWriter(address);
            temp.process(root, out);
            out.flush();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, e.getLocalizedMessage());
        } catch (TemplateException e) {
            JOptionPane.showMessageDialog(this, e.getLocalizedMessage());
        } catch (URISyntaxException e) {
            JOptionPane.showMessageDialog(this, e.getLocalizedMessage());
        }

        return address;
    }
}
