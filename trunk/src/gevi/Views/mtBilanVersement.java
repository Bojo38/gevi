/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gevi.Views;

import gevi.Model.Creneau;
import gevi.Model.Document;
import gevi.Model.Executant;
import gevi.Model.Versement;
import gevi.Model.Visite;
import java.awt.Color;
import java.awt.Component;
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
public class mtBilanVersement extends AbstractTableModel implements TableCellRenderer {

    Document _data;

    public mtBilanVersement(Document data) {
        _data = data;
    }

    public int getColumnCount() {
        return _data.getRemplacants().size() + 1;
    }

    public int getRowCount() {
        return 10;
    }

    public String getColumnName(int col) {
        if (col>0)
        {
        Executant exec = (Executant) _data.getRemplacants().get(col-1);
        return exec.getNom() + ", " + exec.getPrenom();
        }
        else
        {
            return "";
        }
    /*switch (col) {
    case 0:
    return "Nom";
    case 1:
    return "Astreintes";
    case 2:
    return "Payées";
    case 3:
    return "Reversées";
    case 4:
    return "Visites";
    case 5:
    return "Encaissées";
    case 6:
    return "Impayées";
    case 7:
    return "V. a reverser";
    case 8:
    return "Déjà reversé";
    case 9:
    return "V. dues";
    case 10:
    return "Total du";
    }
    return "";*/
    }

