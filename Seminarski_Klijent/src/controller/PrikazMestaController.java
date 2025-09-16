/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domen.Mesto;
import forme.PrikazMestaForma;
import forme.model.ModelTabeleMesta;
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
public class PrikazMestaController {

    private final PrikazMestaForma pmf;

    public PrikazMestaController(PrikazMestaForma pmf) {
        this.pmf = pmf;
        addActionListeners();
    }

    public void otvoriFormu() {
        pripremiFormu();
        pmf.setVisible(true);
    }

    private void pripremiFormu() {
        List<Mesto> mesta = komunikacija.Komunikacija.getInstance().ucitajMesta();
        ModelTabeleMesta mtm = new ModelTabeleMesta(mesta);
        pmf.getTblMesta().setModel(mtm);

        pmf.getTxtNaziv().setText("");
    }

    private void addActionListeners() {
        pmf.addBtnObrisiAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = pmf.getTblMesta().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(pmf, "Sistem ne može da nađe mesto.", "Greška", JOptionPane.ERROR_MESSAGE);
                } else {
                    ModelTabeleMesta mtm = (ModelTabeleMesta) pmf.getTblMesta().getModel();
                    Mesto m = mtm.getLista().get(red);
                    JOptionPane.showMessageDialog(pmf, "Sistem je našao mesto.\nMesto:" + m.getNaziv() + "," + m.getIdMesto(), "Obaveštenje", JOptionPane.INFORMATION_MESSAGE);
                    try {
                        Komunikacija.getInstance().obrisiMesto(m);
                        JOptionPane.showMessageDialog(pmf, "Sistem je obrisao mesto.", "Greška", JOptionPane.INFORMATION_MESSAGE);
                        pripremiFormu();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(pmf, "Sistem ne može da obriše mesto.", "Greška", JOptionPane.ERROR_MESSAGE);
                    }

                }

            }
        });

        pmf.addBtnAzurirajAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = pmf.getTblMesta().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(pmf, "Sistem ne može da nađe mesto.", "Greška", JOptionPane.ERROR_MESSAGE);
                } else {
                    ModelTabeleMesta mtm = (ModelTabeleMesta) pmf.getTblMesta().getModel();
                    Mesto m = mtm.getLista().get(red);
                    JOptionPane.showMessageDialog(pmf, "Sistem je našao mesto.\nMesto:" + m.getNaziv() + "," + m.getIdMesto(), "Obaveštenje", JOptionPane.INFORMATION_MESSAGE);
                    GlavniKontroler.getInstance().dodajParametar("mesto", m);
                    GlavniKontroler.getInstance().otvoriIzmeniMestoFormu();
                }
            }
        }
        );

        pmf.addBtnPretraziAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String naziv = pmf.getTxtNaziv().getText().trim();
                try {
                    Validator.startValidation()
                            .validateOnlyLettersAndSpaces(naziv, "Naziv može sadržati samo slova.")
                            .throwIfInvalide();
                } catch (ValidationException ve) {
                    JOptionPane.showMessageDialog(pmf, ve.getMessage(), "Greška pri validaciji", JOptionPane.ERROR_MESSAGE);
                }
                ModelTabeleMesta modelTabele = (ModelTabeleMesta) pmf.getTblMesta().getModel();
                int brojRezultata = modelTabele.pretrazi(naziv);
                if (brojRezultata == 0) {
                    JOptionPane.showMessageDialog(pmf, "Sistem ne može da nađe mesta po zadatim kriterijumima.", "Greška", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(pmf, "Sistem je našao mesta po zadatim kriterijumima.", "Obaveštenje", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
        );

        pmf.addResetujPretraguActionListener(new ActionListener() {
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
