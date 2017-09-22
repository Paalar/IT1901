package groupFive;

import Json.Festival;
import Json.JsonDecode;
import util.Constants;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;
import java.util.List;

import static util.Filter.getAllFestivalsObservableList;

public class Arrangor_controller {
    @FXML
    private ListView listViewScene1;
    @FXML
    private ListView listViewScene2;
    @FXML
    private ListView listViewScene3;

    @FXML
    private ChoiceBox choiceBoxFestivals;

    @FXML
    private AnchorPane rootPane;

    @FXML
    public void initialize() {
        addItemsToList();
    }

    private void addItemsToList() {
        putFestivalsInChoiceBox();
        //TODO: add alle andre ting vi må putte i lister her som de forksjellige scenene etc.


//        wholeList = ReadWriteConfig.readFile("arrangor");
//        ArrayList<String> listToAdd = Filter.filterList(wholeList, "__");
//        ObservableList<String> observableListToAdd = FXCollections.observableArrayList(listToAdd);
//        listView.setItems(observableListToAdd);
//        listView.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                String itemClicked = listView.getSelectionModel().getSelectedItem().toString();
//                if (itemClicked.equals("Arbeidere")) {
//                    showArbeidere();
//                }
//            }
//        });
    }

    private void putFestivalsInChoiceBox() {
        listViewScene1.setEditable(true);
        List<Festival> festivals = JsonDecode.parseJSON();
        ObservableList<String> observableListToAdd = getAllFestivalsObservableList(festivals);
        choiceBoxFestivals.setItems(observableListToAdd);
        choiceBoxFestivals.getSelectionModel().selectFirst();
        // Denne linjen gjør bare at det første itemet i listen blir vist og selected.
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
