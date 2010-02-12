/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gevi.Model;

import java.util.Date;

/**
 *
 * @author root.106572700130
 */
public class Operation {

    protected Date _date;
    protected double _montant;

    public Operation(Date date, double val)
    {
        _date=date;
        _montant=val;
    }

    public Date getDate()
    {
        return _date;
    }

    public double getMontant()
    {
        return _montant;
    }


}
