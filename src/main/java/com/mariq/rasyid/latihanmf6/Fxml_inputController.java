/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mariq.rasyid.latihanmf6;

import com.mariq.rasyid.latihanmf6.Model.Costumer;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Blizzard
 */
public class Fxml_inputController implements Initializable {

    //Model
    Costumer cmr = new Costumer();

    @FXML
    private Button orderbtn;
    @FXML
    private Button resetbtn;
    @FXML
    private TextField nametxt;
    @FXML
    private ComboBox<String> chbjenis;
    @FXML
    private DatePicker pinjamtanggal;
    @FXML
    private DatePicker tanggalkembali;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        chbjenis.setItems(FXCollections.observableArrayList(
                "--Pilih Jenis Buku--",
                "Komik",
                "Majah",
                "Tabloid"));
        chbjenis.getSelectionModel().select(0);
    }

    @FXML
    private void orderclick(ActionEvent event) {
        if (nametxt.getText().isBlank()) {
            do {
                TextInputDialog txt = new TextInputDialog();
                txt.setContentText("Masukan Nama Peminjam");
                txt.setTitle("Input Nama Peminjam");
                txt.showAndWait();
                nametxt.setText(txt.getResult());
            } while (nametxt.getText().isBlank());
        } else {
            cmr.setName(nametxt.getText());
            SimpleDateFormat tgl = new SimpleDateFormat("dd-MM-yyyy");
            try {
                cmr.setPinjam(tgl.parse(pinjamtanggal.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))
                ));
                cmr.setKembali(tgl.parse(tanggalkembali.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))
                ));
            } catch (ParseException e) {
                e.printStackTrace();

            }
            LocalDate localDate = tanggalkembali.getValue();

            cmr.setHari(localDate.getDayOfWeek().toString());
            cmr.setBook_Type(chbjenis.getSelectionModel().getSelectedIndex());
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mariq/rasyid/latihanmf6/fxml_output.fxml"));
                Parent root = (Parent) loader.load();

                Fxml_outputController ctrl
                        = (Fxml_outputController) loader.getController();
                ctrl.getDenda(cmr);

                Scene scene = new Scene(root);
                Stage stg = new Stage();
                stg.setResizable(false);
                stg.setIconified(false);
                stg.setTitle("Detail Invoice");
                stg.initModality(Modality.APPLICATION_MODAL);
                stg.setScene(scene);
                stg.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    @FXML
    private void reset_click(ActionEvent event) {
        Alert art = new Alert(Alert.AlertType.CONFIRMATION, "Apakah Anda Ingin reset data ini?", ButtonType.YES, ButtonType.NO);
        art.showAndWait();
        if (art.getResult() == ButtonType.YES) {
            nametxt.setText(null);
            chbjenis.getSelectionModel().select(0);
            pinjamtanggal.getEditor().clear();
            tanggalkembali.getEditor().clear();
            nametxt.isFocused();
        }
    }

}
