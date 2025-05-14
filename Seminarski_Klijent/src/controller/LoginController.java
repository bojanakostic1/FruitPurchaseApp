/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domen.Otkupljivac;
import forme.LoginForma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import kontroler.GlavniKontroler;

/**
 *
 * @author Bojana
 */
public class LoginController {

    private final LoginForma lf;

    public LoginController(LoginForma lf) {
        this.lf = lf;
        addActionListeners();
    }

    private void addActionListeners() {
        lf.loginAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ulogujSe(e);
            }

            private void ulogujSe(ActionEvent e) {
                String korisnickoIme = lf.getTxtKorisnickoIme().getText().trim();
                String sifra = String.valueOf(lf.getTxtSifra().getPassword()).trim();
                System.out.println("Klasa LoginController:"+korisnickoIme+" "+sifra);
                Komunikacija.getInstance().konekcija(); System.out.println("Klijent i server su konektovani.");
                Otkupljivac ulogovaniOtkupljivac = Komunikacija.getInstance().login(korisnickoIme, sifra);
                if (ulogovaniOtkupljivac == null) {
                    JOptionPane.showMessageDialog(lf, "Neuspešno logovanje na sistem!", "Neuspešno logovanje", JOptionPane.ERROR_MESSAGE);
                } else {
                    GlavniKontroler.getInstance().setUlogovani(ulogovaniOtkupljivac);
                    JOptionPane.showMessageDialog(lf, "Uspešno ste se ulogovali na sistem!", "Uspešno logovanje", JOptionPane.INFORMATION_MESSAGE);
                    GlavniKontroler.getInstance().otvoriGlavnuFormu();
                    lf.dispose();
                    
                }
            }
        });
    }

    public void otvoriFormu() {
        lf.setVisible(true);
    }

}
