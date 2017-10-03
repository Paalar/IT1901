package Json;

import java.util.ArrayList;

public class Offer {

    String artist;
    String scene;
    String dato;
    int pris;
    ArrayList<String> listOfNeeds;

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

    public String getDato() {
        return dato;
    }

    public int getPris() {
        return pris;
    }

    public String getScene(){
        return scene;
    }

    public ArrayList<String> getNeeds(){
        return listOfNeeds;
    }
}
