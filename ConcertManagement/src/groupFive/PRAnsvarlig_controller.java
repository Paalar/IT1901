package groupFive;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import util.Filter;

public class PRAnsvarlig_controller {

    @FXML
    private AnchorPane rootPane;

    @FXML
    private VBox vBoxBands;

    @FXML
    private TextField searchBand, textFieldEmail, textFieldSales, textFieldLink1, textFieldLink2;

    @FXML
    private TextArea textAreaOmtale;


    public void initialize() {
        putBandsInVbox("");
    }

    private void putInfoInTextAreas(String band) {
        String prevBand = band;
        String bandNoIllegalChars = removeIllegalCharsBan(band);
        generateEmailAndPutInTextField(band);
        generateSalesAndPutInTextField(band);
        //putOmtaleInTextField(band);
        //generateLinksAndPutInTextField(band);
    }

    private String removeIllegalCharsBan(String band) {
        band = band.toLowerCase();
        while (band.contains(" ")) {
            band = band.replace(" ", "");
        }
        band = band.replace("å","aa");
        band = band.replace("æ", "ae");
        band = band.replace("ø", "o");
        return band;
    }

    private void generateSalesAndPutInTextField(String band) {
        textFieldSales.setText(Filter.getSales(band) + "%");
    }

    private void generateEmailAndPutInTextField(String band) {
        textFieldEmail.setText("manager@" + band + ".com");
    }

    private void putBandsInVbox(String bandSearch) {
        vBoxBands.getChildren().clear();
        for (String band : Filter.getAllBandsSpecificFestival("UKA 2017")) {
            if (band.toLowerCase().contains(bandSearch.toLowerCase())) {
                Button btn = createButton(band);
                vBoxBands.getChildren().add(btn);
            }
        }
    }

    public Button createButton(String name) {
        // Denne lager og returnerer en Button.
        final Button button = new Button(name);
        button.setId("arrScenes");
        button.setPrefSize(200,20);
        button.setOnMouseClicked(event -> {
            putInfoInTextAreas(name);
        });
        return button;
    }



    @FXML
    private void onKeyPressSearchBar() {
        String searchText = searchBand.getText();
        putBandsInVbox(searchText);
    }

    @FXML
    private void goHome(){
        String fxmlFileName = "Main";
        Main main = new Main();
        main.changeView(rootPane, fxmlFileName);
    }
}
