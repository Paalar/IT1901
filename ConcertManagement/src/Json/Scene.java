package Json;

import java.util.List;

public class Scene {

    private String navn;
    private int plasser;
    private List<Concert> konsert;

    public String getNavn() {
        return navn;
    }

    public int getPlasser() {
        return plasser;
    }

    public List<Concert> getKonsert() {
        return konsert;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public void setPlasser(int plasser) {
        this.plasser = plasser;
    }

    public void setKonsert(List<Concert> konsert) {
        this.konsert = konsert;
    }
}
