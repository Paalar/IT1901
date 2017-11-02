package groupFive;

import Json.*;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;

import javafx.event.EventHandler;
import javafx.scene.Node;

import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import util.Constants;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;
import java.util.List;

import static util.Filter.getAllFestivalsObservableList;

public class ArrangorController {

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
    private VBox vboxLightTech, vboxSoundTech, vboxArtists;

    @FXML
    private ListView listViewTotal;

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
        totalView();
    }

    private void addItemsToList() {
        // Denne putter bare default verdier inn i alle knapper lister etc.
        putFestivalsInChoiceBox();
        putScenesInChoiceBox(Main.festivals.get(0).getFestival());
        createChoiceBoxListener();
        createSceneBoxListener();
        putSceneNamesInTextBox(Main.festivals.get(0).getFestival(), "All Scenes");

        // Fokuserer og viser navnene til de som jobber for første konsert.
        String festival = choiceBoxFestivals.getItems().get(festivalSelected).toString();
        showArbeidere(Main.festivals.get(0).getScene().get(0).getKonsert().get(0).getArtist());
        repeatFocus(vboxArtists.getChildren().get(0));
        totalView();
    }

    private void putScenesInChoiceBox(String festival) {
        //Putter scenene i dropdown boksen.
        ArrayList<String> sceneNames = new ArrayList<>();
        sceneNames.add("All Scenes");
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
        vboxArtists.getChildren().clear();

        for (Festival f : Main.festivals) {
            if (f.getFestival().equals(festival)) {
                for (int i = 0; i < f.getScene().size(); i++) {
                    if(scene.equals(f.getScene().get(i).getNavn()) || scene.equals("All Scenes")) {
                        List<Concert> concerts = f.getScene().get(i).getKonsert();
                        for (int n = 0; n < concerts.size(); n++) {
                            Button btn = createButton(concerts.get(n).getArtist());
                            btn.setId("tekButts");
                            btn.setPrefSize(200,20);
                            vboxArtists.getChildren().add(btn);
                        }
                    }
                }
            }
        }
        scrollPane.setContent(vboxArtists);
    }

    private void showArbeidere(String concert) {
        String festival = choiceBoxFestivals.getItems().get(festivalSelected).toString();
        String scene = choiceBoxScenes.getItems().get(sceneSelected).toString();

        vboxLightTech.getChildren().clear();
        vboxSoundTech.getChildren().clear();
        for (Festival f : Main.festivals) {
            if (f.getFestival().equals(festival)) {
                for (Scene s : f.getScene()) {
                    if (s.getNavn().equals(scene) || scene.equals("All Scenes")) {
                        for (Concert c : s.getKonsert()) {
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
        }
        totalView();
    }

    public Button createButton(String name) {
        //Lager knapper
        final Button button = new Button(name);
        button.setId("arrScenes");
        button.setPrefSize(300,50);
        button.setOnMouseClicked(event -> {
            try {
                lastButtPress = button;
                showArbeidere(name);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return button;
    }

    private void totalView() {
        String scene = choiceBoxScenes.getItems().get(sceneSelected).toString();
        String festival = choiceBoxFestivals.getItems().get(festivalSelected).toString();
        ArrayList<String> totalOversikt = new ArrayList<>();
//        vboxLightTech.getChildren().clear();
//        vboxSoundTech.getChildren().clear();

        for (Festival f : Main.festivals) {
            if (f.getFestival().equals(festival)) {
                totalOversikt.add("\t" + f.getFestival() + "\n");
                for (Scene s : f.getScene()) {
                    if (s.getNavn().equals(scene) || scene.equals("All Scenes")) {
                        totalOversikt.add("\t\t" + s.getNavn() + "\n");
                        for (int j = 0; j < s.getKonsert().size(); j++) {
                            totalOversikt.add("\t\t\t" + s.getKonsert().get(j).getArtist() + " - " + s.getKonsert().get(j).getDato() + "\n");
                        }
                    }
                }
            }
        }
        ObservableList<String> obsList = FXCollections.observableArrayList(totalOversikt);
        listViewTotal.setEditable(true);
        listViewTotal.setItems(obsList);
        listViewTotal.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String clicked = listViewTotal.getSelectionModel().getSelectedItem().toString();
                if (clicked.startsWith("\t\t\t")) {
                    clicked = clicked.split(" - ")[0];
                    clicked = clicked.replaceAll("[\\t\\n]+", "");
                    // fjerner alle tabs og new lines.
                    showArbeidere(clicked);
                }
            }
        });
    }


    @FXML
    private void goHome(){
        Main main = new Main();
        main.changeView(rootPane, Constants.getHome());
    }

}
