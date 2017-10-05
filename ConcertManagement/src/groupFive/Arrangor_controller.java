package groupFive;

import Json.*;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import util.Constants;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;
import java.util.List;

import static util.Filter.getAllFestivalsObservableList;

public class Arrangor_controller {

    private boolean totalClicked = false;
    private Button lastButtPress = null;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private ChoiceBox choiceBoxFestivals, choiceBoxScenes;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Label labelSoundTech, labelLightTech;

    private int festivalSelected, sceneSelected = 0;

    @FXML
    private VBox vboxLightTech, vboxSoundTech, arrButts;

    private String lydtekniker, lystekniker;

    @FXML
    public void initialize() {
        addItemsToList();
    }

    // Setter fokous på første elementet når man åpner siden.
    private void repeatFocus(Node node) {
        Platform.runLater(() -> {
            if (!node.isFocused()) {
                node.requestFocus();
                repeatFocus(node);
            }
        });
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

        // Fokuserer og viser navnene til de som jobber for første konsert.
        String festival = choiceBoxFestivals.getItems().get(festivalSelected).toString();
        showArbeidere(Main.festivals.get(0).getScene().get(0).getKonsert().get(0).getArtist(), sceneSelected, festival);
        repeatFocus(arrButts.getChildren().get(0));
    }

    private void putScenesInChoiceBox(String festival) {
        //Putter scenene i dropdown boksen.
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
        arrButts.getChildren().clear();

        for (Festival f : Main.festivals) {
            if (f.getFestival().equals(festival)) {
                for (int i = 0; i < f.getScene().size(); i++) {
                    if(scene.equals(f.getScene().get(i).getNavn())) {
                        List<Concert> concerts = f.getScene().get(i).getKonsert();
                        for (int n = 0; n < concerts.size(); n++) {
                            Button btn = createButton(concerts.get(n).getArtist());
                            btn.setId("tekButts");
                            btn.setPrefSize(200,20);
                            arrButts.getChildren().add(btn);
                        }
                    }
                }
            }
        }
        scrollPane.setContent(arrButts);
    }

    private void showArbeidere(String concert, int whichScene, String festival) {
        vboxLightTech.getChildren().clear();
        vboxSoundTech.getChildren().clear();
        for (Festival f : Main.festivals) {
            if (f.getFestival().equals(festival)) {
                for (Concert c : f.getScene().get(whichScene).getKonsert()) {
                    // Her går den gjennom alle konserter på riktig festival på riktig scene.
                    if (c.getArtist().equals(concert)) {
                        lystekniker = "Lysteknikere:\n\n";
                        lydtekniker = "Lydteknikere:\n\n";

                        for (SoundTech st : c.getLyd()) {
                            lystekniker += " - " + st.getNavn() + "\n";
                        }

                        for (LightTech lt : c.getLys()) {
                            lydtekniker += " - " + lt.getNavn() + "\n";
                        }

                        labelLightTech.setText(lystekniker);
                        labelSoundTech.setText(lydtekniker);
                        vboxSoundTech.getChildren().add(labelSoundTech);
                        vboxLightTech.getChildren().add(labelLightTech);
                    }
                }
            }
        }
    }

    public Button createButton(String name) {
        //Lager knapper
        final Button button = new Button(name);
        button.setId("arrScenes");
        button.setPrefSize(300,50);
        button.setOnMouseClicked(event -> {
            try {
                lastButtPress = button;
                String festival = choiceBoxFestivals.getItems().get(festivalSelected).toString();
                showArbeidere(name, sceneSelected, festival);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Du må velge en jobb");
            }
        });
        return button;
    }

    @FXML
    private void totalView() {
        if (totalClicked) {
            String festival = choiceBoxFestivals.getItems().get(festivalSelected).toString();
            if (lastButtPress == null) {lastButtPress = (Button) arrButts.getChildren().get(0);
                repeatFocus(lastButtPress);
            }
                showArbeidere(lastButtPress.getText().toString(), sceneSelected, festival);
                repeatFocus(lastButtPress);
                totalClicked = false;
        } else {
            totalClicked = true;
            ArrayList<String> totalOversikt = new ArrayList<>();
            ListView lv = new ListView();
            vboxLightTech.getChildren().clear();
            vboxSoundTech.getChildren().clear();
            String festival = choiceBoxFestivals.getItems().get(festivalSelected).toString();

            for (Festival f : Main.festivals) {
                if (f.getFestival().equals(festival)) {
                    totalOversikt.add("\t" + f.getFestival() + "\n");
                    for (int i = 0; i < f.getScene().size(); i++) {
                        totalOversikt.add("\t\t" + f.getScene().get(i).getNavn() + "\n");
                        for (int j = 0; j < f.getScene().get(i).getKonsert().size(); j++) {
                            totalOversikt.add("\t\t\t" + f.getScene().get(i).getKonsert().get(j).getArtist() + "\n");
                        }
                    }
                }
            }
            ObservableList<String> obsList = FXCollections.observableArrayList(totalOversikt);
            lv.setItems(obsList);
            vboxLightTech.getChildren().add(lv);
        }
    }

    @FXML
    private void goHome(){
        Main main = new Main();
        main.changeView(rootPane, Constants.getHome());
    }

}
