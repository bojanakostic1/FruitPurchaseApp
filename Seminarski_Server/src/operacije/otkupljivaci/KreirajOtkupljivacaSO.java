/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.otkupljivaci;

import domen.Otkupljivac;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Bojana
 */
public class KreirajOtkupljivacaSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if (objekat == null || !(objekat instanceof Otkupljivac)) {
            throw new Exception("Sistem ne može da zapamti otkupljivača.");
        }

        Otkupljivac otkupljivac = (Otkupljivac) objekat;

        if (otkupljivac.getIme() == null || otkupljivac.getIme().isEmpty()) {
            throw new Exception("Greška. Ime nije ispravno uneto.");
        }
        if (otkupljivac.getPrezime() == null || otkupljivac.getPrezime().isEmpty()) {
            throw new Exception("Greška. Prezime nije ispravno uneto.");
        }

        if (otkupljivac.getKorisnickoIme() == null || otkupljivac.getKorisnickoIme().isEmpty()) {
            throw new Exception("Greška. Korisničko ime nije ispravno uneto.");
        }

        if (otkupljivac.getSifra() == null || otkupljivac.getSifra().isEmpty()) {
            throw new Exception("Greška. Šifra nije ispravno uneta.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        broker.add((Otkupljivac) objekat);
    }

}
