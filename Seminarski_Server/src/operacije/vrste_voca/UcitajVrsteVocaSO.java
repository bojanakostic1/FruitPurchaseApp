/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.vrste_voca;

import domen.VrstaVoca;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Bojana
 */
public class UcitajVrsteVocaSO extends ApstraktnaGenerickaOperacija{
    List<VrstaVoca> lista;

    public List<VrstaVoca> getLista() {
        return lista;
    }
    
    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if (objekat == null || !(objekat instanceof VrstaVoca)) {
            throw new Exception("Sistem ne može da nađe vrstu voća.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat) throws Exception {
        lista = broker.getAll(new VrstaVoca(), null);
    }
    
}
