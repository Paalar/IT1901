package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class Controller2 {
    @FXML
    private AnchorPane rootPane;

    @FXML public void clickBtn() {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("page1.fxml"));
            rootPane.getChildren().setAll(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
