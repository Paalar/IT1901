package util;

import Json.Concert;
import Json.Festival;

import java.util.List;
import java.util.ArrayList;

public class Filter {
    public static List<Concert> getAllConcerts(List<Festival> festivals) {
        List<Concert> filtered = new ArrayList<>();
        for (Festival f : festivals) {
            filtered.addAll(f.getKonsert());
        }
        return filtered;
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
