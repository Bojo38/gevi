/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gevi.Views;

import gevi.Model.Singleton;
import gevi.Settings;
import gevi.Model.XML;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.jdom.output.Format;
import javax.swing.JOptionPane;
import org.jdom.output.XMLOutputter;

/**
 *
 * @author root.106572700130
 */
public class thSaveDocument extends Thread {

    boolean running = true;

    public void setStop() {
        running = false;
    }

    public void run() {

        int savePeriod = Singleton.instance().getDocument().getParametres().getSavePeriod();
        String filename = Settings.getSingleton().getProperty("LastFile");

        if (savePeriod != 0) {
            // Tant que la mémoire libre est supérieure à 10%
            while (running) {
                try {
                    // pause de 1s
                    Thread.sleep(savePeriod);

                    if (running) {
                        try {
                            if (!filename.equals("")) {
                                XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
                                sortie.output(XML.convertDocumentToXML(Singleton.instance().getDocument()), new FileOutputStream(filename));
                            }
                        } catch (FileNotFoundException e) {
                            JOptionPane.showMessageDialog(MainWindow.getMainWindow(), e.getLocalizedMessage());
                            e.printStackTrace();
                        } catch (IOException e) {
                            JOptionPane.showMessageDialog(MainWindow.getMainWindow(), e.getLocalizedMessage());
                            e.printStackTrace();
                        }
                    }
                } catch (InterruptedException ex) {
                }
            }
        }
    }
}
