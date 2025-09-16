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
public class PromeniOtkupljivacaSO extends ApstraktnaGenerickaOperacija {

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

        if (otkupljivac.getSifra() == null || otkupljivac.getSifra().isEmpty() || otkupljivac.getSifra().length()<8) {
            throw new Exception("Greška. Šifra treba da sadrži najmanje 8 karaktera.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat) throws Exception {
        broker.edit((Otkupljivac) objekat);
    }

}
