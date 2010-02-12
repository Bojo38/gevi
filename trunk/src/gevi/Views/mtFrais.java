/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gevi.Views;

import gevi.Model.Creneau;
import gevi.Model.Depense;
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
public class mtFrais extends AbstractTableModel implements TableCellRenderer {

    Vector<Depense> _depenses;

    public mtFrais(Vector<Depense> dep) {
        _depenses = dep;
    }

    public int getColumnCount() {
        return 6;
    }

    public int getRowCount() {
        return _depenses.size();
    }

    public String getColumnName(int col) {
        switch (col) {
            case 0:
                return "Objet";
            case 1:
                return "Fournisseur";
            case 2:
                return "Categorie";
            case 3:
                return "Montant";
            case 4:
                return "Date";
            case 5:
                return "Source";
        }
        return "";
    }

    public Object getValueAt(int row, int col) {
        Depense depense = (Depense) _depenses.get(row);
        switch (col) {
             case 0:
                return depense.getObjet();
            case 1:
                return depense.getVendeur();
            case 2:
                return depense.getCategorie().getNom();
            case 3:
                return depense.getMontant();
            case 4:
                SimpleDateFormat f = new SimpleDateFormat("EEEEEEEEE dd MMMMMMMMMMM yyyy");
                return f.format(depense.getDate());
            case 5:
                return depense.getSource();
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
