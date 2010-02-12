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
public class Payement {

    private String mMoyenPayement = "";
    private String mSourcePayement = "";
    private double mMontant=0.0;
    private Date mDatePayement;

    public double getMontant()
    {
        return mMontant;
    }

    public void setMontant(double val)
    {
        mMontant=val;
    }

     public String getMoyenPayement() {
        return mMoyenPayement;
    }

    public void setMoyenPayement(String val) {
        this.mMoyenPayement = val;
    }

    public String getSourcePayement() {
        return mSourcePayement;
    }

    public void setSourcePayement(String val) {
        this.mSourcePayement = val;
    }

        public Date getDatePayement() {
        return mDatePayement;
    }

    public void setDatePayement(Date val) {
        mDatePayement = val;
    }
}
