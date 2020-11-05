package ehu.isad.controller.db;

import ehu.isad.model.Herrialdea;
import ehu.isad.model.Ordezkaritza;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class EurobisioaKud {

    private static final EurobisioaKud instance = new EurobisioaKud();

    public static EurobisioaKud getInstance() {
        return instance;
    }

    private EurobisioaKud() { }

    public List<Herrialdea> lortuHerrialdeak(){
        String query = "SELECT izena, bandera FROM Herrialde"; //WHERE year(urtea)=year(now()) edo urtea=currentYear()
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        ResultSet rs = dbKudeatzaile.execSQL(query);

        List<Herrialdea> emaitza = new ArrayList<>();
        try {
            while (rs.next()) {
                String izena = rs.getString("izena");
                String bandera = rs.getString("bandera");
                System.out.println(izena + ":" + bandera);
                emaitza.add(new Herrialdea(izena,bandera));
            }
        } catch(SQLException throwables){
            throwables.printStackTrace();
        }
        return emaitza;

    }

    public String bozkatuDu(String herrialdea){
        String query = "SELECT h.bandera bandera FROM Herrialde h, Bozkaketa b WHERE izena='"+herrialdea+"' AND b.bozkatuDu=h.izena AND b.urtea=(SELECT strftime('%Y','now'))";
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        ResultSet rs = dbKudeatzaile.execSQL(query);

        try {
            if(rs.next()){
                return rs.getString("bandera");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public List<Ordezkaritza> ordezkaritzakLortu(){
        List<Ordezkaritza> ordezkaritzak = new ArrayList<>();
        String query = "SELECT bandera, herrialdea, artista, abestia FROM Herrialde, Ordezkaritza WHERE izena=herrialdea AND urtea=(SELECT strftime('%Y','now')) ORDER BY herrialdea ASC";
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        ResultSet rs = dbKudeatzaile.execSQL(query);

        try{
            while(rs.next()){
                String herrialdea = rs.getString("herrialdea");
                String bandera = rs.getString("bandera");
                String artista = rs.getString("artista");
                String abestia = rs.getString("abestia");
                ordezkaritzak.add(new Ordezkaritza((new Herrialdea(herrialdea,bandera)),artista,abestia));
            }
        }catch(SQLException throwables){
            throwables.printStackTrace();
        }
        return ordezkaritzak;
    }


}
