package groupFive;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import util.Constants;
import util.Filter;

import IO.ReadWriteConfig;
import Json.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import Json.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import util.Constants;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static util.Filter.getAllConcerts;
import static util.Filter.getAllFestivalsObservableList;

public class Tekniker_controller {
    private final String UKA = "UKA 2017";
    @FXML
    private TextField textFieldSearch;

    @FXML
    private Label labelNavn;

    @FXML
    private Label labelFestival;

    @FXML
    private ListView listViewTeknikere;

    @FXML
    private ListView listViewDato;

    @FXML
    private ListView listViewArtist;

    @FXML
    private ListView listViewScene;

    @FXML
    private ListView listViewOppgave;

    @FXML
    private AnchorPane rootPane;

    public void initialize() {
        putItemsInLists();
    }

    private void putItemsInLists() {
        putTeknikersInList(UKA, ""); // Tinus
        putFestivalNameInLabel(); // Sondre
    }

    private void putTeknikersInList(String festival, String searchText) {
        ObservableList<String> observableListToAdd = Filter.getAllTeknikers(festival, searchText);
        listViewTeknikere.setEditable(true);
        listViewTeknikere.setItems(observableListToAdd);
        listViewTeknikere.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    String itemClicked = listViewTeknikere.getSelectionModel().getSelectedItem().toString();
                    putKonsertInfoInLists(festival, itemClicked);
                } catch (Exception e) {
                    System.out.println("Du har ikke valgt en Arbeider.");
                }


            }
        });

    }

    @FXML
    private void onKeyPressSearchBar() {
        String searchText = textFieldSearch.getText();
        putTeknikersInList(UKA, searchText);
    }


    private void putFestivalNameInLabel() {
        labelFestival.setText(UKA);
    }

    private void putKonsertInfoInLists(String festival, String arbeider) {
        List<String> datoer = new ArrayList<>();
        List<String> scener = new ArrayList<>();
        List<String> artister = new ArrayList<>();
        List<String> tekniker = new ArrayList<>();

        labelNavn.setText(arbeider);

        List<Scene> scenes = Filter.getAllScenes(festival);
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
