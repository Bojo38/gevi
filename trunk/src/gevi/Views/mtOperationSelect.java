/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gevi.Views;

import gevi.Model.Creneau;
import gevi.Model.Singleton;
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
public class mtOperationSelect extends AbstractTableModel implements TableCellRenderer {

    Vector _operations;
    Vector<Boolean> _reverser;

    public mtOperationSelect(Vector oper, Vector<Boolean> reverser) {
        _operations = oper;
        _reverser = reverser;
    }

    public int getColumnCount() {
        return 7;
    }

    public int getRowCount() {
        return _operations.size();
    }

    public String getColumnName(int col) {
        switch (col) {
            case 0:
                return "Type";
            case 1:
                return "Date/Payement";
            case 2:
                return "Montant";
            case 3:
                return "Montant reversé";
            case 4:
                return "Patient";
            case 5:
                return "Source du payement";
            case 6:
                return "Reversé";
        }
        return "";
    }

    public Object getValueAt(int row, int col) {
        Object obj = (Object) _operations.get(row);
        if (obj instanceof Creneau) {
            Creneau c = (Creneau) obj;
            switch (col) {
                case 0:
                    return "Astreinte";
                case 1:
                    SimpleDateFormat f = new SimpleDateFormat("EEEEEEEEE dd MMMMMMMMMMM yyyy");
                    return f.format(c.getDate());
                case 2:
                    return Singleton.instance().getDocument().getParametres().getTarifAstreinte();
                case 3:
                    return Singleton.instance().getDocument().getParametres().getTarifAstreinte();
                case 4:
                    return "";
                case 5:
                    return "Sécurité sociale";
                case 6:
                    return _reverser.get(row);

            }
        }

        if (obj instanceof Visite) {
            Visite v = (Visite) obj;
            switch (col) {
                case 0:
                    return "Visite";
                case 1:                    
                    return v.getDatesPayement();
                case 2:
                    return v.getTotal();
                case 3:
                    return v.getTotal() * (1 - Singleton.instance().getDocument().getParametres().getPrelevement());
                case 4:
                    return v.getNom();
                case 5:
                    return v.getNom();
                case 6:
                    return _reverser.get(row);

            }
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
        if (col == 6) {
            return true;
        }
        return false;
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (row >= 0) {
            if ((column == 2) || (column == 3)) {
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
                if (column == 6) {
                    JCheckBox jcb = new JCheckBox();
                    jcb.setSelected(_reverser.get(row).booleanValue());
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
        return new JLabel((String) value);
    }
}
