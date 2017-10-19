package util;

import Json.*;
import groupFive.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class Filter {
    public static List<Concert> getAllConcertsForAllFestivals() {
        List<Concert> concerts = new ArrayList<>();
        for (Festival f : Main.festivals) {
            concerts.addAll(getAllConcerts(f.getFestival()));
        }
        return concerts;
    }
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
        return scenes;
    }

    public static List<String> getAllScenesString(String festival) {
        List<String> scenes = new ArrayList<>();
        for (Festival f : Main.festivals) {
            if (f.getFestival().equals(festival)) {
                for (Scene s : f.getScene()) {
                    scenes.add(s.getNavn());
                }
            }
        }
        return scenes;
    }


    public static ObservableList<String> getAllFestivalsObservableList(List<Festival> festivals) {
        // Denne gjør en liste over festivaler over til en observablelist med bare strings som er navnet til festivalen.
        List<String> festivalsString = new ArrayList<>();
        for (Festival f : festivals) {
            festivalsString.add(f.getFestival());
        }
        return FXCollections.observableArrayList(festivalsString);
    }

    public static ObservableList<String> getAllBandsObservableList() {
        // Denne går gjennom alle festivalene og returnerer observablelist av alle unike bandene.
        List<String> artistStrings = new ArrayList<>();
        for (Festival f : Main.festivals) {
            for (Scene s : f.getScene()) {
                for (Concert c : s.getKonsert()) {
                    if (!artistStrings.contains(c.getArtist())) {
                        artistStrings.add(c.getArtist());
                    }
                }
            }
        }
        return FXCollections.observableArrayList(artistStrings);
    }

    public static ObservableList<String> getAllGenresObservableList() {
        // Denne går gjennom alle festivalene og returnerer observablelist av alle unike bandene.
        List<String> genreStrings = new ArrayList<>();
        for (Festival f : Main.festivals) {
            for (Scene s : f.getScene()) {
                for (Concert c : s.getKonsert()) {
                    if (!genreStrings.contains(c.getSjanger())) {
                        genreStrings.add(c.getSjanger());

                    }
                }
            }
        }
        return FXCollections.observableArrayList(genreStrings);
    }

    //Denne brukes til å søke etter band i bookingansvarlig
    public static ObservableList<String> searchAllBandsObservableList(String festival, String searchText) {
        // Denne går gjennom alle festivalene og returnerer observablelist av alle unike bandene dersom det blir søkt etter
        List<String> artistStrings = new ArrayList<>();

        //henter først ut alle artister, fra alle år
        for (Festival f : Main.festivals) {
            for (Scene s : f.getScene()) {
                for (Concert c : s.getKonsert()) {
                    if (!artistStrings.contains(c.getArtist()) && c.getArtist().toLowerCase().contains(searchText.toLowerCase())) {
                        artistStrings.add(c.getArtist());
                    }
                }
            }
        }


        //Hvis funksjonen blir kalt med stringen "previous", så fjernes alle artistene fra return-listen.
        // Deretter legges artistene fra UKA 2013 og 2015 til i listen, men kun dersom de ikke spiller på UKA 2017
        if (festival == "previous") {
            artistStrings.clear();
            List<Concert> concerts2017 = getAllConcerts("UKA 2017");
            List<String> allArtists2017 = new ArrayList<>();
            List<Concert> allConcerts = getAllConcerts("UKA 2015");
            allConcerts.addAll(getAllConcerts("UKA 2013"));

            for (Concert c : concerts2017) {
                if (!allArtists2017.contains(c.getArtist()) && c.getArtist().toLowerCase().contains(searchText.toLowerCase())) {
                    allArtists2017.add(c.getArtist());
                }
            }
            for (Concert c : allConcerts) {
                if (!artistStrings.contains(c.getArtist()) && !allArtists2017.contains(c.getArtist()) && c.getArtist().toLowerCase().contains(searchText.toLowerCase())) {
                    artistStrings.add(c.getArtist());
                }
            }
        }
        return FXCollections.observableArrayList(artistStrings);
    }


    public static ObservableList<String> getAllTeknikers (String festival, String searchText){
        // Denne går gjennom alle konserter for en festival og legger til alle unike som inneholder søketeksten i navnet.
        List<String> teknikerStrings = new ArrayList<>();
        List<Concert> concerts = getAllConcerts(festival);
        for (Concert c : concerts) {
            for (SoundTech st : c.getLyd()) {
                if ((!teknikerStrings.contains(st.getNavn())) && st.getNavn().toLowerCase().contains(searchText.toLowerCase())) {
                    teknikerStrings.add(st.getNavn());
                }
            }

            for (LightTech lt : c.getLys()) {
                if ((!teknikerStrings.contains(lt.getNavn())) && lt.getNavn().toLowerCase().contains(searchText.toLowerCase())) {
                    teknikerStrings.add(lt.getNavn());
                }
            }
        }
        return FXCollections.observableArrayList(teknikerStrings);
    }


    public static int getPopularity(String band) {
        // Denne går gjennom alle festivaler og finner bandet og returnerer den nyeste popularitetsverdier for bandet.
        // Hvis den ikke finner bandet returnerer den 0.
        List<Concert> concerts = getAllConcertsForAllFestivals();
        for (Concert c : concerts) {
            if (c.getArtist().equals(band)) {
                return c.getPopulæritet();
            }
        }
        return 0;
    }

    public static String getGenre(String band) {
        // Denne går gjennom alle festivaler og finner bandet og returnerer sjangeren.
        // Hvis den ikke finner bandet returnerer den "".
        List<Concert> concerts = getAllConcertsForAllFestivals();
        for (Concert c : concerts) {
            if (c.getArtist().equals(band)) {
                return c.getSjanger();
            }
        }
        return "";
    }

    public static int getSales(String band) {
        // Denne går gjennom alle festivaler og finner bandet og returnerer salg.
        // Hvis den ikke finner bandet returnerer den 0.
        List<Concert> concerts = getAllConcertsForAllFestivals();
        for (Concert c : concerts) {
            if (c.getArtist().equals(band)) {
                return c.getSalg();
            }
        }
        return 0;
    }

    public static int getSceneSize(String scene) {
        // Denne går gjennom alle festivaler og finner bandet og returnerer salg.
        // Hvis den ikke finner bandet returnerer den 0.
        for (Festival f : Main.festivals) {
            for (Scene s : f.getScene()) {
                if (s.getNavn().equals(scene)) {
                    return s.getPlasser();
                }
            }
        }
        return 0;
    }

    public static int getPrice(String band) {
        // Denne går gjennom alle festivaler og finner bandet og returnerer pris.
        // Hvis den ikke finner bandet returnerer den 0.
        List<Concert> concerts = getAllConcertsForAllFestivals();
        for (Concert c : concerts) {
            if (c.getArtist().equals(band)) {
                return c.getPris();
            }
        }
        return 0;
    }

    public static ObservableList<String> getConcertsAndScenesForBand(String band) {
        List<String> outputList = new ArrayList<>();
        for (Festival f : Main.festivals) {
            for (Scene s : f.getScene()) {
                for (Concert c  : s.getKonsert()) {
                    if (c.getArtist().equals(band)) {
                        String toAddStr = f.getFestival() + " : " + s.getNavn();
                        outputList.add(toAddStr);
                    }
                }
            }
        }
        return FXCollections.observableArrayList(outputList);
    }

    public static ObservableList<String> getTekniskeBehov(String band) {
        // Denne går gjennom alle festivaler og finner bandet og returnerer listen med tekniske behov.
        List<String> allTekniskeBehov = new ArrayList<>();
        for (Festival f : Main.festivals) {
            if(f.getFestival().equals("UKA 2017")){
                for (Scene s : f.getScene()) {
                    for (Concert c : s.getKonsert()) {
                        for (tekniskeBehov t : c.getTekniskeBehov()){
                            if (c.getArtist().equals(band)) {
                                String addToStr = t.getBehov();
                                allTekniskeBehov.add(addToStr);
                            }
                        }
                    }
                }
            }

        }
        return FXCollections.observableArrayList(allTekniskeBehov);
    }

    public static List<String> getAllBandsFestivalsExcept(String exceptionFestival) {
        List<String> artistsUkaException = new ArrayList<>();
        List<String> artistsOlder = new ArrayList<>();
        for (Festival f : Main.festivals) {
            for (Scene s : f.getScene()) {
                for (Concert c : s.getKonsert()) {
                    if (f.getFestival().equals(exceptionFestival)) {
                        if (!artistsUkaException.contains(c.getArtist())) {
                            artistsUkaException.add(c.getArtist());
                        }
                    } else {
                        if (!artistsOlder.contains(c.getArtist())) {
                            artistsOlder.add(c.getArtist());
                        }
                    }
                }
            }
        }
        artistsOlder.removeAll(artistsUkaException);
        return artistsOlder;
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
