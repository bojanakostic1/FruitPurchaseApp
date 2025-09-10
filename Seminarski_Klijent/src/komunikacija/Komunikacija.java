/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komunikacija;

import domen.Mesto;
import domen.Otkupljivac;
import domen.Priznanica;
import domen.Proizvodjac;
import domen.Sorta;
import domen.VrstaVoca;
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

    public List<VrstaVoca> ucitajVrsteVoca() {
        List<VrstaVoca> lista = new ArrayList<>();
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_VRSTE_VOCA, null);
        posiljalac.posalji(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primi();
        lista = (List<VrstaVoca>) odgovor.getOdgovor();
        return lista;
    }

    public void obrisiVrstuVoca(VrstaVoca vrsta) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.OBRISI_VRSTU_VOCA, vrsta);
        posiljalac.posalji(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primi();
        if (odgovor.getOdgovor() == null) {
            System.out.println("Sistem je obrisao vrstu voca!");
        } else {
            System.out.println("Sistem nije obrisao vrstu voca!");
            ((Exception) odgovor.getOdgovor()).printStackTrace();
            throw new Exception("Greška");
        }
    }

    public void dodajVrstuVoca(VrstaVoca vrstaVoca) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.DODAJ_VRSTU_VOCA, vrstaVoca);
        posiljalac.posalji(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primi();
        if (odgovor.getOdgovor() == null) {
            System.out.println("Vrsta voća je uspesno dodata!");
        } else {
            System.out.println("Vrsta voća nije uspešno dodata!");
            ((Exception) odgovor.getOdgovor()).printStackTrace();
            throw new Exception("Greška.");
        }

    }

    public void azurirajVrstuVoca(VrstaVoca vrsta) {
        Zahtev zahtev = new Zahtev(Operacija.AZURIRAJ_VRSTU_VOCA, vrsta);
        posiljalac.posalji(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primi();
        if (odgovor.getOdgovor() == null) {
            System.out.println("Sistem je azurirao vrstu voca!");
            GlavniKontroler.getInstance().osveziPrikazVrstaVocaFormu();
        } else {
            System.out.println("Sistem nije uspesno azurirao vrstu voca!");
        }
    }

    public List<Otkupljivac> ucitajOtkupljivace() {
        List<Otkupljivac> lista = new ArrayList<>();
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_OTKUPLJIVACE, null);
        posiljalac.posalji(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primi();
        lista = (List<Otkupljivac>) odgovor.getOdgovor();
        return lista;
    }

    public void obrisiOtkupljivaca(Otkupljivac o) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.OBRISI_OTKUPLJIVACA, o);
        posiljalac.posalji(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primi();
        if (odgovor.getOdgovor() == null) {
            System.out.println("Sistem je obrisao otkupljivaca!");
        } else {
            System.out.println("Sistem nije obrisao otkupljivaca!");
            ((Exception) odgovor.getOdgovor()).printStackTrace();
            throw new Exception("Greška");
        }
    }

    public void dodajOtkupljivaca(Otkupljivac otkupljivac) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.DODAJ_OTKUPLJIVACA, otkupljivac);
        posiljalac.posalji(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primi();
        if (odgovor.getOdgovor() == null) {
            System.out.println("Otkupljivac je uspesno dodat!");
        } else {
            System.out.println("Otkupljivac nije uspešno dodat!");
            ((Exception) odgovor.getOdgovor()).printStackTrace();
            throw new Exception("Greška.");
        }
    }

    public void azurirajOtkupljivaca(Otkupljivac otkupljivac) {
        Zahtev zahtev = new Zahtev(Operacija.AZURIRAJ_OTKUPLJIVACA, otkupljivac);
        posiljalac.posalji(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primi();
        if (odgovor.getOdgovor() == null) {
            System.out.println("Sistem je azurirao otkupljivaca!");
            GlavniKontroler.getInstance().osveziPrikazOtkupljivacaFormu();
        } else {
            System.out.println("Sistem nije uspesno azurirao otkupljivaca!");
        }

    }

    public List<Priznanica> ucitajPrizananice() {
        List<Priznanica> priznanice = new ArrayList<>();
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_PRIZNANICE, null);
        posiljalac.posalji(zahtev);

        Odgovor odg = (Odgovor) primalac.primi();
        priznanice = (List<Priznanica>) odg.getOdgovor();
        return priznanice;
    }

    public void obrisiPriznanicu(Priznanica p) throws Exception {
        Zahtev z = new Zahtev(Operacija.OBRISI_PRIZNANICU, p);
        posiljalac.posalji(z);

        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor() == null) {
            System.out.println("Uspešno obrisana priznanica!");
        } else {
            System.out.println("Neuspešno brisanje priznanice!");
            ((Exception) odg.getOdgovor()).printStackTrace();
            throw new Exception("Greska");
        }
    }

    public Priznanica dodajPriznanicu(Priznanica priznanica) throws Exception {
        System.out.println("ID pre slanja u komunikaciju: " + priznanica.getIdPriznanica());
        Zahtev zahtev = new Zahtev(Operacija.DODAJ_PRIZNANICU, priznanica);
        posiljalac.posalji(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primi();
        if (odgovor.getOdgovor() instanceof Priznanica) {
            priznanica = (Priznanica) odgovor.getOdgovor();
            System.out.println("Priznanica je uspesno dodata!");
            System.out.println("komunikacija priznanica:" + priznanica);
            return priznanica;
        } else {
            System.out.println("Priznanica nije uspešno dodata!");
            System.out.println(odgovor.getOdgovor());
            throw new Exception("Greška.");
        }
    }

    public Priznanica azurirajPriznanicu(Priznanica priznanica) throws Exception {
        System.out.println("ID pre slanja u komunikaciju: " + priznanica.getIdPriznanica());
        Zahtev zahtev = new Zahtev(Operacija.AZURIRAJ_PRIZNANICU, priznanica);
        posiljalac.posalji(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primi();
        if (odgovor.getOdgovor() instanceof Priznanica) {
            priznanica = (Priznanica) odgovor.getOdgovor();
            System.out.println("Priznanica je uspesno azurirana!");
            System.out.println("komunikacija priznanica:" + priznanica);
            return priznanica;
        } else {
            System.out.println("Priznanica nije uspešno azurirana!");
            System.out.println(odgovor.getOdgovor());
            throw new Exception("Greška.");
        }
    }

}
