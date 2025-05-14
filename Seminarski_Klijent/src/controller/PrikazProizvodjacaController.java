/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domen.Mesto;
import domen.Proizvodjac;
import forme.PrikazProizvodjacaForma;
import forme.model.ModelTabeleProizvodjaci;
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
public class PrikazProizvodjacaController {

    private final PrikazProizvodjacaForma ppf;

    public PrikazProizvodjacaController(PrikazProizvodjacaForma ppf) {
        this.ppf = ppf;
        addActionListener();
    }

    public void otvoriFormu() {
        ucitajMesta();
        pripremiFormu();
        ppf.setVisible(true);
        //ucitaj sve proizvodjace i povezi sa modelom tabele

    }

    public void pripremiFormu() {
        List<Proizvodjac> proizvodjaci = komunikacija.Komunikacija.getInstance().ucitajProizvodjace();
        ModelTabeleProizvodjaci mtp = new ModelTabeleProizvodjaci(proizvodjaci);
        ppf.getTblProizvodjaci().setModel(mtp);
    }

    private void addActionListener() {
        ppf.addBtnObrisiActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = ppf.getTblProizvodjaci().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(ppf, "Sistem ne može da obriše proizvođača.", "Greška", JOptionPane.ERROR_MESSAGE);
                } else {
                    ModelTabeleProizvodjaci mtp = (ModelTabeleProizvodjaci) ppf.getTblProizvodjaci().getModel();
                    Proizvodjac p = mtp.getLista().get(red);
                    try {
                        Komunikacija.getInstance().obrisiProizvodjaca(p);
                        JOptionPane.showMessageDialog(null, "Sistem je obrisao proizvođača.", "Uspešno", JOptionPane.INFORMATION_MESSAGE);
                        pripremiFormu();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(ppf, "Sistem ne može da obriše proizvođača.", "Greška", JOptionPane.ERROR_MESSAGE);
                    }
                }

            }
        });

        ppf.addBtnAzuirajActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = ppf.getTblProizvodjaci().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(ppf, "Sistem ne može da ažurira proizvođača.", "Greška", JOptionPane.ERROR_MESSAGE);
                } else {
                    ModelTabeleProizvodjaci mtp = (ModelTabeleProizvodjaci) ppf.getTblProizvodjaci().getModel();
                    Proizvodjac p = mtp.getLista().get(red);
                    GlavniKontroler.getInstance().dodajParametar("proizvodjac", p);
                    GlavniKontroler.getInstance().otvoriIzmeniProizvodjacaFormu();
                }

            }
        });

        ppf.addBtnPretraziActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ime = ppf.getTxtIme().getText().trim();
                String prezime = ppf.getTxtPrezime().getText().trim();
                Mesto mesto = (Mesto) ppf.getCmbMesto().getSelectedItem();

                ModelTabeleProizvodjaci mtp = (ModelTabeleProizvodjaci) ppf.getTblProizvodjaci().getModel();
                mtp.pretrazi(ime, prezime, mesto);
            }
        });

        ppf.addBtnResetujPretraguActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pripremiFormu();
            }
        });
    }

    public void osveziFormu() {
        pripremiFormu();
    }

    private void ucitajMesta() {
        List<Mesto> mesta = Komunikacija.getInstance().ucitajMesta();
        for (Mesto m : mesta) {
            ppf.getCmbMesto().addItem(m);
        }
        ppf.getCmbMesto().setSelectedItem(null);
    }

}
