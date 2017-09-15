package IO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReadWriteConfig {
    public static ArrayList<String> readFile(String filename) {
        ArrayList<String> output = new ArrayList<>();
        filename = filename.replace('ø','o');
        filename = filename.toLowerCase(); //filnavn er med små bokstaver.
        // Fordi filen heter arrangor.txt må vi swappe ø med o.
        try {
            String address = new File("").getAbsolutePath() + "/src/resources/" + filename + ".txt";
            File file = new File(address);
            FileReader reader = new FileReader(file);
            Scanner scanner = new Scanner(reader);
            while (scanner.hasNext()) {
                String toAdd = scanner.nextLine();
                output.add(toAdd);
            }
        } catch (IOException e) {
            System.out.println(e);
        }

        return output;
    }

    public static void writeFile(String filename) {

    }
}
