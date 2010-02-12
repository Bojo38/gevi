/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gevi;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

/**
 *
 * @author root.106572700130
 */
public class Settings {
private static Settings _singleton = null;
    private Properties _data;

    private Settings() {
        _data = new Properties();
        try {
            String userDir = System.getProperty("user.dir");
            _data.load(new FileInputStream(userDir + "/Gevi_params.prm"));
            return;
        }
        catch (Exception e) {

        }
    }

    public static Settings getSingleton() {
        if (_singleton == null) {
            _singleton = new Settings();
        }
        return _singleton;
    }

    public void setProperty(String key, String value) {
        _data.setProperty(key, value);
    }

    public String getProperty(String key) {
        return _data.getProperty(key);
    }

    public boolean saveProperties() {
        // Create temp file.
        try {
            String userDir = System.getProperty("user.dir");
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            _data.store(new FileOutputStream(userDir + "/Gevi_params.prm"), sdf.format(cal.getTime()));

            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
