package br.com.challenge.conversor.repository;

import br.com.challenge.conversor.models.PairConversion;
import br.com.challenge.conversor.utils.ConvertOutput;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.OffsetDateTime;
import java.util.Scanner;

public class HistoryConversionRepository {

    private static final String DATA_FOLDER = "resources";

    private static final Path historyFilePath = Paths.get(DATA_FOLDER, "historyCurrencies.txt");

    private static void ensureDirectoryExists() {
        if (!Files.exists(historyFilePath)) {
            try {
                Files.createDirectories(historyFilePath.getParent());
            } catch (IOException e) {
                System.out.println("Error for create directory: " + e.getMessage());
            }
        }
    }

    public static void save(String message) {

        // Ensure that the folder exist
        ensureDirectoryExists();

        String finalMessage = message + ", " + OffsetDateTime.now().toString() + System.lineSeparator();;

        try {
            Files.writeString(historyFilePath,
                    finalMessage,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND);

        } catch (IOException e) {
            System.err.println("Error to save " +
                    historyFilePath.toAbsolutePath() + ": " + e.getMessage());
        }
    }

    public static void readHistory() {

        Scanner sc = new Scanner(System.in);

        System.out.println();
        System.out.println("\u001B[1;36m" + "=".repeat(80) + "\u001B[0m");
        System.out.println("                       --- Conversion History ---");
        System.out.println("\u001B[1;36m" + "=".repeat(80) + "\u001B[0m");

        if (Files.notExists(historyFilePath)) {
            System.out.println("Any history file could not be found.");
            return;
        }

        try (BufferedReader reader = Files.newBufferedReader(historyFilePath)) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            System.out.println("...");
            sc.nextLine();

        } catch (IOException e) {
            System.err.println("Error to read the file: " + e.getMessage());
        }
    }
}
