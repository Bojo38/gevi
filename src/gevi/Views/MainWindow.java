/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MainWindow.java
 *
 * Created on 7 avr. 2009, 14:26:03
 */
package gevi.Views;

import gevi.Views.jdgOptions.jdgParametres;
import gevi.Views.jdgOptions.jdgViewUser;
import gevi.Views.jdgOptions.jdgNewDocument;
import gevi.Views.jdgSystem.jdgAbout;
import gevi.Views.jdgSystem.jdgRevisions;
import gevi.Views.jifReports.jifReportCreneauxPeriod;
import gevi.Views.jifGraphs.jifGraphFrais;
import gevi.Views.jifReports.jifReportVisites;
import gevi.Views.jifReports.jifReportFrais;
import gevi.Views.jifReports.jifReportCreneau;
import gevi.Views.jifReports.jifReportVersement;
import gevi.Views.jifReports.jifReportVersements;
import gevi.Model.Creneau;
import gevi.Model.Document;
import gevi.Model.Executant;
import gevi.Model.Payement;
import gevi.Model.Singleton;
import gevi.Model.Utilisateur;
import gevi.Model.Versement;
import gevi.Model.Visite;
import gevi.Model.XML;
import gevi.Settings;
import gevi.Views.jifCertifs.jifCertif;
import gevi.Views.jifGraphs.jifGraphActivite;
import gevi.Views.jifGraphs.jifGraphCreneau;
import gevi.Views.jifGraphs.jifGraphCreneaux;
import gevi.Views.jifGraphs.jifGraphEncaissements;
import gevi.Views.jifGraphs.jifGraphFinancier;
import gevi.Views.jifGraphs.jifGraphImpayes;
import gevi.Views.jifGraphs.jifGraphVersements;
import gevi.Views.jifGraphs.jifGraphVisites;
import gevi.Views.jifReports.jifReportActivite;
import gevi.Views.jifReports.jifReportEncaissements;
import gevi.Views.jifReports.jifReportFinancier;
import gevi.Views.jifReports.jifReportImpayes;
import it.sauronsoftware.ftp4j.FTPAbortedException;
import it.sauronsoftware.ftp4j.FTPClient;
import it.sauronsoftware.ftp4j.FTPDataTransferException;
import it.sauronsoftware.ftp4j.FTPException;
import it.sauronsoftware.ftp4j.FTPFile;
import it.sauronsoftware.ftp4j.FTPIllegalReplyException;
import it.sauronsoftware.ftp4j.FTPListParseException;
import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.UIManager;
import org.jdesktop.swingx.JXTaskPane;
import javax.swing.tree.DefaultTreeModel;
import org.jdesktop.swingx.JXDatePicker;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import java.io.File;
import java.text.ParseException;
import java.util.Date;
import java.util.StringTokenizer;
import jxl.*;
import jxl.read.biff.BiffException;

/**
 *
 * @author root
 */
public class MainWindow extends javax.swing.JFrame {

    DefaultTreeModel treemodel;
    JButton jbtEntrerCreneau;
    JButton jbtSupprimerCreneau;
    JButton jbtTenuComptes;
    JButton jbtTransfert;
    JButton jbtImporterCreneau;
    JButton jbtVoirFrais;
    JButton jbtRealiserVersement;
    JButton jbtDepense;
    JButton jbtVoirRapportVisites;
    JButton jbtVoirTousVersements;
    JButton jbtVoirVersements;
    JButton jbtVoirRapportVersements;
    JButton jbtVoirRapportVersement;
    JComboBox jcbMedecins;
    JButton jbtVoirRapportPeriode;
    JButton jbtVoirRapportCreneau;
    JButton jbtVoirRapportFrais;
    JButton jbtVoirCreneauxPeriode;
    JXDatePicker jxdpDateDebutRapport;
    JXDatePicker jxdpDateFinRapport;
    JXDatePicker jxdpDateDébutDétails;
    JXDatePicker jxdpDateFinDétails;
    JButton jbtVoirActivite;
    JButton jbtVoirFinancier;
    JButton jbtVoirEncaissements;
    JButton jbtVoirImpayes;
    JButton jbtVoirVisites;
    String _filename = "";

    /** Creates new form MainWindow */
    public MainWindow() {

        initComponents();

        setPreferredSize(new java.awt.Dimension(1024, 768));

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gs = ge.getDefaultScreenDevice();
        DisplayMode dmode = gs.getDisplayMode();

        if (dmode != null) {
            int screenWidth = dmode.getWidth();
            int screenHeight = dmode.getHeight();
            this.setLocation((screenWidth - this.getWidth()) / 2, (screenHeight - this.getHeight()) / 2);
        }

        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("gevi/images/sos_bleu.png"));
        setIconImage(icon);

        this.setExtendedState(this.getExtendedState() | this.MAXIMIZED_BOTH);

        _filename = Settings.getSingleton().getProperty("LastFile");


        InitDétailsPane();
        InitRapportPane();

        if (_filename != null) {
            if (!_filename.equals("")) {
                loadFile(_filename);
            }
        }

