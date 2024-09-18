package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class Ordenaciones {
    public static void main(String[] args) {
        System.out.println("Dime la ubicacion del archivo:");
        Scanner scRoute = new Scanner(System.in);
        String rutaArchivo = scRoute.nextLine();
        Path file = Path.of(rutaArchivo);
        System.out.println("Pon el numero del tipo de ordenación quieres de las siguientes:" + '\n' +
                "1. ascendente case-sensitive" + '\n' +
                "2. ascendente case-insensitive" + '\n' +
                "3. descendente case-sensitive" + '\n' +
                "4. descendente case-insensitive");
        Scanner scSort = new Scanner(System.in);
        String sortType = scSort.nextLine();
        try (BufferedReader lector = new BufferedReader(new FileReader(String.valueOf(file)))) {
            String linea;
            ArrayList<String> stringList = new ArrayList<>();
            String resultadoOrdenado = "";
            while ((linea = lector.readLine()) != null) {
                stringList.add(linea);
                if (sortType.charAt(0) == '1') {
                    resultadoOrdenado = stringList.stream().sorted().toList().toString();
                }
                if (sortType.charAt(0) == '2') {
                    resultadoOrdenado = stringList.stream().sorted(String.CASE_INSENSITIVE_ORDER).toList().toString();
                }
                if (sortType.charAt(0) == '3') {
                    resultadoOrdenado = stringList.stream().sorted(Comparator.reverseOrder()).toList().toString();
                }
                if (sortType.charAt(0) == '4') {
                    resultadoOrdenado = stringList.stream().sorted(String.CASE_INSENSITIVE_ORDER.reversed()).toList().toString();
                }
            }
            System.out.println(resultadoOrdenado);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
