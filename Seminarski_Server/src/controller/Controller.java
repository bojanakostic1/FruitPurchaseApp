/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domen.Mesto;
import domen.Otkupljivac;
import domen.Priznanica;
import domen.Proizvodjac;
import domen.Sorta;
import domen.VrstaVoca;
import java.util.List;
import operacije.login.PrijaviSO;
import operacije.mesta.KreirajMestoSO;
import operacije.mesta.ObrisiMestoSO;
import operacije.mesta.PromeniMestoSO;
import operacije.proizvodjaci.ObrisiProizvodjacaSO;
import operacije.mesta.UcitajMestaSO;
import operacije.otkupljivaci.KreirajOtkupljivacaSO;
import operacije.otkupljivaci.ObrisiOtkupljivacaSO;
import operacije.otkupljivaci.PromeniOtkupljivacaSO;
import operacije.otkupljivaci.UcitajOtkupljivaceSO;
import operacije.priznanice.KreirajPriznanicuSO;
import operacije.priznanice.ObrisiPriznanicuSO;
import operacije.priznanice.PromeniPriznanicuSO;
import operacije.priznanice.UcitajPriznaniceSO;
import operacije.proizvodjaci.KreirajProizvodjacaSO;
import operacije.proizvodjaci.PromeniProizvodjacaSO;
import operacije.proizvodjaci.UcitajProizvodjaceSO;
import operacije.sorte.KreirajSortuSO;
import operacije.sorte.ObrisiSortuSO;
import operacije.sorte.PromeniSortuSO;
import operacije.sorte.UcitajSorteSO;
import operacije.vrste_voca.KreirajVrstuVocaSO;
import operacije.vrste_voca.ObrisiVrstuVocaSO;
import operacije.vrste_voca.PromeniVrstuVocaSO;
import operacije.vrste_voca.UcitajVrsteVocaSO;

/**
 *
 * @author Bojana
 */
public class Controller {

    private static Controller instance;