        repaint();

    }

    protected void InitRapportPane() {
        JLabel debut = new JLabel("Date de début");
        jxdpDateDebutRapport = new JXDatePicker();
        jxtpRapports.add(debut);
        jxtpRapports.add(jxdpDateDebutRapport);
        JLabel fin = new JLabel("Date de fin");
        jxtpRapports.add(fin);
        jxdpDateFinRapport = new JXDatePicker();
        jxtpRapports.add(jxdpDateFinRapport);

        jbtVoirActivite = new JButton("Rapport d'activité");
        jbtVoirActivite.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gevi/images/sos_bleu_l.png")));
        jbtVoirActivite.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                displayWindow(CST_RAPPORT_ACTIVITE, Singleton.instance().getDocument());
            }
        });
        jxtpRapports.add(jbtVoirActivite);

        jbtVoirFinancier = new JButton("Rapport financier");
        jbtVoirFinancier.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gevi/images/sos_bleu_l.png")));
        jbtVoirFinancier.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                displayWindow(CST_RAPPORT_FINANCIER, Singleton.instance().getDocument());
            }
        });
        jxtpRapports.add(jbtVoirFinancier);

        jbtVoirEncaissements = new JButton("Rapport des visites encaissées");
        jbtVoirEncaissements.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gevi/images/sos_rouge_l.png")));
        jbtVoirEncaissements.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                displayWindow(CST_RAPPORT_ENCAISSEMENTS, Singleton.instance().getDocument());
            }
        });
        jxtpRapports.add(jbtVoirEncaissements);

        jbtVoirImpayes = new JButton("Rapport des visites impayées");
        jbtVoirImpayes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gevi/images/sos_rouge_l.png")));
        jbtVoirImpayes.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                displayWindow(CST_RAPPORT_IMPAYES, Singleton.instance().getDocument());
            }
        });
        jxtpRapports.add(jbtVoirImpayes);

        jbtVoirRapportPeriode = new JButton("Rapport des créneaux de la période");
        jbtVoirRapportPeriode.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gevi/images/sos_jaune_l.png")));
        jbtVoirRapportPeriode.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                displayWindow(CST_RAPPORT_CRENEAUX, Singleton.instance().getDocument());
            }
        });
        jxtpRapports.add(jbtVoirRapportPeriode);

        jbtVoirRapportCreneau = new JButton("Rapport d'un créneau");
        jbtVoirRapportCreneau.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gevi/images/sos_jaune_l.png")));
        jbtVoirRapportCreneau.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jdgSelectionCreneau selection = new jdgSelectionCreneau(MainWindow.getMainWindow(), true, Singleton.instance().getDocument(), jxdpDateDebutRapport.getDate(), jxdpDateFinRapport.getDate(), jcbMedecins.getSelectedIndex(), 1);
                selection.setVisible(true);
                selection = null;
            }
        });
        jxtpRapports.add(jbtVoirRapportCreneau);

        jbtVoirRapportVisites = new JButton("Rapport des visites");
        jbtVoirRapportVisites.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gevi/images/sos_vert_l.png")));
        jbtVoirRapportVisites.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                displayWindow(CST_RAPPORT_VISITES, Singleton.instance().getDocument());
            }
        });
        jxtpRapports.add(jbtVoirRapportVisites);

        jbtVoirRapportFrais = new JButton("Rapport des dépenses");
        jbtVoirRapportFrais.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gevi/images/sos_violet_l.png")));
        jbtVoirRapportFrais.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                displayWindow(CST_RAPPORT_FRAIS, Singleton.instance().getDocument());
            }
        });
        jxtpRapports.add(jbtVoirRapportFrais);

        jbtVoirRapportVersement = new JButton("Rapport d'un versement");
        jbtVoirRapportVersement.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gevi/images/sos_cyan_l.png")));
        jbtVoirRapportVersement.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                jdgSelectionVersements jdg = new jdgSelectionVersements(MainWindow.getMainWindow(), true, Singleton.instance().getDocument(), jxdpDateDebutRapport.getDate(), jxdpDateFinRapport.getDate(), jcbMedecins.getSelectedIndex(), true);
                jdg.setVisible(true);
                jdg = null;
            }
        });
        jxtpRapports.add(jbtVoirRapportVersement);

        jbtVoirRapportVersements = new JButton("Rapport des versements");
        jbtVoirRapportVersements.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gevi/images/sos_cyan_l.png")));
        jbtVoirRapportVersements.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                displayWindow(CST_RAPPORT_VERSEMENTS, Singleton.instance().getDocument());
            }
        });
        jxtpRapports.add(jbtVoirRapportVersements);
    }

    protected void InitDétailsPane() {
        JLabel debut = new JLabel("Date de début");
        jxdpDateDébutDétails = new JXDatePicker();
        jxtpDétails.add(debut);
        jxtpDétails.add(jxdpDateDébutDétails);
        JLabel fin = new JLabel("Date de fin");
        jxtpDétails.add(fin);
        jxdpDateFinDétails = new JXDatePicker();
        jxtpDétails.add(jxdpDateFinDétails);
        JLabel qui = new JLabel("Medecin");
        jxtpDétails.add(qui);
        jcbMedecins = new JComboBox();
        jxtpDétails.add(jcbMedecins);

        jbtTransfert = new JButton("Transfert d'argent");
        jbtTransfert.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gevi/images/sos_rouge_l.png")));
        jbtTransfert.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jdgTransfert transfert = new jdgTransfert(MainWindow.getMainWindow(), true, Singleton.instance().getDocument());
                transfert.setVisible(true);
                transfert = null;
            }
        });
        jxtpDétails.add(jbtTransfert);

        jbtTenuComptes = new JButton("Gérer les comptes");
        jbtTenuComptes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gevi/images/sos_rouge_l.png")));
        jbtTenuComptes.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                displayWindow(CST_TENU_COMPTES, Singleton.instance().getDocument());
            }
        });
        jxtpDétails.add(jbtTenuComptes);

        jbtEntrerCreneau = new JButton("Ajouter un créneau");
        jbtEntrerCreneau.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gevi/images/sos_jaune_l.png")));
        jbtEntrerCreneau.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jdgSaisieCreneau saisie = new jdgSaisieCreneau(MainWindow.getMainWindow(), true, Singleton.instance().getDocument());
                saisie.setVisible(true);
                saisie = null;
            }
        });
        jxtpDétails.add(jbtEntrerCreneau);

        jbtSupprimerCreneau = new JButton("Supprimer un créneau");
        jbtSupprimerCreneau.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gevi/images/sos_jaune_l.png")));
        jbtSupprimerCreneau.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jdgSelectionCreneau selection = new jdgSelectionCreneau(MainWindow.getMainWindow(), true, Singleton.instance().getDocument(), jxdpDateDébutDétails.getDate(), jxdpDateFinDétails.getDate(), jcbMedecins.getSelectedIndex(), -1);
                selection.setVisible(true);
                selection = null;
            }
        });
        jxtpDétails.add(jbtSupprimerCreneau);

        jbtVoirCreneauxPeriode = new JButton("Gérer les créneaux");
        jbtVoirCreneauxPeriode.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gevi/images/sos_jaune_l.png")));
        jbtVoirCreneauxPeriode.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jdgSelectionCreneau selection = new jdgSelectionCreneau(MainWindow.getMainWindow(), true, Singleton.instance().getDocument(), jxdpDateDébutDétails.getDate(), jxdpDateFinDétails.getDate(), jcbMedecins.getSelectedIndex(), 0);
                selection.setVisible(true);
                selection = null;
            }
        });
        jxtpDétails.add(jbtVoirCreneauxPeriode);

        jbtVoirVisites = new JButton("Gérer les visites");
        jbtVoirVisites.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gevi/images/sos_vert_l.png")));
        jbtVoirVisites.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                displayWindow(CST_VISITES, Singleton.instance().getDocument());
            }
        });
        jxtpDétails.add(jbtVoirVisites);

        jbtVoirFrais = new JButton("Gérer les frais");
        jbtVoirFrais.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gevi/images/sos_violet_l.png")));
        jbtVoirFrais.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                displayWindow(CST_FRAIS, Singleton.instance().getDocument());
            }
        });
        jxtpDétails.add(jbtVoirFrais);

        jbtDepense = new JButton("Ajouter une dépense");
        jbtDepense.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gevi/images/sos_violet_l.png")));
        jbtDepense.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                jdgDepense jdg = new jdgDepense(MainWindow.getMainWindow(), true, Singleton.instance().getDocument());
                jdg.setVisible(true);
                jdg = null;
            }
        });
        jxtpDétails.add(jbtDepense);

        jbtVoirTousVersements = new JButton("Gérer les versements");
        jbtVoirTousVersements.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gevi/images/sos_cyan_l.png")));
        jbtVoirTousVersements.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                displayWindow(CST_VERSEMENTS, Singleton.instance().getDocument());
            }
        });
        jxtpDétails.add(jbtVoirTousVersements);

        jbtVoirVersements = new JButton("Voir un versement");
        jbtVoirVersements.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gevi/images/sos_cyan_l.png")));
        jbtVoirVersements.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                jdgSelectionVersements jdg = new jdgSelectionVersements(MainWindow.getMainWindow(), true, Singleton.instance().getDocument(), jxdpDateDébutDétails.getDate(), jxdpDateFinDétails.getDate(), jcbMedecins.getSelectedIndex(), false);
                jdg.setVisible(true);
                jdg = null;
            }
        });

        jxtpDétails.add(jbtVoirVersements);
        jbtRealiserVersement = new JButton("Ajouter un versement");
        jbtRealiserVersement.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gevi/images/sos_cyan_l.png")));
        jbtRealiserVersement.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                jdgBilan jdg = new jdgBilan(MainWindow.getMainWindow(), true, Singleton.instance().getDocument());
                jdg.setVisible(true);
                jdg = null;
            }
        });
        jxtpDétails.add(jbtRealiserVersement);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jdpMain = new javax.swing.JDesktopPane();
        jXTaskPaneContainer1 = new org.jdesktop.swingx.JXTaskPaneContainer();
        jxtpDétails = new org.jdesktop.swingx.JXTaskPane();
        jxtpRapports = new org.jdesktop.swingx.JXTaskPane();
        jPanel1 = new javax.swing.JPanel();
        jbtAjouterCréneau = new javax.swing.JButton();
        jbtAddDepense = new javax.swing.JButton();
        jbtAjouterReversement = new javax.swing.JButton();
        jbtAddTransfert = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jmnFichier = new javax.swing.JMenu();
        jmiNouveau = new javax.swing.JMenuItem();
        jmiOuvrir = new javax.swing.JMenuItem();
        jmiEnregistrer = new javax.swing.JMenuItem();
        jmiEnregistrerSous = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JSeparator();
        jmiImport = new javax.swing.JMenuItem();
        jSeparator10 = new javax.swing.JSeparator();
        jmiFTPSave = new javax.swing.JMenuItem();
        jmiFTPDelete = new javax.swing.JMenuItem();
        jmiFTPLoad = new javax.swing.JMenuItem();
        jSeparator9 = new javax.swing.JSeparator();
        jmiQuitter = new javax.swing.JMenuItem();
        jmnOptions = new javax.swing.JMenu();
        jmiRemplacantListe = new javax.swing.JMenuItem();
        jmiUtilisateur = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JSeparator();
        jmiParametres = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JSeparator();
        jmiCategories = new javax.swing.JMenuItem();
        jmnGraphiques = new javax.swing.JMenu();
        jmiActivite = new javax.swing.JMenuItem();
        jmiFinancier = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JSeparator();
        jmiEncaissement = new javax.swing.JMenuItem();
        jmiImpayes = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JSeparator();
        jmiCreneaux = new javax.swing.JMenuItem();
        jmiCreneau = new javax.swing.JMenuItem();
        jSeparator6 = new javax.swing.JSeparator();
        jmiVisites = new javax.swing.JMenuItem();
        jSeparator7 = new javax.swing.JSeparator();
        jmiDepenses = new javax.swing.JMenuItem();
        jSeparator8 = new javax.swing.JSeparator();
        jmiVersements = new javax.swing.JMenuItem();
        jmnCertificats = new javax.swing.JMenu();
        jmiGAV = new javax.swing.JMenuItem();
        jmiHDT = new javax.swing.JMenuItem();
        jmiMEB = new javax.swing.JMenuItem();
        jmiCEB = new javax.swing.JMenuItem();
        jmnFenetres = new javax.swing.JMenu();
        jmiCascade = new javax.swing.JMenuItem();
        jmiMosaique = new javax.swing.JMenuItem();
        jmiFermerTout = new javax.swing.JMenuItem();
        jsepFenetres = new javax.swing.JSeparator();
        jmnHelp = new javax.swing.JMenu();
        jmiAbout = new javax.swing.JMenuItem();
        jmiRevisions = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gestion des Visites");
        setIconImages(null);
        setMinimumSize(new java.awt.Dimension(640, 480));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().add(jdpMain, java.awt.BorderLayout.CENTER);

        jXTaskPaneContainer1.setAutoscrolls(true);
        jXTaskPaneContainer1.setScrollableTracksViewportHeight(true);

        jxtpDétails.setTitle("Détails");
        jxtpDétails.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jxtpDétailsPropertyChange(evt);
            }
        });
        jXTaskPaneContainer1.add(jxtpDétails);

        jxtpRapports.setCollapsed(true);
        jxtpRapports.setScrollOnExpand(true);
        jxtpRapports.setTitle("Rapports");
        jxtpRapports.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jxtpRapportsPropertyChange(evt);
            }
        });
        jXTaskPaneContainer1.add(jxtpRapports);

        getContentPane().add(jXTaskPaneContainer1, java.awt.BorderLayout.WEST);

        jPanel1.setLayout(new java.awt.GridLayout(1, 0));

        jbtAjouterCréneau.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gevi/images/sos_jaune_l.png"))); // NOI18N
        jbtAjouterCréneau.setText("Ajouter un créneau");
        jbtAjouterCréneau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtAjouterCréneauActionPerformed(evt);
            }
        });
        jPanel1.add(jbtAjouterCréneau);

        jbtAddDepense.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gevi/images/sos_violet_l.png"))); // NOI18N
        jbtAddDepense.setText("Ajouter une dépense");
        jbtAddDepense.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtAddDepenseActionPerformed(evt);
            }
        });
        jPanel1.add(jbtAddDepense);

        jbtAjouterReversement.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gevi/images/sos_cyan_l.png"))); // NOI18N
        jbtAjouterReversement.setText("Ajouter un reversement");
        jbtAjouterReversement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtAjouterReversementActionPerformed(evt);
            }
        });
        jPanel1.add(jbtAjouterReversement);

        jbtAddTransfert.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gevi/images/sos_rouge_l.png"))); // NOI18N
        jbtAddTransfert.setText("Ajouter un transfert");
        jbtAddTransfert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtAddTransfertActionPerformed(evt);
            }
        });
        jPanel1.add(jbtAddTransfert);

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jmnFichier.setText("Fichier");

        jmiNouveau.setText("Nouveau");
        jmiNouveau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiNouveauActionPerformed(evt);
            }
        });
        jmnFichier.add(jmiNouveau);

        jmiOuvrir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        jmiOuvrir.setText("Ouvrir");
        jmiOuvrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiOuvrirActionPerformed(evt);
            }
        });
        jmnFichier.add(jmiOuvrir);

        jmiEnregistrer.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jmiEnregistrer.setText("Enregistrer");
        jmiEnregistrer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiEnregistrerActionPerformed(evt);
            }
        });
        jmnFichier.add(jmiEnregistrer);

        jmiEnregistrerSous.setText("Enregistrer sous ...");
        jmiEnregistrerSous.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiEnregistrerSousActionPerformed(evt);
            }
        });
        jmnFichier.add(jmiEnregistrerSous);
        jmnFichier.add(jSeparator1);

        jmiImport.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        jmiImport.setText("Importer depuis un fichier XLS");
        jmiImport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiImportActionPerformed(evt);
            }
        });
        jmnFichier.add(jmiImport);
        jmnFichier.add(jSeparator10);

        jmiFTPSave.setText("Sauvegarde par FTP");
        jmiFTPSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiFTPSaveActionPerformed(evt);
            }
        });
        jmnFichier.add(jmiFTPSave);

        jmiFTPDelete.setText("Effacer un fichier FTP");
        jmiFTPDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiFTPDeleteActionPerformed(evt);
            }
        });
        jmnFichier.add(jmiFTPDelete);

        jmiFTPLoad.setText("Chargement par FTP");
        jmiFTPLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiFTPLoadActionPerformed(evt);
            }
        });
        jmnFichier.add(jmiFTPLoad);
        jmnFichier.add(jSeparator9);

        jmiQuitter.setText("Quitter");
        jmiQuitter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiQuitterActionPerformed(evt);
            }
        });
        jmnFichier.add(jmiQuitter);

        jMenuBar1.add(jmnFichier);

        jmnOptions.setText("Options");

        jmiRemplacantListe.setText("Liste des remplaçants");
        jmiRemplacantListe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiRemplacantListeActionPerformed(evt);
            }
        });
        jmnOptions.add(jmiRemplacantListe);

        jmiUtilisateur.setText("Utilisateur");
        jmiUtilisateur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiUtilisateurActionPerformed(evt);
            }
        });
        jmnOptions.add(jmiUtilisateur);
        jmnOptions.add(jSeparator2);

        jmiParametres.setText("Parametres");
        jmiParametres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiParametresActionPerformed(evt);
            }
        });
        jmnOptions.add(jmiParametres);
        jmnOptions.add(jSeparator3);

        jmiCategories.setText("Categories de dépenses");
        jmiCategories.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiCategoriesActionPerformed(evt);
            }
        });
        jmnOptions.add(jmiCategories);

        jMenuBar1.add(jmnOptions);

        jmnGraphiques.setText("Graphiques");

        jmiActivite.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gevi/images/sos_bleu.png"))); // NOI18N
        jmiActivite.setText("Activité");
        jmiActivite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiActiviteActionPerformed(evt);
            }
        });
        jmnGraphiques.add(jmiActivite);

        jmiFinancier.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gevi/images/sos_bleu.png"))); // NOI18N
        jmiFinancier.setText("Financier");
        jmiFinancier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiFinancierActionPerformed(evt);
            }
        });
        jmnGraphiques.add(jmiFinancier);
        jmnGraphiques.add(jSeparator4);

        jmiEncaissement.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gevi/images/sos_rose.png"))); // NOI18N
        jmiEncaissement.setText("Encaissements");
        jmiEncaissement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiEncaissementActionPerformed(evt);
            }
        });
        jmnGraphiques.add(jmiEncaissement);

        jmiImpayes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gevi/images/sos_rose.png"))); // NOI18N
        jmiImpayes.setText("Impayés");
        jmiImpayes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiImpayesActionPerformed(evt);
            }
        });
        jmnGraphiques.add(jmiImpayes);
        jmnGraphiques.add(jSeparator5);

        jmiCreneaux.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gevi/images/sos_jaune.png"))); // NOI18N
        jmiCreneaux.setText("Créneaux");
        jmiCreneaux.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiCreneauxActionPerformed(evt);
            }
        });
        jmnGraphiques.add(jmiCreneaux);

        jmiCreneau.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gevi/images/sos_jaune.png"))); // NOI18N
        jmiCreneau.setText("Créneau");
        jmiCreneau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiCreneauActionPerformed(evt);
            }
        });
        jmnGraphiques.add(jmiCreneau);
        jmnGraphiques.add(jSeparator6);

        jmiVisites.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gevi/images/sos_vert.png"))); // NOI18N
        jmiVisites.setText("Visites");
        jmiVisites.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiVisitesActionPerformed(evt);
            }
        });
        jmnGraphiques.add(jmiVisites);
        jmnGraphiques.add(jSeparator7);

        jmiDepenses.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gevi/images/sos_violet.png"))); // NOI18N
        jmiDepenses.setText("Dépenses");
        jmiDepenses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiDepensesActionPerformed(evt);
            }
        });
        jmnGraphiques.add(jmiDepenses);
        jmnGraphiques.add(jSeparator8);

        jmiVersements.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gevi/images/sos_cyan.png"))); // NOI18N
        jmiVersements.setText("Versements");
        jmiVersements.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiVersementsActionPerformed(evt);
            }
        });
        jmnGraphiques.add(jmiVersements);

        jMenuBar1.add(jmnGraphiques);

        jmnCertificats.setText("Certificats");

        jmiGAV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gevi/images/sos_blanc.png"))); // NOI18N
        jmiGAV.setText("Garde à vue");
        jmiGAV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiGAVActionPerformed(evt);
            }
        });
        jmnCertificats.add(jmiGAV);

        jmiHDT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gevi/images/sos_blanc.png"))); // NOI18N
        jmiHDT.setText("Hospitalisation à la demande d'un tiers");
        jmiHDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiHDTActionPerformed(evt);
            }
        });
        jmnCertificats.add(jmiHDT);

        jmiMEB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gevi/images/sos_blanc.png"))); // NOI18N
        jmiMEB.setText("Mise en bière");
        jmiMEB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiMEBActionPerformed(evt);
            }
        });
        jmnCertificats.add(jmiMEB);

        jmiCEB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gevi/images/sos_blanc.png"))); // NOI18N
        jmiCEB.setText("Coups et blessures");
        jmiCEB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiCEBActionPerformed(evt);
            }
        });
        jmnCertificats.add(jmiCEB);

        jMenuBar1.add(jmnCertificats);

        jmnFenetres.setText("Fenêtres");

        jmiCascade.setText("Cascade");
        jmiCascade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiCascadeActionPerformed(evt);
            }
        });
        jmnFenetres.add(jmiCascade);

        jmiMosaique.setText("Mosaïque");
        jmiMosaique.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiMosaiqueActionPerformed(evt);
            }
        });
        jmnFenetres.add(jmiMosaique);

        jmiFermerTout.setText("Fermer toutes les fenêtres");
        jmiFermerTout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiFermerToutActionPerformed(evt);
            }
        });
        jmnFenetres.add(jmiFermerTout);
        jmnFenetres.add(jsepFenetres);

        jMenuBar1.add(jmnFenetres);

        jmnHelp.setText("?");

        jmiAbout.setText("A propos de");
        jmiAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiAboutActionPerformed(evt);
            }
        });
        jmnHelp.add(jmiAbout);

        jmiRevisions.setText("Révisions logicielles");
        jmiRevisions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiRevisionsActionPerformed(evt);
            }
        });
        jmnHelp.add(jmiRevisions);

        jMenuBar1.add(jmnHelp);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jmiParametresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiParametresActionPerformed

        jdgParametres params = new jdgParametres(this, true, Singleton.instance().getDocument().getParametres());
        params.setVisible(true);
        params = null;
}//GEN-LAST:event_jmiParametresActionPerformed

    private void jmiOuvrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiOuvrirActionPerformed
        if (!_filename.equals("")) {
            try {
                XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
                SimpleDateFormat format = new SimpleDateFormat(" -- yyyy-MM-dd hh-mm-ss");
                sortie.output(XML.convertDocumentToXML(Singleton.instance().getDocument()), new FileOutputStream(_filename + format.format(new Date())));
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(MainWindow.getMainWindow(), e.getLocalizedMessage());
                e.printStackTrace();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(MainWindow.getMainWindow(), e.getLocalizedMessage());
                e.printStackTrace();
            }
        }

        JFileChooser chooser = new JFileChooser();
        chooser.setMultiSelectionEnabled(false);
        int filechoosed = chooser.showOpenDialog(this);
        if (filechoosed == JFileChooser.APPROVE_OPTION) {
            String filename = chooser.getSelectedFile().getAbsolutePath();
            loadFile(filename);
        }
        repaint();
}//GEN-LAST:event_jmiOuvrirActionPerformed

    private void loadFile(String filename) {
        SAXBuilder sxb = new SAXBuilder();
        try {
            org.jdom.Document document = sxb.build(new File(filename));
            Singleton.instance().setDocument(XML.convertXMLToDocument(document));
            setHMIEnabled(Singleton.instance().getDocument() != null);
            Settings.getSingleton().setProperty("LastFile", filename);
            _filename = filename;
        } catch (JDOMException e) {
            JOptionPane.showMessageDialog(MainWindow.getMainWindow(), e.getLocalizedMessage());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(MainWindow.getMainWindow(), e.getMessage());
        }

        try {
            Singleton.instance().getDocument().getParametres().setSavePeriod(Integer.parseInt(Settings.getSingleton().getProperty("SavePeriod")));
        } catch (NumberFormatException e) {
            Singleton.instance().getDocument().getParametres().setSavePeriod(30000);
        } catch (NullPointerException e) {
        }
    }

    private String toLatin(String val) {
        String tmp = val.replace("\u00E0", "à");
        tmp = tmp.replace("\u00e2", "â");
        tmp = tmp.replace("\u00e4", "ä");
        tmp = tmp.replace("\u00e7", "ç");
        tmp = tmp.replace("\ufffd", "è");
        tmp = tmp.replace("\u00e9", "é");
        tmp = tmp.replace("\u00ea", "ê");
        tmp = tmp.replace("\u00eb", "ë");
        tmp = tmp.replace("\u00ee", "î");
        tmp = tmp.replace("\u00ef", "ï");
        tmp = tmp.replace("\u00f4", "ô");
        tmp = tmp.replace("\u00f6", "ö");
        tmp = tmp.replace("\u00f9", "ù");
        tmp = tmp.replace("\u00fb", "û");
        tmp = tmp.replace("\u00fc", "ü");
        return tmp;
    }

    private void ImportFile(String file) {
        try {
            Workbook wb = Workbook.getWorkbook(new File(file));
            Sheet sheets[] = wb.getSheets();
            for (int i = 0; i < sheets.length; i++) {
                Creneau c = new Creneau();
                SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                String tmp = sheets[i].getName();
                Date d;
                try {
                    d = format.parse(tmp);
                    c.setDate(d);
                    int nb = sheets[i].getRows();
                    for (int j = 1; j < nb; j++) {
                        Cell ligne[] = sheets[i].getRow(j);
                        Visite v = new Visite();

                        v.setNom(toLatin(ligne[0].getContents()));
                        if (ligne.length <= 7) {
                            v.setDescription(toLatin(ligne[1].getContents()));
                        } else {
                            v.setDescription(toLatin(ligne[1].getContents()) + "\nChèque n°" + toLatin(ligne[7].getContents()));
                        }
                        v.setDiagnostique(toLatin(ligne[2].getContents()));

                        if (ligne.length > 3) {
                            StringTokenizer st = new StringTokenizer(toLatin(ligne[3].getContents()), "+");
                            while (st.hasMoreElements()) {
                                String el = st.nextToken();
                                el = el.toUpperCase();
                                if (el.equals("GAV")) {
                                    v.setTypeVisite("GAV");
                                    continue;
                                }

                                if (el.equals("IPM")) {
                                    v.setTypeVisite("IPM");
                                    continue;
                                }
                                if (el.equals("AG")) {
                                    v.setTypeVisite("AG");
                                    continue;
                                }
                                if (el.equals("C")) {
                                    v.setTypeVisite("C");
                                    continue;
                                }
                                if (el.equals("V")) {
                                    v.setTypeVisite("V");
                                    continue;
                                }

                                if (el.equals("V")) {
                                    v.setTypeVisite("V");
                                    continue;
                                }
                                if (el.equals("VMD")) {
                                    v.setTypeVisite("V");
                                    v.setMajorationPeriode("MD");
                                    continue;
                                }
                                if (el.equals("VRD")) {
                                    v.setTypeVisite("V");
                                    v.setMajorationPeriode("VRD");
                                    continue;
                                }
                                if (el.equals("VRN")) {
                                    v.setTypeVisite("V");
                                    v.setMajorationPeriode("VRN");
                                    continue;
                                }
                                if (el.equals("VRM")) {
                                    v.setTypeVisite("V");
                                    v.setMajorationPeriode("VRM");
                                    continue;
                                }
                                if (el.equals("CRD")) {
                                    v.setTypeVisite("C");
                                    v.setMajorationPeriode("CRD");
                                    continue;
                                }
                                if (el.equals("CRN")) {
                                    v.setTypeVisite("C");
                                    v.setMajorationPeriode("CRN");
                                    continue;
                                }
                                if (el.equals("CRM")) {
                                    v.setTypeVisite("C");
                                    v.setMajorationPeriode("CRM");
                                    continue;
                                }
                                if (el.equals("MNO")) {
                                    v.setMajorationNourrisson(true);
                                    continue;
                                }
                                if (el.equals("ECG")) {
                                    v.setECG(true);
                                    v.setECGDomicile(true);
                                    continue;
                                }
                                if (el.equals("UV")) {
                                    v.setUrgenceVitale(true);
                                    continue;
                                }
                                if (el.contains("IK")) {
                                    v.setIK(Integer.parseInt(el.substring(0, el.indexOf("IK"))));
                                    continue;
                                }
                                if (el.contains("IKM")) {
                                    v.setIKM(Integer.parseInt(el.substring(0, el.indexOf("IKM"))));
                                    continue;
                                }
                            }
                            if (ligne.length > 5) {
                                tmp = toLatin(ligne[4].getContents());
                                String source = toLatin(ligne[5].getContents());
                                if (source.equals("Sécu")) {
                                    source = "Sécurité sociale";
                                }
                                if (source.equals("")) {
                                    source = "Particulier";
                                }
                                if (source.equals("Retraite")) {
                                    source = "Maison de retraite";
                                }
                                if (source.equals("CH")) {
                                    source = "Hopital";
                                }
                                if (tmp.equals("Chèque")) {
                                    Payement p = new Payement();
                                    p.setMoyenPayement("Chèque");
                                    p.setSourcePayement(source);
                                    p.setMontant(0);
                                    v.getPayements().add(p);
                                }
                                if (tmp.equals("ALD")) {
                                    Payement p = new Payement();
                                    p.setMoyenPayement("100%");
                                    p.setSourcePayement("Sécurité sociale");
                                    p.setMontant(0);
                                    v.getPayements().add(p);
                                }
                                if (tmp.equals("CMU")) {
                                    Payement p = new Payement();
                                    p.setMoyenPayement("CMU");
                                    p.setSourcePayement("Sécurité sociale");
                                    p.setMontant(0);
                                    v.getPayements().add(p);
                                }
                                if (tmp.equals("TP")) {
                                    Payement p = new Payement();
                                    p.setMoyenPayement("Tiers payant");
                                    p.setSourcePayement("Sécurité sociale");
                                    p.setMontant(0);
                                    v.getPayements().add(p);
                                }
                                if (tmp.equals("Espèces")) {
                                    Payement p = new Payement();
                                    p.setMoyenPayement("Espèces");
                                    p.setSourcePayement("Particulier");
                                    p.setMontant(v.getTotal());
                                    v.getPayements().add(p);
                                }
                            }
                        }
                        c.getVisites().add(v);
                    }
                    Singleton.instance().getDocument().getCreneaux().add(c);
                } catch (ParseException e) {
                    JOptionPane.showMessageDialog(this, "Le format de la date est incorrect: " + tmp);
                }
            }
            wb.close();
            JOptionPane.showMessageDialog(this, "Importation réussie");
            return;

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, e.getLocalizedMessage());
        } catch (BiffException e) {
            JOptionPane.showMessageDialog(this, e.getLocalizedMessage());
        }
        JOptionPane.showMessageDialog(this, "Importation Echouée, format du fichier incorrecte");
    }

    private void jmiNouveauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiNouveauActionPerformed
        jdgNewDocument newDoc = new jdgNewDocument(this, true);
        newDoc.setVisible(true);
        newDoc = null;
        repaint();
    }//GEN-LAST:event_jmiNouveauActionPerformed

    private void jmiQuitterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiQuitterActionPerformed

        Settings.getSingleton().setProperty("SavePeriod", ((Integer) Singleton.instance().getDocument().getParametres().getSavePeriod()).toString());
        save.setStop();

        if (Singleton.instance().getDocument() != null) {

            String options[] = {"Oui", "Non", "Dans un autre fichier"};
            int res = JOptionPane.showOptionDialog(this,
                    "Voulez-vous sauver le document dans le fichier " + _filename + " ?",
                    "Quitter", JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE,
                    null, options, 0);
            if (res == 0) {
                jmiEnregistrerActionPerformed(evt);
            }
            if (res == 2) {
                int save = JOptionPane.showConfirmDialog(this, "Voulez-vous sauver le document ?", "Quitte", JOptionPane.YES_NO_OPTION);
                if (save == JOptionPane.YES_OPTION) {
                    _filename = "";
                    jmiEnregistrerActionPerformed(evt);
                }
            }
        }
        this.setVisible(false);
        System.exit(0);
    }//GEN-LAST:event_jmiQuitterActionPerformed

    private void jmiEnregistrerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiEnregistrerActionPerformed
        if (_filename.equals("")) {
            JFileChooser chooser = new JFileChooser();
            int filechoosed = chooser.showSaveDialog(this);
            if (filechoosed == JFileChooser.APPROVE_OPTION) {
                _filename = chooser.getSelectedFile().getAbsolutePath();
            }
        }
        if (!_filename.equals("")) {
            try {
                /*FileOutputStream fos = new FileOutputStream(filename);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(Singleton.instance().getDocument());
                oos.close();
                fos.close();*/

                XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
                sortie.output(XML.convertDocumentToXML(Singleton.instance().getDocument()), new FileOutputStream(_filename));
                Settings.getSingleton().setProperty("LastFile", _filename);
                Settings.getSingleton().saveProperties();

            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(MainWindow.getMainWindow(), e.getMessage());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(MainWindow.getMainWindow(), e.getMessage());
            }
        }
        repaint();
    }//GEN-LAST:event_jmiEnregistrerActionPerformed

    private void jmiEnregistrerSousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiEnregistrerSousActionPerformed

        _filename = "";
        jmiEnregistrerActionPerformed(evt);
    }//GEN-LAST:event_jmiEnregistrerSousActionPerformed

    private void jmiUtilisateurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiUtilisateurActionPerformed
        jdgViewUser vu = new jdgViewUser(this, true, Singleton.instance().getDocument());
        vu.setVisible(true);
        vu = null;
}//GEN-LAST:event_jmiUtilisateurActionPerformed

    private void jmiRemplacantListeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiRemplacantListeActionPerformed
        jdgRemplacants remp = new jdgRemplacants(this, true, Singleton.instance().getDocument());
        remp.setVisible(true);
        remp = null;
}//GEN-LAST:event_jmiRemplacantListeActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        jmiQuitterActionPerformed(null);
    }//GEN-LAST:event_formWindowClosing

    private void jmiFermerToutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiFermerToutActionPerformed
        for (int i = 0; i < jdpMain.getAllFrames().length; i++) {
            jdpMain.getAllFrames()[i].setVisible(false);
        }
        jdpMain.removeAll();
    }//GEN-LAST:event_jmiFermerToutActionPerformed

    private void jmiCascadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiCascadeActionPerformed
        for (int i = 0; i < jdpMain.getAllFrames().length; i++) {
            JInternalFrame frame = jdpMain.getAllFrames()[i];
            try {
                frame.setIcon(false);
                frame.setSelected(true);
            } catch (PropertyVetoException e) {
                JOptionPane.showMessageDialog(this, e.getLocalizedMessage());
            }

            frame.setLocation((i * 20) % (jdpMain.getWidth() / 2) + (i * 20) / (jdpMain.getWidth() / 2), (i * 10) % (jdpMain.getHeight() / 2));
        }
    }//GEN-LAST:event_jmiCascadeActionPerformed

    private void jmiMosaiqueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiMosaiqueActionPerformed
        int nbWindow = jdpMain.getAllFrames().length;

        int nbCol = (int) Math.round(Math.sqrt(nbWindow));
        int nbRow = (int) nbWindow / nbCol;
        if (nbWindow % nbCol > 0) {
            nbRow++;
        }
        int width = jdpMain.getWidth() / nbCol;
        int heigth = jdpMain.getHeight() / nbRow;

        for (int i = 0; i < jdpMain.getAllFrames().length; i++) {
            JInternalFrame frame = jdpMain.getAllFrames()[i];
            try {
                frame.setIcon(false);
                frame.setSelected(true);
            } catch (PropertyVetoException e) {
                JOptionPane.showMessageDialog(this, e.getLocalizedMessage());
            }
            frame.setBounds((i % nbCol) * width, (i / nbCol) * heigth, width, heigth);
        }
}//GEN-LAST:event_jmiMosaiqueActionPerformed

    private void jmiCategoriesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiCategoriesActionPerformed
        jdgCategories jdg = new jdgCategories(this, true, Singleton.instance().getDocument());
        jdg.setVisible(true);
        jdg = null;
}//GEN-LAST:event_jmiCategoriesActionPerformed

    private void jmiActiviteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiActiviteActionPerformed
        displayWindow(CST_RAPPORT_ACTIVITE_GRAPH, Singleton.instance().getDocument());
}//GEN-LAST:event_jmiActiviteActionPerformed

    private void jmiEncaissementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiEncaissementActionPerformed
        displayWindow(CST_RAPPORT_ENCAISSEMENTS_GRAPH, Singleton.instance().getDocument());
}//GEN-LAST:event_jmiEncaissementActionPerformed

    private void jmiCreneauxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiCreneauxActionPerformed
        displayWindow(CST_RAPPORT_CRENEAUX_GRAPH, Singleton.instance().getDocument());
}//GEN-LAST:event_jmiCreneauxActionPerformed

    private void jmiFinancierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiFinancierActionPerformed
        displayWindow(CST_RAPPORT_FINANCIER_GRAPH, Singleton.instance().getDocument());
}//GEN-LAST:event_jmiFinancierActionPerformed

    private void jmiImpayesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiImpayesActionPerformed
        displayWindow(CST_RAPPORT_IMPAYES_GRAPH, Singleton.instance().getDocument());
}//GEN-LAST:event_jmiImpayesActionPerformed

    private void jmiCreneauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiCreneauActionPerformed
        jdgSelectionCreneau selection = new jdgSelectionCreneau(MainWindow.getMainWindow(), true, Singleton.instance().getDocument(), Singleton.instance().getDocument().getDate(), new Date(), 0, 2);
        selection.setVisible(true);
        selection = null;
}//GEN-LAST:event_jmiCreneauActionPerformed

    private void jmiVisitesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiVisitesActionPerformed
        displayWindow(CST_RAPPORT_VISITES_GRAPH, Singleton.instance().getDocument());
}//GEN-LAST:event_jmiVisitesActionPerformed

    private void jmiDepensesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiDepensesActionPerformed
        displayWindow(CST_RAPPORT_FRAIS_GRAPH, Singleton.instance().getDocument());
}//GEN-LAST:event_jmiDepensesActionPerformed

    private void jmiVersementsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiVersementsActionPerformed
        displayWindow(CST_RAPPORT_VERSEMENTS_GRAPH, Singleton.instance().getDocument());
}//GEN-LAST:event_jmiVersementsActionPerformed

    private void jmiGAVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiGAVActionPerformed
        displayWindow(CST_CERTIF_GAV, Singleton.instance().getDocument().getUtilisateur());
    }//GEN-LAST:event_jmiGAVActionPerformed

    private void jmiHDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiHDTActionPerformed
        displayWindow(CST_CERTIF_HDT, Singleton.instance().getDocument().getUtilisateur());
    }//GEN-LAST:event_jmiHDTActionPerformed

    private void jmiMEBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiMEBActionPerformed
        displayWindow(CST_CERTIF_MEB, Singleton.instance().getDocument().getUtilisateur());
    }//GEN-LAST:event_jmiMEBActionPerformed

    private void jmiCEBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiCEBActionPerformed
        displayWindow(CST_CERTIF_CEB, Singleton.instance().getDocument().getUtilisateur());
}//GEN-LAST:event_jmiCEBActionPerformed

    private void jmiAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiAboutActionPerformed
        jdgAbout jdg = new jdgAbout(this, true);
        jdg.setVisible(true);
        jdg = null;
}//GEN-LAST:event_jmiAboutActionPerformed

    private void jxtpRapportsPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jxtpRapportsPropertyChange
        if (!jxtpRapports.isCollapsed()) {
            jxtpDétails.setCollapsed(true);
        }
}//GEN-LAST:event_jxtpRapportsPropertyChange

    private void jxtpDétailsPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jxtpDétailsPropertyChange
        if (!jxtpDétails.isCollapsed()) {
            jxtpRapports.setCollapsed(true);
        }
}//GEN-LAST:event_jxtpDétailsPropertyChange

    private void jmiRevisionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiRevisionsActionPerformed
        jdgRevisions jdg = new jdgRevisions(this, true);
        jdg.setVisible(true);
        jdg = null;
}//GEN-LAST:event_jmiRevisionsActionPerformed

    private void jbtAjouterCréneauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtAjouterCréneauActionPerformed
        jdgSaisieCreneau saisie = new jdgSaisieCreneau(MainWindow.getMainWindow(), true, Singleton.instance().getDocument());
        saisie.setVisible(true);
        saisie = null;
    }//GEN-LAST:event_jbtAjouterCréneauActionPerformed

    private void jbtAddDepenseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtAddDepenseActionPerformed
        jdgDepense jdg = new jdgDepense(MainWindow.getMainWindow(), true, Singleton.instance().getDocument());
        jdg.setVisible(true);
        jdg = null;
    }//GEN-LAST:event_jbtAddDepenseActionPerformed

    private void jbtAjouterReversementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtAjouterReversementActionPerformed
        jdgBilan jdg = new jdgBilan(MainWindow.getMainWindow(), true, Singleton.instance().getDocument());
        jdg.setVisible(true);
        jdg = null;
    }//GEN-LAST:event_jbtAjouterReversementActionPerformed

    private void jbtAddTransfertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtAddTransfertActionPerformed
        jdgTransfert transfert = new jdgTransfert(MainWindow.getMainWindow(), true, Singleton.instance().getDocument());
        transfert.setVisible(true);
        transfert = null;
}//GEN-LAST:event_jbtAddTransfertActionPerformed

    private void jmiFTPSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiFTPSaveActionPerformed


        FTPClient client = new FTPClient();
        try {
            XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
            sortie.output(XML.convertDocumentToXML(Singleton.instance().getDocument()), new FileOutputStream(_filename));

            client.connect(Singleton.instance().getDocument().getParametres().getFTPServer());
            /* connect & login to host */

            String password = JOptionPane.showInputDialog("Entrez le mot de passe pour le serveur FTP");
            client.login(Singleton.instance().getDocument().getParametres().getFTPId(), password);
            if (client.isConnected()) {
                File f = new File(_filename);
                SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd-HHmmss");

                client.changeDirectory(Singleton.instance().getDocument().getParametres().getFTPDirectory());
                client.upload(f);
                client.rename(f.getName(), f.getName() + "-" + format.format(new Date()));
                JOptionPane.showMessageDialog(this, f.getName() + " sauvegardé");
            } else {
                JOptionPane.showMessageDialog(this, "Erreur de connexion");
            }
            client.disconnect(true);
        } catch (FTPIllegalReplyException e) {
            JOptionPane.showMessageDialog(this, e.getLocalizedMessage());
        } catch (FTPException e) {
            JOptionPane.showMessageDialog(this, e.getLocalizedMessage());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, e.getLocalizedMessage());
        } catch (FTPDataTransferException e) {
            JOptionPane.showMessageDialog(this, e.getLocalizedMessage());
        } catch (FTPAbortedException e) {
            JOptionPane.showMessageDialog(this, e.getLocalizedMessage());
        }

    }//GEN-LAST:event_jmiFTPSaveActionPerformed

    private void jmiFTPLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiFTPLoadActionPerformed
        FTPClient client = new FTPClient();
        try {
            client.connect(Singleton.instance().getDocument().getParametres().getFTPServer());
            /* connect & login to host */

            String password = JOptionPane.showInputDialog("Entrez le mot de passe pour les serveur FTP");
            client.login(Singleton.instance().getDocument().getParametres().getFTPId(), password);
            if (client.isConnected()) {
                client.changeDirectory(Singleton.instance().getDocument().getParametres().getFTPDirectory());
                FTPFile[] list = client.list();
                /*String[] liste = new String[list.length];
                for (int i = 0; i < list.length; i++) {
                liste[i] = list[i].getName();
                }
                int option = JOptionPane.showOptionDialog(this, "Sélectionnez le fichier", "Charger par FTP", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, liste, liste[0]);
                 */
                jdgSelectionFichier jdg = new jdgSelectionFichier(this, true, list);
                jdg.setVisible(true);

                /*if (option >= 0) {
                FTPFile file = list[option];*/
                FTPFile file = jdg.getSelection();
                if (file != null) {
                    File f = new File(_filename);
                    SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd-HHmmss");
                    client.download(file.getName(), new File(f.getParentFile() + "/" + file.getName()));
                    loadFile(f.getParentFile() + "/" + file.getName());
                    _filename = f.getParentFile() + "/" + file.getName();
                    JOptionPane.showMessageDialog(this, file.getName() + " chargé");
                    repaint();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Erreur de connexion");
            }
            client.disconnect(true);
        } catch (FTPIllegalReplyException e) {
            JOptionPane.showMessageDialog(this, e.getLocalizedMessage());
        } catch (FTPException e) {
            JOptionPane.showMessageDialog(this, e.getLocalizedMessage());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, e.getLocalizedMessage());
        } catch (FTPDataTransferException e) {
            JOptionPane.showMessageDialog(this, e.getLocalizedMessage());
        } catch (FTPAbortedException e) {
            JOptionPane.showMessageDialog(this, e.getLocalizedMessage());
        } catch (FTPListParseException e) {
            JOptionPane.showMessageDialog(this, e.getLocalizedMessage());
        }
    }//GEN-LAST:event_jmiFTPLoadActionPerformed

    private void jmiFTPDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiFTPDeleteActionPerformed
        FTPClient client = new FTPClient();
        try {
            client.connect(Singleton.instance().getDocument().getParametres().getFTPServer());
            /* connect & login to host */

            String password = JOptionPane.showInputDialog("Entrez le mot de passe pour les serveur FTP");
            client.login(Singleton.instance().getDocument().getParametres().getFTPId(), password);
            if (client.isConnected()) {
                client.changeDirectory(Singleton.instance().getDocument().getParametres().getFTPDirectory());
                FTPFile[] list = client.list();
                /*String[] liste = new String[list.length];
                for (int i = 0; i < list.length; i++) {
                liste[i] = list[i].getName();
                }
                int option = JOptionPane.showOptionDialog(this, "Sélectionnez le fichier", "Charger par FTP", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, liste, liste[0]);
                 */
                jdgSelectionFichier jdg = new jdgSelectionFichier(this, true, list);
                jdg.setVisible(true);

                /*if (option >= 0) {
                FTPFile file = list[option];*/
                FTPFile file = jdg.getSelection();
                if (file != null) {
                    client.deleteFile(file.getName());
                    JOptionPane.showMessageDialog(this, file.getName() + " effacé");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Erreur de connexion");
            }
            client.disconnect(true);
        } catch (FTPIllegalReplyException e) {
            JOptionPane.showMessageDialog(this, e.getLocalizedMessage());
        } catch (FTPException e) {
            JOptionPane.showMessageDialog(this, e.getLocalizedMessage());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, e.getLocalizedMessage());
        } catch (FTPDataTransferException e) {
            JOptionPane.showMessageDialog(this, e.getLocalizedMessage());
        } catch (FTPAbortedException e) {
            JOptionPane.showMessageDialog(this, e.getLocalizedMessage());
        } catch (FTPListParseException e) {
            JOptionPane.showMessageDialog(this, e.getLocalizedMessage());
        }
    }//GEN-LAST:event_jmiFTPDeleteActionPerformed

    private void jmiImportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiImportActionPerformed
        JFileChooser chooser = new JFileChooser();
        chooser.setMultiSelectionEnabled(false);
        int filechoosed = chooser.showOpenDialog(this);
        if (filechoosed == JFileChooser.APPROVE_OPTION) {
            ImportFile(chooser.getSelectedFile().getAbsolutePath());
        }
        repaint();
    }//GEN-LAST:event_jmiImportActionPerformed
    static thSaveDocument save = null;

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {

                System.setProperty("javax.xml.soap.MessageFactory", "com.sun.xml.messaging.saaj.soap.ver1_1.SOAPMessageFactory1_1Impl");
                JFrame.setDefaultLookAndFeelDecorated(true);
                try {
                    org.jvnet.substance.SubstanceLookAndFeel lf = new org.jvnet.substance.skin.SubstanceMistSilverLookAndFeel();
                    UIManager.setLookAndFeel(lf);
                } catch (Exception e) {
                    System.out.println("Substance Creme Coffee failed to initialize");
                }

                singleton = new MainWindow();

                thCheckMemory check = new thCheckMemory();
                check.start();

                save = new thSaveDocument();
                save.start();

                singleton.setVisible(true);
            }
        });
    }
    static MainWindow singleton = null;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.JMenuBar jMenuBar1;
    javax.swing.JPanel jPanel1;
    javax.swing.JSeparator jSeparator1;
    javax.swing.JSeparator jSeparator10;
    javax.swing.JSeparator jSeparator2;
    javax.swing.JSeparator jSeparator3;
    javax.swing.JSeparator jSeparator4;
    javax.swing.JSeparator jSeparator5;
    javax.swing.JSeparator jSeparator6;
    javax.swing.JSeparator jSeparator7;
    javax.swing.JSeparator jSeparator8;
    javax.swing.JSeparator jSeparator9;
    org.jdesktop.swingx.JXTaskPaneContainer jXTaskPaneContainer1;
    javax.swing.JButton jbtAddDepense;
    javax.swing.JButton jbtAddTransfert;
    javax.swing.JButton jbtAjouterCréneau;
    javax.swing.JButton jbtAjouterReversement;
    javax.swing.JDesktopPane jdpMain;
    javax.swing.JMenuItem jmiAbout;
    javax.swing.JMenuItem jmiActivite;
    javax.swing.JMenuItem jmiCEB;
    javax.swing.JMenuItem jmiCascade;
    javax.swing.JMenuItem jmiCategories;
    javax.swing.JMenuItem jmiCreneau;
    javax.swing.JMenuItem jmiCreneaux;
    javax.swing.JMenuItem jmiDepenses;
    javax.swing.JMenuItem jmiEncaissement;
    javax.swing.JMenuItem jmiEnregistrer;
    javax.swing.JMenuItem jmiEnregistrerSous;
    javax.swing.JMenuItem jmiFTPDelete;
    javax.swing.JMenuItem jmiFTPLoad;
    javax.swing.JMenuItem jmiFTPSave;
    javax.swing.JMenuItem jmiFermerTout;
    javax.swing.JMenuItem jmiFinancier;
    javax.swing.JMenuItem jmiGAV;
    javax.swing.JMenuItem jmiHDT;
    javax.swing.JMenuItem jmiImpayes;
    javax.swing.JMenuItem jmiImport;
    javax.swing.JMenuItem jmiMEB;
    javax.swing.JMenuItem jmiMosaique;
    javax.swing.JMenuItem jmiNouveau;
    javax.swing.JMenuItem jmiOuvrir;
    javax.swing.JMenuItem jmiParametres;
    javax.swing.JMenuItem jmiQuitter;
    javax.swing.JMenuItem jmiRemplacantListe;
    javax.swing.JMenuItem jmiRevisions;
    javax.swing.JMenuItem jmiUtilisateur;
    javax.swing.JMenuItem jmiVersements;
    javax.swing.JMenuItem jmiVisites;
    javax.swing.JMenu jmnCertificats;
    javax.swing.JMenu jmnFenetres;
    javax.swing.JMenu jmnFichier;
    javax.swing.JMenu jmnGraphiques;
    javax.swing.JMenu jmnHelp;
    javax.swing.JMenu jmnOptions;
    javax.swing.JSeparator jsepFenetres;
    org.jdesktop.swingx.JXTaskPane jxtpDétails;
    org.jdesktop.swingx.JXTaskPane jxtpRapports;
    // End of variables declaration//GEN-END:variables

    public static MainWindow getMainWindow() {
        return singleton;
    }

    public void paint(Graphics g) {
        super.paint(g);

        System.gc();
        System.gc();

        boolean enabled = (Singleton.instance().getDocument() != null);
        setHMIEnabled(enabled);

        JInternalFrame frames[] = jdpMain.getAllFrames();
        jmnFenetres.removeAll();
        jmnFenetres.add(jmiCascade);
        jmnFenetres.add(jmiMosaique);
        jmnFenetres.add(jmiFermerTout);
        jmnFenetres.add(jsepFenetres);
        for (int i = 0; i < frames.length; i++) {
            String Name = ((JInternalFrame) frames[i]).getTitle();
            JMenuItem item = new JMenuItem(Name);
            item.addActionListener(new lactMenuFenetres(frames[i], jdpMain));
            item.setIcon(((JInternalFrame) frames[i]).getFrameIcon());
            jmnFenetres.add(item);
        }

        if (_filename != null) {
            if (!_filename.equals("")) {
                this.setTitle("Gestion des visites - " + _filename);
            }
        }
    }

    private void setHMIEnabled(boolean enabled) {
        jmnFenetres.setEnabled(enabled);
        jmnFichier.setEnabled(true);
        jmnOptions.setEnabled(enabled);

        jmiCascade.setEnabled(enabled);
        jmiMosaique.setEnabled(enabled);
        jmiEnregistrer.setEnabled(enabled);
        jmiEnregistrerSous.setEnabled(enabled);
        jmiFermerTout.setEnabled(enabled);
        jmiNouveau.setEnabled(true);
        jmiOuvrir.setEnabled(true);
        jmiParametres.setEnabled(enabled);
        jmiQuitter.setEnabled(true);
        jmiRemplacantListe.setEnabled(enabled);
        jmiUtilisateur.setEnabled(enabled);

        jxdpDateDebutRapport.setEnabled(enabled);
        jxdpDateFinRapport.setEnabled(enabled);
        jxdpDateDébutDétails.setEnabled(enabled);
        jxdpDateFinDétails.setEnabled(enabled);
        jbtVoirActivite.setEnabled(enabled);
        jmiActivite.setEnabled(enabled);
        jmiFinancier.setEnabled(enabled);
        jbtVoirFinancier.setEnabled(enabled);
        jbtVoirEncaissements.setEnabled(enabled);
        jmiEncaissement.setEnabled(enabled);
        jbtVoirImpayes.setEnabled(enabled);
        jmiImpayes.setEnabled(enabled);
        jmiActivite.setEnabled(enabled);
        jcbMedecins.setEnabled(enabled);
        jbtVoirFrais.setEnabled(enabled);
        jbtVoirRapportFrais.setEnabled(enabled);
        jmiDepenses.setEnabled(enabled);
        jbtEntrerCreneau.setEnabled(enabled);
        jbtTenuComptes.setEnabled(enabled);
        jbtTransfert.setEnabled(enabled);
        jbtSupprimerCreneau.setEnabled(enabled);
        jbtRealiserVersement.setEnabled(enabled);
        jbtDepense.setEnabled(enabled);
        jbtVoirRapportVersements.setEnabled(enabled);
        jmiVersements.setEnabled(enabled);
        jbtVoirRapportVersement.setEnabled(enabled);
        jbtVoirVersements.setEnabled(enabled);
        jbtVoirTousVersements.setEnabled(enabled);
        jbtVoirRapportVisites.setEnabled(enabled);
        jmiVisites.setEnabled(enabled);
        jbtVoirRapportPeriode.setEnabled(enabled);
        jbtVoirRapportCreneau.setEnabled(enabled);
        jmiCreneau.setEnabled(enabled);
        jbtVoirCreneauxPeriode.setEnabled(enabled);
        jmiCreneaux.setEnabled(enabled);
        jmiImpayes.setEnabled(enabled);
        jmiEncaissement.setEnabled(enabled);
        jmnGraphiques.setEnabled(enabled);
        jbtVoirVisites.setEnabled(enabled);

        jmnCertificats.setEnabled(enabled);
        jmiGAV.setEnabled(enabled);
        jmiHDT.setEnabled(enabled);
        jmiMEB.setEnabled(enabled);

        jbtAddDepense.setEnabled(enabled);
        jbtAddTransfert.setEnabled(enabled);
        jbtAjouterCréneau.setEnabled(enabled);
        jbtAjouterReversement.setEnabled(enabled);

        if (enabled) {
            int size = Singleton.instance().getDocument().getRemplacants().size();
            Document document = Singleton.instance().getDocument();
            jxdpDateDebutRapport.setDate(document.getDate());
            jxdpDateDébutDétails.setDate(document.getDate());
            jxdpDateFinRapport.setDate(new Date());
            jxdpDateFinDétails.setDate(new Date());
            String noms[] = new String[size + 2];
            noms[0] = "Tous les médecins";
            noms[1] = document.getUtilisateur().getNom() + ", " + document.getUtilisateur().getPrenom();
            for (int i = 0; i < size; i++) {
                Executant user = (Executant) document.getRemplacants().get(i);
                noms[i + 2] = user.getNom() + ", " + user.getPrenom() + " (Remplaçant)";
            }
            DefaultComboBoxModel model = new DefaultComboBoxModel(noms);
            jcbMedecins.setModel(model);
            jcbMedecins.setSelectedIndex(0);

            String nomsV[] = new String[size + 1];
            nomsV[0] = "Tous les médecins";
            for (int i = 0; i < size; i++) {
                Executant user = (Executant) document.getRemplacants().get(i);
                nomsV[i + 1] = user.getNom() + ", " + user.getPrenom() + " (Remplaçant)";
            }
            DefaultComboBoxModel modelV = new DefaultComboBoxModel(nomsV);

        }
    }
    public static final int CST_CRENEAU = 0;
    public static final int CST_RAPPORT_CRENEAUX = 1;
    public static final int CST_VISITES = 2;
    public static final int CST_FRAIS = 3;
    public static final int CST_VERSEMENTS = 4;
    public static final int CST_RAPPORT_CRENEAU = 5;
    public static final int CST_RAPPORT_VISITES = 6;
    public static final int CST_RAPPORT_VERSEMENTS = 7;
    public static final int CST_RAPPORT_VERSEMENT = 8;
    public static final int CST_VERSEMENT = 9;
    public static final int CST_RAPPORT_FRAIS = 10;
    public static final int CST_RAPPORT_FRAIS_GRAPH = 11;
    public static final int CST_RAPPORT_VISITES_GRAPH = 12;
    public static final int CST_RAPPORT_VERSEMENTS_GRAPH = 13;
    public static final int CST_RAPPORT_CRENEAU_GRAPH = 14;
    public static final int CST_RAPPORT_CRENEAUX_GRAPH = 15;
    public static final int CST_RAPPORT_ENCAISSEMENTS_GRAPH = 16;
    public static final int CST_RAPPORT_IMPAYES_GRAPH = 17;
    public static final int CST_RAPPORT_FINANCIER_GRAPH = 18;
    public static final int CST_RAPPORT_ACTIVITE_GRAPH = 19;
    public static final int CST_RAPPORT_ENCAISSEMENTS = 20;
    public static final int CST_RAPPORT_IMPAYES = 21;
    public static final int CST_RAPPORT_FINANCIER = 22;
    public static final int CST_RAPPORT_ACTIVITE = 23;
    public static final int CST_CERTIF_GAV = 24;
    public static final int CST_CERTIF_MEB = 25;
    public static final int CST_CERTIF_HDT = 26;
    public static final int CST_CERTIF_CEB = 27;
    public static final int CST_TENU_COMPTES = 28;

    public void displayWindow(int windowType, Object obj) {
        /* Cherche si la fenêtre existe déjà */
        boolean found = false;
        JInternalFrame jif = null;
        SimpleDateFormat f = new SimpleDateFormat("EEEEEEEEE dd MMMMMMMMMMM yyyy");
        for (int i = 0; i < jdpMain.getAllFrames().length; i++) {
            String title = "";
            switch (windowType) {
                case CST_CRENEAU:
                    Creneau cren = (Creneau) obj;
                    title = cren.getName();
                    break;
                case CST_VISITES:
                    title = "Liste des visites du " + f.format(jxdpDateDébutDétails.getDate()) + " au " + f.format(jxdpDateFinDétails.getDate());
                    break;
                case CST_FRAIS:
                    title = "Liste des dépenses du " + f.format(jxdpDateDébutDétails.getDate()) + " au " + f.format(jxdpDateFinDétails.getDate());
                    break;
                case CST_VERSEMENTS:
                    title = "Liste des versements du " + f.format(jxdpDateDébutDétails.getDate()) + " au " + f.format(jxdpDateFinDétails.getDate());
                    break;
                case CST_RAPPORT_CRENEAUX:
                    title = "Créneaux du " + f.format(jxdpDateDebutRapport.getDate()) + " au " + f.format(jxdpDateFinRapport.getDate());
                    break;
                case CST_RAPPORT_CRENEAUX_GRAPH:
                    title = "Graphiques des créneaux du du " + f.format(((Document) obj).getDate()) + " au " + f.format(new Date());
                    break;
                case CST_RAPPORT_CRENEAU:
                    title = "Créneau du " + f.format(((Creneau) obj).getDate()) + " par " + ((Creneau) obj).getExecutant().getNom() + " " + ((Creneau) obj).getExecutant().getPrenom();
                    break;
                case CST_RAPPORT_CRENEAU_GRAPH:
                    title = "Graphiques du créneau du " + f.format(((Creneau) obj).getDate()) + " par " + ((Creneau) obj).getExecutant().getNom() + " " + ((Creneau) obj).getExecutant().getPrenom();
                    break;
                case CST_VERSEMENT:
                    title = "Versement du " + f.format(((Versement) obj).getDate()) + " à " + ((Versement) obj).getExecutant().getNom() + " " + ((Versement) obj).getExecutant().getPrenom();
                    break;
                case CST_RAPPORT_VISITES:
                    title = "Visites du " + f.format(jxdpDateDebutRapport.getDate()) + " au " + f.format(jxdpDateFinRapport.getDate());
                    break;
                case CST_RAPPORT_VISITES_GRAPH:
                    title = "Graphiques des visites du " + f.format(((Document) obj).getDate()) + " au " + f.format(new Date());
                    break;
                case CST_RAPPORT_VERSEMENT:
                    title = "Rapport du versement du " + f.format(((Versement) obj).getDate()) + " à " + ((Versement) obj).getExecutant().getNom() + " " + ((Versement) obj).getExecutant().getPrenom();
                    break;
                case CST_RAPPORT_VERSEMENTS:
                    title = "Rapport des versements du " + f.format(jxdpDateDebutRapport.getDate()) + " au " + f.format(jxdpDateFinRapport.getDate());
                    break;
                case CST_RAPPORT_FRAIS:
                    title = "Rapport des dépenses du " + f.format(jxdpDateDebutRapport.getDate()) + " au " + f.format(jxdpDateFinRapport.getDate());
                    break;
                case CST_RAPPORT_FRAIS_GRAPH:
                    title = "Graphique des dépenses du " + f.format(((Document) obj).getDate()) + " au " + f.format(new Date());
                    break;
                case CST_RAPPORT_VERSEMENTS_GRAPH:
                    title = "Graphique des versements du " + f.format(((Document) obj).getDate()) + " au " + f.format(new Date());
                    break;
                case CST_RAPPORT_ENCAISSEMENTS_GRAPH:
                    title = "Graphique des encaissements du " + f.format(((Document) obj).getDate()) + " au " + f.format(new Date());
                    break;
                case CST_RAPPORT_IMPAYES_GRAPH:
                    title = "Graphique des impayés du " + f.format(((Document) obj).getDate()) + " au " + f.format(new Date());
                    break;
                case CST_RAPPORT_FINANCIER_GRAPH:
                    title = "Graphique financier du " + f.format(((Document) obj).getDate()) + " au " + f.format(new Date());
                    break;
                case CST_RAPPORT_ACTIVITE_GRAPH:
                    title = "Graphique d'activités du " + f.format(((Document) obj).getDate()) + " au " + f.format(new Date());
                    break;
                case CST_RAPPORT_ENCAISSEMENTS:
                    title = "Rapport des encaissements du " + f.format(jxdpDateDébutDétails.getDate()) + " au " + f.format(jxdpDateFinDétails.getDate());
                    break;
                case CST_RAPPORT_IMPAYES:
                    title = "Rapport des impayés du " + f.format(jxdpDateDébutDétails.getDate()) + " au " + f.format(jxdpDateFinDétails.getDate());
                    break;
                case CST_RAPPORT_FINANCIER:
                    title = "Rapport financier du " + f.format(jxdpDateDebutRapport.getDate()) + " au " + f.format(jxdpDateFinRapport.getDate());
                    break;
                case CST_RAPPORT_ACTIVITE:
                    title = "Rapport d'activités du " + f.format(jxdpDateDebutRapport.getDate()) + " au " + f.format(jxdpDateFinRapport.getDate());
                    break;
                case CST_CERTIF_GAV:
                    title = "Certificat de garde à vue";
                    break;
                case CST_CERTIF_MEB:
                    title = "Certificat de mise en bière";
                    break;
                case CST_CERTIF_HDT:
                    title = "Certificat d'hospitalisation à la demande d'un tiers";
                    break;
                case CST_CERTIF_CEB:
                    title = "Certificat de coups et blessures";
                    break;
                case CST_TENU_COMPTES:
                    title = "Tenue des compte du " + f.format(jxdpDateDébutDétails.getDate()) + " au " + f.format(jxdpDateFinDétails.getDate());
                    break;
            }
            if (jdpMain.getAllFrames()[i].getTitle().equals(title)) {
                found = true;
                jif = jdpMain.getAllFrames()[i];
            }
        }

        if (!found) {
            switch (windowType) {
                case CST_CRENEAU:
                    jif = new jifCreneau((Creneau) obj);
                    break;
                case CST_VISITES:
                    jif = new jifVisites((Document) obj, jxdpDateDébutDétails.getDate(), jxdpDateFinDétails.getDate());
                    break;
                case CST_FRAIS:
                    jif = new jifFrais((Document) obj, jxdpDateDébutDétails.getDate(), jxdpDateFinDétails.getDate());
                    break;
                case CST_VERSEMENTS:
                    jif = new jifVersements((Document) obj, jxdpDateDébutDétails.getDate(), jxdpDateFinDétails.getDate());
                    break;
                case CST_RAPPORT_CRENEAUX:
                    jif = new jifReportCreneauxPeriod((Document) obj, jxdpDateDebutRapport.getDate(), jxdpDateFinRapport.getDate(), jcbMedecins.getSelectedIndex());
                    break;
                case CST_RAPPORT_CRENEAUX_GRAPH:
                    jif = new jifGraphCreneaux((Document) obj, ((Document) obj).getDate(), new Date());
                    break;
                case CST_RAPPORT_CRENEAU:
                    jif = new jifReportCreneau(Singleton.instance().getDocument(), (Creneau) obj);
                    break;
                case CST_RAPPORT_CRENEAU_GRAPH:
                    jif = new jifGraphCreneau((Creneau) obj);
                    break;
                case CST_VERSEMENT:
                    jif = new jifVersement(Singleton.instance().getDocument(), (Versement) obj);
                    break;
                case CST_RAPPORT_VISITES:
                    jif = new jifReportVisites((Document) obj, jxdpDateDebutRapport.getDate(), jxdpDateFinRapport.getDate());
                    break;
                case CST_RAPPORT_VISITES_GRAPH:
                    jif = new jifGraphVisites((Document) obj, ((Document) obj).getDate(), new Date());
                    break;
                case CST_RAPPORT_VERSEMENT:
                    jif = new jifReportVersement(Singleton.instance().getDocument(), (Versement) obj);
                    break;
                case CST_RAPPORT_VERSEMENTS:
                    jif = new jifReportVersements((Document) obj, jxdpDateDebutRapport.getDate(), jxdpDateFinRapport.getDate());
                    break;
                case CST_RAPPORT_VERSEMENTS_GRAPH:
                    jif = new jifGraphVersements((Document) obj, ((Document) obj).getDate(), new Date());
                    break;
                case CST_RAPPORT_FRAIS:
                    jif = new jifReportFrais((Document) obj, jxdpDateDebutRapport.getDate(), jxdpDateFinRapport.getDate());
                    break;
                case CST_RAPPORT_FRAIS_GRAPH:
                    jif = new jifGraphFrais((Document) obj, ((Document) obj).getDate(), new Date());
                    break;
                case CST_RAPPORT_ENCAISSEMENTS_GRAPH:
                    jif = new jifGraphEncaissements((Document) obj, ((Document) obj).getDate(), new Date());
                    break;
                case CST_RAPPORT_IMPAYES_GRAPH:
                    jif = new jifGraphImpayes((Document) obj, ((Document) obj).getDate(), new Date());
                    break;
                case CST_RAPPORT_FINANCIER_GRAPH:
                    jif = new jifGraphFinancier((Document) obj, ((Document) obj).getDate(), new Date());
                    break;
                case CST_RAPPORT_ACTIVITE_GRAPH:
                    jif = new jifGraphActivite((Document) obj, ((Document) obj).getDate(), new Date());
                    break;
                case CST_RAPPORT_ENCAISSEMENTS:
                    jif = new jifReportEncaissements((Document) obj, jxdpDateDébutDétails.getDate(), jxdpDateFinDétails.getDate());
                    break;
                case CST_RAPPORT_IMPAYES:
                    jif = new jifReportImpayes((Document) obj, jxdpDateDébutDétails.getDate(), jxdpDateFinDétails.getDate());
                    break;
                case CST_RAPPORT_FINANCIER:
                    jif = new jifReportFinancier((Document) obj, jxdpDateDebutRapport.getDate(), jxdpDateFinRapport.getDate());
                    break;
                case CST_RAPPORT_ACTIVITE:
                    jif = new jifReportActivite((Document) obj, jxdpDateDebutRapport.getDate(), jxdpDateFinRapport.getDate());
                    break;
                case CST_CERTIF_GAV:
                    jif = new jifCertif((Utilisateur) obj, jifCertif.CERTIF_GARDE_A_VUE);
                    break;
                case CST_CERTIF_MEB:
                    jif = new jifCertif((Utilisateur) obj, jifCertif.CERTIF_MISE_EN_BIERE);
                    break;
                case CST_CERTIF_HDT:
                    jif = new jifCertif((Utilisateur) obj, jifCertif.CERTIF_HDT);
                    break;
                case CST_CERTIF_CEB:
                    jif = new jifCertif((Utilisateur) obj, jifCertif.CERTIF_COUPS_ET_BLESSURES);
                    break;
                case CST_TENU_COMPTES:
                    jif = new jifComptes((Document) obj, jxdpDateDébutDétails.getDate(), jxdpDateFinDétails.getDate());
                    break;
            }
            if (jif != null) {
                jdpMain.add(jif);
                jif.setVisible(true);
                try {
                    jif.setSelected(true);
                } catch (PropertyVetoException e) {
                    JOptionPane.showMessageDialog(this, e.getMessage());
                }
                repaint();
            }
        }
        if (jif != null) {
            jdpMain.setSelectedFrame(jif);
        }
    }

    public void closeWindow(JInternalFrame window) {
        jdpMain.remove(window);
        window = null;
        repaint();
    }
}
