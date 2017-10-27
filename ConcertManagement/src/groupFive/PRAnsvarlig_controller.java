package groupFive;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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

    @FXML
    private Label labelBandInfo;


    public void initialize() {
        putBandsInVbox("");
        putInfoInTextAreas(Main.festivals.get(0).getScene().get(0).getKonsert().get(0).getArtist()); // Velger automatisk den først i listen til å vise.
        repeatFocus(vBoxBands.getChildren().get(0));
    }

    private void repeatFocus(Node node) {
        Platform.runLater(() -> {
            if (!node.isFocused()) {
                node.requestFocus();
                repeatFocus(node);
            }
        });
    }

    private void putInfoInTextAreas(String band) {
        String prevBand = band;
        String bandNoIllegalChars = removeIllegalCharsBan(band);
        putArtistNameInLabel(prevBand);
        generateEmailAndPutInTextField(bandNoIllegalChars);
        generateSalesAndPutInTextField(prevBand);
        generateLinksAndPutInTextField(bandNoIllegalChars);
        putOmtaleInTextField(prevBand);
    }

    private void putArtistNameInLabel(String band) {
        labelBandInfo.setText(band);
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

    private void putOmtaleInTextField(String band) {
        String omtale = Filter.getOmtale(band);
        textAreaOmtale.setText(omtale);
    }

    private void generateLinksAndPutInTextField(String band) {
        textFieldLink1.setText("www.aftenpusten.no/" + band);
        textFieldLink2.setText("www.vg.no/" + band);
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
