package ehu.isad.model;

public class Ordezkaritza {
    private String bandera;
    private Herrialdea herrialdea;
    private String artista;
    private String abestia;
    private int puntuak;

    public Ordezkaritza(Herrialdea herrialde, String artista, String abestia) {
        this.herrialdea = herrialde;
        this.artista = artista;
        this.abestia = abestia;
        this.puntuak = 0;
    }

    public String getBandera(){
        return bandera;
    }

    public void setBandera(){
        this.bandera = bandera;
    }

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
