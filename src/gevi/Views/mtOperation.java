/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gevi.Views;

import gevi.Model.Creneau;
import gevi.Model.Depense;
import gevi.Model.Singleton;
import gevi.Model.Versement;
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
public class mtOperation extends AbstractTableModel implements TableCellRenderer {

    Vector _operations;

    public mtOperation(Vector oper) {
        _operations = oper;
    }

    public int getColumnCount() {
        return 6;
    }

    public int getRowCount() {
        return _operations.size();
    }

    public String getColumnName(int col) {
        switch (col) {
            case 0:
                return "Type";
            case 1:
                return "Date";
            case 2:
                return "Date de Payement";
            case 3:
                return "Montant";
            case 4:
                return "Montant reversé";
            case 5:
                return "Patient";
            case 6:
                return "Source du payement";
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
                    SimpleDateFormat f2 = new SimpleDateFormat("EEEEEEEEE dd MMMMMMMMMMM yyyy");
                    return f2.format(c.getDatePayementAstreinte());
                case 3:
                    return Singleton.instance().getDocument().getParametres().getTarifAstreinte();
                case 4:
                    return Singleton.instance().getDocument().getParametres().getTarifAstreinte();
                case 5:
                    return "";
                case 6:
                    return "Sécurité sociale";
                
            }
        }

        if (obj instanceof Visite) {
            Visite v = (Visite) obj;
            switch (col) {
                case 0:
                    return "Visite";
                case 1:
                    SimpleDateFormat f2 = new SimpleDateFormat("EEEEEEEEE dd MMMMMMMMMMM yyyy");
                    for (int i=0; i<Singleton.instance().getDocument().getCreneaux().size();i++)
                    {
                        Creneau cr=Singleton.instance().getDocument().getCreneaux().get(i);
                        for (int j=0; j<cr.getVisites().size();j++)
                        {
                            if (v.equals(cr.getVisites().get(j)))
                            {
                                return f2.format(cr.getDate());
                            }
                        }
                    }
                case 2:
                    return v.getDatesPayement();
                case 3:
                    return v.getTotal();
                case 4:
                    return v.getTotal() * (1 - Singleton.instance().getDocument().getParametres().getPrelevement());
                case 5:
                    return v.getNom();
                case 6:
                    return v.getSourcesPayement();
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
        return false;
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (row >= 0) {
            if ((column == 3) || (column == 4)) {
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
