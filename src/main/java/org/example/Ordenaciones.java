package org.example;

import java.io.*;

import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;

import java.util.List;
import java.util.Scanner;


public class Ordenaciones {
    public static void main(String[] args) {
        System.out.println("Dime la ubicacion del archivo:");
        Scanner scRoute = new Scanner(System.in);
        String rutaArchivo = scRoute.nextLine();
        System.out.println("Pon el numero del tipo de ordenación quieres de las siguientes:" + '\n' +
                "1. ascendente case-sensitive" + '\n' +
                "2. ascendente case-insensitive" + '\n' +
                "3. descendente case-sensitive" + '\n' +
                "4. descendente case-insensitive");
        Scanner scSort = new Scanner(System.in);
        String sortType = scSort.nextLine();
        String nuevaRutaArchivo;
        try {
            List<String> linea = Files.readAllLines(Paths.get(rutaArchivo));
            String resultadoOrdenado;
            switch (sortType) {
                case "1":
                    resultadoOrdenado = linea.stream().sorted().toList().toString();
                    nuevaRutaArchivo = rutaArchivo.replace(".txt", "-ascendente-case-sensitive.txt");
                    break;
                case "2":
                    resultadoOrdenado = linea.stream().sorted(String.CASE_INSENSITIVE_ORDER).toList().toString();
                    nuevaRutaArchivo = rutaArchivo.replace(".txt", "-ascendente-case-insensitive.txt");
                    break;
                case "3":
                    resultadoOrdenado = linea.stream().sorted(Comparator.reverseOrder()).toList().toString();
                    nuevaRutaArchivo = rutaArchivo.replace(".txt", "-descendente-case-sensitive.txt");
                    break;
                case "4":
                    resultadoOrdenado = linea.stream().sorted(String.CASE_INSENSITIVE_ORDER.reversed()).toList().toString();
                    nuevaRutaArchivo = rutaArchivo.replace(".txt", "-descendente-case-insensitive.txt");
                    break;
                default:
                    throw new IllegalArgumentException("Opcion no valida");
            }
            fileReader(fileMaker(resultadoOrdenado, Path.of(nuevaRutaArchivo)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public static Path fileMaker(String resultadoOrdenado, Path newFilePath) {
        try {
                System.out.println("Se creó el fichero" + '\n');
                BufferedWriter escritor = Files.newBufferedWriter(newFilePath);
                    escritor.write(resultadoOrdenado);
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