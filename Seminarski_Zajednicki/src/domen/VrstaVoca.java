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
public class VrstaVoca {
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
        return "VrstaVoca{" + "idVrstaVoca=" + idVrstaVoca + ", naziv=" + naziv + '}';
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
   
    
}
