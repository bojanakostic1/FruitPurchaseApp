/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroler;

import controller.DodajProizvodjacaController;
import controller.GlavnaFormaController;
import controller.LoginController;
import controller.PrikazProizvodjacaController;
import domen.Otkupljivac;
import forme.DodajProizvodjacaForma;
import forme.FormaMod;
import forme.GlavnaForma;
import forme.LoginForma;
import forme.PrikazProizvodjacaForma;
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
    
    
}
