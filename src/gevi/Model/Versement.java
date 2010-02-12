
package gevi.Model;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;


// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.B7377125-434D-CF80-74D6-CE89EE058F9F]
// </editor-fold> 
public class Versement implements Serializable{

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.FDFEF267-2A30-9EB0-6BE0-5B5013CEE32B]
    // </editor-fold> 
    private Executant mExecutant=null;

    private double mMontant=0;
    private String mDivers="";
    private Date mDate=new Date();
    private int mId=0;

    public String getName()
    {
        SimpleDateFormat f = new SimpleDateFormat("EEEEEEEEE dd MMMMMMMMMMM yyyy");
        return f.format(mDate)+" - "+mExecutant.getNom()+", "+mExecutant.getPrenom();
    }

    public int getId()
    {
        return mId;
    }

    public void setId(int val)
    {
        mId=val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.30FD7C99-4F49-86BA-D753-64F7E695DE65]
    // </editor-fold> 
    public Versement () {
    }
 
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.45FB83C0-A0B3-637C-C084-E36F6689F391]
    // </editor-fold> 
    public Executant getExecutant () {
        return mExecutant;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.8F7A9FD6-05A0-BB91-A6D7-A5A5DE48D638]
    // </editor-fold> 
    public void setExecutant (Executant val) {
        this.mExecutant = val;
    }

    public double getMontant()
    {
        return mMontant;
    }

    public void setMontant(double val)
    {
        mMontant=val;
    }

    public String getDivers()
    {
        return mDivers;
    }
    
    public void setDivers(String val)
    {
        mDivers=val;
    }

    public void setDate(Date val)
    {
        mDate=val;
    }

    public Date getDate()
    {
        return mDate;
    }


}

