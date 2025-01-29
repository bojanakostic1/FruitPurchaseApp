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
public class Mesto implements OpstiDomenskiObjekat{
    private int idMesto;
    private String naziv;

    public Mesto() {
    }

    public Mesto(int idMesto, String naziv) {
        this.idMesto = idMesto;
        this.naziv = naziv;
    }

    public int getIdMesto() {
        return idMesto;
    }

    public void setIdMesto(int idMesto) {
        this.idMesto = idMesto;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @Override
    public String toString() {
        return naziv;
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
        final Mesto other = (Mesto) obj;
        if (this.idMesto != other.idMesto) {
            return false;
        }
        return Objects.equals(this.naziv, other.naziv);
    }

    @Override
    public String vratiNazivTabele() {
        return "mesto";
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<OpstiDomenskiObjekat> mesta = new ArrayList<>();
        
        while(rs.next()){
            int idMesta = rs.getInt("mesto.idMesto");
            String nazivMesta = rs.getString("mesto.naziv");
            Mesto m = new Mesto(idMesta, nazivMesta);
            mesta.add(m);
        }
        return mesta;
    }

    @Override
    public String vratiNaziveKolonaZaUbacivanje() {
        return "naziv";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return naziv;
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "mesto.idMesto="+idMesto;
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "naziv='"+naziv+"'";
    }
    
}
