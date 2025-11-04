package br.com.challenge.conversor.services;

import br.com.challenge.conversor.models.Currency;
import br.com.challenge.conversor.repository.CurrencyRepository;
import br.com.challenge.conversor.utils.ConsoleUtil;

import java.util.Collection;
import java.util.Map;
import java.util.Scanner;

public class MainPrompt {

    static CurrencyRepository repository = new CurrencyRepository();

    public static void showDefaultMenu() {

        Scanner sc = new Scanner(System.in);

        String ANSI_RESET = "\u001B[0m";    // Reset default color
        String ANSI_CYAN = "\u001B[36m";    // Ciano
        String ANSI_YELLOW = "\u001B[33m";  // yellow

        while(true) {

            System.out.println(ANSI_CYAN + "==========================================" + ANSI_RESET);
            System.out.println(ANSI_CYAN + "           " + ANSI_YELLOW + "C" + ANSI_CYAN + "urrency " + ANSI_YELLOW + "C" + ANSI_CYAN + "onversion" + ANSI_RESET);
            System.out.println(ANSI_CYAN + "==========================================" + ANSI_RESET);

            System.out.println("Select Option :");
            System.out.println("----------------------------------------");
            System.out.println(" 1 - Default Currencies");
            System.out.println(" 2 - Dinamic selection currencies");
            System.out.println(" 3 - List currencies");
            System.out.println(" 4 - Exit program");
            System.out.println("----------------------------------------");
            System.out.print("Enter your choice: ");

            String select = sc.nextLine();

            if (select == null || select.trim().equalsIgnoreCase("4")) {
                System.out.println("Goodbye!");
                break;
            }

            switch (select) {
                case "1":
                    ConsoleUtil.clearScreen();
                    showCurrencyDefault();
                    break;
                case "2":
                    ConsoleUtil.clearScreen();
                    showCurrencyPair();
                    break;
                case "3":
                    ConsoleUtil.clearScreen();
                    showAllListCurrencies();
                    break;
                default:
                    System.out.println("Select a valid option");
                    showDefaultMenu();

            }
        }

        sc.close();

    }

    private static void showCurrencyPair() {

        CurrencyPromptPair prompt = new CurrencyPromptPair(repository);

        prompt.start();

    }

    private static void showCurrencyDefault() {

        DefaultCurrencies defaultCurrencies = new DefaultCurrencies(repository);

        defaultCurrencies.start();

    }

    private static void showAllListCurrencies() {

        Collection<Currency> currencies = repository.findAll();

        Scanner sc = new Scanner(System.in);

        System.out.println();
        System.out.println("\u001B[1;36m" + "=".repeat(80) + "\u001B[0m");
        System.out.printf("\u001B[1m%-12s | %-35s | %-25s\u001B[0m%n", "CODE", "NAME", "COUNTRY/REGION");
        System.out.println("\u001B[1;36m" + "=".repeat(80) + "\u001B[0m");

        for (Currency currency : currencies.stream().toList()) {
            System.out.printf("\u001B[1;33m%-12s\u001B[0m | %-35s | %-25s%n",
                    currency.code(),
                    currency.name(),
                    currency.country()
            );
        }

        System.out.println("\u001B[1;36m" + "=".repeat(80) + "\u001B[0m");
        System.out.println();

        System.out.println("...");
        sc.nextLine();

        ConsoleUtil.clearScreen();
    }
}
