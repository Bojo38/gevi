/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * jifPeriodReport.java
 *
 * Created on 9 avr. 2009, 17:13:13
 */
package gevi.Views.jifGraphs;

import gevi.Views.*;
import gevi.Model.Creneau;
import gevi.Model.Document;
import gevi.Model.Executant;
import gevi.Model.Singleton;
import gevi.Model.Visite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.beans.PropertyVetoException;
import java.text.AttributedString;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
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
public class jifGraphCreneaux extends javax.swing.JInternalFrame {

    Document _data;
    Vector<Creneau> _creneaux;
    Vector<Executant> _medecins;
    Date _debut;
    Date _fin;
    ChartPanel _panelImpaye;
    ChartPanel _panelExecutants;
    ChartPanel _panelType;
    ChartPanel _panelSource;

    /** Creates new form jifPeriodReport */
    public jifGraphCreneaux(Document data, Date debut, Date fin) {
        initComponents();
        this.setPreferredSize(new Dimension(640, 480));
        _data = data;
        _creneaux = new Vector();
        _medecins = new Vector();
        _debut = debut;
        _fin = fin;
        SimpleDateFormat format = new SimpleDateFormat("EEEEEEE dd MMMMMMMMMMM yyyy");
        String titre = "Graphiques (Barres) des créneaux du " + format.format(_debut) + " au " + format.format(_fin);

        this.setTitle(titre);
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        model.addElement("Tous");
        model.addElement(_data.getUtilisateur().getNom() + ", " + _data.getUtilisateur().getPrenom());
        for (int i = 0; i < _data.getRemplacants().size(); i++) {
            Executant e = _data.getRemplacants().get(i);
            model.addElement(e.getNom() + ", " + e.getPrenom() + "(Remplaçant)");
        }

        JFreeChart chartImpaye = ChartFactory.createPieChart("Répartition payés/impayés", null, true, true, false);
        JFreeChart chartMedecins = ChartFactory.createPieChart("Répartition par medecins", null, true, true, false);
        JFreeChart chartSource = ChartFactory.createPieChart("Répartition par source de payement", null, true, true, false);
        JFreeChart chartType = ChartFactory.createPieChart("Répartition par type", null, true, true, false);

        _panelExecutants = new ChartPanel(chartMedecins);
        _panelImpaye = new ChartPanel(chartImpaye);
        _panelSource = new ChartPanel(chartSource);
        _panelType = new ChartPanel(chartType);

        jpn1.add(_panelExecutants, BorderLayout.CENTER);
        jpn2.add(_panelImpaye, BorderLayout.CENTER);
        jpn3.add(_panelSource, BorderLayout.CENTER);
        jpn4.add(_panelType, BorderLayout.CENTER);

        try {
            this.setSelected(true);
        } catch (PropertyVetoException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }

        refresh();
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jbtOK = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jpn1 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jbtImprimer1 = new javax.swing.JButton();
        jpn3 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jbtImprimer3 = new javax.swing.JButton();
        jpn2 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jbtImprimer2 = new javax.swing.JButton();
        jpn4 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jbtImprimer = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jrbCamembert = new javax.swing.JRadioButton();
        jrbBarres = new javax.swing.JRadioButton();

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

        getContentPane().add(jPanel1, java.awt.BorderLayout.SOUTH);

        jPanel2.setLayout(new java.awt.GridLayout(2, 2));

        jpn1.setLayout(new java.awt.BorderLayout());

        jbtImprimer1.setText("Imprimer");
        jbtImprimer1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtImprimer1ActionPerformed(evt);
            }
        });
        jPanel7.add(jbtImprimer1);

        jpn1.add(jPanel7, java.awt.BorderLayout.PAGE_END);

        jPanel2.add(jpn1);

        jpn3.setLayout(new java.awt.BorderLayout());

        jbtImprimer3.setText("Imprimer");
        jbtImprimer3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtImprimer3ActionPerformed(evt);
            }
        });
        jPanel9.add(jbtImprimer3);

        jpn3.add(jPanel9, java.awt.BorderLayout.PAGE_END);

        jPanel2.add(jpn3);

        jpn2.setLayout(new java.awt.BorderLayout());

        jbtImprimer2.setText("Imprimer");
        jbtImprimer2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtImprimer2ActionPerformed(evt);
            }
        });
        jPanel8.add(jbtImprimer2);

        jpn2.add(jPanel8, java.awt.BorderLayout.PAGE_END);

        jPanel2.add(jpn2);

        jpn4.setLayout(new java.awt.BorderLayout());

        jbtImprimer.setText("Imprimer");
        jbtImprimer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtImprimerActionPerformed(evt);
            }
        });
        jPanel10.add(jbtImprimer);

        jpn4.add(jPanel10, java.awt.BorderLayout.PAGE_END);

        jPanel2.add(jpn4);

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        jPanel3.setLayout(new java.awt.GridLayout(1, 2, 1, 1));

        buttonGroup1.add(jrbCamembert);
        jrbCamembert.setSelected(true);
        jrbCamembert.setText("Camembert");
        jrbCamembert.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jrbCamembert.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jrbCamembert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbCamembertActionPerformed(evt);
            }
        });
        jPanel3.add(jrbCamembert);

        buttonGroup1.add(jrbBarres);
        jrbBarres.setText("Barres");
        jrbBarres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbBarresActionPerformed(evt);
            }
        });
        jPanel3.add(jrbBarres);

        getContentPane().add(jPanel3, java.awt.BorderLayout.PAGE_START);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtOKActionPerformed
        MainWindow.getMainWindow().closeWindow(this);
        this.setVisible(false);
    }//GEN-LAST:event_jbtOKActionPerformed

    private void jbtImprimerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtImprimerActionPerformed
        _panelType.createChartPrintJob();
}//GEN-LAST:event_jbtImprimerActionPerformed

    private void jbtImprimer1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtImprimer1ActionPerformed
        _panelExecutants.createChartPrintJob();
    }//GEN-LAST:event_jbtImprimer1ActionPerformed

    private void jbtImprimer2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtImprimer2ActionPerformed
        _panelImpaye.createChartPrintJob();
    }//GEN-LAST:event_jbtImprimer2ActionPerformed

    private void jbtImprimer3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtImprimer3ActionPerformed
        _panelSource.createChartPrintJob();
    }//GEN-LAST:event_jbtImprimer3ActionPerformed

    private void jrbCamembertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbCamembertActionPerformed
        refresh();
}//GEN-LAST:event_jrbCamembertActionPerformed

    private void jrbBarresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbBarresActionPerformed
        refresh();
}//GEN-LAST:event_jrbBarresActionPerformed

    private void refresh() {
        _creneaux.clear();
        _medecins.clear();
        Vector<Creneau> cren = Singleton.instance().getDocument().getCreneaux();

        for (int i = 0; i < cren.size(); i++) {
            Creneau c = cren.get(i);
            Vector visites = c.getVisites();
            Date d = c.getDate();
            if ((d.before(_fin) && d.after(_debut)) || (d.equals(_fin)) || (d.equals(_debut))) {
                _medecins.add(c.getExecutant());
                _creneaux.add(c);
            }
        }

        if (jrbBarres.isSelected()) {
            _panelExecutants.setChart(getExecutantChart());
            _panelImpaye.setChart(getImpayeChart());
            _panelSource.setChart(getSourceChart());
            _panelType.setChart(getTypeChart());
        } else {
            _panelExecutants.setChart(getExecutantPie());
            _panelImpaye.setChart(getImpayePie());
            _panelSource.setChart(getSourcePie());
            _panelType.setChart(getTypePie());
        }

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JButton jbtImprimer;
    private javax.swing.JButton jbtImprimer1;
    private javax.swing.JButton jbtImprimer2;
    private javax.swing.JButton jbtImprimer3;
    private javax.swing.JButton jbtOK;
    private javax.swing.JPanel jpn1;
    private javax.swing.JPanel jpn2;
    private javax.swing.JPanel jpn3;
    private javax.swing.JPanel jpn4;
    private javax.swing.JRadioButton jrbBarres;
    private javax.swing.JRadioButton jrbCamembert;
    // End of variables declaration//GEN-END:variables

    JFreeChart getExecutantChart() {
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
        chart = ChartFactory.createBarChart("Créneau par executant", "Executant", "Nombre de visites", ds, PlotOrientation.HORIZONTAL, false, false, false);

        BarRenderer renderer = (BarRenderer) chart.getCategoryPlot().getRenderer();
        chart.getCategoryPlot().setBackgroundAlpha(0);
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

    JFreeChart getImpayeChart() {
        DefaultCategoryDataset ds = new DefaultCategoryDataset();

        ds.addValue(0.0, "", "Payé");
        ds.addValue(0.0, "", "Impayé");

        for (int i = 0; i < _creneaux.size(); i++) {
            Creneau v = _creneaux.get(i);
            ds.incrementValue(v.getTotalPaye(), "", "Payé");
            ds.incrementValue(v.getTotalImpaye(), "", "Impayé");
        }

        JFreeChart chart;
        chart = ChartFactory.createBarChart("Repartition payés/impayés", "", "Montant", ds, PlotOrientation.HORIZONTAL, false, false, false);

        BarRenderer renderer = (BarRenderer) chart.getCategoryPlot().getRenderer();
        chart.getCategoryPlot().setBackgroundAlpha(0);
        GradientPaint gp0 = new GradientPaint(
                0.0f, 0.0f, Color.red,
                0.0f, 0.0f, new Color(64, 0, 0));
        renderer.setSeriesPaint(0, gp0);
        renderer.setSeriesItemLabelsVisible(0, true);
        ItemLabelPosition pos = new ItemLabelPosition(ItemLabelAnchor.CENTER, TextAnchor.CENTER);
        renderer.setBasePositiveItemLabelPosition(pos);
        renderer.setBaseItemLabelGenerator(new CategoryItemLabelGenerator() {

            public String generateRowLabel(CategoryDataset arg0, int arg1) {
                DecimalFormat df = new DecimalFormat("#,##0.00");
                return df.format((Double) arg0.getValue("", arg0.getColumnKey(arg1))) + " €";
            }

            public String generateColumnLabel(CategoryDataset arg0, int arg1) {
                DecimalFormat df = new DecimalFormat("#,##0.00");
                return df.format((Double) arg0.getValue("", arg0.getColumnKey(arg1))) + " €";
            }

            public String generateLabel(CategoryDataset arg0, int arg1, int arg2) {
                DecimalFormat df = new DecimalFormat("#,##0.00");
                return df.format((Double) arg0.getValue(arg1, arg2)) + " €";
            }
        });
        return chart;
    }

    JFreeChart getSourceChart() {
        DefaultCategoryDataset ds = new DefaultCategoryDataset();

        for (int i = 0; i < _creneaux.size(); i++) {
            Creneau c = _creneaux.get(i);
            for (int j = 0; j < c.getVisites().size(); j++) {
                Visite v = c.getVisites().get(j);
                for (int k = 0; k < v.getPayements().size(); k++) {
                    String nom = v.getPayements().get(k).getSourcePayement();
                    try {
                        double tmp = (Double) ds.getValue("", nom);
                        ds.incrementValue(v.getTotal(), "", nom);
                    } catch (org.jfree.data.UnknownKeyException ex) {
                        ds.addValue(v.getTotal(), "", nom);
                    }
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
        renderer.setSeriesPaint(0, gp0);
        renderer.setSeriesItemLabelsVisible(0, true);
        ItemLabelPosition pos = new ItemLabelPosition(ItemLabelAnchor.CENTER, TextAnchor.CENTER);
        renderer.setBasePositiveItemLabelPosition(pos);
        renderer.setBaseItemLabelGenerator(new CategoryItemLabelGenerator() {

            public String generateRowLabel(CategoryDataset arg0, int arg1) {
                DecimalFormat df = new DecimalFormat("#,##0.00");
                return df.format((Double) arg0.getValue("", arg0.getColumnKey(arg1))) + " €";
            }

            public String generateColumnLabel(CategoryDataset arg0, int arg1) {
                DecimalFormat df = new DecimalFormat("#,##0.00");
                return df.format((Double) arg0.getValue("", arg0.getColumnKey(arg1))) + " €";
            }

            public String generateLabel(CategoryDataset arg0, int arg1, int arg2) {
                DecimalFormat df = new DecimalFormat("#,##0.00");
                return df.format((Double) arg0.getValue(arg1, arg2)) + " €";
            }
        });
        return chart;
    }

    JFreeChart getTypeChart() {
        DefaultCategoryDataset ds = new DefaultCategoryDataset();

        for (int i = 0; i < _creneaux.size(); i++) {
            Creneau c = _creneaux.get(i);
            for (int j = 0; j < c.getVisites().size(); j++) {
                Visite v = c.getVisites().get(j);
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
    }

    JFreeChart getExecutantPie() {
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
        chart.getPlot().setBackgroundAlpha(0);
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

    JFreeChart getImpayePie() {
        DefaultPieDataset ds = new DefaultPieDataset();

        ds.insertValue(0, "Payé", 0.0);
        ds.insertValue(1, "Impayé", 0.0);

        for (int j = 0; j < _creneaux.size(); j++) {
            Creneau v = _creneaux.get(j);
            double tmp = (Double) ds.getValue("Payé");
            ds.setValue("Payé", tmp + v.getTotalPaye());
            tmp = (Double) ds.getValue("Impayé");
            ds.setValue("Impayé", tmp + v.getTotalImpaye());

        }
        JFreeChart chart;
        chart = ChartFactory.createPieChart("Répartition payés/impayés", ds, true, true, false);

        chart.getPlot().setBackgroundAlpha(0);
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

    JFreeChart getSourcePie() {
        DefaultPieDataset ds = new DefaultPieDataset();

        for (int j = 0; j <
                _creneaux.size(); j++) {
            for (int i = 0; i <
                    _creneaux.get(j).getVisites().size(); i++) {
                Visite v = _creneaux.get(j).getVisites().get(i);
                for (int k = 0; k < v.getPayements().size(); k++) {
                    String nom = v.getPayements().get(k).getSourcePayement();
                    try {
                        double tmp = (Double) ds.getValue(nom);
                        ds.setValue(nom, tmp + v.getTotal());
                    } catch (org.jfree.data.UnknownKeyException ex) {
                        ds.insertValue(ds.getKeys().size(), nom, v.getTotal());
                    }
                }

            }
        }

        JFreeChart chart;
        chart =
                ChartFactory.createPieChart("Répartition par source", ds, true, true, false);
        chart.getPlot().setBackgroundAlpha(0);
        ((PiePlot) chart.getPlot()).setSimpleLabels(true);
        ((PiePlot) chart.getPlot()).setIgnoreZeroValues(true);
        ((PiePlot) chart.getPlot()).setLabelGenerator(new PieSectionLabelGenerator() {

            public String generateSectionLabel(
                    PieDataset arg0, Comparable arg1) {
                DecimalFormat df = new DecimalFormat("#,##0.00");
                return ((String) arg1) + " - " + df.format((Double) arg0.getValue(arg1)) + " €";
            }

            public AttributedString generateAttributedSectionLabel(
                    PieDataset arg0, Comparable arg1) {
                DecimalFormat df = new DecimalFormat("#,##0.00");
                return new AttributedString(((String) arg1) + " - " + df.format((Double) arg0.getValue(arg1)) + " €");
            }
        });
        return chart;
    }

    JFreeChart getTypePie() {
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
        chart.getPlot().setBackgroundAlpha(0);
        ((PiePlot) chart.getPlot()).setSimpleLabels(true);
        ((PiePlot) chart.getPlot()).setIgnoreZeroValues(true);
        ((PiePlot) chart.getPlot()).setLabelGenerator(new PieSectionLabelGenerator() {

            public String generateSectionLabel(
                    PieDataset arg0, Comparable arg1) {
                return ((String) arg1) + ": " + ((Double) arg0.getValue(arg1)).toString().replace(".0", "");
            }

            public AttributedString generateAttributedSectionLabel(
                    PieDataset arg0, Comparable arg1) {
                return new AttributedString(((String) arg1) + ": " + ((Double) arg0.getValue(arg1)).toString().replace(".0", ""));
            }
        });
        return chart;
    }
}
