package Json;

import java.util.List;

public class Concert {

    String artist;
    String scene;

    public String getArtist() {
        return artist;
    }

    public String getScene() {
        return scene;
    }

    public List<LightTech> getLys() {
        return lys;
    }

    public List<SoundTech> getLyd() {
        return lyd;
    }

    public int getPris() {
        return pris;
    }

    public String getDato() {
        return dato;
    }

    public String getSjanger() {
        return sjanger;
    }

    List<LightTech> lys;
    List<SoundTech> lyd;
    int pris;
    String dato;
    String sjanger;

}
