package groupFive;

import Json.Concert;
import Json.Festival;
import Json.JsonEncode;
import Json.Scene;
import javafx.application.Platform;
import javafx.collections.FXCollections;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import util.Constants;
import util.Filter;
import util.Popup;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static util.Filter.*;

public class BookingansvarligController {
    @FXML
    private CheckBox remove2017;

    @FXML
    private TextField searchBand;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private VBox vBoxBands, vBoxBandsTab2, vBoxGenre;

    @FXML
    private ListView listViewEarlierConcerts, listviewPop, listViewArtist, listViewPublikumsantall, listViewScene;

    @FXML
    private Label labelBandInfo;

    @FXML
    private TextField textFieldArtist, textFieldPris;

    @FXML
    private DatePicker datePicker;

    @FXML
    ListView listViewTekniskeBehov;

    private boolean hasInitialized = false;

    public void initialize() {
        putItemsInLists();
        putBandInfoInLists("Lorde"); // Hardcoded Lorde fordi det var den første i listen.
        putGenreInList();
        hasInitialized = true;
        focusTabOne();
    }

    private void repeatFocus(Node node) {
        Platform.runLater(() -> {
            if (!node.isFocused()) {
                node.requestFocus();
            }
        });
    }

    @FXML
    private void focusTabOne() {
        if (hasInitialized) {
            repeatFocus(vBoxBands.getChildren().get(0));
            putBandInfoInLists("Lorde");
        }
    }

    @FXML
    private void focusTabThree() {
        repeatFocus(vBoxGenre.getChildren().get(0));
        putGenreInfoInLists("Pop");
    }

    public Button createButtonTab(String buttonInput, String tab) {
        // Denne lager og returnerer en Button.
        final Button button = new Button(buttonInput);
        button.setId("arrScenes");
        button.setPrefSize(200,20);
        button.setOnMouseClicked(event -> {
            if (tab.equals("tab1")) {
                // Når du trykker på knappen så kjøres putBandInfoInLists med bandets navn som argument.
                putBandInfoInLists(buttonInput);
            } else if (tab.equals("tab2")) {
                textFieldArtist.setText(buttonInput);
            }
            else if (tab.equals("tab3")) {
                putGenreInfoInLists(buttonInput);
            } else {
                textFieldArtist.setText(buttonInput);
            }
        });
        return button;
    }

    private void putGenreInList() {
        //Legger til alle sjangere i listen i tab3
        for (String genre : getAllGenresObservableList()) {
            Button btn = createButtonTab(genre, "tab3");
            vBoxGenre.getChildren().add(btn);
        }
    }

    private void putItemsInLists() {
        //Denne legger til alle unike band i listen på første tab.
        for (String band : getAllBandsObservableList()) {
            Button btn = createButtonTab(band,"tab1");
            vBoxBands.getChildren().add(btn);
        }
        repeatFocus(vBoxBands.getChildren().get(0));

        List<String> artistsOlder = Filter.getAllBandsFestivalsExcept("UKA 2017");
        for (String band : artistsOlder) {
            Button btn = createButtonTab(band, "tab2");
            vBoxBandsTab2.getChildren().add(btn);
        }
    }


    @FXML
    private void onKeyPressSearchBar() {
        String searchText = searchBand.getText();
        String festival = "all";
        if(remove2017.isSelected()){
            festival = "previous";
        }
        putBandsInList(festival, searchText);
    }

    @FXML
    private void checkRemove2017(){
        String searchText = "";
        if(!remove2017.isSelected()){
            putBandsInList("all", searchText);
        }
        else if(remove2017.isSelected()){
            putBandsInList("previous", searchText);
        }

    }


    private void putBandsInList(String festival, String searchText) {
        ObservableList<String> observableListToAdd = Filter.searchAllBandsObservableList(festival, searchText);
        //System.out.println(observableListToAdd); //for å sjekke om vi faktisk klarer å hente bandene
        vBoxBands.getChildren().clear(); // fjerner de gamle knappene før vi legger til de nye.
        for (String band : observableListToAdd) {
            Button btn = createButtonTab(band, "tab1");
            vBoxBands.getChildren().add(btn);
        } // en knapp per band.
    }

