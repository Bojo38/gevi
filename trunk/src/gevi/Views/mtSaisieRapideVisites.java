/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gevi.Views;

import gevi.Model.Creneau;
import gevi.Model.Visite;
import java.awt.Color;
import java.awt.Component;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author root
 */
public class mtSaisieRapideVisites extends AbstractTableModel implements TableCellRenderer {

    Vector _visites;

    public mtSaisieRapideVisites(Creneau data) {
        _visites = data.getVisites();
    }

    public int getColumnCount() {
        return 14;
    }

    public int getRowCount() {
        return _visites.size();
    }

    public String getColumnName(int col) {
        switch (col) {
            case 0:
                return "Nom du patient";
            case 1:
                return "Diagnostique";
            case 2:
                return "Divers";
            case 3:
                return "Type de visite";
            case 4:
                return "Majoration";
            case 5:
                return "Nourisson";
            case 6:
                return "Urgence Vitale";
            case 7:
                return "ECG";
            case 8:
                return "ECG Domicile";
            case 9:
                return "IK";
            case 10:
                return "IKM";
            case 11:
                return "Dépassement";
            case 12:
                return "Total";
            case 13:
                return "Payé";
        }
        return "";
    }

    public Object getValueAt(int row, int col) {
        Visite visite = (Visite) _visites.get(row);

        switch (col) {
            case 0:
                return visite.getNom();
            case 1:
                return visite.getDiagnostique();
            case 2:
                return visite.getDescription();
            case 3:
                return visite.getTypeVisite();
            case 4:
                return visite.getMajorationPeriode();
            case 5:
                return visite.getMajorationNourrisson();
            case 6:
                return visite.getUrgenceVitale();
            case 7:
                return visite.getECG();
            case 8:
                return visite.getECGDomicile();
            case 9:
                return visite.getIK();
            case 10:
                return visite.getIKM();
            case 11:
                return visite.getDepassement();
            case 12:
                return visite.getTotal();
            case 13:
                /* Premier moyen de payement*/
                if (visite.getPayements().size() > 1) {
                    return "Payements multiples";
                }
                if (visite.getPayements().size() == 1) {
                    return visite.getPayements().get(0).getMoyenPayement();
                }
                if (visite.getPayements().size() == 0) {
                    return "Impayé";
                }
                return "";
        }
        return "";
    }

    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    /*
     * Don't need to implement this method unless your table's
     * editable.
     */
    public boolean isCellEditable(int row, int col) {
        //Note that the data/cell address is constant,
        //no matter where the cell appears onscreen.
        if (col == 12) {
            return false;
        } else {
            return true;
        }
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (row >= 0) {
            boolean paye = ((Visite) _visites.get(row)).getPaye();
            if (column == 12) {
                JFormattedTextField jftt = new JFormattedTextField((Double) value);
                jftt.setEditable(false);
                jftt.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getCurrencyInstance())));
                jftt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                if (!paye) {
                    if (((Visite) _visites.get(row)).getSommePayée() > 0) {
                        jftt.setForeground(Color.ORANGE);
                    } else {
                        jftt.setForeground(Color.RED);
                    }
                } else {
                    jftt.setForeground(new java.awt.Color(0, 153, 0));
                }
                if (isSelected) {
                    jftt.setBackground(Color.DARK_GRAY);
                }
                return jftt;
            } else {
                if ((column == 5) || (column == 6) || (column == 7) || (column == 8)) {
                    JCheckBox jck = new JCheckBox("", (Boolean) value);
                    return jck;
                } else {
                    if ((column == 10) || (column == 9)) {
                        JFormattedTextField jftt = new JFormattedTextField((Integer) value);
                        jftt.setEditable(false);
                        jftt.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
                        jftt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                        return jftt;
                    } else {
                        if (column == 11) {
                            JFormattedTextField jftt = new JFormattedTextField((Double) value);
                            jftt.setEditable(false);
                            jftt.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getCurrencyInstance())));
                            jftt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                            return jftt;
                        } else {
                            JTextField jtf = new JTextField((String) value);
                            jtf.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                            if (isSelected) {
                                jtf.setBackground(Color.DARK_GRAY);
                                jtf.setForeground(Color.WHITE);
                            }
                            return jtf;
                        }
                    }
                }
            }

        }
        return new JLabel((String) value);
    }
}
