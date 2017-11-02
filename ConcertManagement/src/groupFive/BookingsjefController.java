package groupFive;

import Json.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import util.Filter;
import java.util.Arrays;
import java.util.List;

public class BookingsjefController {

    @FXML
    private AnchorPane rootPane;

    @FXML
    private VBox vBoxBands, velgScene, vBoxDatoer;

    @FXML
    private TextField textFieldSearchBar;

    @FXML
    private TextArea textAreaScene1, textAreaScene2, textAreaScene3;

    private List<TextArea> textAreas;

    @FXML
    private Label labelScene1, labelScene2, labelScene3, labelBillettPris, totalKnaus, totalStor, totaltDod;

    @FXML
    private ListView listViewFestival, listViewArtist, listViewSjanger, listViewPlasser, listViewBillettpris, listViewArtistpris, listViewEconomy, listNotEvaluated, listAccepted, listDeclined;

    private List<Label> labels;
    private List<String> scenes = Arrays.asList("Dødens dal", "Storsalen", "Knaus");
    private boolean hasInitialized = false;
    private String offerBandSelected;

    public void initialize() {
        putBandsInVboxTab1("");
        putScenesInVboxTab2();
        textAreas = Arrays.asList(textAreaScene1, textAreaScene2, textAreaScene3);
        labels = Arrays.asList(labelScene1, labelScene2, labelScene3);
        generatePricesAndPutInTextAreas("Avenged Sevenfold");
        putOffersInLists();
        putConcertDatesInList();
        hasInitialized = true;
        focusTabOne();
    }

    private void repeatFocus(Node node) {
        Platform.runLater(() -> {
            node.requestFocus();
        });
    }


    @FXML
    private void focusTabOne() {
        if (hasInitialized) {
            repeatFocus(vBoxBands.getChildren().get(0));
            generatePricesAndPutInTextAreas("Avenged Sevenfold");
        }
    }

    @FXML
    private void foxusTabThree() {
        repeatFocus(velgScene.getChildren().get(0));
        generateReportAndPutInTable("Dødens dal");
    }

    @FXML
    private void goBack() {
        String fxmlFileName = "Main";
        Main main = new Main();
        main.changeView(rootPane, fxmlFileName);
    }

    public Button createButton(String name, int whichTab) {
        // Denne lager og returnerer en Button.
        final Button button = new Button(name);
        button.setId("arrScenes");
        button.setPrefSize(200, 20);
        button.setOnMouseClicked(event -> {
            if (whichTab == 1) {
                generatePricesAndPutInTextAreas(name);
            } else if (whichTab == 2) {
                generateReportAndPutInTable(name);
            }
        });
        return button;
    }

    private void generateReportAndPutInTable(String scene) {
        // Alle de forskjellige listene har hver sin ObservableList.
        ObservableList<String> festivals = FXCollections.observableArrayList();
        ObservableList<String> artists = FXCollections.observableArrayList();
        ObservableList<String> genres = FXCollections.observableArrayList();
        ObservableList<String> tickets = FXCollections.observableArrayList();
        ObservableList<Integer> ticketPrice = FXCollections.observableArrayList();
        ObservableList<Integer> artistPrice = FXCollections.observableArrayList();
        ObservableList<Integer> economyResult = FXCollections.observableArrayList();

        for (Festival f : Main.festivals) {
            if (!f.getFestival().equals("UKA 2017")) {
                for (Scene s : f.getScene()) {
                    if (s.getNavn().equals(scene)) {
                        // Bare eldre festival og på scene du har valgt.
                        for (Concert c : s.getKonsert()) {
                            festivals.add(f.getFestival());
                            artists.add(c.getArtist());
                            genres.add(c.getSjanger());
                            tickets.add(c.getBilletterSolgt() + "/" + String.valueOf(s.getPlasser()));
                            ticketPrice.add(c.getBillettPris());
                            artistPrice.add(c.getPris()); //TODO gjør om 1000000000 til 1 000 000 000
                            int economicResult = calculateEconomicResult(c.getBillettPris(), c.getBilletterSolgt(), c.getPris());
                            economyResult.add(economicResult);
                        }
                    }
                }
            }
        }
        listViewFestival.setItems(festivals);
        listViewArtist.setItems(artists);
        listViewSjanger.setItems(genres);
        listViewPlasser.setItems(tickets);
        listViewBillettpris.setItems(ticketPrice);
        listViewArtistpris.setItems(artistPrice);
        listViewEconomy.setItems(economyResult);
    }

    private int calculateEconomicResult(int ticketPrice, int ticketsSold, int artistPrice) {
        return (ticketPrice * ticketsSold) - artistPrice;
    }

    private ObservableList<Festival> getFestival() {
        ObservableList<Festival> festivals = FXCollections.observableArrayList();
        festivals.add(Main.festivals.get(1));
        return festivals;
    }

    private void putScenesInVboxTab2() {
        List<String> scenes = Filter.getAllScenesString("UKA 2017");
        for (String s : scenes) {
            Button btn = createButton(s, 2);
            velgScene.getChildren().add(btn);
        }
    }

    private void putBandsInVboxTab1(String bandSearch) {
        vBoxBands.getChildren().clear();
        for (String band : Filter.getAllBandsFestivalsExcept("UKA 2017")) {
            if (band.toLowerCase().contains(bandSearch.toLowerCase())) {
                Button btn = createButton(band, 1);
                vBoxBands.getChildren().add(btn);
            }
        }
    }

