package groupFive;

import Json.*;
import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import util.Constants;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import java.util.ArrayList;
import java.util.Arrays;
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

    private String artistNameString;
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

    List<String> needsList = new ArrayList<>();

    List<Offer> offers = new ArrayList<>();

    @FXML
    public void initialize() {
        // listViews = Arrays.asList(listOfOfferView, needListAdded);
        for (int i = 0; i < Main.festivals.get(0).getScene().size(); i++) {
            for (int j = 0; j < Main.festivals.get(0).getScene().get(i).getKonsert().size(); j++){
                List<Json.tekniskeBehov> behov = Main.festivals.get(0).getScene().get(i).getKonsert().get(j).getTekniskeBehov();
                ArrayList<String> behovs = new ArrayList<>();
                for (int n = 0; n < behov.size(); n++){
                    behovs.add(behov.get(n).getBehov());
                }
                    offers.add(new Offer(Main.festivals.get(0).getScene().get(i).getKonsert().get(j).getArtist(), Main.festivals.get(0).getScene().get(i).getNavn(), Main.festivals.get(0).getScene().get(i).getKonsert().get(j).getDato(), Main.festivals.get(0).getScene().get(i).getKonsert().get(j).getPris(), behovs));
            }
        }
        //hideStuffOnStart();
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
                System.out.println("Du må velge en jobb");
            }
        });
        return button;
    }

    @FXML
    private void alertShow(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("I have a great message for you! \nBehovene ble sendt!");
        alert.showAndWait();
    }

    @FXML
    private void hideStuffOnStart(){
        addButton.setVisible(false);
        need.setVisible(false);
        artist.setVisible(false);
        scene.setVisible(false);
        needListAdded.setVisible(false);
        listOfNeedsAddedLabel.setVisible(false);
        sendButton.setVisible(false);
        date.setVisible(false);
    }

    @FXML
    private void showStuff(){
        addButton.setVisible(true);
        need.setVisible(true);
        artist.setVisible(true);
        scene.setVisible(true);
        needListAdded.setVisible(true);
        listOfNeedsAddedLabel.setVisible(true);
        sendButton.setVisible(true);
        date.setVisible(true);
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
        alertShow();
    }

    @FXML
    private void addHoy(){
        boolean check = false;
        for (String s : needsList){
            if (s.toLowerCase().contains("Høytaller".toLowerCase())){
                check = true;
                String[] P = s.split("\\s+");
                int nr = Integer.parseInt(P[0]);
                nr++;
                needsList.set(needsList.indexOf(s), nr + " Høytallere");
                popListView(needsList,needListAdded);
            }
        }
        if (!check){
            needsList.add("1 Høytaller");
            popListView(needsList, needListAdded);
        }
    }

    @FXML
    private void addMic(){
        boolean check = false;
        for (String s : needsList){
            if (s.toLowerCase().contains("Mikrofon".toLowerCase())){
                check = true;
                String[] P = s.split("\\s+");
                int nr = Integer.parseInt(P[0]);
                nr++;
                needsList.set(needsList.indexOf(s), nr + " Mikrofoner");
                popListView(needsList,needListAdded);
            }
        }
        if (!check){
            needsList.add("1 Mikrofon");
            popListView(needsList, needListAdded);
        }
    }

    @FXML
    private void addMon(){
        boolean check = false;
        for (String s : needsList){
            if (s.toLowerCase().contains("Monitor".toLowerCase())){
                check = true;
                String[] P = s.split("\\s+");
                int nr = Integer.parseInt(P[0]);
                nr++;
                needsList.set(needsList.indexOf(s), nr + " Monitorer");
                popListView(needsList,needListAdded);
            }
        }
        if (!check){
            needsList.add("1 Monitor");
            popListView(needsList, needListAdded);
        }
    }

    @FXML
    private void delHoy(){
        for (String s : needsList){
            if (s.toLowerCase().contains("Høytallere".toLowerCase())){
                String[] P = s.split("\\s+");
                int nr = Integer.parseInt(P[0]);
                if(nr > 2){
                    nr--;
                    needsList.set(needsList.indexOf(s), nr + " Høytallere");
                    popListView(needsList,needListAdded);
                }
                else{
                    nr--;
                    needsList.set(needsList.indexOf(s), nr + " Høytaller");
                    popListView(needsList,needListAdded);
                }
            }
            else if (s.toLowerCase().contains("Høytaller".toLowerCase())){
                needsList.remove(needsList.indexOf(s));
                popListView(needsList,needListAdded);
            }
        }
    }

    @FXML
    private void delMic(){
        for (String s : needsList){
            if (s.toLowerCase().contains("Mikrofoner".toLowerCase())){
                String[] P = s.split("\\s+");
                int nr = Integer.parseInt(P[0]);
                if(nr > 2){
                    nr--;
                    needsList.set(needsList.indexOf(s), nr + " Mikrofoner");
                    popListView(needsList,needListAdded);
                }
                else{
                    nr--;
                    needsList.set(needsList.indexOf(s), nr + " Mikrofon");
                    popListView(needsList,needListAdded);
                }
            }
            else if (s.toLowerCase().contains("Mikrofon".toLowerCase())){
                needsList.remove(needsList.indexOf(s));
                popListView(needsList,needListAdded);
            }
        }
    }

    @FXML
    private void delMon(){
        for (String s : needsList){
            if (s.toLowerCase().contains("Monitorer".toLowerCase())){
                String[] P = s.split("\\s+");
                int nr = Integer.parseInt(P[0]);
                if(nr > 2){
                    nr--;
                    needsList.set(needsList.indexOf(s), nr + " Monitorer");
                    popListView(needsList,needListAdded);
                }
                else{
                    nr--;
                    needsList.set(needsList.indexOf(s), nr + " Monitor");
                    popListView(needsList,needListAdded);
                }
            }
            else if (s.toLowerCase().contains("Monitor".toLowerCase())){
                needsList.remove(needsList.indexOf(s));
                popListView(needsList,needListAdded);
            }
        }
    }

    @FXML
    private void delSing(){
        for (String s : needsList){
            if (s.toLowerCase().contains("Sangere".toLowerCase())){
                String[] P = s.split("\\s+");
                int nr = Integer.parseInt(P[0]);
                if(nr > 2){
                    nr--;
                    needsList.set(needsList.indexOf(s), nr + " Sangere");
                    popListView(needsList,needListAdded);
                }
                else{
                    nr--;
                    needsList.set(needsList.indexOf(s), nr + " Sanger");
                    popListView(needsList,needListAdded);
                }
            }
            else if (s.toLowerCase().contains("Sanger".toLowerCase())){
                needsList.remove(needsList.indexOf(s));
                popListView(needsList,needListAdded);
            }
        }
    }

    @FXML
    private void addSing(){
        boolean check = false;
        for (String s : needsList){
            if (s.toLowerCase().contains("Sanger".toLowerCase())){
                check = true;
                String[] P = s.split("\\s+");
                int nr = Integer.parseInt(P[0]);
                nr++;
                needsList.set(needsList.indexOf(s), nr + " Sangere");
                popListView(needsList,needListAdded);
            }
        }
        if (!check){
            needsList.add("1 Sanger");
            popListView(needsList, needListAdded);
        }
    }

    @FXML
    private void delNeed(){
        int nrOfNeed = needListAdded.getSelectionModel().getSelectedIndex();
        needsList.remove(nrOfNeed);
        popListView(needsList, needListAdded);
    }

    private void popListView (List<String> needList, ListView listArea){
        ObservableList<String> obsList = FXCollections.observableArrayList(needList);
        listArea.setItems(obsList);
    }

    @FXML
    private  void addNeedsToList(){
        String aNeed = inputFieldNeed.getText();
        needsList.add(aNeed);
        popListView(needsList, needListAdded);
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
