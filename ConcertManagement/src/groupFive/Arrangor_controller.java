package groupFive;

import IO.ReadWriteConfig;
import Json.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import util.Constants;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static util.Filter.getAllFestivalsObservableList;

public class Arrangor_controller {
    @FXML
    private ScrollPane scrollPane;

    @FXML
    private ChoiceBox choiceBoxFestivals;

    @FXML
    private ChoiceBox choiceBoxScenes;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Label labelSoundTech;

    @FXML
    private Label labelLightTech;

    private int festivalSelected, sceneSelected = 0;

    @FXML
    public void initialize() {
        addItemsToList();
    }

    private void createChoiceBoxListener() {
        // Endrer festivalSelected når du velger en ny festival i choicebox.
        choiceBoxFestivals.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                festivalSelected = (Integer) newValue;
                changedFestOrScene();
            }
        });
    }

    private void createSceneBoxListener() {
        choiceBoxScenes.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                sceneSelected = (Integer) newValue;
                changedFestOrScene();
            }
        });
    }

    private void changedFestOrScene() {
        // Disse strengene er hvilken du har valgt i choiceboxen.
        String festival = choiceBoxFestivals.getItems().get(festivalSelected).toString();
        String scene = choiceBoxScenes.getItems().get(sceneSelected).toString();

        putSceneNamesInTextBox(festival, scene);
    }

    private void addItemsToList() {
        // Denne putter bare default verdier inn i alle knapper lister etc.
        putFestivalsInChoiceBox();
        putScenesInChoiceBox(Main.festivals.get(0).getFestival());
        createChoiceBoxListener();
        createSceneBoxListener();
        putSceneNamesInTextBox(Main.festivals.get(0).getFestival(), Main.festivals.get(0).getScene().get(0).getNavn());
    }

    private void putScenesInChoiceBox(String festival) {
        ArrayList<String> sceneNames = new ArrayList<>();

        for (Festival f : Main.festivals) {
            if (f.getFestival().equals(festival)) {
                for (int i = 0; i < f.getScene().size(); i++) {
                    sceneNames.add(f.getScene().get(i).getNavn());
                }
            }
        }
        ObservableList<String> choiceScenes = FXCollections.observableArrayList(sceneNames);
        choiceBoxScenes.setItems(choiceScenes);
        choiceBoxScenes.getSelectionModel().selectFirst();
    }

    private void putFestivalsInChoiceBox() {
        ObservableList<String> observableListToAdd = getAllFestivalsObservableList(Main.festivals);
        choiceBoxFestivals.setItems(observableListToAdd);
        choiceBoxFestivals.getSelectionModel().selectFirst();
        // Denne linjen gjør bare at det første itemet i listen blir vist og selected.
    }

    private void putSceneNamesInTextBox(String festival, String scene) {
        Main_controller mc = new Main_controller();
        VBox vbox = new VBox();

        for (Festival f : Main.festivals) {
            if (f.getFestival().equals(festival)) {
                for (int i = 0; i < f.getScene().size(); i++) {
                    if(scene.equals(f.getScene().get(i).getNavn())) {
                        List<Concert> concerts = f.getScene().get(i).getKonsert();
                        for (int n = 0; n < concerts.size(); n++) {
                            Button btn = createButton(concerts.get(n).getArtist());
                            btn.setId("arrScenes");
                            vbox.getChildren().add(btn);
                        }
                    }
                }
            }
        }
        scrollPane.setContent(vbox);
    }

    private void showArbeidere(String concert, int whichScene, String festival) {
        for (Festival f : Main.festivals) {
            if (f.getFestival().equals(festival)) {
                for (Concert c : f.getScene().get(whichScene).getKonsert()) {
                    // Her går den gjennom alle konserter på riktig festival på riktig scene.
                    if (c.getArtist().equals(concert)) {
                        String lystekniker = "Lysteknikere\n";
                        String lydtekniker = "Lydteknikere\n";

                        for (SoundTech st : c.getLyd()) {
                            lystekniker += st.getNavn() + "\n";
                        }

                        for (LightTech lt : c.getLys()) {
                            lydtekniker += lt.getNavn() + "\n";
                        }

                        labelLightTech.setText(lystekniker);
                        labelSoundTech.setText(lydtekniker);
                    }
                }
            }
        }
    }

    public Button createButton(String name) {
        final Button button = new Button(name);
        button.setId("arrScenes");
        button.setPrefSize(200,50);
        button.setOnMouseClicked(event -> {
            try {
                String festival = choiceBoxFestivals.getItems().get(festivalSelected).toString();
                showArbeidere(name, sceneSelected, festival );
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Du må velge en jobb");
            }
        });
        return button;
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
        main.changeView(rootPane, Constants.getHome());
    }

}
