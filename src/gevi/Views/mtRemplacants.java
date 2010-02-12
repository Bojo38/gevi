/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gevi.Views;

import gevi.Model.Executant;
import java.util.Vector;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author root
 */
public class mtRemplacants extends AbstractTableModel {

    Vector _data;

    public mtRemplacants(Vector data) {
        _data = data;
    }

    public int getColumnCount() {
        return 6;
    }

    public int getRowCount() {
        return _data.size();
    }

    public String getColumnName(int col) {
        switch (col) {
            case 0:
                return "Nom";
            case 1:
                return "Prénom";
            case 2:
                return "Adresse";
            case 3:
                return "Téléphone";
            case 4:
                return "Courriel";
            case 5:
                return "Divers";            
        }
        return "";
    }

    public Object getValueAt(int row, int col) {

        Executant exec = (Executant) _data.get(row);
        
        switch (col) {
            case 0:
                return exec.getNom();
            case 1:
                return exec.getPrenom();
            case 2:
                return exec.getAdresse();
            case 3:
                return exec.getTelephone();
            case 4:
                return exec.getCourriel();
            case 5:
                return exec.getDivers();
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
        return true;
    }   
}
