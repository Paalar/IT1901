package Json;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class JsonDecode {

    public static JSONArray parseJSON() {
        JSONParser parser = new JSONParser();

        try {
            Object obj = parser.parse(new FileReader(("src/resources/concertManagement.json")));
            //JSONObject jsonObject = (JSONObject) obj;
            JSONArray jsonArray = new JSONArray();
            jsonArray.add(obj);
            System.out.println(jsonArray.get(0));

            return jsonArray;

        }
        catch (FileNotFoundException e) {e.printStackTrace(); }
        catch (IOException e) {e.printStackTrace(); }
        catch (ParseException e) {e.printStackTrace(); }
        catch (Exception e) {e.printStackTrace(); }

        return null;
    }

}
