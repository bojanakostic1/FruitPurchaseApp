/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Bojana
 */
public class Sorta implements OpstiDomenskiObjekat{
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
        return naziv+" - " + kategorija + " (" + cena+" din)";
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

    @Override
    public String vratiNazivTabele() {
        return "sorta";
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<OpstiDomenskiObjekat> sorte = new ArrayList<>();
        
        while(rs.next()){
            int idSorta = rs.getInt("sorta.idSorta");
            String naziv = rs.getString("sorta.naziv");
            int kategorija = rs.getInt("sorta.kategorija");
            double cena = rs.getDouble("sorta.cena");
            Sorta s = new Sorta(idSorta, naziv, kategorija, cena);
            sorte.add(s);
        }
        return sorte;
    }

    @Override
    public String vratiNaziveKolonaZaUbacivanje() {
        return "naziv,kategorija,cena";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'"+naziv+"',"+kategorija+","+cena;
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "sorta.idSorta="+idSorta;
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "naziv='"+naziv+"',kategorija="+kategorija+",cena="+cena;    
    }

    @Override
    public void postaviID(int generatedID) {
        idSorta = generatedID;
    }
}
