/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gevi.Views;

import freemarker.template.SimpleNumber;
import gevi.Model.Creneau;
import gevi.Model.Depense;
import gevi.Model.Document;
import gevi.Model.Operation;
import gevi.Model.Payement;
import gevi.Model.Singleton;
import gevi.Model.Versement;
import gevi.Model.Visite;
import java.awt.Color;
import java.awt.Component;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Vector;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

class internalOperation implements Comparable {

    Date _date;
    double _montant;
    String _description;

    public internalOperation(Date date, double montant, String description) {
        _date = date;
        _montant = montant;
        _description = description;
    }

    public int compareTo(Object obj) {
        if (obj instanceof internalOperation) {
            return _date.compareTo(((internalOperation) obj)._date);
        }
        return -1;
    }
}

/**
 *
 * @author root
 */
public class mtComptes extends AbstractTableModel implements TableCellRenderer {

    Vector<internalOperation> _ops;

    public mtComptes(Document data, Date debut, Date fin, boolean banque) {

        SimpleDateFormat f = new SimpleDateFormat("EEEEEEEEE dd MMMMMMMMMMM yyyy");
        _ops = new Vector<internalOperation>();

        if (banque) {
            /* Liste des opération à construire :
             * 0- Solde initial
             * 1- astreintes
             * 2- visites
             * 3- versments depuis la caisse
             * 4- dépenses
             * 5- versement vers la caisse
             * 6- reversements
             */

            /* 0 - Solde initial */
            double solde = data.getBanque().getSoldeBanqueAvant(debut, data);
            _ops.add(new internalOperation(debut, solde, "Solde au " + f.format(debut)));

            /* 1- Astreintes */
            for (int i = 0; i < data.getCreneaux().size(); i++) {
                Creneau cren = data.getCreneaux().get(i);
                if (cren.getAstreintePayee()) {
                    if ((cren.getDatePayementAstreinte().after(debut) ||
                            cren.getDatePayementAstreinte().equals(debut)) && (cren.getDatePayementAstreinte().before(fin) ||
                            cren.getDatePayementAstreinte().equals(fin))) {
                        _ops.add(new internalOperation(cren.getDatePayementAstreinte(),
                                data.getParametres().getTarifAstreinte(),
                                "Astreinte du " + f.format(cren.getDate())));
                    }
                }

                /* 2 - Visites */
                for (int j = 0; j < cren.getVisites().size(); j++) {
                    Visite v = cren.getVisites().get(j);

                    for (int k = 0; k < v.getPayements().size(); k++) {
                        Payement p = v.getPayements().get(k);
                        if (!p.getMoyenPayement().equals("Espèces")) {
                            if (p.getDatePayement() != null) {
                                if ((p.getDatePayement().after(debut) ||
                                        p.getDatePayement().equals(debut)) && (p.getDatePayement().before(fin) ||
                                        p.getDatePayement().equals(fin))) {

                                    if (p.getMontant() != v.getTotal()) {
                                        _ops.add(new internalOperation(p.getDatePayement(),
                                                p.getMontant(),
                                                "Payement partiel - Visite (" + f.format(cren.getDate()) + "): " + v.getNom()));
                                    } else {
                                        _ops.add(new internalOperation(p.getDatePayement(),
                                                p.getMontant(),
                                                "Visite (" + f.format(cren.getDate()) + "): " + v.getNom()));
                                    }
                                }
                            }
                        }
                    }
                }
            }

            /* 3- Versement depuis la caisse */
            for (int i = 0; i < data.getBanque().getOperationsFromCash().size(); i++) {
                Operation op = data.getBanque().getOperationsFromCash().get(i);
                if ((op.getDate().after(debut) ||
                        op.getDate().equals(debut)) && (op.getDate().before(fin) ||
                        op.getDate().equals(fin))) {

                    _ops.add(new internalOperation(op.getDate(),
                            op.getMontant(),
                            "Versement depuis la caisse"));

                }
            }

            /* 4 - Dépenses de banque */
            for (int i = 0; i < data.getDepenses().size(); i++) {
                Depense dep = data.getDepenses().get(i);
                if (dep.getSource().equals("Banque")) {
                    if ((dep.getDate().after(debut) ||
                            dep.getDate().equals(debut)) && (dep.getDate().before(fin) ||
                            dep.getDate().equals(fin))) {
                        _ops.add(new internalOperation(dep.getDate(),
                                -dep.getMontant(),
                                dep.getCategorie().getNom() + ": " + dep.getObjet()));
                    }
                }
            }

            /* 6 - Retraits en liquide vers la caisse */
            for (int i = 0; i < data.getBanque().getOperationsToCash().size(); i++) {
                Operation op = data.getBanque().getOperationsToCash().get(i);
                if ((op.getDate().after(debut) ||
                        op.getDate().equals(debut)) && (op.getDate().before(fin) ||
                        op.getDate().equals(fin))) {

                    _ops.add(new internalOperation(op.getDate(),
                            -op.getMontant(),
                            "Versement vers la caisse"));

                }
            }

            /* 7 - Versement aux remplaçants */
            for (int i = 0; i < data.getVersements().size(); i++) {
                Versement ver = data.getVersements().get(i);
                if ((ver.getDate().after(debut) ||
                        ver.getDate().equals(debut)) && (ver.getDate().before(fin) ||
                        ver.getDate().equals(fin))) {
                    _ops.add(new internalOperation(ver.getDate(),
                            -ver.getMontant(),
                            "Reversement à " + ver.getExecutant().getNom() + " " + ver.getExecutant().getPrenom()));
                }
            }
        } else {
            /* 0 - Solde initial */
            double solde = data.getBanque().getSoldeCashAvant(debut, data);
            _ops.add(new internalOperation(debut, solde, "Solde au " + f.format(debut)));

            /* 1- Astreintes */
            for (int i = 0; i < data.getCreneaux().size(); i++) {
                Creneau cren = data.getCreneaux().get(i);

                /* 2 - Visites */
                for (int j = 0; j < cren.getVisites().size(); j++) {
                    Visite v = cren.getVisites().get(j);
                    for (int k = 0; k < v.getPayements().size(); k++) {
                        Payement p = v.getPayements().get(k);
                        if (p.getMoyenPayement().equals("Espèces")) {
                            if (p.getDatePayement() != null) {
                                if ((p.getDatePayement().after(debut) ||
                                        p.getDatePayement().equals(debut)) && (p.getDatePayement().before(fin) ||
                                        p.getDatePayement().equals(fin))) {

                                    if (p.getMontant() != v.getTotal()) {
                                        _ops.add(new internalOperation(p.getDatePayement(),
                                                p.getMontant(),
                                                "Payement partiel - Visite (" + f.format(cren.getDate()) + "): " + v.getNom()));
                                    } else {
                                        _ops.add(new internalOperation(p.getDatePayement(),
                                                p.getMontant(),
                                                "Visite (" + f.format(cren.getDate()) + "): " + v.getNom()));
                                    }
                                }
                            }
                        }
                    }
                }
            }

            /* 3- Versement depuis la caisse */
            for (int i = 0; i < data.getBanque().getOperationsFromCash().size(); i++) {
                Operation op = data.getBanque().getOperationsFromCash().get(i);
                if ((op.getDate().after(debut) ||
                        op.getDate().equals(debut)) && (op.getDate().before(fin) ||
                        op.getDate().equals(fin))) {

                    _ops.add(new internalOperation(op.getDate(),
                            -op.getMontant(),
                            "Versement depuis la caisse"));

                }
            }

            /* 4 - Dépenses en espèces */
            for (int i = 0; i < data.getDepenses().size(); i++) {
                Depense dep = data.getDepenses().get(i);
                if (dep.getSource().equals("Espèces")) {
                    if ((dep.getDate().after(debut) ||
                            dep.getDate().equals(debut)) && (dep.getDate().before(fin) ||
                            dep.getDate().equals(fin))) {
                        _ops.add(new internalOperation(dep.getDate(),
                                -dep.getMontant(),
                                dep.getCategorie().getNom() + ": " + dep.getObjet()));
                    }
                }
            }

            /* 6 - Retraits en liquide vers la caisse */
            for (int i = 0; i < data.getBanque().getOperationsToCash().size(); i++) {
                Operation op = data.getBanque().getOperationsToCash().get(i);
                if ((op.getDate().after(debut) ||
                        op.getDate().equals(debut)) && (op.getDate().before(fin) ||
                        op.getDate().equals(fin))) {

                    _ops.add(new internalOperation(op.getDate(),
                            op.getMontant(),
                            "Versement vers la caisse"));

                }
            }
        }

        Collections.sort(_ops);

    }

