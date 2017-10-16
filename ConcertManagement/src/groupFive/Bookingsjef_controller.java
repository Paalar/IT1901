package groupFive;

import Json.Concert;
import Json.Festival;
import Json.Scene;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import util.Filter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static util.Filter.getAllBandsObservableList;

public class Bookingsjef_controller {

    @FXML
    private AnchorPane rootPane;

    @FXML
    private VBox vBoxBands;

    @FXML
    private TextField textFieldSearchBar;

    @FXML
    private TextArea textAreaScene1, textAreaScene2, textAreaScene3;

    private List<TextArea> textAreas;

    @FXML
    private Label labelScene1, labelScene2, labelScene3, labelBillettPris;

    private List<Label> labels;
    private List<String> scenes = Arrays.asList("Dødens dal", "Storsalen", "Knaus");

    public void initialize() {
        putBandsInVbox("");
        textAreas = Arrays.asList(textAreaScene1, textAreaScene2, textAreaScene3);
        labels = Arrays.asList(labelScene1, labelScene2, labelScene3);
    }

    @FXML
    private void goBack(){
        String fxmlFileName = "Main";
        Main main = new Main();
        main.changeView(rootPane, fxmlFileName);
    }

    public Button createButton(String name) {
        // Denne lager og returnerer en Button.
        final Button button = new Button(name);
        button.setId("arrScenes");
        button.setPrefSize(200,20);
        button.setOnMouseClicked(event -> {
            generatePricesAndPutInTextAreas(name);
        });
        return button;
    }

    private void putBandsInVbox(String bandSearch) {
        vBoxBands.getChildren().clear();
        for (String band : Filter.getAllBandsFestivalsExcept("UKA 2017")) {
            if (band.toLowerCase().contains(bandSearch.toLowerCase())) {
                Button btn = createButton(band);
                vBoxBands.getChildren().add(btn);
            }
        }
    }

    private void generatePricesAndPutInTextAreas(String band) {
        labelBillettPris.setText("Forslag til billettpris : " + band);
        double popularity = (double)(Filter.getPopularity(band)) / 100.0;
        double sales = (double)(Filter.getSales(band)) / 100.0;
        int pris = Filter.getPrice(band);
        double prevConcertMultiplier = 0.0;
        for (Festival f : Main.festivals) {
            for (Scene s : f.getScene()) {
                for (Concert c : s.getKonsert()) {
                    if (c.getArtist().equals(band)) {
                        // Sist spilte konserten.
                        prevConcertMultiplier = 2.0 * ((float)(c.getBilletterSolgt()) / (float)(s.getPlasser()));
                        break;
                    }
                }
            }
        }
        for (int i = 0; i < 3; i++) {
            labels.get(i).setText(scenes.get(i));
            int plasser = Filter.getSceneSize(scenes.get(i));
            double multipliers = prevConcertMultiplier * sales * popularity;
            if (multipliers < 1.0) {
                multipliers += 1.0;
            }
            int generatedPrice =(int)(pris / plasser * multipliers);
            //int generatedPrice = (int)(prevConcertMultiplier * ((sales*popularity*10+ pris)/ plasser));
            String toAdd = "";
            toAdd += "Forslag billettpris kr " + Integer.toString(generatedPrice) + "\n";
            toAdd += "Artisten koster kr " + pris + "\n";
            toAdd += "Om du selger " + plasser + " billetter,\ntjener du kr " + String.valueOf((plasser * generatedPrice)- pris);
            textAreas.get(i).setEditable(true);
            textAreas.get(i).setText(toAdd);
        }

    }

    @FXML
    private void onKeyPressSearchBar() {
        putBandsInVbox(textFieldSearchBar.getText().toString());
    }
}
