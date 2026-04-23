package utils;

import java.io.FileWriter;
import java.util.List;

public class FeatureFileGenerator {

    public static void generateFeatureFile() {

        try {
            List<String> keywords = ExcelUtil.getAllData();

            FileWriter writer = new FileWriter("src/test/resources/features/search.feature");

            writer.write("Feature: Wikipedia Search\n\n");

            writer.write("  Scenario Outline: Search keyword <id>\n");
            writer.write("    Given user is on Wikipedia home page\n");
            writer.write("    When user searches for \"<keyword>\"\n");
            writer.write("    Then results should be validated and stored\n\n");

            writer.write("  Examples:\n");
            writer.write("    | id | keyword |\n");

            int id = 1;

            for (String keyword : keywords) {
                writer.write("    | " + id + " | " + keyword + " |\n");
                id++;
            }

            writer.close();

            System.out.println("Feature file generated successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}