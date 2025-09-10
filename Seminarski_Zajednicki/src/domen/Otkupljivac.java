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
public class Otkupljivac implements OpstiDomenskiObjekat {

    private int idOtkupljivac;
    private String ime;
    private String prezime;
    private String korisnickoIme;
    private String sifra;

    public Otkupljivac() {
    }

    public Otkupljivac(int idOtkupljivac, String ime, String prezime, String korisnickoIme, String sifra) {
        this.idOtkupljivac = idOtkupljivac;
        this.ime = ime;
        this.prezime = prezime;
        this.korisnickoIme = korisnickoIme;
        this.sifra = sifra;
    }

    public int getIdOtkupljivac() {
        return idOtkupljivac;
    }

    public void setIdOtkupljivac(int idOtkupljivac) {
        this.idOtkupljivac = idOtkupljivac;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getSifra() {
        return sifra;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    @Override
    public String toString() {
        return  ime + " " + prezime;
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
        final Otkupljivac other = (Otkupljivac) obj;
        if (!Objects.equals(this.korisnickoIme, other.korisnickoIme)) {
            return false;
        }
        return Objects.equals(this.sifra, other.sifra);
    }

    @Override
    public String vratiNazivTabele() {
        return "otkupljivac";
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<OpstiDomenskiObjekat> otkupljivaci = new ArrayList<>();

        while (rs.next()) {
            int idOtkupljivac = rs.getInt("otkupljivac.idOtkupljivac");
            String ime = rs.getString("otkupljivac.ime");
            String prezime = rs.getString("otkupljivac.prezime");
            String korisnickoIme = rs.getString("otkupljivac.korisnickoIme");
            String sifra = rs.getString("otkupljivac.sifra");
            Otkupljivac o = new Otkupljivac(idOtkupljivac, ime, prezime, korisnickoIme, sifra);
            otkupljivaci.add(o);
        }
        return otkupljivaci;
    }

    @Override
    public String vratiNaziveKolonaZaUbacivanje() {
        return "ime,prezime,korisnickoIme,sifra";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'" + ime + "','" + prezime + "','" + korisnickoIme + "','" + sifra + "'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "otkupljivac.idOtkupljivac=" + idOtkupljivac;
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "ime='" + ime + "',prezime='" + prezime + "',korisnickoIme='" + korisnickoIme + "',sifra='" + sifra + "'";
    }

    @Override
    public void postaviID(int generatedID) {
        idOtkupljivac = generatedID;
    }

}
