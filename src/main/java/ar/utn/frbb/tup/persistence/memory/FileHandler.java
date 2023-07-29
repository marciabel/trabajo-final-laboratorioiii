package ar.utn.frbb.tup.persistence.memory;

import java.io.FileWriter;
import java.io.IOException;

public class FileHandler {

    private FileHandler() {
    }

    public static void appendJson(String jsonData, String filePath) {
        try (FileWriter fileWriter = new FileWriter(filePath, true)) {
            fileWriter.append(jsonData).append(System.lineSeparator());
            System.out.println("Data has been successfully appended to the JSON file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
