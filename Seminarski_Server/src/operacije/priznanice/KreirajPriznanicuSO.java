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
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        Priznanica priznanica = (Priznanica) objekat;
        broker.add(priznanica);
        for (StavkaPriznanice stavka : priznanica.getStavkePriznanice()) {
            stavka.setPriznanica(priznanica);
            broker.add(stavka);
        }
    }
}
