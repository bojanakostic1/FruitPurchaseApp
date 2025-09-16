/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.proizvodjaci;

import domen.Proizvodjac;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Bojana
 */
public class UcitajProizvodjaceSO extends ApstraktnaGenerickaOperacija{
    private List<Proizvodjac> sviProizvodjaci;

    public List<Proizvodjac> getSviProizvodjaci() {
        return sviProizvodjaci;
    }
    
    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if (objekat == null || !(objekat instanceof Proizvodjac)) {
            throw new Exception("Sistem ne može da nađe proizvođača.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat) throws Exception {
        String uslov = " JOIN mesto ON mesto.idMesto=proizvodjac.mesto";
        sviProizvodjaci = broker.getAll(new Proizvodjac(), uslov);
        System.out.println("UcitajProizvodjaceSO:"+sviProizvodjaci);
    }
    
}
