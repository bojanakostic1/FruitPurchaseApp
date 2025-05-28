/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domen.Sorta;
import forme.DodajSortuForma;
import forme.FormaMod;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import kontroler.GlavniKontroler;

/**
 *
 * @author Bojana
 */
public class DodajSortuController {

    private final DodajSortuForma dsf;

    public DodajSortuController(DodajSortuForma dsf) {
        this.dsf = dsf;
        addActionListener();
    }

    public void otvoriFormu(FormaMod mod) {
        pripremiFormu(mod);
        dsf.setVisible(true);
    }

    private void pripremiFormu(FormaMod mod) {
        switch (mod) {
            case DODAJ:
                dsf.getBtnAzuriraj().setVisible(false);
                dsf.getBtnDodaj().setVisible(true);
                dsf.getBtnDodaj().setEnabled(true);
                dsf.getTxtId().setText("Biće automatski dodeljen.");
                dsf.getTxtId().setEnabled(false);
                break;
            case IZMENI:
                dsf.getBtnDodaj().setVisible(false);
                dsf.getBtnAzuriraj().setVisible(true);
                dsf.getBtnAzuriraj().setEnabled(true);

                Sorta s = (Sorta) GlavniKontroler.getInstance().vratiParametar("sorta");
                dsf.getTxtId().setText(String.valueOf(s.getIdSorta()));
                dsf.getTxtId().setEnabled(false);
                dsf.getTxtNaziv().setText(s.getNaziv());
                dsf.getTxtKategorija().setText(String.valueOf(s.getKategorija()));
                dsf.getTxtCena().setText(String.valueOf(s.getCena()));
                break;
            default:
                throw new AssertionError();
        }
    }

    private void addActionListener() {
        dsf.dodajAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String naziv = dsf.getTxtNaziv().getText().trim();
                    int kategorija = Integer.parseInt(dsf.getTxtKategorija().getText().trim());
                    double cena = Double.parseDouble(dsf.getTxtCena().getText().trim());
                    //Da li ovde dodati proveru kategorije i cene?
                    Sorta sorta = new Sorta(-1, naziv, kategorija, cena);
                    JOptionPane.showMessageDialog(dsf, "Sistem je kreirao sortu.", "Obaveštenje", JOptionPane.INFORMATION_MESSAGE);
                    try {
                        Komunikacija.getInstance().dodajSortu(sorta);
                        JOptionPane.showMessageDialog(dsf, "Sistem je zapamtio sortu. " + sorta, "Obaveštenje", JOptionPane.INFORMATION_MESSAGE);
                        dsf.dispose();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(dsf, "Sistem ne može da zapamti sortu.", "Greška", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dsf, "Sistem ne može da kreira sortu.", "Greška", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        dsf.azurirajAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(dsf.getTxtId().getText());
                String naziv = dsf.getTxtNaziv().getText().trim();
                int kategorija = Integer.parseInt(dsf.getTxtKategorija().getText().trim());
                double cena = Double.parseDouble(dsf.getTxtCena().getText().trim());

                Sorta s = new Sorta(id, naziv, kategorija, cena);
                
                try {
                    Komunikacija.getInstance().azurirajSortu(s);
                    JOptionPane.showMessageDialog(dsf, "Sistem je zapamtio sortu.\n" + s.toString(), "Obaveštenje", JOptionPane.INFORMATION_MESSAGE);
                    dsf.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dsf, "Sistem ne može da zapamti sortu.", "Greška", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        dsf.otkaziAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dsf.dispose();
            }
        });
    }

}
