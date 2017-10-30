package Json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import groupFive.Main;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import static groupFive.Main.festivals;
import static groupFive.Main.offers;

public class JsonEncode {

    public static void JsonInsert(List<Festival> festivals) {

        /*   Dette viser hvordan man legger til en ny artist, Jay Z og Day Z.
             Først må man gå inn i konserten man skal lage, så lage et nytt Concert objekt.

        List<Concert> concert = festivals.get(0).getScene().get(0).getKonsert();
        Concert konsert = new Concert();
        konsert.setArtist("Jay Z");
        konsert.setDato("31-12-2017");
        konsert.setBillettPris(500);
        konsert.setPris(10001);
        concert.add(konsert);
        Concert konsert1 = new Concert();
        konsert1.setArtist("Day Z");
        konsert1.setDato("31-12-2017");
        konsert1.setBillettPris(500);
        konsert1.setPris(10001);
        concert.add(konsert1);
        festivals.get(0).getScene().get(0).setKonsert(concert);

        */

        try (FileWriter writer = new FileWriter("src/resources/concertManagement.json")){
            Gson newGson = new GsonBuilder().create();
            newGson.toJson(festivals, writer);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeNewOffer(String date, String artist, int price) {
        Offer offer = new Offer();
        offer.setArtist(artist);
        offer.setDato(date);
        offer.setPris(price);
        offer.setStatus("ikke vurdert");
        Main.offers.add(offer);

        try (FileWriter writer = new FileWriter("src/resources/offers.json")){
            Gson newGson = new GsonBuilder().create();
            newGson.toJson(offers, writer);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void acceptOrRejectOffer(String date, String artist, int price, String status) {
        Offer offer = new Offer();
        offer.setArtist(artist);
        offer.setDato(date);
        offer.setPris(price);
        offer.setStatus(status);
        Main.offers.add(offer);

        try (FileWriter writer = new FileWriter("src/resources/offers.json")){
            Gson newGson = new GsonBuilder().create();
            newGson.toJson(offers, writer);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}