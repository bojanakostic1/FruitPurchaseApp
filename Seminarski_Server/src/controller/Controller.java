/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domen.Mesto;
import domen.Otkupljivac;
import domen.Proizvodjac;
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

    

}
