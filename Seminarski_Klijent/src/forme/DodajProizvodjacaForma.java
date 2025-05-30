/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package forme;

import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Bojana
 */
public class DodajProizvodjacaForma extends javax.swing.JFrame {

    /**
     * Creates new form DodajProizvodjacaForma
     */
    public DodajProizvodjacaForma() {
        initComponents();
    }

    public JComboBox getCmbMesta() {
        return cmbMesta;
    }

    public JTextField getTxtImeProizvodjaca() {
        return txtImeProizvodjaca;
    }

    public void setTxtImeProizvodjaca(JTextField txtImeProizvodjaca) {
        this.txtImeProizvodjaca = txtImeProizvodjaca;
    }

    public JTextField getTxtPrezimeProizvodjaca() {
        return txtPrezimeProizvodjaca;
    }

    public void setTxtPrezimeProizvodjaca(JTextField txtPrezimeProizvodjaca) {
        this.txtPrezimeProizvodjaca = txtPrezimeProizvodjaca;
    }

    public JTextField getTxtTelefonProizvodjaca() {
        return txtTelefonProizvodjaca;
    }

    public void setTxtTelefonProizvodjaca(JTextField txtTelefonProizvodjaca) {
        this.txtTelefonProizvodjaca = txtTelefonProizvodjaca;
    }

    public JButton getBtnAzuriraj() {
        return btnAzuriraj;
    }

    public void setBtnAzuriraj(JButton btnAzuriraj) {
        this.btnAzuriraj = btnAzuriraj;
    }

    public JButton getBtnDodaj() {
        return btnDodaj;
    }

    public void setBtnDodaj(JButton btnDodaj) {
        this.btnDodaj = btnDodaj;
    }

    public JTextField getTxtId() {
        return txtId;
    }

    public void setTxtId(JTextField txtId) {
        this.txtId = txtId;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblIme = new javax.swing.JLabel();
        txtImeProizvodjaca = new javax.swing.JTextField();
        lblPrezime = new javax.swing.JLabel();
        txtPrezimeProizvodjaca = new javax.swing.JTextField();
        lblTelefon = new javax.swing.JLabel();
        txtTelefonProizvodjaca = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        cmbMesta = new javax.swing.JComboBox();
        btnDodaj = new javax.swing.JButton();
        btnAzuriraj = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        btnOtkazi = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Dodavanje novog proizvođača");

        lblIme.setText("Ime:");

        lblPrezime.setText("Prezime:");

        lblTelefon.setText("Broj telefona:");

        jLabel1.setText("Mesto:");

        btnDodaj.setText("Dodaj");

        btnAzuriraj.setText("Ažuriraj");

        jLabel2.setText("ID:");

        btnOtkazi.setText("Otkaži");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPrezime, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblIme, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTelefon, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnOtkazi)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtPrezimeProizvodjaca)
                            .addComponent(txtImeProizvodjaca)
                            .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(txtTelefonProizvodjaca, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cmbMesta, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnAzuriraj)
                        .addGap(54, 54, 54)
                        .addComponent(btnDodaj)))
                .addGap(23, 23, 23))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblIme)
                    .addComponent(txtImeProizvodjaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPrezime)
                    .addComponent(txtPrezimeProizvodjaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTelefon)
                    .addComponent(txtTelefonProizvodjaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cmbMesta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 144, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDodaj)
                    .addComponent(btnAzuriraj)
                    .addComponent(btnOtkazi))
                .addGap(28, 28, 28))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAzuriraj;
    private javax.swing.JButton btnDodaj;
    private javax.swing.JButton btnOtkazi;
    private javax.swing.JComboBox cmbMesta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblIme;
    private javax.swing.JLabel lblPrezime;
    private javax.swing.JLabel lblTelefon;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtImeProizvodjaca;
    private javax.swing.JTextField txtPrezimeProizvodjaca;
    private javax.swing.JTextField txtTelefonProizvodjaca;
    // End of variables declaration//GEN-END:variables

    public void dodajAddActionListener(ActionListener actionListener) {
        btnDodaj.addActionListener(actionListener);
    }
    
    public void izmeniAddActionListener(ActionListener actionListener){
        btnAzuriraj.addActionListener(actionListener);
    }
    
    public void otkaziAddActionListener(ActionListener actionListener){
        btnOtkazi.addActionListener(actionListener);
    }
}
