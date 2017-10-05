package groupFive;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import util.Constants;
import util.Filter;

import Json.*;
import javafx.collections.FXCollections;
import Json.Scene;

import java.util.ArrayList;
import java.util.List;

public class Tekniker_controller {
    private final String UKA = "UKA 2017";
    @FXML
    private TextField textFieldSearch;

    @FXML
    private Label labelNavn, labelFestival;

    @FXML
    private VBox vBoxTekniker;

    @FXML
    private ListView listViewDato, listViewArtist, listViewScene, listViewOppgave;

    @FXML
    private AnchorPane rootPane;

    public void initialize() {
        putItemsInLists();
    }

    private void putItemsInLists() {
        // Denne er hardcodet til å bruke UKA fordi vi skal bare finne teknikkere for uka 2017.
        putTeknikersInList(UKA, "");
        putFestivalNameInLabel();
    }

    public Button createButton(String name) {
        // Denne lager og returnerer en Button.
        final Button button = new Button(name);
        button.setId("tekButts");
        button.setPrefSize(200,20);
        button.setOnMouseClicked(event -> {
        // Når du trykker på knappen så kjøres putKonsertInfoInLists med teknikerns navn som argument.
            putKonsertInfoInLists(name);
        });
        return button;
    }

    private void putTeknikersInList(String festival, String searchText) {
        ObservableList<String> observableListToAdd = Filter.getAllTeknikers(festival, searchText);
        vBoxTekniker.getChildren().clear(); // fjerner de gamle knappene før vi legger til de nye.
        for (String tekniker : observableListToAdd) {
            Button btn = createButton(tekniker);
            vBoxTekniker.getChildren().add(btn);
        } // en knapp per tekniker.
    }

    @FXML
    private void onKeyPressSearchBar() {
        String searchText = textFieldSearch.getText();
        putTeknikersInList(UKA, searchText);
    }


    private void putFestivalNameInLabel() {
        labelFestival.setText(UKA);
    }

    private void putKonsertInfoInLists(String arbeider) {
        List<String> datoer = new ArrayList<>();
        List<String> scener = new ArrayList<>();
        List<String> artister = new ArrayList<>();
        List<String> tekniker = new ArrayList<>();

        labelNavn.setText(arbeider);

        List<Scene> scenes = Filter.getAllScenes(UKA);
        for (Scene s : scenes) {
            for (Concert c : s.getKonsert()) {
                for (SoundTech st : c.getLyd()) {
                    if (st.getNavn().equals(arbeider)) {
                        datoer.add(c.getDato());
                        scener.add(s.getNavn());
                        artister.add(c.getArtist());
                        tekniker.add("Lydteknikker");
                    }
                }

                for (LightTech lt : c.getLys()) {
                    if (lt.getNavn().equals(arbeider)) {
                        datoer.add(c.getDato());
                        scener.add(s.getNavn());
                        artister.add(c.getArtist());
                        tekniker.add("Lysteknikker");
                    }
                }
            }
        }
        listViewDato.setEditable(true);
        listViewArtist.setEditable(true);
        listViewScene.setEditable(true);
        listViewOppgave.setEditable(true);

        listViewDato.setItems(FXCollections.observableArrayList(datoer));
        listViewArtist.setItems(FXCollections.observableArrayList(artister));
        listViewScene.setItems(FXCollections.observableArrayList(scener));
        listViewOppgave.setItems(FXCollections.observableArrayList(tekniker));
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
