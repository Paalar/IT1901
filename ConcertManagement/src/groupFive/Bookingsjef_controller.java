package groupFive;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import util.Constants;

public class Bookingsjef_controller {

    @FXML
    private AnchorPane rootPane;

    public void initialize() {

    }

    @FXML
    private void goBack(){
        String fxmlFileName = "Main";
        Main main = new Main();
        main.changeView(rootPane, fxmlFileName);
    }

}
