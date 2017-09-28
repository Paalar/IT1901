package util;

import Json.Concert;
import Json.Festival;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.ArrayList;

public class Filter {
    public static List<Concert> getAllConcerts(List<Festival> festivals) {
        // Denne funksjonen går gjennom alle festivaler og gir ut en liste over konserter.
        List<Concert> filtered = new ArrayList<>();
        for (Festival f : festivals) {
            //filtered.addAll(f.getKonsert());
        }
        return filtered;
    }

    public static ObservableList<String> getAllFestivalsObservableList(List<Festival> festivals) {
        // Denne gjør en liste over festivaler over til en observablelist med bare strings som er navnet til festivalen.
        List<String> festivalsString = new ArrayList<>();
        for (Festival f : festivals) {
            festivalsString.add(f.getFestival());
        }
        return FXCollections.observableArrayList(festivalsString);
    }

//    public static ArrayList<String> filterList(ArrayList<String> wholeList, String split) {
//        ArrayList<String> listToAdd = new ArrayList<>();
//        for (int x  = 0; x < wholeList.size(); x++) {
//            if (wholeList.get(x).startsWith(split)) {
//                listToAdd.add(wholeList.get(x).replace(split, ""));
//            }
//        }
//        return listToAdd;
//    }
}
