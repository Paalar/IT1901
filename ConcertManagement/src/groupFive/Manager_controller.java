package groupFive;

import Json.*;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import util.Constants;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Manager_controller {
    @FXML
    private ListView listOfOfferView;

    @FXML
    private  ListView listOfNeedSendt;

    @FXML
    private  TextField listOfNeedsAddedLabel;

    @FXML
    private TextField need;

    @FXML
    private TextField listOfNeedsAdded;

    @FXML
    private  TextField listOfNeedSendtLabel;

    @FXML
    private Pane needListArea;
    
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

    @FXML
    private ListView listOfNeeds;

    private List<ListView> listViews;

    @FXML
    private Button sendButton;

    @FXML
    private  Button addButton;

    @FXML
    private List<TextField> textFields;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private VBox needsNotSent1;

    @FXML
    private List<String> artistNeeds;

    private static ArrayList<String> needList;
    private static Offer offers = new Offer("Astrid S", "Storsalen", "24.10.2017", 200, needList);
    private static Offer offers1 = new Offer("Martin Garrix", "Dødens dal", "24.10.2017", 500, needList);
    private static Offer offers2 = new Offer("Snoopy", "Storsalen", "23.10.2017", 100, needList);
    private static Offer offers3 = new Offer("Dagny", "Storsalen", "22.10.2017", 10000, needList);
    private  static  ArrayList<Offer> listOfOffers = new ArrayList<>(Arrays.asList(offers,offers1,offers2,offers3));

    @FXML
    public void initialize() {
        listViews = Arrays.asList(listOfOfferView, listOfNeeds);
        //putOfferInChoiceBox();
        //hideStuffOnStart();
        addButtons(listOfOffers, needsNotSent1);
    }

    /**private void createChoiceBoxListener() {
        choiceBoxFestivals.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                putConcertsInSceneLists(choiceBoxFestivals.getItems().get((Integer) newValue).toString());
                System.out.println(choiceBoxFestivals.getItems().get((Integer) newValue));
            }
        });
    }**/

    private void addButtons(ArrayList<Offer> offers, VBox needsList) {
        for (Offer offer : offers) {
            Button btnNumber = createButton(offer);
            System.out.println(offer.getArtist());
            needsList.getChildren().add(btnNumber);
        }
    }

    private Button createButton(Offer name) {
        final Button button = new Button(name.getArtist());
        button.setId("offerButt");
        button.setPrefSize(200,50);
        button.setOnMouseClicked(event -> {
            try {
                updateInfo(name.getArtist(), name.getScene(), name.getDato());
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Du må velge en jobb");
            }
        });
        return button;
    }


   /** private void putSceneNamesInTextBox(String festival) {
        for (Festival f : Main.festivals) {
            if (f.getFestival().equals(festival)) {
                for (int i = 0; i < 3; i++) {
                    textFields.get(i).setText(f.getScene().get(i).getNavn());
                }
            }
        }
    }

    private void putConcertsInSceneLists(final String festival) {
        putSceneNamesInTextBox(festival);
        for (Festival f : Main.festivals) {
            if (f.getFestival().equals(festival)) {
                // Når du har funnet riktig festival.
                for (int i = 0; i < 3; i++) {
                    final int iFinal = i;
                    // Denne må være final for å kunne bruke i en anonym klasse.

                    listViews.get(i).setEditable(true);
                    List<String> concerts = new ArrayList<>();
                    for (Concert c : f.getScene().get(i).getKonsert()) {
                        concerts.add(c.getArtist());
                    }
                    ObservableList<String> observableListToAdd = FXCollections.observableArrayList(concerts);
                    listViews.get(i).setItems(observableListToAdd);
                    listViews.get(i).setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            try {
                                String itemClicked = listViews.get(iFinal).getSelectionModel().getSelectedItem().toString();
                                int whichScene = iFinal;
                                showArbeidere(itemClicked, whichScene, festival);
                            } catch (Exception e) {
                                System.out.println("Du har ikke valgt en konsert.");
                            }


                        }
                    });
                }
            }
        }
    }**/

    @FXML
    private void hideStuffOnStart(){
        addButton.setVisible(false);
        need.setVisible(false);
        artist.setVisible(false);
        scene.setVisible(false);
        listOfNeeds.setVisible(false);
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
        listOfNeeds.setVisible(true);
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
        //json.encode.needs(artistNeeds);
        sendButton.setText("Behov sendt");
    }

    @FXML
    private  void addNeedsToList(){
        String aNeed = need.getText();
        artistNeeds.add(aNeed);
        need.setText("Behov: ");
    }

    @FXML
    private void updateScene(String sceneName){
        System.out.println("Scene: " + sceneName);
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
    private  void loadNeedsThatisFromBefore(String artistName, String sceneName, String dato){
       /** if(artistName == "Something" && sceneName == "Something" && dato == "Something){
            for(int i = 0; i < "someList"; i++){
                listOfNeeds.add(someList.aNeed);
            }
        }**/
    }

    @FXML
    private  void updateInfo(String artistName, String sceneName, String dato){
        this.artistNameString = artistName;
        this.sceneNameString = sceneName;
        this.datoString = dato;
        updateArtistName(artistName);
        updateScene(sceneName);
        updateDate(dato);
        loadNeedsThatisFromBefore(artistName, sceneName, dato);
    }

    public void whenClikdOnArtist(ActionEvent actionEvent) {
        String artistName = "Astrid S";
        String sceneName = "Storsalen";
        String dato = "25-10-2017";
        updateInfo(artistName,sceneName, dato);
        showStuff();
    }
}
