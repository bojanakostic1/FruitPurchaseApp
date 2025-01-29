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
public class StavkaPriznanice implements OpstiDomenskiObjekat{
    private Priznanica priznanica;
    private int rb;
    private String jedinicaMere;
    private double kolicina;
    private double cena;
    private double vrednost;
    private Sorta sorta;

    public StavkaPriznanice() {
    }

    public StavkaPriznanice(Priznanica priznanica, int rb, String jedinicaMere, double kolicina, double cena, double vrednost, Sorta sorta) {
        this.priznanica = priznanica;
        this.rb = rb;
        this.jedinicaMere = jedinicaMere;
        this.kolicina = kolicina;
        this.cena = cena;
        this.vrednost = vrednost;
        this.sorta = sorta;
    }

    public Priznanica getPriznanica() {
        return priznanica;
    }

    public void setPriznanica(Priznanica priznanica) {
        this.priznanica = priznanica;
    }

    public int getRb() {
        return rb;
    }

    public void setRb(int rb) {
        this.rb = rb;
    }

    public String getJedinicaMere() {
        return jedinicaMere;
    }

    public void setJedinicaMere(String jedinicaMere) {
        this.jedinicaMere = jedinicaMere;
    }

    public double getKolicina() {
        return kolicina;
    }

    public void setKolicina(double kolicina) {
        this.kolicina = kolicina;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public double getVrednost() {
        return vrednost;
    }

    public void setVrednost(double vrednost) {
        this.vrednost = vrednost;
    }

    public Sorta getSorta() {
        return sorta;
    }

    public void setSorta(Sorta sorta) {
        this.sorta = sorta;
    }

    @Override
    public String toString() {
        return "StavkaPriznanice{" + "priznanica=" + priznanica + ", rb=" + rb + ", jedinicaMere=" + jedinicaMere + ", kolicina=" + kolicina + ", cena=" + cena + ", vrednost=" + vrednost + ", sorta=" + sorta + '}';
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
        final StavkaPriznanice other = (StavkaPriznanice) obj;
        if (this.rb != other.rb) {
            return false;
        }
        return Objects.equals(this.priznanica, other.priznanica);
    }

    @Override
    public String vratiNazivTabele() {
        return "stavka_priznanice";
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<OpstiDomenskiObjekat> lista = new ArrayList<>();
       
        while(rs.next()){
            int idPriznanica = rs.getInt("priznanica");
            Priznanica priznanica = new Priznanica();
            priznanica.setIdPriznanica(idPriznanica);
            
            int rb = rs.getInt("rb");
            String jedinicaMere = rs.getString("jedinicaMere");
            double kolicina = rs.getDouble("kolicina");
            double cena = rs.getDouble("stavka_priznanice.cena");
            double vrednost = rs.getDouble("vrednost");
            
            int idSorta = rs.getInt("sorta");
            Sorta sorta = new Sorta();
            sorta.setIdSorta(idSorta);
            
            StavkaPriznanice stavka = new StavkaPriznanice(priznanica, rb, jedinicaMere, kolicina, cena, vrednost, sorta);
            lista.add(stavka);
        }    
        return lista;
    }

    @Override
    public String vratiNaziveKolonaZaUbacivanje() {
        return "priznanica,rb,jedinicaMere,kolicina,cena,vrednost,sorta";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return priznanica.getIdPriznanica()+","+rb+",'"+jedinicaMere+"',"+kolicina+","+cena+","+vrednost+","+sorta.getIdSorta();
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "stavka_priznanice.priznanica="+priznanica.getIdPriznanica()+" AND "+"stavka_priznanice.rb="+rb;
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "jedinicaMere='"+jedinicaMere+"',kolicina="+kolicina+",cena="+cena+",vrednost="+vrednost+",sorta="+sorta.getIdSorta();
    }
    
}
