/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domen.Mesto;
import domen.Proizvodjac;
import forme.DodajProizvodjacaForma;
import forme.FormaMod;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import kontroler.GlavniKontroler;
import validator.ValidationException;
import validator.Validator;

/**
 *
 * @author Bojana
 */
public class DodajProizvodjacaController {

    private final DodajProizvodjacaForma dpf;

    public DodajProizvodjacaController(DodajProizvodjacaForma dpf) {
        this.dpf = dpf;
        addActionListener();
    }

    public void otvoriFormu(FormaMod mod) {
        popuniComboBox();
        pripremiFormu(mod);
        dpf.setVisible(true);
    }

    private void addActionListener() {
        dpf.dodajAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodajProizvodjaca(e);
            }

            private void dodajProizvodjaca(ActionEvent e) {
                try {
                    String ime = dpf.getTxtImeProizvodjaca().getText().trim();
                    String prezime = dpf.getTxtPrezimeProizvodjaca().getText().trim();
                    String telefon = dpf.getTxtTelefonProizvodjaca().getText().trim();
                    Mesto mesto = (Mesto) dpf.getCmbMesta().getSelectedItem();

                    Validator.startValidation().validateNotNullOrEmpty(ime, "Ime je obavezno polje!")
                            .validateOnlyLettersAndSpaces(ime, "Ime može sadržati isključivo slova!")
                            .validateNotNullOrEmpty(prezime, "Prezime je obavezno polje!")
                            .validateOnlyLettersAndSpaces(prezime, "Prezime može sadržati isključivo slova!")
                            .validateNotNullOrEmpty(telefon, "Broj telefona je obavezan!")
                            .validatePhoneNumber(telefon, "Broj telefona treba da bude u formatu +381612345678")
                            .validateNotNull(mesto, "Morate odabrati mesto iz kog je proizvođač.")
                            .throwIfInvalide();

                    Proizvodjac p = new Proizvodjac(-1, ime, prezime, telefon, mesto);
                    JOptionPane.showMessageDialog(dpf, "Sistem je kreirao proizvođača\n" + p.toString(), "Obaveštenje", JOptionPane.INFORMATION_MESSAGE);
                    try {
                        Komunikacija.getInstance().dodajProizvodjaca(p);
                        JOptionPane.showMessageDialog(dpf, "Sistem je zapamtio proizvođača.\n" + p.toString(), "Obaveštenje", JOptionPane.INFORMATION_MESSAGE);
                        dpf.dispose();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(dpf, "Sistem ne može da zapamti proizvođača.\n" + ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (ValidationException ve) {
                    JOptionPane.showMessageDialog(dpf, "Sistem ne može da kreira proizvođača.\n" + ve.getMessage(), "Greška pri validaciji", JOptionPane.ERROR_MESSAGE);
                } catch (Exception exc) {
                    JOptionPane.showMessageDialog(dpf, exc.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        dpf.izmeniAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                izmeniProizvodjaca(e);
            }

            private void izmeniProizvodjaca(ActionEvent e) {
                int id = Integer.parseInt(dpf.getTxtId().getText());
                String ime = dpf.getTxtImeProizvodjaca().getText().trim();
                String prezime = dpf.getTxtPrezimeProizvodjaca().getText().trim();
                String telefon = dpf.getTxtTelefonProizvodjaca().getText().trim();
                Mesto mesto = (Mesto) dpf.getCmbMesta().getSelectedItem();
                try {
                    Validator.startValidation().validateNotNullOrEmpty(ime, "Ime je obavezno polje!")
                            .validateOnlyLettersAndSpaces(ime, "Ime može sadržati isključivo slova!")
                            .validateNotNullOrEmpty(prezime, "Prezime je obavezno polje!")
                            .validateOnlyLettersAndSpaces(prezime, "Prezime može sadržati isključivo slova!")
                            .validateNotNullOrEmpty(telefon, "Broj telefona je obavezan!")
                            .validatePhoneNumber(telefon, "Broj telefona treba da bude u formatu +381612345678")
                            .validateNotNull(mesto, "Morate odabrati mesto iz kog je proizvođač.")
                            .throwIfInvalide();
                } catch (ValidationException ve) {
                    JOptionPane.showMessageDialog(dpf, "Sistem ne može da zapamti proizvođača.\n" + ve.getMessage(), "Greška pri validaciji.", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                Proizvodjac p = new Proizvodjac(id, ime, prezime, telefon, mesto);
                try {
                    Komunikacija.getInstance().azurirajProizvodjaca(p);
                    JOptionPane.showMessageDialog(dpf, "Sistem je zapamtio proizvođača.\n" + p.toString(), "Obaveštenje", JOptionPane.INFORMATION_MESSAGE);
                    dpf.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dpf, "Sistem ne može da zapamti proizvođača.\n" + ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        dpf.otkaziAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dpf.dispose();
            }
        });
    }

    private void popuniComboBox() {
        List<Mesto> lista = Komunikacija.getInstance().ucitajMesta();
        for (Mesto mesto : lista) {
            dpf.getCmbMesta().addItem(mesto);
        }
    }

    private void pripremiFormu(FormaMod mod) {
        switch (mod) {
            case DODAJ:
                dpf.getBtnAzuriraj().setVisible(false);
                dpf.getBtnDodaj().setVisible(true);
                dpf.getBtnDodaj().setEnabled(true);
                dpf.getTxtId().setText("Biće automatski dodeljen.");
                dpf.getTxtId().setEnabled(false);
                dpf.getCmbMesta().setSelectedItem(null);
                break;
            case IZMENI:
                dpf.getBtnDodaj().setVisible(false);
                dpf.getBtnAzuriraj().setVisible(true);
                dpf.getBtnAzuriraj().setEnabled(true);

                Proizvodjac p = (Proizvodjac) GlavniKontroler.getInstance().vratiParametar("proizvodjac");
                dpf.getTxtId().setText(String.valueOf(p.getIdProizvodjac()));
                dpf.getTxtId().setEnabled(false);
                dpf.getTxtImeProizvodjaca().setText(p.getIme());
                dpf.getTxtPrezimeProizvodjaca().setText(p.getPrezime());
                dpf.getTxtTelefonProizvodjaca().setText(p.getBrojTelefona());
                dpf.getCmbMesta().setSelectedItem(p.getMesto());
                break;
            default:
                throw new AssertionError();
        }
    }

}
