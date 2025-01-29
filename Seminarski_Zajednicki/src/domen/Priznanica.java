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
public class Priznanica implements OpstiDomenskiObjekat{
    private int idPriznanica;
    private Date datumIzdavanja;
    private double ukupnaVrednost;
    private List<StavkaPriznanice> stavkePriznanice = new ArrayList<>();
    private Otkupljivac otkupljivac;
    private Proizvodjac proizvodjac;

    public Priznanica() {
    }

    public Priznanica(int idPriznanica, Date datumIzdavanja, double ukupnaVrednost, List<StavkaPriznanice> stavkePriznanice, Otkupljivac otkupljivac, Proizvodjac proizvodjac) {
        this.idPriznanica = idPriznanica;
        this.datumIzdavanja = datumIzdavanja;
        this.ukupnaVrednost = ukupnaVrednost;
        this.stavkePriznanice = stavkePriznanice;
        this.otkupljivac = otkupljivac;
        this.proizvodjac = proizvodjac;
    }

    public int getIdPriznanica() {
        return idPriznanica;
    }

    public void setIdPriznanica(int idPriznanica) {
        this.idPriznanica = idPriznanica;
    }

    public Date getDatumIzdavanja() {
        return datumIzdavanja;
    }

    public void setDatumIzdavanja(Date datumIzdavanja) {
        this.datumIzdavanja = datumIzdavanja;
    }

    public double getUkupnaVrednost() {
        return ukupnaVrednost;
    }

    public void setUkupnaVrednost(double ukupnaVrednost) {
        this.ukupnaVrednost = ukupnaVrednost;
    }

    public List<StavkaPriznanice> getStavkePriznanice() {
        return stavkePriznanice;
    }

    public void setStavkePriznanice(List<StavkaPriznanice> stavkePriznanice) {
        this.stavkePriznanice = stavkePriznanice;
    }

    public Otkupljivac getOtkupljivac() {
        return otkupljivac;
    }

    public void setOtkupljivac(Otkupljivac otkupljivac) {
        this.otkupljivac = otkupljivac;
    }

    public Proizvodjac getProizvodjac() {
        return proizvodjac;
    }

    public void setProizvodjac(Proizvodjac proizvodjac) {
        this.proizvodjac = proizvodjac;
    }

    @Override
    public String toString() {
        return "Priznanica{" + "idPriznanica=" + idPriznanica + ", datumIzdavanja=" + datumIzdavanja + ", ukupnaVrednost=" + ukupnaVrednost + ", stavkePriznanice=" + stavkePriznanice + ", otkupljivac=" + otkupljivac + ", proizvodjac=" + proizvodjac + '}';
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
        final Priznanica other = (Priznanica) obj;
        if (this.idPriznanica != other.idPriznanica) {
            return false;
        }
        return Objects.equals(this.datumIzdavanja, other.datumIzdavanja);
    }

    @Override
    public String vratiNazivTabele() {
        return "priznanica";
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<OpstiDomenskiObjekat> lista = new ArrayList<>();
        
        while(rs.next()){
            int idPriznanice = rs.getInt("priznanica.idPriznanica");
            Date datum = rs.getDate("priznanica.datumIzdavanja");
            double ukupnaVrednost = rs.getDouble("priznanica.ukupnaVrednost");
            
            
            int idOtkupljivac = rs.getInt("otkupljivac.idOtkupljivac");
            String ime = rs.getString("otkupljivac.ime");
            String prezime = rs.getString("otkupljivac.prezime");
            String korisnickoIme = rs.getString("otkupljivac.korisnickoIme");
            String sifra = rs.getString("otkupljivac.sifra");
            Otkupljivac o = new Otkupljivac(idOtkupljivac, ime, prezime, korisnickoIme, sifra);
            
            int idProizvodjac = rs.getInt("proizvodjac.idProizvodjac");
            String imeProizvodjac = rs.getString("proizvodjac.ime");
            String prezimeProizvodjac = rs.getString("proizvodjac.prezime");
            String brTel = rs.getString("proizvodjac.brojTelefona");
            
            int idMesto = rs.getInt("mesto.idMesto");
            String naziv = rs.getString("mesto.naziv");
            Mesto m = new Mesto(idMesto, naziv);
            
            Proizvodjac p = new Proizvodjac(idProizvodjac, imeProizvodjac, prezimeProizvodjac, brTel, m);
            
            Priznanica priznanica = new Priznanica(idPriznanice, datum, ukupnaVrednost, new ArrayList<StavkaPriznanice>(), o, p);
            
            int rb = rs.getInt("stavka_priznanice.rb");
            String jedMere = rs.getString("stavka_priznanice.jedinicaMere");
            double kol = rs.getDouble("stavka_priznanice.kolicina");
            double cena = rs.getDouble("stavka_priznanice.cena");
            
            int idSorta = rs.getInt("sorta.idSorta");
            String nazivSorte = rs.getString("sorta.naziv");
            int kategorija = rs.getInt("sorta.kategorija");
            double cenaSorte = rs.getDouble("sorta.cena");
            
            double vrednost = rs.getDouble("stavka_priznanice.vrednost");
            Sorta s = new Sorta(idSorta, nazivSorte, kategorija, cenaSorte);
            StavkaPriznanice sp = new StavkaPriznanice(priznanica, rb, jedMere, kol, cena, vrednost, s);
            
            priznanica.getStavkePriznanice().add(sp);
            lista.add(priznanica);
        }
        
        return lista;
    }

    @Override
    public String vratiNaziveKolonaZaUbacivanje() {
        return "datumIzdavanja,ukupnaVrednost,otkupljivac,proizvodjac";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'"+datumIzdavanja+"',"+ukupnaVrednost+","+otkupljivac.getIdOtkupljivac()+","+proizvodjac.getIdProizvodjac();
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "priznanica.idPriznanice="+idPriznanica;
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "datumIzdavanja='"+datumIzdavanja+"',ukupnaVrednost="+ukupnaVrednost+",otkupljivac="+otkupljivac.getIdOtkupljivac()+",proizvodjac="+proizvodjac.getIdProizvodjac();
    }
    
}
