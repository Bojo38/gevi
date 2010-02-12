/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gevi.Views;

import javax.swing.JOptionPane;

/**
 *
 * @author root.106572700130
 */
public class thCheckMemory extends Thread {

    public void run() {

        long heapFreeSize = Runtime.getRuntime().freeMemory();
        long heapMaxSize=Runtime.getRuntime().maxMemory();
        boolean running=true;
        // Tant que la mémoire libre est supérieure à 10%
        while (running) {
            try {
                // pause de 1s
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
            }

            long min=heapFreeSize/(1024*1024);
            long max=heapMaxSize/(1024*1024);
            if (heapFreeSize < (heapMaxSize/20))
            {
                // lance le collecteur de déchets
                Runtime.getRuntime().gc();
            }
            heapFreeSize = Runtime.getRuntime().freeMemory();
            if (heapFreeSize < heapMaxSize/20)
            {
                //JOptionPane.showMessageDialog(null,"Le logiciel a détécté qu'il n'y avait plus assez de mémoire.\nVeuillez enregistrer votre travail et quitter l'application.","Plus de mémoire",JOptionPane.WARNING_MESSAGE);
                running=false;
            }
        }
    }
}
