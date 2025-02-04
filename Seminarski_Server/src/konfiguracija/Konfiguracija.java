/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package konfiguracija;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bojana
 */
public class Konfiguracija {
    public static Konfiguracija instance;
    private Properties properties;
    
    private Konfiguracija() {
        properties = new Properties();
        try( FileInputStream fis = new FileInputStream("C:\\Users\\Bojana\\Desktop\\Bojana FON\\SEDMI SEMESTAR\\PROJ_SOFT\\Seminarski\\Seminarski_Server\\config\\config.properties")) {
            properties.load(fis);
        }catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static Konfiguracija getInstance() {
        if (instance == null) {
            instance = new Konfiguracija();
        }
        return instance;
    }

    public Konfiguracija(Properties properties) {
        this.properties = properties;
    }

   public String getProperty(String key){
       return properties.getProperty(key, "N/A");
   }
   
    public void setProperty(String key, String value){
       properties.setProperty(key, value);
    }
    
    
    public void sacuvajIzmene(){
        try(FileOutputStream fos = new FileOutputStream("C:\\Users\\Bojana\\Desktop\\Bojana FON\\SEDMI SEMESTAR\\PROJ_SOFT\\Seminarski\\Seminarski_Server\\config\\config.properties")){
            properties.store(fos, null);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
