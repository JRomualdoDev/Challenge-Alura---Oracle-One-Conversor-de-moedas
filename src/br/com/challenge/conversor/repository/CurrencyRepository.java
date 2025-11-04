package br.com.challenge.conversor.repository;

import br.com.challenge.conversor.models.Currency;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class CurrencyRepository {

    private final Map<String, Currency> currencyMap;

    public CurrencyRepository() {
        // Load the data in the moment that the repository is created.
        this.currencyMap = Collections.unmodifiableMap(loadCurrenciesFromResource());
    }

    private Map<String, Currency> loadCurrenciesFromResource() {
        // TreeMap to maintain the code in alphabetical order.
        Map<String, Currency> map = new TreeMap<>();

        String fileName = "br/com/challenge/conversor/resources/currencies.csv";

        // Load from the class and search the file currency.csv and open like stream
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(fileName);
            // Translate variable is(bytes) to text
            InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(isr)) {

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
        catch (Exception e) {
            System.err.println("Fatal error: cannot load the coin list.");
            e.printStackTrace();
            System.exit(1);
        }
//        System.out.println("Loaded " + currencyMap.size() + " currencies");
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
