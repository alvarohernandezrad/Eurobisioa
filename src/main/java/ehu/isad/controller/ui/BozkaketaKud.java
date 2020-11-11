package ehu.isad.controller.ui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import ehu.isad.Main;
import ehu.isad.controller.db.EurobisioaKud;
import ehu.isad.model.Herrialdea;
import ehu.isad.model.Ordezkaritza;
import ehu.isad.utils.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.util.Callback;
import javafx.util.converter.IntegerStringConverter;

public class BozkaketaKud {

    private Main main;

    private String pasatzenDigutenHerrialdea;

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
    private ImageView image_logo;

    @FXML
    private ImageView image_bihotza;

    @FXML
    void OnClick(ActionEvent event) {
    List<Ordezkaritza> botoakOrdezkaritza = this.botoakJasoDitu(); //zein herrialdek jaso dituzten puntuak begiratuko du
        if(this.puntuak <= 5 && this.puntuak > 0 ){ //puntuak 5 baino gutxiago edo berdin eta 0 baino gehiago badira sartuko da (ondo legoke warning batzuk sartzea)
            for (int i = 0; i < botoakOrdezkaritza.size(); i++) {
                Ordezkaritza ordezkaritza = botoakOrdezkaritza.get(i);
                EurobisioaKud.getInstance().bozkaketaGorde(ordezkaritza.getHerrialdea().getIzena(), this.pasatzenDigutenHerrialdea, ordezkaritza.getPuntuak());
                EurobisioaKud.getInstance().puntuakSartu(ordezkaritza.getHerrialdea().getIzena(), ordezkaritza.getPuntuak());
            }
            this.puntuak = 0; //puntuak berriz 0-ra jarriko ditu hurrengo bozkaketarako
            main.top3Erakutsi();
        }
    }

    @FXML
    void initialize() {
        tv_Bozkatu.setEditable(true);
        col_herrialdea.setCellValueFactory(new PropertyValueFactory<>("Herrialdea"));
        col_artista.setCellValueFactory(new PropertyValueFactory<>("Artista"));
        col_abestia.setCellValueFactory(new PropertyValueFactory<>("Abestia"));
        col_puntuak.setCellValueFactory(new PropertyValueFactory<>("Puntuak"));

        col_puntuak.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        col_puntuak.setOnEditCommit(//puntuak sartzen dira
                t -> {
                    if (t.getNewValue() > t.getOldValue() && t.getNewValue() >= 0) { //puntuak eman
                        this.puntuak = this.puntuak + (t.getNewValue() - t.getOldValue());
                    } else if (t.getNewValue() < t.getOldValue() && t.getNewValue() > 0) { //kendu puntuak
                        this.puntuak = this.puntuak - (t.getOldValue() - t.getNewValue());
                    }
                    t.getTableView().getItems().get(t.getTablePosition().getRow()).setPuntuak(t.getNewValue());
                }
        );
        col_bandera.setCellValueFactory(new PropertyValueFactory<Ordezkaritza, Image>("bandera")); //lankide batena
        col_bandera.setCellFactory(p -> new TableCell<>(){
            public void updateItem(Image image, boolean empty) {
                if (image != null && !empty){
                    final ImageView imageview = new ImageView();
                    imageview.setFitHeight(15);
                    imageview.setFitWidth(20);
                    imageview.setImage(image);
                    setGraphic(imageview);
                    setAlignment(Pos.CENTER);
                }else{
                    setGraphic(null);
                    setText(null);
                }
            };
        }
        );
        this.taulaKargatu();

        //ezin eman botoa batek bere buruari
        Callback<TableColumn<Ordezkaritza, Integer>, TableCell<Ordezkaritza, Integer>> defaultTextFieldCellFactory
                = TextFieldTableCell.<Ordezkaritza, Integer>forTableColumn(new IntegerStringConverter());

        col_puntuak.setCellFactory(col -> {
            TableCell<Ordezkaritza, Integer> cell = defaultTextFieldCellFactory.call(col);

            cell.setOnMouseClicked(event -> {
                if (! cell.isEmpty()) {
                    if (cell.getTableView().getSelectionModel().getSelectedItem().getHerrialdea().getIzena().equals(this.pasatzenDigutenHerrialdea)) {
                        cell.setEditable(false);
                    }else {
                        cell.setEditable(true);
                    }
                }
            });

            return cell ;
        });
    }

    public void norkBozkatu(Herrialdea herrialdea){
        this.txt_banatu.setText(herrialdea.getIzena()+ "k horrela nahi ditu bere puntuak banatu:");
        this.bihotzarenIrudiaJarri(herrialdea.getBandera());
        this.taulaKargatu();
    }

    private void taulaKargatu(){
        ordezkaritzaGuztiak = EurobisioaKud.getInstance().ordezkaritzakLortu();
        ObservableList<Ordezkaritza> ordezkaritzak = FXCollections.observableArrayList(ordezkaritzaGuztiak);
        tv_Bozkatu.setItems(ordezkaritzak);
    }

    private void bihotzarenIrudiaJarri(String herrialdea){
        image_bihotza.setImage(this.irudiaLortu(herrialdea));
    }

    private Image irudiaLortu(String bandera) {
        Image image = null;
        image = new Image(getClass().getResourceAsStream("/Images/"+bandera+"Bihotza.png"));
        return image;
    }


    private List<Ordezkaritza> botoakJasoDitu(){ //taulako puntuen zutabean != 0 dutenak sartuko dira emaitzan
        List<Ordezkaritza> puntuatuakIzanDirenHerrialdeak = new ArrayList<>();
        for(int i=0; i < this.ordezkaritzaGuztiak.size(); i++){
            if(this.ordezkaritzaGuztiak.get(i).getPuntuak() != 0){
                puntuatuakIzanDirenHerrialdeak.add(this.ordezkaritzaGuztiak.get(i));
            }
        }
        return puntuatuakIzanDirenHerrialdeak;
    }

    public void herrialdeBozkatzailea(String unekoHerrialdea) { // zein izango da botoak emango dituena
        this.pasatzenDigutenHerrialdea = unekoHerrialdea;
    }
}
