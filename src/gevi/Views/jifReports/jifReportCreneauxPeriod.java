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
import gevi.Model.Creneau;
import gevi.Model.Document;
import gevi.Model.Executant;
import gevi.Model.Singleton;
import gevi.Model.Visite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.image.BufferedImage;
import java.awt.print.PrinterException;
import java.beans.PropertyVetoException;
import java.io.File;
import java.net.URI;
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
public class jifReportCreneauxPeriod extends javax.swing.JInternalFrame {

    Document _data;
    Date _debut;
    Date _fin;
    int _index;
    Vector<Creneau> _creneaux;
    Vector<Executant> _medecins;

    /** Creates new form jifPeriodReport */
    public jifReportCreneauxPeriod(Document data, Date debut, Date fin, int index) {
        initComponents();
        this.setPreferredSize(new Dimension(640, 480));
        _data = data;

        _debut = debut;
        _fin = fin;
        _index = index;
        _creneaux = new Vector();
        _medecins = new Vector();
        SimpleDateFormat format = new SimpleDateFormat("EEEEEEE dd MMMMMMMMMMM yyyy");
        String titre = "Créneaux du " + format.format(_debut) + " au " + format.format(_fin);
        this.setTitle(titre);

        try {
            jepHTML.setContentType("html");
            File f = genererRapportCreneaux(_debut, _fin);
            jepHTML.setPage(f.toURI().toURL());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(MainWindow.getMainWindow(), e.getLocalizedMessage());
        }

        try {
            this.setSelected(true);
        } catch (PropertyVetoException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }

        jckAfficheRemplacant.setSelected(true);
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
        jckAfficheRemplacant = new javax.swing.JCheckBox();

        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/gevi/images/sos_jaune.png"))); // NOI18N
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

        jckAfficheRemplacant.setSelected(true);
        jckAfficheRemplacant.setText("Affiche les remplaçants");
        jckAfficheRemplacant.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jckAfficheRemplacant.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jckAfficheRemplacant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jckAfficheRemplacantActionPerformed(evt);
            }
        });
        jPanel2.add(jckAfficheRemplacant);

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

    private void jckAfficheRemplacantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jckAfficheRemplacantActionPerformed
        try {
            jepHTML.setContentType("html");
            File f = genererRapportCreneaux(_debut, _fin);
            jepHTML.setPage(f.toURI().toURL());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(MainWindow.getMainWindow(), e.getLocalizedMessage());
        }
}//GEN-LAST:event_jckAfficheRemplacantActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtImprimer;
    private javax.swing.JButton jbtOK;
    private javax.swing.JCheckBox jckAfficheRemplacant;
    private javax.swing.JEditorPane jepHTML;
    // End of variables declaration//GEN-END:variables

    public File genererRapportCreneaux(Date debut, Date fin) {
        File address = null;
        try {
            Configuration cfg = new Configuration();
            URI uri=getClass().getResource("/gevi/templates").toURI();
            if (uri.toString().contains(".jar!"))
            {
                String tmp=uri.toString();
                tmp=tmp.substring(10, tmp.indexOf(".jar!")-4);
                tmp=tmp+"templates";
                cfg.setDirectoryForTemplateLoading(new File(tmp));
            }
            else
            {
                cfg.setDirectoryForTemplateLoading(new File(uri));
            }
            cfg.setObjectWrapper(new DefaultObjectWrapper());
            Template temp = cfg.getTemplate("creneaux.html");

            DecimalFormat nf = new DecimalFormat();
            nf.setMaximumFractionDigits(2);
            nf.setMinimumFractionDigits(2);
            
            Map root = new HashMap();
            Document doc = Singleton.instance().getDocument();
            SimpleDateFormat format = new SimpleDateFormat("EEEEEEE dd MMMMMMMMMMM yyyy");
            SimpleDateFormat formatShort = new SimpleDateFormat("dd/MM/yyyy");
            root.put("nom", doc.getUtilisateur().getNom() + " " + doc.getUtilisateur().getPrenom());
            root.put("ADELI", doc.getUtilisateur().getAdeli());
            
            Map dates = new HashMap();
            root.put("dates", dates);
            dates.put("debut", format.format(debut));
            dates.put("fin", format.format(fin));

            Vector parMedecin = new Vector();

            _creneaux.clear();
            _medecins.clear();

            for (int cpt = 0; cpt < _data.getRemplacants().size() + 1; cpt++) {
                Executant e;
                if (cpt == 0) {
                    e = _data.getUtilisateur();
                } else {
                    e = _data.getRemplacants().get(cpt - 1);
                }


                Map medecin = new HashMap();

                if (cpt == 0) {
                    medecin.put("nom", "Dr " + e.getNom() + " " + e.getPrenom());
                } else {
                    medecin.put("nom", "Dr " + e.getNom() + " " + e.getPrenom() + " (Rempla&ccedil;ant)");
                }

                if (!jckAfficheRemplacant.isSelected()) {
                    cpt = _data.getRemplacants().size() + 1;
                }

                Map resume = new HashMap();
                medecin.put("resume", resume);

                int nbCreneaux = 0;
                int nbVisites=0;
                double paye = 0;
                double total = 0;
                double impaye = 0;
                double astreintes = 0;
                double astreintes_payees = 0;

                Vector creneaux = new Vector();
                for (int i = 0; i < _data.getCreneaux().size(); i++) {
                    Creneau cr = _data.getCreneaux().get(i);
                    if ((cr.getDate().before(fin) && (cr.getDate().after(debut))) ||
                            cr.getDate().equals(debut) ||
                            cr.getDate().equals(fin)) {
                        if (cr.getExecutant().equals(e)) {
                            Map cren = new HashMap();
                            cren.put("date", formatShort.format(cr.getDate()));
                            cren.put("nombre_visites", Integer.toString(cr.getVisites().size()));
                            nbVisites+=cr.getVisites().size();
                            cren.put("somme_totale", nf.format(cr.getTotal()).replace(" ", "&#160;"));
                            cren.put("somme_encaissee", nf.format(cr.getTotalPaye()).replace(" ", "&#160;"));
                            cren.put("somme_impayee", nf.format(cr.getTotalImpaye()).replace(" ", "&#160;"));
                            if (cr.getAstreinte()) {
                                cren.put("somme_astreinte", nf.format(Singleton.instance().getDocument().getParametres().getTarifAstreinte()).replace(" ", "&#160;"));
                                if (cr.getAstreintePayee()) {
                                    cren.put("somme_astreinte_payee", nf.format(Singleton.instance().getDocument().getParametres().getTarifAstreinte()).replace(" ", "&#160;"));
                                } else {
                                    cren.put("somme_astreinte_payee", "0.00");
                                }
                            } else {
                                cren.put("somme_astreinte", "---");
                                cren.put("somme_astreinte_payee", "---");
                            }

                            nbCreneaux++;
                            total = total + cr.getTotal();
                            paye = paye + cr.getTotalPaye();
                            impaye = impaye + cr.getTotalImpaye();
                            if (cr.getAstreinte()) {
                                astreintes = astreintes + Singleton.instance().getDocument().getParametres().getTarifAstreinte();
                                if (cr.getAstreintePayee()) {
                                    astreintes_payees = astreintes_payees + Singleton.instance().getDocument().getParametres().getTarifAstreinte();
                                }
                            }
                            creneaux.add(cren);

                            _medecins.add(cr.getExecutant());
                            _creneaux.add(cr);

                        }
                    }
                }

                medecin.put("creneaux", creneaux);
                resume.put("nombre_creneaux", Integer.toString(nbCreneaux));
                resume.put("nombre_visites", Integer.toString(nbVisites));
                resume.put("somme_encaissee", nf.format(paye).replace(" ", "&#160;"));
                resume.put("somme_totale", nf.format(total).replace(" ", "&#160;"));
                resume.put("somme_impayee", nf.format(impaye).replace(" ", "&#160;"));
                resume.put("somme_astreintes", nf.format(astreintes).replace(" ", "&#160;"));
                resume.put("somme_astreintes_payees", nf.format(astreintes_payees).replace(" ", "&#160;"));

                if (nbCreneaux > 0) {
                    parMedecin.add(medecin);
                }
            }
            root.put("medecins", parMedecin);
            root.put("dateGeneration", formatShort.format(new Date()));

            address = File.createTempFile("creneaux" + format.format(debut) + format.format(fin), ".tmp");
            address.deleteOnExit();

            File dir = address.getParentFile();

/*            if (jckAfficheCamemberts.isSelected()) {

                KeypointPNGEncoderAdapter encoder = new KeypointPNGEncoderAdapter();
                encoder.setEncodingAlpha(true);

                root.put("display_graph_type_pie", 1);
                FileOutputStream imgwriter2 = new FileOutputStream(dir + "/typepie.png");
                encoder.encode(getTypeChart().createBufferedImage(640, 480, BufferedImage.BITMASK, null), imgwriter2);
                imgwriter2.close();

                if (jckAfficheRemplacant.isSelected()) {
                    root.put("display_graph_exec_pie", 1);
                    FileOutputStream imgwriter3 = new FileOutputStream(dir + "/execpie.png");
                    encoder.encode(getExecutantChart().createBufferedImage(640, 480, BufferedImage.BITMASK, null), imgwriter3);
                    imgwriter3.close();
                } else {
                    root.put("display_graph_exec_pie", 0);
                }

                root.put("display_graph_source_pie", 1);
                FileOutputStream imgwriter4 = new FileOutputStream(dir + "/sourcepie.png");
                encoder.encode(getSourceChart().createBufferedImage(640, 480, BufferedImage.BITMASK, null), imgwriter4);
                imgwriter4.close();

                root.put("display_graph_paye_pie", 1);
                FileOutputStream imgwriter5 = new FileOutputStream(dir + "/payepie.png");
                encoder.encode(getImpayeChart().createBufferedImage(640, 480, BufferedImage.BITMASK, null), imgwriter5);
                imgwriter5.close();

            } else {*/
                root.put("display_graph_type_pie", 0);
                root.put("display_graph_exec_pie", 0);
                root.put("display_graph_source_pie", 0);
                root.put("display_graph_paye_pie", 0);
           /* }

            if (jckAfficheBarres.isSelected()) {

                KeypointPNGEncoderAdapter encoder = new KeypointPNGEncoderAdapter();
                encoder.setEncodingAlpha(true);

                root.put("display_graph_type_bar", 1);
                FileOutputStream imgwriter2 = new FileOutputStream(dir + "/typebar.png");
                encoder.encode(getTypeChartBar().createBufferedImage(640, 480, BufferedImage.BITMASK, null), imgwriter2);
                imgwriter2.close();

                if (jckAfficheRemplacant.isSelected()) {
                    root.put("display_graph_exec_bar", 1);
                    FileOutputStream imgwriter3 = new FileOutputStream(dir + "/execbar.png");
                    encoder.encode(getExecutantChartBar().createBufferedImage(640, 480, BufferedImage.BITMASK, null), imgwriter3);
                    imgwriter3.close();
                } else {
                    root.put("display_graph_exec_bar", 0);
                }

                root.put("display_graph_source_bar", 1);
                FileOutputStream imgwriter4 = new FileOutputStream(dir + "/sourcebar.png");
                encoder.encode(getSourceChartBar().createBufferedImage(640, 480, BufferedImage.BITMASK, null), imgwriter4);
                imgwriter4.close();

                root.put("display_graph_paye_bar", 1);
                FileOutputStream imgwriter5 = new FileOutputStream(dir + "/payebar.png");
                encoder.encode(getImpayeChartBar().createBufferedImage(640, 480, BufferedImage.BITMASK, null), imgwriter5);
                imgwriter5.close();

            } else {*/
                root.put("display_graph_type_bar", 0);
                root.put("display_graph_exec_bar", 0);
                root.put("display_graph_source_bar", 0);
                root.put("display_graph_paye_bar", 0);
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

    /*JFreeChart getExecutantChart() {
        DefaultPieDataset ds = new DefaultPieDataset();

        for (int i = 0; i < _medecins.size(); i++) {
            Executant e = _medecins.get(i);
            String nom = e.getNom() + " " + e.getPrenom();
            try {
                double tmp = (Double) ds.getValue(nom);
                ds.setValue(nom, tmp + 1);
            } catch (org.jfree.data.UnknownKeyException ex) {
                double tmp = 1;
                ds.insertValue(ds.getKeys().size(), nom, tmp);
            }
        }

        JFreeChart chart;
        chart = ChartFactory.createPieChart("Répartition par médecin", ds, true, true, false);
        chart.setBackgroundPaint(new Color(255, 255, 255, 0));
        ((PiePlot) chart.getPlot()).setBackgroundAlpha(0);
        ((PiePlot) chart.getPlot()).setSimpleLabels(true);
        ((PiePlot) chart.getPlot()).setIgnoreZeroValues(true);
        ((PiePlot) chart.getPlot()).setLabelGenerator(new PieSectionLabelGenerator() {

            public String generateSectionLabel(PieDataset arg0, Comparable arg1) {
                return ((String) arg1) + ": " + ((Double) arg0.getValue(arg1)).toString();
            }

            public AttributedString generateAttributedSectionLabel(PieDataset arg0, Comparable arg1) {
                return new AttributedString(((String) arg1) + ": " + ((Double) arg0.getValue(arg1)).toString());
            }
        });
        return chart;
    }

    JFreeChart getImpayeChart() {
        DefaultPieDataset ds = new DefaultPieDataset();

        ds.insertValue(0, "Payé", 0.0);
        ds.insertValue(1, "Impayé", 0.0);

        for (int i = 0; i < _creneaux.size(); i++) {
            Creneau v = _creneaux.get(i);
            double tmp = (Double) ds.getValue("Payé");
            ds.setValue("Payé", tmp + v.getTotalPaye());
            tmp = (Double) ds.getValue("Impayé");
            ds.setValue("Impayé", tmp + v.getTotalImpaye());

        }

        JFreeChart chart;
        chart = ChartFactory.createPieChart("Répartition payés/impayés", ds, true, true, false);
        chart.setBackgroundPaint(new Color(255, 255, 255, 0));
        ((PiePlot) chart.getPlot()).setBackgroundAlpha(0);
        ((PiePlot) chart.getPlot()).setSimpleLabels(true);
        ((PiePlot) chart.getPlot()).setIgnoreZeroValues(true);
        ((PiePlot) chart.getPlot()).setLabelGenerator(new PieSectionLabelGenerator() {

            public String generateSectionLabel(PieDataset arg0, Comparable arg1) {
                DecimalFormat df = new DecimalFormat("#,##0.00");
                return ((String) arg1) + " - " + df.format((Double) arg0.getValue(arg1)) + " €";
            }

            public AttributedString generateAttributedSectionLabel(PieDataset arg0, Comparable arg1) {
                DecimalFormat df = new DecimalFormat("#,##0.00");
                return new AttributedString(((String) arg1) + " - " + df.format((Double) arg0.getValue(arg1)) + " €");
            }
        });
        return chart;
    }

    JFreeChart getSourceChart() {
        DefaultPieDataset ds = new DefaultPieDataset();

        for (int j = 0; j < _creneaux.size(); j++) {
            for (int i = 0; i < _creneaux.get(j).getVisites().size(); i++) {
                Visite v = _creneaux.get(j).getVisites().get(i);
                String nom = v.getSourcePayement();

                try {
                    double tmp = (Double) ds.getValue(nom);
                    ds.setValue(nom, tmp + v.getTotal());
                } catch (org.jfree.data.UnknownKeyException ex) {
                    ds.insertValue(ds.getKeys().size(), nom, v.getTotal());
                }

            }
        }

        JFreeChart chart;
        chart = ChartFactory.createPieChart("Répartition par source", ds, true, true, false);
        chart.setBackgroundPaint(new Color(255, 255, 255, 0));
        ((PiePlot) chart.getPlot()).setBackgroundAlpha(0);
        ((PiePlot) chart.getPlot()).setSimpleLabels(true);
        ((PiePlot) chart.getPlot()).setIgnoreZeroValues(true);
        ((PiePlot) chart.getPlot()).setLabelGenerator(new PieSectionLabelGenerator() {

            public String generateSectionLabel(PieDataset arg0, Comparable arg1) {
                DecimalFormat df = new DecimalFormat("#,##0.00");
                return ((String) arg1) + " - " + df.format((Double) arg0.getValue(arg1)) + " €";
            }

            public AttributedString generateAttributedSectionLabel(PieDataset arg0, Comparable arg1) {
                DecimalFormat df = new DecimalFormat("#,##0.00");
                return new AttributedString(((String) arg1) + " - " + df.format((Double) arg0.getValue(arg1)) + " €");
            }
        });
        return chart;
    }

    JFreeChart getTypeChart() {
        DefaultPieDataset ds = new DefaultPieDataset();

        for (int j = 0; j < _creneaux.size(); j++) {
            for (int i = 0; i < _creneaux.get(j).getVisites().size(); i++) {
                Visite v = _creneaux.get(j).getVisites().get(i);
                String nom = v.getTypeVisite();

                try {
                    double tmp = (Double) ds.getValue(nom);
                    ds.setValue(nom, tmp + 1);
                } catch (org.jfree.data.UnknownKeyException ex) {
                    double tmp = 1;
                    ds.insertValue(ds.getKeys().size(), nom, tmp);
                }
            }
        }

        JFreeChart chart;
        chart = ChartFactory.createPieChart("Répartition par type", ds, true, true, false);
        chart.setBackgroundPaint(new Color(255, 255, 255, 0));
        ((PiePlot) chart.getPlot()).setBackgroundAlpha(0);
        ((PiePlot) chart.getPlot()).setSimpleLabels(true);
        ((PiePlot) chart.getPlot()).setIgnoreZeroValues(true);
        ((PiePlot) chart.getPlot()).setLabelGenerator(new PieSectionLabelGenerator() {

            public String generateSectionLabel(PieDataset arg0, Comparable arg1) {
                return ((String) arg1) + ": " + ((Double) arg0.getValue(arg1)).toString();
            }

            public AttributedString generateAttributedSectionLabel(PieDataset arg0, Comparable arg1) {
                return new AttributedString(((String) arg1) + ": " + ((Double) arg0.getValue(arg1)).toString());
            }
        });
        return chart;
    }

    JFreeChart getExecutantChartBar() {
        DefaultCategoryDataset ds = new DefaultCategoryDataset();

        for (int i = 0; i < _medecins.size(); i++) {
            Executant e = _medecins.get(i);
            String nom = e.getNom() + " " + e.getPrenom();

            try {
                double tmp = (Double) ds.getValue("", nom);
                ds.setValue(tmp + 1, "", nom);
            } catch (org.jfree.data.UnknownKeyException ex) {
                double tmp = 1;
                ds.addValue(tmp, "", nom);
            }
        }

        JFreeChart chart;
        chart = ChartFactory.createBarChart("Visites par executant", "Executant", "Nombre de visites", ds, PlotOrientation.HORIZONTAL, false, false, false);

        BarRenderer renderer = (BarRenderer) chart.getCategoryPlot().getRenderer();
        chart.getCategoryPlot().setBackgroundAlpha(0);
        chart.setBackgroundPaint(new Color(255, 255, 255, 0));
        GradientPaint gp0 = new GradientPaint(
                0.0f, 0.0f, Color.blue,
                0.0f, 0.0f, new Color(0, 0, 64));
        renderer.setSeriesPaint(0, gp0);

        renderer.setSeriesItemLabelsVisible(0, true);
        ItemLabelPosition pos = new ItemLabelPosition(ItemLabelAnchor.CENTER, TextAnchor.CENTER);
        renderer.setBasePositiveItemLabelPosition(pos);
        renderer.setBaseItemLabelGenerator(new CategoryItemLabelGenerator() {

            public String generateRowLabel(CategoryDataset arg0, int arg1) {
                return ((Double) arg0.getValue("", arg0.getColumnKey(arg1))).toString().replace(".0", "");
            }

            public String generateColumnLabel(CategoryDataset arg0, int arg1) {
                return ((Double) arg0.getValue("", arg0.getColumnKey(arg1))).toString().replace(".0", "");
            }

            public String generateLabel(CategoryDataset arg0, int arg1, int arg2) {
                return ((Double) arg0.getValue(arg1, arg2)).toString().replace(".0", "");
            }
        });
        return chart;
    }

    JFreeChart getImpayeChartBar() {
        DefaultCategoryDataset ds = new DefaultCategoryDataset();

        ds.addValue(0.0, "", "Payé");
        ds.addValue(0.0, "", "Impayé");

        for (int j = 0; j < _creneaux.size(); j++) {
            ds.incrementValue(_creneaux.get(j).getTotalPaye(), "", "Payé");
            ds.incrementValue(_creneaux.get(j).getTotalImpaye(), "", "Impayé");            
        }

        JFreeChart chart;
        chart = ChartFactory.createBarChart("Repartition vistes payées/impayées", "", "Montant", ds, PlotOrientation.HORIZONTAL, false, false, false);

        BarRenderer renderer = (BarRenderer) chart.getCategoryPlot().getRenderer();
        chart.getCategoryPlot().setBackgroundAlpha(0);
        GradientPaint gp0 = new GradientPaint(
                0.0f, 0.0f, Color.red,
                0.0f, 0.0f, new Color(64, 0, 0));
        chart.setBackgroundPaint(new Color(255, 255, 255, 0));
        renderer.setSeriesPaint(0, gp0);
        renderer.setSeriesItemLabelsVisible(0, true);
        ItemLabelPosition pos = new ItemLabelPosition(ItemLabelAnchor.CENTER, TextAnchor.CENTER);
        renderer.setBasePositiveItemLabelPosition(pos);
        renderer.setBaseItemLabelGenerator(new CategoryItemLabelGenerator() {

            public String generateRowLabel(CategoryDataset arg0, int arg1) {
                return ((Double) arg0.getValue("", arg0.getColumnKey(arg1))).toString() + " €";
            }

            public String generateColumnLabel(CategoryDataset arg0, int arg1) {
                return ((Double) arg0.getValue("", arg0.getColumnKey(arg1))).toString() + " €";
            }

            public String generateLabel(CategoryDataset arg0, int arg1, int arg2) {
                return ((Double) arg0.getValue(arg1, arg2)).toString() + " €";
            }
        });
        return chart;
    }

    JFreeChart getSourceChartBar() {
        DefaultCategoryDataset ds = new DefaultCategoryDataset();

        for (int j = 0; j < _creneaux.size(); j++) {
            for (int i = 0; i < _creneaux.get(j).getVisites().size(); i++) {
                Visite v = _creneaux.get(j).getVisites().get(i);
                String nom = v.getSourcePayement();

                try {
                    double tmp = (Double) ds.getValue("", nom);
                    ds.incrementValue(v.getTotal(), "", nom);
                } catch (org.jfree.data.UnknownKeyException ex) {
                    ds.addValue(v.getTotal(), "", nom);
                }
            }
        }

        JFreeChart chart;
        chart = ChartFactory.createBarChart("Sources de payement", "Source", "Montant", ds, PlotOrientation.HORIZONTAL, false, false, false);

        BarRenderer renderer = (BarRenderer) chart.getCategoryPlot().getRenderer();
        chart.getCategoryPlot().setBackgroundAlpha(0);
        GradientPaint gp0 = new GradientPaint(
                0.0f, 0.0f, Color.green,
                0.0f, 0.0f, new Color(0, 64, 0));
        chart.setBackgroundPaint(new Color(255, 255, 255, 0));
        renderer.setSeriesPaint(0, gp0);
        renderer.setSeriesItemLabelsVisible(0, true);
        ItemLabelPosition pos = new ItemLabelPosition(ItemLabelAnchor.CENTER, TextAnchor.CENTER);
        renderer.setBasePositiveItemLabelPosition(pos);
        renderer.setBaseItemLabelGenerator(new CategoryItemLabelGenerator() {

            public String generateRowLabel(CategoryDataset arg0, int arg1) {
                return ((Double) arg0.getValue("", arg0.getColumnKey(arg1))).toString() + " €";
            }

            public String generateColumnLabel(CategoryDataset arg0, int arg1) {
                return ((Double) arg0.getValue("", arg0.getColumnKey(arg1))).toString() + " €";
            }

            public String generateLabel(CategoryDataset arg0, int arg1, int arg2) {
                return ((Double) arg0.getValue(arg1, arg2)).toString() + " €";
            }
        });
        return chart;
    }

    JFreeChart getTypeChartBar() {
        DefaultCategoryDataset ds = new DefaultCategoryDataset();

        for (int j = 0; j < _creneaux.size(); j++) {
            for (int i = 0; i < _creneaux.get(j).getVisites().size(); i++) {
                Visite v = _creneaux.get(j).getVisites().get(i);
                String nom = v.getTypeVisite();

                try {
                    double tmp = (Double) ds.getValue("", nom);
                    ds.incrementValue(1, "", nom);
                } catch (org.jfree.data.UnknownKeyException ex) {
                    double tmp = 1;
                    ds.addValue(tmp, "", nom);
                }
            }
        }

        JFreeChart chart;
        chart = ChartFactory.createBarChart("Visites par type", "Type", "Nombre", ds, PlotOrientation.HORIZONTAL, false, false, false);

        chart.setBackgroundPaint(new Color(255, 255, 255, 0));
        BarRenderer renderer = (BarRenderer) chart.getCategoryPlot().getRenderer();
        chart.getCategoryPlot().setBackgroundAlpha(0);
        GradientPaint gp0 = new GradientPaint(
                0.0f, 0.0f, new Color(255, 255, 0),
                0.0f, 0.0f, new Color(64, 64, 0));
        renderer.setSeriesPaint(0, gp0);
        renderer.setSeriesItemLabelsVisible(0, true);
        ItemLabelPosition pos = new ItemLabelPosition(ItemLabelAnchor.CENTER, TextAnchor.CENTER);
        renderer.setBasePositiveItemLabelPosition(pos);
        renderer.setBaseItemLabelGenerator(new CategoryItemLabelGenerator() {

            public String generateRowLabel(CategoryDataset arg0, int arg1) {
                return ((Double) arg0.getValue("", arg0.getColumnKey(arg1))).toString().replace(".0", "");
            }

            public String generateColumnLabel(CategoryDataset arg0, int arg1) {
                return ((Double) arg0.getValue("", arg0.getColumnKey(arg1))).toString().replace(".0", "");
            }

            public String generateLabel(CategoryDataset arg0, int arg1, int arg2) {
                return ((Double) arg0.getValue(arg1, arg2)).toString().replace(".0", "");
            }
        });
        return chart;
    }*/
}