package groupFive;

import IO.ReadWriteConfig;
import Json.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import util.Constants;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static util.Filter.getAllFestivalsObservableList;

public class Arrangor_controller {
    @FXML
    private ListView listViewScene1;
    @FXML
    private ListView listViewScene2;
    @FXML
    private ListView listViewScene3;

    // De 3 listviewsene i en liste.
    private List<ListView> listViews;

    @FXML
    private TextField textFieldScene1;

    @FXML
    private TextField textFieldScene2;

    @FXML
    private TextField textFieldScene3;

    //3 textfields i en liste
    private List<TextField> textFields;

    @FXML
    private ChoiceBox choiceBoxFestivals;

    @FXML
    private AnchorPane rootPane;

    @FXML
    public void initialize() {
        listViews = Arrays.asList(listViewScene1, listViewScene2, listViewScene3);
        textFields = Arrays.asList(textFieldScene1, textFieldScene2, textFieldScene3);
        addItemsToList();
    }

    private void createChoiceBoxListener() {
        choiceBoxFestivals.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                putConcertsInSceneLists(choiceBoxFestivals.getItems().get((Integer) newValue).toString());
                System.out.println(choiceBoxFestivals.getItems().get((Integer) newValue));
            }
        });
    }

    private void addItemsToList() {
        putFestivalsInChoiceBox();
        createChoiceBoxListener();
        putConcertsInSceneLists("UKA 2017"); //Dette er default festivalen som blir først markert, kan kanskje endre denne til bare første i listen.
        //TODO: add alle andre ting vi må putte i lister her som de forksjellige scenene etc.
    }

    private void putFestivalsInChoiceBox() {
        ObservableList<String> observableListToAdd = getAllFestivalsObservableList(Main.festivals);
        choiceBoxFestivals.setItems(observableListToAdd);
        choiceBoxFestivals.getSelectionModel().selectFirst();
        // Denne linjen gjør bare at det første itemet i listen blir vist og selected.
        // TODO: gjør listen klikkbar og endre ting i scene boksen videre.
    }

    private void putSceneNamesInTextBox(String festival) {
        for (Festival f : Main.festivals) {
            if (f.getFestival().equals(festival)) {
                for (int i = 0; i < 3; i++) {
                    textFields.get(i).setText(f.getScene().get(i).getNavn());
                }
            }
        }
    }

    private void putConcertsInSceneLists(final String festival) {
        putSceneNamesInTextBox(festival);
        for (Festival f : Main.festivals) {
            if (f.getFestival().equals(festival)) {
                // Når du har funnet riktig festival.
                for (int i = 0; i < 3; i++) {
                    final int iFinal = i;
                    // Denne må være final for å kunne bruke i en anonym klasse.

                    listViews.get(i).setEditable(true);
                    List<String> concerts = new ArrayList<>();
                    for (Concert c : f.getScene().get(i).getKonsert()) {
                        concerts.add(c.getArtist());
                    }
                    ObservableList<String> observableListToAdd = FXCollections.observableArrayList(concerts);
                    listViews.get(i).setItems(observableListToAdd);
                    listViews.get(i).setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            try {
                                String itemClicked = listViews.get(iFinal).getSelectionModel().getSelectedItem().toString();
                                int whichScene = iFinal;
                                showArbeidere(itemClicked, whichScene, festival);
                            } catch (Exception e) {
                                System.out.println("Du har ikke valgt en konsert.");
                            }


                        }
                    });
                }
            }
        }
    }

    private void showArbeidere(String concert, int whichScene, String festival) {
        // TODO: lag popupen finere.
        for (Festival f : Main.festivals) {
            if (f.getFestival().equals(festival)) {
                for (Concert c : f.getScene().get(whichScene).getKonsert()) {
                    // Her går den gjennom alle konserter på riktig festival på riktig scene.
                    if (c.getArtist().equals(concert)) {
                        Stage stage = new Stage(StageStyle.DECORATED);
                        stage.setX(400);
                        stage.setY(400);
                        // Hvilke kordinater det nye vinduet skal åpnes i.

                        Pane layout = new AnchorPane();
                        layout.setPrefSize(500, 250);
                        stage.setScene(new Scene(layout));
                        // lager ny layout og scene som er 500px bred og 270px høy.

                        ListView listViewSoundTechs = new ListView();
                        listViewSoundTechs.setPrefSize(250, 250);
                        listViewSoundTechs.setEditable(true);

                        List<String> soundTechs = new ArrayList<>();
                        soundTechs.add("SoundTechs: ");
                        for (SoundTech st : c.getLyd()) {
                            soundTechs.add(st.getNavn());
                        }
                        ObservableList<String> observableListSoundTechs = FXCollections.observableArrayList(soundTechs);
                        listViewSoundTechs.setItems(observableListSoundTechs);
                        // Lager ny listview og går gjennom alle SoundTechsene og legger de til i listen.


                        ListView listViewLightTechs = new ListView();
                        listViewLightTechs.setPrefSize(250, 250);
                        listViewLightTechs.setLayoutX(250);
                        listViewLightTechs.setEditable(true);

                        List<String> LightTechs = new ArrayList<>();
                        LightTechs.add("LightTechs: ");
                        for (LightTech lt : c.getLys()) {
                            LightTechs.add(lt.getNavn());
                        }
                        ObservableList<String> observableListLightTechs = FXCollections.observableArrayList(LightTechs);
                        listViewLightTechs.setItems(observableListLightTechs);
                        // Lager ny listview og går gjennom alle LightTechsene og legger de til i listen.

                        layout.getChildren().add(listViewSoundTechs);
                        layout.getChildren().add(listViewLightTechs);
                        // Legger til listene i layouten og viser den.
                        stage.show();
                    }
                }
            }
        }
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
