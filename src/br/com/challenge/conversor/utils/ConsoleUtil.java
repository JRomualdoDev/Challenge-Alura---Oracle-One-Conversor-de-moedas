package br.com.challenge.conversor.utils;

import java.io.IOException;

public class ConsoleUtil {
    public static void clearScreen() {
        // Imprime 50 linhas em branco para "empurrar" o texto antigo
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
}