    private void putGenreInfoInLists(String genre) {
        List<String> artister = new ArrayList<>();
        List<String> festivaler = new ArrayList<>();
        List<String> publikumstall = new ArrayList<>();

        for (Festival f : Main.festivals) {
            if (!(f.getFestival().equals("UKA 2017"))) {
                for (Scene s : f.getScene()) {
                    for (Concert c : s.getKonsert()) {
                        if (c.getSjanger().equals(genre)) {
                            artister.add(c.getArtist());
                            festivaler.add(f.getFestival() + " : " + s.getNavn());
                            publikumstall.add(c.getBilletterSolgt() + " av " + s.getPlasser());
                        }
                    }
                }
            }
        }

        listViewArtist.setEditable(true);
        listViewScene.setEditable(true);
        listViewPublikumsantall.setEditable(true);

        listViewPublikumsantall.setItems(FXCollections.observableArrayList(publikumstall));
        listViewScene.setItems(FXCollections.observableArrayList(festivaler));
        listViewArtist.setItems(FXCollections.observableArrayList(artister));

    }

    private void putBandInfoInLists(String band) {
        ArrayList<String> bandInfo = new ArrayList<>();
        String stringToPutInTextArea = band;
        // Lager en string hvor jeg putter inn all informasjonen før jeg setter labelen sin tekst til hele stringen.

        int popularity = getPopularity(band);
        bandInfo.add("Populæritet: " + String.valueOf(popularity) + "%");
        String genre = getGenre(band);
        bandInfo.add("Sjanger: " + genre);
        int earlierSales = getSales(band);
        bandInfo.add("Tidligere salg av billetter: " + String.valueOf(earlierSales) + "%");

        labelBandInfo.setText(stringToPutInTextArea);
        listviewPop.setItems(FXCollections.observableArrayList(bandInfo));
        listViewEarlierConcerts.setEditable(true);
        listViewEarlierConcerts.setItems(getConcertsAndScenesForBand(band));

        listViewTekniskeBehov.setEditable(true);
        listViewTekniskeBehov.setItems(getTekniskeBehov(band));
    }

    @FXML
    private void sendOffer() {
        // Jeg formaterer datoer til dag - måned - år.
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        try {
            String date = datePicker.getValue().format(formatter);
            int[] dateSplitted = new int[3];
            int[] festivalStart = new int[3];
            int[] festivalSlutt = new int[3];
            for (int i = 0; i < 3; i++) {
                dateSplitted[i] = Integer.valueOf(date.split("-")[i]);
                festivalStart[i] = Integer.valueOf(Main.festivals.get(0).getDatoStart().split("-")[i]);
                festivalSlutt[i] = Integer.valueOf(Main.festivals.get(0).getDatoSlutt().split("-")[i]);
            }


            if (!(dateSplitted[2] == festivalStart[2] && dateSplitted[1] == festivalStart[1] && dateSplitted[0] >= festivalStart[0] && dateSplitted[0] <= festivalSlutt[0])) {
                Popup.showPopup("Tilbud","Dato er ikke innenfor festivalen.", "");
                return;
            }

            String artist = textFieldArtist.getText();
            int pris = Integer.valueOf(textFieldPris.getText());
            JsonEncode.writeNewOffer(date, artist, pris);
        } catch (Exception e) {
            //System.out.println("Velg dato.");
        }
        Popup.showPopup("Tilbud","Tilbud er nå sendt til godkjenning.", "");
        textFieldArtist.setText("");
        textFieldPris.setText("");
        // Fjerner informasjon i textfieldene etter den har blitt lagret.
    }


    @FXML
    private void goHome(){
        Main main = new Main();
        //Constants.emptyStack(); Jeg kommenterte ut linjen som ikke virker.
        main.changeView(rootPane, Constants.getHome());
    }
}
