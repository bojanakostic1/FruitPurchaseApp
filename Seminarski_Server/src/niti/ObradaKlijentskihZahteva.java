/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package niti;

import java.net.Socket;
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

    public ObradaKlijentskihZahteva(Socket socket) {
        this.soket = socket;
        primalac = new Primalac(socket);
        posiljalac = new Posiljalac(socket);
    }

    @Override
    public void run() {
        while (true) {
            Zahtev zahtev = (Zahtev) primalac.primi();
            Odgovor odgovor = new Odgovor();
            switch (zahtev.getOperacija()) {
                case LOGIN:

                    break;
                default:
                    System.out.println("Greska!Operacija ne postoji!");
            }
            posiljalac.posalji(odgovor);
        }
    }

}
