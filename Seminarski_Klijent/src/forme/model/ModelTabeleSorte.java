/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import domen.Sorta;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import java.util.stream.Collectors;

/**
 *
 * @author Bojana
 */
public class ModelTabeleSorte extends AbstractTableModel {

    List<Sorta> lista;
    String[] kolone = {"ID", "Naziv", "Kategorija", "Cena"};

    public ModelTabeleSorte(List<Sorta> lista) {
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
        Sorta sorta = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return sorta.getIdSorta();
            case 1:
                return sorta.getNaziv();
            case 2:
                return sorta.getKategorija();
            case 3:
                return sorta.getCena();
            default:
                return "N/A";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public List<Sorta> getLista() {
        return lista;
    }

    public int pretrazi(String naziv, Integer kategorija, Double cena) {
         List<Sorta> filtriranaLista = lista.stream()
        .filter(s -> (naziv == null || naziv.isEmpty() || s.getNaziv().toLowerCase().contains(naziv.toLowerCase())))
        .filter(s -> (kategorija == null || s.getKategorija() == kategorija))
        .filter(s -> (cena == null || Math.abs(s.getCena() - cena) < 0.001))
        .collect(Collectors.toList());


        this.lista = filtriranaLista;
        fireTableDataChanged();

        return filtriranaLista.size();
    }

}