    public int getColumnCount() {
        return 3;
    }

    public int getRowCount() {
        return _ops.size();
    }

    public String getColumnName(int col) {
        switch (col) {
            case 0:
                return "Date";
            case 1:
                return "Montant";
            case 2:
                return "Description";
        }
        return "";
    }

    public Object getValueAt(int row, int col) {
        internalOperation obj = _ops.get(row);
        switch (col) {
            case 0:
                return obj._date;
            case 1:

                return obj._montant;
            case 2:
                return obj._description;
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
        JLabel jlb = new JLabel();
        jlb.setHorizontalAlignment(javax.swing.JLabel.CENTER);

        if (row >= 0) {
            DecimalFormat df = new DecimalFormat("#,##0.00 €");
            SimpleDateFormat f = new SimpleDateFormat("EEEEEEEEE dd MMMMMMMMMMM yyyy");


            switch (column) {
                case 0:
                    jlb.setText(f.format((Date) value));
                    break;
                case 1:
                    if (((Double) value) >= 0.0) {
                        jlb.setForeground(Color.BLUE);
                    } else {
                        jlb.setForeground(Color.RED);
                    }
                    jlb.setText(df.format((Double) value));
                    break;
                case 2:
                    jlb.setText((String) value);
                    break;
            }
        }
        return jlb;
    }
}
