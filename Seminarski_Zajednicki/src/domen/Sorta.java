/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.util.Objects;

/**
 *
 * @author Bojana
 */
public class Sorta {
    private int idSorta;
    private String naziv;
    private int kategorija;
    private double cena;

    public Sorta() {
    }

    public Sorta(int idSorta, String naziv, int kategorija, double cena) {
        this.idSorta = idSorta;
        this.naziv = naziv;
        this.kategorija = kategorija;
        this.cena = cena;
    }

    public int getIdSorta() {
        return idSorta;
    }

    public void setIdSorta(int idSorta) {
        this.idSorta = idSorta;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getKategorija() {
        return kategorija;
    }

    public void setKategorija(int kategorija) {
        this.kategorija = kategorija;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    @Override
    public String toString() {
        return "Sorta{" + "idSorta=" + idSorta + ", naziv=" + naziv + ", kategorija=" + kategorija + ", cena=" + cena + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Sorta other = (Sorta) obj;
        return Objects.equals(this.naziv, other.naziv);
    }
    
    
    
}
