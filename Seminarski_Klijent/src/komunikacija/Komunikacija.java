/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komunikacija;

import domen.Mesto;
import domen.Otkupljivac;
import domen.Proizvodjac;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import kontroler.GlavniKontroler;

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
        if (instance == null) {
            instance = new Komunikacija();
        }
        return instance;
    }

    public void konekcija() {
        try {
            soket = new Socket("localhost", 9000);
            posiljalac = new Posiljalac(soket);
            primalac = new Primalac(soket);
        } catch (IOException ex) {
            System.out.println("Server nije povezan!");
        }

    }

    public Otkupljivac login(String korisnickoIme, String sifra) {
        Otkupljivac otkupljivac = new Otkupljivac();
        otkupljivac.setKorisnickoIme(korisnickoIme);
        System.out.print("Klasa Komunikacija: " + otkupljivac.getKorisnickoIme() + " ");
        otkupljivac.setSifra(sifra);
        System.out.println(otkupljivac.getSifra());
        Zahtev zahtev = new Zahtev(Operacija.LOGIN, otkupljivac);
        System.out.println(zahtev.getParametar().toString());
        posiljalac.posalji(zahtev);

        Odgovor odg = (Odgovor) primalac.primi();
        Otkupljivac o = (Otkupljivac) odg.getOdgovor();

        return o;
    }

    public List<Proizvodjac> ucitajProizvodjace() {
        List<Proizvodjac> proizvodjaci = new ArrayList<>();
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_PROIZVODJACE, null);
        posiljalac.posalji(zahtev);

        Odgovor odg = (Odgovor) primalac.primi();
        proizvodjaci = (List<Proizvodjac>) odg.getOdgovor();
        return proizvodjaci;
    }

    public void obrisiProizvodjaca(Proizvodjac p) throws Exception {
        Zahtev z = new Zahtev(Operacija.OBRISI_PROIZVODJACA, p);
        posiljalac.posalji(z);

        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor() == null) {
            System.out.println("Uspešno obrisan proizvođač!");
        } else {
            System.out.println("Neuspešno brisanje proizvođača!");
            ((Exception) odg.getOdgovor()).printStackTrace();
            throw new Exception("Greska");
        }
    }

    public List<Mesto> ucitajMesta() {
        Zahtev z = new Zahtev(Operacija.UCITAJ_MESTA, null);
        posiljalac.posalji(z);

        Odgovor odg = (Odgovor) primalac.primi();
        List<Mesto> mesta = new ArrayList<>();
        mesta = (List<Mesto>) odg.getOdgovor();
        return mesta;
    }

    public void dodajProizvodjaca(Proizvodjac p) {
        Zahtev z = new Zahtev(Operacija.DODAJ_PROIZVODJACA, p);
        posiljalac.posalji(z);

        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor() == null) {
            System.out.println("Novi proizvođač je uspešno dodat!");
        } else {
            System.out.println("Novi proizvodjaca; nije upsešno dodat!");
        }

    }

    public void azurirajProizvodjaca(Proizvodjac p) {
        Zahtev z = new Zahtev(Operacija.AZURIRAJ_PROIZVODJACA, p);
        posiljalac.posalji(z);
        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor() == null) {
            System.out.println("Sistem je azurirao proizvodjaca!");
            GlavniKontroler.getInstance().osveziPrikazProizvodjacaFormu();
        } else {
            System.out.println("Sistem nije uspesno azurirao proizvodjaca!");
        }
    }

}
