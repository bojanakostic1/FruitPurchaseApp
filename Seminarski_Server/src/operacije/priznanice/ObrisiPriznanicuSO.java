/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.priznanice;

import domen.Priznanica;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Bojana
 */
public class ObrisiPriznanicuSO extends ApstraktnaGenerickaOperacija{

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        Priznanica priznanica = (Priznanica) objekat;
        if(!priznanica.getStavkePriznanice().isEmpty()){
            throw new Exception("Sistem ne može da obriše priznanicu.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        broker.delete((Priznanica)objekat);
    }
    
}
