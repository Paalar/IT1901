package groupFive;

import Json.Concert;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import util.Constants;
import javafx.scene.control.ListView;

import java.util.List;

import static util.Filter.*;

public class Bookingansvarlig_controller {
    @FXML
    AnchorPane rootPane;

    @FXML
    VBox vBoxBands;

    @FXML
    ListView listViewEarlierConcerts;

    @FXML
    Label labelBandInfo;

    public void initialize() {
        putItemsInLists();
        putBandInfoInLists("Lorde"); // Hardcoded Lorde fordi det var den første i listen.
    }

    public Button createButton(String name) {
        // Denne lager og returnerer en Button.
        final Button button = new Button(name);
        button.setId("arrScenes");
        button.setPrefSize(200,50);
        button.setOnMouseClicked(event -> {
            // Når du trykker på knappen så kjøres putBandInfoInLists med bandets navn som argument.
            putBandInfoInLists(name);
        });
        return button;
    }

    private void putItemsInLists() {
        for (String band : getAllBandsObservableList()) {
            Button btn = createButton(band);
            btn.setId("btn" + band);
            vBoxBands.getChildren().add(btn);
        }
    }

    private void putBandInfoInLists(String band) {
        String stringToPutInTextArea = band + ":\n";
        // Lager en string hvor jeg putter inn all informasjonen før jeg setter labelen sin tekst til hele stringen.

        int popularity = getPopularity(band);
        stringToPutInTextArea += "Populæritet: " + String.valueOf(popularity) + "%\n";

        String genre = getGenre(band);
        stringToPutInTextArea += "Sjanger: " + genre + "\n";

        int earlierSales = getSales(band);
        stringToPutInTextArea += "Tidligere salg: " + String.valueOf(earlierSales) + "%\n";

        labelBandInfo.setText(stringToPutInTextArea);

        listViewEarlierConcerts.setEditable(true);
        listViewEarlierConcerts.setItems(getConcertsAndScenesForBand(band));


    }

    @FXML
    private void goBack(){
        String fxmlFileName = "Main";
        Main main = new Main();
        main.changeView(rootPane, fxmlFileName);
    }

    @FXML
    private void goHome(){
        Main main = new Main();
        //Constants.emptyStack(); Jeg kommenterte ut linjen som ikke virker.
        main.changeView(rootPane, Constants.getHome());
    }
}
