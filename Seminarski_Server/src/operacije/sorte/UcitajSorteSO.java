/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.sorte;

import domen.Sorta;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Bojana
 */
public class UcitajSorteSO extends ApstraktnaGenerickaOperacija{
    private List<Sorta> listaSorti;

    public List<Sorta> getListaSorti() {
        return listaSorti;
    }
    
    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if(objekat == null || !(objekat instanceof Sorta)){
            throw new Exception("Sistem ne može da nađe sortu.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat) throws Exception {
        listaSorti = broker.getAll(new Sorta(), null);
    }
    
}
