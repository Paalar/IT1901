package groupFive;

import util.Constants;
import IO.ReadWriteConfig;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;

public class Arrangor_controller {
    private ArrayList<String> wholeList;

    @FXML
    private ListView listView;

    @FXML
    private ChoiceBox choiceBoxArtists;

    @FXML
    private AnchorPane rootPane;

    @FXML
    public void initialize() {
        addItemsToList();
    }

    private void addItemsToList() {
        listView.setEditable(true);
        wholeList = ReadWriteConfig.readFile("arrangor");
        ArrayList<String> listToAdd = Main.filterList(wholeList, "__");
        ObservableList<String> observableListToAdd = FXCollections.observableArrayList(listToAdd);
        listView.setItems(observableListToAdd);
        listView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String itemClicked = listView.getSelectionModel().getSelectedItem().toString();
                if (itemClicked.equals("Arbeidere")) {
                    showArbeidere();
                }
            }
        });
    }

    private void showArbeidere() {
        choiceBoxArtists.setVisible(true);
        ArrayList<String> artists = Main.filterList(wholeList,"?_");
        ObservableList<String> observableListToAdd = FXCollections.observableArrayList(artists);
        choiceBoxArtists.setItems(observableListToAdd);
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
