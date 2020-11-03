package ehu.isad.controller.ui;

import ehu.isad.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class HasieraKud implements Initializable {

    private Main main;

    public void setMainApp(Main main) {
        this.main = main;
    }

    @FXML
    private Button btn_bozkatu;

    @FXML
    void OnClick(ActionEvent event) {
        this.main.herrialdeaHautatuErakutsi();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
