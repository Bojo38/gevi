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
public class mtVisites extends AbstractTableModel implements TableCellRenderer {

    Vector _visites;
    Vector _dates_visites;
    Date _date;

    public mtVisites(Creneau data) {
        _visites = data.getVisites();
        _date=data.getDate();
        _dates_visites = null;
    }

    public mtVisites(Vector data, Vector dates) {
        _visites = data;
        _dates_visites = dates;
    }

    public int getColumnCount() {
        return 7;
    }

    public int getRowCount() {
        return _visites.size();
    }

    public String getColumnName(int col) {
        switch (col) {
            case 0:
                return "Date";
            case 1:
                return "Nom du patient";
            case 2:
                return "Diagnostique";
            case 3:
                return "Total";
            case 4:
                return "Somme payée";
            case 5:
                return "Source";
            case 6:
                return "Détails";
        }
        return "";
    }

    public Object getValueAt(int row, int col) {
        Visite visite = (Visite) _visites.get(row);

        switch (col) {
            case 0:
                if (_dates_visites!=null)
                {
                    Date d = (Date) _dates_visites.get(row);
                    SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
                    return f.format(d);
                }
                else
                {
                    SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
                    return f.format(_date);
                }
            case 1:
                return visite.getNom();
            case 2:
                return visite.getDiagnostique();
            case 3:
                return visite.getTotal();
            case 4:
                return visite.getSommePayée();
            case 5:
                return visite.getSourcesPayement();
            case 6:
                return visite.getDescription();
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
        return false;
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (row >= 0) {
            boolean paye = ((Visite) _visites.get(row)).getPaye();
            if ((column == 3) || (column == 4)) {
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
                JTextField jtf = new JTextField((String) value);
                jtf.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                if (isSelected) {
                    jtf.setBackground(Color.DARK_GRAY);
                    jtf.setForeground(Color.WHITE);
                }
                return jtf;
            }

        }
        return new JLabel(
                (String) value);
    }
}
