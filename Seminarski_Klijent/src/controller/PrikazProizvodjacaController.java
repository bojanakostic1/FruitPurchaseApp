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

        ppf.getCmbMesto().setSelectedItem(null);
        ppf.getTxtIme().setText("");
        ppf.getTxtPrezime().setText("");
    }

    private void addActionListener() {
        ppf.addBtnObrisiActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = ppf.getTblProizvodjaci().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(ppf, "Sistem ne može da nađe proizvođača.", "Greška", JOptionPane.ERROR_MESSAGE);
                } else {
                    ModelTabeleProizvodjaci mtp = (ModelTabeleProizvodjaci) ppf.getTblProizvodjaci().getModel();
                    Proizvodjac p = mtp.getLista().get(red);
                    JOptionPane.showMessageDialog(ppf, "Sistem je našao proizvođača.\n"+p.toString(), "Obaveštenje", JOptionPane.INFORMATION_MESSAGE);
                    try {
                        Komunikacija.getInstance().obrisiProizvodjaca(p);
                        JOptionPane.showMessageDialog(null, "Sistem je obrisao proizvođača.", "Obaveštenje", JOptionPane.INFORMATION_MESSAGE);
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
                    JOptionPane.showMessageDialog(ppf, "Sistem ne može da nađe proizvođača.", "Greška", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(ppf, "Sistem je našao proizvođača.", "Obaveštenje", JOptionPane.INFORMATION_MESSAGE);
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
                int brojRezultata = mtp.pretrazi(ime, prezime, mesto);
                if (brojRezultata == 0) {
                    JOptionPane.showMessageDialog(ppf, "Sistem ne može da nađe proizvođače po zadatim kriterijumima.", "Greška", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(ppf, "Sistem je našao proizvođače po zadatim kriterijumima.", "Obaveštenje", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        ppf.addBtnResetujPretraguActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pripremiFormu();
            }
        });

        ppf.addBtnDetaljiActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = ppf.getTblProizvodjaci().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(ppf, "Sistem ne može da nađe proizvođača.", "Greška", JOptionPane.ERROR_MESSAGE);
                } else {
                    ModelTabeleProizvodjaci mtp = (ModelTabeleProizvodjaci) ppf.getTblProizvodjaci().getModel();
                    Proizvodjac p = mtp.getLista().get(red);
                    JOptionPane.showMessageDialog(null, "Sistem je našao proizvođača.\n" + p.toString(), "Obaveštenje", JOptionPane.INFORMATION_MESSAGE);

                }
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
