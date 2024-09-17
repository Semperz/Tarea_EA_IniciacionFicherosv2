package org.example;

import java.nio.file.Path;
import java.util.Scanner;

public class Ordenaciones {
    public static void main(String[] args) {
        System.out.println("Dime la ubicacion del archivo:");
        Scanner scRoute = new Scanner(System.in);
        String rutaArchivo = scRoute.nextLine();
        Path file = Path.of(rutaArchivo);
        System.out.println("Dime el nombre del archivo nuevo:");
        Scanner scfileName = new Scanner(System.in);
        String fileName = scfileName.nextLine();
        System.out.println("Dime que tipo de ordenación quieres de las siguientes:" +
                "ascendente case-sensitive/ascendente case-insensitive/descendente case-sensitive/descendente case-insensitive");
        Scanner scSort = new Scanner(System.in);
        String sortType = scSort.nextLine();

    }
}
