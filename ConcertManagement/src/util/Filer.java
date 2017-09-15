package util;

import java.util.ArrayList;

public class Filer {
    public static ArrayList<String> filterList(ArrayList<String> wholeList, String split) {
        ArrayList<String> listToAdd = new ArrayList<>();
        for (int x  = 0; x < wholeList.size(); x++) {
            if (wholeList.get(x).startsWith(split)) {
                listToAdd.add(wholeList.get(x).replace(split, ""));
            }
        }
        return listToAdd;
    }
}
