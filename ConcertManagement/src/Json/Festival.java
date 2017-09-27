package Json;

import java.util.List;

public class Festival {

    String festival;
    String datoStart;
    String datoSlutt;
    List<Scene> scene;
    /*
    List<Offer> tilbud;
    List<Result> resultat;
    */

    public String getFestival() {
        return festival;
    }

    public String getDatoStart() {
        return datoStart;
    }

    public String getDatoSlutt() {
        return datoSlutt;
    }

    public List<Scene> getScene() {
        return scene;
    }

   /*
    public List<Offer> getTilbud() { return tilbud; }

    public List<Result> getResultat() {
        return resultat;
    }
    */
}
