/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.priznanice;

import domen.Priznanica;
import domen.StavkaPriznanice;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Bojana
 */
public class UcitajPriznaniceSO extends ApstraktnaGenerickaOperacija{
    private List<Priznanica> svePriznanice;

    public List<Priznanica> getSvePriznanice() {
        return svePriznanice;
    }
    
    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if (objekat == null || !(objekat instanceof Priznanica)) {
            throw new Exception("Sistem ne može da nađe priznanicu.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat) throws Exception {
        String uslov =  " JOIN proizvodjac ON priznanica.proizvodjac=proizvodjac.idProizvodjac "
                + " JOIN otkupljivac ON priznanica.otkupljivac=otkupljivac.idOtkupljivac ";
              
        svePriznanice = broker.getAll(new Priznanica(), uslov);
        for (Priznanica priznanica : svePriznanice) {
            String uslov1 =  " JOIN sorta ON stavka_priznanice.sorta=sorta.idSorta WHERE stavka_priznanice.priznanica="+priznanica.getIdPriznanica();
            List<StavkaPriznanice> sveStavke = broker.getAll(new StavkaPriznanice(),uslov1);
            priznanica.setStavkePriznanice(sveStavke);
        }
    }
    
}
