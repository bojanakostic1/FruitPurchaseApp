/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domen.Mesto;
import forme.DodajMestoForma;
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
public class DodajMestoController {

    private final DodajMestoForma dmf;

    public DodajMestoController(DodajMestoForma dmf) {
        this.dmf = dmf;
        addActionListener();
    }

    public void otvoriFormu(FormaMod mod) {
        dmf.setVisible(true);
        pripremiFormu(mod);
    }

    private void addActionListener() {
        dmf.dodajAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodajMesto(e);
            }

            private void dodajMesto(ActionEvent e) {
                try {
                    String naziv = dmf.getTxtNaziv().getText().trim();
                    Validator validator = Validator.startValidation()
                            .validateNotNullOrEmpty(naziv, "Naziv je obavezan.")
                            .validateOnlyLettersAndSpaces(naziv, "Naziv može sadržati samo slova.");

                    validator.throwIfInvalide();
                    Mesto mesto = new Mesto(-1, naziv);
                    JOptionPane.showMessageDialog(dmf, "Sistem je kreirao mesto.", "Obaveštenje", JOptionPane.INFORMATION_MESSAGE);
                    try {
                        Komunikacija.getInstance().dodajMesto(mesto);
                        JOptionPane.showMessageDialog(dmf, "Sistem je zapamtio mesto.\n" + mesto.toString(), "Obaveštenje", JOptionPane.INFORMATION_MESSAGE);
                        dmf.dispose();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(dmf, "Sistem ne može da zapamti mesto.\n" + ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (ValidationException ve) {
                    JOptionPane.showMessageDialog(dmf, ve.getMessage(), "Greške pri validaciji", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dmf, "Sistem ne može da kreira mesto.", "Greška", JOptionPane.ERROR_MESSAGE);
                    dmf.dispose();
                }

            }
        });

        dmf.azurirajAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                izmeniMesto(e);
            }

            private void izmeniMesto(ActionEvent e) {
                try {
                    int id = Integer.parseInt(dmf.getTxtId().getText().trim());
                    String naziv = dmf.getTxtNaziv().getText().trim();

                    Validator.startValidation()
                            .validateNotNullOrEmpty(naziv, "Naziv je obavezan.")
                            .validateOnlyLettersAndSpaces(naziv, "Naziv može sadržati samo slova.")
                            .throwIfInvalide();

                    Mesto mesto = new Mesto(id, naziv);
                    try {
                        Komunikacija.getInstance().azurirajMesto(mesto);
                        JOptionPane.showMessageDialog(dmf, "Sistem je zapamtio mesto.\n" + mesto.toString(), "Obaveštenje", JOptionPane.INFORMATION_MESSAGE);
                        dmf.dispose();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(dmf, "Sistem ne može da zapamti mesto.", "Greška", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (ValidationException ve) {
                    JOptionPane.showMessageDialog(dmf, ve.getMessage(), "Greška pri validaciji", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        dmf.otkaziAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dmf.dispose();
            }
        });
    }

    private void pripremiFormu(FormaMod mod) {
        switch (mod) {
            case DODAJ:
                dmf.setTitle("Dodavanje novog mesta");
                dmf.getBtnAzuriraj().setVisible(false);
                dmf.getBtnDodaj().setVisible(true);
                dmf.getBtnDodaj().setEnabled(true);
                dmf.getTxtId().setEnabled(false);
                dmf.getTxtId().setText("ID će biti automatski dodeljen.");
                break;
            case IZMENI:
                dmf.setTitle("Izmena postojećeg mesta");
                dmf.getBtnAzuriraj().setVisible(true);
                dmf.getBtnAzuriraj().setEnabled(true);
                dmf.getBtnDodaj().setVisible(false);

                Mesto m = (Mesto) GlavniKontroler.getInstance().vratiParametar("mesto");
                dmf.getTxtId().setText(String.valueOf(m.getIdMesto()));
                dmf.getTxtId().setEnabled(false);
                dmf.getTxtNaziv().setText(m.getNaziv());
                break;
            default:
                throw new AssertionError();
        }
    }

}
