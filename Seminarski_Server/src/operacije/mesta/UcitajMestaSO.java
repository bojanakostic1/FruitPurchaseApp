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
public class UcitajMestaSO extends ApstraktnaGenerickaOperacija{
    private List<Mesto> svaMesta;

    public List<Mesto> getSvaMesta() {
        return svaMesta;
    }

    @Override
    protected void preduslovi(Object objekat) throws Exception {
      if (objekat == null || !(objekat instanceof Mesto)) {
            throw new Exception("Sistem ne može da nađe mesto.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat) throws Exception {
        svaMesta = broker.getAll(new Mesto(), null);
        System.out.println("UcitajMestaSO: "+svaMesta);
    }
    
    
    
}
