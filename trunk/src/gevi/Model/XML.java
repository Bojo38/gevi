/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gevi.Model;

import gevi.Views.MainWindow;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Date;
import java.util.Vector;
import javax.swing.JOptionPane;
import org.jdom.Attribute;
import org.jdom.DataConversionException;
import org.jdom.Element;

/**
 *
 * @author root
 */
public class XML {

    public static org.jdom.Document convertDocumentToXML(Document doc) {
        Element document = new Element("Document");

        /* Document */
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Attribute date = new Attribute("Date", format.format(doc.getDate()));
        document.setAttribute(date);

        /* Utilisateur */
        Element user = new Element("Utilisateur");
        Utilisateur util = doc.getUtilisateur();

        Attribute nomU = new Attribute("Nom", util.getNom());
        user.setAttribute(nomU);
        Attribute prenomU = new Attribute("Prénom", util.getPrenom());
        user.setAttribute(prenomU);
        Attribute adresseU = new Attribute("Adresse", util.getAdresse());
        user.setAttribute(adresseU);
        Attribute telephoneU = new Attribute("Telephone", util.getAdresse());
        user.setAttribute(telephoneU);
        Attribute courrielU = new Attribute("Courriel", util.getAdresse());
        user.setAttribute(courrielU);
        Attribute diversU = new Attribute("Divers", util.getAdresse());
        user.setAttribute(diversU);
        Attribute adeliU = new Attribute("ADELI", util.getAdeli());
        user.setAttribute(adeliU);

        document.addContent(user);

        /* Banque */
        Element banque = new Element("Banque");
        Banque b = doc.getBanque();

        Attribute soldeC = new Attribute("SoldeCaisse", Double.toString(b.getSoldeInitialCaisse()));
        banque.setAttribute(soldeC);
        Attribute soldeB = new Attribute("SoldeBanque", Double.toString(b.getSoldeInitialBanque()));
        banque.setAttribute(soldeB);

        /* Operations */
        /* fromCash */
        Vector<Operation> _fromCash = doc.getBanque().getOperationsFromCash();
        for (int i = 0; i < _fromCash.size(); i++) {
            Element operation = new Element("FromCash");
            Operation op = _fromCash.get(i);
            operation.setAttribute("Date", format.format(op.getDate()));
            operation.setAttribute("Montant", Double.toString(op.getMontant()));
            banque.addContent(operation);
        }

        /* ToCash */
        Vector<Operation> _toCash = doc.getBanque().getOperationsToCash();
        for (int i = 0; i < _toCash.size(); i++) {
            Element operation = new Element("ToCash");
            Operation op = _toCash.get(i);
            operation.setAttribute("Date", format.format(op.getDate()));
            operation.setAttribute("Montant", Double.toString(op.getMontant()));
            banque.addContent(operation);
        }

        document.addContent(banque);

        /* Remplacants */
        Vector<Executant> remplacants = doc.getRemplacants();
        for (int i = 0; i < remplacants.size(); i++) {
            Element remp = new Element("Remplacant");
            Executant exec = remplacants.get(i);

            Attribute nom = new Attribute("Nom", exec.getNom());
            remp.setAttribute(nom);
            Attribute prenom = new Attribute("Prénom", exec.getPrenom());
            remp.setAttribute(prenom);
            Attribute adresse = new Attribute("Adresse", exec.getAdresse());
            remp.setAttribute(adresse);
            Attribute telephone = new Attribute("Telephone", exec.getAdresse());
            remp.setAttribute(telephone);
            Attribute courriel = new Attribute("Courriel", exec.getAdresse());
            remp.setAttribute(courriel);
            Attribute divers = new Attribute("Divers", exec.getAdresse());
            remp.setAttribute(divers);
            document.addContent(remp);
        }

        /* Parametres */
        Element parametres = new Element("Parametres");
        Attribute prelevement = new Attribute("Prelevement", Double.toString(doc.getParametres().getPrelevement()));
        parametres.setAttribute(prelevement);
        Attribute C = new Attribute("C", Double.toString(doc.getParametres().getTarifC()));
        parametres.setAttribute(C);
        Attribute CRD = new Attribute("CRD", Double.toString(doc.getParametres().getTarifCRD()));
        parametres.setAttribute(CRD);
        Attribute CRM = new Attribute("CRM", Double.toString(doc.getParametres().getTarifCRM()));
        parametres.setAttribute(CRM);
        Attribute CRN = new Attribute("CRN", Double.toString(doc.getParametres().getTarifCRN()));
        parametres.setAttribute(CRN);
        Attribute V = new Attribute("V", Double.toString(doc.getParametres().getTarifV()));
        parametres.setAttribute(V);
        Attribute VRD = new Attribute("VRD", Double.toString(doc.getParametres().getTarifVRD()));
        parametres.setAttribute(VRD);
        Attribute VRN = new Attribute("VRN", Double.toString(doc.getParametres().getTarifVRN()));
        parametres.setAttribute(VRN);
        Attribute VRM = new Attribute("VRM", Double.toString(doc.getParametres().getTarifVRM()));
        parametres.setAttribute(VRM);
        Attribute MD = new Attribute("MD", Double.toString(doc.getParametres().getTarifMD()));
        parametres.setAttribute(MD);
        Attribute MNO = new Attribute("MNO", Double.toString(doc.getParametres().getTarifMNO()));
        parametres.setAttribute(MNO);
        Attribute IK = new Attribute("IK", Double.toString(doc.getParametres().getTarifIK()));
        parametres.setAttribute(IK);
        Attribute IKM = new Attribute("IKM", Double.toString(doc.getParametres().getTarifIKM()));
        parametres.setAttribute(IKM);
        Attribute YYYY490 = new Attribute("YYYY490", Double.toString(doc.getParametres().getTarifYYYY490()));
        parametres.setAttribute(YYYY490);
        Attribute DEQP003 = new Attribute("DEQP003", Double.toString(doc.getParametres().getTarifDEQP003()));
        parametres.setAttribute(DEQP003);
        Attribute YYYY010 = new Attribute("YYYY010", Double.toString(doc.getParametres().getTarifYYYY010()));
        parametres.setAttribute(YYYY010);
        Attribute Astreinte = new Attribute("Astreinte", Double.toString(doc.getParametres().getTarifAstreinte()));
        parametres.setAttribute(Astreinte);
        Attribute IPM = new Attribute("IPM", Double.toString(doc.getParametres().getTarifIPM()));
        parametres.setAttribute(IPM);
        Attribute GAV = new Attribute("GAV", Double.toString(doc.getParametres().getTarifGAV()));
        parametres.setAttribute(GAV);
        Attribute Period = new Attribute("Period", Integer.toString(doc.getParametres().getSavePeriod()));
        parametres.setAttribute(Period);
        Attribute AutoFTP = new Attribute("AutoFTP", Boolean.toString(doc.getParametres().getAutoFTP()));
        parametres.setAttribute(AutoFTP);
        Attribute FTPDirectory = new Attribute("FTPDirectory", doc.getParametres().getFTPDirectory());
        parametres.setAttribute(FTPDirectory);
        Attribute FTPLogin = new Attribute("FTPLogin", doc.getParametres().getFTPId());
        parametres.setAttribute(FTPLogin);
        Attribute FTPServer = new Attribute("FTPServer", doc.getParametres().getFTPServer());
        parametres.setAttribute(FTPServer);
        document.addContent(parametres);

        /* Creneaux */
        Vector<Creneau> creneaux = doc.getCreneaux();
        for (int i = 0; i < creneaux.size(); i++) {
            Element cren = new Element("Creneau");
            Creneau cr = creneaux.get(i);

            Attribute nom = new Attribute("Nom", cr.getExecutant().getNom());
            cren.setAttribute(nom);
            Attribute prenom = new Attribute("Prénom", cr.getExecutant().getPrenom());
            cren.setAttribute(prenom);
            Attribute dateC = new Attribute("Date", format.format(cr.getDate()));
            cren.setAttribute(dateC);
            Attribute astreinte = new Attribute("Astreinte", Boolean.toString(cr.getAstreinte()));
            cren.setAttribute(astreinte);
            Attribute astreintePayee = new Attribute("AstreintePayée", Boolean.toString(cr.getAstreintePayee()));
            cren.setAttribute(astreintePayee);
            Attribute kilometres = new Attribute("Kilomètres", Integer.toString(cr.getKilometres()));
            cren.setAttribute(kilometres);

            try {
                Attribute datePayementAstreinte = new Attribute("DatePayementAstreinte", format.format(cr.getDatePayementAstreinte()));
                cren.setAttribute(datePayementAstreinte);
            } catch (NullPointerException e) {
            }

            Attribute versementId = new Attribute("Versement", Integer.toString(cr.getVersementId()));
            cren.setAttribute(versementId);
            /* Visites */
            Vector<Visite> visites = cr.getVisites();
            for (int j = 0; j < visites.size(); j++) {
                Element visite = new Element("Visite");
                Visite vis = visites.get(j);

                visite.setAttribute("Nom", vis.getNom());
                visite.setAttribute("Diagnostique", vis.getDiagnostique());
                visite.setAttribute("Description", vis.getDescription());
                visite.setAttribute("Type", vis.getTypeVisite());
                visite.setAttribute("Majoration", vis.getMajorationPeriode());

                visite.setAttribute("IK", Integer.toString(vis.getIK()));
                visite.setAttribute("IKM", Integer.toString(vis.getIKM()));
                visite.setAttribute("Dépassement", Double.toString(vis.getDepassement()));

                //visite.setAttribute("Déplacement", Boolean.toString(vis.getDeplacement()));
                visite.setAttribute("ECG", Boolean.toString(vis.getECG()));
                visite.setAttribute("ECGDomicile", Boolean.toString(vis.getECGDomicile()));
                visite.setAttribute("Nourrisson", Boolean.toString(vis.getMajorationNourrisson()));
                visite.setAttribute("UrgenceVitale", Boolean.toString(vis.getUrgenceVitale()));
                visite.setAttribute("Versement", Integer.toString(vis.getVersementId()));

                for (int k = 0; k < vis.getPayements().size(); k++) {
                    Payement pay = vis.getPayements().get(k);
                    Element payement = new Element("Payement");

                    payement.setAttribute("Moyen", pay.getMoyenPayement());
                    payement.setAttribute("Source", pay.getSourcePayement());
                    payement.setAttribute("Montant", Double.toString(pay.getMontant()));
                    try {
                        Attribute datePayement = new Attribute("DatePayement", format.format(pay.getDatePayement()));
                        payement.setAttribute(datePayement);
                    } catch (NullPointerException e) {
                        Attribute datePayement = new Attribute("DatePayement", format.format(new Date()));
                        payement.setAttribute(datePayement);
                    }
                    visite.addContent(payement);
                }
                cren.addContent(visite);
            }
            document.addContent(cren);
        }

        /* Catégories de frais */
        Vector<Categorie> categories = doc.getCategories();
        for (int i = 0; i < categories.size(); i++) {
            Element categorie = new Element("Categorie");
            Categorie cat = categories.get(i);
            categorie.setAttribute("Nom", cat.getNom());
            document.addContent(categorie);
        }

        /* Dépenses */
        Vector<Depense> depenses = doc.getDepenses();
        for (int i = 0; i < depenses.size(); i++) {
            Element depense = new Element("Depense");
            Depense dep = depenses.get(i);
            depense.setAttribute("Vendeur", dep.getVendeur());
            depense.setAttribute("Objet", dep.getObjet());
            try {
                Attribute date2 = new Attribute("Date", format.format(dep.getDate()));
                depense.setAttribute(date2);
            } catch (NullPointerException e) {
            }

            try {
                Attribute source = new Attribute("Source", dep.getSource());
                depense.setAttribute(source);
            } catch (NullPointerException e) {
            }
            Attribute montant = new Attribute("Montant", Double.toString(dep.getMontant()));
            depense.setAttribute(montant);
            depense.setAttribute("Categorie", dep.getCategorie().getNom());
            document.addContent(depense);
        }

        /* Versements */
        Vector<Versement> versements = doc.getVersements();
        for (int i = 0; i < versements.size(); i++) {
            Element versement = new Element("Versement");
            Versement ver = versements.get(i);
            versement.setAttribute("Montant", Double.toString(ver.getMontant()));
            versement.setAttribute("Divers", ver.getDivers());
            versement.setAttribute("Nom", ver.getExecutant().getNom() + ", " + ver.getExecutant().getPrenom());
            versement.setAttribute("Date", format.format(ver.getDate()));
            versement.setAttribute("Id", Integer.toString(ver.getId()));
            document.addContent(versement);
        }

        return new org.jdom.Document(document);
    }

