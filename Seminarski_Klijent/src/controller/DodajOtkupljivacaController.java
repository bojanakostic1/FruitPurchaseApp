/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domen.Otkupljivac;
import forme.DodajOtkupljivacaForma;
import forme.FormaMod;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import kontroler.GlavniKontroler;
import validator.ValidationException;
import validator.Validator;

/**
 *
 * @author Bojana
 */
public class DodajOtkupljivacaController {

    private final DodajOtkupljivacaForma dof;

    public DodajOtkupljivacaController(DodajOtkupljivacaForma dof) {
        this.dof = dof;
        addActionListener();
    }

    public void otvoriFormu(FormaMod mod) {
        pripremiFormu(mod);
        dof.setVisible(true);
    }

    private void pripremiFormu(FormaMod mod) {
        switch (mod) {
            case DODAJ:
                dof.setTitle("Dodavanje novog otkupljivača");
                dof.getBtnAzuriraj().setVisible(false);
                dof.getBtnDodaj().setVisible(true);
                dof.getBtnDodaj().setEnabled(true);
                dof.getTxtId().setText("Biće automatski dodeljen.");
                dof.getTxtId().setEnabled(false);
                break;
            case IZMENI:
                dof.setTitle("Izmena postojećeg otkupljivača");
                dof.getBtnDodaj().setVisible(false);
                dof.getBtnAzuriraj().setVisible(true);
                dof.getBtnAzuriraj().setEnabled(true);

                Otkupljivac otkupljivac = (Otkupljivac) GlavniKontroler.getInstance().vratiParametar("otkupljivac");
                dof.getTxtId().setText(String.valueOf(otkupljivac.getIdOtkupljivac()));
                dof.getTxtId().setEnabled(false);
                dof.getTxtIme().setText(otkupljivac.getIme());
                dof.getTxtPrezime().setText(otkupljivac.getPrezime());
                dof.getTxtKorisnickoIme().setText(otkupljivac.getKorisnickoIme());
                dof.getTxtSifra().setText(otkupljivac.getSifra());
                break;
            default:
                throw new AssertionError();
        }
    }

    private void addActionListener() {
        dof.dodajAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String ime = dof.getTxtIme().getText().trim();
                    String prezime = dof.getTxtPrezime().getText().trim();
                    String korisnickoIme = dof.getTxtKorisnickoIme().getText().trim();
                    String sifra = String.valueOf(dof.getTxtSifra().getPassword());
                    Validator validator = Validator.startValidation()
                            .validateNotNullOrEmpty(ime, "Ime je obavezno.")
                            .validateOnlyLettersAndSpaces(ime, "Ime može sadržati samo slova.")
                            .validateNotNullOrEmpty(prezime, "Prezime je obavezno.")
                            .validateOnlyLettersAndSpaces(prezime, "Prezime može sadržati samo slova.")
                            .validateNotNullOrEmpty(korisnickoIme, "Korisničko ime je obavezno.")
                            .validateNotNullOrEmpty(sifra, "Šifra je obavezna.");
                    try {
                        validator.throwIfInvalide();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(dof, "Greške u validaciji:\n" + ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    Otkupljivac otkupljivac = new Otkupljivac(-1, ime, prezime, korisnickoIme, sifra);
                    JOptionPane.showMessageDialog(dof, "Sistem je kreirao otkupljivača.", "Obaveštenje", JOptionPane.INFORMATION_MESSAGE);
                    try {
                        Komunikacija.getInstance().dodajOtkupljivaca(otkupljivac);
                        JOptionPane.showMessageDialog(dof, "Sistem je zapamtio otkupljivača.\n" + otkupljivac, "Obaveštenje", JOptionPane.INFORMATION_MESSAGE);
                        dof.dispose();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(dof, "Sistem ne može da zapamti otkupljivača.\n" + ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dof, "Sistem ne može da kreira otkupljivača.", "Greška", JOptionPane.ERROR_MESSAGE);
                    dof.dispose();
                }
            }
        });

        dof.azuirajAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(dof.getTxtId().getText().trim());
                String ime = dof.getTxtIme().getText().trim();
                String prezime = dof.getTxtPrezime().getText().trim();
                String korisnickoIme = dof.getTxtKorisnickoIme().getText().trim();
                String sifra = new String(dof.getTxtSifra().getPassword());
                try {
                            Validator.startValidation()
                            .validateNotNullOrEmpty(ime, "Ime je obavezno.")
                            .validateOnlyLettersAndSpaces(ime, "Ime može sadržati samo slova.")
                            .validateNotNullOrEmpty(prezime, "Prezime je obavezno.")
                            .validateOnlyLettersAndSpaces(prezime, "Prezime može sadržati samo slova.")
                            .validateNotNullOrEmpty(korisnickoIme, "Korisničko ime je obavezno.")
                            .validateNotNullOrEmpty(sifra, "Šifra je obavezna.")
                                    .throwIfInvalide();
                } catch (ValidationException ex) {
                    JOptionPane.showMessageDialog(dof, "Greške u validaciji:\n" + ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                Otkupljivac otkupljivac = new Otkupljivac(id, ime, prezime, korisnickoIme, sifra);
                try {
                    Komunikacija.getInstance().azurirajOtkupljivaca(otkupljivac);
                    JOptionPane.showMessageDialog(dof, "Sistem je zapamtio otkupljivača.\n" + otkupljivac, "Obaveštenje", JOptionPane.INFORMATION_MESSAGE);
                    dof.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dof, ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        dof.otkaziAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dof.dispose();
            }
        });

    }

}
