/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domen.Otkupljivac;
import forme.PrikazOtkupljivacaForma;
import forme.model.ModelTabeleOtkupljivaci;
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
public class PrikazOtkupljivacaController {

    private final PrikazOtkupljivacaForma pof;

    public PrikazOtkupljivacaController(PrikazOtkupljivacaForma pof) {
        this.pof = pof;
        addActionListener();
    }

    public void otvoriFormu() {
        pripremiFormu();
        pof.setVisible(true);
    }

    private void pripremiFormu() {
        List<Otkupljivac> otkupljivaci = Komunikacija.getInstance().ucitajOtkupljivace();
        ModelTabeleOtkupljivaci mto = new ModelTabeleOtkupljivaci(otkupljivaci);
        pof.getTblOtkupljivaci().setModel(mto);

        pof.getTxtIme().setText("");
        pof.getTxtPrezime().setText("");
        pof.getTxtKorisnickoIme().setText("");
    }

    private void addActionListener() {
        pof.addBtnObrisiActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = pof.getTblOtkupljivaci().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(pof, "Sistem ne može da nađe otkupljivača.", "Greška", JOptionPane.ERROR_MESSAGE);
                } else {
                    ModelTabeleOtkupljivaci mto = (ModelTabeleOtkupljivaci) pof.getTblOtkupljivaci().getModel();
                    Otkupljivac o = mto.getLista().get(red);
                    JOptionPane.showMessageDialog(pof, "Sistem je našao otkupljivača.\n" + o, "Obaveštenje", JOptionPane.INFORMATION_MESSAGE);
                    try {
                        Komunikacija.getInstance().obrisiOtkupljivaca(o);
                        JOptionPane.showMessageDialog(pof, "Sistem je obrisao otkupljivača.", "Obaveštenje", JOptionPane.INFORMATION_MESSAGE);
                        pripremiFormu();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(pof, "Sistem ne može da obriše otkupljivača.", "Greška", JOptionPane.ERROR_MESSAGE);
                    }

                }
            }
        });

        pof.addBtnAzurirajActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = pof.getTblOtkupljivaci().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(pof, "Sistem ne može da nađe otkupljivača.", "Greška", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(pof, "Sistem je našao otkupljivača.", "Obaveštenje", JOptionPane.INFORMATION_MESSAGE);
                    ModelTabeleOtkupljivaci mto = (ModelTabeleOtkupljivaci) pof.getTblOtkupljivaci().getModel();

                    Otkupljivac o = mto.getLista().get(red);
                    GlavniKontroler.getInstance().dodajParametar("otkupljivac", o);
                    GlavniKontroler.getInstance().otvoriAzurirajOtkupljivacaFormu();
                }
            }
        });

        pof.addBtnPretraziActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ime = pof.getTxtIme().getText().trim();
                String prezime = pof.getTxtPrezime().getText().trim();
                String korisnickoIme = pof.getTxtKorisnickoIme().getText().trim();
                try {
                    if (ime != null && !pof.getTxtIme().getText().trim().isEmpty()) {
                        Validator.startValidation().validateOnlyLettersAndSpaces(ime, "Ime može sadržati samo slova.").throwIfInvalide();
                    }
                    if (prezime != null && !pof.getTxtPrezime().getText().trim().isEmpty()) {
                        Validator.startValidation().validateOnlyLettersAndSpaces(prezime, "Prezime može sadržati samo slova.").throwIfInvalide();
                    }
                } catch (ValidationException ex) {
                    JOptionPane.showMessageDialog(pof, "Greške u validaciji:\n" + ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
                }
                ModelTabeleOtkupljivaci mto = (ModelTabeleOtkupljivaci) pof.getTblOtkupljivaci().getModel();
                int brojRezultata = mto.pretrazi(ime, prezime, korisnickoIme);
                if (brojRezultata == 0) {
                    JOptionPane.showMessageDialog(pof, "Sistem ne može da nađe otkupljivače po zadatim kriterijumima.", "Greška", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(pof, "Sistem je našao otkupljivače po zadatim kriterijumima.", "Obaveštenje", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        pof.addBtnResetujPretraguActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pripremiFormu();
            }
        });

    }

    public void osveziFormu() {
        pripremiFormu();
    }
}
