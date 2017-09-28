package util;

import Json.*;
import groupFive.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.ArrayList;

public class Filter {
    public static List<Concert> getAllConcerts(String festival) {
        List<Concert> concerts = new ArrayList<>();
        for (Festival f : Main.festivals) {
            if (f.getFestival().equals(festival)) {
                // Nå har du funnet riktig festival.
                for (Scene s : f.getScene()) {
                    for (Concert c : s.getKonsert()) {
                        concerts.add(c);
                    }
                }
            }
        }
        return concerts;
    }

    public static List<Scene> getAllScenes(String festival) {
        List<Scene> scenes = new ArrayList<>();
        for (Festival f : Main.festivals) {
            if (f.getFestival().equals(festival)) {
                for (Scene s : f.getScene()) {
                    scenes.add(s);
                }
            }
        }
        return  scenes;
    }


    public static ObservableList<String> getAllFestivalsObservableList(List<Festival> festivals) {
        // Denne gjør en liste over festivaler over til en observablelist med bare strings som er navnet til festivalen.
        List<String> festivalsString = new ArrayList<>();
        for (Festival f : festivals) {
            festivalsString.add(f.getFestival());
        }
        return FXCollections.observableArrayList(festivalsString);
    }

    public static ObservableList<String>getAllTeknikers(String festival) {
        List<String> teknikerStrings = new ArrayList<>();
        
        return FXCollections.observableArrayList(teknikerStrings);
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
