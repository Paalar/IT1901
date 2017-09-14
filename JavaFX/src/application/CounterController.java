package application;


import java.awt.Button;
import java.awt.event.ActionEvent;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;


  
public class CounterController {
 
    UpOrDownCounter counter;
 
    @FXML
    Text counterOutput;
 
    @FXML
    void initialize() {
        counter = new UpOrDownCounter();
    }
 
    @FXML
    void handleCountAction() {
        counter.count1();
        counterOutput.setText("Current counter value: " + counter.getCounter());
    }
    
    @FXML
    private Stage stage;
    @FXML
    public void onChange(javafx.event.ActionEvent event) throws IOException{
    	Parent blah = FXMLLoader.load(getClass().getResource("scene2.fxml"));
        Scene scene = new Scene(blah);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.show();
    	}
    @FXML
    public void change(javafx.event.ActionEvent event) throws IOException{
    	Parent blah = FXMLLoader.load(getClass().getResource("fxml_example.fxml"));
        Scene scene = new Scene(blah);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.show();
    	}
    
}