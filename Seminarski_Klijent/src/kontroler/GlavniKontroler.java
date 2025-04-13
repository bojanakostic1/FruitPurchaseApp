/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroler;

import controller.LoginController;
import forme.LoginForma;

/**
 *
 * @author Bojana
 */
public class GlavniKontroler {
    private static GlavniKontroler instance;
    private LoginController loginController;
    
    private GlavniKontroler() {
    }

    public static GlavniKontroler getInstance() {
        if(instance == null){
            instance = new GlavniKontroler();
        }
        return instance;
    }

    public void otvoriLoginFormu() {
        loginController = new LoginController(new LoginForma());
        loginController.otvoriFormu();
    }
}
