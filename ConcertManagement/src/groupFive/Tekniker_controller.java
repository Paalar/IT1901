package groupFive;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import util.Constants;

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
    private AnchorPane rootPane;

    public void initialize() {
        private void putItemsInLists();
    }

    private void putItemsInLists() {
        putTeknikersInList(); // Tinus
        putFestivalNameInLabel(); // Sondre
        putKonsertInfoInLists();  // Sondre
    }

    private void putTeknikersInList() {

    }

    private void putFestivalNameInLabel() {

    }

    private void putKonsertInfoInLists() {

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
