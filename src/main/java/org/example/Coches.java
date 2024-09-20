package org.example;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Coches {
    public static void main(String[] args) {
        Path filePath = Path.of("res/coches.txt");
        Map<String, String> cocheMarca= new HashMap<>();
        System.out.println("Donde quieres crear el archivo nuevo?" + '\n');
        Scanner scNewFile = new Scanner(System.in);
        String routeNewFile = scNewFile.nextLine();
        Path newFilePath = Path.of(routeNewFile);
        try (BufferedReader lector = new BufferedReader(new FileReader(String.valueOf(filePath)))) {
            String linea;
            StringBuilder formatoOrdenado = new StringBuilder();
            while ((linea = lector.readLine()) != null) {
                    int posicionPrimerBS = linea.indexOf(" ");
                if (posicionPrimerBS == -1) {
                    continue;
                }
                    String coche = linea.substring(0, posicionPrimerBS);
                    String marca = linea.substring(posicionPrimerBS + 1);
                    cocheMarca.merge(coche, marca, (a, b) -> a + ", " + b);
                    }
            for (HashMap.Entry<String, String> entry : cocheMarca.entrySet()){
                formatoOrdenado.append(entry.getKey()).append(": ").append(entry.getValue()).append('\n');
            }
            fileReader(fileMaker(formatoOrdenado.toString(), newFilePath, "marcas.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static Path fileMaker(String formatoOrdenado, Path newFilePath, String fileName) {
        try {
            Path filePath = Path.of(newFilePath.toString()+fileName);
            try {
                Files.createFile(filePath);
                System.out.println("Se creó el fichero" + '\n');
                try (BufferedWriter escritor = new BufferedWriter(new FileWriter(String.valueOf(filePath),true))) {
                    escritor.write(formatoOrdenado);
                }catch (IOException e){
                    System.err.println(e.getMessage());
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (InvalidPathException e) {
            System.out.println("El Path introducido no es correcto");
        }
        return Path.of(newFilePath +fileName);
    }
    public static void fileReader(Path newfilePath) {
        try (var lector = new Scanner(newfilePath)){
            while (lector.hasNextLine()) {
                System.out.println(lector.nextLine());
           }
        }catch (IOException e){
           throw new RuntimeException(e);
        }
    }
}

