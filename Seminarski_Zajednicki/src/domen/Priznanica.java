/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Bojana
 */
public class Priznanica {
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
    
    
    
    
    
}
