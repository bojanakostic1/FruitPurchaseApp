package operacije.login;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import domen.OpstiDomenskiObjekat;
import domen.Otkupljivac;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Bojana
 */
public class PrijaviSO extends ApstraktnaGenerickaOperacija {

    private Otkupljivac otkupljivac;

    public Otkupljivac getOtkupljivac() {
        return otkupljivac;
    }

    public void setOtkupljivac(Otkupljivac Otkupljivac) {
        this.otkupljivac = Otkupljivac;
    }

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if (objekat == null || !(objekat instanceof Otkupljivac)) {
            throw new Exception("Korisničko ime i šifra nisu ispravni.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat) throws Exception {
        List<Otkupljivac> sviOtkupljivaci = broker.getAll((OpstiDomenskiObjekat) objekat, null);
        System.out.println("Klasa PrijaviSO " + sviOtkupljivaci);

        for (Otkupljivac o : sviOtkupljivaci) {
            if (o.equals(objekat)) {
                otkupljivac = o;
                return;
            }
        }
        otkupljivac = null;
    }

}