    private void generatePricesAndPutInTextAreas(String band) {
        labelBillettPris.setText("Forslag til billettpris : " + band);
        double popularity = (double) (Filter.getPopularity(band)) / 100.0;
        double sales = (double) (Filter.getSales(band)) / 100.0;
        int pris = Filter.getPrice(band);
        double prevConcertMultiplier = 0.0;
        for (Festival f : Main.festivals) {
            for (Scene s : f.getScene()) {
                for (Concert c : s.getKonsert()) {
                    if (c.getArtist().equals(band)) {
                        // Sist spilte konserten.
                        prevConcertMultiplier = 2.0 * ((float) (c.getBilletterSolgt()) / (float) (s.getPlasser()));
                        break;
                    }
                }
            }
        }
        int[] plasserArray = {7000, 1000, 200};
        for (int i = 0; i < 3; i++) {
            labels.get(i).setText(scenes.get(i));
            int plasser = plasserArray[i];
            double multipliers = prevConcertMultiplier * sales * popularity;
            if (multipliers < 1.0) {
                multipliers += 1.0;
            }
            int generatedPrice = (int) (pris / plasser * multipliers);
            //int generatedPrice = (int)(prevConcertMultiplier * ((sales*popularity*10+ pris)/ plasser));
            String toAdd = "";
            toAdd += "Forslag billettpris kr " + Integer.toString(generatedPrice) + "\n";
            toAdd += "Artisten koster kr " + pris + "\n";
            toAdd += "Om du selger " + plasser + " billetter,\ntjener du kr " + String.valueOf((plasser * generatedPrice) - pris);
            textAreas.get(i).setEditable(true);
            textAreas.get(i).setText(toAdd);
        }

    }

    @FXML
    private void onKeyPressSearchBar() {
        putBandsInVboxTab1(textFieldSearchBar.getText().toString());
    }


    private void putOffersInLists() {
        ObservableList<String> offersNotEval = FXCollections.observableArrayList();
        ObservableList<String> offersRejected = FXCollections.observableArrayList();
        ObservableList<String> offersAccepted = FXCollections.observableArrayList();
        for (Offer o : Main.offers) {
            if (o.getStatus().equals("ikke vurdert")) {
                offersNotEval.add(o.getArtist() + " - " + o.getDato() + " - " + o.getPris());
            } else if (o.getStatus().equals("ikke godkjent")) {
                offersRejected.add(o.getArtist() + " - " + o.getDato() + " - " + o.getPris());
            } else {
                offersAccepted.add(o.getArtist() + " - " + o.getDato() + " - " + o.getPris());
            }
        }
        listAccepted.setItems(offersAccepted);
        listDeclined.setItems(offersRejected);
        listNotEvaluated.setItems(offersNotEval);

        listNotEvaluated.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    String clicked = listNotEvaluated.getSelectionModel().getSelectedItem().toString();
                    clicked = clicked.split(" - ")[0];
                    clicked = clicked.replaceAll("[\\t\\n]+", "");
                    // fjerner alle tabs og new lines.
                    offerBandSelected = clicked;
                } catch (Exception e) {
                    System.out.println("Du har ingen i listen.");
                }
            }
        });
    }

    private void approveOrDecline(String status) {
        if (offerBandSelected != null) {
            for (Offer o : Main.offers) {
                if (o.getArtist().equals(offerBandSelected)) {
                    String dato = o.getDato();
                    String artist = o.getArtist();
                    int pris = o.getPris();
                    Main.offers.remove(o);
                    JsonEncode.acceptOrRejectOffer(dato, artist, pris, status);
                    putOffersInLists();
                    offerBandSelected = null;
                    break;
                }
            }
        }
    }

    @FXML
    private void approveOffer() {
        approveOrDecline("godkjent");
    }

    @FXML
    private void declineOffer() {
        approveOrDecline("ikke godkjent");
    }

    private void putConcertDatesInList() {
        int[] sumBusyScenes = {0, 0, 0};


        String dagFestivalStart = Main.festivals.get(0).getDatoStart();
        String dagFestivalSlutt = Main.festivals.get(0).getDatoSlutt();
        int dagStartInt = Integer.valueOf(dagFestivalStart.split("-")[0]);
        int dagSluttInt = Integer.valueOf(dagFestivalSlutt.split("-")[0]);

        for (int i = dagStartInt; i <= dagSluttInt; i++) {
            HBox hbox = new HBox();
            TextField tf = createTextField(i + "-10-2017", "white");
            hbox.getChildren().add(tf);
            String[] availability = Filter.getAvailability(i + "-10-2017");

            for (int j = 0; j < 3; j++) {
                TextField tfAvail;
                if (availability[j].equals("Ledig")) {
                    tfAvail = createTextField("Ledig", "lightgreen");
                } else {
                    tfAvail = createTextField(availability[j], "pink");
                    sumBusyScenes[j]++;
                }
                hbox.getChildren().add(tfAvail);
            }
            vBoxDatoer.getChildren().add(hbox);
        }
        totaltDod.setText("Totalt: " + String.valueOf(sumBusyScenes[0]) + "/" + String.valueOf(dagSluttInt - dagStartInt + 1));
        totalStor.setText("Totalt: " + String.valueOf(sumBusyScenes[1]) + "/" + String.valueOf(dagSluttInt - dagStartInt + 1));
        totalKnaus.setText("Totalt: " + String.valueOf(sumBusyScenes[2]) + "/" + String.valueOf(dagSluttInt - dagStartInt + 1));

    }

    public TextField createTextField(String name, String color) {
        final TextField textField = new TextField(name);
        textField.setId("");
        textField.setPrefSize(275, 20);
        textField.setFont(Font.font("Arial", 15));
        textField.setStyle("-fx-background-color:" + color);
        return textField;
    }
}