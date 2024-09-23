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
        String newFilePath = "res/marcas.txt";
        try (BufferedReader lector = new BufferedReader(new FileReader(String.valueOf(filePath)))) {
            String linea;
            StringBuilder marcasOrdenadas = new StringBuilder();
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
                marcasOrdenadas.append(entry.getKey()).append(": ").append(entry.getValue()).append('\n');
            }
            fileReader(fileMaker(marcasOrdenadas.toString(), Path.of(newFilePath)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static Path fileMaker(String marcasOrdenadas, Path newFilePath) {
        try {
            System.out.println("Se creó el fichero" + '\n');
            BufferedWriter escritor = Files.newBufferedWriter(newFilePath);
            escritor.write(marcasOrdenadas);
            escritor.close();
        }catch (IOException e){
            System.err.println(e.getMessage());
        }catch (InvalidPathException e) {
            System.out.println("El Path introducido no es correcto");
        }
        return newFilePath;
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

