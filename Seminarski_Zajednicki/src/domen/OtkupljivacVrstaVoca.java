/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Bojana
 */
public class OtkupljivacVrstaVoca implements OpstiDomenskiObjekat{
    private Otkupljivac otkupljivac;
    private VrstaVoca vrstaVoca;
    private Date datumOtkupa;

    public OtkupljivacVrstaVoca() {
    }

    public OtkupljivacVrstaVoca(Otkupljivac otkupljivac, VrstaVoca vrstaVoca, Date datumOtkupa) {
        this.otkupljivac = otkupljivac;
        this.vrstaVoca = vrstaVoca;
        this.datumOtkupa = datumOtkupa;
    }

    public Otkupljivac getOtkupljivac() {
        return otkupljivac;
    }

    public void setOtkupljivac(Otkupljivac otkupljivac) {
        this.otkupljivac = otkupljivac;
    }

    public VrstaVoca getVrstaVoca() {
        return vrstaVoca;
    }

    public void setVrstaVoca(VrstaVoca vrstaVoca) {
        this.vrstaVoca = vrstaVoca;
    }

    public Date getDatumOtkupa() {
        return datumOtkupa;
    }

    public void setDatumOtkupa(Date datumOtkupa) {
        this.datumOtkupa = datumOtkupa;
    }

    @Override
    public String toString() {
        return "OtkuljivacVrstaVoca{" + "otkupljivac=" + otkupljivac + ", vrstaVoca=" + vrstaVoca + ", datumOtkupa=" + datumOtkupa + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final OtkupljivacVrstaVoca other = (OtkupljivacVrstaVoca) obj;
        if (!Objects.equals(this.otkupljivac, other.otkupljivac)) {
            return false;
        }
        if (!Objects.equals(this.vrstaVoca, other.vrstaVoca)) {
            return false;
        }
        return Objects.equals(this.datumOtkupa, other.datumOtkupa);
    }

    @Override
    public String vratiNazivTabele() {
        return "otkupljivac_vrsta_voca";
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<OpstiDomenskiObjekat> lista = new ArrayList<>();
        
        while(rs.next()){
            int idOtkupljivac = rs.getInt("otkupljivac.idOtkupljivac");
            String ime = rs.getString("otkupljivac.ime");
            String prezime = rs.getString("otkupljivac.prezime");
            String korisnickoIme = rs.getString("otkupljivac.korisnickoIme");
            String sifra = rs.getString("otkupljivac.sifra");
            Otkupljivac o = new Otkupljivac(idOtkupljivac, ime, prezime, korisnickoIme, sifra);
            
            int idVrstaVoca = rs.getInt("vrsta_voca.idVrstaVoca");
            String naziv = rs.getString("vrsta_voca.naziv");
            VrstaVoca vv = new VrstaVoca(idVrstaVoca, naziv);
            
            Date datumOtkupa = rs.getDate("otkupljivac_vrsta_voca.datumOtkupa");
            OtkupljivacVrstaVoca ovv = new OtkupljivacVrstaVoca(o, vv, datumOtkupa);
            lista.add(ovv);
        }
        return lista;
    }

    @Override
    public String vratiNaziveKolonaZaUbacivanje() {
        return "otkupljivac,vrstaVoca,datumOtkupa";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return otkupljivac.getIdOtkupljivac()+","+vrstaVoca.getIdVrstaVoca()+",'"+datumOtkupa+"'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "otkupljivac_vrsta_voca.otkupljivac="+otkupljivac.getIdOtkupljivac()+" AND "+"otkupljivac_vrsta_voca.vrsta_voca="+vrstaVoca.getIdVrstaVoca()+" AND "+"otkupljivac_vrsta_voca.datumOtkupa='"+datumOtkupa+"'";
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "otkupljivac="+otkupljivac.getIdOtkupljivac()+",vrsta_voca="+vrstaVoca.getIdVrstaVoca()+",datumOtkupa='"+datumOtkupa+"'";
    }

    @Override
    public void postaviID(int generatedID) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
