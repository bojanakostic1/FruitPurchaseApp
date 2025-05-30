/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domen.Mesto;
import domen.Otkupljivac;
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
        prijaviSO.izvrsi(o, null);
        System.out.println("Klasa Controller: "+prijaviSO.getOtkupljivac());
        return prijaviSO.getOtkupljivac();
    }

    public List<Proizvodjac> ucitajProizvodjace() throws Exception {
        UcitajProizvodjaceSO ucitajProizvodjaceSO = new UcitajProizvodjaceSO();
        ucitajProizvodjaceSO.izvrsi(null, null);
        System.out.println("Klasa Controller: "+ucitajProizvodjaceSO.getSviProizvodjaci());
        return ucitajProizvodjaceSO.getSviProizvodjaci();
    }

    public void obrisiProizvodjaca(Proizvodjac proizvodjac) throws Exception {
        ObrisiProizvodjacaSO obrisiProizvodjacaSO = new ObrisiProizvodjacaSO();
        obrisiProizvodjacaSO.izvrsi(proizvodjac, null);
    }

    public List<Mesto> ucitajMesta() throws Exception {
        UcitajMestaSO ucitajMestaSO = new UcitajMestaSO();
        ucitajMestaSO.izvrsi(null, null);
        System.out.println("Klasa Controller: "+ucitajMestaSO.getSvaMesta());
        return ucitajMestaSO.getSvaMesta();
    }

    public void dodajProizvodjaca(Proizvodjac proizvodjac) throws Exception {
        KreirajProizvodjacaSO kreirajProizvodjacaSO = new KreirajProizvodjacaSO();
        kreirajProizvodjacaSO.izvrsi(proizvodjac, null);
    }

    public void azurirajProizvodjaca(Proizvodjac p) throws Exception {
        PromeniProizvodjacaSO promeniProizvodjacaSO = new PromeniProizvodjacaSO();
        promeniProizvodjacaSO.izvrsi(p, null);
    }

    public void obrisiMesto(Mesto m) throws Exception {
        ObrisiMestoSO obrisiMestoSO = new ObrisiMestoSO();
        obrisiMestoSO.izvrsi(m, null);
    }

    public void dodajMesto(Mesto m) throws Exception {
        KreirajMestoSO kreirajMestoSO = new KreirajMestoSO();
        kreirajMestoSO.izvrsi(m, null);
    }

    public void azurirajMesto(Mesto mesto) throws Exception {
        PromeniMestoSO promeniMestoSO = new PromeniMestoSO();
        promeniMestoSO.izvrsi(mesto, null);
    }

    public List<Sorta> ucitajSorte() throws Exception {
        UcitajSorteSO ucitajSorteSO = new UcitajSorteSO();
        ucitajSorteSO.izvrsi(null, null);
        return ucitajSorteSO.getListaSorti();
    }

    public void obrisiSortu(Sorta sorta) throws Exception {
        ObrisiSortuSO obrisiSortuSO = new ObrisiSortuSO();
        obrisiSortuSO.izvrsi(sorta, null);
    }

    public void dodajSortu(Sorta sorta) throws Exception {
        KreirajSortuSO kreirajSortuSO = new KreirajSortuSO();
        kreirajSortuSO.izvrsi(sorta, null);
    }

    public void azurirajSortu(Sorta s) throws Exception {
        PromeniSortuSO promeniSortuSO = new PromeniSortuSO();
        promeniSortuSO.izvrsi(s, null);
    }

    public List<VrstaVoca> ucitajVrsteVoca() throws Exception {
        UcitajVrsteVocaSO ucitajVrsteVocaSO = new UcitajVrsteVocaSO();
        ucitajVrsteVocaSO.izvrsi(null, null);
        return ucitajVrsteVocaSO.getLista();
    }

    public void obrisiVrstuVoca(VrstaVoca vrsta) throws Exception {
        ObrisiVrstuVocaSO obrisiVrstuVocaSO = new ObrisiVrstuVocaSO();
        obrisiVrstuVocaSO.izvrsi(vrsta, null);
    }

    public void dodajVrstuVoca(VrstaVoca vrsta) throws Exception {
        KreirajVrstuVocaSO kreirajVrstuVocaSO = new KreirajVrstuVocaSO();
        kreirajVrstuVocaSO.izvrsi(vrsta, null);
    }

    public void azurirajVrstuVoca(VrstaVoca vrsta) throws Exception {
        PromeniVrstuVocaSO promeniVrstuVocaSO = new PromeniVrstuVocaSO();
        promeniVrstuVocaSO.izvrsi(vrsta, null);
    }

    

}
