package utils;

import java.io.FileWriter;
import java.io.IOException;

public class FileUtil {

    public static void writeToFile(String content) {
        try {
            System.out.println("Inside FileUtil: " + content);

            FileWriter writer = new FileWriter("output.txt", true);
            writer.write(content + "\n");
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}