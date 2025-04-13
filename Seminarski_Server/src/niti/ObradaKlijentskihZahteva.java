/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package niti;

import controller.Controller;
import domen.Otkupljivac;
import java.io.IOException;
import java.net.Socket;
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
