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

import static groupFive.constants.*;

public class Main extends Application {
    constants constanses = new constants();

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
        if(constants.getHome() == null){
            setHome("main");
            setCurrent("main");
        }
        constanses.setPrev(constanses.getCurrent());
        constanses.setCurrent(fxmlFile);
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource(fxmlFile + ".fxml"));
            rootPane.getChildren().setAll(pane);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void changeViewBack(AnchorPane rootPane, String fxmlFile){
        constanses.setForw(fxmlFile);
        constanses.setCurrent(fxmlFile);
        changeView(rootPane,constanses.getPrev());
    }

    public static ArrayList<String> filterList(ArrayList<String> wholeList, String split) {
        ArrayList<String> listToAdd = new ArrayList<>();
        for (int x  = 0; x < wholeList.size(); x++) {
            if (wholeList.get(x).startsWith(split)) {
                listToAdd.add(wholeList.get(x).replace(split, ""));
            }
        }
        return listToAdd;
    }
}