    public static Document convertXMLToDocument(org.jdom.Document source) {
        Document doc = new Document();

        Element racine = source.getRootElement();

        try {
            /* Date du document */
            String date = racine.getAttributeValue("Date");
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            doc.setDate(format.parse(date));
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(MainWindow.getMainWindow(), e.getLocalizedMessage());
        }

        /* Utilisateur */
        Element userU = racine.getChild("Utilisateur");
        if (userU != null) {
            doc.getUtilisateur().setNom(userU.getAttribute("Nom").getValue());
            doc.getUtilisateur().setPrenom(userU.getAttribute("Prénom").getValue());
            doc.getUtilisateur().setAddress(userU.getAttribute("Adresse").getValue());
            doc.getUtilisateur().setTelephone(userU.getAttribute("Telephone").getValue());
            doc.getUtilisateur().setDivers(userU.getAttribute("Divers").getValue());
            doc.getUtilisateur().setAdeli(userU.getAttribute("ADELI").getValue());
        }

        Element banque = racine.getChild("Banque");
        if (banque != null) {
            try {
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

                doc.getBanque().setSoldeInitialCaisse(banque.getAttribute("SoldeCaisse").getDoubleValue());
                doc.getBanque().setSoldeInitialBanque(banque.getAttribute("SoldeBanque").getDoubleValue());

                List fromCash = banque.getChildren("FromCash");
                Iterator i = fromCash.iterator();
                while (i.hasNext()) {
                    Element op = (Element) i.next();
                    Operation operation = new Operation(format.parse(op.getAttributeValue("Date")), op.getAttribute("Montant").getDoubleValue());
                    doc.getBanque().getOperationsFromCash().add(operation);
                }

                List toCash = banque.getChildren("ToCash");
                i = toCash.iterator();
                while (i.hasNext()) {
                    Element op = (Element) i.next();
                    Operation operation = new Operation(format.parse(op.getAttributeValue("Date")), op.getAttribute("Montant").getDoubleValue());
                    doc.getBanque().getOperationsToCash().add(operation);
                }
            } catch (DataConversionException e) {
            } catch (ParseException e) {
            } catch (NullPointerException e) {
            }
        }

        /* Remplaçants */
        List remplacants = racine.getChildren("Remplacant");
        Iterator i = remplacants.iterator();
        while (i.hasNext()) {
            Element remplacant = (Element) i.next();
            Executant remp = new Executant();
            remp.setNom(remplacant.getAttributeValue("Nom"));
            remp.setPrenom(remplacant.getAttributeValue("Prénom"));
            remp.setAddress(remplacant.getAttributeValue("Adresse"));
            remp.setTelephone(remplacant.getAttributeValue("Telephone"));
            remp.setDivers(remplacant.getAttributeValue("Divers"));
            doc.getRemplacants().add(remp);
        }

        Element parametresU = racine.getChild("Parametres");
        if (parametresU != null) {
            try {
                doc.getParametres().setPrelevement(parametresU.getAttribute("Prelevement").getDoubleValue());
                doc.getParametres().setTarifC(parametresU.getAttribute("C").getDoubleValue());
                doc.getParametres().setTarifCRD(parametresU.getAttribute("CRD").getDoubleValue());
                doc.getParametres().setTarifCRM(parametresU.getAttribute("CRM").getDoubleValue());
                doc.getParametres().setTarifCRN(parametresU.getAttribute("CRN").getDoubleValue());
                doc.getParametres().setTarifV(parametresU.getAttribute("V").getDoubleValue());
                doc.getParametres().setTarifVRD(parametresU.getAttribute("VRD").getDoubleValue());
                doc.getParametres().setTarifVRN(parametresU.getAttribute("VRN").getDoubleValue());
                doc.getParametres().setTarifVRM(parametresU.getAttribute("VRM").getDoubleValue());
                doc.getParametres().setTarifMD(parametresU.getAttribute("MD").getDoubleValue());
                doc.getParametres().setTarifMNO(parametresU.getAttribute("MNO").getDoubleValue());
                doc.getParametres().setTarifDEQP003(parametresU.getAttribute("DEQP003").getDoubleValue());
                doc.getParametres().setTarifYYYY490(parametresU.getAttribute("YYYY490").getDoubleValue());
                doc.getParametres().setTarifYYYY010(parametresU.getAttribute("YYYY010").getDoubleValue());
                doc.getParametres().setTarifAstreinte(parametresU.getAttribute("Astreinte").getDoubleValue());
                doc.getParametres().setTarifIPM(parametresU.getAttribute("IPM").getDoubleValue());
                doc.getParametres().setTarifGAV(parametresU.getAttribute("GAV").getDoubleValue());
                doc.getParametres().setTarifIK(parametresU.getAttribute("IK").getDoubleValue());
                doc.getParametres().setTarifIKM(parametresU.getAttribute("IKM").getDoubleValue());
                doc.getParametres().setSavePeriod(parametresU.getAttribute("Period").getIntValue());
                doc.getParametres().setAutoFTP(parametresU.getAttribute("AutoFTP").getBooleanValue());
                doc.getParametres().setFTPDirectory(parametresU.getAttribute("FTPDirectory").getValue());
                doc.getParametres().setFTPId(parametresU.getAttribute("FTPLogin").getValue());
                doc.getParametres().setFTPServer(parametresU.getAttribute("FTPServer").getValue());
            } catch (DataConversionException e) {
                JOptionPane.showMessageDialog(MainWindow.getMainWindow(), e.getLocalizedMessage());
            } catch (NullPointerException e) {
                JOptionPane.showMessageDialog(MainWindow.getMainWindow(), e.getLocalizedMessage());
            }

        }

        /* Creneaux */
        List creneaux = racine.getChildren("Creneau");
        i = creneaux.iterator();
        while (i.hasNext()) {
            Element creneau = (Element) i.next();
            Creneau cr = new Creneau();

            /* Récupération de l'executant */
            String nom = creneau.getAttributeValue("Nom");
            String prenom = creneau.getAttributeValue("Prénom");

            if ((nom.equals(doc.getUtilisateur().getNom())) && (prenom.equals(doc.getUtilisateur().getPrenom()))) {
                cr.setExecutant(doc.getUtilisateur());
            } else {
                for (int cpt = 0; cpt < doc.getRemplacants().size(); cpt++) {
                    Executant e = doc.getRemplacants().get(cpt);
                    if ((nom.equals(e.getNom())) && (prenom.equals(e.getPrenom()))) {
                        cr.setExecutant(e);
                        break;
                    }
                }
            }

            /* Date */
            try {
                String date = creneau.getAttributeValue("Date");
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                cr.setDate(format.parse(date));
            } catch (ParseException e) {
                JOptionPane.showMessageDialog(MainWindow.getMainWindow(), e.getLocalizedMessage());
            }

            try {
                cr.setAstreinte(creneau.getAttribute("Astreinte").getBooleanValue());
                cr.setAstreintePayee(creneau.getAttribute("AstreintePayée").getBooleanValue());
                cr.setVersementId(creneau.getAttribute("Versement").getIntValue());

                /* Date Payement */
                try {
                    String datePayement = creneau.getAttributeValue("DatePayementAstreinte");
                    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                    cr.setDatePayementAstreinte(format.parse(datePayement));
                } catch (ParseException e) {
                    JOptionPane.showMessageDialog(MainWindow.getMainWindow(), e.getLocalizedMessage());
                } catch (NullPointerException e) {
                }

                try {
                    cr.setKilometres(creneau.getAttribute("Kilomètres").getIntValue());
                } catch (DataConversionException e) {
                    JOptionPane.showMessageDialog(MainWindow.getMainWindow(), e.getLocalizedMessage());
                } catch (NullPointerException e) {
                }

            } catch (DataConversionException e) {
                JOptionPane.showMessageDialog(MainWindow.getMainWindow(), e.getLocalizedMessage());
            } catch (NullPointerException e) {
                JOptionPane.showMessageDialog(MainWindow.getMainWindow(), e.getLocalizedMessage());
            }

            /* Visites */
            List visites = creneau.getChildren("Visite");
            Iterator j = visites.iterator();
            while (j.hasNext()) {
                Element visite = (Element) j.next();
                Visite vis = new Visite();
                try {
                    try {
                        vis.setDepassement(visite.getAttribute("Dépassement").getDoubleValue());
                        //vis.setDeplacement(visite.getAttribute("Déplacement").getBooleanValue());
                        vis.setDescription(visite.getAttributeValue("Description"));
                        vis.setDiagnostique(visite.getAttributeValue("Diagnostique"));
                        vis.setECG(visite.getAttribute("ECG").getBooleanValue());
                        vis.setECGDomicile(visite.getAttribute("ECGDomicile").getBooleanValue());
                        vis.setIK(visite.getAttribute("IK").getIntValue());
                        vis.setIKM(visite.getAttribute("IKM").getIntValue());
                        vis.setMajorationNourrisson(visite.getAttribute("Nourrisson").getBooleanValue());
                        vis.setMajorationPeriode(visite.getAttributeValue("Majoration"));
                        vis.setNom(visite.getAttributeValue("Nom"));
                        vis.setTypeVisite(visite.getAttributeValue("Type"));
                        vis.setUrgenceVitale(visite.getAttribute("UrgenceVitale").getBooleanValue());
                        vis.setVersementId(visite.getAttribute("Versement").getIntValue());
                    } catch (NullPointerException e) {
                        JOptionPane.showMessageDialog(MainWindow.getMainWindow(), e.getLocalizedMessage());
                    }

                    Attribute dep = visite.getAttribute("Déplacement");
                    if (dep != null) {
                        if (dep.getBooleanValue()) {
                            vis.setMajorationPeriode("MD");
                        }
                    }

                    List payements = visite.getChildren("Payement");
                    if (payements.isEmpty()) {
                        try {
                            Payement p = new Payement();
                            p.setMoyenPayement(visite.getAttributeValue("Moyen"));
                            String tmpSource = visite.getAttributeValue("Source");
                            if (tmpSource.equals("Sécurité sociale - 100%")) {
                                p.setSourcePayement("Sécurité sociale");
                                p.setMoyenPayement("100%");
                            } else {
                                if (tmpSource.equals("Sécurité sociale - CMU")) {
                                    p.setSourcePayement("Sécurité sociale");
                                    p.setMoyenPayement("CMU");
                                } else {
                                    p.setSourcePayement(tmpSource);
                                }
                            }
                            /* Date Payement */
                            String date = visite.getAttributeValue("DatePayement");
                            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                            p.setDatePayement(format.parse(date));

                            boolean paye = visite.getAttribute("Payé").getBooleanValue();
                            if (paye)
                            {
                                p.setMontant(vis.getTotal(doc.getParametres()));
                            }

                            vis.getPayements().add(p);
                        } catch (ParseException e) {
                            JOptionPane.showMessageDialog(MainWindow.getMainWindow(), e.getLocalizedMessage());
                        } catch (NullPointerException e) {
                            //e.printStackTrace();
                        }

                    } else {

                        Iterator k = payements.iterator();
                        while (k.hasNext()) {
                            Element pay = (Element) k.next();
                            Payement p = new Payement();
                            p.setMoyenPayement(pay.getAttributeValue("Moyen"));
                            try {
                                p.setMontant(pay.getAttribute("Montant").getDoubleValue());
                            } catch (NullPointerException e) {
                            }
                            String tmpSource = pay.getAttributeValue("Source");
                            if (tmpSource.equals("Sécurité sociale - 100%")) {
                                p.setSourcePayement("Sécurité sociale");
                                p.setMoyenPayement("100%");
                            } else {
                                if (tmpSource.equals("Sécurité sociale - CMU")) {
                                    p.setSourcePayement("Sécurité sociale");
                                    p.setMoyenPayement("CMU");
                                } else {
                                    p.setSourcePayement(tmpSource);
                                }
                            }
                            /* Date Payement */
                            try {
                                String date = pay.getAttributeValue("DatePayement");
                                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                                p.setDatePayement(format.parse(date));
                            } catch (ParseException e) {
                                JOptionPane.showMessageDialog(MainWindow.getMainWindow(), e.getLocalizedMessage());
                            } catch (NullPointerException e) {
                            }
                            vis.getPayements().add(p);
                        }
                    }

                } catch (DataConversionException e) {
                    JOptionPane.showMessageDialog(MainWindow.getMainWindow(), e.getLocalizedMessage());
                } catch (NullPointerException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(MainWindow.getMainWindow(), e.getLocalizedMessage());
                }
                cr.getVisites().add(vis);
            }
            doc.getCreneaux().add(cr);
        }

        /* Categories */
        List categories = racine.getChildren("Categorie");
        i = categories.iterator();
        while (i.hasNext()) {
            Element categorie = (Element) i.next();
            Categorie cat = new Categorie();
            cat.setNom(categorie.getAttributeValue("Nom"));
            doc.getCategories().add(cat);
        }

        /* Depenses */
        List depenses = racine.getChildren("Depense");
        i = depenses.iterator();
        while (i.hasNext()) {
            Element depense = (Element) i.next();
            Depense dep = new Depense();
            dep.setVendeur(depense.getAttributeValue("Vendeur"));
            dep.setObjet(depense.getAttributeValue("Objet"));
            try {
                String date = depense.getAttributeValue("Date");
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                dep.setDate(format.parse(date));
            } catch (ParseException e) {
                JOptionPane.showMessageDialog(MainWindow.getMainWindow(), e.getLocalizedMessage());
            } catch (NullPointerException e) {
            }

            try {
                String sourceP = depense.getAttributeValue("Source");
                if (sourceP==null)
                {
                    dep.setSource("Banque");
                }
                else
                {
                    dep.setSource(sourceP);
                }
            } catch (NullPointerException e) {
                dep.setSource("Banque");
            }
            try {
                dep.setMontant(depense.getAttribute("Montant").getDoubleValue());
            } catch (DataConversionException e) {
                JOptionPane.showMessageDialog(MainWindow.getMainWindow(), e.getLocalizedMessage());
            }

            String cat = depense.getAttributeValue("Categorie");
            for (int cpt = 0; cpt < doc.getCategories().size(); cpt++) {
                if (doc.getCategories().get(cpt).getNom().equals(cat)) {
                    dep.setCategorie((Categorie) doc.getCategories().get(cpt));
                    break;
                }
            }
            if (dep.getCategorie() == null) {
                Categorie c = new Categorie();
                c.setNom(cat);
                dep.setCategorie(c);
                doc.getCategories().add(c);
            }
            doc.getDepenses().add(dep);
        }


        /* Versements */
        List versements = racine.getChildren("Versement");
        i = versements.iterator();
        while (i.hasNext()) {
            Element versement = (Element) i.next();
            Versement ver = new Versement();
            try {
                ver.setMontant(versement.getAttribute("Montant").getDoubleValue());
                ver.setId(versement.getAttribute("Id").getIntValue());
            } catch (DataConversionException e) {
                JOptionPane.showMessageDialog(MainWindow.getMainWindow(), e.getLocalizedMessage());
            }
            ver.setDivers(versement.getAttributeValue("Divers"));
            try {
                String date = versement.getAttributeValue("Date");
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                ver.setDate(format.parse(date));

            } catch (ParseException e) {
                JOptionPane.showMessageDialog(MainWindow.getMainWindow(), e.getLocalizedMessage());
            } catch (NullPointerException e) {
                JOptionPane.showMessageDialog(MainWindow.getMainWindow(), e.getLocalizedMessage());
            }

            String nom = versement.getAttributeValue("Nom");
            for (int cpt = 0; cpt < doc.getRemplacants().size(); cpt++) {
                Executant e = doc.getRemplacants().get(cpt);
                if (nom.equals(e.getNom() + ", " + e.getPrenom())) {
                    ver.setExecutant(e);
                    break;
                }
            }

            doc.getVersements().add(ver);
        }


        return doc;
    }
}
