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
public class VrstaVoca implements OpstiDomenskiObjekat{
    private int idVrstaVoca;
    private String naziv;

    public VrstaVoca() {
    }

    public VrstaVoca(int idVrstaVoca, String naziv) {
        this.idVrstaVoca = idVrstaVoca;
        this.naziv = naziv;
    }

    public int getIdVrstaVoca() {
        return idVrstaVoca;
    }

    public void setIdVrstaVoca(int idVrstaVoca) {
        this.idVrstaVoca = idVrstaVoca;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @Override
    public String toString() {
        return "ID: " + idVrstaVoca + "\nNaziv vrste: " + naziv;
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
        final VrstaVoca other = (VrstaVoca) obj;
        return Objects.equals(this.naziv, other.naziv);
    }

    @Override
    public String vratiNazivTabele() {
        return "vrsta_voca";
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<OpstiDomenskiObjekat> vrsteVoca = new ArrayList<>();
        
        while(rs.next()){
            int idVrstaVoca = rs.getInt("vrsta_voca.idVrstaVoca");
            String naziv = rs.getString("vrsta_voca.naziv");
            VrstaVoca vv = new VrstaVoca(idVrstaVoca, naziv);
            vrsteVoca.add(vv);
        }
        return vrsteVoca;
    }

    @Override
    public String vratiNaziveKolonaZaUbacivanje() {
        return "naziv";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'"+naziv+"'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "vrsta_voca.idVrstaVoca="+idVrstaVoca;
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "naziv='"+naziv+"'";
    }
   
}
