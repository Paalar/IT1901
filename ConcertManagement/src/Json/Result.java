package Json;

public class Result {

    private String artist;
    private int publikum;
    private int kostnad;
    private  int inntekt;
    private int scene;

    public String getArtist() {
        return artist;
    }

    public int getPublikum() {
        return publikum;
    }

    public int getKostnad() {
        return kostnad;
    }

    public int getInntekt() {
        return inntekt;
    }

    public int getScene() {
        return scene;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setPublikum(int publikum) {
        this.publikum = publikum;
    }

    public void setKostnad(int kostnad) {
        this.kostnad = kostnad;
    }

    public void setInntekt(int inntekt) {
        this.inntekt = inntekt;
    }

    public void setScene(int scene) {
        this.scene = scene;
    }
}
