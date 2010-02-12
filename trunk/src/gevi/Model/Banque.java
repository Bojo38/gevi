/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gevi.Model;

import java.util.Vector;
import gevi.Model.Operation;
import java.util.Date;



/**
 *
 * @author root.106572700130
 */
public class Banque {

    protected double _soldeInitialCaisse = 0;
    protected double _soldeInitialBanque = 0;
    protected Vector<Operation> _fromCash = new Vector();
    protected Vector<Operation> _toCash = new Vector();

    public void setSoldeInitialBanque(double solde) {
        _soldeInitialBanque = solde;
    }

    public double getSoldeInitialBanque() {
        return _soldeInitialBanque;
    }

    public void setSoldeInitialCaisse(double solde) {
        _soldeInitialCaisse = solde;
    }

    public double getSoldeInitialCaisse() {
        return _soldeInitialCaisse;
    }

    public double getSoldeBanque(Date date, Document doc) {
        double solde = _soldeInitialBanque;

        /* Ajout des versement de liquide */
        for (int i = 0; i < _fromCash.size(); i++) {
            Operation op = _fromCash.get(i);
            if ((op.getDate().before(date)) || (op.getDate().equals(date))) {
                solde += op.getMontant();
            }
        }

        /* Rtraits en liquide vers la caisse */
        for (int i = 0; i < _toCash.size(); i++) {
            Operation op = _toCash.get(i);
            if ((op.getDate().before(date)) || (op.getDate().equals(date))) {
                solde -= op.getMontant();
            }
        }

        /* Dépenses de banque */
        for (int i = 0; i < doc.getDepenses().size(); i++) {
            Depense dep = doc.getDepenses().get(i);
            if (dep.getSource().equals("Banque")) {
                if ((dep.getDate().before(date)) || (dep.getDate().equals(date))) {
                    solde -= dep.getMontant();
                }
            }
        }

        /* Versement aux remplaçants */
        for (int i = 0; i < doc.getVersements().size(); i++) {
            Versement ver = doc.getVersements().get(i);
            if ((ver.getDate().before(date)) || (ver.getDate().equals(date))) {
                solde -= ver.getMontant();
            }
        }

        /* Argent encaissé */
        for (int i = 0; i < doc.getCreneaux().size(); i++) {
            Creneau cren = doc.getCreneaux().get(i);
            /* Astreinte */
            if (cren.getAstreintePayee()) {
                if ((cren.getDatePayementAstreinte().before(date)) || (cren.getDatePayementAstreinte().equals(date))) {
                    solde += doc.getParametres().getTarifAstreinte();
                }
            }

            /* Visites */
            for (int j = 0; j < cren.getVisites().size(); j++) {
                Visite v = cren.getVisites().get(j);

                for (int k = 0; k < v.getPayements().size(); k++) {
                    Payement p = v.getPayements().get(k);
                    if (!p.getMoyenPayement().equals("Espèces")) {
                        if ((p.getDatePayement().before(date)) || (p.getDatePayement().equals(date))) {
                            solde += p.getMontant();
                        }
                    }
                }
            }
        }

        return solde;
    }

    public double getSoldeBanqueAvant(Date date, Document doc) {
        double solde = _soldeInitialBanque;

        /* Ajout des versement de liquide */
        for (int i = 0; i < _fromCash.size(); i++) {
            Operation op = _fromCash.get(i);
            if ((op.getDate().before(date))) {
                solde += op.getMontant();
            }
        }

        /* Rtraits en liquide vers la caisse */
        for (int i = 0; i < _toCash.size(); i++) {
            Operation op = _toCash.get(i);
            if ((op.getDate().before(date))) {
                solde -= op.getMontant();
            }
        }

        /* Dépenses de banque */
        for (int i = 0; i < doc.getDepenses().size(); i++) {
            Depense dep = doc.getDepenses().get(i);
            if (dep.getSource().equals("Banque")) {
                if ((dep.getDate().before(date))) {
                    solde -= dep.getMontant();
                }
            }
        }

        /* Versement aux remplaçants */
        for (int i = 0; i < doc.getVersements().size(); i++) {
            Versement ver = doc.getVersements().get(i);
            if ((ver.getDate().before(date))) {
                solde -= ver.getMontant();
            }
        }

        /* Argent encaissé */
        for (int i = 0; i < doc.getCreneaux().size(); i++) {
            Creneau cren = doc.getCreneaux().get(i);
            /* Astreinte */
            if (cren.getAstreintePayee()) {
                if ((cren.getDatePayementAstreinte().before(date))) {
                    solde += doc.getParametres().getTarifAstreinte();
                }
            }

            /* Visites */
            for (int j = 0; j < cren.getVisites().size(); j++) {
                Visite v = cren.getVisites().get(j);
                for (int k = 0; k < v.getPayements().size(); k++) {
                    Payement p = v.getPayements().get(k);
                    if (!p.getMoyenPayement().equals("Espèces")) {
                        if (p.getDatePayement() != null) {
                            if (p.getDatePayement().before(date)) {
                                solde += p.getMontant();
                            }
                        }
                    }
                }
            }
        }

        return solde;
    }

