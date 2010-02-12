/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gevi.Views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

/**
 *
 * @author root
 */
public class lactMenuFenetres implements ActionListener{

    JInternalFrame _jif;
    JDesktopPane _jdp;

    public lactMenuFenetres(JInternalFrame jif,JDesktopPane jdp)
    {
        _jif=jif;
        _jdp=jdp;
    }

    public void actionPerformed(ActionEvent evt)
    {
        try
        {
             _jif.setIcon(false);
             _jif.setSelected(true);
        }
        catch (PropertyVetoException e)
        {

        }
    }

}
