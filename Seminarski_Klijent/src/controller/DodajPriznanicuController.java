/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import com.sun.source.tree.ParenthesizedTree;
import domen.Mesto;
import domen.Otkupljivac;
import domen.Priznanica;
import domen.Proizvodjac;
import domen.Sorta;
import domen.StavkaPriznanice;
import forme.DodajPriznanicuForma;
import forme.FormaMod;
import forme.model.ModelTabeleProizvodjaci;
import forme.model.ModelTabeleStavkePriznanice;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import kontroler.GlavniKontroler;

/**
 *
 * @author Bojana
 */
public class DodajPriznanicuController {

    private final DodajPriznanicuForma dpf;
    private Priznanica priznanica = new Priznanica();

    public DodajPriznanicuController(DodajPriznanicuForma dpf) {
        this.dpf = dpf;
        addActionListener();
    }

    public void otvoriFormu(FormaMod mod) {
        popuniComboBoxOtkupljivaci();
        popuniComboBoxMesto();
        popuniComboBoxSorte();
        popuniTabeluProizvodjaci();
        pripremiTabeluStavke();
        pripremiFormu(mod);
        dpf.setVisible(true);
    }

    public void addActionListener() {

        dpf.SacuvajPriznanicuAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodajPriznanicu(e);
            }

