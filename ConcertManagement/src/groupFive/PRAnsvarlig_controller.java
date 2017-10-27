package groupFive;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class PRAnsvarlig_controller {

    @FXML
    private AnchorPane rootPane;





/* Searchbar funksjon
    @FXML
    private void onKeyPressSearchBar() {
        String searchText = searchBand.getText();
        String festival = "all";
        if(remove2017.isSelected()){
            festival = "previous";
        }
        putBandsInList(festival, searchText);
    }
  */

    @FXML
    private void goHome(){
        String fxmlFileName = "Main";
        Main main = new Main();
        main.changeView(rootPane, fxmlFileName);
    }
}
