package gevi.Model;


// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.12C5EA18-0544-380A-6E82-BB86C977C559]
// </editor-fold> 
public class Singleton {

    private  Singleton()
    {
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.0D59D227-7204-225B-CF9D-2AA1E4F89A50]
    // </editor-fold> 
    private static Singleton uniqueInstance;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.B01CDB9D-F872-4BEE-AB35-BF2D36AC0469]
    // </editor-fold> 
    private int singletonData;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.B38FBC3F-AEC4-A117-1387-BE613CD6FDA4]
    // </editor-fold> 
    private static Document mDocument;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.5BFB6865-12F6-74C1-3896-9A0F877891EC]
    // </editor-fold> 
    public static Singleton instance () {
        if (uniqueInstance==null)
        {
            uniqueInstance=new Singleton();
        }
        return uniqueInstance;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.7FC5198C-D20A-0103-4C11-4913F1691832]
    // </editor-fold> 
    public void singletonOperation () {
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.6504BFAA-E855-7C13-1F58-F4F6E306E711]
    // </editor-fold> 
    public int getSingletonData () {
        return 0;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.E3D2B245-A832-2BEF-47EA-F71DEC5838F3]
    // </editor-fold> 
    public Document getDocument () {
        return mDocument;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.1CBAFB5D-4EF1-A01E-052D-A0AFE3FA321B]
    // </editor-fold> 
    public void setDocument (Document val) {
        this.mDocument = val;
    }

}

