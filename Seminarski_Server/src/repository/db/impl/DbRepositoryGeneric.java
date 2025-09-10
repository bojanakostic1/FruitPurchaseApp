/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.db.impl;

import java.sql.PreparedStatement;
import domen.OpstiDomenskiObjekat;
import java.util.ArrayList;
import java.util.List;
import repository.db.DbRepository;
import java.sql.Statement;
import repository.db.DbConnectionFactory;
import java.sql.ResultSet;

/**
 *
 * @author Bojana
 */
public class DbRepositoryGeneric implements DbRepository<OpstiDomenskiObjekat> {

    @Override
    public List<OpstiDomenskiObjekat> getAll(OpstiDomenskiObjekat param, String uslov) throws Exception {
        List<OpstiDomenskiObjekat> lista = new ArrayList<>();
        String upit = "SELECT * FROM " + param.vratiNazivTabele();
        if (uslov != null) {
            upit += uslov;
        }
        System.out.println(upit);
        Statement st = DbConnectionFactory.getInstance().getConnection().createStatement();
        ResultSet rs = st.executeQuery(upit);
        lista = param.vratiListu(rs);
        rs.close();
        st.close();
        return lista;
    }

    @Override
    public void add(OpstiDomenskiObjekat param) throws Exception {
        String upit = "INSERT INTO " + param.vratiNazivTabele() + " (" + param.vratiNaziveKolonaZaUbacivanje() + ") " + " VALUES (" + param.vratiVrednostiZaUbacivanje() + ")";
        System.out.println(upit);
        PreparedStatement ps = DbConnectionFactory.getInstance().getConnection().prepareStatement(upit,Statement.RETURN_GENERATED_KEYS);
        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        if(rs.next()){
            int generatedID = rs.getInt(1);
            param.postaviID(generatedID);
            System.out.println("id u repository generic:"+generatedID);
        }
        rs.close();
        ps.close();
    }

    @Override
    public void edit(OpstiDomenskiObjekat param) throws Exception {
        String upit = "UPDATE " + param.vratiNazivTabele() + " SET " + param.vratiVrednostiZaIzmenu()+" WHERE "+param.vratiPrimarniKljuc();
        System.out.println(upit);
        Statement st = DbConnectionFactory.getInstance().getConnection().createStatement();
        st.executeUpdate(upit);
        st.close();
    }

    @Override
    public void delete(OpstiDomenskiObjekat param) throws Exception {
        String upit = "DELETE FROM "+param.vratiNazivTabele()+" WHERE "+param.vratiPrimarniKljuc();
        System.out.println(upit);
        Statement st = DbConnectionFactory.getInstance().getConnection().createStatement();
        st.executeUpdate(upit);
        st.close();
    }
}
