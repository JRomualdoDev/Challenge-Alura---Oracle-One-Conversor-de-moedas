package br.com.challenge.conversor.repository;

import br.com.challenge.conversor.models.Currency;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class CurrencyRepository {

    private final Map<String, Currency> currencyMap;
    private static final Path DATA_FILE_PATH = Paths.get("resources/currencies.csv");

    public CurrencyRepository() {
        // Load the data in the moment that the repository is created.
        this.currencyMap = Collections.unmodifiableMap(loadCurrenciesFromResource());
    }

    private Map<String, Currency> loadCurrenciesFromResource() {
        // TreeMap to maintain the code in alphabetical order.
        Map<String, Currency> map = new TreeMap<>();

        String fileName = "resources/currencies.csv";
        Path filePath = Paths.get(fileName);

        try (BufferedReader reader = Files.newBufferedReader(filePath, StandardCharsets.UTF_8)) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                if (parts.length == 3) {
                    String code = parts[0].trim();
                    String name = parts[1].trim();
                    String country = parts[2].trim();
                    map.put(code, new Currency(code, name, country));
                }
            }
        }
        catch (IOException e) {
            System.err.println("Fatal error: cannot load the coin list from path: " + filePath.toAbsolutePath());
            e.printStackTrace();
            System.exit(1);
        }

        // System.out.println("Loaded " + currencyMap.size() + " currencies");
        return map;
    }

    public Collection<Currency> findAll() {
        return currencyMap.values();
    }

    public Optional<Currency> findByCode(String code) {
        return Optional.ofNullable(currencyMap.get(code.toUpperCase()));
    }

    public List<Currency> search(String query) {

        String lowerQuery = query.toLowerCase();

        return currencyMap.values().stream()
                .filter(currency ->
                        currency.code().toLowerCase().contains(lowerQuery) ||
                                currency.name().toLowerCase().contains(lowerQuery) ||
                                currency.country().toLowerCase().contains(lowerQuery)
                )
                .collect(Collectors.toList());
    }
}
