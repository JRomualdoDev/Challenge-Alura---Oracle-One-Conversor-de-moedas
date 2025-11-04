package br.com.challenge.conversor.services;

import br.com.challenge.conversor.models.Currency;
import br.com.challenge.conversor.models.PairConversion;
import br.com.challenge.conversor.repository.CurrencyRepository;
import br.com.challenge.conversor.utils.ConsoleUtil;
import br.com.challenge.conversor.utils.ConvertOutput;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.Scanner;

public class CurrencyPromptPair {

    private final CurrencyRepository repository;

    public CurrencyPromptPair(CurrencyRepository repository) {
        this.repository = repository;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=".repeat(80));
        System.out.println("Currency Converter - Smart Search");
        System.out.println("=".repeat(80));
        System.out.println();

        while (true) {
            System.out.print("Search Coin Base (code, name, or country) or 'exit': ");
            String coinBase = scanner.nextLine();

            if (coinBase == null || coinBase.trim().equalsIgnoreCase("exit")) {
                ConsoleUtil.clearScreen();
                break;
            }

            String queryBase = coinBase.trim();
            if (queryBase.isEmpty()) {
                continue;
            }

            // Search logic and table display
            List<Currency> resultsBase = repository.search(queryBase);

            if (resultsBase.isEmpty()) {
                System.out.println("\u001B[1;31mNo currency found containing '" + queryBase + "'.\u001B[0m");
            } else {
                // Create formatted table
                System.out.println();
                System.out.println("\u001B[1;36m" + "=".repeat(80) + "\u001B[0m");
                System.out.printf("\u001B[1m%-12s | %-35s | %-25s\u001B[0m%n", "CODE", "COIN BASE", "COUNTRY/REGION");
                System.out.println("\u001B[1;36m" + "=".repeat(80) + "\u001B[0m");

                for (Currency currency : resultsBase) {
                    System.out.printf("\u001B[1;33m%-12s\u001B[0m | %-35s | %-25s%n",
                            currency.code(),
                            truncate(currency.name(), 35),
                            truncate(currency.country(), 25)
                    );
                }

                System.out.println("\u001B[1;36m" + "=".repeat(80) + "\u001B[0m");
                System.out.println();


                // Coin Target
                System.out.print("Search Coin Target (code, name, or country) or 'exit': ");
                String coinTarget = scanner.nextLine();

                if (coinTarget == null || coinTarget.trim().equalsIgnoreCase("exit")) {
                    ConsoleUtil.clearScreen();
                    break;
                }

                String queryTarget = coinTarget.trim();
                if (queryTarget.isEmpty()) {
                    continue;
                }

                // Search logic and table display
                List<Currency> resultsTarget = repository.search(queryTarget);

                if (resultsTarget.isEmpty()) {
                    System.out.println("\u001B[1;31mNo currency found containing '" + queryTarget + "'.\u001B[0m");
                } else {
                    // Create formatted table
                    System.out.println();
                    System.out.println("\u001B[1;36m" + "=".repeat(80) + "\u001B[0m");
                    System.out.printf("\u001B[1m%-12s | %-35s | %-25s\u001B[0m%n", "CODE", "COIN BASE", "COUNTRY/REGION");
                    System.out.println("\u001B[1;36m" + "=".repeat(80) + "\u001B[0m");

                    for (Currency currency : resultsBase) {
                        System.out.printf("\u001B[1;33m%-12s\u001B[0m | %-35s | %-25s%n",
                                currency.code(),
                                truncate(currency.name(), 35),
                                truncate(currency.country(), 25)
                        );
                    }

                    System.out.println("\u001B[1;36m" + "=".repeat(80) + "\u001B[0m");
                    System.out.printf("\u001B[1m%-12s | %-35s | %-25s\u001B[0m%n", "CODE", "COIN TARGET", "COUNTRY/REGION");
                    System.out.println("\u001B[1;36m" + "=".repeat(80) + "\u001B[0m");

                    for (Currency currency : resultsTarget) {
                        System.out.printf("\u001B[1;33m%-12s\u001B[0m | %-35s | %-25s%n",
                                currency.code(),
                                truncate(currency.name(), 35),
                                truncate(currency.country(), 25)
                        );
                    }

                    System.out.println("\u001B[1;36m" + "=".repeat(80) + "\u001B[0m");
                    System.out.println();

                    Currency coinBaseCurrency = resultsBase.get(0);
                    Currency coinTargetCurrency = resultsTarget.get(0);

                    ApiConversion apiConvertion = new ApiConversion();
                    HttpResponse<String> response = apiConvertion.convertPair(coinBaseCurrency.code(), coinTargetCurrency.code());

                    PairConversion pairConvertion = ConvertOutput.stringToObject(response);


                    System.out.printf("\u001B[1;33m%-50s\u001B[0m | %-25s%n",
                            "Conversion Rate",
                            "1 " + coinBaseCurrency.code() + " = " + pairConvertion.conversion_rate() + " " + coinTargetCurrency.code()
                    );

                    System.out.println("\u001B[1;36m" + "=".repeat(80) + "\u001B[0m");
                    System.out.println();
                }
            }
        }

//        scanner.close();
    }

    private String truncate(String text, int maxLength) {
        if (text == null) return "";
        if (text.length() <= maxLength) return text;
        return text.substring(0, maxLength - 3) + "...";
    }
}