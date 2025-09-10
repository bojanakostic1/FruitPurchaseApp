/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import domen.StavkaPriznanice;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Bojana
 */
public class ModelTabeleStavkePriznanice extends AbstractTableModel {

    private List<StavkaPriznanice> lista = new ArrayList<>();
    private String[] kolone = {"RB", "Jedinica mere", "Količina", "Cena", "Vrednost", "Naziv sorte"};

    public ModelTabeleStavkePriznanice(List<StavkaPriznanice> lista) {
        this.lista = lista;
    }

    public ModelTabeleStavkePriznanice() {
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public List<StavkaPriznanice> getLista() {
        return lista;
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
        StavkaPriznanice stavka = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return stavka.getRb();
            case 1:
                return stavka.getJedinicaMere();
            case 2:
                return stavka.getKolicina();
            case 3:
                return stavka.getCena();
            case 4:
                return Math.round((stavka.getVrednost()) * 100.0) / 100.0;
            case 5:
                return stavka.getSorta().getNaziv();
            default:
                return "N/A";
        }
    }

    public void osveziTabelu() {
        fireTableDataChanged();
    }

    public void dodajStavku(StavkaPriznanice sp) {
        lista.add(sp);
        System.out.println("sp:" + sp);
        fireTableDataChanged();
    }

    public double vratiUkupnoVrednostStavki() {
        double suma = 0;
        for (StavkaPriznanice stavkaPriznanice : lista) {
            suma += stavkaPriznanice.getVrednost();
        }
        return suma;
    }

    public void obrisiStavku(StavkaPriznanice sp) {
        lista.remove(sp);
        fireTableDataChanged();
    }
}
