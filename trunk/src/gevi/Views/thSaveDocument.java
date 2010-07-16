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
import java.text.SimpleDateFormat;
import java.util.Date;
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

        int savePeriod = 30000;
        try
        {
            savePeriod=Singleton.instance().getDocument().getParametres().getSavePeriod();
        }
        catch (NullPointerException e)
        {
            
        }
        String filename;

        if (savePeriod != 0) {
            int index=1;
            // Tant que la mémoire libre est supérieure à 10%
            while (running) {
                try {
                    // pause de 1s
                    Thread.sleep(savePeriod);
                    if (running) {
                        filename = MainWindow.getMainWindow()._filename;
                        try {
                            if (!filename.equals("")) {
                                XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
                                //SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");
                                index++;
                                if (index==10)
                                {
                                    index=0;
                                }
                                sortie.output(XML.convertDocumentToXML(Singleton.instance().getDocument()), new FileOutputStream(filename+"-"+Integer.toString(index)));
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
