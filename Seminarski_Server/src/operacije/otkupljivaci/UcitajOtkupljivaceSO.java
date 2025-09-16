/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.otkupljivaci;

import domen.Otkupljivac;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Bojana
 */
public class UcitajOtkupljivaceSO extends ApstraktnaGenerickaOperacija {

    List<Otkupljivac> lista;
    
    public List<Otkupljivac> getLista() {
        return lista;
    }

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if(objekat == null || !(objekat instanceof Otkupljivac)){
            throw new Exception("Sistem ne može da nađe otkupljivača.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat) throws Exception {
        lista = broker.getAll(new Otkupljivac(), null);
    }

}
