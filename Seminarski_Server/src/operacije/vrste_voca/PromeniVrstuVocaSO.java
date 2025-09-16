/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.vrste_voca;

import domen.VrstaVoca;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Bojana
 */
public class PromeniVrstuVocaSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if (objekat == null || !(objekat instanceof VrstaVoca)) {
            throw new Exception("Sistem ne može da zapamti vrstu voća.");
        }

        VrstaVoca vrsta = (VrstaVoca) objekat;

        if (vrsta.getNaziv() == null || vrsta.getNaziv().isEmpty()) {
            throw new Exception("Greška. Naziv vrste voća nije ispravno unet.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat) throws Exception {
        broker.edit((VrstaVoca)objekat);
    }

}
