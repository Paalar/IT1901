package groupFive;

import Json.Concert;
import Json.Festival;
import Json.Scene;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import util.Constants;

import java.util.ArrayList;
import java.util.List;

import static util.Filter.getAllBandsObservableList;

public class Bookingsjef_controller {

    @FXML
    private AnchorPane rootPane;

    @FXML
    private VBox vBoxBands;

    public void initialize() {
        putItemsInLists();
    }

    @FXML
    private void goBack(){
        String fxmlFileName = "Main";
        Main main = new Main();
        main.changeView(rootPane, fxmlFileName);
    }

    public Button createButton(String name) {
        // Denne lager og returnerer en Button.
        final Button button = new Button(name);
        button.setId("arrScenes");
        button.setPrefSize(200,20);
        button.setOnMouseClicked(event -> {
            System.out.println(name);
        });
        return button;
    }

    private void putItemsInLists() {
        //Denne legger til alle unike band i listen på første tab.
        for (String band : getAllBandsObservableList()) {
            Button btn = createButton(band);
            vBoxBands.getChildren().add(btn);
        }
    }
}