    private Controller() {
    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    public Otkupljivac login(Otkupljivac o) throws Exception {
        PrijaviSO prijaviSO = new PrijaviSO();
        prijaviSO.izvrsi(o);
        System.out.println("Klasa Controller: " + prijaviSO.getOtkupljivac());
        return prijaviSO.getOtkupljivac();
    }

    public List<Proizvodjac> ucitajProizvodjace() throws Exception {
        UcitajProizvodjaceSO ucitajProizvodjaceSO = new UcitajProizvodjaceSO();
        ucitajProizvodjaceSO.izvrsi(new Proizvodjac());
        System.out.println("Klasa Controller: " + ucitajProizvodjaceSO.getSviProizvodjaci());
        return ucitajProizvodjaceSO.getSviProizvodjaci();
    }

    public void obrisiProizvodjaca(Proizvodjac proizvodjac) throws Exception {
        ObrisiProizvodjacaSO obrisiProizvodjacaSO = new ObrisiProizvodjacaSO();
        obrisiProizvodjacaSO.izvrsi(proizvodjac);
    }

    public List<Mesto> ucitajMesta() throws Exception {
        UcitajMestaSO ucitajMestaSO = new UcitajMestaSO();
        ucitajMestaSO.izvrsi(new Mesto());
        System.out.println("Klasa Controller: " + ucitajMestaSO.getSvaMesta());
        return ucitajMestaSO.getSvaMesta();
    }

    public void dodajProizvodjaca(Proizvodjac proizvodjac) throws Exception {
        KreirajProizvodjacaSO kreirajProizvodjacaSO = new KreirajProizvodjacaSO();
        kreirajProizvodjacaSO.izvrsi(proizvodjac);
    }

    public void azurirajProizvodjaca(Proizvodjac p) throws Exception {
        PromeniProizvodjacaSO promeniProizvodjacaSO = new PromeniProizvodjacaSO();
        promeniProizvodjacaSO.izvrsi(p);
    }

    public void obrisiMesto(Mesto m) throws Exception {
        ObrisiMestoSO obrisiMestoSO = new ObrisiMestoSO();
        obrisiMestoSO.izvrsi(m);
    }

    public void dodajMesto(Mesto m) throws Exception {
        KreirajMestoSO kreirajMestoSO = new KreirajMestoSO();
        kreirajMestoSO.izvrsi(m);
    }

    public void azurirajMesto(Mesto mesto) throws Exception {
        PromeniMestoSO promeniMestoSO = new PromeniMestoSO();
        promeniMestoSO.izvrsi(mesto);
    }

    public List<Sorta> ucitajSorte() throws Exception {
        UcitajSorteSO ucitajSorteSO = new UcitajSorteSO();
        ucitajSorteSO.izvrsi(new Sorta());
        return ucitajSorteSO.getListaSorti();
    }

    public void obrisiSortu(Sorta sorta) throws Exception {
        ObrisiSortuSO obrisiSortuSO = new ObrisiSortuSO();
        obrisiSortuSO.izvrsi(sorta);
    }

    public void dodajSortu(Sorta sorta) throws Exception {
        KreirajSortuSO kreirajSortuSO = new KreirajSortuSO();
        kreirajSortuSO.izvrsi(sorta);
    }

    public void azurirajSortu(Sorta s) throws Exception {
        PromeniSortuSO promeniSortuSO = new PromeniSortuSO();
        promeniSortuSO.izvrsi(s);
    }

    public List<VrstaVoca> ucitajVrsteVoca() throws Exception {
        UcitajVrsteVocaSO ucitajVrsteVocaSO = new UcitajVrsteVocaSO();
        ucitajVrsteVocaSO.izvrsi(new VrstaVoca());
        return ucitajVrsteVocaSO.getLista();
    }

    public void obrisiVrstuVoca(VrstaVoca vrsta) throws Exception {
        ObrisiVrstuVocaSO obrisiVrstuVocaSO = new ObrisiVrstuVocaSO();
        obrisiVrstuVocaSO.izvrsi(vrsta);
    }

    public void dodajVrstuVoca(VrstaVoca vrsta) throws Exception {
        KreirajVrstuVocaSO kreirajVrstuVocaSO = new KreirajVrstuVocaSO();
        kreirajVrstuVocaSO.izvrsi(vrsta);
    }

    public void azurirajVrstuVoca(VrstaVoca vrsta) throws Exception {
        PromeniVrstuVocaSO promeniVrstuVocaSO = new PromeniVrstuVocaSO();
        promeniVrstuVocaSO.izvrsi(vrsta);
    }

    public List<Otkupljivac> ucitajOtkupljivace() throws Exception {
        UcitajOtkupljivaceSO ucitajOtkupljivaceSO = new UcitajOtkupljivaceSO();
        ucitajOtkupljivaceSO.izvrsi(new Otkupljivac());
        return ucitajOtkupljivaceSO.getLista();
    }

    public void obrisiOtkupljivaca(Otkupljivac otkupljivac1) throws Exception {
        ObrisiOtkupljivacaSO obrisiOtkupljivacaSO = new ObrisiOtkupljivacaSO();
        obrisiOtkupljivacaSO.izvrsi(otkupljivac1);
    }

    public void dodajOtkupljivaca(Otkupljivac otkupljivac1) throws Exception {
        KreirajOtkupljivacaSO kreirajOtkupljivacaSO = new KreirajOtkupljivacaSO();
        kreirajOtkupljivacaSO.izvrsi(otkupljivac1);
    }

    public void azurirajOtkupljivaca(Otkupljivac otkupljivac2) throws Exception {
        PromeniOtkupljivacaSO promeniOtkupljivacaSO = new PromeniOtkupljivacaSO();
        promeniOtkupljivacaSO.izvrsi(otkupljivac2);
    }

    public List<Priznanica> ucitajPriznanice() throws Exception {
        UcitajPriznaniceSO ucitajPriznaniceSO = new UcitajPriznaniceSO();
        ucitajPriznaniceSO.izvrsi(new Priznanica());
        return ucitajPriznaniceSO.getSvePriznanice();
    }

    public void obrisiPriznanicu(Priznanica priznanica) throws Exception {
        ObrisiPriznanicuSO obrisiPriznanicuSO = new ObrisiPriznanicuSO();
        obrisiPriznanicuSO.izvrsi(priznanica);
    }

    public void dodajPriznanicu(Priznanica priznanica) throws Exception {
        KreirajPriznanicuSO kreirajPriznanicuSO = new KreirajPriznanicuSO();
        kreirajPriznanicuSO.izvrsi(priznanica);
    }

    public void azurirajPriznanicu(Priznanica priznanica) throws Exception {
        PromeniPriznanicuSO promeniPriznanicuSO = new PromeniPriznanicuSO();
        promeniPriznanicuSO.izvrsi(priznanica);
    }
}
