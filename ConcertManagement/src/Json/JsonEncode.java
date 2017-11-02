package Json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import groupFive.Main;

import java.io.*;
import java.util.List;

import static groupFive.Main.offers;

public class JsonEncode {

    public static void JsonInsert(List<Festival> festivals) {
        try (Writer writer = new OutputStreamWriter(new FileOutputStream("src/resources/concertManagement.json") , "UTF-8")) {
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
        try (Writer writer = new OutputStreamWriter(new FileOutputStream("src/resources/offers.json") , "UTF-8")) {
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

        try (Writer writer = new OutputStreamWriter(new FileOutputStream("src/resources/offers.json") , "UTF-8")) {
            Gson newGson = new GsonBuilder().create();
            newGson.toJson(offers, writer);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}