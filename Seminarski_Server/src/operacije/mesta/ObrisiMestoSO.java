/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.mesta;

import domen.Mesto;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Bojana
 */
public class ObrisiMestoSO extends ApstraktnaGenerickaOperacija{

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if(objekat == null || !(objekat instanceof Mesto)){
            throw new Exception("Sistem ne može da nađe mesto.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        broker.delete((Mesto)objekat);
    }
    
}
