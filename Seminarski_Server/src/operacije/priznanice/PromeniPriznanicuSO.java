/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.priznanice;

import domen.Priznanica;
import domen.StavkaPriznanice;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Bojana
 */
public class PromeniPriznanicuSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if (objekat == null || !(objekat instanceof Priznanica)) {
            throw new Exception("Sistem ne može da zapamti priznanicu.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat) throws Exception {
        Priznanica priznanica = (Priznanica) objekat;
        broker.edit(priznanica);
        String uslov = " JOIN sorta ON stavka_priznanice.sorta=sorta.idSorta WHERE priznanica = " + priznanica.getIdPriznanica();
        List<StavkaPriznanice> stavkeZaBrisanje = broker.getAll(new StavkaPriznanice(), uslov);
        for (StavkaPriznanice s : stavkeZaBrisanje) {
            broker.delete(s);
        }

        for (StavkaPriznanice stavka : priznanica.getStavkePriznanice()) {
            stavka.setPriznanica(priznanica);
            broker.add(stavka);
        }
    }
}
