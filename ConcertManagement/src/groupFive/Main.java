package groupFive;

import Json.Festival;
import Json.Offer;
import Json.JsonDecode;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.List;


public class Main extends Application {
    public static List<Festival> festivals;
    public static List<Offer> offers;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        primaryStage.setTitle("Concert Management");
        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.show();
        primaryStage.setResizable(false);
        festivals = JsonDecode.parseJSONFestivals();
        offers = JsonDecode.parseJSONOffers();
    }

    public void changeView(AnchorPane rootPane, String fxmlFile) {
        fxmlFile = fxmlFile.replace("ø", "o");
        fxmlFile = fxmlFile.replace("-", "");
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource(fxmlFile + ".fxml"));
            rootPane.getChildren().setAll(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
