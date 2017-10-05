package Json;

import java.util.ArrayList;
import java.util.List;

public class Concert {

    private String artist;
    private int billetterSolgt;
    private List<LightTech> lys;
    private List<SoundTech> lyd;
    private int billettPris;
    private String dato;
    private String sjanger;
    private int populæritet;
    private int salg;
    private int pris;
    private List<tekniskeBehov> tekniskeBehov;

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public int getBilletterSolgt() {
        return billetterSolgt;
    }

    public void setBilletterSolgt(int billetterSolgt) {
        this.billetterSolgt = billetterSolgt;
    }

    public List<LightTech> getLys() {
        return lys;
    }

    public void setLys(List<LightTech> lys) {
        this.lys = lys;
    }

    public List<SoundTech> getLyd() {
        return lyd;
    }

    public void setLyd(List<SoundTech> lyd) {
        this.lyd = lyd;
    }

    public int getBillettPris() {
        return billettPris;
    }

    public void setBillettPris(int billettPris) {
        this.billettPris = billettPris;
    }

    public String getDato() {
        return dato;
    }

    public void setDato(String dato) {
        this.dato = dato;
    }

    public String getSjanger() {
        return sjanger;
    }

    public void setSjanger(String sjanger) {
        this.sjanger = sjanger;
    }

    public int getPopulæritet() {
        return populæritet;
    }

    public void setPopulæritet(int populæritet) {
        this.populæritet = populæritet;
    }

    public int getSalg() {
        return salg;
    }

    public void setSalg(int salg) {
        this.salg = salg;
    }

    public int getPris() {
        return pris;
    }

    public void setPris(int pris) {
        this.pris = pris;
    }

    public List<Json.tekniskeBehov> getTekniskeBehov() {
        return tekniskeBehov;
    }

    public void setTekniskeBehov(List<Json.tekniskeBehov> tekniskeBehov) {
        this.tekniskeBehov = tekniskeBehov;
    }
}
