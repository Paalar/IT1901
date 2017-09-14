package groupFive;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.control.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class main_controller {
    @FXML
    private ListView loginListView;

    @FXML
    public void initialize() {
        // Denne funksjonen blir kjørt automatisk når alt er loadet og du kan begynne å endre på ting.
        addLoginElementsInList();
    }

    public void addLoginElementsInList() {
        //Legge til default login knappene.
        ArrayList<String> jobs = new ArrayList<>(Arrays.asList("Arrangør", "Bookinansvarlig", "Bookingsjef", "Manager", "Lydtekniker", "Lystekniker"));
        addToList(jobs, loginListView);
    }


    public void addToList(ArrayList<String> listToAdd, ListView whichList) {
        ObservableList<String> observableListToAdd = FXCollections.observableArrayList(listToAdd);
        loginListView.setEditable(true);
        loginListView.setItems(observableListToAdd);
        loginListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("clicked on " + loginListView.getSelectionModel().getSelectedItem());
            }
        });
    }
}