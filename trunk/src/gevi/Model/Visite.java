package gevi.Model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.343F4C97-B5E3-5F8D-13C7-7B9471E7FF74]
// </editor-fold> 
public class Visite implements Serializable {

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.8CE812BB-AAD5-3A59-1077-37F91433C6FB]
    // </editor-fold> 
    private String mNom = "";
    private String mDescription = "";
    private int mVersementId = -1;
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.6D55E150-9A8B-937A-9CDA-16F803553FFB]
    // </editor-fold> 
    private String mDiagnostique = "";
    private String mMajorationPeriode = ""; /* CRD,CRM,CRN,VRD,VRM,VRN */

    private String mTypeVisite = ""; /* C, V, GAV, IPM */
    //private boolean mDeplacement = false; /* MD */
    private boolean mMajorationNourrisson = false; /* MNO */
    private boolean mUrgenceVitale = false; /* YYYY010*/
    private boolean mECG = false; /* DEQP003 */
    private boolean mECGDomicile = false; /* YYYY490*/
    private double mDepassement = 0.0;
    private int mIK = 0;
    private int mIKM = 0;
    
    private Vector<Payement> mPayements=new Vector();   

    public double getSommePayée()
    {
        double somme=0.0;

        for (int i=0; i<mPayements.size(); i++)
        {
            somme+=mPayements.get(i).getMontant();
        }
        return somme;
    }

    public int getVersementId() {
        return mVersementId;
    }

    public void setVersementId(int val) {
        mVersementId = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.5B8E2D8D-04F7-17A7-B2C9-D409A8294FA3]
    // </editor-fold> 
    public Visite() {
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.D8075E29-BBAA-0EBC-F2E7-8CBDCDDA2CEF]
    // </editor-fold> 
    public String getNom() {
        return mNom;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.44E0C1CD-9812-EC96-49C2-AAA57C3E6EB5]
    // </editor-fold> 
    public void setNom(String val) {
        this.mNom = val;
    }

    public String getDiagnostique() {
        return mDiagnostique;
    }

    public void setDiagnostique(String val) {
        this.mDiagnostique = val;
    }

    public double getTotal() {
        Parametres param = Singleton.instance().getDocument().getParametres();

        return getTotal(param);
    }

    public double getTotal( Parametres param) {

        if (mTypeVisite.equals("")) {
            return 0.0;
        }

        if (mTypeVisite.equals("GAV")) {
            return param.getTarifGAV();
        }

        if (mTypeVisite.equals("IPM")) {
            return param.getTarifIPM();
        }

        double somme = 0;
        if (mTypeVisite.equals("C")) {
            somme = param.getTarifC();
        }

        if (mTypeVisite.equals("V")) {
            somme = param.getTarifV();
        }

        if (mMajorationPeriode.equals("CRD")) {
            somme = somme + param.getTarifCRD();
        }

        if (mMajorationPeriode.equals("CRN")) {
            somme = somme + param.getTarifCRN();
        }

        if (mMajorationPeriode.equals("CRM")) {
            somme = somme + param.getTarifCRM();
        }

        if (mMajorationPeriode.equals("VRD")) {
            somme = somme + param.getTarifVRD();
        }

        if (mMajorationPeriode.equals("VRN")) {
            somme = somme + param.getTarifVRN();
        }

        if (mMajorationPeriode.equals("VRM")) {
            somme = somme + param.getTarifVRM();
        }

        if (mMajorationPeriode.equals("MD")) {
            somme = somme + param.getTarifMD();
        }

        if (mECG) {
            somme = somme + param.getTarifDEQP003();
        }

        if (mECGDomicile) {
            somme = somme + param.getTarifYYYY010();
        }
        if (mMajorationNourrisson) {
            somme = somme + param.getTarifMNO();
        }
        if (mUrgenceVitale) {
            somme = somme + param.getTarifYYYY010();
        }

        somme = somme + mDepassement;
        somme = somme + mIK * param.getTarifIK();
        somme = somme + mIKM * param.getTarifIKM();
        return somme;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String val) {
        this.mDescription = val;
    }

    public String getMajorationPeriode() {
        return mMajorationPeriode;
    }

    public void setMajorationPeriode(String val) {
        this.mMajorationPeriode = val;
    }

    public String getTypeVisite() {
        return mTypeVisite;
    }

    public void setTypeVisite(String val) {
        this.mTypeVisite = val;
    }

/*    public boolean getDeplacement() {
        return mDeplacement;
    }

    public void setDeplacement(boolean val) {
        this.mDeplacement = val;
    }*/

    public boolean getMajorationNourrisson() {
        return mMajorationNourrisson;
    }

    public void setMajorationNourrisson(boolean val) {
        this.mMajorationNourrisson = val;
    }

    public boolean getUrgenceVitale() {
        return mUrgenceVitale;
    }

    public void setUrgenceVitale(boolean val) {
        this.mUrgenceVitale = val;
    }

    public boolean getECG() {
        return mECG;
    }

    public void setECG(boolean val) {
        this.mECG = val;
    }

    public boolean getECGDomicile() {
        return mECGDomicile;
    }

    public void setECGDomicile(boolean val) {
        this.mECGDomicile = val;
    }

    public boolean getPaye() {
        return (getTotal()-getSommePayée()<0.01);
    }


    public double getDepassement() {
        return mDepassement;
    }

    public void setDepassement(double val) {
        this.mDepassement = val;
    }

    public int getIK() {
        return mIK;
    }

    public void setIK(int val) {
        this.mIK = val;
    }

    public int getIKM() {
        return mIKM;
    }

    public void setIKM(int val) {
        this.mIKM = val;
    }

    public Vector<Payement> getPayements()
    {
        return mPayements;
    }

    public String getSourcesPayement()
    {
        String tmp="";

        for (int i=0; i<mPayements.size(); i++)
        {
            tmp=tmp+" "+mPayements.get(i).getSourcePayement();
        }
        return tmp;
    }

    public String getDatesPayement()
    {
        String tmp="";

        SimpleDateFormat f = new SimpleDateFormat("EEEEEEEEE dd MMMMMMMMMMM yyyy");
        
        for (int i=0; i<mPayements.size(); i++)
        {
            Date tmpDate=mPayements.get(i).getDatePayement();
            tmp=tmp+" "+f.format(tmpDate);
        }
        return tmp;

                    

    }
}

