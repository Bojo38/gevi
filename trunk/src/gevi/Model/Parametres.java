package gevi.Model;

import java.io.Serializable; 

// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.B58FA19B-7CC5-3DE6-7220-0CAEE446309A]
// </editor-fold> 
public class Parametres implements Serializable {


    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.0894DC73-59E1-781D-4827-211298CD90CA]
    // </editor-fold> 
    private double mPrelevement = 0.25;

    /* Type de visite*/
    private double mTarif_C = 22;
    private double mTarif_V = 22;
    /* Déplacement */
    private double mTarif_MD=10;
    /* Zone temporelle */
    private double mTarif_CRD=26.5;
    private double mTarif_CRN=42.5;
    private double mTarif_CRM=51.5;
    private double mTarif_VRD=30;
    private double mTarif_VRN=46;
    private double mTarif_VRM=55;
    /* Mineurs */
    private double mTarif_MNO=5;
    /* ECG */
    /* cabinet */
    private double mTarif_DEQP003=13.34;
    /* Déplacement*/
    private double mTarif_YYYY490=9.6;
    /* Urgence vitale */
    private double mTarif_YYYY010=9.6;
    /* Kilometrique*/
    private double mTarif_IK=0.66;
    private double mTarif_IKM=0.91;
    /* Astreinte */
    private double mTarif_Astreinte=150;

    /* Garde à vue */
    private double mTarif_GAV=55;
    /* IPM */
    private double mTarif_IPM=55;

    private int mSavePeriod=30000;

    private String mFTPServer="";
    private String mFTPId="";
    
    private String mFTPDir="";
    private boolean mFTPAuto=true;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.76E147C5-64D2-B15A-0767-591435DCB48F]
    // </editor-fold> 
    public Parametres() {
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=y,regenBody=yeses,id=DCE.6CB77303-89F8-A6B7-5CC0-95F05A085092]
    // </editor-fold> 
    public double getPrelevement () {
        return mPrelevement;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=y,regenBody=yeses,id=DCE.F83516B4-F88A-6A17-F8D0-85B5533668A4]
    // </editor-fold> 
    public void setPrelevement (double val) {
        mPrelevement=val;
    }

    public double getTarifC()
    {
        return mTarif_C;
    }
    
    public void setTarifC(double val)
    {
        mTarif_C=val;
    }

    public double getTarifV()
    {
        return mTarif_V;
    }

    public void setTarifV(double val)
    {
        mTarif_V=val;
    }

    public double getTarifMD()
    {
        return mTarif_MD;
    }

    public void setTarifMD(double val)
    {
        mTarif_MD=val;
    }

    public double getTarifCRD()
    {
        return mTarif_CRD;
    }
    
    public void setTarifCRD(double val)
    {
        mTarif_CRD=val;
    }

    public double getTarifCRN()
    {
        return mTarif_CRN;
    }

    public void setTarifCRN(double val)
    {
        mTarif_CRN=val;
    }

    public double getTarifCRM()
    {
        return mTarif_CRM;
    }

    public void setTarifCRM(double val)
    {
        mTarif_CRM=val;
    }

    public double getTarifVRD()
    {
        return mTarif_VRD;
    }

    public void setTarifVRD(double val)
    {
        mTarif_VRD=val;
    }

    public double getTarifVRN()
    {
        return mTarif_VRN;
    }

    public void setTarifVRN(double val)
    {
        mTarif_VRN=val;
    }

    public double getTarifVRM()
    {
        return mTarif_VRM;
    }

    public void setTarifVRM(double val)
    {
        mTarif_VRM=val;
    }

    public double getTarifMNO()
    {
        return mTarif_MNO;
    }

    public void setTarifMNO(double val)
    {
        mTarif_MNO=val;
    }

    public double getTarifDEQP003()
    {
        return mTarif_DEQP003;
    }

    public void setTarifDEQP003(double val)
    {
        mTarif_DEQP003=val;
    }

    public double getTarifYYYY490()
    {
        return mTarif_YYYY490;
    }

    public void setTarifYYYY490(double val)
    {
        mTarif_YYYY490=val;
    }

    public double getTarifYYYY010()
    {
        return mTarif_YYYY010;
    }

    public void setTarifYYYY010(double val)
    {
        mTarif_YYYY010=val;
    }

    public double getTarifIK()
    {
        return mTarif_IK;
    }

    public void setTarifIK(double val)
    {
        mTarif_IK=val;
    }

    public double getTarifIKM()
    {
        return mTarif_IKM;
    }

    public void setTarifIKM(double val)
    {
        mTarif_IKM=val;
    }

    public double getTarifAstreinte()
    {
        return mTarif_Astreinte;
    }

    public void setTarifAstreinte(double val)
    {
        mTarif_Astreinte=val;
    }

    public double getTarifGAV()
    {
        return mTarif_GAV;
    }

    public void setTarifGAV(double val)
    {
        mTarif_GAV=val;
    }
    public double getTarifIPM()
    {
        return mTarif_IPM;
    }

    public void setTarifIPM(double val)
    {
        mTarif_IPM=val;
    }

    public void setSavePeriod(int val)
    {
        mSavePeriod=val;
    }

    public int getSavePeriod()
    {
        return mSavePeriod;
    }

    public boolean getAutoFTP()
    {
        return mFTPAuto;
    }

    public void setAutoFTP(boolean val)
    {
        mFTPAuto=val;
    }

    public String getFTPServer()
    {
        return mFTPServer;
    }

    public void setFTPServer(String val)
    {
        mFTPServer=val;
    }

    public String getFTPId()
    {
        return mFTPId;
    }

    public void setFTPId(String val)
    {
        mFTPId=val;
    }
    
    public String getFTPDirectory()
    {
        return mFTPDir;
    }

    public void setFTPDirectory(String val)
    {
        mFTPDir=val;
    }

}

