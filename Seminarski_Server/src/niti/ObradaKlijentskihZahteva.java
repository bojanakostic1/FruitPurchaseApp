/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package niti;

import controller.Controller;
import domen.Mesto;
import domen.Otkupljivac;
import domen.Proizvodjac;
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
