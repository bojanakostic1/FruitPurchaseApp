package operacije.login;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import domen.Otkupljivac;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Bojana
 */
public class PrijaviSO extends ApstraktnaGenerickaOperacija {

    private Otkupljivac otkupljivac;

    public Otkupljivac getOtkupljivac() {
        return otkupljivac;
    }

    public void setOtkupljivac(Otkupljivac Otkupljivac) {
        this.otkupljivac = Otkupljivac;
    }

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        List<Otkupljivac> sviOtkupljivaci = broker.getAll(objekat, null);
        System.out.println("Klasa PrijaviSO " + sviOtkupljivaci);

        if (sviOtkupljivaci.contains(objekat)) {
            for (Otkupljivac o : sviOtkupljivaci) {
                if (o.equals(objekat)) {
                    otkupljivac = o;
                    return;
                }
            }
        } else {
            otkupljivac = null;
        }
    }

}
