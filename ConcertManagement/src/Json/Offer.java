package Json;

public class Offer {

    private String artist;
    private String dato;
    private int pris;
    private String status;

    public String getStatus() { return status; };

    public void setStatus(String status) { this.status = status; };

    public String getArtist() {
        return artist;
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

    public void setPris(int pris) {
        this.pris = pris;
    }
}
