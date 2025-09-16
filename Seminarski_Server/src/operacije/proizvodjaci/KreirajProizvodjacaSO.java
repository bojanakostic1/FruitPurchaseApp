/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.proizvodjaci;

import domen.Proizvodjac;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Bojana
 */
public class KreirajProizvodjacaSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if (objekat == null || !(objekat instanceof Proizvodjac)) {
            throw new Exception("Sistem ne može da zapamti proizvođača.");
        }

        Proizvodjac p = (Proizvodjac) objekat;

        if (p.getIme() == null || p.getIme().isEmpty()) {
            throw new Exception("Greška. Ime nije ispravno uneto.");
        }
        if (p.getPrezime()== null || p.getPrezime().isEmpty()) {
            throw new Exception("Greška. Prezime nije ispravno uneto.");
        }

        if (p.getBrojTelefona()== null || p.getBrojTelefona().isEmpty()) {
            throw new Exception("Greška. Broj telefona nije ispravno unet.");
        }
        if (p.getMesto()== null) {
            throw new Exception("Greška. Niste izabrali mesto.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat) throws Exception {
        broker.add((Proizvodjac) objekat);
    }

}
