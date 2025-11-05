package br.com.challenge.conversor.services;

import br.com.challenge.conversor.models.Currency;
import br.com.challenge.conversor.models.PairConversion;
import br.com.challenge.conversor.repository.CurrencyRepository;
import br.com.challenge.conversor.repository.HistoryConversionRepository;
import br.com.challenge.conversor.utils.ConsoleUtil;
import br.com.challenge.conversor.utils.ConvertOutput;

import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DefaultCurrencies {

    private final CurrencyRepository repository;

    public DefaultCurrencies(CurrencyRepository repository) {
        this.repository = repository;
    }

    public void start() {

        Scanner sc = new Scanner(System.in);

        // List defaul coins
        Map<String, Currency> currencies = new HashMap<>();

        currencies.put("JPY", new Currency("JPY", "Japanese Yen", "Japan"));
        currencies.put("USD", new Currency("USD", "United States Dollar", "United States"));
        currencies.put("ARS", new Currency("ARS", "Argentine Peso", "Argentina"));
        currencies.put("RUB", new Currency("RUB", "Russian Ruble", "Russia"));
        currencies.put("UAH", new Currency("UAH", "Ukrainian Hryvnia", "Ukraine"));
        currencies.put("MXN", new Currency("MXN", "Mexican Peso", "Mexico"));

        while(true) {

            // Create formatted table
            System.out.println();
            System.out.println("\u001B[1;36m" + "=".repeat(80) + "\u001B[0m");
            System.out.printf("\u001B[1m%-12s | %-35s | %-25s\u001B[0m%n", "CODE", "COIN BASE", "COUNTRY/REGION");
            System.out.println("\u001B[1;36m" + "=".repeat(80) + "\u001B[0m");

            for (Map.Entry<String, Currency> entry : currencies.entrySet()) {
                System.out.printf("\u001B[1;33m%-12s\u001B[0m | %-35s | %-25s%n",
                        entry.getValue().code(),
                        entry.getValue().name(),
                        entry.getValue().country()
                );
            }

            System.out.println("\u001B[1;36m" + "=".repeat(80) + "\u001B[0m");
            System.out.println();

            System.out.println("Choose the Coin Target for conversion or exit: ");
            String currencyChoose = sc.nextLine();

            currencyChoose = currencyChoose.toUpperCase();

            if (currencyChoose.equals("EXIT")) {
                ConsoleUtil.clearScreen();
                break;
            }

            String queryBase = "";
            Currency selectedCurrency = null;


            switch (currencyChoose) {
                case "JPY":
                    queryBase = "JPY";
                    selectedCurrency = currencies.get(queryBase);
                    break;
                case "USD":
                    queryBase = "USD";
                    selectedCurrency = currencies.get(queryBase);
                    break;
                case "ARS":
                    queryBase = "ARS";
                    selectedCurrency = currencies.get(queryBase);
                    break;
                case "RUB":
                    queryBase = "RUB";
                    selectedCurrency = currencies.get(queryBase);
                    break;
                case "UAH":
                    queryBase = "UAH";
                    selectedCurrency = currencies.get(queryBase);
                    break;
                case "MXN":
                    queryBase = "MXN";
                    selectedCurrency = currencies.get(queryBase);
                    break;
                default:
                    System.out.println();
                    System.out.println("Choose the Defaults curriencies");
                    System.out.println();

            }

            if (selectedCurrency != null) {

                ConsoleUtil.clearScreen();

                // Create formatted table
                System.out.println();
                System.out.println("\u001B[1;36m" + "=".repeat(80) + "\u001B[0m");
                System.out.printf("\u001B[1m%-12s | %-35s | %-25s\u001B[0m%n", "CODE", "COIN BASE", "COUNTRY/REGION");
                System.out.println("\u001B[1;36m" + "=".repeat(80) + "\u001B[0m");

                    System.out.printf("\u001B[1;33m%-12s\u001B[0m | %-35s | %-25s%n",
                            selectedCurrency.code(),
                            selectedCurrency.name(),
                            selectedCurrency.country()
                    );

                System.out.println("\u001B[1;36m" + "=".repeat(80) + "\u001B[0m");
                System.out.println();

                ApiConversion apiConvertion = new ApiConversion();
                HttpResponse<String> response = apiConvertion.convertPair("BRL", selectedCurrency.code());

                PairConversion pairConvertion = ConvertOutput.stringToObject(response);

                String historyMessage = "Conversion Rate Default, 1 BRL = " + pairConvertion.conversion_rate() + " " + selectedCurrency.code();
                HistoryConversionRepository.save(historyMessage);

                System.out.printf("\u001B[1;33m%-50s\u001B[0m | %-25s%n",
                        "Conversion Rate",
                        "1 " + "BRL" + " = " + pairConvertion.conversion_rate() + " " + selectedCurrency.code()
                );

                System.out.println("\u001B[1;36m" + "=".repeat(80) + "\u001B[0m");
                System.out.println();

                // Wait only for the user digit somenthing to proceed
                System.out.println("...");
                sc.nextLine();
            }
        }

    }
}
