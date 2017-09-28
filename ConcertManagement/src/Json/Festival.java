package Json;

import java.util.List;

public class Festival {

    private String festival;
    private String datoStart;
    private String datoSlutt;
    private List<Scene> scene;
    /*
    List<Offer> tilbud;
    List<Result> resultat;
    */

    public String getFestival() {
        return festival;
    }

    public void setFestival(String festival) {
        this.festival = festival;
    }

    public String getDatoStart() {
        return datoStart;
    }

    public void setDatoStart(String datoStart) {
        this.datoStart = datoStart;
    }

    public String getDatoSlutt() {
        return datoSlutt;
    }

    public void setDatoSlutt(String datoSlutt) {
        this.datoSlutt = datoSlutt;
    }

    public List<Scene> getScene() {
        return scene;
    }

    public void setScene(List<Scene> scene) {
        this.scene = scene;
    }

    /*
    public List<Offer> getTilbud() { return tilbud; }

    public List<Result> getResultat() {
        return resultat;
    }
    */
}
