/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komunikacija;

import domen.Otkupljivac;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author Bojana
 */
public class Komunikacija {
    private Socket soket;
    private Posiljalac posiljalac;
    private Primalac primalac;
    private static Komunikacija instance;

    private Komunikacija() {
    }

    public static Komunikacija getInstance() {
        if(instance == null){
            instance = new Komunikacija();
        }
        return instance;
    }
    
    public void konekcija(){
        try {
            soket = new Socket("localhost", 9000);
            posiljalac = new Posiljalac(soket);
            primalac = new Primalac(soket);
        } catch (IOException ex) {
            System.out.println("Server nije povezan!");
        }
        
    }

    public Otkupljivac login(String korisnickoIme,String sifra) {
        Otkupljivac otkupljivac = new Otkupljivac();
        otkupljivac.setKorisnickoIme(korisnickoIme);System.out.print("Klasa Komunikacija: "+otkupljivac.getKorisnickoIme()+" ");
        otkupljivac.setSifra(sifra);System.out.println(otkupljivac.getSifra());
        Zahtev zahtev = new Zahtev(Operacija.LOGIN, otkupljivac); System.out.println(zahtev.getParametar().toString());
        posiljalac.posalji(zahtev);
        
        Odgovor odg = (Odgovor) primalac.primi();
        Otkupljivac o = (Otkupljivac) odg.getOdgovor();
        
        return o;
    }
    
    
}
