package ehu.isad.model;

import ehu.isad.utils.Utils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Ordezkaritza {
    private Image bandera;
    private Herrialdea herrialdea;
    private String artista;
    private String abestia;
    private int puntuak;

    public Ordezkaritza(Herrialdea herrialde, String artista, String abestia) {
        this.herrialdea = herrialde;
        this.artista = artista;
        this.abestia = abestia;
        this.puntuak = 0;


        String irudiakErruta = Utils.lortuEzarpenak().getProperty("pathToImages")+herrialdea.getBandera()+".png";
        try {
            this.bandera = new Image(new FileInputStream(irudiakErruta));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Image getBandera(){
        return bandera;
    }

    public void setBandera(Image bandera) { this.bandera = bandera;   }

    public Herrialdea getHerrialdea() {
        return herrialdea;
    }

    public void setHerrialdea(Herrialdea herrialdea) {
        this.herrialdea = herrialdea;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public String getAbestia() {
        return abestia;
    }

    public void setAbestia(String abestia) {
        this.abestia = abestia;
    }

    public Integer getPuntuak() {
        return puntuak;
    }

    public void setPuntuak(Integer puntuak) {
        this.puntuak = puntuak;
    }


}
