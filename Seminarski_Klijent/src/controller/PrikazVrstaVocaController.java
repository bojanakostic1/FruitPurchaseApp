/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domen.VrstaVoca;
import forme.PrikazVrstaVocaForma;
import forme.model.ModelTabeleVrsteVoca;
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
public class PrikazVrstaVocaController {

    private final PrikazVrstaVocaForma pvvf;

    public PrikazVrstaVocaController(PrikazVrstaVocaForma pvvf) {
        this.pvvf = pvvf;
        addActionListeners();
    }

    public void otvoriFormu() {
        pripremiFormu();
        pvvf.setVisible(true);
    }

    private void pripremiFormu() {
        List<VrstaVoca> lista = Komunikacija.getInstance().ucitajVrsteVoca();
        ModelTabeleVrsteVoca mtvv = new ModelTabeleVrsteVoca(lista);
        pvvf.getTblVrsteVoca().setModel(mtvv);

        pvvf.getTxtNaziv().setText("");
    }

    private void addActionListeners() {
        pvvf.addBtnObrisiActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = pvvf.getTblVrsteVoca().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(pvvf, "Sistem ne može da nađe vrstu voća.", "Greška", JOptionPane.ERROR_MESSAGE);
                } else {
                    ModelTabeleVrsteVoca mtvv = (ModelTabeleVrsteVoca) pvvf.getTblVrsteVoca().getModel();
                    VrstaVoca vrsta = mtvv.getLista().get(red);
                    JOptionPane.showMessageDialog(pvvf, "Sistem je našao vrstu voća.\n" + vrsta, "Obaveštenje", JOptionPane.INFORMATION_MESSAGE);
                    try {
                        Komunikacija.getInstance().obrisiVrstuVoca(vrsta);
                        JOptionPane.showMessageDialog(pvvf, "Sistem je obrisao vrstu voća.", "Greška", JOptionPane.INFORMATION_MESSAGE);
                        pripremiFormu();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(pvvf, "Sistem ne može da obriše vrstu voća.", "Greška", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        pvvf.addBtnAzurirajActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = pvvf.getTblVrsteVoca().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(pvvf, "Sistem ne može da nađe vrstu voća.", "Greška", JOptionPane.ERROR_MESSAGE);
                } else {
                    ModelTabeleVrsteVoca model = (ModelTabeleVrsteVoca) pvvf.getTblVrsteVoca().getModel();
                    VrstaVoca vrsta = model.getLista().get(red);
                    JOptionPane.showMessageDialog(pvvf, "Sistem je našao vrstu voća.\n" + vrsta, "Obaveštenje", JOptionPane.INFORMATION_MESSAGE);
                    GlavniKontroler.getInstance().dodajParametar("vrsta_voca", vrsta);
                    GlavniKontroler.getInstance().otvoriIzmeniVrstuVocaFormu();
                }
            }
        });

        pvvf.addBtnPretraziActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String naziv = pvvf.getTxtNaziv().getText().trim();
                try {
                    Validator.startValidation()
                            .validateNotNullOrEmpty(naziv, "Naziv vrste voća je obavezno polje.")
                            .validateOnlyLettersAndSpaces(naziv, "Naziv može sadržati samo slova.")
                            .throwIfInvalide();
                } catch (ValidationException ve) {
                    JOptionPane.showMessageDialog(pvvf, ve.getMessage(), "Greške pri validaciji", JOptionPane.ERROR_MESSAGE);
                }
                ModelTabeleVrsteVoca model = (ModelTabeleVrsteVoca) pvvf.getTblVrsteVoca().getModel();
                int brojRezultata = model.pretrazi(naziv);
                if (brojRezultata == 0) {
                    JOptionPane.showMessageDialog(pvvf, "Sistem ne može da nađe vrste voća po zadatim kriterijumima.", "Greška", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(pvvf, "Sistem je našao vrste voća po zadatim kriterijumima.", "Obaveštenje", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        pvvf.addBtnResetujPretraguActionListener(new ActionListener() {
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
