/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package niti;

import controller.Controller;
import domen.Mesto;
import domen.Otkupljivac;
import domen.Priznanica;
import domen.Proizvodjac;
import domen.Sorta;
import domen.VrstaVoca;
import java.io.IOException;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import komunikacija.Odgovor;
import komunikacija.Posiljalac;
import komunikacija.Primalac;
import komunikacija.Zahtev;

/**
 *
 * @author Bojana
 */
public class ObradaKlijentskihZahteva extends Thread {

    Socket soket;
    Primalac primalac;
    Posiljalac posiljalac;
    boolean kraj = false;

    public ObradaKlijentskihZahteva(Socket socket) {
        this.soket = socket;
        primalac = new Primalac(socket);
        posiljalac = new Posiljalac(socket);
    }

    @Override
    public void run() {
        while (!kraj) {
            try {
                Zahtev zahtev = (Zahtev) primalac.primi();

                if (zahtev == null) {
                    System.out.println("Klijent je prekinuo vezu.");
                    break; // Prekida nit ako klijent zatvori konekciju
                }
                Odgovor odgovor = new Odgovor();

                switch (zahtev.getOperacija()) {
                    case LOGIN:
                        Otkupljivac o = (Otkupljivac) zahtev.getParametar();
                        Otkupljivac otkupljivac = Controller.getInstance().login(o);
                        odgovor.setOdgovor(otkupljivac);
                        break;
                    case UCITAJ_PROIZVODJACE:
                        List<Proizvodjac> proizvodjaci = Controller.getInstance().ucitajProizvodjace();
                        odgovor.setOdgovor(proizvodjaci);
                        break;
                    case OBRISI_PROIZVODJACA:
                        try {
                            Proizvodjac proizvodjac = (Proizvodjac) zahtev.getParametar();
                            Controller.getInstance().obrisiProizvodjaca(proizvodjac);
                            odgovor.setOdgovor(null);
                        } catch (Exception e) {
                            odgovor.setOdgovor(e);
                        }
                        break;
                    case UCITAJ_MESTA:
                        List<Mesto> mesta = Controller.getInstance().ucitajMesta();
                        odgovor.setOdgovor(mesta);
                        break;
                    case DODAJ_PROIZVODJACA:
                        Proizvodjac proizvodjac = (Proizvodjac) zahtev.getParametar();
                        Controller.getInstance().dodajProizvodjaca(proizvodjac);
                        odgovor.setOdgovor(null);
                        break;
                    case AZURIRAJ_PROIZVODJACA:
                        Proizvodjac p = (Proizvodjac) zahtev.getParametar();
                        Controller.getInstance().azurirajProizvodjaca(p);
                        odgovor.setOdgovor(null);
                    case OBRISI_MESTO:
                        try {
                            Mesto m = (Mesto) zahtev.getParametar();
                            Controller.getInstance().obrisiMesto(m);
                            odgovor.setOdgovor(null);
                        } catch (Exception ex) {
                            odgovor.setOdgovor(ex);
                        }
                        break;
                    case DODAJ_MESTO:
                        Mesto m = (Mesto) zahtev.getParametar();
                        try {
                            Controller.getInstance().dodajMesto(m);
                        } catch (Exception ex) {
                            odgovor.setOdgovor(ex);
                        }
                        break;
                    case AZURIRAJ_MESTO:
                        Mesto mesto = (Mesto) zahtev.getParametar();
                        Controller.getInstance().azurirajMesto(mesto);
                        odgovor.setOdgovor(null);
                    case UCITAJ_SORTE:
                        List<Sorta> sorte = Controller.getInstance().ucitajSorte();
                        odgovor.setOdgovor(sorte);
                        break;
                    case OBRISI_SORTU:
                        try {
                            Sorta sorta = (Sorta) zahtev.getParametar();
                            Controller.getInstance().obrisiSortu(sorta);
                            odgovor.setOdgovor(null);
                        } catch (Exception ex) {
                            odgovor.setOdgovor(ex);
                        }
                        break;
                    case DODAJ_SORTU:
                        Sorta sorta = (Sorta) zahtev.getParametar();
                        try {
                            Controller.getInstance().dodajSortu(sorta);
                            odgovor.setOdgovor(null);
                        } catch (Exception ex) {
                            odgovor.setOdgovor(ex);
                        }
                        break;
                    case AZURIRAJ_SORTU:
                        Sorta s = (Sorta) zahtev.getParametar();
                        Controller.getInstance().azurirajSortu(s);
                        odgovor.setOdgovor(null);
                    case UCITAJ_VRSTE_VOCA:
                        List<VrstaVoca> lista = Controller.getInstance().ucitajVrsteVoca();
                        odgovor.setOdgovor(lista);
                        break;
                    case OBRISI_VRSTU_VOCA:
                        try {
                            VrstaVoca vrsta = (VrstaVoca) zahtev.getParametar();
                            Controller.getInstance().obrisiVrstuVoca(vrsta);
                            odgovor.setOdgovor(null);
                        } catch (Exception ex) {
                            odgovor.setOdgovor(ex);
                        }
                        break;
                    case DODAJ_VRSTU_VOCA:
                        try {
                            VrstaVoca vrsta = (VrstaVoca) zahtev.getParametar();
                            Controller.getInstance().dodajVrstuVoca(vrsta);
                            odgovor.setOdgovor(null);
                        } catch (Exception ex) {
                            odgovor.setOdgovor(ex);
                        }
                        break;
                    case AZURIRAJ_VRSTU_VOCA:
                        VrstaVoca vrsta = (VrstaVoca) zahtev.getParametar();
                        Controller.getInstance().azurirajVrstuVoca(vrsta);
                        odgovor.setOdgovor(null);
                        break;
                    case UCITAJ_OTKUPLJIVACE:
                        List<Otkupljivac> otkupljivaci = Controller.getInstance().ucitajOtkupljivace();
                        odgovor.setOdgovor(otkupljivaci);
                        break;
                    case OBRISI_OTKUPLJIVACA:
                        try {
                            Otkupljivac otkupljivac1 = (Otkupljivac) zahtev.getParametar();
                            Controller.getInstance().obrisiOtkupljivaca(otkupljivac1);
                            odgovor.setOdgovor(null);
                        } catch (Exception ex) {
                            odgovor.setOdgovor(ex);
                        }
                        break;
                    case DODAJ_OTKUPLJIVACA:
                        Otkupljivac otkupljivac1 = (Otkupljivac) zahtev.getParametar();
                         try {
                            Controller.getInstance().dodajOtkupljivaca(otkupljivac1);
                        } catch (Exception ex) {
                            odgovor.setOdgovor(ex);
                        }
                        break;
                    case AZURIRAJ_OTKUPLJIVACA:
                        Otkupljivac otkupljivac2 = (Otkupljivac) zahtev.getParametar();
                        Controller.getInstance().azurirajOtkupljivaca(otkupljivac2);
                        odgovor.setOdgovor(null);
                        break;
                    case UCITAJ_PRIZNANICE:
                        List<Priznanica> priznanice = Controller.getInstance().ucitajPriznanice();
                        odgovor.setOdgovor(priznanice);
                        break;
                    case OBRISI_PRIZNANICU:
                        try {
                            Priznanica priznanica = (Priznanica) zahtev.getParametar();
                            Controller.getInstance().obrisiPriznanicu(priznanica);
                            odgovor.setOdgovor(null);
                        } catch (Exception e) {
                            odgovor.setOdgovor(e);
                        }
                        break;
                    case DODAJ_PRIZNANICU:
                        try {
                            Priznanica priznanica  = (Priznanica) zahtev.getParametar();
                            Controller.getInstance().dodajPriznanicu(priznanica);
                            System.out.println("priznanica okz:"+priznanica);
                            odgovor.setOdgovor(priznanica);
                        } catch (Exception ex) {
                            odgovor.setOdgovor(ex);
                        }
                        break;
                        case AZURIRAJ_PRIZNANICU:
                        try {
                            Priznanica priznanica  = (Priznanica) zahtev.getParametar();
                            Controller.getInstance().azurirajPriznanicu(priznanica);
                            System.out.println("priznanica okz:"+priznanica);
                            odgovor.setOdgovor(priznanica);
                        } catch (Exception ex) {
                            odgovor.setOdgovor(ex);
                        }
                        break;
                    default:
                        System.out.println("Greska!Operacija ne postoji!");
                }
                posiljalac.posalji(odgovor);
            } catch (Exception ex) {
                System.out.println("Greška u obradi klijentskog zahteva: " + ex.getMessage());
                Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void prekiniNit() {
        kraj = true;
        try {
            soket.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        interrupt();

    }
}
