package groupFive;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;
import org.json.simple.JSONArray;

import java.io.IOException;
import java.util.ArrayList;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        primaryStage.setTitle("Concert Management");
        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.show();

        JSONArray jsonArray = JsonDecode.parseJSON();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void changeView(AnchorPane rootPane, String fxmlFile) {
        // Denne burde også legge til hva du har endret til i en stack så vi kan lett lage fram og tilbake knapper.

        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource(fxmlFile + ".fxml"));
            rootPane.getChildren().setAll(pane);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
