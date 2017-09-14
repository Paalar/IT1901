package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class Controller1 {
    @FXML
    private AnchorPane rootPane;

    @FXML
    private TextArea textArea1;

    @FXML public void clickBtn() {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("page2.fxml"));
            rootPane.getChildren().setAll(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML public void clickBtn2() {
        System.out.println(textArea1.getText());

    }
}
