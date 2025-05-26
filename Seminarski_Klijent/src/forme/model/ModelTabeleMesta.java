/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import domen.Mesto;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Bojana
 */
public class ModelTabeleMesta extends AbstractTableModel {

    List<Mesto> lista;
    String[] kolone = {"ID", "Naziv mesta"};

    public ModelTabeleMesta(List<Mesto> lista) {
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
        Mesto mesto = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return mesto.getIdMesto();
            case 1:
                return mesto.getNaziv();
            default:
                return "N/A";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public List<Mesto> getLista() {
        return lista;
    }

    public int pretrazi(String naziv) {
        List<Mesto> filtriranaLista = lista.stream()
                .filter(m -> (naziv == null || naziv.isEmpty() || m.getNaziv().toLowerCase().contains(naziv.toLowerCase())))
                .collect(Collectors.toList());

        this.lista = filtriranaLista;
        fireTableDataChanged();

        return filtriranaLista.size();
    }

}
