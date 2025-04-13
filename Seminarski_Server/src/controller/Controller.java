/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domen.Otkupljivac;
import operacije.login.PrijaviSO;

/**
 *
 * @author Bojana
 */
public class Controller {

    private static Controller instance;

    private Controller() {
    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    public Otkupljivac login(Otkupljivac o) throws Exception {
        PrijaviSO prijaviSO = new PrijaviSO();
        prijaviSO.izvrsi(o, null);
        System.out.println("Klasa Controller: "+prijaviSO.getOtkupljivac());
        return prijaviSO.getOtkupljivac();
    }

    

}
