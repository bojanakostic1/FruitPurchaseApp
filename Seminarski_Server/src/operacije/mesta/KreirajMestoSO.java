/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.mesta;

import domen.Mesto;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Bojana
 */
public class KreirajMestoSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if (objekat == null || !(objekat instanceof Mesto)) {
            throw new Exception("Sistem ne može da zapamti mesto.");
        }

        Mesto m = (Mesto) objekat;

        if (m.getNaziv() == null || m.getNaziv().isEmpty()) {
            throw new Exception("Greška. Naziv mesta nije ispravno unet.");
        }
        // Provera da li već postoji mesto sa tim nazivom
        List<Mesto> listaMesta = broker.getAll(objekat, " WHERE naziv='"+m.getNaziv()+"'");
    if (listaMesta.contains(objekat) ) {
        throw new Exception("Greška. Mesto sa tim nazivom već postoji u bazi.");
    }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        broker.add((Mesto)objekat);
    }

}
