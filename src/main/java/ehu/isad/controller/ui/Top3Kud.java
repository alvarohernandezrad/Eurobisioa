package ehu.isad.controller.ui;

import java.net.URL;
import java.util.ResourceBundle;

import ehu.isad.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;

public class Top3Kud {

    private Main main;

    public void setMainApp(Main main){
        this.main = main;
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Text txt_top3;

    @FXML
    private TableView<?> tv_top3;

    @FXML
    private Button btn_OK;

    @FXML
    void OnClick(ActionEvent event) {
        //main.aplikazioaItxi();
        System.exit(0);
    }

    @FXML
    void initialize() {
        assert txt_top3 != null : "fx:id=\"txt_top3\" was not injected: check your FXML file 'Top3.fxml'.";
        assert tv_top3 != null : "fx:id=\"tv_top3\" was not injected: check your FXML file 'Top3.fxml'.";
        assert btn_OK != null : "fx:id=\"btn_OK\" was not injected: check your FXML file 'Top3.fxml'.";

    }
}
