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
public class Proizvodjac implements OpstiDomenskiObjekat{
    private int idProizvodjac;
    private String ime;
    private String prezime;
    private String brojTelefona;
    private Mesto mesto;

    public Proizvodjac() {
    }

    public Proizvodjac(int idProizvodjac, String ime, String prezime, String brojTelefona, Mesto mesto) {
        this.idProizvodjac = idProizvodjac;
        this.ime = ime;
        this.prezime = prezime;
        this.brojTelefona = brojTelefona;
        this.mesto = mesto;
    }

    public int getIdProizvodjac() {
        return idProizvodjac;
    }

    public void setIdProizvodjac(int idProizvodjac) {
        this.idProizvodjac = idProizvodjac;
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

    public String getBrojTelefona() {
        return brojTelefona;
    }

    public void setBrojTelefona(String brojTelefona) {
        this.brojTelefona = brojTelefona;
    }

    public Mesto getMesto() {
        return mesto;
    }

    public void setMesto(Mesto mesto) {
        this.mesto = mesto;
    }

    @Override
    public String toString() {
        return "Proizvođač: " + ime + " " + prezime + ", "+mesto + ", br.tel: "+brojTelefona;
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
        final Proizvodjac other = (Proizvodjac) obj;
        if (this.idProizvodjac != other.idProizvodjac) {
            return false;
        }
        if (!Objects.equals(this.ime, other.ime)) {
            return false;
        }
        return Objects.equals(this.prezime, other.prezime);
    }

    @Override
    public String vratiNazivTabele() {
        return "proizvodjac";
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<OpstiDomenskiObjekat> proizvodjaci = new ArrayList<>();
        
        while(rs.next()){
            int idProizvodjac = rs.getInt("proizvodjac.idProizvodjac");
            String ime = rs.getString("proizvodjac.ime");
            String prezime = rs.getString("proizvodjac.prezime");
            String brojTelefona  = rs.getString("proizvodjac.brojTelefona");
            
            Mesto mesto = new Mesto();
            mesto.setIdMesto(rs.getInt("mesto.idMesto"));
            mesto.setNaziv(rs.getString("mesto.naziv"));
            Proizvodjac p = new Proizvodjac(idProizvodjac, ime, prezime, brojTelefona, mesto);
            proizvodjaci.add(p);
        }
        return proizvodjaci;
    }

    @Override
    public String vratiNaziveKolonaZaUbacivanje() {
        return "ime,prezime,brojTelefona,mesto";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'"+ime+"','"+prezime+"','"+brojTelefona+"',"+mesto.getIdMesto();
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "proizvodjac.idProizvodjac="+idProizvodjac;
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "ime='"+ime+"',prezime='"+prezime+"',brojTelefona='"+brojTelefona+"',mesto="+mesto.getIdMesto();
    }
}
