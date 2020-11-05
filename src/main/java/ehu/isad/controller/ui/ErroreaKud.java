package ehu.isad.controller.ui;

import ehu.isad.Main;
import ehu.isad.model.Herrialdea;
import ehu.isad.utils.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

public class ErroreaKud implements Initializable {

    private Main main;

    public void setMainApp(Main main){
        this.main = main;
    }


    @FXML
    private Text txt_errorea;

    @FXML
    private Button btn_OK;

    @FXML
    void OnClick(ActionEvent event) {
        main.herrialdeaHautatuErakutsi();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void erroreaErakutsi(Herrialdea herrialdea){
        this.txt_errorea.setText(herrialdea.getIzena()+ "k jada banatu ditu bere puntuak");
    }
}

