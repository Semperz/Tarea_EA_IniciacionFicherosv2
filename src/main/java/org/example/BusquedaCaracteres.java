package org.example;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Scanner;

public class BusquedaCaracteres {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String nombreArchivo = sc.nextLine();
        Path file = Path.of(nombreArchivo);
        HashMap<Character, Integer> numChars = new HashMap<>();
        try (var lector = new Scanner(file)) {
            while (lector.hasNextLine()) {
                String linea = sc.nextLine();
                for (char character : linea.toCharArray()){
                    long timesChar = linea
                            .chars()
                            .filter(c -> c == character)
                            .count();

                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}