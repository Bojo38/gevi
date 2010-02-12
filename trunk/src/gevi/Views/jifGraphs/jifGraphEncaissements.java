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
import org.jfree.chart.plot.MultiplePiePlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.TextAnchor;
import org.jfree.util.TableOrder;

/**
 *
 * @author root
 */
public class jifGraphEncaissements extends javax.swing.JInternalFrame {

    Document _data;
    Vector<Visite> _visitesPayees;
    Vector<Visite> _visitesImpayees;
    Vector<Creneau> _astreintesPayees;
    Vector<Creneau> _astreintesImpayees;
    Vector<Executant> _medecinsPayees;
    Vector<Executant> _medecinsImpayees;
    Date _debut;
    Date _fin;
    ChartPanel _panelExecutants;
    ChartPanel _panelType;
    ChartPanel _panelSource;
    ChartPanel _panelAstreintes;

    /** Creates new form jifPeriodReport */
    public jifGraphEncaissements(Document data, Date debut, Date fin) {
        initComponents();
        this.setPreferredSize(new Dimension(640, 480));
        _data = data;
        _visitesPayees = new Vector();
        _astreintesPayees = new Vector();
        _visitesImpayees = new Vector();
        _astreintesImpayees = new Vector();
        _medecinsPayees = new Vector();
        _medecinsImpayees = new Vector();
        _debut = debut;
        _fin = fin;
        SimpleDateFormat format = new SimpleDateFormat("EEEEEEE dd MMMMMMMMMMM yyyy");
        String titre = "Graphique (Barres) des encaissements du " + format.format(_debut) + " au " + format.format(_fin);

        this.setTitle(titre);

        JFreeChart chartAstreintes = ChartFactory.createPieChart("Astreintes", null, true, true, false);
        JFreeChart chartMedecins = ChartFactory.createPieChart("Encaissements par medecins", null, true, true, false);
        JFreeChart chartSource = ChartFactory.createPieChart("Encaissements par source de payement", null, true, true, false);
        JFreeChart chartType = ChartFactory.createPieChart("Encaissements par type", null, true, true, false);

        _panelExecutants = new ChartPanel(chartMedecins);
        _panelAstreintes = new ChartPanel(chartAstreintes);
        _panelSource = new ChartPanel(chartSource);
        _panelType = new ChartPanel(chartType);

        jpn1.add(_panelExecutants, BorderLayout.CENTER);
        jpn2.add(_panelAstreintes, BorderLayout.CENTER);
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
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/gevi/images/sos_rose.png"))); // NOI18N
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
        _panelAstreintes.createChartPrintJob();
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
        _visitesPayees.clear();
        _astreintesPayees.clear();
        _visitesImpayees.clear();
        _astreintesImpayees.clear();
        _medecinsPayees.clear();
        _medecinsImpayees.clear();

        Vector<Creneau> cren = Singleton.instance().getDocument().getCreneaux();

        for (int i = 0; i < cren.size(); i++) {
            Creneau c = cren.get(i);
            Vector visites = c.getVisites();
            Date d = c.getDate();
            if (c.getAstreintePayee()) {
                if ((d.before(_fin) && d.after(_debut)) || (d.equals(_fin)) || (d.equals(_debut))) {
                    _astreintesPayees.add(c);
                }
            } else {
                if (c.getAstreinte()) {
                    _astreintesImpayees.add(c);
                }
            }

            for (int j = 0; j < c.getVisites().size(); j++) {
                Visite v = c.getVisites().get(j);
                for (int k = 0; k < v.getPayements().size(); k++) {
                    d = c.getVisites().get(j).getPayements().get(k).getDatePayement();
                    if (d != null) {
                        if ((d.before(_fin) && d.after(_debut)) || (d.equals(_fin)) || (d.equals(_debut))) {
                            _medecinsPayees.add(c.getExecutant());
                            _visitesPayees.add(c.getVisites().get(j));
                        }
                    } else {
                        _medecinsImpayees.add(c.getExecutant());
                        _visitesImpayees.add(c.getVisites().get(j));
                    }
                }
            }
        }

        if (jrbBarres.isSelected()) {
            _panelExecutants.setChart(getExecutantChart());
            _panelAstreintes.setChart(getAstreinteChart());
            _panelSource.setChart(getSourceChart());
            _panelType.setChart(getTypeChart());
        } else {
            _panelExecutants.setChart(getExecutantPie());
            _panelAstreintes.setChart(getAstreintePie());
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

    public JFreeChart getExecutantChart() {
        DefaultCategoryDataset ds = new DefaultCategoryDataset();

        for (int i = 0; i < _visitesPayees.size(); i++) {
            Executant e = _medecinsPayees.get(i);
            String nom = e.getNom() + " " + e.getPrenom();

            try {
                double tmp = (Double) ds.getValue("Payé", nom);
                ds.setValue(tmp + _visitesPayees.get(i).getTotal(), "Payé", nom);
            } catch (org.jfree.data.UnknownKeyException ex) {
                double tmp = _visitesPayees.get(i).getTotal();
                ds.addValue(tmp, "Payé", nom);
            }
        }

        for (int i = 0; i < _visitesImpayees.size(); i++) {
            Executant e = _medecinsImpayees.get(i);
            String nom = e.getNom() + " " + e.getPrenom();

            try {
                double tmp = (Double) ds.getValue("Impayé", nom);
                ds.setValue(tmp + _visitesImpayees.get(i).getTotal(), "Impayé", nom);
            } catch (org.jfree.data.UnknownKeyException ex) {
                double tmp = _visitesImpayees.get(i).getTotal();
                ds.addValue(tmp, "Impayé", nom);
            } catch (NullPointerException ex) {
                double tmp = _visitesImpayees.get(i).getTotal();
                ds.addValue(tmp, "Impayé", nom);
            }
        }

        JFreeChart chart;
        chart = ChartFactory.createStackedBarChart("Encaissement des visites par Executant", "Executant", "Montant", ds, PlotOrientation.HORIZONTAL, true, true, false);

        BarRenderer renderer = (BarRenderer) chart.getCategoryPlot().getRenderer();
        chart.getCategoryPlot().setBackgroundAlpha(0);
        GradientPaint gp0 = new GradientPaint(
                0.0f, 0.0f, Color.blue,
                0.0f, 0.0f, new Color(0, 0, 64));
        renderer.setSeriesPaint(0, gp0);
        GradientPaint gp1 = new GradientPaint(
                0.0f, 0.0f, Color.red,
                0.0f, 0.0f, new Color(64, 0, 0));
        renderer.setSeriesPaint(1, gp1);
        renderer.setSeriesItemLabelsVisible(0, true);
        renderer.setSeriesItemLabelsVisible(1, true);
        ItemLabelPosition pos = new ItemLabelPosition(ItemLabelAnchor.CENTER, TextAnchor.CENTER);
        renderer.setBasePositiveItemLabelPosition(pos);
        renderer.setBaseItemLabelGenerator(new CategoryItemLabelGenerator() {

            public String generateRowLabel(
                    CategoryDataset arg0, int arg1) {
                DecimalFormat df = new DecimalFormat("#,##0.00");
                return df.format((Double) arg0.getValue(arg0.getRowKey(arg1), "")) + " €";
            }

            public String generateColumnLabel(
                    CategoryDataset arg0, int arg1) {
                DecimalFormat df = new DecimalFormat("#,##0.00");
                return df.format((Double) arg0.getValue("", arg0.getColumnKey(arg1))) + " €";
            }

            public String generateLabel(
                    CategoryDataset arg0, int arg1, int arg2) {
                DecimalFormat df = new DecimalFormat("#,##0.00");
                return df.format((Double) arg0.getValue(arg1, arg2)) + " €";
            }
        });
        return chart;
    }

    public JFreeChart getAstreinteChart() {
        DefaultCategoryDataset ds = new DefaultCategoryDataset();

        for (int i = 0; i < _astreintesPayees.size(); i++) {
            Creneau c = _astreintesPayees.get(i);
            Executant e = c.getExecutant();
            String nom = e.getNom() + " " + e.getPrenom();

            try {
                double tmp = (Double) ds.getValue("Payé", nom);
                ds.setValue(tmp + Singleton.instance().getDocument().getParametres().getTarifAstreinte(), "Payé", nom);
            } catch (org.jfree.data.UnknownKeyException ex) {
                double tmp = Singleton.instance().getDocument().getParametres().getTarifAstreinte();
                ds.addValue(tmp, "Payé", nom);
            }
        }

        for (int i = 0; i < _astreintesImpayees.size(); i++) {
            Creneau c = _astreintesImpayees.get(i);
            Executant e = c.getExecutant();
            String nom = e.getNom() + " " + e.getPrenom();

            try {
                double tmp = (Double) ds.getValue("Impayé", nom);
                ds.setValue(tmp + Singleton.instance().getDocument().getParametres().getTarifAstreinte(), "Impayé", nom);
            } catch (org.jfree.data.UnknownKeyException ex) {
                double tmp = Singleton.instance().getDocument().getParametres().getTarifAstreinte();
                ds.addValue(tmp, "Impayé", nom);
            }
        }

        JFreeChart chart;
        chart = ChartFactory.createStackedBarChart("Astreintes par executant", "Executant", "Montant", ds, PlotOrientation.HORIZONTAL, true, true, false);

        BarRenderer renderer = (BarRenderer) chart.getCategoryPlot().getRenderer();
        chart.getCategoryPlot().setBackgroundAlpha(0);
        GradientPaint gp0 = new GradientPaint(
                0.0f, 0.0f, Color.green,
                0.0f, 0.0f, new Color(0, 64, 0));
        renderer.setSeriesPaint(0, gp0);
        GradientPaint gp1 = new GradientPaint(
                0.0f, 0.0f, new Color(255, 128, 128),
                0.0f, 0.0f, new Color(64, 32, 32));
        renderer.setSeriesPaint(1, gp1);
        renderer.setSeriesItemLabelsVisible(0, true);
        renderer.setSeriesItemLabelsVisible(1, true);
        ItemLabelPosition pos = new ItemLabelPosition(ItemLabelAnchor.CENTER, TextAnchor.CENTER);
        renderer.setBasePositiveItemLabelPosition(pos);
        renderer.setBaseItemLabelGenerator(new CategoryItemLabelGenerator() {

            public String generateRowLabel(
                    CategoryDataset arg0, int arg1) {
                DecimalFormat df = new DecimalFormat("#,##0.00");
                return df.format((Double) arg0.getValue(arg0.getRowKey(arg1), "")) + " €";
            }

            public String generateColumnLabel(
                    CategoryDataset arg0, int arg1) {
                DecimalFormat df = new DecimalFormat("#,##0.00");
                return df.format((Double) arg0.getValue("", arg0.getColumnKey(arg1))) + " €";
            }

            public String generateLabel(
                    CategoryDataset arg0, int arg1, int arg2) {
                DecimalFormat df = new DecimalFormat("#,##0.00");
                return df.format((Double) arg0.getValue(arg1, arg2)) + " €";
            }
        });
        return chart;
    }

    public JFreeChart getSourceChart() {
        DefaultCategoryDataset ds = new DefaultCategoryDataset();

        for (int i = 0; i < _visitesPayees.size(); i++) {
            for (int k = 0; k < _visitesPayees.get(i).getPayements().size(); k++) {
                String nom = _visitesPayees.get(i).getPayements().get(k).getSourcePayement();
                try {
                    double tmp = (Double) ds.getValue("Payé", nom);
                    ds.setValue(tmp + _visitesPayees.get(i).getTotal(), "Payé", nom);
                } catch (org.jfree.data.UnknownKeyException ex) {
                    double tmp = _visitesPayees.get(i).getTotal();
                    ds.addValue(tmp, "Payé", nom);
                }
            }
        }

        for (int i = 0; i < _visitesImpayees.size(); i++) {
            for (int k = 0; k < _visitesImpayees.get(i).getPayements().size(); k++) {
                String nom = _visitesImpayees.get(i).getPayements().get(k).getSourcePayement();
                try {
                    double tmp = (Double) ds.getValue("Impayé", nom);
                    ds.setValue(tmp + _visitesImpayees.get(i).getTotal(), "Impayé", nom);
                } catch (org.jfree.data.UnknownKeyException ex) {
                    double tmp = _visitesImpayees.get(i).getTotal();
                    ds.addValue(tmp, "Impayé", nom);
                } catch (NullPointerException ex) {
                    double tmp = _visitesImpayees.get(i).getTotal();
                    ds.addValue(tmp, "Impayé", nom);
                }
            }
        }

        JFreeChart chart;
        chart = ChartFactory.createStackedBarChart("Encaissement des visites par source de payement", "Source", "Montant", ds, PlotOrientation.HORIZONTAL, true, true, false);

        BarRenderer renderer = (BarRenderer) chart.getCategoryPlot().getRenderer();
        chart.getCategoryPlot().setBackgroundAlpha(0);
        GradientPaint gp0 = new GradientPaint(
                0.0f, 0.0f, new Color(255, 255, 0),
                0.0f, 0.0f, new Color(64, 64, 0));
        renderer.setSeriesPaint(0, gp0);
        GradientPaint gp1 = new GradientPaint(
                0.0f, 0.0f, new Color(0, 255, 255),
                0.0f, 0.0f, new Color(0, 64, 64));
        renderer.setSeriesPaint(1, gp1);
        renderer.setSeriesItemLabelsVisible(0, true);
        renderer.setSeriesItemLabelsVisible(1, true);
        ItemLabelPosition pos = new ItemLabelPosition(ItemLabelAnchor.CENTER, TextAnchor.CENTER);
        renderer.setBasePositiveItemLabelPosition(pos);
        renderer.setBaseItemLabelGenerator(new CategoryItemLabelGenerator() {

            public String generateRowLabel(
                    CategoryDataset arg0, int arg1) {
                DecimalFormat df = new DecimalFormat("#,##0.00");
                return df.format((Double) arg0.getValue(arg0.getRowKey(arg1), "")) + " €";
            }

            public String generateColumnLabel(
                    CategoryDataset arg0, int arg1) {
                DecimalFormat df = new DecimalFormat("#,##0.00");
                return df.format((Double) arg0.getValue("", arg0.getColumnKey(arg1))) + " €";
            }

            public String generateLabel(
                    CategoryDataset arg0, int arg1, int arg2) {
                DecimalFormat df = new DecimalFormat("#,##0.00");
                return df.format((Double) arg0.getValue(arg1, arg2)) + " €";
            }
        });
        return chart;
    }

    public JFreeChart getTypeChart() {
        DefaultCategoryDataset ds = new DefaultCategoryDataset();

        for (int i = 0; i < _visitesPayees.size(); i++) {
            String nom = _visitesPayees.get(i).getTypeVisite();

            try {
                double tmp = (Double) ds.getValue("Payé", nom);
                ds.setValue(tmp + _visitesPayees.get(i).getTotal(), "Payé", nom);
            } catch (org.jfree.data.UnknownKeyException ex) {
                double tmp = _visitesPayees.get(i).getTotal();
                ds.addValue(tmp, "Payé", nom);
            }
        }

        for (int i = 0; i < _visitesImpayees.size(); i++) {
            String nom = _visitesImpayees.get(i).getTypeVisite();

            try {
                double tmp = (Double) ds.getValue("Impayé", nom);
                ds.setValue(tmp + _visitesImpayees.get(i).getTotal(), "Impayé", nom);
            } catch (org.jfree.data.UnknownKeyException ex) {
                double tmp = _visitesImpayees.get(i).getTotal();
                ds.addValue(tmp, "Impayé", nom);
            } catch (NullPointerException ex) {
                double tmp = _visitesImpayees.get(i).getTotal();
                ds.addValue(tmp, "Impayé", nom);
            }
        }

        JFreeChart chart;
        chart = ChartFactory.createStackedBarChart("Encaissement des visites par type", "Source", "Montant", ds, PlotOrientation.HORIZONTAL, true, true, false);

        BarRenderer renderer = (BarRenderer) chart.getCategoryPlot().getRenderer();
        chart.getCategoryPlot().setBackgroundAlpha(0);
        GradientPaint gp0 = new GradientPaint(
                0.0f, 0.0f, new Color(255, 0, 255),
                0.0f, 0.0f, new Color(64, 0, 64));
        renderer.setSeriesPaint(0, gp0);
        GradientPaint gp1 = new GradientPaint(
                0.0f, 0.0f, new Color(128, 128, 255),
                0.0f, 0.0f, new Color(32, 32, 64));
        renderer.setSeriesPaint(1, gp1);
        renderer.setSeriesItemLabelsVisible(0, true);
        renderer.setSeriesItemLabelsVisible(1, true);
        ItemLabelPosition pos = new ItemLabelPosition(ItemLabelAnchor.CENTER, TextAnchor.CENTER);
        renderer.setBasePositiveItemLabelPosition(pos);
        renderer.setBaseItemLabelGenerator(new CategoryItemLabelGenerator() {

            public String generateRowLabel(
                    CategoryDataset arg0, int arg1) {
                DecimalFormat df = new DecimalFormat("#,##0.00");
                return df.format((Double) arg0.getValue(arg0.getRowKey(arg1), "")) + " €";
            }

            public String generateColumnLabel(
                    CategoryDataset arg0, int arg1) {
                DecimalFormat df = new DecimalFormat("#,##0.00");
                return df.format((Double) arg0.getValue("", arg0.getColumnKey(arg1))) + " €";
            }

            public String generateLabel(
                    CategoryDataset arg0, int arg1, int arg2) {
                DecimalFormat df = new DecimalFormat("#,##0.00");
                return df.format((Double) arg0.getValue(arg1, arg2)) + " €";
            }
        });
        return chart;
    }

    public JFreeChart getExecutantPie() {
        DefaultCategoryDataset ds = new DefaultCategoryDataset();

        for (int i = 0; i < _visitesPayees.size(); i++) {
            Executant e = _medecinsPayees.get(i);
            String nom = e.getNom() + " " + e.getPrenom();

            try {
                double tmp = (Double) ds.getValue("Payé", nom);
                ds.setValue(tmp + _visitesPayees.get(i).getTotal(), "Payé", nom);
            } catch (org.jfree.data.UnknownKeyException ex) {
                double tmp = _visitesPayees.get(i).getTotal();
                ds.addValue(tmp, "Payé", nom);
            }
        }

        for (int i = 0; i < _visitesImpayees.size(); i++) {
            Executant e = _medecinsImpayees.get(i);
            String nom = e.getNom() + " " + e.getPrenom();

            try {
                double tmp = (Double) ds.getValue("Impayé", nom);
                ds.setValue(tmp + _visitesImpayees.get(i).getTotal(), "Impayé", nom);
            } catch (org.jfree.data.UnknownKeyException ex) {
                double tmp = _visitesImpayees.get(i).getTotal();
                ds.addValue(tmp, "Impayé", nom);
            } catch (NullPointerException ex) {
                double tmp = _visitesImpayees.get(i).getTotal();
                ds.addValue(tmp, "Impayé", nom);
            }
        }

        JFreeChart chart;
        chart = ChartFactory.createMultiplePieChart("Encaissement des visites par executant", ds, TableOrder.BY_COLUMN, false, true, false);

        chart.getPlot().setBackgroundAlpha(0);

        PiePlot p = (PiePlot) ((MultiplePiePlot) chart.getPlot()).getPieChart().getPlot();
        p.setSimpleLabels(true);
        p.setIgnoreZeroValues(true);
        p.setLabelGenerator(new PieSectionLabelGenerator() {

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

    public JFreeChart getAstreintePie() {
        DefaultCategoryDataset ds = new DefaultCategoryDataset();

        for (int i = 0; i < _astreintesPayees.size(); i++) {
            Creneau c = _astreintesPayees.get(i);
            Executant e = c.getExecutant();
            String nom = e.getNom() + " " + e.getPrenom();

            try {
                double tmp = (Double) ds.getValue("Payé", nom);
                ds.setValue(tmp + Singleton.instance().getDocument().getParametres().getTarifAstreinte(), "Payé", nom);
            } catch (org.jfree.data.UnknownKeyException ex) {
                double tmp = Singleton.instance().getDocument().getParametres().getTarifAstreinte();
                ds.addValue(tmp, "Payé", nom);
            }
        }

        for (int i = 0; i < _astreintesImpayees.size(); i++) {
            Creneau c = _astreintesImpayees.get(i);
            Executant e = c.getExecutant();
            String nom = e.getNom() + " " + e.getPrenom();

            try {
                double tmp = (Double) ds.getValue("Impayé", nom);
                ds.setValue(tmp + Singleton.instance().getDocument().getParametres().getTarifAstreinte(), "Impayé", nom);
            } catch (org.jfree.data.UnknownKeyException ex) {
                double tmp = Singleton.instance().getDocument().getParametres().getTarifAstreinte();
                ds.addValue(tmp, "Impayé", nom);
            }
        }

        JFreeChart chart;
        chart = ChartFactory.createMultiplePieChart("Encaissement des visites par executant", ds, TableOrder.BY_COLUMN, false, true, false);

        chart.getPlot().setBackgroundAlpha(0);
        PiePlot p = (PiePlot) ((MultiplePiePlot) chart.getPlot()).getPieChart().getPlot();
        p.setSimpleLabels(true);
        p.setIgnoreZeroValues(true);
        p.setLabelGenerator(new PieSectionLabelGenerator() {

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

    public JFreeChart getSourcePie() {
        DefaultCategoryDataset ds = new DefaultCategoryDataset();

        for (int i = 0; i < _visitesPayees.size(); i++) {
            for (int k = 0; k < _visitesPayees.get(i).getPayements().size(); k++) {
                String nom = _visitesPayees.get(i).getPayements().get(k).getSourcePayement();
                try {
                    double tmp = (Double) ds.getValue("Payé", nom);
                    ds.setValue(tmp + _visitesPayees.get(i).getTotal(), "Payé", nom);
                } catch (org.jfree.data.UnknownKeyException ex) {
                    double tmp = _visitesPayees.get(i).getTotal();
                    ds.addValue(tmp, "Payé", nom);
                }
            }
        }

        for (int i = 0; i < _visitesImpayees.size(); i++) {
            for (int k = 0; k < _visitesImpayees.get(i).getPayements().size(); k++) {
                String nom = _visitesImpayees.get(i).getPayements().get(k).getSourcePayement();
                try {
                    double tmp = (Double) ds.getValue("Impayé", nom);
                    ds.setValue(tmp + _visitesImpayees.get(i).getTotal(), "Impayé", nom);
                } catch (org.jfree.data.UnknownKeyException ex) {
                    double tmp = _visitesImpayees.get(i).getTotal();
                    ds.addValue(tmp, "Impayé", nom);
                } catch (NullPointerException ex) {
                    double tmp = _visitesImpayees.get(i).getTotal();
                    ds.addValue(tmp, "Impayé", nom);
                }
            }
        }

        JFreeChart chart;
        chart = ChartFactory.createMultiplePieChart("Encaissement des visites par executant", ds, TableOrder.BY_COLUMN, false, true, false);

        chart.getPlot().setBackgroundAlpha(0);
        PiePlot p = (PiePlot) ((MultiplePiePlot) chart.getPlot()).getPieChart().getPlot();
        p.setSimpleLabels(true);
        p.setIgnoreZeroValues(true);
        p.setLabelGenerator(new PieSectionLabelGenerator() {

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

    public JFreeChart getTypePie() {
        DefaultCategoryDataset ds = new DefaultCategoryDataset();

        for (int i = 0; i < _visitesPayees.size(); i++) {
            String nom = _visitesPayees.get(i).getTypeVisite();

            try {
                double tmp = (Double) ds.getValue("Payé", nom);
                ds.setValue(tmp + _visitesPayees.get(i).getTotal(), "Payé", nom);
            } catch (org.jfree.data.UnknownKeyException ex) {
                double tmp = _visitesPayees.get(i).getTotal();
                ds.addValue(tmp, "Payé", nom);
            }
        }

        for (int i = 0; i < _visitesImpayees.size(); i++) {
            String nom = _visitesImpayees.get(i).getTypeVisite();

            try {
                double tmp = (Double) ds.getValue("Impayé", nom);
                ds.setValue(tmp + _visitesImpayees.get(i).getTotal(), "Impayé", nom);
            } catch (org.jfree.data.UnknownKeyException ex) {
                double tmp = _visitesImpayees.get(i).getTotal();
                ds.addValue(tmp, "Impayé", nom);
            } catch (NullPointerException ex) {
                double tmp = _visitesImpayees.get(i).getTotal();
                ds.addValue(tmp, "Impayé", nom);
            }
        }

        JFreeChart chart;
        chart = ChartFactory.createMultiplePieChart("Encaissement des visites par executant", ds, TableOrder.BY_COLUMN, false, true, false);

        chart.getPlot().setBackgroundAlpha(0);
        PiePlot p = (PiePlot) ((MultiplePiePlot) chart.getPlot()).getPieChart().getPlot();
        p.setSimpleLabels(true);
        p.setIgnoreZeroValues(true);
        p.setLabelGenerator(new PieSectionLabelGenerator() {

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
}
