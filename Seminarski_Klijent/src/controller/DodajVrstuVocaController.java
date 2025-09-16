/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domen.VrstaVoca;
import forme.DodajVrstuVocaForma;
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
public class DodajVrstuVocaController {

    private final DodajVrstuVocaForma dvvf;

    public DodajVrstuVocaController(DodajVrstuVocaForma dvvf) {
        this.dvvf = dvvf;
        addActionListeners();
    }

    public void otvoriFormu(FormaMod mod) {
        pripremiFormu(mod);
        dvvf.setVisible(true);
    }

    private void addActionListeners() {
        dvvf.dodajAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String naziv = dvvf.getTxtNaziv().getText().trim();
                    Validator validator = Validator.startValidation()
                            .validateNotNullOrEmpty(naziv, "Naziv vrste voća je obavezno polje.")
                            .validateOnlyLettersAndSpaces(naziv, "Naziv može sadržati samo slova.");
                    validator.throwIfInvalide();
                    VrstaVoca vrstaVoca = new VrstaVoca(-1, naziv);
                    try {
                        Komunikacija.getInstance().dodajVrstuVoca(vrstaVoca);
                        JOptionPane.showMessageDialog(dvvf, "Sistem je zapamtio vrstu voća.\n" + vrstaVoca.getNaziv(), "Obaveštenje", JOptionPane.INFORMATION_MESSAGE);
                        dvvf.dispose();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(dvvf, "Sistem ne može da zapamti vrstu voća.\n" + ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ve) {
                    JOptionPane.showMessageDialog(dvvf, ve.getMessage(), "Greške pri validaciji", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        dvvf.azurirajAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(dvvf.getTxtId().getText().trim());
                    String naziv = dvvf.getTxtNaziv().getText().trim();
                    Validator.startValidation()
                            .validateNotNullOrEmpty(naziv, "Naziv vrste voća je obavezno polje.")
                            .validateOnlyLettersAndSpaces(naziv, "Naziv može sadržati samo slova.")
                            .throwIfInvalide();
                    VrstaVoca vrsta = new VrstaVoca(id, naziv);
                    try {
                        Komunikacija.getInstance().azurirajVrstuVoca(vrsta);
                        JOptionPane.showMessageDialog(dvvf, "Sistem je zapamtio vrstu voća.\n" + vrsta, "Obaveštenje", JOptionPane.INFORMATION_MESSAGE);
                        dvvf.dispose();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(dvvf, "Sistem ne može da zapamti vrstu voća.", "Greška", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (ValidationException ve) {
                    JOptionPane.showMessageDialog(dvvf, ve.getMessage(), "Greške pri validaciji", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        dvvf.otkaziAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dvvf.dispose();
            }
        });
    }

    private void pripremiFormu(FormaMod mod) {
        switch (mod) {
            case DODAJ:
                dvvf.setTitle("Dodavanje nove vrste voća");
                dvvf.getBtnAzuriraj().setVisible(false);
                dvvf.getBtnDodaj().setVisible(true);
                dvvf.getBtnDodaj().setEnabled(true);
                dvvf.getTxtId().setEnabled(false);
                dvvf.getTxtId().setText("ID će biti automatski dodeljen.");
                break;
            case IZMENI:
                dvvf.setTitle("Izmena postojeće vrste voća");
                dvvf.getBtnAzuriraj().setVisible(true);
                dvvf.getBtnAzuriraj().setEnabled(true);
                dvvf.getBtnDodaj().setVisible(false);

                VrstaVoca vrsta = (VrstaVoca) GlavniKontroler.getInstance().vratiParametar("vrsta_voca");
                dvvf.getTxtId().setText(String.valueOf(vrsta.getIdVrstaVoca()));
                dvvf.getTxtId().setEnabled(false);
                dvvf.getTxtNaziv().setText(vrsta.getNaziv());
                break;
            default:
                throw new AssertionError();
        }
    }
}
