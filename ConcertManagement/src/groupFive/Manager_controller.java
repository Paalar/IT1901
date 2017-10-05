package groupFive;

import Json.*;
import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import util.Constants;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
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

    List<Festival> festivals = Main.festivals;

    ArrayList<String> needsList = new ArrayList<>();

    ArrayList<Offer> offers = new ArrayList<>();

    @FXML
    public void initialize() {
       // listViews = Arrays.asList(listOfOfferView, needListAdded);
        for (int i = 0; i < festivals.get(0).getScene().size(); i++) {
            for (int j = 0; j < festivals.get(0).getScene().get(i).getKonsert().size(); j++){
                ArrayList<Json.tekniskeBehov> behov = festivals.get(0).getScene().get(i).getKonsert().get(j).getTekniskeBehov();
                ArrayList<String> behovs = new ArrayList<>();
                for (int n = 0; n < behov.size(); n++){
                   behovs.add(behov.get(n).getBehov());
                }
                offers.add(new Offer(festivals.get(0).getScene().get(i).getKonsert().get(j).getArtist(), festivals.get(0).getScene().get(i).getNavn(), festivals.get(0).getScene().get(i).getKonsert().get(j).getDato(), festivals.get(0).getScene().get(i).getKonsert().get(j).getPris(), behovs));
            }
        }
        //hideStuffOnStart();
        addButtons(offers, needsNotSent1);
    }

    private void addButtons(ArrayList<Offer> offers, VBox needsList) {
        for (Offer offer : offers) {
            Button btnNumber = createButton(offer);
            needsList.getChildren().add(btnNumber);
        }
    }

    private Button createButton(Offer name) {
        final Button button = new Button(name.getArtist());
        button.setId("offerButt");
        button.setPrefSize(200,50);
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
        for (int i = 0; i < festivals.get(0).getScene().size(); i ++){
            if (festivals.get(0).getScene().get(i).getNavn() == sceneNameString){
                for (int j = 0; j < festivals.get(0).getScene().get(j).getKonsert().size(); j++){
                    if(festivals.get(0).getScene().get(j).getKonsert().get(j).getArtist() == artistNameString){
                        ArrayList<tekniskeBehov> nyeTekniskeBehov = new ArrayList<>();
                        System.out.println("HER");
                        for(int n = 0; n < needsList.size(); n++){
                            tekniskeBehov nyttBehov = new tekniskeBehov(needsList.get(n));
                            nyeTekniskeBehov.add(nyttBehov);
                        }
                        festivals.get(0).getScene().get(j).getKonsert().get(j).setTekniskeBehov(nyeTekniskeBehov);
                        System.out.println(festivals.get(0).getScene().get(j).getKonsert().get(j).getTekniskeBehov());
                    }
                }
            }
        }
        JsonInsert(festivals);
        sendButton.setText("Behov sendt");
    }

    private void popListView (ArrayList<String> needList, ListView listArea){
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
