package groupFive;

import IO.ReadWriteConfig;
import Json.Concert;
import Json.Festival;
import Json.JsonDecode;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.scene.control.TextField;
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

    private void putConcertsInSceneLists(String festival) {
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
                            String itemClicked = listViews.get(iFinal).getSelectionModel().getSelectedItem().toString();
                            
                            System.out.println(itemClicked);
                        }
                    });
                }
            }
        }



    }

    private void showArbeidere() {
//        choiceBoxArtists.setVisible(true);
//        ArrayList<String> artists = Filter.filterList(wholeList,"?_");
//        ObservableList<String> observableListToAdd = FXCollections.observableArrayList(artists);
//        choiceBoxArtists.setItems(observableListToAdd);
    }

    @FXML
    private void goBack(){
        String fxmlFileName = "main";
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
