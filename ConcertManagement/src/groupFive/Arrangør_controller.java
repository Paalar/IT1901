package groupFive;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.util.ArrayList;

public class Arrangør_controller {
    @FXML
    private ListView listView;

    @FXML
    public void initialize() {
        addItemsToList();
    }

    private void addItemsToList() {
        ArrayList<String> wholeList = ReadWriteConfig.readFile("arrangor");
        ArrayList<String> listToAdd = new ArrayList<>();
        listView.setEditable(true);
        for (int x  = 0; x < wholeList.size(); x++) {
            if (wholeList.get(x).startsWith("__")) {
                listToAdd.add(wholeList.get(x));
            }
        }
        ObservableList<String> observableListToAdd = FXCollections.observableArrayList(listToAdd);
        listView.setItems(observableListToAdd);
    }

}
