/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mariq.rasyid.latihanmf6;

import com.mariq.rasyid.latihanmf6.Model.Costumer;
import java.net.URL;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Blizzard
 */
public class Fxml_outputController implements Initializable {

    //Curency Formatter
    Locale Indonesia = new Locale("in", "ID");
    NumberFormat formater = NumberFormat.getCurrencyInstance(Indonesia);

    @FXML
    private Label Nama_Peminjamlbl;
    @FXML
    private Label Jenis_Bukulbl;
    @FXML
    private Label Tanggal_Pinjamlbl;
    @FXML
    private Label tglkembalilbl;
    @FXML
    private Label lamapinjamlbl;
    @FXML
    private Label bataspinjamlbl;
    @FXML
    private Label selisih_lbl;
    @FXML
    private Label hargadendalbl;
    @FXML
    private Label dendalbl;
    @FXML
    private Label bonuslbl;
    @FXML
    private Button closebtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void close_click(ActionEvent event) {
        closebtn.getScene().getWindow().hide();
    }

    public void getDenda(Costumer cmr) {
        String Jenis = null, Bonus = null;
        double denda_perhari = 0;
        long batas = 0;
        switch (cmr.getBook_Type()) {
            case 1: {
                Jenis = "Komik";
                Jenis_Bukulbl.setText(Jenis);
                batas = 3;
                bataspinjamlbl.setText(String.valueOf(batas));
                denda_perhari = 500;
                break;
            }
            case 2: {
                Jenis = "Majalah";
                Jenis_Bukulbl.setText(Jenis);
                batas = 2;
                bataspinjamlbl.setText(String.valueOf(batas));
                denda_perhari = 000;
                break;

            }
            case 3: {
                Jenis = "Tabloid";
                Jenis_Bukulbl.setText(Jenis);
                batas = 1;
                bataspinjamlbl.setText(String.valueOf(batas));
                denda_perhari = 700;
                break;
            }
            default: {
                Jenis = "";
                Jenis_Bukulbl.setText(Jenis);
                batas = 0;
                bataspinjamlbl.setText(String.valueOf(batas));
                denda_perhari = 0;
                break;
            }
        }

        //Nama Peminjam
        Nama_Peminjamlbl.setText(cmr.getName());

        long stgl = Math.round(1.0 * (cmr.getKembali().getTime()
                - cmr.getPinjam().getTime()) / (1000 * 60 * 60 * 24));
        long selisih = stgl - batas;

        //Lama Pinjam
        lamapinjamlbl.setText(String.valueOf(stgl));

        //Total Denda
        double Denda = denda_perhari * selisih;
        dendalbl.setText(formater.format(Denda));

        //Tanggal pinjam
        Tanggal_Pinjamlbl.setText(cmr.getPinjam().toString());

        //Tanggal Kembali
        tglkembalilbl.setText(cmr.getKembali().toString());

        //Harga Denda
        hargadendalbl.setText(formater.format(denda_perhari));

        //Selisih hari
        selisih_lbl.setText(String.valueOf(selisih));

        /*
        * Get Bonus
         */
        if (cmr.getHari() == "FRIDAY") {
            Bonus = "Buku Tulis";
            bonuslbl.setText(Bonus);
        } else {
            Bonus = "-";
            bonuslbl.setText(Bonus);
        }
    }
}
