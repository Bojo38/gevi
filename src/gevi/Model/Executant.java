package gevi.Model;

import java.io.Serializable;


// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.8D0C8272-A0AD-95F9-EE56-D325FC6E158D]
// </editor-fold> 
public class Executant implements Serializable {

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.523EED5E-B5F4-44E0-EB2F-2472D38C143C]
    // </editor-fold> 
    protected String mNom="";

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.6BFDE336-7CD3-B37F-1B24-81697F010982]
    // </editor-fold> 
    protected String mPrenom="";
    protected String mAdresse="";
    protected String mTelephone="";
    protected String mCourriel="";
    protected String mDivers="";

    
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.B30B5904-0549-CF61-35A1-BCFEABCCA487]
    // </editor-fold> 
    public Executant () {
    }


    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.4AFA26FD-1E8C-A296-3681-817CA2F74D9B]
    // </editor-fold> 
    public String getNom () {
        return mNom;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.4D9908E4-B2C7-9D5C-CB23-A52E98350FAD]
    // </editor-fold> 
    public void setNom (String val) {
        mNom=val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.48AFC9BF-1EC7-1139-CF96-826AE814DE15]
    // </editor-fold> 
    public String getPrenom () {
        return mPrenom;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.4547C3D5-8C45-F436-FA42-A62BBE93241D]
    // </editor-fold> 
    public void setPrenom (String val) {
        mPrenom=val;
    }

    public String getAdresse () {
        return mAdresse;
    }

    public void setAddress (String val) {
        mAdresse=val;
    }

    public String getTelephone () {
        return mTelephone;
    }

    public void setTelephone (String val) {
        mTelephone=val;
    }

    public String getCourriel () {
        return mCourriel;
    }

    public void setCourriel (String val) {
        mCourriel=val;
    }

    public String getDivers () {
        return mDivers;
    }

    public void setDivers (String val) {
        mDivers=val;
    }

}