            private void dodajPriznanicu(ActionEvent e) {
                LocalDate datumIzdavanja = LocalDate.now();
                Otkupljivac o = (Otkupljivac) dpf.getCmbOtkupljivaci().getSelectedItem();

                priznanica.setDatumIzdavanja(datumIzdavanja);
                priznanica.setOtkupljivac(o);

                ModelTabeleStavkePriznanice mts = (ModelTabeleStavkePriznanice) dpf.getTblStavkePriznanice().getModel();
                List<StavkaPriznanice> stavke = mts.getLista();
                priznanica.setStavkePriznanice(stavke);
                priznanica.setUkupnaVrednost(mts.vratiUkupnoVrednostStavki());

                try {
                    JOptionPane.showMessageDialog(dpf, "Sistem je kreirao priznanicu.", "Obaveštenje", JOptionPane.INFORMATION_MESSAGE);
                    try {
                        priznanica = Komunikacija.getInstance().dodajPriznanicu(priznanica);
                        JOptionPane.showMessageDialog(dpf, "Sistem je zapamtio priznanicu.\n" + priznanica.toString(), "Obaveštenje", JOptionPane.INFORMATION_MESSAGE);
                        dpf.dispose();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(dpf, "Sistem ne može da zapamti priznanicu.", "Greška", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception exc) {
                    JOptionPane.showMessageDialog(dpf, "Sistem ne može da kreira priznanicu.", "Greška", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        dpf.addBtnPretraziActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ime = dpf.getTxtImeProizvodjaca().getText().trim();
                String prezime = dpf.getTxtPrezimeProizvodjaca().getText().trim();
                Mesto mesto = (Mesto) dpf.getCmbMesto().getSelectedItem();

                ModelTabeleProizvodjaci mtp = (ModelTabeleProizvodjaci) dpf.getTblProizvodjaci().getModel();
                int brojRezultata = mtp.pretrazi(ime, prezime, mesto);
                if (brojRezultata == 0) {
                    JOptionPane.showMessageDialog(dpf, "Sistem ne može da nađe proizvođače po zadatim kriterijumima.", "Greška", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(dpf, "Sistem je našao proizvođače po zadatim kriterijumima.", "Obaveštenje", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        dpf.addBtnResetujPretraguActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                popuniTabeluProizvodjaci();
            }
        });

        dpf.addBtnIzaberiProizvodjacaActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                odaberiProizvodjaca();
            }
        });

        dpf.addBtnDodajStavkuActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String jedinicaMere = dpf.getTxtJM().getText().trim();
                double kolicina = Double.parseDouble(dpf.getTxtKolicina().getText());
                Sorta sorta = (Sorta) dpf.getCmbSorte().getSelectedItem();
                double cena = 0;
                switch (sorta.getKategorija()) {
                    case 1:
                        cena = sorta.getCena();
                        break;
                    case 2:
                        cena = 0.75 * sorta.getCena();
                        break;
                    case 3:
                        cena = 0.5 * sorta.getCena();
                        break;
                    default:
                        throw new AssertionError();
                }
                double vrednost = kolicina * cena;
                int rb = dpf.getTblStavkePriznanice().getRowCount() + 1; 
                StavkaPriznanice sp = new StavkaPriznanice(priznanica, rb, jedinicaMere, kolicina, cena, vrednost, sorta);

                ModelTabeleStavkePriznanice mts = (ModelTabeleStavkePriznanice) dpf.getTblStavkePriznanice().getModel();
                mts.dodajStavku(sp);
                mts.osveziTabelu();
                dpf.getTxtUkupnaVrednost().setText(mts.vratiUkupnoVrednostStavki() + " din");

            }
        }
        );

        dpf.addBtnObrisiStavkuActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = dpf.getTblStavkePriznanice().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(dpf, "Odaberite koju stavku želite da obrišete iz tabele stavki.", "Greška", JOptionPane.ERROR_MESSAGE);
                    return;
                } else {
                    ModelTabeleStavkePriznanice mts = (ModelTabeleStavkePriznanice) dpf.getTblStavkePriznanice().getModel();
                    StavkaPriznanice sp = (StavkaPriznanice) mts.getLista().get(red);
                    mts.obrisiStavku(sp);
                    mts.osveziTabelu();
                    dpf.getTxtUkupnaVrednost().setText(mts.vratiUkupnoVrednostStavki() + " din");
                }

            }
        }
        );

        dpf.addBtnAzurirajPriznanicuActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(dpf.getTxtIdPriznanice().getText().trim());
                LocalDate datumIzdavanja = LocalDate.parse(dpf.getTxtDatumIzdavanja().getText());
                Otkupljivac o = (Otkupljivac) dpf.getCmbOtkupljivaci().getSelectedItem();
                Proizvodjac proizvodjac = priznanica.getProizvodjac();
                System.out.println("proizvodjac:" + proizvodjac);
                priznanica.setIdPriznanica(id);
                priznanica.setDatumIzdavanja(datumIzdavanja);
                priznanica.setOtkupljivac(o);
                if (priznanica.getProizvodjac() == null) {
                    priznanica.setProizvodjac(((Priznanica) GlavniKontroler.getInstance().vratiParametar("priznanica")).getProizvodjac());
                }
                ModelTabeleStavkePriznanice mts = (ModelTabeleStavkePriznanice) dpf.getTblStavkePriznanice().getModel();
                List<StavkaPriznanice> stavke = mts.getLista();
                priznanica.setStavkePriznanice(stavke);
                priznanica.setUkupnaVrednost(mts.vratiUkupnoVrednostStavki());
                try {
                    priznanica = Komunikacija.getInstance().azurirajPriznanicu(priznanica);
                    JOptionPane.showMessageDialog(dpf, "Sistem je zapamtio priznanicu.\n" + priznanica.toString(), "Obaveštenje", JOptionPane.INFORMATION_MESSAGE);
                    dpf.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dpf, "Sistem ne može da zapamti priznanicu.", "Greška", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public void odaberiProizvodjaca() {
        ModelTabeleProizvodjaci mtp = (ModelTabeleProizvodjaci) dpf.getTblProizvodjaci().getModel();
        int red = dpf.getTblProizvodjaci().getSelectedRow();
        if (red == -1) {
            JOptionPane.showMessageDialog(dpf, "Sistem ne može da nađe proizvođača.", "Greška", JOptionPane.ERROR_MESSAGE);
        } else {
            Proizvodjac proizvodjac = mtp.getLista().get(red);
            priznanica.setProizvodjac(proizvodjac);
            JOptionPane.showMessageDialog(dpf, "Sistem je našao proizvođača.\n", "Obaveštenje", JOptionPane.INFORMATION_MESSAGE);
            dpf.getTxtIzabraniProizvodjac().setText(proizvodjac.getIme() + " " + proizvodjac.getPrezime());
        }
    }

    private void popuniTabeluProizvodjaci() {
        List<Proizvodjac> lista = Komunikacija.getInstance().ucitajProizvodjace();
        ModelTabeleProizvodjaci mtp = new ModelTabeleProizvodjaci(lista);
        dpf.getTblProizvodjaci().setModel(mtp);

        dpf.getTxtImeProizvodjaca().setText("");
        dpf.getTxtPrezimeProizvodjaca().setText("");
        dpf.getCmbMesto().setSelectedItem(null);
    }

    private void pripremiTabeluStavke() {
        ModelTabeleStavkePriznanice mts = new ModelTabeleStavkePriznanice();
        dpf.getTblStavkePriznanice().setModel(mts);
    }

    private void popuniComboBoxMesto() {
        List<Mesto> lista = Komunikacija.getInstance().ucitajMesta();
        for (Mesto mesto : lista) {
            dpf.getCmbMesto().addItem(mesto);
        }
    }

    private void popuniComboBoxSorte() {
        List<Sorta> lista = Komunikacija.getInstance().ucitajSorte();
        for (Sorta sorta : lista) {
            dpf.getCmbSorte().addItem(sorta);
        }
    }

    private void popuniComboBoxOtkupljivaci() {
        List<Otkupljivac> lista = Komunikacija.getInstance().ucitajOtkupljivace();
        for (Otkupljivac otkupljivac : lista) {
            dpf.getCmbOtkupljivaci().addItem(otkupljivac);
        }
    }

    private void pripremiFormu(FormaMod mod) {
        switch (mod) {
            case DODAJ:
                dpf.setTitle("Dodavanje nove priznanice");
                dpf.getBtnAzurirajPriznanicu().setVisible(false);
                dpf.getBtnSacuvajPriznanicu().setVisible(true);
                dpf.getBtnSacuvajPriznanicu().setEnabled(true);
                dpf.getTxtIdPriznanice().setText("Biće automatski dodeljen.");
                dpf.getTxtDatumIzdavanja().setText("Biće automatski dodeljen.");
                dpf.getCmbOtkupljivaci().setSelectedItem(GlavniKontroler.getInstance().getUlogovani());
                dpf.getTxtIdPriznanice().setEnabled(false);
                dpf.getTxtDatumIzdavanja().setEnabled(false);
                dpf.getCmbMesto().setSelectedItem(null);
                dpf.getCmbSorte().setSelectedItem(null);
                dpf.getTxtUkupnaVrednost().setText(0 + " din");
                break;
            case IZMENI:
                dpf.setTitle("Izmena postojeće priznanice");
                dpf.getBtnSacuvajPriznanicu().setVisible(false);
                dpf.getBtnAzurirajPriznanicu().setVisible(true);
                dpf.getBtnAzurirajPriznanicu().setEnabled(true);

                Priznanica p = (Priznanica) GlavniKontroler.getInstance().vratiParametar("priznanica");
                dpf.getTxtIdPriznanice().setText(String.valueOf(p.getIdPriznanica()));
                dpf.getTxtIdPriznanice().setEnabled(false);
                String datumString = p.getDatumIzdavanja().format(DateTimeFormatter.ISO_LOCAL_DATE);
                dpf.getTxtDatumIzdavanja().setText(datumString);
                dpf.getCmbOtkupljivaci().setSelectedItem(p.getOtkupljivac());
                dpf.getTxtIzabraniProizvodjac().setText(p.getProizvodjac() + "");
                ModelTabeleStavkePriznanice mts = new ModelTabeleStavkePriznanice(p.getStavkePriznanice());
                dpf.getTblStavkePriznanice().setModel(mts);
                dpf.getTxtUkupnaVrednost().setText(p.getUkupnaVrednost() + "");
                break;
            default:
                throw new AssertionError();
        }
    }
}
