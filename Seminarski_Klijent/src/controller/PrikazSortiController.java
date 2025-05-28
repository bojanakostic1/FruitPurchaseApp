/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domen.Sorta;
import forme.PrikazSortiForma;
import forme.model.ModelTabeleSorte;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import kontroler.GlavniKontroler;

/**
 *
 * @author Bojana
 */
public class PrikazSortiController {

    private final PrikazSortiForma psf;

    public PrikazSortiController(PrikazSortiForma psf) {
        this.psf = psf;
        addActionListener();
    }

    public void otvoriFormu() {
        pripremiFormu();
        psf.setVisible(true);
    }

    public void pripremiFormu() {
        List<Sorta> sorte = Komunikacija.getInstance().ucitajSorte();
        ModelTabeleSorte mts = new ModelTabeleSorte(sorte);
        psf.getTblSorte().setModel(mts);

        psf.getTxtNaziv().setText("");
        psf.getTxtKategorija().setText("");
        psf.getTxtCena().setText("");
    }

    private void addActionListener() {
        psf.addBtnObrisiActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = psf.getTblSorte().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(psf, "Sistem ne može da nađe sorte.", "Greška", JOptionPane.ERROR_MESSAGE);
                } else {
                    ModelTabeleSorte modelTabele = (ModelTabeleSorte) psf.getTblSorte().getModel();
                    Sorta s = modelTabele.getLista().get(red);
                    JOptionPane.showMessageDialog(psf, "Sistem je našao sortu. " + s, "Obaveštenje", JOptionPane.INFORMATION_MESSAGE);
                    try {
                        Komunikacija.getInstance().obrisiSortu(s);
                        JOptionPane.showMessageDialog(null, "Sistem je obrisao sortu.", "Obaveštenje", JOptionPane.INFORMATION_MESSAGE);
                        pripremiFormu();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(psf, "Sistem ne može da obriše sortu.", "Greška", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        psf.addBtnAzurirajActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = psf.getTblSorte().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(psf, "Sistem ne može da nađe sortu.", "Greška", JOptionPane.ERROR_MESSAGE);
                } else {
                    ModelTabeleSorte mts = (ModelTabeleSorte) psf.getTblSorte().getModel();
                    Sorta s = mts.getLista().get(red);
                    JOptionPane.showMessageDialog(psf, "Sistem je našao sortu. "+s, "Obaveštenje", JOptionPane.INFORMATION_MESSAGE);
                    GlavniKontroler.getInstance().dodajParametar("sorta", s);
                    GlavniKontroler.getInstance().otvoriAzurirajSortuFormu();
                }
            }
        });

        psf.addBtnPretraziActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String naziv = psf.getTxtNaziv().getText().trim();
                Integer kategorija = null;
                if (!psf.getTxtKategorija().getText().trim().isEmpty()) {
                    try {
                        kategorija = Integer.valueOf(psf.getTxtKategorija().getText().trim());
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(psf, "Kategorija mora biti broj (1, 2 ili 3).", "Greška", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }

                Double cena = null;
                if (!psf.getTxtCena().getText().trim().isEmpty()) {
                    try {
                        cena = Double.parseDouble(psf.getTxtCena().getText().trim());
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(psf, "Cena mora biti broj.", "Greška", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
                System.out.println("Pokupljeni podaci");
                ModelTabeleSorte mts = (ModelTabeleSorte) psf.getTblSorte().getModel();
                int brojRezultata = mts.pretrazi(naziv, kategorija, cena);
                if (brojRezultata == 0) {
                    JOptionPane.showMessageDialog(psf, "Sistem ne može da nađe sorte po zadatim kriterijumima.", "Greška", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(psf, "Sistem je našao sorte po zadatim kriterijumima.", "Obaveštenje", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        psf.addBtnResetujPretraguActionListener(new ActionListener() {
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
