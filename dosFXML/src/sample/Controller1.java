package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class Controller1 {
    @FXML
    private AnchorPane rootPane;

    @FXML
    private TextArea textArea1;

    @FXML
    private ListView list1;

    public static final ObservableList data = FXCollections.observableArrayList();

    @FXML public void clickBtn() {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("page2.fxml"));
            rootPane.getChildren().setAll(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML public void clickBtn2() {
        list1.setEditable(true);
        data.add(textArea1.getText());
        list1.setItems(data);
        list1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("clicked on " + list1.getSelectionModel().getSelectedItem());
            }
        });
    }
    public void openItemList(String whichListItemClicked) {
        System.out.println(whichListItemClicked);
    }
}
