package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BusquedaCaracteres {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String rutaArchivo = sc.nextLine();
        Path file = Path.of(rutaArchivo);
        HashMap<Character, Integer> numChars = new HashMap<>();
        try (BufferedReader lector = new BufferedReader(new FileReader(String.valueOf(file)))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                for (char character : linea.toCharArray()) {
                    long timesChar = linea
                            .chars()
                            .filter(c -> c == character)
                            .count();
                    numChars.put(character, (int) timesChar);
                }
            }
            Character charAppear = numChars
                        .entrySet()
                        .stream()
                        .max(Map.Entry.comparingByValue())
                        .orElse(null)
                        .getKey();
            System.out.println(charAppear);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}