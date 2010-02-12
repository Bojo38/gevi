package gevi.Model;

import java.util.Vector;
import java.io.Serializable;
import java.util.Date;


// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.6EE0461C-926F-B895-AE56-9B1704DD83DE]
// </editor-fold> 
public class Document implements Serializable {

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.D461AEF6-515B-E42E-85E3-9C65992BAAC5]
    // </editor-fold> 
    protected Vector<Executant> mRemplacants;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.482C7EB6-FE65-25F4-813D-159BD9872289]
    // </editor-fold> 
    private Parametres mParametres;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.80E0F4A2-0A30-561F-B487-63FF891600A8]
    // </editor-fold> 
    private Utilisateur mUtilisateur;
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.429B8993-8565-0483-BF6B-CB7E0D4B35A7]
    // </editor-fold> 
    private Vector<Versement> mVersements;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.BA3A9149-0EA5-4DA8-6F4F-704C32E32305]
    // </editor-fold> 
    private Vector<Creneau> mCreneaux;
    private Vector<Categorie> mCategories;
    private Vector<Depense> mDepenses;
    private Date mDate;

    private Banque mBanque;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.7CF4BD7C-84E0-8D74-6CC8-7B0F7A070F58]
    // </editor-fold> 
    public Document() {
        mParametres = new Parametres();
        mRemplacants = new Vector<Executant>();
        mCreneaux = new Vector<Creneau>();
        mDate = new Date();
        mVersements = new Vector<Versement>();
        mUtilisateur = new Utilisateur();
        mParametres = new Parametres();
        mCategories = new Vector<Categorie>();
        mDepenses = new Vector<Depense>();
        mBanque=new Banque();
    }

    public void setDate(Date val) {
        mDate = val;
    }

    public Date getDate() {
        return mDate;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.C5F81CD0-3302-C14E-7155-4B41C561A5C9]
    // </editor-fold> 
    public Vector<Executant> getRemplacants() {
        return mRemplacants;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.01A4ECDB-21CF-95FF-1B30-C58C267EEFA6]
    // </editor-fold> 
    public void setRemplacants(Vector<Executant> val) {
        mRemplacants = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.271A8E9B-955C-AC68-0BB5-40F4CB7460CE]
    // </editor-fold> 
    public Parametres getParametres() {
        return mParametres;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.592585EB-A328-0DD9-98CF-AC06D0C3E422]
    // </editor-fold> 
    public void setParametres(Parametres val) {
        mParametres = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.6F3D6D7D-7E97-DC79-D1AC-860A62366D9C]
    // </editor-fold> 
    public Vector<Creneau> getCreneaux() {
        return mCreneaux;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.5EEDDF40-CFB1-F19D-646F-B5D1B8025F55]
    // </editor-fold> 
    public void setCreneaux(Vector<Creneau> val) {
        mCreneaux = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.38A376CA-B6AA-FB6C-4AD0-1FF1473877D4]
    // </editor-fold> 
    public Utilisateur getUtilisateur() {
        return mUtilisateur;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.BAB861D9-F3A3-3A84-3A65-B99D09F80460]
    // </editor-fold> 
    public void setUtilisateur(Utilisateur val) {
        mUtilisateur = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.8A300791-58B9-C173-01EE-7C941AF748B7]
    // </editor-fold> 
    public Vector<Versement> getVersements() {
        return mVersements;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.A0F05F46-D64F-EB69-827D-0A84340AF111]
    // </editor-fold> 
    public void setVersements(Vector<Versement> val) {
        mVersements = val;
    }

    public Vector<Depense> getDepenses() {
        return mDepenses;
    }

    public void setDepenses(Vector<Depense> val) {
        mDepenses = val;
    }

    public Vector<Categorie> getCategories() {
        return mCategories;
    }

    public void setCategories(Vector<Categorie> val) {
        mCategories = val;
    }

    public Banque getBanque()
    {
        return mBanque;
    }
}

