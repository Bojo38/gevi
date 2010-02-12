/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gevi.Views;

import gevi.Model.Creneau;
import gevi.Model.Depense;
import gevi.Model.Payement;
import gevi.Model.Visite;
import java.awt.Color;
import java.awt.Component;
import java.text.SimpleDateFormat;
import java.util.Vector;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author root
 */
public class mtPayements extends AbstractTableModel implements TableCellRenderer {

    Vector<Payement> _data;

    public mtPayements(Vector<Payement> data) {
        _data = data;
    }

    public int getColumnCount() {
        return 4;
    }

    public int getRowCount() {
        return _data.size();
    }

    public String getColumnName(int col) {
        switch (col) {
            case 0:
                return "Date de payement";
            case 1:
                return "Source";
            case 2:
                return "Moyen";
            case 3:
                return "Montant";
        }
        return "";
    }

    public Object getValueAt(int row, int col) {
        Payement p = (Payement) _data.get(row);
        switch (col) {
             case 0:
                 SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
                 if (p.getDatePayement()!=null)
                 {
                    return f.format(p.getDatePayement());
                 }
                 else
                 {
                     return "";
                 }
            case 1:
                return p.getSourcePayement();
            case 2:
                return p.getMoyenPayement();
            case 3:
                return p.getMontant();
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
            if (column == 3) {
                JFormattedTextField jftt = new JFormattedTextField((Double) value);
                jftt.setEditable(false);
                jftt.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getCurrencyInstance())));
                jftt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                if (isSelected) {
                    jftt.setBackground(Color.DARK_GRAY);
                    jftt.setForeground(Color.WHITE);
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
        return new JLabel((String) value);
    }
}
