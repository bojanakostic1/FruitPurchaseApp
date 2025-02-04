/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import niti.ObradaKlijentskihZahteva;

/**
 *
 * @author Bojana
 */
public class Server {
    boolean kraj = false;
    ServerSocket serverSoket;
    List<ObradaKlijentskihZahteva> klijenti;

    public Server() {
        klijenti = new ArrayList<>();
    }
    
    public void pokreniServer() throws IOException {
         serverSoket = new ServerSocket(9000);
       
        while (!kraj) {
            Socket s = serverSoket.accept();
            System.out.println("Klijent je povezan!");
            ObradaKlijentskihZahteva okz = new ObradaKlijentskihZahteva(s);
            klijenti.add(okz);
            okz.start();
        }
    }
    
    public void zaustaviServer(){
        kraj = true;
        try {
            for (ObradaKlijentskihZahteva klijent : klijenti) {
                klijent.prekiniNit();
            }
            serverSoket.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
