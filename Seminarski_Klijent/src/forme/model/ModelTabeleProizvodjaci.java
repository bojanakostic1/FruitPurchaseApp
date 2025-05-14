/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import domen.Mesto;
import domen.Proizvodjac;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Bojana
 */
public class ModelTabeleProizvodjaci extends AbstractTableModel{
    List<Proizvodjac> lista;
    String[] kolone = {"ID","Ime","Prezime","Broj telefona","Mesto"};

    public ModelTabeleProizvodjaci(List<Proizvodjac> lista) {
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
    public String getColumnName(int column) {
        return kolone[column];
    }

    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Proizvodjac proizvodjac = lista.get(rowIndex);
        switch(columnIndex){
            case 0: return proizvodjac.getIdProizvodjac();
            case 1: return proizvodjac.getIme();
            case 2: return proizvodjac.getPrezime();
            case 3: return proizvodjac.getBrojTelefona();
            case 4: return proizvodjac.getMesto().getNaziv();
            default: return "N/A";
        }
    }

    public List<Proizvodjac> getLista() {
        return lista;
    }

    public void pretrazi(String ime, String prezime, Mesto mesto) {
        List<Proizvodjac> filtriranaLista = lista.stream()
                .filter(p-> (ime == null || ime.isEmpty() || p.getIme().toLowerCase().contains(ime.toLowerCase())))
                .filter (p-> (prezime == null || prezime.isEmpty() || p.getPrezime().toLowerCase().contains(prezime.toLowerCase())))
                .filter(p-> (mesto==null || p.getMesto().equals(mesto)))
                .collect( Collectors.toList());
        
        this.lista = filtriranaLista;
        fireTableDataChanged();
    }
    
}
