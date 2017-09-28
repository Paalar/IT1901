package groupFive;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import util.Constants;
import util.Filter;

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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static util.Filter.getAllFestivalsObservableList;

public class Tekniker_controller {

    @FXML
    private ListView listViewTeknikere;

    @FXML
    private AnchorPane rootPane;

    public void initialize() {
        putItemsInLists();
    }

    private void putItemsInLists() {
        putTeknikersInList("Uka 2017"); // Tinus
        putFestivalNameInLabel(); // Sondre
    }

    private void putTeknikersInList(String festival) {
        ObservableList<String> observableListToAdd = Filter.getAllTeknikers(festival);
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

    private void putFestivalNameInLabel() {

    }

    private void putKonsertInfoInLists(String festival, String Arbeider) {

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
