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
public class KreirajSortuSO extends ApstraktnaGenerickaOperacija{

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if(objekat == null || !(objekat instanceof Sorta)){
            throw new Exception("Sistem ne može da zapamti sortu.");
        }
        
        Sorta s = (Sorta) objekat;
        if(s.getNaziv() == null || s.getNaziv().isEmpty()){
             throw new Exception("Greška. Naziv nije ispravno unet.");
        }
        if(s.getKategorija()<1 || s.getKategorija()>3){
             throw new Exception("Greška. Kategorija nije ispravno uneta. Sorta voća može biti 1, 2. ili 3. kategorije.");
        }
        if(s.getCena()<=0){
             throw new Exception("Greška. Cena mora biti veća od nule.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat) throws Exception {
        broker.add((Sorta)objekat);
    }
    
}
