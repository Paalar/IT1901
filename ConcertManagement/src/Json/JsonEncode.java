package Json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class JsonEncode {

    public static void JsonInsert(List<Festival> festivals) {
        /*
        List<Festival> festivals = JsonDecode.parseJSON();
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

        try (FileWriter writer = new FileWriter("src/resources/test.json")){
            Gson newGson = new GsonBuilder().create();
            newGson.toJson(festivals, writer);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
