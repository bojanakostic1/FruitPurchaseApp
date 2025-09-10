/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import domen.Otkupljivac;
import domen.Priznanica;
import domen.Proizvodjac;
import domen.Sorta;
import domen.StavkaPriznanice;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Bojana
 */
public class ModelTabelePriznanice extends AbstractTableModel {

    private List<Priznanica> lista;
    private String[] kolone = {"ID", "Datum izdavanja", "Otkupljivač", "Proizvođač", "Ukupna vrednost"};
    private List<StavkaPriznanice> stavkePriznanice = new ArrayList<>();

    public ModelTabelePriznanice(List<Priznanica> lista) {
        this.lista = lista;
    }

    public List<Priznanica> getLista() {
        return lista;
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Priznanica p = lista.get(rowIndex);
        stavkePriznanice = p.getStavkePriznanice();
        switch (columnIndex) {
            case 0:
                return p.getIdPriznanica();
            case 1:
                return p.getDatumIzdavanja();
            case 2:
                return p.getOtkupljivac().getIme() + " " + p.getOtkupljivac().getPrezime();
            case 3:
                return p.getProizvodjac().getIme() + " " + p.getProizvodjac().getPrezime();
            case 4: {
                double suma = 0;
                for (StavkaPriznanice stavka : stavkePriznanice) {
                    suma += stavka.getVrednost();
                }
                return suma;
            }
            default:
                return "N/A";
        }
    }

    public List<StavkaPriznanice> getStavkePriznanice() {
        return stavkePriznanice;
    }

    public int pretrazi(int id, LocalDate datum, Otkupljivac otkupljivac, Proizvodjac proizvodjac, Sorta sorta) {
        List<Priznanica> filtriranaLista = lista.stream()
                .filter(p -> id == -1 || id == p.getIdPriznanica())
                .filter(p -> datum == null || datum.isEqual(p.getDatumIzdavanja()))
                .filter(p -> otkupljivac == null || p.getOtkupljivac().equals(otkupljivac))
                .filter(p -> proizvodjac == null || p.getProizvodjac().equals(proizvodjac))
                .filter(p -> sorta == null || p.getStavkePriznanice().stream().anyMatch(s -> s.getSorta().equals(sorta)))
                .collect(Collectors.toList());

        this.lista = filtriranaLista;
        fireTableDataChanged();

        return filtriranaLista.size();
    }

}