    public Object getValueAt(int row, int col) {
        if (col == 0) {
            switch (row) {
                case 0:
                    return "Nombre d'astreintes";
                case 1:
                    return "Astreintes payées";
                case 2:
                    return "Astreintes reversées";
                case 3:
                    return "Total des visites";
                case 4:
                    return "Visites encaissées";
                case 5:
                    return "Visites impayées";
                case 6:
                    return "Reversements";
                case 7:
                    return "Visites déjà reversées";
                case 8:
                    return "Visites dûes";
                case 9:
                    return "Total dû";
            }
            return "";
        } else {
            Executant exec = (Executant) _data.getRemplacants().get(col-1);
            switch (row) {
                case 0:
                    return Integer.valueOf(getNombreAstreintes(exec));
                case 1:
                    return Integer.valueOf(getNombreAstreintesPayees(exec));
                case 2:
                    return Integer.valueOf(getNombreAstreintesReversees(exec));
                case 3:
                    return Double.valueOf(getTotalVisites(exec));
                case 4:
                    return Double.valueOf(getTotalVisitesPayees(exec));
                case 5:
                    return Double.valueOf(getTotalVisitesImpayees(exec));
                case 6:
                    return Double.valueOf(getTotalVisitesPayees(exec) * (1 - _data.getParametres().getPrelevement()));
                case 7:
                    return Double.valueOf(getTotalVerse(exec)-getNombreAstreintesReversees(exec)* _data.getParametres().getTarifAstreinte());
                case 8:
                    return Double.valueOf(getTotalVisitesPayees(exec)* (1 - _data.getParametres().getPrelevement())-(getTotalVerse(exec)-getNombreAstreintesReversees(exec)* _data.getParametres().getTarifAstreinte()));
                case 9:
                    return Double.valueOf(getTotalVisitesPayees(exec) * (1 - _data.getParametres().getPrelevement()) - getTotalVerse(exec) + getNombreAstreintesPayees(exec) * _data.getParametres().getTarifAstreinte());

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
            if (value instanceof String) {
                if (column==0)
                {
                    JLabel l=new JLabel((String)value);
                    l.setHorizontalAlignment(JLabel.CENTER);
                    return l;
                }else
                {
                JTextField jtf = new JTextField((String) value);
                jtf.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                if (isSelected) {
                    jtf.setBackground(Color.DARK_GRAY);
                    jtf.setForeground(Color.WHITE);
                }
                return jtf;
                }
            }
            if (value instanceof Double) {
                JFormattedTextField jftt = new JFormattedTextField((Double) value);
                jftt.setEditable(false);
                jftt.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getCurrencyInstance())));
                jftt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                if ((row == 3) || (row == 4) || (row == 5)) {
                    if (isSelected) {
                        jftt.setBackground(new Color(0, 150, 0));
                        jftt.setForeground(Color.BLACK);
                    } else {
                        jftt.setBackground(new Color(0, 75, 0));
                        jftt.setForeground(Color.WHITE);
                    }
                } else {
                    if ((row == 7) || (row == 8) || (row == 6)) {
                        if (isSelected) {
                            jftt.setBackground(new Color(150, 0, 0));
                            jftt.setForeground(Color.BLACK);
                        } else {
                            jftt.setBackground(new Color(75, 0, 0));
                            jftt.setForeground(Color.WHITE);
                        }
                    } else {
                        if (isSelected) {
                            jftt.setBackground(Color.DARK_GRAY);
                            jftt.setForeground(Color.WHITE);
                        }
                    }
                }

                return jftt;
            }
            if (value instanceof Integer) {
                JFormattedTextField jftt = new JFormattedTextField((Integer) value);
                jftt.setEditable(false);
                jftt.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
                jftt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                if ((row == 1) || (row == 2) || (row == 0)) {
                    if (isSelected) {
                        jftt.setBackground(new Color(0, 0, 150));
                        jftt.setForeground(Color.BLACK);
                    } else {
                        jftt.setBackground(new Color(0, 0, 75));
                        jftt.setForeground(Color.WHITE);
                    }
                } else {
                    if (isSelected) {
                        jftt.setBackground(Color.DARK_GRAY);
                        jftt.setForeground(Color.WHITE);
                    }
                }
                return jftt;
            }
        }
        return new JLabel((String) value);
    }

    private int getNombreAstreintes(Executant e) {
        int astreintes = 0;
        for (int i = 0; i < _data.getCreneaux().size(); i++) {
            Creneau c = (Creneau) _data.getCreneaux().get(i);
            if (c.getExecutant().equals(e)) {
                if (c.getAstreinte()) {
                    astreintes++;
                }
            }
        }
        return astreintes;
    }

    private int getNombreAstreintesPayees(Executant e) {
        int astreintes = 0;
        for (int i = 0; i < _data.getCreneaux().size(); i++) {
            Creneau c = (Creneau) _data.getCreneaux().get(i);
            if (c.getExecutant().equals(e)) {
                if (c.getAstreintePayee()) {
                    astreintes++;
                }
            }
        }
        return astreintes;
    }

    private int getNombreAstreintesImpayees(Executant e) {
        int astreintes = 0;
        for (int i = 0; i < _data.getCreneaux().size(); i++) {
            Creneau c = (Creneau) _data.getCreneaux().get(i);
            if (c.getExecutant().equals(e)) {
                if (!c.getAstreintePayee()) {
                    astreintes++;
                }
            }
        }
        return astreintes;
    }

    private int getNombreAstreintesReversees(Executant e) {
        int astreintes = 0;
        for (int i = 0; i < _data.getCreneaux().size(); i++) {
            Creneau c = (Creneau) _data.getCreneaux().get(i);
            if (c.getExecutant().equals(e)) {
                if (c.getVersementId()>=0) {
                    astreintes++;
                }
            }
        }
        return astreintes;
    }

    private double getTotalVisites(Executant e) {
        double total = 0;
        for (int i = 0; i < _data.getCreneaux().size(); i++) {
            Creneau c = (Creneau) _data.getCreneaux().get(i);
            if (c.getExecutant().equals(e)) {
                for (int j = 0; j < c.getVisites().size(); j++) {
                    Visite v = c.getVisites().get(j);
                    total = total + v.getTotal();
                }
            }
        }
        return total;
    }

    private double getTotalVisitesPayees(Executant e) {
        double total = 0;
        for (int i = 0; i < _data.getCreneaux().size(); i++) {
            Creneau c = (Creneau) _data.getCreneaux().get(i);
            if (c.getExecutant().equals(e)) {
                for (int j = 0; j < c.getVisites().size(); j++) {
                    Visite v = c.getVisites().get(j);
                    total = total + v.getSommePayée();
                }
            }
        }
        return total;
    }

    /*private double getTotalVisitesReversees(Executant e) {
        double total = 0;
        for (int i = 0; i < _data.getCreneaux().size(); i++) {
            Creneau c = (Creneau) _data.getCreneaux().get(i);
            if (c.getExecutant().equals(e)) {
                for (int j = 0; j < c.getVisites().size(); j++) {
                    Visite v = c.getVisites().get(j);
                    if (v.getPaye()&&(v.getVersementId()>=0)) {
                        total = total + v.getTotal();
                    }
                }
            }
        }
        return total;
    }*/

    private double getTotalVisitesImpayees(Executant e) {
        double total = 0;
        for (int i = 0; i < _data.getCreneaux().size(); i++) {
            Creneau c = (Creneau) _data.getCreneaux().get(i);
            if (c.getExecutant().equals(e)) {
                for (int j = 0; j < c.getVisites().size(); j++) {
                    Visite v = c.getVisites().get(j);
                    total = total + v.getTotal()-v.getSommePayée();
                }
            }
        }
        return total;
    }

    private double getTotalVerse(Executant e) {
        double total = 0;
        for (int i = 0; i < _data.getVersements().size(); i++) {
            Versement v = (Versement) _data.getVersements().get(i);
            if (v.getExecutant().equals(e)) {
                total = total + v.getMontant();
            }
        }
        return total;
    }
}
