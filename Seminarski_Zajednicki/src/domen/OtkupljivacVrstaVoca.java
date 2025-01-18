/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Bojana
 */
public class OtkupljivacVrstaVoca {
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
    
    
}
