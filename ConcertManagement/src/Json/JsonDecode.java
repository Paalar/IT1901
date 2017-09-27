package Json;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class JsonDecode {

    public static List<Festival> parseJSON() {
        try {
            JsonReader jsonReader = new JsonReader(new FileReader("src/resources/test.json"));

            Gson gson = new Gson();
            Type foundListType = new TypeToken<ArrayList<Festival>>(){}.getType();

            List<Festival> festival = gson.fromJson(jsonReader, foundListType);
            return festival;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }

    }

}




