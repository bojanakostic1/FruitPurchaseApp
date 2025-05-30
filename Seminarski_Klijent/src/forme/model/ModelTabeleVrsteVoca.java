/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import domen.VrstaVoca;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Bojana
 */
public class ModelTabeleVrsteVoca extends AbstractTableModel{
    List<VrstaVoca> lista;
    String kolone[] = {"ID","Naziv vrste voća"};

    public ModelTabeleVrsteVoca(List<VrstaVoca> lista) {
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
        VrstaVoca vrsta = lista.get(rowIndex);
        switch (columnIndex) {
            case 0: return vrsta.getIdVrstaVoca();
            case 1: return vrsta.getNaziv();
            default: return "N/A";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public List<VrstaVoca> getLista() {
        return lista;
    }

    public int pretrazi(String naziv) {
        List<VrstaVoca> filtriranaLista = lista.stream()
                .filter(v -> (naziv == null || naziv.isEmpty() || v.getNaziv().toLowerCase().contains(naziv.toLowerCase())))
                .collect(Collectors.toList());

        this.lista = filtriranaLista;
        fireTableDataChanged();

        return filtriranaLista.size();
    }
    
}
