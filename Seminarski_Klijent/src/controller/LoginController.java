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
import validator.Validator;

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
                try {
                    Validator validator = Validator.startValidation()
                            .validateNotNullOrEmpty(korisnickoIme, "Korisničko ime nije ispravno.")
                            .validateNotNullOrEmpty(sifra, "Šifra nije ispravna.");

                    validator.throwIfInvalide();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(lf, "Greške u validaciji:\n" + ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                System.out.println("Klasa LoginController:" + korisnickoIme + " " + sifra);
                Komunikacija.getInstance().konekcija();
                System.out.println("Klijent i server su konektovani.");
                Otkupljivac ulogovaniOtkupljivac = Komunikacija.getInstance().login(korisnickoIme, sifra);
                if (ulogovaniOtkupljivac == null) {
                    JOptionPane.showMessageDialog(lf, "Korisničko ime i šifra nisu ispravni.", "Neuspešno logovanje", JOptionPane.ERROR_MESSAGE);
                    JOptionPane.showMessageDialog(lf, "Ne može da se otvori glavna forma i meni.", "Neuspešno otvaranje glavne forme", JOptionPane.ERROR_MESSAGE);
                } else {
                    GlavniKontroler.getInstance().setUlogovani(ulogovaniOtkupljivac);
                    JOptionPane.showMessageDialog(lf, "Korisničko ime i šifra su ispravni.", "Uspešno logovanje", JOptionPane.INFORMATION_MESSAGE);
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
