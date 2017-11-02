package Json;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class JsonDecode {

    public static List<Festival> parseJSONFestivals() {
        try {
            JsonReader jsonReader = new JsonReader(new InputStreamReader(new FileInputStream("src/resources/concertManagement.json"), "UTF-8"));
            Gson gson = new Gson();
            Type foundListType = new TypeToken<ArrayList<Festival>>(){}.getType();
            List<Festival> festival = gson.fromJson(jsonReader, foundListType);
            return festival;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static List<Offer> parseJSONOffers() {
        try {
            JsonReader jsonReader = new JsonReader(new InputStreamReader(new FileInputStream("src/resources/offers.json"), "UTF-8"));
            Gson gson = new Gson();
            Type foundListType = new TypeToken<ArrayList<Offer>>(){}.getType();
            List<Offer> offers = gson.fromJson(jsonReader, foundListType);
            return offers;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

}




