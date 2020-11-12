package ehu.isad.controller.ui;

import java.net.URL;
import java.util.ResourceBundle;

import ehu.isad.Main;
import ehu.isad.controller.db.EurobisioaKud;
import ehu.isad.model.Top3;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Top3Kud {

    Main main;

    public void setMainApp(Main main) {
        this.main = main;
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;


    @FXML
    private Button btn_OK;

    @FXML
    private Label lbl_top1;

    @FXML
    private Label lbl_top2;

    @FXML
    private Label lbl_top3;

    @FXML
    private ImageView im_her1;

    @FXML
    private ImageView im_her2;

    @FXML
    private ImageView im_her3;

    @FXML
    void initialize() {
        this.datuakJarri(EurobisioaKud.getInstance().lortuTop3());
    }

    private void datuakJarri(Top3 irabazleak[]){ //gure botoa ez dugu hemen ikusiko. Hurrengoak botoa ematerakoan gure botoak sartuta egongo dira.
        Top3 top3;
        for(int i = 0;i < irabazleak.length;i++){
            top3 = irabazleak[i];
            /*if(i==0){
                lbl_top1.setText(top3.getIzena()+" - "+top3.getPuntuak()+" puntu");
                im_her1.setImage(this.irudiaLortu(top3.getIzena().toLowerCase()));
            }else if(i==1){
                lbl_top2.setText(top3.getIzena()+" - "+top3.getPuntuak()+" puntu");
                im_her2.setImage(this.irudiaLortu(top3.getIzena().toLowerCase()));
            }else if(i==2){
                lbl_top3.setText(top3.getIzena()+" - "+top3.getPuntuak()+" puntu");
                im_her3.setImage(this.irudiaLortu(top3.getIzena().toLowerCase()));
            }*/
            switch(i){
                case 0:
                    lbl_top1.setText(top3.getIzena()+" - "+top3.getPuntuak()+" puntu");
                    im_her1.setImage(this.irudiaLortu(top3.getIzena().toLowerCase()));
                    break;
                case 1:
                    lbl_top2.setText(top3.getIzena()+" - "+top3.getPuntuak()+" puntu");
                    im_her2.setImage(this.irudiaLortu(top3.getIzena().toLowerCase()));
                    break;
                case 2:
                    lbl_top3.setText(top3.getIzena()+" - "+top3.getPuntuak()+" puntu");
                    im_her3.setImage(this.irudiaLortu(top3.getIzena().toLowerCase()));
                    break;
            }
        }
    }

    private Image irudiaLortu(String herrialdeIzena) {
        Image image = null;
        image = new Image(getClass().getResourceAsStream("/Images/"+herrialdeIzena+".png"));
        return image;
    }

    public void OnClick(ActionEvent actionEvent) {
        System.exit(0);
    }
}