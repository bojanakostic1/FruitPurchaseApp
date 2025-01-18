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
public class StavkaPriznanice {
    private Priznanica priznanica;
    private int rb;
    private String jedinicaMere;
    private double kolicina;
    private double cena;
    private double vrednost;
    private Sorta sorta;

    public StavkaPriznanice() {
    }

    public StavkaPriznanice(Priznanica priznanica, int rb, String jedinicaMere, double kolicina, double cena, double vrednost, Sorta sorta) {
        this.priznanica = priznanica;
        this.rb = rb;
        this.jedinicaMere = jedinicaMere;
        this.kolicina = kolicina;
        this.cena = cena;
        this.vrednost = vrednost;
        this.sorta = sorta;
    }

    public Priznanica getPriznanica() {
        return priznanica;
    }

    public void setPriznanica(Priznanica priznanica) {
        this.priznanica = priznanica;
    }

    public int getRb() {
        return rb;
    }

    public void setRb(int rb) {
        this.rb = rb;
    }

    public String getJedinicaMere() {
        return jedinicaMere;
    }

    public void setJedinicaMere(String jedinicaMere) {
        this.jedinicaMere = jedinicaMere;
    }

    public double getKolicina() {
        return kolicina;
    }

    public void setKolicina(double kolicina) {
        this.kolicina = kolicina;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public double getVrednost() {
        return vrednost;
    }

    public void setVrednost(double vrednost) {
        this.vrednost = vrednost;
    }

    public Sorta getSorta() {
        return sorta;
    }

    public void setSorta(Sorta sorta) {
        this.sorta = sorta;
    }

    @Override
    public String toString() {
        return "StavkaPriznanice{" + "priznanica=" + priznanica + ", rb=" + rb + ", jedinicaMere=" + jedinicaMere + ", kolicina=" + kolicina + ", cena=" + cena + ", vrednost=" + vrednost + ", sorta=" + sorta + '}';
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
        final StavkaPriznanice other = (StavkaPriznanice) obj;
        if (this.rb != other.rb) {
            return false;
        }
        return Objects.equals(this.priznanica, other.priznanica);
    }
    
    
}
