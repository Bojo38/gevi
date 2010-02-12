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
public class Depense {
    
    Categorie _categorie=null;
    double _montant=0.0;
    String _vendeur="";
    Date _date=new Date();
    String _objet="";
    String _source="";

    public String getObjet()
    {
        return _objet;
    }

    public void setObjet(String val)
    {
        _objet=val;
    }

    public Categorie getCategorie()
    {
        return _categorie;
    }

    public void setCategorie(Categorie val)
    {
        _categorie=val;
    }

    public double getMontant()
    {
        return _montant;
    }

    public void setMontant(double val)
    {
        _montant=val;
    }

    public String getVendeur()
    {
        return _vendeur;
    }

    public void setVendeur(String val)
    {
        _vendeur=val;
    }

    public Date getDate()
    {
        return _date;
    }

    public void setDate(Date val)
    {
        _date=val;
    }

    public String getSource()
    {
        return _source;
    }

    public void setSource(String val)
    {
        _source=val;
    }
}
