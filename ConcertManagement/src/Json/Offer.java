package Json;

import java.util.ArrayList;

public class Offer {
    private String artist;
    private String scene;
    private String dato;
    private int pris;
    private ArrayList<String> listOfNeeds;

    private String status;

    public String getStatus() { return status; };

    public void setStatus(String status) { this.status = status; };

    public  Offer(){

    }

    public Offer(String artist, String scene, String dato, int pris, ArrayList<String> listOfNeeds) {
        this.artist = artist;
        this.scene = scene;
        this.dato = dato;
        this.pris = pris;
        this.listOfNeeds = listOfNeeds;
    }

    public String getArtist() {
        return this.artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getDato() {
        return dato;
    }

    public void setDato(String dato) {
        this.dato = dato;
    }

    public int getPris() {
        return pris;
    }

    public String getScene(){
        return scene;
    }

    public ArrayList<String> getNeeds() {
        return listOfNeeds;
    }

    public void setPris(int pris) {
        this.pris = pris;
    }
}
