package groupFive;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Arrays;

public class Main_controller {
    @FXML
    private VBox jobsList;

    @FXML
    private AnchorPane rootPane;

    @FXML
    public void initialize() {
        // Denne funksjonen blir kjørt automatisk når alt er loadet og du kan begynne å endre på ting.
        addLoginElementsInList();
    }

    public void addLoginElementsInList() {
        //Legge til default login knappene.
        ArrayList<String> jobs = new ArrayList<>(Arrays.asList("Arrangør", "Bookinansvarlig", "Bookingsjef", "Manager", "Tekniker"));
        addButtons(jobs, jobsList);

    }


    public void addButtons(ArrayList<String> jobs, VBox jobsList) {
        for (int i = 0; i < jobs.size(); i++) {
            Button btnNumber = createButton(jobs.get(i));
            jobsList.getChildren().add(btnNumber);
        }
    }

    public Button createButton(String name) {
        final Button button = new Button(name);
        button.setId("jobButt");
        button.setPrefSize(200,50);
        button.setOnMouseClicked(event -> {
            try {
                Main main = new Main();
                main.changeView(rootPane, name);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Du må velge en jobb");
            }
        });
        return button;
    }
}