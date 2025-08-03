/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import domen.Otkupljivac;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import java.util.stream.Collectors;

/**
 *
 * @author Bojana
 */
public class ModelTabeleOtkupljivaci extends AbstractTableModel {

    List<Otkupljivac> lista;
    String[] kolone = {"ID", "Ime", "Prezime", "Korisničko ime"};

    public ModelTabeleOtkupljivaci(List<Otkupljivac> lista) {
        this.lista = lista;
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
        Otkupljivac otkupljivac = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return otkupljivac.getIdOtkupljivac();
            case 1:
                return otkupljivac.getIme();
            case 2:
                return otkupljivac.getPrezime();
            case 3:
                return otkupljivac.getKorisnickoIme();
            default:
                return "N/A";
        }
    }

    public List<Otkupljivac> getLista() {
        return lista;
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public int pretrazi(String ime, String prezime, String korisnickoIme) {
        List<Otkupljivac> filtriranaLista = lista.stream()
                .filter(o -> (ime == null || ime.isEmpty() || o.getIme().toLowerCase().contains(ime.toLowerCase())))
                .filter(o -> (prezime == null || prezime.isEmpty() || o.getPrezime().toLowerCase().contains(prezime.toLowerCase())))
                .filter(o -> (korisnickoIme == null || korisnickoIme.isEmpty() || o.getKorisnickoIme().toLowerCase().contains(korisnickoIme.toLowerCase())))
                .collect(Collectors.toList());

        this.lista = filtriranaLista;
        fireTableDataChanged();

        return filtriranaLista.size();
    }

}
