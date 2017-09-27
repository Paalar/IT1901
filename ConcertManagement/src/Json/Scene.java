package Json;

import java.util.List;

public class Scene {

    String navn;
    int plasser;
    List<Concert> konsert;

    public String getNavn() {
        return navn;
    }

    public int getPlasser() {
        return plasser;
    }

    public List<Concert> getKonsert() {
        return konsert;
    }
}
