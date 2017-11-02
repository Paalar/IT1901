package Json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import groupFive.Main;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static groupFive.Main.offers;

public class JsonEncode {

    public static void JsonInsert(List<Festival> festivals) {
        try (FileWriter writer = new FileWriter("src/resources/concertManagement.json")) {
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

        try (FileWriter writer = new FileWriter("src/resources/offers.json")) {
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

        try (FileWriter writer = new FileWriter("src/resources/offers.json")) {
            Gson newGson = new GsonBuilder().create();
            newGson.toJson(offers, writer);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}