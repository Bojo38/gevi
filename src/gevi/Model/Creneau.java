package gevi.Model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.3270187D-AB27-E0E7-2C0D-A91EFC8B9A90]
// </editor-fold> 
public class Creneau implements Serializable {

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.3220A0D9-8A02-80C3-E2F9-8C5A433FF714]
    // </editor-fold> 
    private Executant mExecutant;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.CEC45DA6-3B96-E409-0921-3106212A9948]
    // </editor-fold> 
    private Vector<Visite> mVisites;
    private Date mDate;
    private boolean mAstreinte;
    private boolean mAstreintePayee = false;
    private Date mDatePayementAstreinte = null;
    private int mVersementId = -1;
    protected int mKilometres=0;

    public int getKilometres()
    {
        return mKilometres;
    }

    public void setKilometres(int val)
    {
        mKilometres=val;
    }

    public Date getDatePayementAstreinte() {
        return mDatePayementAstreinte;
    }

    public void setDatePayementAstreinte(Date val)
    {
        mDatePayementAstreinte=val;
    }

    public int getVersementId() {
        return mVersementId;
    }

    public void setVersementId(int val) {
        mVersementId = val;
    }

    public boolean getAstreintePayee() {
        return mAstreintePayee;
    }

    public void setAstreintePayee(boolean val) {
        mAstreintePayee = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.06EA9963-B80B-9CDC-7FAA-3F7244CA756E]
    // </editor-fold> 
    public Creneau() {
        mVisites = new Vector();
        mExecutant = new Executant();
        mDate = new Date();
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date val) {
        mDate = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.53D07E0A-6F3F-0991-B29C-7D9E1F49CF60]
    // </editor-fold> 
    public Vector<Visite> getVisites() {
        return mVisites;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.C135140E-1673-BCEB-B783-3BE4C07FB945]
    // </editor-fold> 
    public Executant getExecutant() {
        return mExecutant;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.4ED7CA2A-FAFD-5F32-A597-47BA4835D81A]
    // </editor-fold> 
    public void setExecutant(Executant val) {
        this.mExecutant = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.0674EBB1-93D3-F52E-FAEE-F09F1B60ED7B]
    // </editor-fold> 
    public void setVisites(Vector<Visite> val) {
        this.mVisites = val;
    }

    public String getName() {
        DateFormat format = new SimpleDateFormat("EEEEEEE dd MMMMMMMMMMM yyyy");
        return format.format(mDate) + " - " + mExecutant.getNom() + " " + mExecutant.getPrenom();
    }

    public double getTotal() {
        double somme = 0;
        for (int i = 0; i < mVisites.size(); i++) {
            Visite v = (Visite) mVisites.get(i);
            somme = somme + v.getTotal();
        }
        return somme;
    }

    public double getTotalImpaye() {
        double somme = 0;
        for (int i = 0; i < mVisites.size(); i++) {
            Visite v = (Visite) mVisites.get(i);
            somme = somme + v.getTotal()-v.getSommePayée();
        }
        return somme;
    }

    public Double getTotalPaye() {
        double somme = 0;
        for (int i = 0; i < mVisites.size(); i++) {
            Visite v = (Visite) mVisites.get(i);
            somme = somme + v.getSommePayée();
        }
        return somme;
    }

    public boolean getAstreinte() {
        return mAstreinte;
    }

    public void setAstreinte(boolean val) {
        mAstreinte = val;
    }
}

