/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package domen;

import java.io.Serializable;
import java.util.List;
import java.sql.ResultSet;

/**
 *
 * @author Bojana
 */
public interface OpstiDomenskiObjekat extends Serializable{
    public String vratiNazivTabele();
    public String vratiNaziveKolonaZaUbacivanje();
    public String vratiVrednostiZaUbacivanje();
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws Exception;
    public String vratiPrimarniKljuc();
    public String vratiVrednostiZaIzmenu();
    public void postaviID(int generatedID);
}
