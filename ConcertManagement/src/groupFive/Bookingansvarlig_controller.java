package groupFive;

import Json.Concert;
import Json.Festival;
import Json.JsonEncode;
import Json.Scene;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import util.Constants;
import util.Popup;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static util.Filter.*;

public class Bookingansvarlig_controller {
    @FXML
    private AnchorPane rootPane;

    @FXML
    private VBox vBoxBands;

    @FXML
    private VBox vBoxBandsTab2;

    @FXML
    private ListView listViewEarlierConcerts;

    @FXML
    private Label labelBandInfo;

    @FXML
    private TextField textFieldArtist;

    @FXML
    private TextField textFieldPris;

    @FXML
    private DatePicker datePicker;

    public void initialize() {
        putItemsInLists();
        putBandInfoInLists("Lorde"); // Hardcoded Lorde fordi det var den første i listen.
    }

    public Button createButton(String name, boolean tab1) {
        // Denne lager og returnerer en Button.
        final Button button = new Button(name);
        button.setId("arrScenes");
        button.setPrefSize(200,50);
        button.setOnMouseClicked(event -> {
            if (tab1) {
                // Når du trykker på knappen så kjøres putBandInfoInLists med bandets navn som argument.
                putBandInfoInLists(name);
            } else {
                textFieldArtist.setText(name);
            }
        });
        return button;
    }

    private void putItemsInLists() {
        //Denne legger til alle unike band i listen på første tab.
        for (String band : getAllBandsObservableList()) {
            Button btn = createButton(band, true);
            btn.setId("btn" + band);
            vBoxBands.getChildren().add(btn);
        }
        //Denne legger til alle band som var i uka 2015,2013 men ikke i 2017.

        List<String> artistsUka17 = new ArrayList<>();
        List<String> artistsOlder = new ArrayList<>();
        for (Festival f : Main.festivals) {
            for (Scene s : f.getScene()) {
                for (Concert c : s.getKonsert()) {
                    if (f.getFestival().equals("UKA 2017")) {
                        if (!artistsUka17.contains(c.getArtist())) {
                            artistsUka17.add(c.getArtist());
                        }
                    } else {
                        if (!artistsOlder.contains(c.getArtist())) {
                            artistsOlder.add(c.getArtist());
                        }
                    }
                }
            }
        }
        artistsOlder.removeAll(artistsUka17);
        for (String band : artistsOlder) {
            Button btn = createButton(band, false);
            btn.setId("btn" + band);
            vBoxBandsTab2.getChildren().add(btn);
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
    private void sendOffer() {
        // Jeg formaterer datoer til dag - måned - år.
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        try {
            String date = datePicker.getValue().format(formatter);
            String artist = textFieldArtist.getText();
            int pris = Integer.valueOf(textFieldPris.getText());
            JsonEncode.writeNewOffer(date, artist, pris);
        } catch (Exception e) {
            System.out.println("Velg dato.");
        }
        Popup.showPopup("Tilbud","Tilbud er nå sendt til godkjenning.", "");
        textFieldArtist.setText("");
        textFieldPris.setText("");
        // Fjerner informasjon i textfieldene etter den har blitt lagret.
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
