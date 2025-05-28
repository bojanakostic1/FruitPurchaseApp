/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package komunikacija;

import java.io.Serializable;

/**
 *
 * @author Bojana
 */
public enum Operacija implements Serializable{
    LOGIN, 
    UCITAJ_PROIZVODJACE, OBRISI_PROIZVODJACA, UCITAJ_MESTA, DODAJ_PROIZVODJACA, AZURIRAJ_PROIZVODJACA,
    OBRISI_MESTO, DODAJ_MESTO, AZURIRAJ_MESTO,
    UCITAJ_SORTE, OBRISI_SORTU, DODAJ_SORTU, AZURIRAJ_SORTU,
}
