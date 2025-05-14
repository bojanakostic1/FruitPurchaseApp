/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domen.Otkupljivac;
import forme.GlavnaForma;
import kontroler.GlavniKontroler;

/**
 *
 * @author Bojana
 */
public class GlavnaFormaController {
    private final GlavnaForma gf;

    public GlavnaFormaController(GlavnaForma gf) {
        this.gf = gf;
        addActionListeners();
    }

    private void addActionListeners() {
        //TODO
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void otvoriFormu() {
        Otkupljivac ulogovaniOtkupljivac = GlavniKontroler.getInstance().getUlogovani();
        String imePrezime = ulogovaniOtkupljivac.getIme()+" "+ulogovaniOtkupljivac.getPrezime()+"!";
        gf.setVisible(true);
        gf.getLblUlogovani().setText(imePrezime);
    }
    
    
}
