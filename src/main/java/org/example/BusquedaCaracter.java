package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

public class BusquedaCaracter {
    public static void main(String[] args) {
        System.out.println("Dime la ubicacion del archivo:");
        Scanner scRoute = new Scanner(System.in);
        String rutaArchivo = scRoute.nextLine();
        Path file = Path.of(rutaArchivo);
        System.out.println("Dime la letra a buscar:");
        Scanner scChar = new Scanner(System.in);
        Character charChose = scChar.next().charAt(0);
        try (BufferedReader lector = new BufferedReader(new FileReader(String.valueOf(file)))) {
            String linea;
            long timesChar = 0;
            while ((linea = lector.readLine()) != null) {
                    timesChar = linea
                            .chars()
                            .filter(c -> c == charChose)
                            .count();
                }
            System.out.println(timesChar);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
