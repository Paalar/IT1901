package Json;

import java.util.List;

public class Festival {

    String festival;
    List<Concert> konsert;
    List<Offer> tilbud;
    List<Result> resultat;

    public String getFestival() {
        return festival;
    }

    public List<Concert> getKonsert() {
        return konsert;
    }

    public List<Offer> getTilbud() {
        return tilbud;
    }

    public List<Result> getResultat() {
        return resultat;
    }
}
