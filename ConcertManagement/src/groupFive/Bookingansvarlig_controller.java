package groupFive;

import Json.Concert;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import util.Constants;
import javafx.scene.control.ListView;

import java.util.List;

import static util.Filter.*;

public class Bookingansvarlig_controller {
    @FXML
    AnchorPane rootPane;

    @FXML
    ListView listViewAllBands;

    @FXML
    ListView listViewEarlierConcerts;

    @FXML
    TextArea textAreaBandInfo;

    public void initialize() {
        putItemsInLists();
    }

    private void putItemsInLists() {
        listViewAllBands.setEditable(true);
        listViewAllBands.setItems(getAllBandsObservableList());
        listViewAllBands.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    String itemClicked = listViewAllBands.getSelectionModel().getSelectedItem().toString();
                    putBandInfoInLists(itemClicked);
                } catch (Exception e) {
                    System.out.println("Du har ikke valgt et band.");
                }
            }
        });
    }

    private void putBandInfoInLists(String band) {
        String stringToPutInTextArea = "";

        int popularity = getPopularity(band);
        stringToPutInTextArea += "Populæritet: " + String.valueOf(popularity) + "%\n";

        String genre = getGenre(band);
        stringToPutInTextArea += "Sjanger: " + genre + "\n";

        int earlierSales = getSales(band);
        stringToPutInTextArea += "Tidligere salg: " + String.valueOf(earlierSales) + "%\n";

        textAreaBandInfo.setText(stringToPutInTextArea);

        listViewEarlierConcerts.setEditable(true);
        listViewEarlierConcerts.setItems(getConcertsAndScenesForBand(band));


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
