package ehu.isad.controller.ui;

import ehu.isad.Main;
import ehu.isad.controller.db.EurobisioaKud;
import ehu.isad.model.Herrialdea;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class HerrialdeaHautatuKud implements Initializable {

    private Main main;

    public void setMainApp(Main main){
        this.main = main;
    }


    @FXML
    private Text txt_aukeratu;

    @FXML
    private ComboBox<Herrialdea> cmbx_herrialdeak;

    @FXML
    private Button btn_OK;


    @FXML
    void OnClick(ActionEvent event) {
        if(cmbx_herrialdeak.getValue()!=null){
            Herrialdea herrialdea = cmbx_herrialdeak.getValue();
            String bandera = EurobisioaKud.getInstance().bozkatuDu(herrialdea.getIzena());
            if(bandera!=null){ //Jada bozkatu du
                main.erroreaBozkaketaErakutsi(herrialdea);

            }
            else{ //Oraindik bozkatu behar du
                System.out.println("Oraindik bozkatu behar du");//hemen bozkatzeko pantaila agertarazi beharko da
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Herrialdea> herrialdeakLista = EurobisioaKud.getInstance().lortuHerrialdeak();
        ObservableList<Herrialdea> herrialdeak = FXCollections.observableArrayList(herrialdeakLista);
        cmbx_herrialdeak.setItems(herrialdeak);
    }
}

