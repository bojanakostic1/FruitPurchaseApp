/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domen.Otkupljivac;
import domen.Priznanica;
import domen.Proizvodjac;
import domen.Sorta;
import domen.StavkaPriznanice;
import forme.PrikazPriznanicaForma;
import forme.model.ModelTabelePriznanice;
import forme.model.ModelTabeleStavkePriznanice;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
public class PrikazPriznanicaController {

    private final PrikazPriznanicaForma ppf;

    public PrikazPriznanicaController(PrikazPriznanicaForma ppf) {
        this.ppf = ppf;
        addActionListener();
    }

    public void otvoriFormu() {
        ucitajComboBoxeve();
        pripremiFormu();
        ppf.setVisible(true);

    }

    public void pripremiFormu() {
        List<Priznanica> priznanice = komunikacija.Komunikacija.getInstance().ucitajPrizananice();
        ModelTabelePriznanice mtp = new ModelTabelePriznanice(priznanice);
        ppf.getTblPriznanice().setModel(mtp);
        ppf.getPnlStavkePriznanice().setVisible(false);

        ppf.getTxtId().setText("");
        ppf.getTxtDatum().setText("");
        ppf.getCmbOtkupljivaci().setSelectedItem(null);
        ppf.getCmbProizvodjaci().setSelectedItem(null);
        ppf.getCmbSorte().setSelectedItem(null);
    }

    private void addActionListener() {
        ppf.addBtnDetaljiActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = ppf.getTblPriznanice().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(ppf, "Sistem ne može da nađe prizananicu.", "Greška", JOptionPane.ERROR_MESSAGE);
                } else {
                    ModelTabelePriznanice mtp = (ModelTabelePriznanice) ppf.getTblPriznanice().getModel();
                    Priznanica p = mtp.getLista().get(red);
                    JOptionPane.showMessageDialog(ppf, "Sistem je našao priznanicu.\n" + p.toString(), "Obaveštenje", JOptionPane.INFORMATION_MESSAGE);
                    List<StavkaPriznanice> lista = p.getStavkePriznanice();
                    ModelTabeleStavkePriznanice mts = new ModelTabeleStavkePriznanice(lista);
                    ppf.getTblStavkePriznanice().setModel(mts);
                    ppf.getPnlStavkePriznanice().setVisible(true);

                }
            }
        });

        ppf.addBtnObrisiActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = ppf.getTblPriznanice().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(ppf, "Sistem ne može da nađe priznanicu.", "Greška", JOptionPane.ERROR_MESSAGE);
                } else {
                    ModelTabelePriznanice mtp = (ModelTabelePriznanice) ppf.getTblPriznanice().getModel();
                    Priznanica p = mtp.getLista().get(red);
                    JOptionPane.showMessageDialog(ppf, "Sistem je našao priznanicu.\n" + p.toString(), "Obaveštenje", JOptionPane.INFORMATION_MESSAGE);
                    try {
                        Komunikacija.getInstance().obrisiPriznanicu(p);
                        JOptionPane.showMessageDialog(ppf, "Sistem je obrisao priznanicu.", "Obaveštenje", JOptionPane.INFORMATION_MESSAGE);
                        pripremiFormu();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(ppf, "Sistem ne može da obriše priznanicu.", "Greška", JOptionPane.ERROR_MESSAGE);
                    }
                }

            }
        });

        ppf.addBtnPretraziActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idStr = ppf.getTxtId().getText().trim();
                String datumStr = ppf.getTxtDatum().getText().trim();
                try {
                    int id = -1;
                    if (idStr != null && !ppf.getTxtId().getText().trim().isEmpty()) {
                        Validator.startValidation().validateValueIsNumber(idStr, "ID mora biti broj!").throwIfInvalide();
                        id = Integer.parseInt(ppf.getTxtId().getText().trim());
                    }
                    LocalDate datum = null;
                    if (datumStr != null && !ppf.getTxtDatum().getText().trim().isEmpty()) {
                        Validator.startValidation().validateValueIsDate(datumStr, "yyyy-MM-dd", "Datum mora biti u formatu yyyy-MM-dd!").throwIfInvalide();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        datum = LocalDate.parse(ppf.getTxtDatum().getText().trim(), formatter);

                    }

                    Otkupljivac otkupljivac = (Otkupljivac) ppf.getCmbOtkupljivaci().getSelectedItem();
                    Proizvodjac proizvodjac = (Proizvodjac) ppf.getCmbProizvodjaci().getSelectedItem();
                    Sorta sorta = (Sorta) ppf.getCmbSorte().getSelectedItem();

                    ModelTabelePriznanice mtp = (ModelTabelePriznanice) ppf.getTblPriznanice().getModel();
                    int brojRezultata = mtp.pretrazi(id, datum, otkupljivac, proizvodjac, sorta);
                    if (brojRezultata == 0) {
                        JOptionPane.showMessageDialog(ppf, "Sistem ne može da nađe priznanice po zadatim kriterijumima.", "Greška", JOptionPane.ERROR_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(ppf, "Sistem je našao priznanice po zadatim kriterijumima.", "Obaveštenje", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (ValidationException ve) {
                    JOptionPane.showMessageDialog(ppf, ve.getMessage(), "Greška pri validaciji", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(ppf, ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        ppf.addBtnResetujPretraguActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pripremiFormu();
            }
        });

        ppf.addBtnAzurirajActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = ppf.getTblPriznanice().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(ppf, "Sistem ne može da nađe priznanicu.", "Greška", JOptionPane.ERROR_MESSAGE);
                } else {
                    ModelTabelePriznanice mtp = (ModelTabelePriznanice) ppf.getTblPriznanice().getModel();
                    Priznanica p = mtp.getLista().get(red);
                    JOptionPane.showMessageDialog(ppf, "Sistem je našao priznanicu.\n" + p.toString(), "Obaveštenje", JOptionPane.INFORMATION_MESSAGE);
                    GlavniKontroler.getInstance().dodajParametar("priznanica", p);
                    GlavniKontroler.getInstance().otvoriIzmeniPriznanicuFormu();
                    osveziFormu();
                }
            }
        });
    }

    public void osveziFormu() {
        pripremiFormu();
    }

    private void ucitajComboBoxeve() {
        List<Otkupljivac> otkupljivaci = Komunikacija.getInstance().ucitajOtkupljivace();
        for (Otkupljivac otkupljivac : otkupljivaci) {
            ppf.getCmbOtkupljivaci().addItem(otkupljivac);
        }

        List<Proizvodjac> proizvodjaci = Komunikacija.getInstance().ucitajProizvodjace();
        for (Proizvodjac proizvodjac : proizvodjaci) {
            ppf.getCmbProizvodjaci().addItem(proizvodjac);
        }

        List<Sorta> sorte = Komunikacija.getInstance().ucitajSorte();
        for (Sorta sorta : sorte) {
            ppf.getCmbSorte().addItem(sorta);
        }
    }
}
