package ehu.isad.controller.ui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import ehu.isad.Main;
import ehu.isad.controller.db.EurobisioaKud;
import ehu.isad.model.Herrialdea;
import ehu.isad.model.Ordezkaritza;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.util.converter.IntegerStringConverter;

public class BozkaketaKud {

    private Main main;

    private Integer puntuak = 0;

    private List<Ordezkaritza> ordezkaritzaGuztiak;

    public void setMainApp(Main main){
        this.main = main;
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Ordezkaritza> tv_Bozkatu;

    @FXML
    private TableColumn<Ordezkaritza, Image> col_bandera;

    @FXML
    private TableColumn<Ordezkaritza, Herrialdea> col_herrialdea;

    @FXML
    private TableColumn<Ordezkaritza, String> col_artista;

    @FXML
    private TableColumn<Ordezkaritza, String> col_abestia;

    @FXML
    private TableColumn<Ordezkaritza, Integer> col_puntuak;

    @FXML
    private Button btn_gorde;

    @FXML
    private Text txt_banatu;

    @FXML
    void OnClick(ActionEvent event) {
        //List<Ordezkaritza>

        main.top3Erakutsi();
    }

    @FXML
    void initialize() {
        tv_Bozkatu.setEditable(true);
        col_herrialdea.setCellValueFactory(new PropertyValueFactory<>("Herrialdea"));
        col_artista.setCellValueFactory(new PropertyValueFactory<>("Artista"));
        col_abestia.setCellValueFactory(new PropertyValueFactory<>("Abestia"));
        col_puntuak.setCellValueFactory(new PropertyValueFactory<>("Puntuak"));



        col_puntuak.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        col_puntuak.setOnEditCommit(
                t -> {
                    if (t.getNewValue() > t.getOldValue() && t.getNewValue() >= 0) { //puntuak eman
                        this.puntuak = this.puntuak + (t.getNewValue() - t.getOldValue());
                    } else if (t.getNewValue() < t.getOldValue() && t.getNewValue() > 0) { //kendu puntuak
                        this.puntuak = this.puntuak - (t.getOldValue() - t.getNewValue());
                    }
                    t.getTableView().getItems().get(t.getTablePosition().getRow()).setPuntuak(t.getNewValue());
                }
        );
        this.taulaKargatu();
    }

    public void norkBozkatu(Herrialdea herrialdea){
        this.txt_banatu.setText(herrialdea.getIzena()+ "k bere puntuak banatuko ditu");
        this.taulaKargatu();
    }

    private void taulaKargatu(){
        ordezkaritzaGuztiak = EurobisioaKud.getInstance().ordezkaritzakLortu();
        ObservableList<Ordezkaritza> ordezkaritzak = FXCollections.observableArrayList(ordezkaritzaGuztiak);
        tv_Bozkatu.setItems(ordezkaritzak);
    }
}
