package org.example;

import java.io.*;

import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;

import java.util.Scanner;


public class Ordenaciones {
    public static void main(String[] args) {
        System.out.println("Dime la ubicacion del archivo:");
        Scanner scRoute = new Scanner(System.in);
        String rutaArchivo = scRoute.nextLine();
        int posicionBarra = rutaArchivo.lastIndexOf("\\");
        Path file = Path.of(rutaArchivo);
        System.out.println("Pon el numero del tipo de ordenación quieres de las siguientes:" + '\n' +
                "1. ascendente case-sensitive" + '\n' +
                "2. ascendente case-insensitive" + '\n' +
                "3. descendente case-sensitive" + '\n' +
                "4. descendente case-insensitive");
        Scanner scSort = new Scanner(System.in);
        String sortType = scSort.nextLine();
        System.out.println("Donde quieres crear el archivo nuevo?" + '\n');
        Scanner scNewFile = new Scanner(System.in);
        String routeNewFile = scNewFile.nextLine();
        Path newFilePath = Path.of(routeNewFile);
        try (BufferedReader lector = new BufferedReader(new FileReader(String.valueOf(file)))) {
            String linea;
            ArrayList<String> stringList = new ArrayList<>();
            String resultadoOrdenado = "";
            String newFileName = "";
            while ((linea = lector.readLine()) != null) {
                stringList.add(linea);
                if (sortType.charAt(0) == '1') {
                    resultadoOrdenado = stringList.stream().sorted().toList().toString();
                    newFileName = "ascendente-case-sensitive-";
                }
                if (sortType.charAt(0) == '2') {
                    resultadoOrdenado = stringList.stream().sorted(String.CASE_INSENSITIVE_ORDER).toList().toString();
                    newFileName = "ascendente-case-insensitive-";
                }
                if (sortType.charAt(0) == '3') {
                    resultadoOrdenado = stringList.stream().sorted(Comparator.reverseOrder()).toList().toString();
                    newFileName = "descendente-case-sensitive-";
                }
                if (sortType.charAt(0) == '4') {
                    resultadoOrdenado = stringList.stream().sorted(String.CASE_INSENSITIVE_ORDER.reversed()).toList().toString();
                    newFileName = "descendente-case-insensitive-";
                }
            }
            fileReader(fileMaker(resultadoOrdenado,newFilePath, newFileName +
                    rutaArchivo.substring(posicionBarra + 1)));  //Ordenacion + antiguo nombre

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static Path fileMaker(String resultadoOrdenado, Path newFilePath, String fileName) {
        try {
            Path filePath = Path.of(newFilePath.toString()+fileName);
            try {
                Files.createFile(filePath);
                System.out.println("Se creó el fichero" + '\n');
                try (BufferedWriter escritor = new BufferedWriter(new FileWriter(String.valueOf(filePath),true))) {
                    escritor.write(resultadoOrdenado);
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
        Path p = newfilePath;
        try (var lector = new Scanner(p)){
            while (lector.hasNextLine()) {
                System.out.println(lector.nextLine());
            }
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}

