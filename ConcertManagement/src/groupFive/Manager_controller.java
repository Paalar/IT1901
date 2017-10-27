package groupFive;

import Json.*;
import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import util.Constants;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import java.util.ArrayList;
import java.util.List;

import static Json.JsonEncode.JsonInsert;

public class Manager_controller {
    @FXML
    private ListView listOfOfferView;

    @FXML
    private ListView needListAdded;

    @FXML
    private  TextField listOfNeedsAddedLabel;

    @FXML
    private TextField need;

    @FXML
    private TextField inputFieldNeed;

    @FXML
    private Label artist;

    @FXML
    private Label scene;

    @FXML
    private Label date;

    private String artistNameString = "";
    private String sceneNameString;
    private String needString;
    private String datoString;
    private List<ListView> listViews;

    @FXML
    private Button sendButton;

    @FXML
    private Button addButton;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private VBox needsNotSent1;

    private List<String> needsList = new ArrayList<>();

    private List<Offer> offers = new ArrayList<>();
    private String err = "error";

    @FXML
    public void initialize() {
        // listViews = Arrays.asList(listOfOfferView, needListAdded);
        for (Scene scene : Main.festivals.get(0).getScene()) {
            for (Concert conert : scene.getKonsert()){
                List<Json.tekniskeBehov> behov = conert.getTekniskeBehov();
                ArrayList<String> behovs = new ArrayList<>();
                for (int n = 0; n < behov.size(); n++){
                    behovs.add(behov.get(n).getBehov());
                }
                    String artist = conert.getArtist();
                    String sceneName = scene.getNavn();
                    String dato = conert.getDato();
                    int pris = conert.getPris();
                    Offer newOffer = new Offer(artist, sceneName, dato, pris, behovs);
                    offers.add(newOffer);
            }
        }
        //hideStuffOrShow(false);
        addButtons(offers, needsNotSent1);
    }

    private void addButtons(List<Offer> offers, VBox needsList) {
        for (Offer offer : offers) {
            Button btnNumber = createButton(offer);
            needsList.getChildren().add(btnNumber);
        }
    }

