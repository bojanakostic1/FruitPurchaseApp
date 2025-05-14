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
public class ObrisiProizvodjacaSO extends ApstraktnaGenerickaOperacija{

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if(objekat == null || !(objekat instanceof Proizvodjac)){
            throw  new Exception("Sistem ne može da nađe proizvođača.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        broker.delete((Proizvodjac)objekat);
    }
}
