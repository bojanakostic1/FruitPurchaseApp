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
                String ime = dpf.getTxtImeProizvodjaca().getText().trim();
                String prezime = dpf.getTxtPrezimeProizvodjaca().getText().trim();
                String telefon = dpf.getTxtTelefonProizvodjaca().getText().trim();
                Mesto mesto = (Mesto) dpf.getCmbMesta().getSelectedItem();
                try {
                    Proizvodjac p = new Proizvodjac(-1, ime, prezime, telefon, mesto);
                    JOptionPane.showMessageDialog(dpf, "Sistem je kreirao proizvođača.", "Obaveštenje", JOptionPane.INFORMATION_MESSAGE);
                    try {
                        Komunikacija.getInstance().dodajProizvodjaca(p);
                        JOptionPane.showMessageDialog(dpf, "Sistem je zapamtio proizvođača.\n"+p.toString(), "Obaveštenje", JOptionPane.INFORMATION_MESSAGE);
                        dpf.dispose();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(dpf, "Sistem ne može da zapamti proizvođača.", "Greška", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception exc) {
                    JOptionPane.showMessageDialog(dpf, "Sistem ne može da kreira proizvođača.", "Greška", JOptionPane.ERROR_MESSAGE);
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
                Proizvodjac p = new Proizvodjac(id, ime, prezime, telefon, mesto);
                try {
                    Komunikacija.getInstance().azurirajProizvodjaca(p);
                    JOptionPane.showMessageDialog(dpf, "Sistem je zapamtio proizvođača.\n"+p.toString(), "Obaveštenje", JOptionPane.INFORMATION_MESSAGE);
                    dpf.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dpf, "Sistem ne može da zapamti proizvođača.", "Greška", JOptionPane.ERROR_MESSAGE);
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
