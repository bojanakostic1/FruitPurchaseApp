/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komunikacija;

import domen.Mesto;
import domen.Otkupljivac;
import domen.Proizvodjac;
import domen.Sorta;
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

    public void obrisiMesto(Mesto m) throws Exception {
        Zahtev z = new Zahtev(Operacija.OBRISI_MESTO, m);
        posiljalac.posalji(z);

        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor() == null) {
            System.out.println("Uspešno obrisano mesto!");
        } else {
            System.out.println("Neuspešno brisanje mesta!");
            ((Exception) odg.getOdgovor()).printStackTrace();
            throw new Exception("Greška");
        }
    }

    public void dodajMesto(Mesto mesto) throws Exception {
        Zahtev z = new Zahtev(Operacija.DODAJ_MESTO, mesto);
        posiljalac.posalji(z);

        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor() == null) {
            System.out.println("Mesto je uspesno dodato!");
        } else {
            System.out.println("Mesto nije uspešno dodato!");
            ((Exception) odg.getOdgovor()).printStackTrace();
            throw new Exception("Greška.");
        }

    }

    public void azurirajMesto(Mesto mesto) {
        Zahtev zahtev = new Zahtev(Operacija.AZURIRAJ_MESTO, mesto);
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();

        if (odgovor.getOdgovor() == null) {
            System.out.println("Sistem je azurirao mesto!");
            GlavniKontroler.getInstance().osveziPrikazMestaFormu();
        } else {
            System.out.println("Sistem nije uspesno azurirao proizvodjaca!");
        }

    }

    public List<Sorta> ucitajSorte() {
        List<Sorta> lista = new ArrayList<>();
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_SORTE, null);
        posiljalac.posalji(zahtev);

        Odgovor odg = (Odgovor) primalac.primi();
        lista = (List<Sorta>) odg.getOdgovor();
        return lista;
    }

    public void obrisiSortu(Sorta s) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.OBRISI_SORTU, s);
        posiljalac.posalji(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primi();
        if (odgovor.getOdgovor() == null) {
            System.out.println("Sistem je obrisao sortu!");
        } else {
            System.out.println("Sistem nije obrisao sortu!");
            ((Exception) odgovor.getOdgovor()).printStackTrace();
            throw new Exception("Greška");
        }
    }

    public void dodajSortu(Sorta sorta) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.DODAJ_SORTU, sorta);
        posiljalac.posalji(zahtev);
        
        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor() == null) {
            System.out.println("Sorta je uspesno dodata!");
        } else {
            System.out.println("Sorta nije uspešno dodata!");
            ((Exception) odg.getOdgovor()).printStackTrace();
            throw new Exception("Greška.");
        }
    }

    public void azurirajSortu(Sorta s) {
       Zahtev z = new Zahtev(Operacija.AZURIRAJ_SORTU, s);
       posiljalac.posalji(z);
       
       Odgovor odg = (Odgovor) primalac.primi();
       if (odg.getOdgovor() == null) {
            System.out.println("Sistem je azurirao sortu!");
            GlavniKontroler.getInstance().osveziPrikazSortiFormu();
        } else {
            System.out.println("Sistem nije uspesno azurirao sortu!");
        }
                
    }

}