    private Button createButton(Offer name) {
        final Button button = new Button(name.getArtist());
        button.setId("offerButt");
        button.setPrefSize(200,20);
        button.setOnMouseClicked(event -> {
            try {
                needListAdded.getItems().clear();
                updateInfo(name.getArtist(), name.getScene(), name.getDato(), name.getNeeds());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return button;
    }

    @FXML
    private void alertShow(String title, String contentText){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    @FXML
    private void showWithMouseClick(){
        hideStuffOrShow(true);
    }

    @FXML
    private void hideStuffOrShow(boolean what){
        addButton.setVisible(what);
        need.setVisible(what);
        artist.setVisible(what);
        scene.setVisible(what);
        needListAdded.setVisible(what);
        listOfNeedsAddedLabel.setVisible(what);
        sendButton.setVisible(what);
        date.setVisible(what);
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
        main.changeView(rootPane, Constants.getHome());
    }
    @FXML
    private  void sendTheNeeds(){
        if(artistNameString.equals("")){
            alertShow(err, "Du må velge en artist først");
        }
        else {
            for (Festival f : Main.festivals) {
                if (f.getFestival().equals("UKA 2017")) { //Endrer bare behov på UKA 2017 og ikke de andre gamle.
                    for (Scene s : f.getScene()) {
                        if (s.getNavn().equals(sceneNameString)) {
                            for (Concert c : s.getKonsert()) {
                                if (c.getArtist().equals(artistNameString)) {
                                    List<tekniskeBehov> nyeTekniskeBehov = new ArrayList<>();
                                    for (String needs : needsList) {
                                        tekniskeBehov nyttBehov = new tekniskeBehov(needs);
                                        nyeTekniskeBehov.add(nyttBehov);
                                    }
                                    c.setTekniskeBehov(nyeTekniskeBehov);
                                }
                            }
                        }
                    }
                }
            }
            JsonInsert(Main.festivals);
            alertShow("Information Dialog", "Behovene ble sendt!");
        }
    }

    @FXML
    private void addHoy(){
        addItem("Høyttalere", "Høyttaler");
    }

    @FXML
    private void addMic(){
        addItem("Mikrofoner", "Mikrofon");
    }

    @FXML
    private void addMon(){
        addItem("Monitorer", "Monitor");
    }

    @FXML
    private void addSing(){
        addItem("Sangere", "Sanger");
    }

    @FXML
    private void delHoy() {
        delItem("Høyttalere", "Høyttaler");
    }

    @FXML
    private void delMic(){
        delItem("Mikrofoner", "Mikrofon");
    }

    @FXML
    private void delMon(){
        delItem("Monitorer", "Monitor");
    }

    @FXML
    private void delSing(){
        delItem("Sangere", "Sanger");
    }

    @FXML
    private void addItem(String itemMulti, String itemSingel){
        if(artistNameString.equals("")){
            alertShow(err, "Du må velge en artist først");
            inputFieldNeed.setText("");
        }
        else {
            boolean check = false;
            for (String s : needsList) {
                if (s.toLowerCase().contains(itemSingel.toLowerCase())) {
                    check = true;
                    String[] P = s.split("\\s+");
                    int nr = Integer.parseInt(P[0]);
                    nr++;
                    needsList.set(needsList.indexOf(s), nr + " " + itemMulti);
                    popListView(needsList, needListAdded);
                }
            }
            if (!check) {
                needsList.add("1 " + itemSingel);
                popListView(needsList, needListAdded);
            }
        }
    }

    @FXML
    private void delItem(String itemMulti, String itemSingel){
        if(artistNameString.equals("")){
            alertShow(err, "Du må velge en artist først");
            inputFieldNeed.setText("");
        }
        else {
            try {
                for (String s : needsList) {
                    if (s.toLowerCase().contains(itemMulti.toLowerCase())) {
                        String[] P = s.split("\\s+");
                        int nr = Integer.parseInt(P[0]);
                        if (nr > 2) {
                            nr--;
                            needsList.set(needsList.indexOf(s), nr + " " + itemMulti);
                            popListView(needsList, needListAdded);
                        } else {
                            nr--;
                            needsList.set(needsList.indexOf(s), nr + " " + itemSingel);
                            popListView(needsList, needListAdded);
                        }
                    }
                    else if (s.toLowerCase().contains(itemSingel.toLowerCase())) {
                        needsList.remove(needsList.indexOf(s));
                        popListView(needsList, needListAdded);
                        break;
                    }
                }
            } catch (Exception e) {
                alertShow(err, "Du kan ikke slette noe som ikke er der");
                System.err.println(e.getMessage());
            }
        }
    }

    @FXML
    private void delNeed(){
        if (artistNameString.equals("")){
            alertShow(err, "Du må velge en artist først");
        }
        else {
            int nrOfNeed = needListAdded.getSelectionModel().getSelectedIndex();
            if(nrOfNeed == -1){
                alertShow(err, "Du må velge et behov først");
            }
            else {
                needsList.remove(nrOfNeed);
                popListView(needsList, needListAdded);
            }
        }
    }

    private void popListView (List<String> needList, ListView listArea){
        ObservableList<String> obsList = FXCollections.observableArrayList(needList);
        listArea.setItems(obsList);
    }

    @FXML
    private  void addNeedsToList(){
        if(artistNameString.equals("")){
            alertShow(err, "Du må velge en artist først");
            inputFieldNeed.setText("");
        }
        else {
            String aNeed = inputFieldNeed.getText();
            needsList.add(aNeed);
            popListView(needsList, needListAdded);
            inputFieldNeed.setText("");
        }
    }

    @FXML
    private void updateScene(String sceneName){
        this.scene.setText("Scene: " + sceneName);
        this.sceneNameString = sceneName;
    }

    @FXML
    private void updateArtistName(String artistName){
        this.artist.setText("Artist: " + artistName);
        this.sceneNameString = artistName;
    }

    @FXML
    private  void updateDate(String dato){
        this.date.setText("Dato: " + dato);
        this.datoString = dato;
    }

    @FXML
    private  void updateInfo(String artistName, String sceneName, String dato, ArrayList<String> artistsNeeds){
        this.artistNameString = artistName;
        this.sceneNameString = sceneName;
        this.datoString = dato;
        this.needsList = artistsNeeds;
        if(!artistsNeeds.isEmpty()){
            popListView(artistsNeeds, needListAdded);
        }
        updateArtistName(artistName);
        updateScene(sceneName);
        updateDate(dato);
    }
}
