/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.sorte;

import domen.Sorta;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Bojana
 */
public class PromeniSortuSO extends ApstraktnaGenerickaOperacija{

    @Override
    protected void preduslovi(Object objekat) throws Exception {
         if (objekat == null || !(objekat instanceof Sorta)) {
            throw new Exception("Sistem ne može da zapamti sortu.");
        }

        Sorta sorta = (Sorta) objekat;

        if (sorta.getNaziv()== null || sorta.getNaziv().isEmpty()) {
            throw new Exception("Greška. Naziv sorte nije ispravno unet.");
        } 
        if(sorta.getKategorija()<1 || sorta.getKategorija()>3){
             throw new Exception("Greška. Kategorija nije ispravno uneta. Sorta voća može biti 1, 2. ili 3. kategorije.");
        }
        if(sorta.getCena()<=0){
            throw new Exception("Greška. Cena mora biti veća od nule.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        broker.edit(objekat);
    }
    
}