    public double getSoldeCash(Date date, Document doc) {
        double solde = _soldeInitialCaisse;

        /* Ajout des versement de liquide */
        for (int i = 0; i < _fromCash.size(); i++) {
            Operation op = _fromCash.get(i);
            if ((op.getDate().before(date)) || (op.getDate().equals(date))) {
                solde -= op.getMontant();
            }
        }

        /* Rtraits en liquide vers la caisse */
        for (int i = 0; i < _toCash.size(); i++) {
            Operation op = _toCash.get(i);
            if ((op.getDate().before(date)) || (op.getDate().equals(date))) {
                solde += op.getMontant();
            }
        }

        /* Dépenses en espèces */
        for (int i = 0; i < doc.getDepenses().size(); i++) {
            Depense dep = doc.getDepenses().get(i);
            if (dep.getSource().equals("Espèces")) {
                if ((dep.getDate().before(date)) || (dep.getDate().equals(date))) {
                    solde -= dep.getMontant();
                }
            }
        }

        /* Argent encaissé */
        for (int i = 0; i < doc.getCreneaux().size(); i++) {
            Creneau cren = doc.getCreneaux().get(i);

            /* Visites */
            for (int j = 0; j < cren.getVisites().size(); j++) {
                Visite v = cren.getVisites().get(j);
                for (int k = 0; k < v.getPayements().size(); k++) {
                    Payement p = v.getPayements().get(k);
                    if (p.getMoyenPayement().equals("Espèces")) {
                        if ((p.getDatePayement().before(date)) || (p.getDatePayement().equals(date))) {
                            solde += p.getMontant();
                        }
                    }
                }
            }
        }

        return solde;
    }

    public double getSoldeCashAvant(Date date, Document doc) {
        double solde = _soldeInitialCaisse;

        /* Ajout des versement de liquide */
        for (int i = 0; i < _fromCash.size(); i++) {
            Operation op = _fromCash.get(i);
            if ((op.getDate().before(date))) {
                solde -= op.getMontant();
            }
        }

        /* Rtraits en liquide vers la caisse */
        for (int i = 0; i < _toCash.size(); i++) {
            Operation op = _toCash.get(i);
            if ((op.getDate().before(date))) {
                solde += op.getMontant();
            }
        }

        /* Dépenses en espèces */
        for (int i = 0; i < doc.getDepenses().size(); i++) {
            Depense dep = doc.getDepenses().get(i);
            if (dep.getSource().equals("Espèces")) {
                if ((dep.getDate().before(date))) {
                    solde -= dep.getMontant();
                }
            }
        }

        /* Argent encaissé */
        for (int i = 0; i < doc.getCreneaux().size(); i++) {
            Creneau cren = doc.getCreneaux().get(i);

            /* Visites */
            for (int j = 0; j < cren.getVisites().size(); j++) {
                Visite v = cren.getVisites().get(j);
                for (int k = 0; k < v.getPayements().size(); k++) {
                    Payement p = v.getPayements().get(k);
                    if (p.getMoyenPayement().equals("Espèces")) {
                        if (p.getDatePayement() != null) {
                            if (p.getDatePayement().before(date)) {
                                solde += p.getMontant();
                            }
                        }
                    }
                }
            }
        }

        return solde;
    }

    public Vector<Operation> getOperationsFromCash() {
        return _fromCash;
    }

    public Vector<Operation> getOperationsToCash() {
        return _toCash;
    }
}
