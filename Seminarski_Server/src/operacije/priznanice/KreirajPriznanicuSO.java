/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.priznanice;

import domen.Priznanica;
import domen.StavkaPriznanice;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Bojana
 */
public class KreirajPriznanicuSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if (objekat == null || !(objekat instanceof Priznanica)) {
            throw new Exception("Sistem ne može da zapamti priznanicu.");
        }
          Priznanica p = (Priznanica) objekat;

        if (p.getDatumIzdavanja() == null) {
            throw new Exception("Greška. Datum nije ispravno unet.");
        }
        if (p.getOtkupljivac()== null) {
            throw new Exception("Greška. Otkupljivač nije odabran.");
        }

        if (p.getProizvodjac()== null) {
            throw new Exception("Greška. Proizvođač nije odabran.");
        }
        if (p.getUkupnaVrednost()<= 0) {
            throw new Exception("Greška. Ukupna vrednost mora biti veća od 0. Dodajte stavke.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat) throws Exception {
        Priznanica priznanica = (Priznanica) objekat;
        broker.add(priznanica);
        for (StavkaPriznanice stavka : priznanica.getStavkePriznanice()) {
            stavka.setPriznanica(priznanica);
            broker.add(stavka);
        }
    }
}
