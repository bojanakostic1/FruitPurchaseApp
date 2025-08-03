/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroler;

import controller.DodajMestoController;
import controller.DodajOtkupljivacaController;
import controller.DodajProizvodjacaController;
import controller.DodajSortuController;
import controller.DodajVrstuVocaController;
import controller.GlavnaFormaController;
import controller.LoginController;
import controller.PrikazMestaController;
import controller.PrikazOtkupljivacaController;
import controller.PrikazProizvodjacaController;
import controller.PrikazSortiController;
import controller.PrikazVrstaVocaController;
import domen.Otkupljivac;
import forme.DodajMestoForma;
import forme.DodajOtkupljivacaForma;
import forme.DodajProizvodjacaForma;
import forme.DodajSortuForma;
import forme.DodajVrstuVocaForma;
import forme.FormaMod;
import forme.GlavnaForma;
import forme.LoginForma;
import forme.PrikazMestaForma;
import forme.PrikazOtkupljivacaForma;
import forme.PrikazProizvodjacaForma;
import forme.PrikazSortiForma;
import forme.PrikazVrstaVocaForma;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Bojana
 */
public class GlavniKontroler {
    private static GlavniKontroler instance;
    private Otkupljivac ulogovani;
    private LoginController loginController;
    private GlavnaFormaController glavnaFormaController;
    private PrikazProizvodjacaController prikazProizvodjacaController;
    private DodajProizvodjacaController dodajProizvodjacaController;
    private Map<String, Object> parametri;
    private PrikazMestaController prikazMestaController;
    private DodajMestoController dodajMestoController;
    private PrikazSortiController prikazSortiController;
    private DodajSortuController dodajSortuController;
    private PrikazVrstaVocaController prikazVrstaVocaController;
    private DodajVrstuVocaController dodajVrstuVocaController;
    private PrikazOtkupljivacaController prikazOtkupljivacaController;
    private DodajOtkupljivacaController dodajOtkupljivacaController;
    
    private GlavniKontroler() {
        parametri = new HashMap<>();
    }

    public static GlavniKontroler getInstance() {
        if(instance == null){
            instance = new GlavniKontroler();
        }
        return instance;
    }

    public void otvoriLoginFormu() {
        loginController = new LoginController(new LoginForma());
        loginController.otvoriFormu();
    }

    public void otvoriGlavnuFormu() {
        glavnaFormaController = new GlavnaFormaController(new GlavnaForma());
        glavnaFormaController.otvoriFormu();
    }

     public void otvoriPrikazProizvodjacaFormu() {
        prikazProizvodjacaController = new PrikazProizvodjacaController(new PrikazProizvodjacaForma());
        prikazProizvodjacaController.otvoriFormu();
    }

    public Otkupljivac getUlogovani() {
        return ulogovani;
    }

    public void setUlogovani(Otkupljivac ulogovani) {
        this.ulogovani = ulogovani;
    }

    public void otvoriDodajProizvodjacaFormu() {
        dodajProizvodjacaController = new DodajProizvodjacaController(new DodajProizvodjacaForma());
        dodajProizvodjacaController.otvoriFormu(FormaMod.DODAJ);
    }

   public void dodajParametar(String s, Object o){
       parametri.put(s, o);
   }
   
   public Object vratiParametar(String s){
       return parametri.get(s);
   }

    public void otvoriIzmeniProizvodjacaFormu() {
        dodajProizvodjacaController = new DodajProizvodjacaController(new DodajProizvodjacaForma());
        dodajProizvodjacaController.otvoriFormu(FormaMod.IZMENI);
        
    }

    public void osveziPrikazProizvodjacaFormu() {
        prikazProizvodjacaController.osveziFormu();
    }

    public void otvoriPrikazMestaFormu() {
        prikazMestaController = new PrikazMestaController(new PrikazMestaForma());
        prikazMestaController.otvoriFormu();
    }

    public void otvoriDodajMestoFormu() {
        dodajMestoController = new DodajMestoController(new DodajMestoForma());
        dodajMestoController.otvoriFormu(FormaMod.DODAJ);
    }

    public void otvoriIzmeniMestoFormu() {
        dodajMestoController = new DodajMestoController(new DodajMestoForma());
        dodajMestoController.otvoriFormu(FormaMod.IZMENI);
    }

    public void osveziPrikazMestaFormu() {
        prikazMestaController.osveziFormu();
    }

    public void otvoriPrikazSortiFormu() {
        prikazSortiController = new PrikazSortiController(new PrikazSortiForma());
        prikazSortiController.otvoriFormu();
    }

    public void otvoriDodajSortuFormu() {
        dodajSortuController = new DodajSortuController(new DodajSortuForma());
        dodajSortuController.otvoriFormu(FormaMod.DODAJ);
    }

    public void otvoriAzurirajSortuFormu() {
        dodajSortuController = new DodajSortuController(new DodajSortuForma());
        dodajSortuController.otvoriFormu(FormaMod.IZMENI);
    }

    public void osveziPrikazSortiFormu() {
        prikazSortiController.osveziFormu();
    }

    public void otvoriPrikazVrstaVocaFormu() {
        prikazVrstaVocaController = new PrikazVrstaVocaController(new PrikazVrstaVocaForma());
        prikazVrstaVocaController.otvoriFormu();
    }

    public void otvoriDodajVrstuVocaForma() {
        dodajVrstuVocaController = new DodajVrstuVocaController(new DodajVrstuVocaForma());
        dodajVrstuVocaController.otvoriFormu(FormaMod.DODAJ);
    }

    public void otvoriIzmeniVrstuVocaFormu() {
        dodajVrstuVocaController = new DodajVrstuVocaController(new DodajVrstuVocaForma());
        dodajVrstuVocaController.otvoriFormu(FormaMod.IZMENI);
    }

    public void osveziPrikazVrstaVocaFormu() {
        prikazVrstaVocaController.osveziFormu();
    }

    public void otvoriPrikazOtkupljivacaFormu() {
        prikazOtkupljivacaController = new PrikazOtkupljivacaController(new PrikazOtkupljivacaForma());
        prikazOtkupljivacaController.otvoriFormu();
    }

    public void otvoriDodajOtkupljivacaFormu() {
        dodajOtkupljivacaController = new DodajOtkupljivacaController(new DodajOtkupljivacaForma());
        dodajOtkupljivacaController.otvoriFormu(FormaMod.DODAJ);
    }

    public void otvoriAzurirajOtkupljivacaFormu() {
        dodajOtkupljivacaController = new DodajOtkupljivacaController(new DodajOtkupljivacaForma());
        dodajOtkupljivacaController.otvoriFormu(FormaMod.IZMENI);
    }

    public void osveziPrikazOtkupljivacaFormu() {
        prikazOtkupljivacaController.osveziFormu();
    }
    
}
