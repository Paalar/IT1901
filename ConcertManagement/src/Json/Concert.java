package Json;

import java.util.List;

public class Concert {

    String artist;
    int billetterSolgt;
    List<LightTech> lys;
    List<SoundTech> lyd;
    int billettPris;
    String dato;
    String sjanger;
    int populæritet;
    int salg;
    int pris;
    List<tekniskeBehov> tekniskeBehov;

    public String getArtist() {
        return artist;
    }

    public int getBilletterSolgt() {
        return billetterSolgt;
    }

    public List<LightTech> getLys() {
        return lys;
    }

    public List<SoundTech> getLyd() {
        return lyd;
    }

    public int getBillettPris() {
        return billettPris;
    }

    public String getDato() {
        return dato;
    }

    public String getSjanger() {
        return sjanger;
    }

    public int getPopulæritet() {
        return populæritet;
    }

    public int getSalg() {
        return salg;
    }

    public int getPris() {
        return pris;
    }

    public List<Json.tekniskeBehov> getTekniskeBehov() {
        return tekniskeBehov;
    }
}
